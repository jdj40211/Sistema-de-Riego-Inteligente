
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class InterfazSistemaRiego extends JPanel implements ActionListener, ItemListener {
    private JButton jcomp1;
    private JButton jcomp2;
    private JButton jcomp3;
    private JTextField casillaHumedadActual;
    private JLabel titulo1;
    private JComboBox<String> selPlantas;
    private JLabel tituloHumedad;
    private JLabel tituloHumedadIdeal;
    private JTextField casillaHumedadIdeal;
    private JLabel tituloAlerta;
    private JTextField casillaAlerta;
    private JSlider jcomp12;
    private JToggleButton botonRiego;
    private JTextField jcomp14;
    private JToggleButton jcomp15;
    private JLabel tituloVolumen;
    private JTextField casillaCapacidadTanque;
    private Procesamiento P1;
    private SistemaRiego S2;
    public InterfazSistemaRiego() throws IOException {
        //construct preComponents
        String[] selPlantasItems = {"Papa", "Lechuga", "Tomate"};

        //construct components
        jcomp1 = new JButton ("Button 1");
        jcomp2 = new JButton ("Button 2");
        jcomp3 = new JButton ("Button 3");
        casillaHumedadActual = new JTextField (5);
        titulo1 = new JLabel ("Seleccione Tipo de Planta");
        selPlantas = new JComboBox <String>(selPlantasItems);
        tituloHumedad = new JLabel ("Humedad Actual");
        tituloHumedadIdeal = new JLabel ("Humedad Ideal");
        casillaHumedadIdeal = new JTextField (5);
        tituloAlerta = new JLabel ("Necesidad de Riego");
        casillaAlerta = new JTextField (5);
        jcomp12 = new JSlider (0, 20);
        botonRiego = new JToggleButton ("REGAR", false);
        jcomp14 = new JTextField (5);
        jcomp15 = new JToggleButton ("ON/OFF", false);
        tituloVolumen = new JLabel ("Capacidad del Tanque");
        casillaCapacidadTanque = new JTextField (5);

        //set components properties
        jcomp12.setOrientation (JSlider.HORIZONTAL);
        jcomp12.setMinorTickSpacing (1);
        jcomp12.setMajorTickSpacing (5);
        jcomp12.setPaintTicks (true);
        jcomp12.setPaintLabels (true);

        //adjust size and set layout
        setPreferredSize (new Dimension (944, 557));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (casillaHumedadActual);
        add (titulo1);
        add (selPlantas);
        add (tituloHumedad);
        add (tituloHumedadIdeal);
        add (casillaHumedadIdeal);
        casillaHumedadIdeal.addActionListener(this);
        add (tituloAlerta);
        add (casillaAlerta);
        add (jcomp12);
        add (botonRiego);
        botonRiego.addActionListener(this);
        add (jcomp14);
        add (jcomp15);
        add (tituloVolumen);
        add (casillaCapacidadTanque);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (-270, 125, 100, 20);
        jcomp2.setBounds (-395, 185, 100, 20);
        jcomp3.setBounds (-555, 205, 140, 20);
        casillaHumedadActual.setBounds (70, 265, 100, 25);
        titulo1.setBounds (60, 70, 156, 37);
        selPlantas.setBounds (65, 105, 145, 25);
        tituloHumedad.setBounds (70, 235, 100, 25);
        tituloHumedadIdeal.setBounds (230, 235, 100, 25);
        casillaHumedadIdeal.setBounds (230, 265, 100, 25);
        tituloAlerta.setBounds (70, 350, 120, 25);
        casillaAlerta.setBounds (70, 375, 115, 25);
        jcomp12.setBounds (-455, 235, 100, 50);
        botonRiego.setBounds (70, 430, 100, 25);
        jcomp14.setBounds (-365, 325, 100, 25);
        jcomp15.setBounds (-505, 225, 100, 25);
        tituloVolumen.setBounds (595, 235, 130, 30);
        casillaCapacidadTanque.setBounds (595, 260, 125, 25);
        
        //Extras
        P1 = new Procesamiento();
        S2 = P1.getp1().getS1();
        
        imp();
        
    }
    public void imp(){
        ArrayList<Planta> temp = S2.getListaP();
        for(int i = 0;i<temp.size();i++){
            System.out.println(temp.get(i).getNombre());
            System.out.println(temp.get(i).getHumedadIdeal());
            System.out.println(temp.get(i).getAguaSemanal());
        }
        
    }

    public void itemStateChanged(ItemEvent e){
        if(e.getSource()==selPlantas){
            String seleccionado = (String)selPlantas.getSelectedItem();
            if (seleccionado.equals("Papa")){
                Planta papa = S2.buscarNombre(seleccionado);
                String hum = casillaHumedadActual.getText();
                Float humedad = Float.parseFloat(hum);
                System.out.println(Float.toString(papa.getHumedadIdeal()));
                casillaHumedadIdeal.setText(Float.toString(papa.getHumedadIdeal()));
                casillaCapacidadTanque.setText(Float.toString(S2.getNvlTanque()));
                if (Procesamiento.compararHumedad(humedad, papa)){
                    casillaAlerta.setText("Humedad insuficiente");
                    if(Procesamiento.compararTanque(S2,papa)){
                        casillaAlerta.setText("Riego Ejecutado");
                    }
                    else{
                        casillaAlerta.setText("No hay suficiente agua en el tanque");
                    }
                } else{
                    casillaAlerta.setText("Humedad optima");
                }
            } else if(seleccionado.equals("Cebolla")){
                Planta cebolla = S2.buscarNombre(seleccionado);
                casillaHumedadIdeal.setText(Float.toString(cebolla.getHumedadIdeal()));
                casillaCapacidadTanque.setText(Float.toString(S2.getNvlTanque()));
                String hum = casillaHumedadActual.getText();
                Float humedad = Float.parseFloat(hum);
                 if (Procesamiento.compararHumedad(humedad, cebolla)){
                    casillaAlerta.setText("Humedad insuficiente");
                    if(Procesamiento.compararTanque(S2,cebolla)){
                        casillaAlerta.setText("Riego Ejecutado");
                    }
                    else{
                        casillaAlerta.setText("No hay suficiente agua en el tanque");
                    }
                } else{
                    casillaAlerta.setText("Humedad optima");
                }
            } else if(seleccionado.equals("Lechuga")){
                Planta Lechuga = S2.buscarNombre(seleccionado);
                casillaHumedadIdeal.setText(Float.toString(Lechuga.getHumedadIdeal()));
                casillaCapacidadTanque.setText(Float.toString(S2.getNvlTanque()));
                String hum = casillaHumedadActual.getText();
                Float humedad = Float.parseFloat(hum);
                 if (Procesamiento.compararHumedad(humedad, Lechuga)){
                    casillaAlerta.setText("Humedad insuficiente");
                    if(Procesamiento.compararTanque(S2,Lechuga)){
                        casillaAlerta.setText("Riego Ejecutado");
                    }
                    else{
                        casillaAlerta.setText("No hay suficiente agua en el tanque");
                    }
                } else{
                    casillaAlerta.setText("Humedad optima");
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == botonRiego){
            //poner el metodo que riegue la planta
        }
    }

    
}