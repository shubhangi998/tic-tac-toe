package controllers;

import exceptions.BotCountException;
import exceptions.PlayerCountDimensionMismatchException;
import exceptions.SymbolCountException;
import models.*;
import stratergies.winningStrategies.OrderOneWinningStrategy;
import stratergies.winningStrategies.WinningStrategy;

import java.util.List;
import java.util.Scanner;

public class GameController {

    public Game startGame() throws BotCountException, SymbolCountException, PlayerCountDimensionMismatchException {

        return Game.getBuilder()
                .setDimension()
                .setPlayers()
                .setWinningStrategies()
                .setGameStatus(GameState.IN_PROGRESS)
                .build();
    }

    public void makeMove(Game game){
       game.makeMove();
    }
    public void displayBoard(Game game){

        game.displayBoard();
    }
    public void getWinner(Game game){

    }
    public GameState checkState(Game game){
        return game.getGameState();
    }
}
