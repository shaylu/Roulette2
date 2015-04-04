/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Shay
 */
public class RouletteGameManager {

    public void Start()
    {
        String action;
        boolean keepRunning = true;

        Scanner scanner = new Scanner(System.in);

        while (keepRunning == true)
        {
            System.out.print("Create a new game (y/n)? ");
            action = (scanner.nextLine().equalsIgnoreCase("y")) ? "NEW_GAME" : "EXIT";
            switch  (action) {
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
        RouletteSettings res = new RouletteSettings(RouletteSettings.RouletteType.FRENCH, 1, 1, 150, 5, 1, "New Game");
        return res;
    }

    private void ReadPlayers(RouletteGame game) {
        Scanner s = new Scanner(System.in);
        
        for (int i = 0; i < game.GetSettings().GetNumOfRealPlayers(); i++) {
            System.out.print("Please enter Player " + (i+1) + " name: ");
            try {
                game.AddPlayer(RoulettePlayer.RoulettePlayerType.HUMAN, s.nextLine());
            }
            catch (RoulettePlayer.PlayerNameAlreadyTakenException ex)
            {
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
        
        while(game.GetActiveHumanPlayersNumber() > 0 && keepRunning == true)
        {
            game.NewRound();

            // place bets
            for (int i = 0; i < game.GetPlayers().size(); i++) {
                RoulettePlayer player = game.GetPlayers().get(i);
                
                if (player.GetIsPlaying() == true)
                    keepRunning = PlaceBet(game.GetRound(), player);
            }
            
            // place computerized bets
            //game.GetRound().PlaceComputerizedBets();
            
            // get number from wheel
            RouletteNumber number = game.TurnWheel();
            
            // get winning money
            for (int i = 0; i < game.GetPlayers().size(); i++) {
                RoulettePlayer player = game.GetPlayers().get(i);
                int winningMoney = game.GetRound().CalculateWinningForPlayer(player.GetName(), number);
                
                if (winningMoney > 0)
                {
                    System.out.println(player.GetName() + " won " + winningMoney);
                    player.RecieveMoney(winningMoney);
                }
            }
            
            game.SetBankruppedPlayersAsNotPlaying();
        }
        
        ShowScoreBoard(game);
    }

    private void ShowScoreBoard(RouletteGame game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean PlaceBet(RouletteRound round, RoulettePlayer player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

              
//        if (player.GetPlayerType() == RoulettePlayer.RoulettePlayerType.COMPUTER)
//        {
//            bet = round.GetBetForComputerPlayer(player);
//        }
//        else
//        {
//            Scanner scanner = new Scanner(System.in);
//            
//            double money;
//            while (true) {
//                System.out.println(player.GetName() + ", place a bet (0 to stop playing or -1 to exit): ");
//                money = Double.parseDouble(scanner.nextLine());
//                if (money == 0)
//                {
//                    player.SetIsPlaying(false);
//                    return true;
//                }
//                else if (money == -1)
//                    return false;
//                
//                if (money <= player.GetMoney())
//                    break;
//                else
//                    System.out.println(player.GetName() + " has only " + player.GetMoney() + ", please make a lower bet.");
//            }
//            
//            ArrayList<RouletteNumber> numbers = new ArrayList<RouletteNumber> numbers();
//        }
//        
//        try {
//            round.PlaceBet(player, bet);
//        } catch (Exception e) {
//            
//        }
//    }
    }
}
