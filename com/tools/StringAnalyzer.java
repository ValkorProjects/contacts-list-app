/*  
    PROJECT MADE BY:
    -----
    Guilherme Marques de Lima       - 248151
    Luis Fillipe de Medeiros Silva  - 248370
    Vittorio Pivarci                - 248674

    
    GITHUB: https://github.com/ValkorProjects/contacts-list-app
*/

package com.tools;
import java.util.function.IntPredicate;

public class StringAnalyzer {
    public static int countCharOccurences(String str_source, char chr_target){
        int count = 0;
        for (int i = 0; i < str_source.length(); i++) {
            if(str_source.charAt(i) == chr_target)
                count++;
        }
        return count;
    }

    public static int countConditionOccurences(String str_source, IntPredicate condition)
    {
        int count = 0;
        for(int i = 0; i < str_source.length(); i++){
            if(condition.test(str_source.charAt(i)))
                count++;
        }
        return count;
    }

    public static char findFirstcharNotUnderCondition(String str_source, IntPredicate condition){
        for (int i = 0; i < str_source.length(); i++) {
            if(condition.test(str_source.charAt(i)))
                return str_source.charAt(i);
        }
        return '.';
    }

    public static int distanceToCharFromPoint(String str_source, char chr_target, int start_index){
        int count = 0;
        for(int i = 0; i < str_source.length(); i++){
            if(str_source.charAt(i) == chr_target)
                break;
            else count++;
        }
        return count;
    }
}
