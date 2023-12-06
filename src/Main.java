import controllers.GameController;
import exceptions.BotCountException;
import exceptions.PlayerCountDimensionMismatchException;
import exceptions.SymbolCountException;
import models.Game;
import models.GameState;
import stratergies.winningStrategies.OrderOneWinningStrategy;
import stratergies.winningStrategies.WinningStrategy;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws BotCountException, SymbolCountException, PlayerCountDimensionMismatchException {
        GameController gameController = new GameController();

        Game game = gameController.startGame();

        while (gameController.checkState(game).equals(GameState.IN_PROGRESS)){
            gameController.displayBoard(game);
            gameController.makeMove(game);
        }
        if (gameController.checkState(game).equals(GameState.SUCCESS)){
            System.out.println("We have winner "+game.getWinner().getName());
        } else if (gameController.checkState(game).equals(GameState.DRAW)) {
            System.out.println("Game is Drawn");
        }
    }
}