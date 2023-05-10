import javax.swing.*;
import java.io.IOException;

public class Main
{
    public static void main (String[] args)throws IOException {
        JFrame frame = new JFrame ("SISTEMA DE RIEGO");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Entrada());
        frame.pack();
        frame.setVisible (true);
    }
}
