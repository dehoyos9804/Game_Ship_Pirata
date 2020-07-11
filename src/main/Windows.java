package main;

import grafico.Imagenes;
import grafico.Loader;
import grafico.Sonidos;
import objeto.Constantes;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import objeto.AccionBoton;
import objeto.Botones;
import objeto.MouseInput;
import objeto.Texto;

public class Windows extends Applet implements KeyListener, Runnable {

    Imagenes image;//clase que contiene toda las imagenes
    EstadoJuego estadoJuego;//clase que tiene toda la logica de juego @jugador, enemigos, colisiones, disparos, efectos y más
    EstadoMenu estadoMenu;//clase que tiene toda la logica del menu
    private Thread hilo;//hilo nuevo
    private boolean running;
    Sonidos sonido;
    Sonidos historia;
    int velocidadEnemigo = -3;
    
    boolean puedopintar; //boolean que me indica si puedo pintar el mensaje de escenario
    int escenario;//variable que sera evaluada para realizar los diferentes escenarios en cada nivel
    
    //llamo la clase de eventos del mouse
    private MouseInput mouseInput;
    Botones btnRInstrucciones;//creo el boton regresar para futuros 
    Botones btnRHistoria;//

    @Override
    public void init() {
        this.setSize(Constantes.WIDTH, Constantes.HEIGHT);
        running = true;
        this.addKeyListener(this);
        this.addMouseListener(mouseInput);
        this.addMouseMotionListener(mouseInput);
    }

    public Windows() {
        image = new Imagenes();
        mouseInput = new MouseInput();//inicio la clase del mouse
        puedopintar = true;
        escenario = 0;
        estadoJuego = new EstadoJuego(); //inicio la clase Estado juego
        estadoMenu = new EstadoMenu();//inicio la clase Estado Menu
        estadoJuego.iniciarJugador(Constantes.X_JUGADOR, Constantes.Y_JUGADOR);//inicio el jugador
        estadoJuego.iniciarOlaEnemigos(Constantes.WIDTH - image.getEnemigos()[0].getIconWidth());//iniciar ola de enemigos
        sonido = new Sonidos("/sonidos/backgroundMusic.wav");
        historia = new Sonidos("/sonidos/historia.wav");
        //agrego el boton de instrucciones
        btnRInstrucciones = new Botones(Constantes.WIDTH / 2 - image.getButtonGray().getIconWidth() / 2,(Constantes.HEIGHT - image.getButtonGray().getIconWidth())+100,
                 "Regresar",
                new AccionBoton() {
                    @Override
                    public void crearAccion() {
                        estadoMenu.setIniciarInstrucciones(false);
                        escenario = 0;
                        historia.stop();
                    }
                }
        );
        
        //agrego el boton de historia
        btnRHistoria = new Botones(Constantes.WIDTH / 2 - image.getButtonGray().getIconWidth() / 2,(Constantes.HEIGHT - image.getButtonGray().getIconWidth())+100,
                 "Regresar",
                new AccionBoton() {
                    @Override
                    public void crearAccion() {
                        estadoMenu.setIniciarHistoria(false);
                        escenario = 0;
                        historia.stop();
                    }
                }
        );
    }

    @Override
    public void paint(Graphics g) {
        //pintamos nuestro menu
        if(escenario == 0){
            escenarioMenu(g);
        }
        
        //pinto las instrucciones
        if(escenario == 4){
            pintarFondo(g);
            Texto.drawText(g, "Instrucciones del Juego", Constantes.WIDTH/2, 80 , true, Color.BLACK, Loader.getFuente(30));
            g.drawImage(image.getInstrucciones().getImage(), 0, 0, this);
            btnRInstrucciones.pintarBotones(g, this);
        }
        
        //pinto la historia
        if(escenario == 5){
            pintarFondo(g);
            Texto.drawText(g, "Historia", Constantes.WIDTH/2, 80 , true, Color.BLACK, Loader.getFuente(30));
            g.drawImage(image.getHistoria().getImage(), 0, 0, this);
            btnRHistoria.pintarBotones(g, this);
        }
        
        //pintamos nuestras escenas del juego
        if(estadoJuego.getJugador().getVidas() > 0){
              escenarios(g);
        }else{
            pintarFondo(g);
            estadoJuego.pintarEnemigos(g, this);
            estadoJuego.pintarJugador(g, this);
            estadoJuego.pintarPuntaje(g, this);
            estadoJuego.pintarVidas(g, this);
            esperar(2000);
            escenafinalizar(g);
        }
    }

