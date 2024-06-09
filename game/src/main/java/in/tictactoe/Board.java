package in.tictactoe;

import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Board {

    // The code snippet you provided in the `Board` class is defining a private 2D
    // array `map` of type
    // `Box` with dimensions 3x3. This array represents the game board in a
    // Tic-Tac-Toe game.
    private Box[][] map = new Box[3][3];

    public Box[][] getMap() {
        return map;
    }

    public void setMap(Box[][] map) {
        this.map = map;
    }

    // The `public Board(gamePanel g)` constructor in the `Board` class is
    // initializing the game board by
    // creating instances of `Box` objects and adding action listeners to them.
    public Board(gamePanel g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = new Box(Integer.toString(i) + Integer.toString(j));
                map[i][j].addActionListener(g);
                ;
            }
        }
    }

    /**
     * The `draw` function iterates through a 2D array of `Box` objects and adds
     * each `Box` to a
     * `gamePanel`.
     * 
     * @param g The parameter `g` in the `draw` method is of type `gamePanel`, which
     *          is likely a class
     *          representing the panel or canvas where the game objects are drawn.
     *          The method iterates over a 2D
     *          array of `Box` objects called `map` and adds each `Box`
     */
    public void draw(gamePanel g) {
        for (Box[] boxes : map) {
            for (Box box : boxes) {
                g.add(box);
            }
        }
    }

    /**
     * The `move` function takes in parameters for the player's turn, the location
     * to move to, and a
     * flag for UI interaction, and updates the game board accordingly.
     * 
     * @param turn     The `turn` parameter in the `move` method is a boolean value
     *                 that indicates whose
     *                 turn it is to make a move in the game. If `turn` is `true`,
     *                 it represents Player X's turn, and if
     *                 `turn` is `false`, it represents Player O's turn.
     * @param location The `location` parameter is a String that represents the
     *                 position where the
     *                 player wants to make a move on the game board. It contains
     *                 the coordinates of the cell where the
     *                 player wants to place their symbol.
     * @param UI       The `UI` parameter in the `move` method is a boolean flag
     *                 that indicates whether the
     *                 method should update the user interface (UI) with the
     *                 player's move. If `UI` is `true`, the
     *                 method will update the UI by setting an icon based on the
     *                 player's turn (X
     * @return The method `move` returns a boolean value. If the location on the map
     *         specified by the
     *         input coordinates is empty, it sets the icon and value based on the
     *         turn and returns the opposite
     *         of the current turn. If the location is not empty, it simply returns
     *         the current turn.
     */
    public boolean move(boolean turn, String location, boolean UI) {
        int i = Integer.parseInt(location.substring(0, 1));
        int j = Integer.parseInt(location.substring(1));

        if (map[i][j].isEmpty()) {
            // System.out.print(location + " | ");
            // System.out.print(i);
            // System.out.println(j);
            if (UI) {
                String src = turn ? "game/src/main/java/in/tictactoe/assets/x.png"
                        : "game\\src\\main\\java\\in\\tictactoe\\assets\\o.png";
                Icon img = new ImageIcon(src);
                map[i][j].setIcon(img);
            }
            map[i][j].setValue(turn);
            return !turn;
        }
        return turn;
    }

    /**
     * The `remove` function takes a location and a boolean flag as input, and
     * either resets or sets the
     * value of a cell in a 2D map based on the flag.
     * 
     * @param location The `location` parameter is a string that represents the
     *                 position in a 2D array.
     *                 It is expected to be in the format of "ij" where 'i'
     *                 represents the row index and 'j' represents
     *                 the column index in the array.
     * @param UI       The `UI` parameter in the `remove` method is a boolean flag
     *                 that indicates whether the
     *                 removal operation should be done with consideration for the
     *                 user interface (`UI` is true) or not
     *                 (`UI` is false).
     */
    public void remove(String location, boolean UI) {
        int i = Integer.parseInt(location.substring(0, 1));
        int j = Integer.parseInt(location.substring(1));
        if (UI) {

            map[i][j].reset();
        } else {
            map[i][j].setValue();
        }

    }

    /**
     * This Java function returns an array of valid moves by iterating through a 2D
     * array of Box objects
     * and adding the action command of empty boxes to an ArrayList.
     * 
     * @return An array of valid moves is being returned.
     */
    Object[] getValidMoves() {
        ArrayList<String> s = new ArrayList<>();
        for (Box[] boxes : map) {
            for (Box box : boxes) {
                if (box.isEmpty()) {
                    s.add(box.getActionCommand());
                }
            }
        }
        return s.toArray();
    }

    // !RESULT
    /**
     * The `isGameOver` function in Java returns true if the game result is not 0 or
     * there are no valid
     * moves left.
     * 
     * @return The method isGameOver() returns a boolean value, which is determined
     *         by whether the
     *         result of the game is not equal to 0 or if there are no valid moves
     *         left.
     */
    public boolean isGameOver() {
        return (result() != 0 || getValidMoves().length == 0);
    }

    // This section of code in the `Board` class is responsible for checking the
    // game board for a
    // winning condition or a draw in a Tic-Tac-Toe game.
    public int result() {
        int r = rowCheck();
        int c = columnCheck();
        int x = diagCheck();
        if (r != 0) {
            return r;
        }
        if (c != 0) {
            return c;
        }
        if (x != 0) {
            return x;
        }
        return 0;
    }

    /**
     * The `diagCheck` function checks for a diagonal win condition in a 3x3 game
     * board represented by a 2D
     * array.
     * 
     * @return The `diagCheck` method is checking for diagonal matches in a 3x3 grid
     *         represented by the
     *         `map` array. If there is a diagonal match with a non-zero value, it
     *         returns that value. If no
     *         diagonal match is found, it returns 0.
     */
    private int diagCheck() {
        if (map[0][2].getValue() == map[1][1].getValue() && map[2][0].getValue() == map[1][1].getValue()
                && map[0][2].getValue() != 0) {
            return map[1][1].getValue();
        }

        if (map[0][0].getValue() == map[1][1].getValue() && map[2][2].getValue() == map[1][1].getValue()
                && map[0][0].getValue() != 0) {
            return map[1][1].getValue();
        }
        return 0;
    }

    /**
     * The `columnCheck` function checks for a winning condition in the columns of a
     * game board.
     * 
     * @return The `columnCheck` method is checking for a winning condition in the
     *         columns of a 2D array
     *         `map`. If there is a column where all three elements have the same
     *         non-zero value, that value is
     *         returned. If no such column is found, the method returns 0.
     */
    private int columnCheck() {
        for (int i = 0; i < map.length; i++) {
            if (map[0][i].getValue() == map[1][i].getValue() && map[2][i].getValue() == map[1][i].getValue()
                    && map[0][i].getValue() != 0) {
                return map[0][i].getValue();
            }
        }
        return 0;
    }

    /**
     * The `rowCheck` function checks if any row in a 2D array of `Box` objects has
     * the same non-zero
     * value in all three elements.
     * 
     * @return The `rowCheck` method is checking each row in a 2D array of `Box`
     *         objects to see if all
     *         three values in the row are equal and not equal to 0. If a row is
     *         found where all three values
     *         are equal and not equal to 0, the method returns the value of that
     *         row. If no such row is found,
     *         the method returns 0.
     */
    private int rowCheck() {
        for (Box[] boxes : map) {
            if (boxes[0].getValue() == boxes[1].getValue() && boxes[2].getValue() == boxes[1].getValue()
                    && boxes[0].getValue() != 0) {
                return boxes[0].getValue();
            }
        }
        return 0;
    }

    /**
     * The `print` function iterates through a 2D array of `Box` objects and prints
     * their action
     * command and value in a formatted manner.
     */
    void print() {
        for (Box[] boxs : map) {
            for (Box box : boxs) {
                System.out.printf("[%-2s %1d]\t", box.getActionCommand(), box.getValue());
            }
            System.out.println("");
        }
    }

}
