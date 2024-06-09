package in.tictactoe;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Game extends JFrame {

    // In the provided Java code snippet, the following lines are defining instance
    // variables for the
    // `Game` class:
    private gamePanel g;
    private Integer X = 10,
            Y = 10,
            W_WIDTH = 600,
            W_HEIGHT = 600;

    // The `Game()` constructor in the provided Java code snippet is initializing a
    // new instance of the
    // `Game` class. Inside the constructor, the `setWindow()` method is called to
    // set up the JFrame window
    // with a title, size, layout, and default close operation.
    Game() {
        setWindow();
        // New game
        g = new gamePanel(this);
        add(g, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * The `setWindow` function in Java sets the title, size, layout, and default
     * close operation for a
     * JFrame window.
     */
    private void setWindow() {
        setTitle("TicTacToe");
        setBounds(X, Y, W_WIDTH, W_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    /**
     * The `endGame` function hides and disposes of the current window in a Java
     * program.
     */
    void endGame() {
        setVisible(false);
        dispose();
    }

}
