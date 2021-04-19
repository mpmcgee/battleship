package battleship;

//player object
public class Player {
    private static Ship[] ships;
    private String[][] fogOfWar;
    private String[][] battlefield;

    public Ship[] getShips() {
        return this.ships;
    }

    // create a blank board
    public String[][] createBoard(){

        String [][] board = new String[11][11];
        char c = 'A';
        for (int i = 0; i <= 10; i++){
            for (int j = 0; j <= 10; j++) {
                if (i == 0 && j == 0) {
                    board[i][j] = " ";
                } else if (i == 0) {
                    board[i][j] = String.valueOf(j);
                } else if (j == 0) {
                    board[i][j] = String.valueOf(c++);
                } else {
                    board[i][j] = "~";
                }
            }
        }
        return board;
    }

    //prints the battlefield
    public void printBattlefield() {
        System.out.print("\n");
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                System.out.print(this.battlefield[i][j] + " ");
            }
            System.out.print("\n");

        }
        System.out.print("\n");
    }

    //prints the fog of war
    public void printFogOfWar() {
        System.out.print("\n");
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                System.out.print(this.fogOfWar[i][j] + " ");
            }
            System.out.print("\n");

        }
        System.out.print("\n");
    }



    public void createFogOfWar() {
        this.fogOfWar = createBoard();
    }

    public String[][] getFogOfWar() {
        return this.fogOfWar;
    }

    public void createBattlefield() {
        this.battlefield = createBoard();
    }

    public String[][] getBattlefield() {
        return this.battlefield;
    }

    //update battlefield
    public void updateBattlefield(int row, int col, String val) {
        this.battlefield[row][col] = val;
    }

    //update fog of war
    public void updateFogOfWar(int row, int col, String val) {
        this.fogOfWar[row][col] = val;
    }

    // creates a new player with a new fleet
    public void createFleet() {

        Ship aircraftCarrier = new Ship("Aircraft Carrier", 5, 0);
        Ship battleship = new Ship ("Battleship", 4, 0);
        Ship submarine = new Ship ("Submarine", 3, 0);
        Ship cruiser = new Ship ("Cruiser", 3, 0);
        Ship destroyer = new Ship ("Destroyer", 2, 0);

        Ship [] fleet = {aircraftCarrier, battleship, submarine, cruiser, destroyer};

        this.ships = fleet;
    }

    public void placeShip(int rowStart, int colStart, int rowEnd, int colEnd, Ship ship) {
        if (rowStart == rowEnd) {
            for (int i = colStart; i <= colStart + ship.getCells()-1; i++) {
                this.battlefield[rowStart][i] = "O";
            }
            //vertical ships
        } else {
            for (int i = rowStart; i < rowStart + ship.getCells(); i++) {
                this.battlefield[i][colStart] = "O";
            }
        }

    }


}
