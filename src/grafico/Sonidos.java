package grafico;

//import java.applet.Applet;

import java.applet.Applet;
import java.applet.AudioClip;

//import javax.sound.sampled.Clip;
//import javax.sound.sampled.FloatControl;

/*
*Clase que permite implementar los metodos para el sonido
*/
public class Sonidos {
    private AudioClip clip;
    
    public static final Sonidos[] sounds = {
        new Sonidos("/sonidos/backgroundMusic.wav"),
        new Sonidos("/sonidos/efectoCanion.wav"),
        new Sonidos("/sonidos/gameOver.wav"), 
        new Sonidos("/sonidos/gran_explosion.wav")
    };
    
    public Sonidos(String name){
        clip = Applet.newAudioClip(getClass().getResource(name));  
    }
    
    
    public void play(){
        new Thread(){
            public void run(){
                clip.play();
            }
        }.start();
    }
    
    public void loop(){
        new Thread(){
            public void run(){
                clip.loop();
            }
        }.start();
    }
    
    public void stop(){
        clip.stop();
    }
    //private Clip clip;
    //private FloatControl volumen; //atributo que permite controlar el volumen del sonido
    
    //constructor
    //public Sonidos(Clip clip) {
        //this.clip = Applet.newAudioClip(getClass().getClassLoader().getResource(clip));
        //this.volumen = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    //}
    
    //método que permite iniciar el sonido
    //public void play(){
      //  clip.setFramePosition(0);//inicio de la pista
       // clip.start();//iniciamos la pista
    //}
    
    //método que permite sonar el sonido infinitamente
    /*public void loop(){
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }*/
    
     //método que permite deterner el sonido
    //public void stop(){
      //  clip.stop();
    //}
    
    //método que permite saber el largo del sonido
    /*public int getFramePosition(){
        return clip.getFramePosition();
    }
    
    //método que permite controla el volumen de la musica
    public void changeVolume(float value){
        volumen.setValue(value);
    }*/
}
