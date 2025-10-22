package com.src;

import javax.swing.JOptionPane;

public class Agenda{
    public static void main(String[] args) {
        String[] options = {"Cadastrar Amigo na Agenda", "Buscar Amigo pelo Nome", "Aniversariantes do Mês", "Listar todos os Amigos", "Sair da Agenda"};
        int x=0;
        String menu =
            "=====================\n" +
            "Menu de Opções\n" +
            "=====================\n\n" +
            "1 - Cadastrar Amigo na Agenda\n" +
            "2 - Buscar Amigo pelo Nome\n" +
            "3 - Aniversariantes no Mês\n" +
            "4 - Listar todos os Amigos\n" +
            "5 - Sair da Agenda\n\n" +
            "Escolha uma opção:";
        JOptionPane jOptionPane = new JOptionPane();
        while(true){
            String z=JOptionPane.showOptionDialog(null,
                null,
               menu,
            "Menu",
            JOptionPane.QUESTION_MESSAGE);  
            int y=Integer.parseInt(z);

            switch(y){
                case 1: 
                    GerenciarAmigo.cadastrarAmigo();
                    break;
                case 2: 
                    GerenciarAmigo.buscarAmigo();
                    break;
                case 3: 
                    GerenciarAmigo.aniversariantesDoMes();
                    break;
                case 4: 
                    GerenciarAmigo.listarTodosAmigos();
                    break;
                case 5: 
                    JOptionPane.showMessageDialog(null, "Saindo da Agenda...");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida!");
    };
}
}
}