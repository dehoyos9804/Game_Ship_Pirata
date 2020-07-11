package objeto;

import grafico.Imagenes;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Cannion {
    //dimensiones que tendra el cañon en el plano
    private int x;
    private int y;
    private Imagenes imgCannion;

    //Constructor Con Parametros
    public Cannion(int x, int y) {
        this.x = x;
        this.y = y;
        imgCannion = new Imagenes();
    }
    
    //Método que permite Transladar a la bala de cañon
    public void trasladar(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }
    
    //método que permite pintar la bala de cañon en pantalla
    public void pintarCannion(Graphics g, Container c){
        g.drawImage(imgCannion.getCannon().getImage(), x, y, c);
    }
    
    //método que genera un rectangulo 
    public Rectangle getRectangle(){
        int anchoImagen = imgCannion.getCannon().getIconWidth();
        int altoImagen = imgCannion.getCannon().getIconHeight();
        return  (new Rectangle(x, y, anchoImagen, altoImagen));
    }
    
    //getter y setter
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

    public Imagenes getImgCannion() {
        return imgCannion;
    }

    public void setImgCannion(Imagenes imgCannion) {
        this.imgCannion = imgCannion;
    }
}
