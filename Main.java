import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class Main
{
    public static void main (String[] args) {
        JFrame frame = new JFrame ("CÓDIGO INTERFAZ SISTEMA");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new GUI());
        frame.pack();
        frame.setVisible (true);
    }
}