    //método que permite pintar el fondo 
    private void pintarFondo(Graphics g) {
        //pintamos el agua 
        for (int i = 0; i < Constantes.HEIGHT; i += image.getAgua().getIconHeight()) {
            for (int j = 0; j < Constantes.WIDTH; j += image.getAgua().getIconWidth()) {
                g.drawImage(image.getAgua().getImage(), j, i, this);
            }
        }

        //pintamos las islas
        for (int i = 0; i < Constantes.HEIGHT; i += image.getTile26().getIconHeight()) {
            g.drawImage(image.getTile26().getImage(), 100, i, this);
            g.drawImage(image.getTile27().getImage(), 0, i, this);
        }
        
    }   
    
    //método que permite pintar el escenario del menu con sus eventos
    private void escenarioMenu(Graphics g){
        //creo el menu
        pintarFondo(g);
        estadoMenu.pintarTodosBotones(g, this);//pinto todo los botones necesarios
            
        //si ejecutamos el boton de iniciar juego se empiezo el juego
        if(estadoMenu.isEmpezarjuego()){
            escenario = 1;
            sonido.loop();//inicio el sonido de la musica de fondo
        }
        
        if(estadoMenu.isIniciarInstrucciones()){
            escenario = 4;
            historia.loop();
        }
        
        if(estadoMenu.isIniciarHistoria()){
            escenario = 5;
            historia.loop();
        }
    }
    
    
    //método que permite pintar el escenario 1
    private void escenarios(Graphics g){
        switch(escenario){
            case 1://nivel 1
                pintarFondo(g);
                //pintar el mensaje del nivel una sola vez por cada escena que se repita
                if(puedopintar){
                    estadoJuego.addWaves();
                    puedopintar = false;
                }
                estadoJuego.pintarJugador(g, this);
                estadoJuego.pintarEnemigos(g, this);
                estadoJuego.pintarCanniones(g, this);
                estadoJuego.pintarPuntaje(g, this);
                estadoJuego.pintarVidas(g, this);
                estadoJuego.actualizarExplosion(g, this);
                estadoJuego.actualizarResurrecion();
                
                //si el número de enemigos de la primera oleadas es cero, implementamos el segundo nivel
                if(estadoJuego.getEnemigos().isEmpty()){
                   estadoJuego.setNumeroOleadas(2);
                   velocidadEnemigo = -6;//duplico la velocidad
                   puedopintar = true;
                   estadoJuego.setNumeroenemigos(40);//duplico el numero de enemigos 
                   //inicio una nueva oleada de enemigos
                   estadoJuego.iniciarOlaEnemigos(Constantes.WIDTH - image.getEnemigos()[0].getIconWidth());
                   escenario = 2;//cambio el escenario a 2
                }
                break;
            case 2://nivel 2
                pintarFondo(g);
                //pintar el mensaje de escenario una sola vez por cada escena que se repita
                if(puedopintar){
                    estadoJuego.addWaves();
                    puedopintar = false;
                }
                estadoJuego.pintarJugador(g, this);
                estadoJuego.pintarEnemigos(g, this);
                estadoJuego.pintarCanniones(g, this);
                estadoJuego.pintarPuntaje(g, this);
                estadoJuego.pintarVidas(g, this);
                estadoJuego.actualizarExplosion(g, this);
                estadoJuego.actualizarResurrecion();
                
                //si el jugador pasa el nivel 2 pasamos al escenario de ganar
                if(estadoJuego.getEnemigos().isEmpty()){
                    escenario = 3;
                    //detengo la musica de fondo 
                    sonido.stop();
                }
                break;
            case 3:
                pintarFondo(g);
                estadoJuego.pintarEnemigos(g, this);
                estadoJuego.pintarJugador(g, this);
                estadoJuego.pintarPuntaje(g, this);
                estadoJuego.pintarVidas(g, this);
                esperar(2000);
                
                pintarFondo(g);
                Texto.drawText(g, "GANADOR :-)", Constantes.WIDTH/2, 100 , true, Color.BLACK, Loader.getFuente(60));
                pintarTexto(g);
                break;
        }
    }
    
    
    //método que permite pintar la escena de perder
    private void escenafinalizar(Graphics g){
        sonido.stop();//detengo la musica de fondo
        //g.setColor(Color.BLACK);
        //g.fillRect(0, 0, Constantes.WIDTH, Constantes.HEIGHT);
        pintarFondo(g);
        Sonidos.sounds[2].play();//sonido game Over
        esperar(1000);
        //estadoJuego.addTexto("GAME OVER");
        Texto.drawText(g, "GAME OVER", Constantes.WIDTH/2, Constantes.HEIGHT/2, true, Color.WHITE, Loader.getFuente(70));
        
        esperar(3000);
        pintarFondo(g);
        Texto.drawText(g, "PERDEDOR :'(", Constantes.WIDTH/2, 100 , true, Color.BLACK, Loader.getFuente(60));
        pintarTexto(g);
    }


