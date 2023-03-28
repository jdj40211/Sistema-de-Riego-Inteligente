import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class GUI extends JPanel {
    private JLabel titulo1;
    private JLabel titulo2;
    private JComboBox jcomp3;
    private JLabel titulo3;
    private JTextField jcomp5;
    private JLabel tituloTemp;
    private JTextField jcomp7;
    private JLabel jcomp8;
    private JPasswordField jcomp9;
    private JLabel jcomp10;

    public GUI() {
        //construct preComponents
        String[] jcomp3Items = {"Planta 1", "Planta 2", "Planta 3"};

        //construct components
        titulo1 = new JLabel ("Sistema de Riego Inteligente");
        titulo2 = new JLabel ("Plantas a monitorear:");
        jcomp3 = new JComboBox (jcomp3Items);
        titulo3 = new JLabel ("Datos de la plata seleccionada");
        jcomp5 = new JTextField (5);
        tituloTemp = new JLabel ("Temperatura");
        jcomp7 = new JTextField (5);
        jcomp8 = new JLabel ("Humedad");
        jcomp9 = new JPasswordField (5);
        jcomp10 = new JLabel ("Estado");

        //adjust size and set layout
        setPreferredSize (new Dimension (723, 413));
        setLayout (null);

        //add components
        add (titulo1);
        add (titulo2);
        add (jcomp3);
        add (titulo3);
        add (jcomp5);
        add (tituloTemp);
        add (jcomp7);
        add (jcomp8);
        add (jcomp9);
        add (jcomp10);

        //set component bounds (only needed by Absolute Positioning)
        titulo1.setBounds (265, 15, 175, 45);
        titulo2.setBounds (80, 95, 140, 30);
        jcomp3.setBounds (80, 130, 100, 25);
        titulo3.setBounds (80, 220, 185, 25);
        jcomp5.setBounds (80, 285, 100, 25);
        tituloTemp.setBounds (80, 260, 100, 25);
        jcomp7.setBounds (220, 285, 100, 25);
        jcomp8.setBounds (220, 260, 100, 25);
        jcomp9.setBounds (365, 285, 100, 25);
        jcomp10.setBounds (365, 260, 100, 25);
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("CÓDIGO INTERFAZ SISTEMA");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new GUI());
        frame.pack();
        frame.setVisible (true);
    }
}