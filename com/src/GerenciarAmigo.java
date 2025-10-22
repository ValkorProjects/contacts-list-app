package com.src;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GerenciarAmigo{
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
            if(a.getName() != null && a.getName().equalsIgnoreCase(nome.trim())) {
                StringBuilder sb = new StringBuilder();
                sb.append(a.imprimir()).append(System.lineSeparator());
                LocalDate bd = a.getBirthdate();
                if (bd == null) {
                    sb.append("Data de nascimento: N/A").append(System.lineSeparator());
                } else {
                    int dias = a.calcularDiasParaAniversariar();
                    if (dias == 0) {sb.append("Aniversario hoje!");
                    } else {
                        sb.append("Faltam ").append(dias).append(" dia(s) para o aniversário.");
                    }
                }
                return sb.toString();
            }
        }
        return "Amigo não encontrado" ;
    }

    // Retorna lista de amigos que fazem aniversario no mês informado
    public String aniversariarNoMes(int mes) {
        if (mes < 1 || mes > 12) {
            return "Mês inexistente";
        }

        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();

        for (Amigo a : amigos) {
            if (a.getBirthdate() != null) {
                Date d = Date.from(a.getBirthdate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                if ((cal.get(Calendar.MONTH) + 1) == mes) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(a.getName());
                }
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
        sb.append("Lista de todos os amigos:").append(System.lineSeparator());

        for (Amigo a : amigos) {
            sb.append(a.imprimir()).append(System.lineSeparator());
        }

        return sb.toString();
    }
}