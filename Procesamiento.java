import java.io.IOException;

public class Procesamiento
{
    Persistencia p1;
    public Procesamiento()throws IOException{
        p1 = new Persistencia();
    }
    public static boolean compararTanque(SistemaRiego actual, Planta necesario){
        if(actual.getNvlTanque() >= necesario.getAguaSemanal()){
            actual.setNvlTanque(actual.getNvlTanque() - necesario.getAguaSemanal());
            return true;
        }
        return false;
    }
     public static boolean compararHumedad(float actual, float ideal){
         System.out.println(ideal-actual);
        return (ideal-actual)>0.15;
    }
    public static boolean compararHumedad(float actual, Planta ideal){
        return (ideal.getHumedadIdeal()-actual)>0.15;
    }
    public Persistencia getp1(){
        return this.p1;
    }
}   
