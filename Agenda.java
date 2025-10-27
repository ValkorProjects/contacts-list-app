/*  
    PROJECT MADE BY:
    -----
    Guilherme Marques de Lima       - 248151
    Luis Fillipe de Medeiros Silva  - 248370
    Vittorio Pivarci                - 248674

    
    GITHUB: https://github.com/ValkorProjects/contacts-list-app
*/

import com.src.*;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;

public class Agenda {

    // aceita letras, números, pontuação comum e espaços — rejeita emojis/símbolos (\p{S})
    private static final Pattern ALLOWED_TEXT = Pattern.compile("^[\\p{L}\\p{N}\\p{P}\\p{Zs}]+$");

    private static boolean isAllowedText(String s) {
        return s != null && !s.isBlank() && ALLOWED_TEXT.matcher(s).matches();
    }

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
                    // Nome (obrigatório)
                    String nome;
                    while (true) {
                        nome = JOptionPane.showInputDialog(null, "Nome: ");
                        if (nome == null) { /* cancel */ break; }
                        nome = nome.trim();
                        if (nome.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Nome vazio. Digite um nome.");
                            continue;
                        }
                        if (nome.length() > 50) {
                            JOptionPane.showMessageDialog(null, "Nome muito longo (máx 50 caracteres).");
                            continue;
                        }
                        if (!isAllowedText(nome)) {
                            JOptionPane.showMessageDialog(null, "Caracteres inválidos no nome. Não use emojis ou símbolos.");
                            continue;
                        }
                        break;
                    }
                    if (nome == null) break;

