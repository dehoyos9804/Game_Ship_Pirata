
package grafico;

import java.awt.Container;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *clase que permite pintar la explosion
 */
public class Animacion {
    private int x;//coordenada en x
    private int y;//coordenada en y
    Imagenes fotogramas;//número total de imagenes para la explosion
    int velocidad; //velocidad con la que se cambia una animación
    private int index;
    private boolean running; //advierte si la animacion esta corriendo o no
    
    //variables auxiliares para controlar el tiempo
    private long time;
    private long lastTime;
    
    //constructor
    public Animacion(int x, int y, int velocidad) {
        this.x = x;
        this.y = y;
        this.fotogramas = new Imagenes();
        this.velocidad = velocidad;
        index = 0;
        running = true;
        time = 0;
        lastTime = System.currentTimeMillis();
    }
    
    //método que permite pintar la animacion en pantalla
    public void pintarExplosiones(Graphics g, Container c){
        //registro del tiempo
        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if(time > velocidad){
            time = 0;//reseteamos el tiempo
            index++;//aumentamos el indice
            
            if(index >= fotogramas.getExplosiones().length){
                running = false;
                index = 8;
            }
        }
        g.drawImage(fotogramas.getExplosiones()[index].getImage(), (int) x, (int) y, c);
    }
    
    //saber si la animación esta corriendo o no 
    public boolean isRunning() {
        return running;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    //retorna una imagen
    public ImageIcon getCurrentFrame(){
        return fotogramas.getExplosiones()[index];
    }
    
}
