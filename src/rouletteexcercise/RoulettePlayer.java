/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;

/**
 *
 * @author Shay
 */
public class RoulettePlayer {


    
    public enum RoulettePlayerType
    {
        COMPUTER, HUMEN
    }
    

    
    public static class PlayerNameAlreadyTakenException extends Exception
    {
        public PlayerNameAlreadyTakenException()
        {
            super("A player with the selected name is already exist.");
        }
    }
    
    public static class NotEnoughtMoneyException extends Exception
    {
        public NotEnoughtMoneyException()
        {
            super("The player doesn't have enought money.");
        }
    }
    
    private RoulettePlayerType _playerType;
    private String _name;
    private double _money;
    private boolean _isPlaying;

    public RoulettePlayer(RoulettePlayerType playerType, String name, double initialMoneyAmount) {
        this._playerType = playerType;
        this._name = name;
        this._money = initialMoneyAmount;
        this._isPlaying = true;
    }
    
    public String GetName()
    {
        return _name;
    }
    
    public double GetMoney()
    {
        return _money;
    }
    
    public void SetIsPlaying(boolean isPlaying) {
        _isPlaying = isPlaying;
    }
    
    public boolean GetIsPlaying()
    {
        return _isPlaying;
    }
    
    public RoulettePlayerType GetPlayerType()
    {
        return _playerType;
    }
    
    public void RecieveMoney(double money)
    {
        _money += money;
    }
    
    public void PayMoney(double money) throws NotEnoughtMoneyException
    {
        if (money > _money)
            throw new NotEnoughtMoneyException();
        
        _money -= money;
    }
}
