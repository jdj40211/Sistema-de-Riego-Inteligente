import java.io.*;
import java.util.*;
import java.io.IOException;

public class Persistencia
{
    private File baseplantas;
    private SistemaRiego sistemaBase;
    public Persistencia() throws IOException{
            baseplantas = new File("plantas.txt");
            sistemaBase = new SistemaRiego();
            crearPlantas();
            leerPLantas();
    }
    public void crearPlantas() throws IOException{
        PrintWriter impPlantas = new PrintWriter(baseplantas);
        impPlantas.println("Cebolla");
        impPlantas.println("0.6");
        impPlantas.println("3750.0");//153m////8dias
        impPlantas.println("Papa");
        impPlantas.println("0.9");
        impPlantas.println("2100.0");//10dias
        impPlantas.println("Lechuga");
        impPlantas.println("0.75");
        impPlantas.println("2700.0");//7 dias
        impPlantas.close();
    }
    public void leerPLantas() throws IOException{
        Scanner readF = new Scanner(baseplantas);
        while(readF.hasNextLine()){
            sistemaBase.agregarPlanta(new Planta(readF.nextLine(),Float.valueOf(readF.nextLine()),Float.valueOf(readF.nextLine())));
        }
        readF.close();
    }
    public SistemaRiego getSistemaBase(){
        return this.sistemaBase;
    }

}
