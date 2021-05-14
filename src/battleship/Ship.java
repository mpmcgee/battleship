package battleship;

//ship object
public class Ship {
    private final String name;
    private int[] shipCoords;
    private final int cells; //number of cells in ship
    private int cellsHit; // number of cells not hit

    //constructor for ships
    public Ship(String name, int cells) {
        this.name = name;
        this.cells = cells;
        this.cellsHit = 0;
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

    public int[] getShipCoords() {
        return this.shipCoords;
    }

    public void setShipCoords(int[] shipCoords){
        this.shipCoords = shipCoords;
    }



}