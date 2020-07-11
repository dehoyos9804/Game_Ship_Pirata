package objeto;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import main.EstadoJuego;

public class Message {
    EstadoJuego gameState;
    float alpha;
    String text;//texto que se mostrara cuando salga la animación 
    int x;//coordenada en x
    int y;//coordenada en y
    Color color;//color de la animacion
    boolean center;//boleado para saber si sera centrado 
    boolean fade;//boolean que permite saber cuando desvanecer la animacion
    Font font;//tipo de fuente
    private final float deltaAlpha = 0.01f;
	
    public Message(int x, int y, boolean fade, String text, Color color,
			boolean center, Font font, EstadoJuego gameState) {
	this.font = font;
	this.gameState = gameState;
	this.text = text;
	this.x = x;
        this.y = y;
	this.fade = fade;
	this.color = color;
	this.center = center;
		
	if(fade)
            alpha = 1;
	else
            alpha = 0;
    }
	
    public void draw(Graphics2D g2d) {
		
	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		
	Texto.drawText(g2d, text, x, y, center, color, font);
		
	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		
	y = y -1;
		
	if(fade)
            alpha -= deltaAlpha;
	else
            alpha += deltaAlpha;
		
	if(fade && alpha < 0) {
            gameState.getMessage().remove(this);
	}
		
	if(!fade && alpha > 1) {
	    fade = true;
	    alpha = 1;
	}
    }
}
