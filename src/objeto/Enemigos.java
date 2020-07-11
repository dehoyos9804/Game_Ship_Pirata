package objeto;

import grafico.Imagenes;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemigos {
    private int x;//posicion en x
    private int y;//posicion en y
    private Imagenes imgEnemigo;//Imagen que representará al enemigo
    private int indexImagen;
    private boolean numerovidas;
    
    //Constructor Con Parametros
    public Enemigos(int x, int y) {
        this.x = x;
        this.y = y;
        this.imgEnemigo = new Imagenes();
        this.indexImagen = (int)(Math.random()*4);
        this.numerovidas = true;
    }
    
    //método que permite pintar el jugador en pantalla
    public void pintarEnemigo(Graphics g, Container c){
       g.drawImage(imgEnemigo.getEnemigos()[indexImagen].getImage(), (int) x, (int) y, c);
    }
    
    //Método que permite Transladar al jugador
    public void trasladar(int dx, int dy){
        this.x += dx;
        this.y += dy;
    
        int anchoImagen = imgEnemigo.getEnemigos()[0].getIconWidth();
        int altoImagen = imgEnemigo.getEnemigos()[0].getIconHeight();
        
        if(x +  anchoImagen>= Constantes.WIDTH){
            x = Constantes.WIDTH - anchoImagen; 
        }else{
            if(x<60){
              x = 60;          
            }
        }
        
        if(y + altoImagen >= Constantes.HEIGHT){
            y= Constantes.HEIGHT - altoImagen;
        }else{
            if(y<0){
                y = 0;
            }
        }
    }
    
    //Obtiene el centro del jugador
    public Enemigos getCenter(){
        return new Enemigos(this.getX() + imgEnemigo.getEnemigos()[0].getIconWidth()/2, this.getY() + imgEnemigo.getEnemigos()[0].getIconHeight() / 2);
    }
   
    //método que genera un rectangulo
    public Rectangle getRectangle(){
        int anchoImagen = imgEnemigo.getEnemigos()[0].getIconWidth();
        int altoImagen = imgEnemigo.getEnemigos()[0].getIconHeight();
        return  (new Rectangle(x, y, anchoImagen, altoImagen));
    }
    
    //Creamos Getters y Setters
    public int getX() {
        return x;
    }

    public int getIndexImagen() {
        return indexImagen;
    }

    public void setIndexImagen(int indexImagen) {
        this.indexImagen = indexImagen;
    }

    public boolean isNumerovidas() {
        return numerovidas;
    }

    public void setNumerovidas(boolean numerovidas) {
        this.numerovidas = numerovidas;
    }
    
    public int getY() {
        return y;
    }

    public Imagenes getImgEnemigo() {
        return imgEnemigo;
    }

    public void setImgEnemigo(Imagenes imgEnemigo) {
        this.imgEnemigo = imgEnemigo;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
