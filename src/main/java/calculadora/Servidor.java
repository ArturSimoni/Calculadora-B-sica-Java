package calculadora;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            
            Calculadora calc = new CalculadoraImpl();
            
            // Registra o objeto remoto com o nome "CalculadoraService"
            registry.rebind("CalculadoraService", calc);
            
            System.out.println("Servidor da Calculadora RMI pronto e rodando...");
        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}