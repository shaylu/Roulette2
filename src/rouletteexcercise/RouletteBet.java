/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;

import java.util.ArrayList;

/**
 *
 * @author Shay
 */
public class RouletteBet {

    

    private RouletteGame _game;
    private RoulettePlayer _player;
    private BetType _betType;
    private ArrayList<RouletteNumber> _numbers;
    private double _money;

    public RouletteBet(RouletteGame _game, RoulettePlayer _player, BetType _betType, ArrayList<RouletteNumber> _numbers, double _money) {
        this._game = _game;
        this._player = _player;
        this._betType = _betType;
        this._numbers = _numbers;
        this._money = _money;
    }

    public double CalculateWinning(RouletteNumber number) {
        boolean won = IsNumberFound(number);
        if (won == true) {
            double factor = ((double) _game.GetSettings().GetRouletteType().NumbersOnRoullete / (double) _numbers.size()) - (double) 1;
            return (_money + (_money * factor));
        } else {
            return 0;
        }
    }

    /**
     * @return the _betType
     */
    public BetType GetBetType() {
        return _betType;
    }

    /**
     * @return the _player
     */
    public RoulettePlayer GetPlayer() {
        return _player;
    }

    /**
     * @param _player the _player to set
     */
    public void SetPlayer(RoulettePlayer _player) {
        this._player = _player;
    }

    /**
     * @return the _numbers
     */
    public ArrayList<RouletteNumber> GetNumbers() {
        return _numbers;
    }

    /**
     * @param _numbers the _numbers to set
     */
    public void SetNumbers(ArrayList<RouletteNumber> _numbers) {
        this._numbers = _numbers;
    }

    /**
     * @return the _money
     */
    public double GetMoney() {
        return _money;
    }

    /**
     * @param _money the _money to set
     */
    public void SetMoney(double _money) {
        this._money = _money;
    }

    /**
     * @return the _game
     */
    public RouletteGame GetGame() {
        return _game;
    }

    /**
     * @param _game the _game to set
     */
    public void SetGame(RouletteGame _game) {
        this._game = _game;
    }

    private boolean IsNumberFound(RouletteNumber number) {
        for (int i = 0; i < _numbers.size(); i++) {
            if (number.equals(number)) {
                return true;
            }
        }

        return false;
    }
}
