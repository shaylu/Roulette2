/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import rouletteexcercise.RouletteGame.BetType;

/**
 *
 * @author Dell
 */
public class RouletteBetWizard {



    public static class BetOptionGroup{
        public int NumbersInBet;
        public BetOption[] BetOptions;

        public BetOptionGroup(int NumbersInBet, BetOption[] BetOptions) {
            this.NumbersInBet = NumbersInBet;
            this.BetOptions = BetOptions;
        }
    }
    public static class BetOption {

        public BetType TypeOfBet;
        public RouletteGame.RouletteType RouletteType;
        public String NameDescription;
        public String[] NumbersOnBet;

        public BetOption(BetType TypeOfBet, RouletteGame.RouletteType RouletteType, String NameDescription, String... NumbersOnBet) {
            this.TypeOfBet = TypeOfBet;
            this.RouletteType = RouletteType;
            this.NameDescription = NameDescription;
            this.NumbersOnBet = NumbersOnBet;
        }
    }

    private static BetOption[] GetStraightBetOptions(RouletteGame.RouletteType rouletteType) {
        String[] numbers = RouletteWheelFactory.Create(rouletteType);
        BetOption[] res = new BetOption[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            res[i] = new BetOption(BetType.STRAIGHT, rouletteType, numbers[i], numbers[i]);
        }

        return res;
    }
    
    private static BetOption[] GetSplitBetOptions(RouletteGame.RouletteType rouletteType) {
        String[][] options = new String[][]{{"3", "2"},
                                            {"2", "1"},
                                            {"6", "5"},
                                            {"5", "4"},
                                            {"9", "8"},
                                            {"8", "7"},
                                            {"12", "11"},
                                            {"11", "10"},
                                            {"15", "14"},
                                            {"14", "13"},
                                            {"18", "17"},
                                            {"17", "16"},
                                            {"21", "20"},
                                            {"20", "19"},
                                            {"24", "23"},
                                            {"23", "22"},
                                            {"27", "26"}, 
                                            {"26", "25"},
                                            {"30", "29"},
                                            {"29", "28"},
                                            {"33", "32"},
                                            {"32", "31"},
                                            {"36", "35"},
                                            {"35", "34"},
                                            {"3" ,"6"},
                                            {"6" ,"9"},
                                            {"9" ,"12"},
                                            {"12" ,"15"},
                                            {"15" ,"18"},
                                            {"18" ,"21"},
                                            {"21" ,"24"},
                                            {"24" ,"27"},
                                            {"27" ,"30"},
                                            {"30" ,"33"},
                                            {"33" ,"36"},
                                            {"2" ,"5"},
                                            {"5" ,"8"},
                                            {"8" ,"11"},
                                            {"11" ,"14"},
                                            {"14" ,"17"},
                                            {"17" ,"20"},
                                            {"20" ,"23"},
                                            {"23" ,"26"},
                                            {"26" ,"29"},
                                            {"29" ,"32"},
                                            {"32" ,"35"},
                                            {"1" ,"4"},
                                            {"4" ,"7"},
                                            {"7" ,"10"},
                                            {"10" ,"13"},
                                            {"13" ,"16"},
                                            {"16" ,"19"},
                                            {"19" ,"22"},
                                            {"22" ,"25"},
                                            {"25" ,"28"},
                                            {"28" ,"31"},
                                            {"31" ,"34"}};
        
        BetOption[] res = new BetOption[options.length];
        for (int i = 0; i < options.length; i++) {
            res[i] = new BetOption(BetType.SPLIT, rouletteType, null, options[i].)
        }
        
        
    }
    
    private static RouletteBetWizard.BetOptionGroup GetStraightBetGroup(RouletteGame.RouletteType rouletteType){
        return new BetOptionGroup(1, GetStraightBetOptions(rouletteType));
    }
    
    private static RouletteBetWizard.BetOptionGroup GetSplitBetGroup(RouletteGame.RouletteType rouletteType){
        return new BetOptionGroup(2, GetSplitBetOptions(rouletteType));
    }

//    public static BetOption[] AllOption = {
//        new BetOption(BetType.STRAIGHT, RouletteGame.RouletteType.FRENCH, null, NumbersOnBet)
//    };
    public static HashMap<String, String[]> GetBetOptionsForBetType(RouletteGame.RouletteType rouletteType, RouletteGame.BetType betType) {

    }
}
