package calculadora;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Controller
public class CalculadoraController {

    private Calculadora obterCalculadoraRemota() throws Exception {
        String host = System.getenv("RMI_HOST") != null ? System.getenv("RMI_HOST") : "localhost";
        Registry registry = LocateRegistry.getRegistry(host, 1099);
        return (Calculadora) registry.lookup("CalculadoraService");
    }

    @GetMapping("/")
    public String paginaInicial() {
        return "index"; 
    }

    @PostMapping("/calcular")
    public String calcular(@RequestParam double num1, 
                           @RequestParam double num2, 
                           @RequestParam String operacao, 
                           Model model) {
        try {
            Calculadora calc = obterCalculadoraRemota();
            double resultado = 0;

            switch (operacao) {
                case "somar" -> resultado = calc.somar(num1, num2);
                case "subtrair" -> resultado = calc.subtrair(num1, num2);
                case "multiplicar" -> resultado = calc.multiplicar(num1, num2);
                case "dividir" -> resultado = calc.dividir(num1, num2);
            }

            model.addAttribute("resultado", "Resultado: " + resultado);
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao processar: " + e.getMessage());
        }

        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        
        return "index";
    }
}