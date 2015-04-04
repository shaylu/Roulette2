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
        COMPUTER, HUMAN;
        
        /**
         *
         * @param string
         * @return
         * @throws Exception
         */
        public static RoulettePlayerType Parse(String string) throws Exception {
            switch (string.toUpperCase()) {
                case "COMPUTER":
                    return RoulettePlayerType.COMPUTER;
                case "HUMAN":
                    return RoulettePlayerType.HUMAN;
                default:
                    throw new Exception("Failed to parse " + string + " to RoulettePlayerType.");
            }
        }
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
    private int _money;
    private boolean _isPlaying;

    public RoulettePlayer(RoulettePlayerType playerType, String name, int initialMoneyAmount) {
        this._playerType = playerType;
        this._name = name;
        this._money = initialMoneyAmount;
        this._isPlaying = true;
    }
    
    public String GetName()
    {
        return _name;
    }
    
    public int GetMoney()
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
    
    public void RecieveMoney(int money)
    {
        _money += money;
    }
    
    public void PayMoney(int money) throws NotEnoughtMoneyException
    {
        if (money > _money)
            throw new NotEnoughtMoneyException();
        
        _money -= money;
    }
}
