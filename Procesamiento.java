import java.io.IOException;

public class Procesamiento
{
    Persistencia persistencia;
    public Procesamiento()throws IOException{
        persistencia = new Persistencia();
    }
    public static boolean compararTanque(SistemaRiego actual, Planta necesario){
        if(actual.getNvlTanque() >= necesario.getAguaSemanal()){
            actual.setNvlTanque(actual.getNvlTanque() - necesario.getAguaSemanal());
            return true;
        }
        return false;
    }
     public static boolean compararHumedad(float actual, float ideal){
        return (ideal-actual)>0.15;
    }
    public static boolean compararHumedad(float actual, Planta ideal){
        return (ideal.getHumedadIdeal()-actual)>0.15;
    }
    public Persistencia getPersistencia(){
        return this.persistencia;
    }
}   
