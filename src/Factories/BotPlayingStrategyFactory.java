package Factories;

import models.BotDifficultyLevel;
import stratergies.botPlayingStrategies.BotPlayingStrategy;
import stratergies.botPlayingStrategies.EasyBotPlayingStratergy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getStrategy(BotDifficultyLevel botDifficultyLevel){
        if(botDifficultyLevel.equals(BotDifficultyLevel.EASY))
            return new EasyBotPlayingStratergy();
        return new EasyBotPlayingStratergy();
    }
}
