package in.tictactoe;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class gamePanel extends JPanel implements ActionListener {
    private boolean player;
    private Board board;
    private boolean gameover = false;
    private Game thisGame;
    private boolean played = false;
    private audio sound=new audio();
    private Thread soundThread;

    gamePanel(Game game, boolean turn2, boolean first) {
        thisGame = game;
        player = turn2;
        setBackground(Color.BLACK);
        setLayout(new GridLayout(3, 3));
        board = new Board(this);
        board.draw(this);
        setVisible(true);
        if (!first) {
            moveAi();
        }
        soundThread=new Thread(sound);
        soundThread.start();  
    }

    /**
     * This function handles player actions in a game, checks for game over
     * conditions, and triggers AI
     * moves.
     * 
     * @param e The parameter `e` in the `actionPerformed` method of the code
     *          snippet is an `ActionEvent`
     *          object. This object represents the event that occurred, such as a
     *          button click or menu selection,
     *          which triggers the actionPerformed method to be executed. Within the
     *          method, the `ActionEvent`
     *          object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameover) {
            return;
        }
        if (!played) {
            boolean valid = false;
            for (Object move : board.getValidMoves()) {
                if (e.getActionCommand().equals((String) move)) {
                    valid = true;
                }
            }
            if (valid) {
                sound.click();
                System.out.println(Thread.activeCount());
                board.move(player, e.getActionCommand(), true);
                int result = board.result();
                if (board.isGameOver()) {
                    gameOver(result);
                    return;
                }
                played = true;
                
                moveAi();
            }
        }

    }

    /**
     * The `gameOver` function sets the game over flag, prints the result of the
     * game (draw, X win, or O
     * win), ends the current game, and restarts the application.
     * 
     * @param result The `result` parameter in the `gameOver` method is used to
     *               determine the outcome of
     *               the game. It is an integer value that can have the following
     *               meanings:
     */
    private void gameOver(int result) {
        gameover = true;
        sound.stop();
        if (result == 0) {
            printM("Its a Draw");
        } else if (result == 1) {
            printM("X won the game ");
        } else {
            printM("O won the game ");
        }
        new menu();
        thisGame.endGame();
    }

    /**
     * The `moveAi` function in Java prints the current turn, moves the AI on the
     * board, checks for game
     * over conditions, and updates the turn.
     */
    public void moveAi() {
        AI.move(board, !player);
        int result = board.result();
        if (board.isGameOver()) {
            gameOver(result);
            return;
        }
        played = false;
    }

    /**
     * The `printM` function displays a message using a dialog box in a Java
     * program.
     * 
     * @param msg The `msg` parameter is a `String` variable that contains the
     *            message to be displayed in a
     *            dialog box using `JOptionPane.showMessageDialog`.
     */
    public void printM(String msg) {
        JOptionPane.showMessageDialog(thisGame, msg);
    }
}
