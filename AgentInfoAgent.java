import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.StaleProxyException;
import java.util.Date;

public class AgentInfoAgent extends Agent {
    protected void setup() {
        addBehaviour(new AgentInfoBehaviour());
    }

    private class AgentInfoBehaviour extends OneShotBehaviour {
        public void action() {
            // Obtener el GUID del agente
            String agentGUID = getAID().getName();

            // Obtener el nombre del agente
            String agentName = getLocalName();

            // Obtener la fecha actual de ejecución del agente
            Date currentDate = new Date();

            System.out.println("Información del agente:");
            System.out.println("GUID del agente: " + agentGUID);
            System.out.println("Nombre del agente: " + agentName);
            System.out.println("Fecha de ejecución: " + currentDate);
        }
    }

    public static void main(String[] args) {
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        AgentContainer container = rt.createMainContainer(p);

        try {
            AgentController ac = container.createNewAgent("AgentInfoAgent", AgentInfoAgent.class.getName(), new Object[]{});
            ac.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}






