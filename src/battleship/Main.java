package battleship;
import java.lang.reflect.Array;
import java.nio.file.AtomicMoveNotSupportedException;
import java.util.Scanner;


public class Main {

    public static boolean checkWin(Player player) {
        int count = 0;
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                if (player.getBattlefield()[i][j].equals("O")) {
                    count++;

                }
            }
        }
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkAvailable(int rowStart, int colStart, int rowEnd, int colEnd, String battlefield[][]) {

        //check to see that space is open
        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = colStart; j <= colEnd; j++) {


                //horizontal ships
                if (rowStart == rowEnd) {
                    // check that rows in, above, and below are open
                    if (i == 1) {

                        if (battlefield[i][j] != "~" || battlefield[i + 1][j] != "~") {


                            return false;
                        }

                    } else if (i == 10) {

                        if (battlefield[i][j] != "~" || battlefield[i - 1][j] != "~") {

                            return false;
                        }

                    } else {
                        if (battlefield[i][j] != "~" || battlefield[i + 1][j] != "~"
                                || battlefield[i - 1][j] != "~") {


                            return false;
                        }
                    }

                    //vertical ships
                } else if (colStart == colEnd) {
                    // check that rows in, left of, and right of are open
                    if (j == 1) {

                        if (battlefield[i][j] != "~" || battlefield[i][j + 1] != "~") {


                            return false;
                        }

                    } else if (j == 10) {

                        if (battlefield[i][j] != "~" || battlefield[i][j - 1] != "~") {


                            return false;
                        }

                    } else {
                        if (battlefield[i][j] != "~" || battlefield[i][j + 1] != "~"
                                || battlefield[i][j - 1] != "~") {


                            return false;
                        }
                    }
                } else {
                    return false;
                }



            }


        }
        return true;
    }

    public static boolean checkEnds(int rowStart, int colStart, int rowEnd, int colEnd, String battlefield[][]) {



        if (rowStart == rowEnd) {
            if (colStart == 1) {
                if (battlefield[rowStart][colEnd + 1] != "~") {

                    return false;
                }

            } else if (colEnd == 10) {
                if (battlefield[rowStart][colStart - 1] != "~") {

                    return false;
                }
            } else {
                if (battlefield[rowStart][colStart - 1] != "~" || battlefield[rowStart][colEnd + 1] != "~") {

                    return false;
                }

            }

        } else if (colStart == colEnd) {
            if (rowStart == 1) {
                if (battlefield[rowEnd +1 ][colEnd] != "~") {

                    return false;
                }

            } else if (rowEnd == 10) {
                if (battlefield[rowStart - 1][colEnd] != "~") {

                    return false;
                }
            } else {
                if (battlefield[rowStart - 1][colEnd] != "~" || battlefield[rowEnd + 1][colEnd] != "~") {

                    return false;
                }


            }
        }

        return true;

    }



    public static void placeShips(Player player) {


        Ship[] fleet = player.getShips();

        player.printBattlefield();

        for (int ship = 0; ship < fleet.length; ship++) {
            boolean inputValid = false;
            boolean available = false;

            System.out.println("Enter the coordinates of the " + fleet[ship].getName() + "(" + fleet[ship].getCells() + " cells): \n");
            while (!inputValid) {


                Scanner scanner = new Scanner(System.in);
                String coordinates = scanner.nextLine();



                try {
                    //convert start coordinate input
                    int colStart = 0;
                    int colEnd = 0;
                    String[] arrCoord = coordinates.split(" ", 2);

                    char c1 = arrCoord[0].charAt(0);
                    int rowStart = c1 - 64;
                    if (arrCoord[0].length() > 2) {
                        colStart = Integer.parseInt(arrCoord[0].substring(1, 3));
                    } else if (arrCoord[0].length() == 2) {
                        colStart = Integer.parseInt(String.valueOf(arrCoord[0].charAt(1)));
                    } else {

                        throw new ArrayIndexOutOfBoundsException();

                    }

                    //convert end coordinate input
                    char c2 = arrCoord[1].charAt(0);
                    int rowEnd = c2 - 64;
                    if (arrCoord[1].length() > 2) {
                        colEnd = Integer.parseInt(arrCoord[1].substring(1, 3));
                    } else if (arrCoord[1].length() == 2) {
                        colEnd = Integer.parseInt(String.valueOf(arrCoord[1].charAt(1)));
                    } else {
                        throw new ArrayIndexOutOfBoundsException();
                    }


                    //horizontal check
                    if (rowStart == rowEnd) {
                        if (colStart > colEnd) {
                            int temp = colEnd;
                            colEnd = colStart;
                            colStart = temp;
                        }
                        //check to see if length is incorrect

                        if (colEnd - colStart != (fleet[ship].getCells())-1) {
                            throw new IllegalArgumentException();
                        }

                        //vertical check
                    } else if (colStart == colEnd) {
                        if (rowStart > rowEnd) {
                            int temp1 = rowEnd;
                            rowEnd = rowStart;
                            rowStart = temp1;

                            //check to see if length is incorrect
                            if (rowEnd - rowStart != (fleet[ship].getCells())-1) {
                                throw new IllegalArgumentException();

                            }
                        }

                    } else {
                        throw new ArrayIndexOutOfBoundsException();


                    }


                    boolean check1 = checkAvailable(rowStart, colStart, rowEnd, colEnd, player.getBattlefield());

                    boolean check2 = checkEnds(rowStart, colStart, rowEnd, colEnd, player.getBattlefield());

                    if (check1 && check2){
                        available = true;

                    } else {
                        throw new Exception();

                    }





                    //place ship
                    if (available) {
                        player.placeShip(rowStart, colStart, rowEnd, colEnd, fleet[ship]);
                        inputValid = true;
                        player.printBattlefield();
                    }


                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("\nError! Wrong ship location! Try again: \n");
                } catch (NumberFormatException e) {
                    System.out.println("\nPlease enter two coordinates in the correct range:\n");
                } catch (IllegalArgumentException e) {
                    System.out.println("\nError! Wrong length of the " + fleet[ship].getName() + "! Try again:\n");
                } catch(Exception e) {
                    System.out.println("\nError! You placed it too close to another one. Try again:\n");
                }
            }
        }


    }

    public static int hitShip(Player player, int row, int col) {
        int guessCoords = Integer.parseInt(String.valueOf(row) + String.valueOf(col));
        Ship[] fleet = player.getShips();
        for (int ship = 0; ship < fleet.length; ship++) {
            int[] shipCoords = fleet[ship].getShipCoords();
            for (int i = 0; i < fleet[ship].getCells(); i++) {
                if (guessCoords == shipCoords[i]) {
                    fleet[ship].setCellsHit(fleet[ship].getCellsHit() + 1);
                    if (fleet[ship].getCellsHit() == fleet[ship].getCells()) {

                        if (checkWin(player)) {
                            player.printFogOfWar();
                            return 0;

                        } else {
                            player.printFogOfWar();
                            System.out.println("You sank a ship! Specify a new target:\n");
                            return 0;

                        }

                    }
                }
            }
        }
        player.printFogOfWar();
        System.out.println("You hit a ship! Try again:\n");
        return 0;
    }





    public static void takeShot(Player player) {
        boolean inputValid = false;





        while (!inputValid) {
            int row;
            int col;

            Scanner scanner = new Scanner(System.in);
            String coordinates = scanner.nextLine();

            try {



                char c1 = coordinates.charAt(0);
                row = c1 - 64;
                if (coordinates.length() == 2) {
                    col = Character.getNumericValue(coordinates.charAt(1));
                } else if (coordinates.length() == 3) {
                    col = Integer.parseInt(coordinates.substring(1,3));
                } else {
                    throw new Exception();
                }

                if (player.getBattlefield()[row][col] == "O") {
                    player.updateBattlefield(row, col, "X");
                    player.updateFogOfWar(row, col, "X");
                    hitShip(player, row, col);
                    inputValid = true;


                } else if (player.getBattlefield()[row][col] == "~") {
                    player.updateBattlefield(row, col, "M");
                    player.updateFogOfWar(row, col, "M");
                    player.printFogOfWar();
                    System.out.println("You missed!");
                    inputValid = true;

                } else {
                    System.out.println("here");
                    player.printFogOfWar();
                    throw new Exception();
                }


            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You entered the wrong coordinates! Try again\n");
            } catch (NumberFormatException e) {
                System.out.println("You entered the wrong coordinates! Try again\n");
            } catch (IllegalArgumentException e) {
                System.out.println("You entered the wrong coordinates! Try again\n");
            } catch (Exception e) {
                System.out.println("You entered the wrong coordinates! Try again\n");
            }
        }
    }





    public static void main(String[] args) {

        //create players, boards, and fleets
        boolean win = false;
        Player p1 = new Player();
        p1.createBattlefield();
        p1.createFogOfWar();
        p1.createFleet();
        //place ships
        placeShips(p1);


        //begin game
        System.out.println("The game starts!");
        p1.printFogOfWar();
        System.out.println("Take a shot!\n");
        while (!win) {
            takeShot(p1);
            win = checkWin(p1);
        }

        System.out.println("You sank the last ship. You won. Congratulations!");

    }
}


