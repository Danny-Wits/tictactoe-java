package in.tictactoe;



import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;


/**
 * The Box class extends JButton and represents a box in a Tic-Tac-Toe game with methods to set and
 * reset its value.
 */
public class Box extends JButton {
    
    private int value=0;
    Box(String text){
       setActionCommand(text);
       setBackground(Color.white);
       setBorder(new LineBorder(Color.DARK_GRAY));
       setFocusable(false);
       setIcon(new ImageIcon("game\\src\\main\\java\\in\\tictactoe\\assets\\b.png"));
    }
    boolean isEmpty(){
        return (value==0);
    }
    public int getValue() {
        return value;
    }
    public void setValue(boolean turn) {
        this.value = turn?1:-1;
    }
    public void reset() {
       value=0;
       setIcon(new ImageIcon("game\\src\\main\\java\\in\\tictactoe\\assets\\b.png"));
    }
    public void setValue() {
       value=0;
    }
   
}
