/*  
    PROJECT MADE BY:
    -----
    Guilherme Marques de Lima       - 248151
    Luis Fillipe de Medeiros Silva  - 248370
    Vittorio Pivarci                - 248674

    
    GITHUB: https://github.com/ValkorProjects/contacts-list-app
*/

package com.tools;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.function.Predicate;
import javax.swing.JOptionPane;

public class Validate 
{
    public static String getValidatedInput(String prompt, Predicate<String> validator, String errorMessage, boolean proceedFailure)
    {
        Object[] error_options = {"OK"};
        while (true) { 
            String input = JOptionPane.showInputDialog(prompt);
            if(input == null) return null;
            if(validator.test(input)) return input;

            if(proceedFailure) return null;
            
            //In case of error
            JOptionPane.showOptionDialog(null, 
                errorMessage, 
                "Erro", 
                JOptionPane.ERROR_MESSAGE, 
                JOptionPane.ERROR_MESSAGE, 
                null, 
                error_options, 
                error_options[0]
            );
        }
    }

    public static Object getValidatedInputConvert(String targetClass, String prompt, Predicate<String> validator, String errorMessage, boolean proceedFailure)
    {
        Object[] error_options = {"OK"};
        while (true) { 
            String input = JOptionPane.showInputDialog(prompt);
            if(input == null) return 0;
            if(validator.test(input)){
                return switch (targetClass.toUpperCase()) {
                    case "INTEGER" -> Integer.valueOf(input);
                    case "FLOAT" -> Float.valueOf(input);
                    case "DOUBLE" -> Double.valueOf(input);
                    default -> input;
                };
            }

            if(proceedFailure) return 0;

            //In case of error
            JOptionPane.showOptionDialog(null, 
                errorMessage, 
                "Erro", 
                JOptionPane.ERROR_MESSAGE, 
                JOptionPane.ERROR_MESSAGE, 
                null, 
                error_options, 
                error_options[0]
            );
        }
    }

    public static LocalDate getValidatedDateInput(String prompt, Predicate<String> validator, String errorMessage, boolean proceedFailure){
    {
        Object[] error_options = {"OK"};
        while (true) { 
            String input = JOptionPane.showInputDialog(prompt);
            if(input == null) return null;
            if(validator.test(input)){
                LocalDate data;
                    try {
                        if (!input.isBlank()) {
                            data = StringAnalyzer.parseDateAnyFormat(input.trim());
                            return data;
                        }
                    } catch (DateTimeParseException ex) {
                        return null;
                    }
            }

            if(proceedFailure) return null;

            //In case of error
            JOptionPane.showOptionDialog(null, 
                errorMessage, 
                "Erro", 
                JOptionPane.ERROR_MESSAGE, 
                JOptionPane.ERROR_MESSAGE, 
                null, 
                error_options, 
                error_options[0]
            );
            return null;
        }
    }
    }
}
