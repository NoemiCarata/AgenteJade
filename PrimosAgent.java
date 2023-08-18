import jade.core.Agent;
import jade.core.behaviours.*;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.StaleProxyException;
import java.util.Scanner;

public class PrimosAgent extends Agent {
    protected void setup() {
        addBehaviour(new PrimosBehaviour());
    }

    private class PrimosBehaviour extends OneShotBehaviour {
        public void action() {
            Scanner scanner = new Scanner(System.in);
            int[] numeros = new int[4];

            System.out.println("Verificar si es numeros primos");
            for (int i = 0; i < 4; i++) {
                System.out.print("Ingresa el numero " + (i + 1) + ": ");
                numeros[i] = scanner.nextInt();
            }

            for (int i = 0; i < 4; i++) {
                if (esPrimo(numeros[i])) {
                    System.out.println(numeros[i] + " es un numero primo.");
                } else {
                    System.out.println(numeros[i] + " no es un numero primo.");
                }
            }
        }
    }

    private boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        AgentContainer container = rt.createMainContainer(p);

        try {
            AgentController ac = container.createNewAgent("PrimosAgent", PrimosAgent.class.getName(), new Object[]{});
            ac.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
