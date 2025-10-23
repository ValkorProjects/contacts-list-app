

import com.src.Amigo;
import com.src.Endereco;    
import com.src.GerenciarAmigo;
import java.awt.HeadlessException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            String input = JOptionPane.showInputDialog(null, menu, "Menu", JOptionPane.QUESTION_MESSAGE);
            if (input == null) { // user canceled
                continue;
            }

            int option;
            try {
                option = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Digite um número entre 1 e 5.");
                continue;
            }

            switch (option) {
                case 1 ->  {
                    String nome = JOptionPane.showInputDialog("Nome:");
                    String telefone = JOptionPane.showInputDialog("Telefone:");
                    String email = JOptionPane.showInputDialog("Email:");
                    String dataStr = JOptionPane.showInputDialog("Data de nascimento (dd/MM/yyyy):");
                    LocalDate data = null;
                    try {
                        if (dataStr != null && !dataStr.isBlank()) {
                            data = LocalDate.parse(dataStr.trim(), formatter);
                        }
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(null, "Data inválida. Cadastro cancelado.");
                    }

                    // Endereço
                    String rua = JOptionPane.showInputDialog("Endereço - Rua:");
                    String numeroStr = JOptionPane.showInputDialog("Endereço - Número (deixe vazio se não houver):");
                    int numero = 0;
                    try {
                        if (numeroStr != null && !numeroStr.isBlank()) {
                            numero = Integer.parseInt(numeroStr.trim());
                        }
                    } catch (NumberFormatException ex) {
                        numero = 0;
                    }
                    String complemento = JOptionPane.showInputDialog("Endereço - Complemento:");
                    String cidade = JOptionPane.showInputDialog("Endereço - Cidade:");
                    String estado = JOptionPane.showInputDialog("Endereço - Estado:");
                    String cep = JOptionPane.showInputDialog("Endereço - CEP:");

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
                case 2 ->  {
                    String nome = JOptionPane.showInputDialog("Nome para buscar:");
                    String res = manager.buscarAmigoPeloNome(nome);
                    JOptionPane.showMessageDialog(null, res);
                }
                case 3 ->  {
                    //Loop until input data is correct. 
                    String mesStr = JOptionPane.showInputDialog("Digite o mês (1-12):");
                    boolean isDataCorrect = false;
                    while(!isDataCorrect && mesStr != null){
                        try {
                            mesStr = JOptionPane.showInputDialog("Digite o mês (1-12):");
                            int mes = Integer.parseInt(mesStr.trim());
                            String res = manager.aniversariarNoMes(mes);
                            JOptionPane.showMessageDialog(null, res);
                            isDataCorrect = true;
                        }
                        catch (NumberFormatException e){
                            JOptionPane.showMessageDialog(null, "O número do mês é inválido");
                        }
                        catch (HeadlessException e){
                            JOptionPane.showMessageDialog(null, "O dispositivo não fornece suporte à função requisitada. ");
                        }
                        catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }   
                    }
                }
                case 4 ->  {
                    String res = manager.listarTodosAmigos();
                    JOptionPane.showMessageDialog(null, res);
                }
                case 5 -> {
                    JOptionPane.showMessageDialog(null, "Saindo da Agenda...");
                    System.exit(0);
                }
                default -> JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }
}