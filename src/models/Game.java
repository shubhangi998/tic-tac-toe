package models;

import exceptions.BotCountException;
import exceptions.PlayerCountDimensionMismatchException;
import exceptions.SymbolCountException;
import stratergies.winningStrategies.OrderOneWinningStrategy;
import stratergies.winningStrategies.WinningStrategy;

import java.util.*;

public class Game {
    Board board;
    List <Player> players;
    List <Move> moves;
    WinningStrategy winningStrategies;
    GameState gameState;
    int currentPlayerIndex;
    Player winner;

    public Game(int dimension, List<Player> players, WinningStrategy winningStrategies) {
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.currentPlayerIndex = 0;
        this.board = new Board(dimension);
        this.winner = null;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public void displayBoard() {
        this.board.displayBoard();
    }
    public void makeMove(){
        //Figure whose turn is this
        Player currentPlayer = getPlayers().get(currentPlayerIndex);
        //make move
        Move move = currentPlayer.makeMove(this.board);
        moves.add(move);
        if(winningStrategies.checkWinner(board,move)){
            setGameState(GameState.SUCCESS);
            setWinner(currentPlayer);
            return;
        }
        if(moves.size() == board.getSize()* board.getSize()){
            setGameState(GameState.DRAW);
            return;
        }
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
    public static class Builder{
        private int dimension;
        private List <Player> players;
        private WinningStrategy winningStrategies;

        Scanner scanner = new Scanner(System.in);
        private GameState gameState;

        public Builder setDimension() {
            System.out.println("Enter board size: ");
            this.dimension = scanner.nextInt();
            return this;
        }

        public Builder setPlayers() {
            players = new ArrayList<>();
            for (int i = 0; i < dimension-1; i++) {
                Player player = new Player();
                Symbol symbol = new Symbol();
                System.out.println("Enter Play "+(i+1) +" name:");
                String name = scanner.next();
                player.setName(name);
                System.out.println("Enter Symbol :");
                symbol.setaChar(scanner.next().charAt(0));
                player.setSymbol(symbol);
                player.setPlayerType(PlayerType.HUMAN);
                player.setId(i+1);
                addPlayer(player);
            }
            return this;
        }

        public Builder setWinningStrategies() {
            winningStrategies = new OrderOneWinningStrategy(dimension);
            return this;
        }
        public Builder addPlayer(Player player){
            this.players.add(player);
            return this;
        }
        public Game build() throws BotCountException, SymbolCountException, PlayerCountDimensionMismatchException {
            validate();
            return new Game(this.dimension,this.players,this.winningStrategies);
        }

        private void validateBotCount() throws BotCountException {
            int botCount = 0;
            for(Player p:players){
                if(p.getPlayerType().equals(PlayerType.BOT)){
                    botCount+=1;
                }
                if(botCount > 1){
                    throw new BotCountException();
                }
            }
        }
        private void validatePlayersCount() throws PlayerCountDimensionMismatchException {
            if(players.size() != dimension -1){
                throw new PlayerCountDimensionMismatchException();
            }
        }
        private void validateSymbolCount() throws SymbolCountException {
            Map<Character,Integer> symCount = new HashMap<>();
            for(Player p : players){
                if(!symCount.containsKey(p.getSymbol().getaChar())){
                    symCount.put(p.getSymbol().getaChar(),0);
                }
                symCount.put(p.getSymbol().getaChar(),symCount.get(p.getSymbol().getaChar())+1);
                if(symCount.get(p.getSymbol().getaChar())>1){
                    throw new SymbolCountException();
                }
            }
        }
        private void validate() throws BotCountException, PlayerCountDimensionMismatchException, SymbolCountException {
            //validate single bot player
            validateBotCount();
            //validate number of players = dimension-1
            validatePlayersCount();
            //validate diff player has diff symbol
            validateSymbolCount();
        }

        public Builder setGameStatus(GameState gameState) {
            this.gameState = gameState;
            return this;
        }

        public GameState getGameState() {
            return gameState;
        }
    }
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public WinningStrategy getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(WinningStrategy winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
