/*  
    PROJECT MADE BY:
    -----
    Guilherme Marques de Lima       - 248151
    Luis Fillipe de Medeiros Silva  - 248370
    Vittorio Pivarci                - 248674

    
    GITHUB: https://github.com/ValkorProjects/contacts-list-app
*/

package com.src;

public class Endereco {
    private String enderecoRua;
    private String enderecoComplemento;
    private String enderecoCidade;
    private String enderecoEstado;
    private String enderecoCEP;
    private int enderecoNumero;

    public void setAddressStreet(String _street)            { this.enderecoRua = _street; }
    public String getAddressStreet()                        { return this.enderecoRua; }

    public void setAddressNumber(int _number)               { this.enderecoNumero = _number; }
    public int getAddressNumber()                           { return this.enderecoNumero; }

    public void setAddressComplement(String _complement)    { this.enderecoComplemento = _complement; }
    public String getAddresssComplement()                   { return this.enderecoComplemento; }

    public void setAddressCity(String _city)                { this.enderecoCidade = _city; }
    public String getAddressCity()                          { return this.enderecoCidade; }

    public void setAddressState(String _state)              { this.enderecoEstado = _state; }
    public String getAddressState()                         { return this.enderecoEstado; }

    public void setAddressPostalCode(String _cep)           { this.enderecoCEP = _cep; }
    public String getAddressPostalCode()                    { return this.enderecoCEP; }
}