    @Override
    public void run() {
        while (running) {
            if(escenario == 0){
                estadoMenu.actualizarTodosBotones();//actualizar los botones necesarios
                repaint();
            }
            
            //si el jugador no ha perdido las vidas se puede seguir traslado las balas y los enemigos
            if(estadoJuego.getJugador().getVidas() > 0 && escenario != 0 && escenario!=4 && escenario !=5){
                estadoJuego.transladarCanniones(Constantes.VELOCIDAD_CANNON, 0);//trasladamos los cañones
                estadoJuego.transladarEnemigos(velocidadEnemigo, 0);//trasladamos a los enemigos
                repaint();
            }
            
            //si se clickeo el boton regresar automaticamente se pinta el menu principa
            if(escenario == 4){
                btnRInstrucciones.actualizar();
                repaint();
            }
            
            //si se clickeo el boton regresar automaticamente se pinta el menu principa
            if(escenario == 5){
                btnRHistoria.actualizar();
                repaint();
            }
            
            esperar(100);
        }
    }

    /*inicia el hilo*/
    public void start() {
        hilo = new Thread(this);
        hilo.start();
    }

    //detener el hilo
    public void stop() {
        running = false;
    }

    //métodos abstractos de la clase 
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        mover(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    //método que permite mover objetos @jugador, enemigos
    private void mover(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                estadoJuego.trasladarJugador(0, -Constantes.ESPACIO);
                break;

            case KeyEvent.VK_DOWN:
                estadoJuego.trasladarJugador(0, Constantes.ESPACIO);
                break;

            case KeyEvent.VK_SPACE:
                if (!estadoJuego.getJugador().isModoResucitar() || estadoJuego.getJugador().getVidas() > 0) {//verifica si la variable resucitar esta desactivada   
                    Sonidos.sounds[1].play();//sonido de cañon
                    estadoJuego.iniciarCanniones();
                }
                break;

            default:
                e.consume();
                break;
        }
    }

    //método que permite validar pintar imagen al realizarse el movimiento
    private void esperar(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException ex) {
            Logger.getLogger(Windows.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*metodo que permite pintar los nombre e integrantes*/
    private void pintarTexto(Graphics g) {
        //g.setColor(Color.BLACK);
        //g.fillRect(0, 0, Constantes.WIDTH, Constantes.HEIGHT);
        Texto.drawText(g, "NUMERO TOTAL ENEMIGOS FALTANTES: " +(estadoJuego.getEnemigos().size()), Constantes.WIDTH/2, 200 , true, Color.BLACK, Loader.getFuente(30));
        Texto.drawText(g, "NUMERO DE VIDA: " + (estadoJuego.getJugador().getVidas()< 0 ? 0 : estadoJuego.getJugador().getVidas()), Constantes.WIDTH / 2 + 40, 250 , true, Color.BLACK, Loader.getFuente(30));
        Texto.drawText(g, "NIVEL ALCANZADO: " + (escenario == 3 ? 2 : escenario), Constantes.WIDTH / 2,300 , true, Color.BLACK, Loader.getFuente(30));
        Texto.drawText(g, "PUNTAJE: " + estadoJuego.getPuntuacion(), Constantes.WIDTH / 2, 350 , true, Color.BLACK, Loader.getFuente(30));
   
        
        Texto.drawText(g, "Corporación Unificada de Educacion Superior", Constantes.WIDTH / 2, Constantes.HEIGHT - 80, true, Color.BLACK, Loader.getFuente(20));
        Texto.drawText(g, "Aldair Luis De Hoyos Teran", Constantes.WIDTH / 2, Constantes.HEIGHT - 60, true, Color.BLACK, Loader.getFuente(20));
        Texto.drawText(g, "Adalberto Segundo Perez Muñoz", Constantes.WIDTH / 2, Constantes.HEIGHT - 40, true, Color.BLACK, Loader.getFuente(20));
        Texto.drawText(g, "Ingenieria De Sistema - Diurna", Constantes.WIDTH / 2, Constantes.HEIGHT - 20, true, Color.BLACK, Loader.getFuente(20));
        Texto.drawText(g, "2019- periodo 2", Constantes.WIDTH / 2, Constantes.HEIGHT, true, Color.BLACK, Loader.getFuente(20));
        esperar(5000);
        System.exit(0);//termino el juego
        
    }
}
