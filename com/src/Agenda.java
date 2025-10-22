package com.src;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Agenda {
    public static void main(String[] args) {
        String menu =
            "=====================\n" +
            "Menu de Opções\n" +
            "=====================\n\n" +
            "1 - Cadastrar Amigo na Agenda\n" +
            "2 - Buscar Amigo pelo Nome\n" +
            "3 - Aniversariantes no Mês\n" +
            "4 - Listar todos os Amigos\n" +
            "5 - Sair da Agenda\n\n" +
            "Escolha uma opção (1-5):";

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
                case 1: {
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
                        break;
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
                    break;
                }
                case 2: {
                    String nome = JOptionPane.showInputDialog("Nome para buscar:");
                    String res = manager.buscarAmigoPeloNome(nome);
                    JOptionPane.showMessageDialog(null, res);
                    break;
                }
                case 3: {
                    String mesStr = JOptionPane.showInputDialog("Digite o mês (1-12):");
                    try {
                        int mes = Integer.parseInt(mesStr.trim());
                        String res = manager.aniversariarNoMes(mes);
                        JOptionPane.showMessageDialog(null, res);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Mês inválido.");
                    }
                    break;
                }
                case 4: {
                    String res = manager.listarTodosAmigos();
                    JOptionPane.showMessageDialog(null, res);
                    break;
                }
                case 5:
                    JOptionPane.showMessageDialog(null, "Saindo da Agenda...");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }
}