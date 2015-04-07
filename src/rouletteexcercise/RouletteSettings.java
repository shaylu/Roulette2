/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;
import rouletteexcercise.RouletteGame.RouletteType;

/**
 *
 * @author Shay
 */

public class RouletteSettings {

    private RouletteType _rouletteType;
    private int _minimumBetsPerPlayer;
    private int _maximumBetsPerPlayer;
    private int _initialAmoutOfMoneyPerPlayer;
    private int _numOfComputerizedPlayers;
    private int _numOfRealPlayers;
    private String _gameName;

    public RouletteSettings(RouletteType _rouletteType, int _minimumBetsPerPlayer, int _maximumBetsPerPlayer, int _initialAmoutOfMoneyPerPlayer, int _numOfComputerizedPlayers, int _numOfRealPlayers, String _gameName) {
        this._rouletteType = _rouletteType;
        this._minimumBetsPerPlayer = _minimumBetsPerPlayer;
        this._maximumBetsPerPlayer = _maximumBetsPerPlayer;
        this._initialAmoutOfMoneyPerPlayer = _initialAmoutOfMoneyPerPlayer;
        this._numOfComputerizedPlayers = _numOfComputerizedPlayers;
        this._numOfRealPlayers = _numOfRealPlayers;
        this._gameName = _gameName;
    }
    
    public RouletteType GetRouletteType()
    {
        return _rouletteType;
    }
    
    public int GetInitialAmoutOfMoneyPerPlayer()
    {
        return _initialAmoutOfMoneyPerPlayer;
    }
    
    /**
     * @return the _minimumBetsPerPlayer
     */
    public int GetMinimumBetsPerPlayer() {
        return _minimumBetsPerPlayer;
    }

    /**
     * @return the _maximumBetsPerPlayer
     */
    public int GetMaximumBetsPerPlayer() {
        return _maximumBetsPerPlayer;
    }

    /**
     * @return the _numOfComputerizedPlayers
     */
    public int GetNumOfComputerizedPlayers() {
        return _numOfComputerizedPlayers;
    }

    /**
     * @return the _numOfRealPlayers
     */
    public int GetNumOfRealPlayers() {
        return _numOfRealPlayers;
    }

    /**
     * @return the _gameName
     */
    public String GetGameName() {
        return _gameName;
    }
}
