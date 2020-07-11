package objeto;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//clase que implementa eventos del mouse
public class MouseInput extends MouseAdapter{
    public static int x;//coordenada en x donde se encuentra el mouse
    public static int y;//coordenada en y donde se encuentra el mouse
    public static boolean MLB;//indica si se ha oprimido el boton izquirdo del mous (mouse left botton)
    
    @Override
    public void mousePressed(MouseEvent e) {
        //método que se invoca cuando se presiona uno de los botones del mouse
        if(e.getButton() == MouseEvent.BUTTON1){//si precio el boton izquierdo del mouse coloco mi variable verdadera
            MLB = true;
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        //método que se invoca cuando se suelta uno de los botones del mouse
        if(e.getButton() == MouseEvent.BUTTON1){
            MLB = false;
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        //método que se invoca cuando se presiona uno de los botones del mouse y se mueve el mouse
        x = e.getX();//obtengo las coordenas de x que tiene el cursor
        y = e.getY();//obtengo las coordenas de x que tiene el cursor
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        //método que se invoca cuando se mueve el mouse
        x = e.getX();
        y = e.getY();
    }
    
}
