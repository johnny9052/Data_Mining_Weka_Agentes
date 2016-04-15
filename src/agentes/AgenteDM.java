/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Johnny Alexander 
 *
 */
package agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import weka.associations.Apriori;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

/**
 * Agente que recibe la informacion del archivo cargado proveniente del agente
 * AgenteArchivo, este se encarga de aplicar los procesos de mineria de datos y
 * envia los resultados al AgenteResultado
 */
public class AgenteDM extends Agent {
    
    /*Comportamiento del agente, realiza todo el proceso de mineria de datos*/
    public class aplicarClasificacion extends SimpleBehaviour {

        public void action() {

            ACLMessage msg = this.myAgent.blockingReceive();//accede al agente que tenga la accion y lo bloquea para que solo quede esperando el mensaje        
            String mensaje = msg.getContent();//recibimos el mensaje
            StringReader sr = new StringReader(mensaje); // El mensaje tipo String lo convertimos a un StringReader
            BufferedReader br = new BufferedReader(sr); // el StringReader lo convertimos a un Buffer

            try {
                DecimalFormat formato = new DecimalFormat("#.##");//definimos el formato para los decimales
                Instances data = new Instances(br);//definimos un objeto que contendra los datos a clasificar
                data.setClassIndex(data.numAttributes() - 1);//Seleccionamos cual sera el atributo clase                
                br.close();//cerramos el objeto buffer

                NaiveBayes nb = new NaiveBayes(); //Creamos un clasificador Bayesiano                
                J48 j48 = new J48(); // Creamos un clasidicador J48
                Apriori aso = new Apriori();

                //Classifier cls3 = new MultilayerPerceptron();
                nb.buildClassifier(data);//creamos el clasificador de la redBayesiana 
                j48.buildClassifier(data);//creamos el clasificador  del J48 con los datos 
                aso.buildAssociations(data);

                /*Se define el encabezado del mensaje, teniendo en cuanta el atributo clase*/
                String descripcion = "<b>El atributo clase seleccionado es " + data.attribute(data.numAttributes() - 1).name() + "</b>";
                descripcion += " <b>con posibles valores:</b> ";
                /*Se recorren los posibles valores del atributo clase*/
                for (int z = 0; z < data.attribute(data.numAttributes() - 1).numValues(); z++) {
                    descripcion += "<b>" + data.attribute(data.numAttributes() - 1).value(z) + "</b> ";
                }

                Evaluation evalB = new Evaluation(data);//Creamos un objeto para la validacion del modelo con redBayesiana
                Evaluation evalJ48 = new Evaluation(data);//Creamos un objeto para la validacion del modelo con redBayesiana

                /*Aplicamos el clasificador bayesiano*/
                evalB.crossValidateModel(nb, data, 10, new Random(1));//hacemos validacion cruzada, de redBayesiana, con 10 campos, y el aleatorio                                
                String resBay = "<br><br><b><center>Resultados NaiveBayes</center><br>========<br>Modelo generado indica los siguientes resultados:<br>========<br></b>";//Obtenemos resultados
                resBay = resBay + ("<b>1. Numero de instancias clasificadas:</b> " + (int) evalB.numInstances() + "<br>");
                resBay = resBay + ("<b>2. Porcentaje de instancias correctamente clasificadas:</b> " + formato.format(evalB.pctCorrect()) + "%<br>");
                resBay = resBay + ("<b>3. Numero de instancias correctamente clasificadas:</b> " + (int) evalB.correct() + "<br>");
                resBay = resBay + ("<b>4. Porcentaje de instancias incorrectamente clasificadas:</b> " + formato.format(evalB.pctIncorrect()) + "%<br>");
                resBay = resBay + ("<b>5. Numero de instancias incorrectamente clasificadas:</b> " + (int) evalB.incorrect() + "<br>");
                resBay = resBay + ("<b>6. Media del error absoluto:</b> " + formato.format(evalB.meanAbsoluteError()) + "%<br>");
                resBay = resBay + ("<b>7. " + evalB.toMatrixString("Matriz de confusion</b>").replace("\n", "<br>"));


                /*Aplicamos el clasificador J48*/
                evalJ48.crossValidateModel(j48, data, 10, new Random(1));//hacemos validacion cruzada, de redBayesiana, con 10 campos, y el aleatorio                 
                String resJ48 = "<br><b><center>Resultados Arbol de decision J48</center><br>========<br>Modelo generado indica los siguientes resultados:<br>========<br></b>";//Obtenemos resultados

                resJ48 = resJ48 + ("<b>1. Numero de instancias clasificadas:</b> " + (int) evalJ48.numInstances() + "<br>");
                resJ48 = resJ48 + ("<b>2. Porcentaje de instancias correctamente clasificadas:</b> " + formato.format(evalJ48.pctCorrect()) + "<br>");
                resJ48 = resJ48 + ("<b>3. Numero de instancias correctamente clasificadas:</b>" + (int) evalJ48.correct() + "<br>");
                resJ48 = resJ48 + ("<b>4. Porcentaje de instancias incorrectamente clasificadas:</b> " + formato.format(evalJ48.pctIncorrect()) + "<br>");
                resJ48 = resJ48 + ("<b>5. Numero de instancias incorrectamente clasificadas:</b> " + (int) evalJ48.incorrect() + "<br>");
                resJ48 = resJ48 + ("<b>6. Media del error absoluto:</b> " + formato.format(evalJ48.meanAbsoluteError()) + "<br>");
                resJ48 = resJ48 + ("<b>7. " + evalJ48.toMatrixString("Matriz de confusion</b>").replace("\n", "<br>"));

                // Se grafica el arbol de decision 
                final javax.swing.JFrame jf = new javax.swing.JFrame("Arbol de decision: J48");
                jf.setSize(500, 400);
                jf.getContentPane().setLayout(new BorderLayout());
                TreeVisualizer tv = new TreeVisualizer(null, j48.graph(), new PlaceNode2());
                jf.getContentPane().add(tv, BorderLayout.CENTER);
                jf.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        jf.dispose();
                    }
                });

