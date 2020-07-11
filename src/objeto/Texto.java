/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objeto;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *inicializa una nueva fuente
 */
public class Texto {
    public static void drawText(Graphics g, String text, int x, int y, boolean center, Color color, Font font) {
        g.setColor(color);
        g.setFont(font);
        
        if(center) {
            FontMetrics fm = g.getFontMetrics();
            x = x - fm.stringWidth(text)/2;
            y = y - fm.getHeight()/2;
        }
        
        g.drawString(text, x, y);
		
    }
}
