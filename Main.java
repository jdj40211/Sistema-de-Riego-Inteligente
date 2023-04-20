import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.IOException;

public class Main
{
    public static void main (String[] args)throws IOException{
        JFrame frame = new JFrame ("SISTEMA DE RIEGO");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Interfaz());
        frame.pack();
        frame.setVisible (true);
    }
}
