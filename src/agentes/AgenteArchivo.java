/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes;

/**
 *
 * @author Johnny Alexander
 */
import jade.core.AID;
import jade.core.behaviours.SimpleBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/*
 Agente que controla la carga del archivo, procesandolo y enviando el contenido de 
 este al agente AgenteDM
 */
public class AgenteArchivo extends GuiAgent {

    BufferedReader file = null;//Buffer para el archivo a leer
    private JfrmAgenteArchivo vfile;//objeto de la interfaz grafica
    AgenteArchivo ag = this;//variable que se almacena el mismo para ser accedido por la accion del agente

    /*Evento llamado desde la interfaz grafica*/
    @Override
    protected void onGuiEvent(GuiEvent ge) {

        ACLMessage msg = new ACLMessage(ACLMessage.CONFIRM);//se define objeto de tipo mensaje

        try {
            String ruta = vfile.obtenerRuta();
            if (!ruta.equals("")) { //si se ha seleccionado archivo
                file = new BufferedReader(new FileReader(ruta)); //Se lee el archivo
                msg.setContent(vfile.convertir(file));//se le añade el contenido al objeto de tipo mensaje, convirtiendo el Buffer en un String
                msg.addReceiver(new AID("dm", AID.ISLOCALNAME));//AID= Agent identification, se le añade a quien se le envia
                send(msg); //el agente actual envia el mensaje                
                file.close();//se cierra el archivo
            } else {
                JOptionPane.showMessageDialog(vfile, "Por favor seleccione un archivo");
            }
        } catch (IOException ex) {
            Logger.getLogger(AgenteArchivo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vfile, ex.getMessage());
        }

    }

    /*Comportamiento del agente, el cual se encargara de cargar la interfaz*/
    class CargarArchivo extends SimpleBehaviour {

        @Override
        public void action() {
            vfile = new JfrmAgenteArchivo(ag);//se instancia la interfaz
            vfile.setVisible(true);//se muestra la interfaz
        }

        @Override
        public boolean done() {
            return true;
        }
    }
    /*
     * Convierte un elemento Buffer a un String y lo retorna
     */

    /*Aqui se añaden los comportamientos del agente, se añade el comportamiento CargarArchivo*/
    protected void setup() {
        CargarArchivo cs = new CargarArchivo();//instanciamos el comportamiento
        this.addBehaviour(cs);//añadimos el comportamiento al agente
    }
}
