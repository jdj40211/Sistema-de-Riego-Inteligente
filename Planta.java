class Planta
{
    private String nombre;
    private float humedad_ideal;
    private float agua_semanal;//litros
    //Tiempo Riego
    public Planta(String name,float humedad, float aguaS){
        this.nombre = name;
        this.humedad_ideal = humedad;
        this.agua_semanal = aguaS;
    }
    public Planta(){}
    //private date tiempoRiego;
    public String getNombre(){
        return this.nombre;
    }
    public float getHumedadIdeal(){
        return humedad_ideal;
    }
    public float getAguaSemanal(){
        return this.agua_semanal;
    }
    //get,set tiempoRiego
}
