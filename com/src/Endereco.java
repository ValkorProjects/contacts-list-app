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

        
    public void setAddressStreet(String _street)            {enderecoRua = _street;}
    public String getAddressStreet()                        {return enderecoRua;}

    public void setAddressComplement(String _complement)    {enderecoComplemento = _complement;}
    public String getAddresssComplement()                   {return enderecoComplemento;}

    public void setAddressCity(String _city)                {enderecoCidade = _city;}
    public String getAddressCity()                          {return enderecoCidade;}

    public void setAddressState(String _state)              {enderecoEstado = _state;}
    public String getAddressState()                         {return enderecoEstado;}

    public void setAddressPostalCode(String _postalCode)    {enderecoCEP = _postalCode;}
    public String getAddressPostalCode()                    {return enderecoCEP;}
    
    public void setAddressNumber(int _number)               {enderecoNumero = _number;}
    public int getAddressNumber()                           {return enderecoNumero;}
    }