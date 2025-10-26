/*  Guilherme Marques de Lima       - 248151
    Luis Fillipe de Medeiros Silva  - 248370
    Vittorio Pivarci                - 248674
*/

package com.src;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    //Comecar os getters e setters
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

    // seta data a partir de string (dd/MM/yyyy) com tratamento
    public void setBirthdate(String _birthdate)                      
    {
        if (_birthdate == null || _birthdate.isBlank()) {
            dataNascimento = null;
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            dataNascimento = LocalDate.parse(_birthdate.trim(), formatter);
        } catch (DateTimeParseException ex) {
            // não sobrescrever se string inválida; define como null para indicar ausência
            dataNascimento = null;
        }
    }

    // overload que aceita LocalDate diretamente
    public void setBirthdate(LocalDate date) {
        dataNascimento = date;
    }

    public LocalDate getBirthdate()                         
    {return dataNascimento;}

    // aBota endereço
    public void setEndereco(Endereco e) {endereco = e;}
    public Endereco getEndereco() {return endereco;}

    //Imprime todos os dados do usuário (apenas uma linha "Data de Nascimento")
    public String imprimir() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = (dataNascimento == null) ? "N/A" : dataNascimento.format(formatter);

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

            if (endereco.getAddressCity() != null && !endereco.getAddressCity().isEmpty()) {
                if (hasAny) sb.append(", ");
                sb.append(endereco.getAddressCity());
                hasAny = true;
            }

            if (endereco.getAddressState() != null && !endereco.getAddressState().isEmpty()) {
                sb.append(" / ").append(endereco.getAddressState());
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

    //Calcula dias para aniversariar (robusto para 29/02)
    public int calcularDiasParaAniversariar()
    {
        if (dataNascimento == null) {
            return -1;
        }

        LocalDate hoje = LocalDate.now();
        MonthDay md = MonthDay.from(dataNascimento);

        // tenta encontrar o próximo aniversário em até 5 anos (cuida do 29/02)
        LocalDate proximo = null;
        for (int add = 0; add <= 5; add++) {
            int year = hoje.getYear() + add;
            try {
                LocalDate candidate = md.atYear(year);
                if (!candidate.isBefore(hoje)) {
                    proximo = candidate;
                    break;
                }
            } catch (DateTimeException ex) {
                // pular ano inválido (ex: 29/02 em ano não-bissexto)
            }
        }

        if (proximo == null) {
            // fallback: ajustar para 28/02
            MonthDay fallback = MonthDay.of(md.getMonthValue(), Math.min(md.getDayOfMonth(), 28));
            proximo = fallback.atYear(hoje.getYear());
            if (proximo.isBefore(hoje)) proximo = proximo.plusYears(1);
        }

        if (proximo.isEqual(hoje)) return 0;

        long dias = ChronoUnit.DAYS.between(hoje, proximo);
        return (int) dias;
    }
}
