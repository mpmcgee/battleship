package battleship;

//ship object
public class Ship {
    private final String name;
    private final int cells; //number of cells in ship
    private int cellsHit; // number of cells not hit

    //constructor for ships
    public Ship(String name, int cells, int cellsHit) {
        this.name = name;
        this.cells = cells;
        this.cellsHit = cellsHit;
    }

    public String getName() {
        return this.name;
    }

    public int getCells() {
        return this.cells;
    }

    public int getCellsHit() {
        return this.cellsHit;
    }

    public void setCellsHit(int cellsHit) {
        this.cellsHit = cellsHit;
    }



}