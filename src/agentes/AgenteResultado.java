/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes;

/**
 *
 * @author Johnny Alexander
 *
 */
import jade.core.behaviours.SimpleBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

/* Agente que se encarga de controlar la visualizacion de resultados en pantalla, 
 recibe estos del AgenteDM*/
public class AgenteResultado extends GuiAgent {

    private JfrmAgenteResultado vres;//interfaz
    AgenteResultado ag = this;//variable que se almacena el mismo para ser accedido por la accion del agente

    /*Evento guiEvent, como solo muestra resultados no se ejecutara nada*/
    @Override
    protected void onGuiEvent(GuiEvent ge) {
    }

    /*Definicion del comportamiento que se encarga de mostrar resultados*/
    public class mostrarResultado extends SimpleBehaviour {

        public void action() {
            ACLMessage msg = this.myAgent.blockingReceive();//accede al agente que tenga la accion y lo bloquea para que solo quede esperando el mensaje                  
            vres.setVisible(true);//solo se muestra la interfaz cuando se ha recibido el mensaje
            String mensaje = msg.getContent();//Obtenemos el resultado
            vres.mostrarResultado(mensaje); //se llama la funcion mostrar resultado que se encuentra en el Jfrm
        }

        @Override
        public boolean done() {
            return false;
        }
    }

    /*Asignacion de comportamientos, se asigna el comportamiento mostrarResultados*/
    protected void setup() {
        vres = new JfrmAgenteResultado(ag);//se instancia la interfaz
        mostrarResultado cs = new mostrarResultado();
        this.addBehaviour(cs);
    }
}
