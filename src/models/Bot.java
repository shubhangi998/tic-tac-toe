package models;

import Factories.BotPlayingStrategyFactory;
import stratergies.botPlayingStrategies.BotPlayingStrategy;

public class Bot extends Player{
    BotDifficultyLevel botDifficultyLevel;

    public Bot(String name, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
        setName(name);
        Symbol symbol = new Symbol();
        symbol.setaChar('O');
        setSymbol(symbol);
        setPlayerType(playerType);
        setBotDifficultyLevel(botDifficultyLevel);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
    @Override
    public Move makeMove(Board board){
        BotPlayingStrategy botPlayingStrategy = BotPlayingStrategyFactory.getStrategy(botDifficultyLevel);
            return  botPlayingStrategy.makeMove(board,this);
    }
}
