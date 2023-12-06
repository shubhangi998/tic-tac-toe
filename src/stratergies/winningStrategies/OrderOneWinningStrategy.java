package stratergies.winningStrategies;

import models.Board;
import models.Cell;
import models.Move;
import models.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderOneWinningStrategy implements WinningStrategy{
    List <Map<Symbol,Integer>> rows;
    List <Map<Symbol,Integer>> cols;
    Map<Symbol,Integer> diagonal;
    Map<Symbol,Integer> reverseDiagonal;

    public  OrderOneWinningStrategy(int size){
        this.rows = new ArrayList<>();
        this.cols = new ArrayList<>();
        this.diagonal = new HashMap<>();
        this.reverseDiagonal = new HashMap<>();
        for (int i = 0; i < size; i++) {
           rows.add(new HashMap<>());
           cols.add(new HashMap<>());
        }
    }
    @Override
    public boolean checkWinner(Board board, Move move) {
        Cell cell = move.getCell();
        Symbol symbol = cell.getPlayer().getSymbol();
        //update symbol count in row where move is made
        int row = cell.getRow();
        Map<Symbol,Integer> rowMap = rows.get(row);
        rowMap.put(symbol, rowMap.getOrDefault(symbol,0)+1);
        //update symbol count in column where move is made
        int col = cell.getColumn();
        Map <Symbol,Integer> colMap = cols.get(col);
        colMap.put(symbol,colMap.getOrDefault(symbol,0)+1);
        //update diagonal if move is made there
        if(row == col){
            diagonal.put(symbol,diagonal.getOrDefault(symbol,0)+1);
        }
        //update reverse diagonal
        if(row+col == board.getSize()-1){
            reverseDiagonal.put(symbol,reverseDiagonal.getOrDefault(symbol,0)+1);
        }

        return rowMap.getOrDefault(symbol,0) == board.getSize() ||
                colMap.getOrDefault(symbol,0) == board.getSize() ||
                diagonal.getOrDefault(symbol,0) == board.getSize() ||
                reverseDiagonal.getOrDefault(symbol,0) == board.getSize();
    }
}
