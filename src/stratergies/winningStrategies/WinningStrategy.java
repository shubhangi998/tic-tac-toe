package stratergies.winningStrategies;

import models.Board;
import models.Move;

public interface WinningStrategy {
    public boolean checkWinner(Board board, Move move);
}
