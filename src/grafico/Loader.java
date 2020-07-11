
package grafico;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

/*clase que permite cargar fuentes en memoria*/
public class Loader {
    public static Font loadFont(String path, int size) {
	try {
            return Font.createFont(Font.TRUETYPE_FONT, Loader.class.getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
	} catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return null;
	}
    }
    
    public static Font getFuente(int tamaño){
        Font f = Loader.loadFont("/fuentes/futureFont.ttf", tamaño);
        return f;
    }
}        
