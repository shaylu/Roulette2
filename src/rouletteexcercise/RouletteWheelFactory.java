/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;

import java.util.HashMap;
import rouletteexcercise.RouletteSettings.RouletteType;

/**
 *
 * @author Shay
 */
public class RouletteWheelFactory {
    
    public static final String[] Create(RouletteType rouletteType)
    {
        if (rouletteType == RouletteType.FRENCH)
            return CreateFrenchWheel();
        else
            return CreateAmericanWheel();
    }
    
    private static String[] CreateFrenchWheel(){
//        return new String[]{ 
//            "1", "2", "3", "4" "5", "6", "7", "8", "9", "10",
//            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
//            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
//            "31", "32", "33", "34", "35", "36",
//            "0"};
        
        
    }
    
    private static String[] CreateAmericanWheel(){
        String[] res = new String[38];
        System.arraycopy(CreateFrenchWheel(), 0, res, 0, 37);
        res[36] = "00";
        
        return res;
    }
}
