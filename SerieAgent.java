import jade.core.Agent;
import jade.core.behaviours.*;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.StaleProxyException;
import java.util.Scanner;

public class SerieAgent extends Agent {
    protected void setup() {
        addBehaviour(new SerieBehaviour());
    }

    private class SerieBehaviour extends OneShotBehaviour {
        public void action() {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Ingresa el numero 'n': ");
            int n = scanner.nextInt();

            int valor = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    System.out.print(valor + " ");
                }
                valor = 1 - valor; 
            }
        }
    }

    public static void main(String[] args) {
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        AgentContainer container = rt.createMainContainer(p);

        try {
            AgentController ac = container.createNewAgent("SerieAgent", SerieAgent.class.getName(), new Object[]{});
            ac.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}