                jf.setVisible(true);
                tv.fitToScreen();

                /*Se cargan los resultados de loa asociacion apriori*/
                String resApriori = "<br><b><center>Resultados Asociacion Apriori</center><br>========<br>El modelo de asociacion generado indica los siguientes resultados:<br>========<br></b>";//Obtenemos resultados
                for (int i = 0; i < aso.getAssociationRules().getRules().size(); i++) {
                    resApriori = resApriori + "<b>" + (i + 1) + ". Si</b> " + aso.getAssociationRules().getRules().get(i).getPremise().toString();
                    resApriori = resApriori + " <b>Entonces</b> " + aso.getAssociationRules().getRules().get(i).getConsequence().toString();
                    resApriori = resApriori + " <b>Con un</b> " + (int) (aso.getAssociationRules().getRules().get(i).getPrimaryMetricValue() * 100) + "% de probabilidad<br>";
                }

                /*Se concatenan resultados y se envian*/
                ACLMessage resultado = new ACLMessage(ACLMessage.CONFIRM);//se define objeto de tipo mensaje
                resultado.setContent(descripcion + "\n" + resBay + "\n" + resJ48 + "\n" + resApriori);//se le añade el contenido al objeto de tipo mensaje
                resultado.addReceiver(new AID("pantalla", AID.ISLOCALNAME));//AID= Agent identification, se le añade a quien se le envia
                this.myAgent.send(resultado); //el agente actual envia el mensaje

            } catch (Exception e) {
                System.out.println("El error es" + e.getMessage());
                JFrame MiVentana = new JFrame("Error"); //llamamos a la clase y creamos un objeto llamado MiVentana 
                JOptionPane.showMessageDialog(MiVentana, e.getMessage());
            }
        }

        @Override
        public boolean done() {
            return false;
        }
    }

    /*Asignacion de comportamientos al agente, se asigna el comportamiento aplicarClasificacion*/
    protected void setup() {
        aplicarClasificacion cs = new aplicarClasificacion();
        this.addBehaviour(cs);
    }
}
