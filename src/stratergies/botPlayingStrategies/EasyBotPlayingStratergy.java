package stratergies.botPlayingStrategies;

import models.*;

public class EasyBotPlayingStratergy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board, Bot bot) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Cell cell = board.getBoard().get(i).get(j);
                if(cell.getCellState().equals(CellState.FILLED))
                    continue;
                cell.setCellState(CellState.FILLED);
                cell.setPlayer(bot);
                Move move = new Move();
                move.setCell(cell);
                move.setPlayer(bot);
                return move;
            }
        }
        return null;
    }
}
