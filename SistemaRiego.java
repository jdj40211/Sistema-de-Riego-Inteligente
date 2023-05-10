import java.util.ArrayList;
public class SistemaRiego
{
    private float temperatura;
    private float humedadAct;
    private float nvltanque;//litros
    private ArrayList<Planta> listaP;
    public SistemaRiego(){
        this.listaP = new ArrayList<>();
        this.temperatura = 23.0f;
        this.humedadAct = 0.0f;
        this.nvltanque = 25000.0f;//25m3
    }
    public void setNvlTanque(float nvlt){
        this.nvltanque = nvlt;
    }
    public void setTemperatura(float temp){
      this.temperatura = temp;  
    }
    public float getTemperatura(){
        return this.temperatura;
    }
    public void setHumedadAct(float humedadact){
        this.humedadAct = humedadact;
    }
    public float getHumedadAct(){
        return this.humedadAct;
    }
    public Planta buscarNombre(String n){
        Planta temp = null;
        for(int i = 0; i<listaP.size();i++){
            if(listaP.get(i).getNombre().equals(n))
                temp = listaP.get(i);
        }
        return temp;
    }
    public void agregarPlanta(Planta p){
        listaP.add(p);
    }
    public ArrayList<Planta> getListaP(){
        return this.listaP;
    }
    public float getNvlTanque(){
        return this.nvltanque;
    }
}
