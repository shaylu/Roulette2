/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import rouletteexcercise.RouletteGame.BetType;

/**
 *
 * @author Shay
 */
public class RouletteBet {

    private RouletteGame _game;
    private RoulettePlayer _player;
    private BetType _betType;
    private ArrayList<String> _numbers;
    private int _money;

    public RouletteBet(RouletteGame _game, RoulettePlayer _player, BetType _betType, ArrayList<String> _numbers, int _money) {
        this._game = _game;
        this._player = _player;
        this._betType = _betType;
        
        if (_numbers == null)
            _numbers = new ArrayList<String>(Arrays.asList(_betType.Numbers));
                    
                    this._numbers = _numbers;
        this._money = _money;
    }

    public int CalculateWinning(RouletteNumber number) {
        boolean won = IsNumberFound(number);
        if (won == true) {
            int factor = _game.GetSettings().GetRouletteType().NumbersOnRoullete / _numbers.size() -  1;
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
    public ArrayList<String> GetNumbers() {
        return _numbers;
    }

    /**
     * @param _numbers the _numbers to set
     */
    public void SetNumbers(ArrayList<String> _numbers) {
        this._numbers = _numbers;
    }

    /**
     * @return the _money
     */
    public int GetMoney() {
        return _money;
    }

    /**
     * @param _money the _money to set
     */
    public void SetMoney(int _money) {
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
            if (_numbers.get(i).equals(number.GetName())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return (this._player.GetName() + " bet: " + this._money + ", Bet Type: " + this._betType.name() + GetNumbersString() );
    }

    private String GetNumbersString() {
        if ( GetBetType().NeedsNumbers == true)
        {
            return ", Numbers: " + GetNumbersArrayString();
        }
        else
            return "";
    }

    private String GetNumbersArrayString() {
        String res = new String();
        for(String object: _numbers){
            res = res.concat(object + " ");
        }
        
        return res;
    }
    
    
}