                    // Telefone (obrigatório)
                    String telefone;
                    while (true) {
                        telefone = JOptionPane.showInputDialog(null, "Telefone (8 a 11 dígitos): ");
                        if (telefone == null) { telefone = null; break; }
                        telefone = telefone.trim();
                        if (telefone.isEmpty() || telefone.length() < 8 || telefone.length() > 11 || !telefone.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, "Telefone inválido. Use apenas dígitos (8 a 11).");
                            continue;
                        }
                        break;
                    }
                    if (telefone == null) break;

                    // Email (obrigatório)
                    String email;
                    while (true) {
                        email = JOptionPane.showInputDialog(null, "Email (ex: usuario@gmail.com):");
                        if (email == null) { email = null; break; }
                        email = email.trim();
                        if (email.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Email vazio. Digite um email.");
                            continue;
                        }
                        if (email.length() >= 50) {
                            JOptionPane.showMessageDialog(null, "Email muito longo.");
                            continue;
                        }
                        if (!isAllowedText(email) || !(email.endsWith("@gmail.com") || email.endsWith("@hotmail.com") || email.endsWith("@outlook.com"))) {
                            JOptionPane.showMessageDialog(null, "Email inválido ou contém caracteres não permitidos.");
                            continue;
                        }
                        break;
                    }
                    if (email == null) break;

                    // Data de nascimento (OBRIGATÓRIA)
                    boolean cancelRegistration = false;
                    java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate data = null;
                    while (true) {
                        String dataStr = JOptionPane.showInputDialog(null, "Data de nascimento (dd/MM/yyyy): ");
                        if (dataStr == null) { JOptionPane.showMessageDialog(null, "Cadastro cancelado."); cancelRegistration = true; break; }
                        dataStr = dataStr.trim();
                        if (dataStr.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Data de nascimento obrigatória. Digite no formato dd/MM/yyyy.");
                            continue;
                        }
                        try {
                            LocalDate parsed = LocalDate.parse(dataStr, formatter);
                            if (parsed.isAfter(LocalDate.now())) {
                                JOptionPane.showMessageDialog(null, "Data no futuro. Digite uma data válida.");
                                continue;
                            }
                            if (parsed.isBefore(LocalDate.of(1900, 1, 1))) {
                                JOptionPane.showMessageDialog(null, "Data muito antiga. Digite uma data válida.");
                                continue;
                            }
                            data = parsed;
                            break;
                        } catch (java.time.format.DateTimeParseException ex) {
                            JOptionPane.showMessageDialog(null, "Formato inválido. Use dd/MM/yyyy.");
                        }
                    }
                    if (cancelRegistration) break;

                    // Rua (obrigatória)
                    String rua;
                    while (true) {
                        rua = JOptionPane.showInputDialog(null, "Endereço - Rua: ");
                        if (rua == null) { rua = null; break; }
                        rua = rua.trim();
                        if (rua.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Rua vazia. Digite a rua.");
                            continue;
                        }
                        if (!isAllowedText(rua)) {
                            JOptionPane.showMessageDialog(null, "Caracteres inválidos na rua. Não use emojis ou símbolos.");
                            continue;
                        }
                        break;
                    }
                    if (rua == null) break;

                    // Número (obrigatório)
                    Integer numero = null;
                    while (true) {
                        String numeroStr = JOptionPane.showInputDialog(null, "Endereço - Número: ");
                        if (numeroStr == null) { numero = null; break; }
                        numeroStr = numeroStr.trim();
                        if (numeroStr.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Número obrigatório. Digite o número do endereço.");
                            continue;
                        }
                        try {
                            int n = Integer.parseInt(numeroStr);
                            if (n <= 0) { JOptionPane.showMessageDialog(null, "Número inválido. Deve ser maior que zero."); continue; }
                            numero = n;
                            break;
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Número inválido. Digite apenas dígitos.");
                        }
                    }
                    if (numero == null) break;

                    // Complemento (AGORA obrigatório)
                    String complemento;
                    while (true) {
                        complemento = JOptionPane.showInputDialog(null, "Endereço - Complemento: ");
                        if (complemento == null) { complemento = null; break; }
                        complemento = complemento.trim();
                        if (complemento.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Complemento obrigatório. Digite o complemento.");
                            continue;
                        }
                        if (!isAllowedText(complemento)) {
                            JOptionPane.showMessageDialog(null, "Caracteres inválidos no complemento.");
                            continue;
                        }
                        break;
                    }
                    if (complemento == null) break;

                    // Cidade (obrigatória)
                    String cidade;
                    while (true) {
                        cidade = JOptionPane.showInputDialog(null, "Endereço - Cidade: ");
                        if (cidade == null) { cidade = null; break; }
                        cidade = cidade.trim();
                        if (cidade.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Cidade vazia. Digite a cidade.");
                            continue;
                        }
                        if (!isAllowedText(cidade)) {
                            JOptionPane.showMessageDialog(null, "Caracteres inválidos na cidade. Não use emojis ou símbolos.");
                            continue;
                        }
                        break;
                    }
                    if (cidade == null) break;

                    // Estado (obrigatório)
                    String estado;
                    while (true) {
                        estado = JOptionPane.showInputDialog(null, "Endereço - Estado: ");
                        if (estado == null) { estado = null; break; }
                        estado = estado.trim();
                        if (estado.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Estado vazio. Digite o estado.");
                            continue;
                        }
                        if (!isAllowedText(estado)) {
                            JOptionPane.showMessageDialog(null, "Caracteres inválidos no estado. Não use emojis ou símbolos.");
                            continue;
                        }
                        break;
                    }
                    if (estado == null) break;

                    // CEP (obrigatório)
                    String cep;
                    while (true) {
                        cep = JOptionPane.showInputDialog(null, "Endereço - CEP (ex: 12345-678 ou 12345678): ");
                        if (cep == null) { cep = null; break; }
                        cep = cep.trim();
                        if (!cep.matches("^\\d{5}-?\\d{3}$")) {
                            JOptionPane.showMessageDialog(null, "CEP inválido. Use 12345-678 ou 12345678.");
                            continue;
                        }
                        cep = cep.replaceAll("\\D", "");
                        break;
                    }
                    if (cep == null) break;

                    // monta objeto Amigo/Endereco e salva
                    Amigo a = new Amigo(nome, telefone, email, data);
                    Endereco end = new Endereco();
                    end.setAddressStreet(rua);
                    end.setAddressNumber(numero);
                    end.setAddressComplement(complemento);
                    end.setAddressCity(cidade);
                    end.setAddressState(estado);
                    end.setAddressPostalCode(cep);
                    a.setEndereco(end);

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