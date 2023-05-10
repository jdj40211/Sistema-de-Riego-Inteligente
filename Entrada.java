import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
public class Entrada extends JPanel implements ActionListener{
    private JButton bSalir;
    private JComboBox cajaPlantas;
    private JLabel labelCapacidadTanque;
    private JTextField cajaCapacidadTanque;
    private JLabel labelLitros;
    private JTextField cajaEstado;
    private JLabel labelEstado;
    private JLabel labelIndicadores;
    private JTextField cajaHumedadIdeal;
    private JLabel labelHumedadIdeal;
    private JTextField cajaHumedadActual;
    private JLabel labelInfo;
    private JLabel labelporcentaje1;
    private JLabel labaelPorcentaje2;
    private JButton bRegar;
    private Procesamiento procesamiento1;
    private SistemaRiego sistemabasecopia;

    public Entrada() throws IOException {
        //construct preComponents
        String[] cajaPlantasItems = {"Seleccionar planta", "Papa", "Cebolla", "Lechuga"};

        //construct components
        bSalir = new JButton ("SALIR");
        cajaPlantas = new JComboBox (cajaPlantasItems);
        labelCapacidadTanque = new JLabel ("Capacidad actual del tanque");
        cajaCapacidadTanque = new JTextField (5);
        labelLitros = new JLabel ("(litros)");
        cajaEstado = new JTextField (5);
        labelEstado = new JLabel ("Estado de ejecución");
        labelIndicadores = new JLabel ("Indicadores del sistema");
        cajaHumedadIdeal = new JTextField (5);
        labelHumedadIdeal = new JLabel ("Humedad Esperada");
        cajaHumedadActual = new JTextField (5);
        labelInfo = new JLabel ("Ingrese un valor para simular la humedad: ");
        labelporcentaje1 = new JLabel ("%");
        labaelPorcentaje2 = new JLabel ("%");
        bRegar = new JButton ("REGAR");

        //set components properties
        cajaCapacidadTanque.setEnabled (false);
        cajaEstado.setEnabled (false);
        cajaHumedadIdeal.setEnabled (false);

        //adjust size and set layout
        setPreferredSize (new Dimension (516, 366));
        setLayout (null);

        //add components
        add (bSalir);
        bSalir.addActionListener(this);
        add (cajaPlantas);
        add (labelCapacidadTanque);
        add (cajaCapacidadTanque);
        add (labelLitros);
        add (cajaEstado);
        add (labelEstado);
        add (labelIndicadores);
        add (cajaHumedadIdeal);
        add (labelHumedadIdeal);
        add (cajaHumedadActual);
        add (labelInfo);
        add (labelporcentaje1);
        add (labaelPorcentaje2);
        add (bRegar);
        bRegar.addActionListener(this);

        //set component bounds (only needed by Absolute Positioning)
        bSalir.setBounds (195, 310, 118, 43);
        cajaPlantas.setBounds (25, 20, 134, 34);
        labelCapacidadTanque.setBounds (305, 40, 166, 25);
        cajaCapacidadTanque.setBounds (310, 75, 90, 27);
        labelLitros.setBounds (405, 75, 43, 25);
        cajaEstado.setBounds (310, 245, 171, 25);
        labelEstado.setBounds (305, 210, 163, 26);
        labelIndicadores.setBounds (275, 5, 142, 37);
        cajaHumedadIdeal.setBounds (310, 160, 90, 27);
        labelHumedadIdeal.setBounds (305, 130, 119, 14);
        cajaHumedadActual.setBounds (25, 105, 100, 34);
        labelInfo.setBounds (20, 75, 243, 25);
        labelporcentaje1.setBounds (130, 105, 22, 28);
        labaelPorcentaje2.setBounds (405, 160, 24, 25);
        bRegar.setBounds (20, 170, 174, 53);
        //Extras
        procesamiento1 = new Procesamiento();
        sistemabasecopia = procesamiento1.getPersistencia().getSistemaBase();
        cajaCapacidadTanque.setText(Float.toString(sistemabasecopia.getNvlTanque()));
        cajaPlantas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cajaPlantas.getSelectedItem().equals("Papa")){
                    Planta papa = sistemabasecopia.buscarNombre((String)cajaPlantas.getSelectedItem());
                    cajaHumedadIdeal.setText(Float.toString(papa.getHumedadIdeal()));

                } else if(cajaPlantas.getSelectedItem().equals("Cebolla")){
                    Planta cebolla = sistemabasecopia.buscarNombre((String)cajaPlantas.getSelectedItem());
                    cajaHumedadIdeal.setText(Float.toString(cebolla.getHumedadIdeal()));

                } else if(cajaPlantas.getSelectedItem().equals("Lechuga")){
                    Planta Lechuga = sistemabasecopia.buscarNombre((String)cajaPlantas.getSelectedItem());
                    cajaHumedadIdeal.setText(Float.toString(Lechuga.getHumedadIdeal()));
                }else{
                    cajaHumedadIdeal.setText("");
                }
            }

        });

    }
    public void cajas(Planta actual) {
        try {
            String hum = cajaHumedadActual.getText();
            float humedad = Float.parseFloat(hum);
            if(!(humedad<=100&&humedad>=0)){
                throw new NumberFormatException();
            }
            //cajaHumedadIdeal.setText(Float.toString(actual.getHumedadIdeal()));
            if (Procesamiento.compararHumedad(humedad, actual)) {
                if (Procesamiento.compararTanque(sistemabasecopia, actual)) {
                    cajaEstado.setText("Riego ejecutado");
                    cajaCapacidadTanque.setText(Float.toString(sistemabasecopia.getNvlTanque()));
                } else {
                    cajaEstado.setText("Cantidad de agua insuficiente");
                    cajaCapacidadTanque.setText(Float.toString(sistemabasecopia.getNvlTanque()));
                }
            } else {
                cajaEstado.setText("Humedad excesiva");
            }
        } catch (NumberFormatException u) {
            cajaEstado.setText("Ingrese un valor entre [0-100]");
        }
    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bRegar &&(cajaPlantas.getSelectedItem().equals("Papa"))) {
            Planta papa = sistemabasecopia.buscarNombre((String)cajaPlantas.getSelectedItem());
            cajas(papa);
        }
         else if(e.getSource() == bRegar &&(cajaPlantas.getSelectedItem().equals("Cebolla"))){
            Planta cebolla = sistemabasecopia.buscarNombre((String)cajaPlantas.getSelectedItem());
             cajas(cebolla);
        }
         else if(e.getSource() == bRegar &&(cajaPlantas.getSelectedItem().equals("Lechuga"))){
            Planta lechuga = sistemabasecopia.buscarNombre((String)cajaPlantas.getSelectedItem());
            cajas(lechuga);
         }

         else if (e.getSource() == bSalir) {
            System.exit(0);
        }
         else{
             cajaEstado.setText("Debes elegir una planta");
        }
    }
}
/*
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
        add(cajaHumedadActual);
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
            if(!(humedad<=100&&humedad>=0)){
                throw new NumberFormatException();
            }
            cajaHumedadIdeal.setText(Float.toString(actual.getHumedadIdeal()));
            if (Procesamiento.compararHumedad(humedad, actual)) {
                if (Procesamiento.compararTanque(sistemabasecopia, actual)) {
                    cajaEstado.setText("");
                    cajaCapacidadTanque.setText(Float.toString(sistemabasecopia.getNvlTanque()));
                } else {
                    cajaEstado.setText("Cantidad de agua insuficiente");
                    cajaCapacidadTanque.setText(Float.toString(sistemabasecopia.getNvlTanque()));
                }
            } else {
                cajaEstado.setText("Hay mucha hume");
            }
        } catch (NumberFormatException u) {
            cajaHumedadActual.setText("Ingrese un valor entre [0-100]");
        }


    }
}
*/
