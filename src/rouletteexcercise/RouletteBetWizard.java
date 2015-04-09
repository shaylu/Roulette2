/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;

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
