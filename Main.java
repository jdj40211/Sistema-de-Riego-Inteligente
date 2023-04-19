import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.IOException;

public class Main
{
    public static void main (String[] args) throws IOException {
        JFrame frame = new JFrame ("CODIGO INTERFAZ ACTUALIZADA");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new InterfazSistemaRiego());
        frame.pack();
        frame.setVisible (true);
    }
}
