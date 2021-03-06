/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;

import java.util.ArrayList;
import java.util.Collections;
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
        System.out.println("\n======== NEW GAME ========");

        boolean keepRunning = true;
        RouletteSettings settings = ReadSettings();
        RouletteGame game = new RouletteGame(settings);
        ReadPlayers(game);
        game.CreateComputerizedPlayers();

        while (game.GetActiveHumanPlayersNumber() > 0 && keepRunning == true) {
            game.NewRound();
            System.out.println("\n******* NEW ROUND *******");

            // place bets
            for (Entry<String, RoulettePlayer> entry : game.GetPlayers().entrySet()) {
                RoulettePlayer player = entry.getValue();
                if (PlaceBets(game, game.GetRound(), player) == false) {
                    System.out.println("Seems like you don't want to bet anymore, would you like to exit (y/n)? ");
                    Scanner scanner = new Scanner(System.in);
                    String str = scanner.nextLine();

                    if (str.equals("y")) {
                        keepRunning = false;
                    }
                }
            }

            // get number from wheel
            RouletteNumber number = game.TurnWheel();
            System.out.println("\nTurning wheel...... \nThe roulette number won is: " + number.GetName());
            System.out.println("==================================");
            // get winning money
            for (Entry<String, RoulettePlayer> playerEntry : game.GetPlayers().entrySet()) {
                RoulettePlayer player = playerEntry.getValue();

                int winningMoney = game.GetRound().CalculateWinningForPlayer(player.GetName(), number);

                if (winningMoney > 0) {
                    System.out.println(player.GetName() + " won " + winningMoney);
                    player.RecieveMoney(winningMoney);
                } else {
                    System.out.println(player.GetName() + " didn't win nothing. ");
                }
            }

            game.EndRound();
        }

        System.out.println("\nNo more active human players left.");
        ShowScoreBoard(game);
    }

    private void ShowScoreBoard(RouletteGame game) {
        System.out.println("\n//////// SCORE BOARD ////////");
        RoulettePlayer[] players = game.GetPlayers().values().toArray(new RoulettePlayer[game.GetPlayers().size()]);
        SortPlayersByMoney(players);
        for (int i  = 0; i < players.length; i++) {
            RoulettePlayer player = players[i];
            System.out.println(player.GetName() + ": " + player.GetMoney());
        }

        System.out.println("");
    }

    private boolean PlaceBets(RouletteGame game, RouletteRound round, RoulettePlayer player) {

        if (player.GetMoney() <= 0) {
            System.out.println(player.GetName() + ", you don't have any more money left.");
            return true;
        }

        RouletteBet bet;

        // player is computer
        if (player.IsHuman() == false) {
            try {
                bet = round.GetBetForComputerPlayer(player);
                round.PlaceBet(player, bet);
                System.out.println(bet);
                return true;
            } catch (Exception e) {
                System.out.println("Failed to place bet for computer player (" + player.GetName() + ").");
            }
        }

        // player is human
        boolean keepPlaying = AskPlayerIfHeWantsToKeepPlaying(player);
        if (keepPlaying == false) {
            player.SetIsPlaying(false);
            return false;
        }

        RouletteGame.BetType type = GetBetTypeFromConsole(game.GetSettings().GetRouletteType());
        int money = ReadAmountOfMoneyFromPlayer(player.GetMoney());
        ArrayList<String> numbers = null;

        if (type.NeedsNumbers) {
            numbers = ReadNumbersFromConsole(game, type);
            bet = new RouletteBet(game, player, type, numbers, money);
        } else {
            bet = new RouletteBet(game, player, type, null, money);

        }

        try {
            round.PlaceBet(player, bet);
            System.out.println("\n" + bet);
        } catch (RoulettePlayer.NotEnoughtMoneyException e) {
            return PlaceBets(game, round, player);
        }

        System.out.println("\nPlace another bet (y/n)?");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        if (str.equals("y")) {
            return PlaceBets(game, round, player);
        } else {
            return true;
        }
    }

    private RouletteGame.BetType GetBetTypeFromConsole(RouletteGame.RouletteType type) {
        System.out.println("\nChoose bet type: ");

        RouletteGame.BetType[] betTypes = type.BetsTypes;
        for (int i = 0; i < betTypes.length; i++) {
            System.out.print((i + 1) + ". " + betTypes[i].name() +"\t");
            
            if (i % 5 == 0)
                System.out.println("");
        }
        System.out.print("\n");

        System.out.print("Your selection: ");
        Scanner scanner = new Scanner(System.in);
        int selection = Integer.parseInt(scanner.nextLine());

        while (true) {
            if (selection >= 1 && selection <= betTypes.length) {
                return betTypes[selection - 1];
            } else {
                return GetBetTypeFromConsole(type);
            }
        }
    }

    private ArrayList<String> ReadNumbersFromConsole(RouletteGame game, RouletteGame.BetType type) {
        System.out.print("\nHow many numbers do you want to give? ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Integer numbersToGet = Integer.parseInt(str);
        ArrayList<String> res = new ArrayList<>();

        if (numbersToGet <= 0) {
            System.out.println("Please give a positive number.");
            return ReadNumbersFromConsole(game, type);
        }

        for (int i = 0; i < numbersToGet; i++) {
            System.out.print("Number " + (i + 1) + " of " + numbersToGet + ": ");
            String num = scanner.nextLine();
            if (game.IsNumberValidForBet(num) == true && !res.contains(num)) {
                res.add(num);
            } else {
                System.out.println("Number invalid!");
                i--;
            }
        }

        return res;
    }

    private int ReadAmountOfMoneyFromPlayer(int max) {
        while (true) {
            System.out.println("\nPlace a bet between 0 to " + max + ": ");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            Integer money = Integer.parseInt(str);

            if (money >= 0 && money <= max) {
                return money;
            }
        }
    }

    private boolean AskPlayerIfHeWantsToKeepPlaying(RoulettePlayer player) {
        System.out.print("\n" + player.GetName() + ", would you like to place a bet (y) or game out (n)? ");

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        if (str.equals("y") == true) {
            return true;
        } else {
            return false;
        }
    }

    private void SortPlayersByMoney(RoulettePlayer[] players) {
        int n = players.length;
        RoulettePlayer temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (players[j - 1].GetMoney() < players[j].GetMoney()) {
                    //swap the elements!
                    temp = players[j - 1];
                    players[j - 1] = players[j];
                    players[j] = temp;
                }
            }
        }
    }
}
