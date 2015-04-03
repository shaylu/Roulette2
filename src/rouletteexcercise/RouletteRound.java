/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 *
 * @author Shay
 */
public class RouletteRound {

    private RouletteGame _game;
    private HashMap<String, ArrayList<RouletteBet>> _bets;

    public RouletteRound(RouletteGame game) {
        _game = game;
        _bets = new HashMap<String, ArrayList<RouletteBet>>();
    }

    public void PlaceBet(RoulettePlayer player, RouletteBet bet) throws RoulettePlayer.NotEnoughtMoneyException {
        String playerName = player.GetName();

        if (_bets.containsKey(playerName) == false) {
            _bets.put(playerName, new ArrayList<RouletteBet>());
        }

        player.PayMoney(bet.GetMoney());
        _bets.get(playerName).add(bet);

    }

    public double CalculateWinningForPlayer(String name, RouletteNumber number) {
        double res = 0;

        if (_bets.containsKey(name) == true) {
            ArrayList<RouletteBet> betsPerPlayer = _bets.get(name);
            for (int i = 0; i < betsPerPlayer.size(); i++) {
                res += betsPerPlayer.get(i).CalculateWinning(number);
            }
        }

        return res;
    }

    public RouletteBet GetBetForComputerPlayer(RoulettePlayer player) {
        Random rnd = new Random();

        int index = rnd.nextInt(_game.GetWheel().length); // get a random number for the wheel array
        double money = rnd.nextDouble() * player.GetMoney(); // set how much money to bet on

        ArrayList<RouletteNumber> numbers = new ArrayList<>();
        numbers.add(_game.GetWheel()[index]); // adds the random number to the bets

        return new RouletteBet(_game, player, numbers, money);
    }
}
