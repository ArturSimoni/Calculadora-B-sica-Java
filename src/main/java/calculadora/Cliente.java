package calculadora;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    public static void main(String[] args) {
        String host = (args.length > 0) ? args[0] : "localhost";

        try {
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            
            Calculadora calc = (Calculadora) registry.lookup("CalculadoraService");
            
            double num1 = 10.0;
            double num2 = 5.0;
            
            System.out.println("--- Testando Calculadora Remota ---");
            System.out.println(num1 + " + " + num2 + " = " + calc.somar(num1, num2));
            System.out.println(num1 + " - " + num2 + " = " + calc.subtrair(num1, num2));
            System.out.println(num1 + " * " + num2 + " = " + calc.multiplicar(num1, num2));
            System.out.println(num1 + " / " + num2 + " = " + calc.dividir(num1, num2));
            
        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}