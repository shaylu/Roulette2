/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import rouletteexcercise.RouletteGame.RouletteType;

/**
 *
 * @author Shay
 */
public class RouletteGameManager {

    public void Start() {
        String action;
        boolean keepRunning = true;

        Scanner scanner = new Scanner(System.in);

        while (keepRunning == true) {
            System.out.print("Create a new game (y/n)? ");
            action = (scanner.nextLine().equalsIgnoreCase("y")) ? "NEW_GAME" : "EXIT";
            switch (action) {
                case "NEW_GAME":
                    NewGame();
                    break;
                case "EXIT":
                    keepRunning = false;
                    break;
                default:
                    keepRunning = false;
                    break;
            }
        }
    }

    private RouletteSettings ReadSettings() {
        RouletteSettings res = new RouletteSettings(RouletteType.FRENCH, 1, 1, 150, 5, 1, "New Game");
        return res;
    }

    private void ReadPlayers(RouletteGame game) {
        Scanner s = new Scanner(System.in);

        for (int i = 0; i < game.GetSettings().GetNumOfRealPlayers(); i++) {
            System.out.print("Please enter Player " + (i + 1) + " name: ");
            try {
                game.AddPlayer(RoulettePlayer.RoulettePlayerType.HUMAN, s.nextLine());
            } catch (RoulettePlayer.PlayerNameAlreadyTakenException ex) {
                System.out.println(ex.getMessage());
                i--;
            }
        }

    }

    private void NewGame() {
        boolean keepRunning = true;
        RouletteSettings settings = ReadSettings();
        RouletteGame game = new RouletteGame(settings);
        ReadPlayers(game);
        game.CreateComputerizedPlayers();

        while (game.GetActiveHumanPlayersNumber() > 0 && keepRunning == true) {
            game.NewRound();

            // place bets
            for (Entry<String, RoulettePlayer> entry : game.GetPlayers().entrySet()) {
                RoulettePlayer player = entry.getValue();
                if (PlaceBets(game, game.GetRound(), player) == false)
                {
                    System.out.println("Seems like you don't want to bet anymore, would you like to exit (y/n)? ");
                    Scanner scanner = new Scanner(System.in);
                    String str = scanner.nextLine();
                    
                    if (str.equals("y"))
                        keepRunning = false;
                }
            }

            // get number from wheel
            RouletteNumber number = game.TurnWheel();

            // get winning money
            for (Entry<String, RoulettePlayer> playerEntry : game.GetPlayers().entrySet()) {
                RoulettePlayer player = playerEntry.getValue();

                int winningMoney = game.GetRound().CalculateWinningForPlayer(player.GetName(), number);

                if (winningMoney > 0) {
                    System.out.println(player.GetName() + " won " + winningMoney);
                    player.RecieveMoney(winningMoney);
                }
            }

            game.SetBankruppedPlayersAsNotPlaying();
        }

        ShowScoreBoard(game);
    }

    private void ShowScoreBoard(RouletteGame game) {
        //
    }

    private boolean PlaceBets(RouletteGame game, RouletteRound round, RoulettePlayer player) {
        RouletteBet bet;
        
        // player is computer
        if (player.IsHuman() == false){
            try {
                bet = round.GetBetForComputerPlayer(player);
                round.PlaceBet(player, bet);
                System.out.println(bet);
                return true;
            } catch (Exception e) {
                System.out.println("Faile to place bet for computer player ("+ player.GetName() + ").");
            }
        }
        
        // player is human
        boolean keepPlaying = AskPlayerIfHeWantsToKeepPlaying(player);
        if (keepPlaying == false)
        {
            player.SetIsPlaying(false);
            return false;
        }
        
        RouletteGame.BetType type = GetBetTypeFromConsole(game.GetSettings().GetRouletteType());
        int money = ReadAmountOfMoneyFromPlayer(player.GetMoney());
        ArrayList<String> numbers = null;
        
        if (type.NeedsNumbers){
            numbers = ReadNumbersFromConsole(type);
            bet = new RouletteBet(game, player, type, numbers, money);
        }
        
        bet = new RouletteBet(game, player, type, null, money);
        try {
            round.PlaceBet(player, bet);
            System.out.println(bet);
        } catch (RoulettePlayer.NotEnoughtMoneyException e) {
            return PlaceBets(game, round, player);
        }
        
        System.out.println("Place another bet (y/n)?");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        if (str.equals("y"))
            return PlaceBets(game, round, player);
        else
            return true;
    }

    private RouletteGame.BetType GetBetTypeFromConsole(RouletteGame.RouletteType type) {
       System.out.println("Choose bet type: ");
       
        RouletteGame.BetType[] betTypes = type.BetsTypes;
        for (int i = 0; i < betTypes.length; i++) {
            System.out.println((i + 1) + ". " + betTypes[i].name());
        }
        
        Scanner scanner = new Scanner(System.in);
        int selection = Integer.parseInt(scanner.nextLine());
        
        while(true)
        {
            if (selection >= 1 && selection <= betTypes.length){
                return betTypes[selection - 1];
            }
            else
            {
                return GetBetTypeFromConsole(type);
            }
        }
    }

    private ArrayList<String> ReadNumbersFromConsole(RouletteGame.BetType type) {
        System.out.println("How many numbers do you want to give ?");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Integer numbersToGet = Integer.parseInt(str);
        ArrayList<String> res = new ArrayList<>();
        
        if (numbersToGet <= 0)
        {
            System.out.println("Please give a positive number.");
            return ReadNumbersFromConsole(type);
        }
        
        for (int i = 0; i < numbersToGet; i++) {
            String num = scanner.nextLine();
            if ()
            
        }
    }

    private int ReadAmountOfMoneyFromPlayer(int max) {
        while(true){
            System.out.println("Place a bet between 0 to " + max + ": ");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            Integer money = Integer.parseInt(str);
            
            if (money >= 0 && money <= max)
                return money;
        }
    }

    private boolean AskPlayerIfHeWantsToKeepPlaying(RoulettePlayer player) {
        System.out.print(player.GetName() + ", would you like to place a bet (y) or game out (n)? ");
        
        Scanner scanner = new Scanner(System.in);
        String str =  scanner.nextLine();
        if (str.equals("y") == true)
            return true;
        else
           return false;
    }
}
