/*  
    PROJECT MADE BY:
    -----
    Guilherme Marques de Lima       - 248151
    Luis Fillipe de Medeiros Silva  - 248370
    Vittorio Pivarci                - 248674

    
    GITHUB: https://github.com/ValkorProjects/contacts-list-app
*/

import com.src.*;
import com.tools.StringAnalyzer;
import com.tools.Validate;
import java.time.LocalDate;
import java.time.Year;
import javax.swing.JOptionPane;

public class Agenda {
    public static void main(String[] args) {
        String menu =
            """
            =====================
            Menu de Op\u00e7\u00f5es
            =====================
            
            1 - Cadastrar Amigo na Agenda
            2 - Buscar Amigo pelo Nome
            3 - Aniversariantes no M\u00eas
            4 - Listar todos os Amigos
            5 - Sair da Agenda
            
            Escolha uma op\u00e7\u00e3o (1-5):""";

        GerenciarAmigo manager = new GerenciarAmigo();

        while (true) {
            String input = JOptionPane.showInputDialog(null, menu, "Menu", JOptionPane.QUESTION_MESSAGE);
            int option;
            
            if (input == null) { // user canceled
                continue;
            }

            try {
                option = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Digite um número entre 1 e 5.");
                continue;
            }

            switch (option) {
                //Cadastrar Amigo na Agenda
                case 1 ->  {
                    String nome = Validate.getValidatedInput("Nome:", 
                    (string) -> {
                        return string.length() < 50;
                    }, 
                    "O nome de usuário excede 50 caracteres.",
                    false);

                    String telefone = Validate.getValidatedInput("Telefone:", 
                    (string) -> {
                        return (string.length() <= 11 && string.length() >= 8);
                    }, 
                    "O numero de telefone excede 11 dígitos.",
                    false);
                    
                    String email = Validate.getValidatedInput("Email:", 
                    (string) -> {
                        return ((string.endsWith("@gmail.com")      || 
                                string.endsWith("@hotmail.com")     ||
                                string.endsWith("@outlook.com"))    &&
                                string.length() < 50);
                    }, 
                    "Email não termina no domínio correto ou muito longo.",
                    false);
                    
                    LocalDate data = Validate.getValidatedDateInput("Data de nascimento (dd/MM/yyyy):", 
                    (string) -> {
                        if (string == null) return false;
                        return (string.length() <= 10 && !string.endsWith(Year.now().toString()));
                    }, 
                    "Data inválida.",
                    false); 
                    
                    //###########
                    // Endereço
                    //? For address NAMES, ensure the user cannot input numbers
                    //###########
                    String rua = Validate.getValidatedInput("Endereço - Rua:", 
                    (string) -> {
                        return (StringAnalyzer.countConditionOccurences(string, (ch) -> {return (ch >= '0' && ch <= '9');}) <= 0);
                    }, 
                    "O nome da rua não deve conter números.",
                    false);

                    int numero = (int)Validate.getValidatedInputConvert("INTEGER",
                    "Endereço - Número (deixe vazio se não houver):", 
                    (_int) -> {
                        if(_int == null)
                        {
                            return false;
                        }

                        try {
                            if (!_int.isBlank()) {
                                Integer.valueOf(_int.trim());
                                return true;
                            }
                            else return false;
                        } catch (NumberFormatException ex) {
                            return false;
                        }
                    }, 
                    "O valor digitado não é válido.",
                    true);
                    
                    String complemento = JOptionPane.showInputDialog("Endereço - Complemento:");

                    String cidade = Validate.getValidatedInput("Endereço - Cidade:", 
                    (string) -> {
                        return (StringAnalyzer.countConditionOccurences(string, (ch) -> {return (ch >= '0' && ch <= '9');}) <= 0);
                    }, 
                    "O nome da cidade não deve conter números.",
                    false);

                    String estado = Validate.getValidatedInput("Endereço - Estado:", 
                    (string) -> {
                        return (StringAnalyzer.countConditionOccurences(string, (ch) -> {return (ch >= '0' && ch <= '9');}) <= 0);
                    }, 
                    "O nome da estado não deve conter números.",
                    false);

                    String cep = Validate.getValidatedInput("Endereço - CEP (ex: 12345-678 ou 12345678):", 
                    (string) -> {
                        if (string == null) return false;
                        return string.matches("^\\d{5}-?\\d{3}$");
                    },
                    "CEP incorreto. Use 12345-678 ou 12345678.",
                    false); 

                    // normaliza para somente dígitos antes de salvar
                    if (cep != null) {
                        cep = cep.replaceAll("\\D", "");
                    }

                    Amigo a = new Amigo(nome, telefone, email, data);

                    Endereco end = new Endereco();
                    end.setAddressStreet(rua);
                    end.setAddressNumber(numero);
                    end.setAddressComplement(complemento);
                    end.setAddressCity(cidade);
                    end.setAddressState(estado);
                    end.setAddressPostalCode(cep);
                    a.setEndereco(end); // Junta endereço com amigo

                    String res = manager.cadastrarAmigo(a);
                    JOptionPane.showMessageDialog(null, res);
                }
                //Buscar Amigo pelo Nome
                case 2 ->  {
                    String nome = JOptionPane.showInputDialog("Nome para buscar:");
                    String res = manager.buscarAmigoPeloNome(nome);
                    JOptionPane.showMessageDialog(null, res);
                }
                //Aniversariantes no Mes
                case 3 ->  {
                    boolean done = false;
                    while (!done) {
                        String mesStr = JOptionPane.showInputDialog(null, "Digite o mês (1-12):");
                        if (mesStr == null) break; // usuário cancelou

                        mesStr = mesStr.trim();
                        if (mesStr.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Entrada vazia. Digite um número entre 1 e 12.");
                            continue;
                        }

                        int mes;
                        try {
                            mes = Integer.parseInt(mesStr);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Mês inválido. Digite um número entre 1 e 12.");
                            continue;
                        }

                        if (mes < 1 || mes > 12) {
                            JOptionPane.showMessageDialog(null, "Mês fora do intervalo. Digite um número entre 1 e 12.");
                            continue;
                        }

                        String res = manager.aniversariarNoMes(mes);
                        JOptionPane.showMessageDialog(null, res);
                        done = true;
                    }
                }
                //Listar todos os Amigos
                case 4 ->  {
                    String res = manager.listarTodosAmigos();
                    JOptionPane.showMessageDialog(null, res);
                }
                //Sair
                case 5 -> {
                    JOptionPane.showMessageDialog(null, "Saindo da Agenda...");
                    System.exit(0);
                }
                default -> JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }
}