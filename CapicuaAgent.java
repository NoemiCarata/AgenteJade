import jade.core.Agent;
import jade.core.behaviours.*;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.StaleProxyException;
import java.util.Scanner;

public class CapicuaAgent extends Agent {
    protected void setup() {
        addBehaviour(new CapicuaBehaviour());
    }

    private class CapicuaBehaviour extends OneShotBehaviour {
        public void action() {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Ingresa un numero de dos o mas dígitos: ");
            int numero = scanner.nextInt();

            if (esCapicua(numero)) {
                System.out.println(numero + " es un numero capicua.");
            } else {
                System.out.println(numero + " no es un numero capicua.");
            }
        }
	
        private boolean esCapicua(int numero) {
            int numeroOriginal = numero;
            int numeroReverso = 0;

            while (numero > 0) {
                int digito = numero % 10;
                numeroReverso = numeroReverso * 10 + digito;
                numero /= 10;
            }

            return numeroReverso == numeroOriginal;
        }
    }

    public static void main(String[] args) {
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        AgentContainer container = rt.createMainContainer(p);

        try {
            AgentController ac = container.createNewAgent("CapicuaAgent", CapicuaAgent.class.getName(), new Object[]{});
            ac.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}





