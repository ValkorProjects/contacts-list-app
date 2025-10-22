package com.src;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Amigo {
    //Dados pessoais
    private String nome;
    private String email;
    private String numeroTelefone;
    private LocalDate dataNascimento;
    private Endereco endereco; 

    public Amigo(){}

    public Amigo(String _nome, 
                String _numeroTelefone, 
                String _email,
                LocalDate _dataNascimento){
        nome = _nome;
        numeroTelefone = _numeroTelefone;
        email = _email;
        dataNascimento = _dataNascimento;
    }
    //Getters/Setters
    public void setName(String _name)                       
    {nome = _name;}
    public String getName()                                 
    {return nome;}

    public void setPhoneNumber(String _number)              
    {numeroTelefone = _number;}
    public String getPhoneNumber()                          
    {return numeroTelefone;}
    
    public void setEmail(String _email)
    {email = _email;}
    public String getEmail()
    {return email;}

    public void setBirthdate(String _birthdate)                      
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataNascimento = LocalDate.parse(_birthdate, formatter);
        System.out.println("dat nascimento: "+dataNascimento);
    }
    public LocalDate getBirthdate()                         
    {return dataNascimento;}

    //Imprime todos os dados do usuário
    public String imprimir() {
        String data = (dataNascimento == null) ? "N/A" : new java.text.SimpleDateFormat("dd/MM/yyyy").format(dataNascimento);

        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nome == null ? "" : nome).append(System.lineSeparator());
        sb.append("Telefone: ").append(numeroTelefone == null ? "" : numeroTelefone).append(System.lineSeparator());
        sb.append("Email: ").append(email == null ? "" : email).append(System.lineSeparator());
        sb.append("Data de Nascimento: ").append(data).append(System.lineSeparator());
        sb.append("Endereço: ");
        if (endereco != null) {
            boolean hasAny = false;
            if (endereco.getAddressStreet() != null && !endereco.getAddressStreet().isEmpty()) {
                sb.append(endereco.getAddressStreet());
                hasAny = true;
            }

            if (endereco.getAddressNumber() > 0) {
                if (hasAny) sb.append(", ");
                sb.append(endereco.getAddressNumber());
                hasAny = true;
            }

            if (endereco.getAddresssComplement() != null && !endereco.getAddresssComplement().isEmpty()) {
                if (hasAny) sb.append(", ");
                sb.append(endereco.getAddresssComplement());
                hasAny = true;
            }

            if (endereco.getAddressCity() != null && !endereco.getAddressState().isEmpty()) {
                if(hasAny) sb.append("/");
                sb.append(endereco.getAddressState());
                hasAny = true;
            }

            if (endereco.getAddressPostalCode() != null && !endereco.getAddressPostalCode().isEmpty()) {
                sb.append(" CEP: ").append(endereco.getAddressPostalCode());
            }
            if (!hasAny) sb.append("N/A");
        } else {
            sb.append("N/A");
        }

        return sb.toString();
    }

    //Calcula dias para aniversariar 
    public int calcularDiasParaAniversariar()
    {
        if (dataNascimento == null) {
            return -1;
        }

        LocalDate hoje = LocalDate.now();

        MonthDay niverMesDia = MonthDay.from(dataNascimento);

        LocalDate niverEsteAno = niverMesDia.atYear(hoje.getYear());

        LocalDate proximoNiver;

        if (niverEsteAno.isBefore(hoje)) {
            proximoNiver = niverEsteAno.plusYears(1);
        } else {
            proximoNiver = niverEsteAno;
        }

        if (proximoNiver.isEqual(hoje)) {
            return 0;
        }

        long dias = ChronoUnit.DAYS.between(hoje, proximoNiver);

        return (int) dias;
    }
}
