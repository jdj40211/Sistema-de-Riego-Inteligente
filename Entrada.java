import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;

public class Entrada extends JPanel {
    private JTextField cajaHumedadActual;
    private JComboBox cajaPlantas;
    private JLabel labelHumedadActual;
    private JLabel labelHumedadIdeal;
    private JTextField cajaHumedadIdeal;
    private JTextField cajaCapacidadTanque;
    private JLabel labelCapacidadTAnque;
    private JLabel labelEstado;
    private JTextField cajaEstado;
     private Procesamiento procesamiento1;
    private SistemaRiego sistemabasecopia;
    public Entrada() throws IOException{
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
        procesamiento1 = new Procesamiento();
        sistemabasecopia = procesamiento1.getPersistencia().getSistemaBase();
        //imp();
        
        cajaPlantas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cajaPlantas.getSelectedItem().equals("Papa")){
                Planta papa = sistemabasecopia.buscarNombre((String)cajaPlantas.getSelectedItem());
                cajas(papa);
            } else if(cajaPlantas.getSelectedItem().equals("Cebolla")){
                Planta cebolla = sistemabasecopia.buscarNombre((String)cajaPlantas.getSelectedItem());
                cajas(cebolla);
            } else if(cajaPlantas.getSelectedItem().equals("Lechuga")){
                Planta Lechuga = sistemabasecopia.buscarNombre((String)cajaPlantas.getSelectedItem());
                cajas(Lechuga);

            }else{
                    cajaEstado.setText("");
                    cajaHumedadIdeal.setText("");
                }
            }
        });
        cajaCapacidadTanque.setText(Float.toString(sistemabasecopia.getNvlTanque()));
    }
    public void cajas(Planta actual) {
        try {
            String hum = cajaHumedadActual.getText();
            float humedad = Float.parseFloat(hum);
            cajaHumedadIdeal.setText(Float.toString(actual.getHumedadIdeal()));
            if (Procesamiento.compararHumedad(humedad, actual)) {
                cajaEstado.setText("Humedad insuficiente");
                if (Procesamiento.compararTanque(sistemabasecopia, actual)) {
                    cajaEstado.setText("Riego Ejecutado");
                    cajaCapacidadTanque.setText(Float.toString(sistemabasecopia.getNvlTanque()));
                } else {
                    cajaEstado.setText("Llenar tanque");
                    cajaCapacidadTanque.setText(Float.toString(sistemabasecopia.getNvlTanque()));
                }
            } else {
                cajaEstado.setText("Humedad optima");
            }
        } catch (NumberFormatException u) {
            cajaHumedadActual.setText("Ingrese un valor primero");
        }


    }
}
