package objeto;

//clase que representara los atributos de un boton en pantalla

import grafico.Imagenes;
import grafico.Loader;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Botones {
    private Imagenes ImageOutMouse;//imagen que tendra el boton cuando el mouse esté encima
    private Imagenes ImageInMouse;//imagen que tendra el boton cuando el mouse esté encima
    private boolean mouse_encima;//indica si el mouse esta encima del boton
    private Rectangle boton;//rectangulo que represanta la caja del boton
    private String texto;//texto que estara en el boton
    private AccionBoton accion;//que accion tendra el boton
    
    //constructor con parametros

    public Botones(int x, int y, String texto, AccionBoton accion) {
        ImageOutMouse = new Imagenes();
        ImageInMouse = new Imagenes();
        this.texto = texto;
        boton = new Rectangle(x, y, ImageInMouse.getButtonBlue().getIconWidth(), ImageInMouse.getButtonBlue().getIconHeight());
        this.accion = accion;
    }
    
    //método que permite actualiza
    public void actualizar(){
        //preguntamos si las coordenas del mouse estan dentro del boton 
        //@contains devuelve un boolean con respecto a las coordenas que se les pasan
        if(boton.contains(MouseInput.x, MouseInput.y)){
            mouse_encima = true;
        }else{
            mouse_encima = false;
        }
        
        //si el mouse se encuentra en las coordenas del boton y presiono el boton derecho creo un evento
        if(mouse_encima && MouseInput.MLB){
            accion.crearAccion();//verifico que accion voy a realizar
        }
    }
    
    //método pintar 
    public void pintarBotones(Graphics g, Container c){
        if(mouse_encima){
            g.drawImage(ImageInMouse.getButtonBlue().getImage(), (int) boton.getX(), (int) boton.getMinY(), c);
        }else{
            g.drawImage(ImageOutMouse.getButtonGray().getImage(), (int) boton.getX(), (int) boton.getMinY(), c);
        }
        
        //dibujamos el texto
        Texto.drawText(g, texto, (int)(boton.getX() + boton.getWidth()/2), (int)(boton.getY() + boton.getHeight()), true, Color.BLACK, Loader.getFuente(20));
    }

    public Imagenes getImageOutMouse() {
        return ImageOutMouse;
    }

    public Imagenes getImageInMouse() {
        return ImageInMouse;
    }
    
    
}
