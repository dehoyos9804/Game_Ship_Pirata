package objeto;
/*
*clase que permite cronometrar el tiempo transcurrido en un metodo o evento de cualquie clase
*/
public class Cronometro {
    private long delta;//representa el tiempo que transcurrio al iniciar una evento y mientras finaliza
    private long tiempoTrascurrido;
    private long tiempo;//tiempo en que demora la actividad
    private boolean corriendo;//verifica si se ha inicializado el cronometro esta corriendo o no
    
    //constructor
    public Cronometro() {
        delta = 0;
        tiempoTrascurrido = 0;
        corriendo = false;
    }
    
    //método que permite iniciar el cronometro
    public void run(long time){
        corriendo = true;
	this.tiempo = time;
    }
    
    //método que actualiza el tiempo
    public void update(){	
	if(corriendo){
	    delta += System.currentTimeMillis() - tiempoTrascurrido;
        }
        if(delta >= tiempo){
            corriendo = false;
            delta = 0;
        }		
	tiempoTrascurrido = System.currentTimeMillis();
    }
    
    //geter que verifica si el cronometro esta corriendo

    public boolean isCorriendo() {
        return corriendo;
    }
    
    
}
