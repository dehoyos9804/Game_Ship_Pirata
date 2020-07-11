package objeto;
import grafico.Imagenes;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class Jugador {
    private int x;//posicion en x
    private int y;//posicion en y
    private Imagenes imgJugador;//Imagen que representará al jugador
    private final int anchoImagen;
    private final int altoImagen;
    private AffineTransform at;
    
    private boolean visible; //verifica si el jugador no ha sido destruido
    private boolean modoResucitar;//verifica si el jugador ha perdido vidas y considera que tiene que volver a pintarse en pantalla pero sin detectar colisiones ni disparar
    private int vidas;
    private Cronometro tiempoReaparecer;
    private Cronometro tiempoParpadeo;
    
    
    //Constructor Con Parametros
    public Jugador(int x, int y) {
        this.x = x;
        this.y = y;
        this.imgJugador = new Imagenes();
        this.anchoImagen= imgJugador.getJugador()[0].getIconWidth();
        this.altoImagen= imgJugador.getJugador()[0].getIconHeight();
        this.visible = true;
        this.vidas = 100;
        this.tiempoReaparecer = new Cronometro();
        this.tiempoParpadeo = new Cronometro();
    }
    
    //método que permite pintar el jugador en pantalla
    public void pintar(Graphics g, Container c){
        if(vidas > 75){
            g.drawImage(imgJugador.getJugador()[0].getImage(), x, y, c);
        }
        
        if(vidas <= 75 && vidas >= 50){
            g.drawImage(imgJugador.getJugador()[1].getImage(), x, y, c);
        }
        
        if(vidas < 50 && vidas > 0){
            g.drawImage(imgJugador.getJugador()[2].getImage(), x, y, c);
        }
        
        if(vidas <= 0){
            g.drawImage(imgJugador.getJugador()[3].getImage(), x, y, c);
            g.drawImage(imgJugador.getFire1().getImage(), x + imgJugador.getJugador()[3].getIconWidth()/2 , y + imgJugador.getJugador()[3].getIconHeight()/2, c);
            g.drawImage(imgJugador.getFire2().getImage(), x + (imgJugador.getJugador()[3].getIconWidth()/2)-10 , (y + imgJugador.getJugador()[3].getIconHeight()/2) -10, c);
            g.drawImage(imgJugador.getFire2().getImage(), x + (imgJugador.getJugador()[3].getIconWidth()/2)+20 , (y + imgJugador.getJugador()[3].getIconHeight()/2) -20, c);
        }
    }
    
    //Método que permite Transladar al jugador
    public void trasladar(int dx, int dy){
        this.x += dx;
        this.y += dy;
        
        if(y + altoImagen >= Constantes.HEIGHT){
            y= Constantes.HEIGHT - altoImagen;
        }else{
            if(y<0){
                y = 0;
            }
        }
    }
    
    //Obtiene el centro del jugador
    public Jugador getCenter(){
        return new Jugador(this.getX() + imgJugador.getJugador()[0].getIconWidth()/2, this.getY() + imgJugador.getJugador()[0].getIconHeight() / 2);
    }
    
    //método que genera un rectangulo 
    public Rectangle getRectangle(){
        return  (new Rectangle(x, y, anchoImagen, altoImagen));
    }
    
    
    //getter y setter
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Imagenes getImgJugador() {
        return imgJugador;
    }

    public void setImgJugador(Imagenes imgJugador) {
        this.imgJugador = imgJugador;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isModoResucitar() {
        return modoResucitar;
    }

    public void setModoResucitar(boolean modoResucitar) {
        this.modoResucitar = modoResucitar;
    }

    public Cronometro getTiempoReaparecer() {
        return tiempoReaparecer;
    }

    public void setTiempoReaparecer(Cronometro tiempoReaparecer) {
        this.tiempoReaparecer = tiempoReaparecer;
    }

    public Cronometro getTiempoParpadeo() {
        return tiempoParpadeo;
    }

    public void setTiempoParpadeo(Cronometro tiempoParpadeo) {
        this.tiempoParpadeo = tiempoParpadeo;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas -= vidas;
    }
    
    public void setVid(int vidas){
        this.vidas = vidas;
    }
}
