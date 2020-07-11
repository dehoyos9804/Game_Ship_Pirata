package grafico;

import javax.swing.ImageIcon;

public class Imagenes {
    //imagenes para el fondo
    private final ImageIcon agua;
    private final ImageIcon tile26;
    private final ImageIcon tile27;
    private final ImageIcon tesoro;
    private final ImageIcon fondo;
    
    //imagen para el jugador
    private final ImageIcon[] jugador = new ImageIcon[4];
    
    //imagenes para los enemigos
    private final ImageIcon[] enemigos = new ImageIcon[4];
    
    //imagenes para disparar el cañon
    private final ImageIcon cannon;
    
    //imagenes para las explosiones
    private final ImageIcon[] explosiones = new ImageIcon[9];
    
    //imagenes de incendio
    private final ImageIcon fire1;
    private final ImageIcon fire2;
    
    
    //imagenes para dibujar el puntaje
    private final ImageIcon[] numeros = new ImageIcon[11];
    
    //otras imagen
    private final ImageIcon otros;
    
    //imagenes para los botones
    private final ImageIcon buttonGray;
    private final ImageIcon buttonBlue;
    
    //imagen que muestra la historia del juego
    private final ImageIcon historia;
    
    //imagen que muestra las instrucciones del juego
    private final ImageIcon instrucciones;
    
    public Imagenes(){
        //cargar fondo
        this.agua = new ImageIcon(getClass().getResource("/fondo/agua.png"));
        this.tile26 = new ImageIcon(getClass().getResource("/fondo/tile26.png"));
        this.tile27 = new ImageIcon(getClass().getResource("/fondo/tile27.png"));
        this.tesoro = new ImageIcon(getClass().getResource("/fondo/tesoro.png"));
        this.fondo = new ImageIcon(getClass().getResource("/fondo/fondo.png"));
        
        //cargar jugador
        for(int i = 0; i < jugador.length; i++){
            jugador[i] = new ImageIcon(getClass().getResource("/ships/jugador" + i + ".png"));
        }
        
        //cargar enemigos
        for(int i = 0; i < enemigos.length; i++){
            enemigos[i] = new ImageIcon(getClass().getResource("/ships/enemigo" + i + ".png"));
        }
        
        //cargar disparos para el cañon
        this.cannon = new ImageIcon(getClass().getResource("/balas/cannon.png"));
        
        //cargar las explosiones
        for(int i = 0; i < explosiones.length; i++){
            explosiones[i] = new ImageIcon(getClass().getResource("/explosion/" + i + ".png"));
        }
        
        //cargar imagenes de incendio
        fire1 = new ImageIcon(getClass().getResource("/explosion/fire1.png"));
        fire2 = new ImageIcon(getClass().getResource("/explosion/fire2.png"));
        
        //cargo los numeros
        for(int i = 0; i < numeros.length; i++){
            numeros[i] = new ImageIcon(getClass().getResource("/numeros/" + i + ".png"));
        }
        
        //pintar otra imagen
        this.otros = new ImageIcon(getClass().getResource("/otros/simboloPirata.png"));
        
        //cargar las imagenes que estaran en el boton
        this.buttonGray = new ImageIcon(getClass().getResource("/ui/grey_button.png"));
        this.buttonBlue = new ImageIcon(getClass().getResource("/ui/blue_button.png"));
        
        //cargar la imagen de la historia
        this.historia = new ImageIcon(getClass().getResource("/otros/historia.png"));
        
        //cargar la imagen de las instrucciones
        this.instrucciones = new ImageIcon(getClass().getResource("/otros/instrucciones.png"));
        
    }

    public ImageIcon getAgua() {
        return agua;
    }

    public ImageIcon getTile26() {
        return tile26;
    }

    public ImageIcon getTile27() {
        return tile27;
    }

    public ImageIcon[] getJugador() {
        return jugador;
    }

    public ImageIcon[] getEnemigos() {
        return enemigos;
    }

    public ImageIcon getTesoro() {
        return tesoro;
    }

    public ImageIcon getCannon() {
        return cannon;
    }

    public ImageIcon[] getExplosiones() {
        return explosiones;
    }

    public ImageIcon[] getNumeros() {
        return numeros;
    }

    public ImageIcon getOtros() {
        return otros;
    }

    public ImageIcon getButtonGray() {
        return buttonGray;
    }

    public ImageIcon getButtonBlue() {
        return buttonBlue;
    }

    public ImageIcon getFire1() {
        return fire1;
    }

    public ImageIcon getFire2() {
        return fire2;
    }

    public ImageIcon getFondo() {
        return fondo;
    }

    public ImageIcon getHistoria() {
        return historia;
    }

    public ImageIcon getInstrucciones() {
        return instrucciones;
    }
    
    
}
