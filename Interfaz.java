import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.IOException;

public class Interfaz extends JPanel {
    private JTextField cajaHumedadActual;
    private JComboBox cajaPlantas;
    private JLabel labelHumedadActual;
    private JLabel labelHumedadIdeal;
    private JTextField cajaHumedadIdeal;
    private JTextField cajaCapacidadTanque;
    private JLabel labelCapacidadTAnque;
    private JLabel labelEstado;
    private JTextField cajaEstado;
     private Procesamiento P1;
    private SistemaRiego S2;
    public Interfaz() throws IOException{
        //construct preComponents
        String[] cajaPlantasItems = {"Seleccionar planta", "Papa", "Cebolla", "Lechuga"};

        //construct components
        cajaHumedadActual = new JTextField (5);
        cajaPlantas = new JComboBox (cajaPlantasItems);
        labelHumedadActual = new JLabel ("Humedad Actual");
        labelHumedadIdeal = new JLabel ("Humedad Ideal");
        cajaHumedadIdeal = new JTextField (5);
        cajaCapacidadTanque = new JTextField (5);
        labelCapacidadTAnque = new JLabel ("Capacidad Tanque");
        labelEstado = new JLabel ("Estado");
        cajaEstado = new JTextField (5);

        //set components properties
        cajaHumedadIdeal.setEnabled (false);
        cajaCapacidadTanque.setEnabled (false);
        cajaEstado.setEnabled (false);

        //adjust size and set layout
        setPreferredSize (new Dimension (518, 345));
        setLayout (null);

        //add components
        add (cajaHumedadActual);
        add (cajaPlantas);
        add (labelHumedadActual);
        add (labelHumedadIdeal);
        add (cajaHumedadIdeal);
        add (cajaCapacidadTanque);
        add (labelCapacidadTAnque);
        add (labelEstado);
        add (cajaEstado);

        //set component bounds (only needed by Absolute Positioning)
        cajaHumedadActual.setBounds (40, 145, 130, 35);
        cajaPlantas.setBounds (40, 50, 155, 40);
        labelHumedadActual.setBounds (40, 120, 100, 25);
        labelHumedadIdeal.setBounds (215, 120, 100, 25);
        cajaHumedadIdeal.setBounds (215, 145, 115, 35);
        cajaCapacidadTanque.setBounds (340, 55, 110, 35);
        labelCapacidadTAnque.setBounds (340, 30, 150, 25);
        labelEstado.setBounds (40, 225, 100, 25);
        cajaEstado.setBounds (40, 245, 100, 25);
        
        //Extras
        P1 = new Procesamiento();
        S2 = P1.getp1().getS1();
        //imp();
        
        cajaPlantas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cajaPlantas.getSelectedItem().equals("Papa")){
                Planta papa = S2.buscarNombre("Papa");
                String hum = cajaHumedadActual.getText();
                Float humedad = Float.parseFloat(hum);
                System.out.println(Float.toString(papa.getHumedadIdeal()));
                cajaHumedadIdeal.setText(Float.toString(papa.getHumedadIdeal()));
                cajaCapacidadTanque.setText(Float.toString(S2.getNvlTanque()));
                System.out.println(Procesamiento.compararHumedad(humedad, papa));
                if (Procesamiento.compararHumedad(humedad, papa)){
                    cajaEstado.setText("Humedad insuficiente");
                    if(Procesamiento.compararTanque(S2,papa)){
                        cajaEstado.setText("Riego Ejecutado");
                    }
                    else{
                        cajaEstado.setText("No hay suficiente agua en el tanque");
                    }
                } else{
                    cajaEstado.setText("Humedad optima");
                }
            } else if(cajaPlantas.getSelectedItem().equals("Cebolla")){
                Planta cebolla = S2.buscarNombre((String)cajaPlantas.getSelectedItem());
                cajaHumedadIdeal.setText(Float.toString(cebolla.getHumedadIdeal()));
                cajaCapacidadTanque.setText(Float.toString(S2.getNvlTanque()));
                String hum = cajaHumedadActual.getText();
                Float humedad = Float.parseFloat(hum);
                System.out.println(Procesamiento.compararHumedad(humedad, cebolla.getHumedadIdeal()));
                 if (Procesamiento.compararHumedad(humedad, cebolla)){
                    cajaEstado.setText("Humedad insuficiente");
                    if(Procesamiento.compararTanque(S2,cebolla)){
                        cajaEstado.setText("Riego Ejecutado");
                    }
                    else{
                        cajaEstado.setText("No hay suficiente agua en el tanque");
                    }
                } else{
                    cajaEstado.setText("Humedad optima");
                }
            } else if(cajaPlantas.getSelectedItem().equals("Lechuga")){
                Planta Lechuga = S2.buscarNombre((String)cajaPlantas.getSelectedItem());
                cajaHumedadIdeal.setText(Float.toString(Lechuga.getHumedadIdeal()));
                cajaCapacidadTanque.setText(Float.toString(S2.getNvlTanque()));
                String hum = cajaHumedadActual.getText();
                Float humedad = Float.parseFloat(hum);
                 if (Procesamiento.compararHumedad(humedad, Lechuga)){
                    cajaEstado.setText("Humedad insuficiente");
                    if(Procesamiento.compararTanque(S2,Lechuga)){
                        cajaEstado.setText("Riego Ejecutado");
                    }
                    else{
                        cajaEstado.setText("No hay suficiente agua en el tanque");
                    }
                } else{
                    cajaEstado.setText("Humedad optima");
                }
            }
            }
        });
    }


    public static void main (String[] args)throws IOException{
        JFrame frame = new JFrame ("CodigoFinal");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Interfaz());
        frame.pack();
        frame.setVisible (true);
    }
}
