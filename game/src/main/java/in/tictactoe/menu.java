package in.tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class menu extends JFrame implements ActionListener {

    // In the provided Java code snippet, the following lines are defining instance
    // variables for the
    // `Game` class:
    private Integer X = 10,
            Y = 10,
            W_WIDTH = 600,
            W_HEIGHT = 600;

    // The `Game()` constructor in the provided Java code snippet is initializing a
    // new instance of the
    // `Game` class. Inside the constructor, the `setWindow()` method is called to
    // set up the JFrame window
    // with a title, size, layout, and default close operation.
    menu() {
        setWindow();
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        p.setLayout(new BorderLayout(0,50));
        p.setBounds(0, 0, W_WIDTH, W_HEIGHT);

        JLabel title = new JLabel(new ImageIcon("game\\src\\main\\java\\in\\tictactoe\\assets\\title.png"));
        JPanel t1=new JPanel();
        t1.setBackground(Color.white);
        t1.add(title);
        p.add(t1,BorderLayout.NORTH);

        JPanel c= new JPanel();
        c.setBackground(Color.white);
        c.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
        JButton X = new JButton();
        X.setIcon(new ImageIcon("game\\src\\main\\java\\in\\tictactoe\\assets\\x.png"));
        X.setBackground(Color.white);
        X.setFocusable(false);
        X.setActionCommand("x");
        X.setBorder(null);
        X.addActionListener(this);
        c.add(X);

        JButton O = new JButton();
        O.setBackground(Color.white);
        O.setIcon(new ImageIcon("game\\src\\main\\java\\in\\tictactoe\\assets\\o.png"));
        O.setActionCommand("o");
        O.setFocusable(false);
        O.addActionListener(this);
        O.setBorder(null);
        c.add(O);

        p.add(c,BorderLayout.CENTER);
        add(p);
        setVisible(true);
    }

    /**
     * The `setWindow` function in Java sets the title, size, layout, and default
     * close operation for a
     * JFrame window.
     */
    private void setWindow() {
        setTitle("TicTacToe");
        setBackground(Color.white);
        setLayout(new GridLayout(1,1));
        setBounds(X, Y, W_WIDTH, W_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="x"){
            new Game(true,true);
        }else if (e.getActionCommand()=="o"){
            new Game(false,false);
        }
        this.setVisible(false);
        this.dispose();
    }

}
