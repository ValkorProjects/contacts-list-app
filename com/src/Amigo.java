package com.src;
import java.text.DateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.*;

public class Amigo {
    //Dados pessoais
    private String nome;
    private String numeroTelefone;
    private Date dataNascimento;
    private Endereco endereco;

    public Amigo(){}

    public Amigo(String _nome, 
                String _numeroTelefone, 
                Date _dataNascimento){
        nome = _nome;
        numeroTelefone = _numeroTelefone;
        dataNascimento = _dataNascimento;
    }
    //Getters/Setters
    public void setName(String _name)                       {nome = _name;}
    public String getName()                                 {return nome;}

    public void setPhoneNumber(String _number)              {numeroTelefone = _number;}
    public String getPhoneNumber()                          {return numeroTelefone;}

    public void setBirthdate(String _birthdate)             //DD/MM/YYYY             
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyyy").withResolverStyle(ResolverStyle.STRICT);
        dataNascimento = formatter.parse();  
    }
    public Date getBirthdate()                              {return dataNascimento;}

    //Imprime todos os dados do usuário
    public String imprimir() {
        String data = (dataNascimento == null) ? "N/A" : new java.text.SimpleDateFormat("dd/MM/yyyy").format(dataNascimento);

        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nome == null ? "" : nome).append(System.lineSeparator());
        sb.append("Telefone: ").append(numeroTelefone == null ? "" : numeroTelefone).append(System.lineSeparator());
        sb.append("Data de Nascimento: ").append(data).append(System.lineSeparator());
        sb.append("Endereço: ");
        if (enderecoRua != null && !enderecoRua.isEmpty()) sb.append(enderecoRua);
        if (enderecoNumero > 0) {
            sb.append(", ").append(enderecoNumero);
        }
        if (enderecoComplemento != null && !enderecoComplemento.isEmpty()) { 
            sb.append(", ").append(enderecoComplemento);
        }
        if (enderecoCidade != null && !enderecoCidade.isEmpty()) {
            sb.append(", ").append(enderecoCidade);
        }
        if (enderecoEstado != null && !enderecoEstado.isEmpty()) {
            sb.append("/").append(enderecoEstado);
        }
        if (enderecoCEP != null && !enderecoCEP.isEmpty()) {
            sb.append(" CEP: ").append(enderecoCEP);
        }

        return sb.toString();
    }

    //Calcula dias para aniversariar 
    public int calcularDiasParaAniversariar()
    {
        var dateNow = Date.from(Instant.now());
        var daysTillBirthday = dateNow.compareTo(dataNascimento) * -1;
        return daysTillBirthday;
    }
}
