/*  
    PROJECT MADE BY:
    -----
    Guilherme Marques de Lima       - 248151
    Luis Fillipe de Medeiros Silva  - 248370
    Vittorio Pivarci                - 248674

    
    GITHUB: https://github.com/ValkorProjects/contacts-list-app
*/

package com.src;

import java.time.LocalDate;
import java.util.ArrayList;

public class GerenciarAmigo {
    private final ArrayList<Amigo> amigos = new ArrayList<>();

    // Cadastra um amigo e retorna confirmação de cadastro
    public String cadastrarAmigo(Amigo a) {
        if (a == null) return "Amigo inválido.";
        amigos.add(a);
        return "Novo amigo!";
    }

    // Busca pelo nome e retorna os dados e o dias para aniversário
    public String buscarAmigoPeloNome(String nome) {
        if (nome == null || nome.isBlank()) return "Nome inválido.";
        for (Amigo a : amigos) {
            if (a.getName() != null && a.getName().equalsIgnoreCase(nome.trim())) {
                StringBuilder sb = new StringBuilder();
                sb.append(a.imprimir()).append(System.lineSeparator());

                int dias = a.calcularDiasParaAniversariar();
                switch(dias){
                    case -1 -> {
                        sb.append("Dias para aniversário: N/A").append(System.lineSeparator());
                    }
                    case 0 -> {
                        sb.append("Aniversário hoje!").append(System.lineSeparator());
                    }
                    default -> {
                        sb.append("Faltam ").append(dias).append(" dia(s) para o aniversário.").append(System.lineSeparator());
                    }
                }

                return sb.toString();
            }
        }
        return "Amigo não encontrado";
    }

    // Retorna lista de amigos que fazem aniversario no mês informado
    public String aniversariarNoMes(int mes) {
        if (mes < 1 || mes > 12) {
            return "Mês inexistente";
        }

        StringBuilder sb = new StringBuilder();
        for (Amigo a : amigos) {
            LocalDate bd = a.getBirthdate();
            if (bd != null && bd.getMonthValue() == mes) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(a.getName() == null ? "Nome desconhecido" : a.getName());
            }
        }
        return sb.length() > 0 ?
            "Amigos que fazem aniversário no mês " + mes + ": " + sb.toString() :
            "Nenhum amigo faz aniversário no mês " + mes;
    }

    // Lista todos amigos
    public String listarTodosAmigos() {
        if (amigos.isEmpty()) {
            return "Nenhum amigo cadastrado";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Lista de todos os amigos:").append(System.lineSeparator()).append(System.lineSeparator());

        int idx = 0;
        for (Amigo a : amigos) {
            idx++;
            try {
                sb.append(a.imprimir()).append(System.lineSeparator());

                int dias = a.calcularDiasParaAniversariar();
                switch(dias){
                    case -1 -> {
                        sb.append("Dias para aniversário: N/A").append(System.lineSeparator());
                    }
                    case 0 -> {
                        sb.append("Aniversário hoje!").append(System.lineSeparator());
                    }
                    default -> {
                        sb.append("Faltam ").append(dias).append(" dia(s) para o aniversário.").append(System.lineSeparator());
                    }
                }

                sb.append(System.lineSeparator()); // linha em branco entre amigos
            } catch (Exception ex) {
                sb.append("Erro ao imprimir amigo #").append(idx)
                  .append(": ").append(ex.getClass().getSimpleName())
                  .append(" - ").append(ex.getMessage())
                  .append(System.lineSeparator()).append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }
}