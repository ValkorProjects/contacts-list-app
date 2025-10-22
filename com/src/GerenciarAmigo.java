package com.src;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

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
                Date bd = a.getBirthdate();
                if (bd == null) {
                    sb.append("Data de nascimento: N/A").append(System.lineSeparator());
                } else {
                    int dias = diasParaAniversario(bd);
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
}