/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import jdk.nashorn.internal.objects.NativeArray;
import rouletteexcercise.RoulettePlayer.RoulettePlayerType;

/**
 *
 * @author Shay
 */
public class RouletteGame {

    public static enum BetType {

        STRAIGHT,
        SPLIT,
        STREET,
        CORNER,
        SIX_LINE,
        TRIO,
        BASKET,
        TOP_LINE,
        MANQUE,
        PASSE,
        ROUGE,
        NOIR,
        PAIR,
        IMPAIR,
        PREMIERE_DOUZAINE,
        MOYENNE_DOUZAINE,
        DERNIERE_DOUZAINE,
        COLUMN,
        SNAKE
    }

    /**
     *
     */
    public static final HashMap<String, RouletteNumber> RouletteNumbers = CreateRouletteNumbersHasMap();

    private static HashMap<String, RouletteNumber> CreateRouletteNumbersHasMap() {
        HashMap<String, RouletteNumber> res = new HashMap<>();
        res.put("1", new RouletteNumber("1", RouletteNumber.RouletteNumberColor.RED));
        res.put("2", new RouletteNumber("2", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("3", new RouletteNumber("3", RouletteNumber.RouletteNumberColor.RED));
        res.put("4", new RouletteNumber("4", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("5", new RouletteNumber("5", RouletteNumber.RouletteNumberColor.RED));
        res.put("6", new RouletteNumber("6", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("7", new RouletteNumber("7", RouletteNumber.RouletteNumberColor.RED));
        res.put("8", new RouletteNumber("8", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("9", new RouletteNumber("9", RouletteNumber.RouletteNumberColor.RED));
        res.put("10", new RouletteNumber("10", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("11", new RouletteNumber("11", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("12", new RouletteNumber("12", RouletteNumber.RouletteNumberColor.RED));
        res.put("13", new RouletteNumber("13", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("14", new RouletteNumber("14", RouletteNumber.RouletteNumberColor.RED));
        res.put("15", new RouletteNumber("15", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("16", new RouletteNumber("16", RouletteNumber.RouletteNumberColor.RED));
        res.put("17", new RouletteNumber("17", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("18", new RouletteNumber("18", RouletteNumber.RouletteNumberColor.RED));
        res.put("19", new RouletteNumber("19", RouletteNumber.RouletteNumberColor.RED));
        res.put("20", new RouletteNumber("20", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("21", new RouletteNumber("21", RouletteNumber.RouletteNumberColor.RED));
        res.put("22", new RouletteNumber("22", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("23", new RouletteNumber("23", RouletteNumber.RouletteNumberColor.RED));
        res.put("24", new RouletteNumber("24", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("25", new RouletteNumber("25", RouletteNumber.RouletteNumberColor.RED));
        res.put("26", new RouletteNumber("26", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("27", new RouletteNumber("27", RouletteNumber.RouletteNumberColor.RED));
        res.put("28", new RouletteNumber("28", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("29", new RouletteNumber("29", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("30", new RouletteNumber("30", RouletteNumber.RouletteNumberColor.RED));
        res.put("31", new RouletteNumber("31", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("32", new RouletteNumber("32", RouletteNumber.RouletteNumberColor.RED));
        res.put("33", new RouletteNumber("33", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("34", new RouletteNumber("34", RouletteNumber.RouletteNumberColor.RED));
        res.put("35", new RouletteNumber("35", RouletteNumber.RouletteNumberColor.BLACK));
        res.put("36", new RouletteNumber("36", RouletteNumber.RouletteNumberColor.RED));
        res.put("0", new RouletteNumber("0", RouletteNumber.RouletteNumberColor.GREEN));
        res.put("00", new RouletteNumber("00", RouletteNumber.RouletteNumberColor.GREEN));

        return res;
    }

    private String[] _computerizedPlayerNames = {"McFly", "Aurora", "Frogger", "Polaris", "Pluton", "Feeling Lucky"};

    private RouletteSettings _settings;
    private String[] _wheel;
    private HashMap<String, RoulettePlayer> _players;
    private RouletteRound _round;

    public RouletteGame(RouletteSettings settings) {
        _settings = settings;
        _wheel = RouletteWheelFactory.Create(settings.GetRouletteType());
        _players = new HashMap<String, RoulettePlayer>();
        _round = null;
    }

    public RouletteSettings GetSettings() {
        return _settings;
    }

    public RouletteRound GetRound() {
        return _round;
    }

    public RouletteNumber[] GetWheel() {
        return _wheel;
    }

    public HashMap<String, RoulettePlayer> GetPlayers() {
        return _players;
    }

    public RouletteNumber TurnWheel() throws NullPointerException {
        if (_wheel == null) {
            throw new NullPointerException();
        }

        Random random = new Random();
        int index = random.nextInt(_wheel.length);

        return _wheel[index];
    }

    public void AddPlayer(RoulettePlayer.RoulettePlayerType playerType, String name, double initialMoneyAmount) throws RoulettePlayer.PlayerNameAlreadyTakenException {
        if (!_players.containsKey(name)) {
            RoulettePlayer player = new RoulettePlayer(playerType, name, initialMoneyAmount);
            _players.put(name, player);
        } else {
            throw new RoulettePlayer.PlayerNameAlreadyTakenException();
        }
    }

    public void AddPlayer(RoulettePlayer.RoulettePlayerType playerType, String name) throws RoulettePlayer.PlayerNameAlreadyTakenException {
        AddPlayer(playerType, name, _settings.GetInitialAmoutOfMoneyPerPlayer());
    }

    public void CreateComputerizedPlayers() {
        Random rnd = new Random();
        HashMap<Integer, String> names = new HashMap<Integer, String>();

        ArrayList<String> computerizedNamesArrayCopy = new ArrayList<String>();
        computerizedNamesArrayCopy.addAll(Arrays.asList(_computerizedPlayerNames));

        for (int i = 0; i < _settings.GetNumOfComputerizedPlayers() && _players.size() <= 6; i++) {
            int index = rnd.nextInt(computerizedNamesArrayCopy.size()); // pick a random index from names array copy

            try {
                AddPlayer(RoulettePlayerType.COMPUTER, computerizedNamesArrayCopy.get(index), _settings.GetInitialAmoutOfMoneyPerPlayer()); // add player to list
                computerizedNamesArrayCopy.remove(computerizedNamesArrayCopy.get(index)); // remove the chosen name from names copy array
            } catch (RoulettePlayer.PlayerNameAlreadyTakenException ex) {
                // can't get here
            }
        }
    }

    public int GetActiveHumanPlayersNumber() {
        int res = 0;

        for (Entry<String, RoulettePlayer> pair : _players.entrySet()) {
            RoulettePlayer player = pair.getValue();

            if (player.GetIsPlaying() == true) {
                res++;
            }

        }

        return res;
    }

    void NewRound() {
        _round = new RouletteRound(this);
    }

    void SetBankruppedPlayersAsNotPlaying() {
        for (Entry<String, RoulettePlayer> pair : _players.entrySet()) {
            RoulettePlayer player = pair.getValue();

            if (player.GetIsPlaying() == true && player.GetMoney() == 0) {
                player.SetIsPlaying(false);
            }

        }
    }

}