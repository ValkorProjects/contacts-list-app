import java.text.DateFormat;
import java.util.Date;

import com.src.*;

public class App {
    public static void main(String[] args) {
        Amigo a = new Amigo();
        try{ 
            var data = DateFormat.getDateTimeInstance(3, 1, null).parse("22/04/2006");
            a.setBirthdate(data);
            System.out.println("Dias até aniversário: "+a.calcularDiasParaAniversariar());
        } catch (Exception ex){
            System.out.println("Erro convertendo data");
        }
        // System.out.println("nao me hackeia ofv cesar S2 desculpa :()");
    }
}
