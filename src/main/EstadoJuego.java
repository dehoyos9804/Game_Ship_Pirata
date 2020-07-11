package main;

import grafico.Animacion;
import grafico.Loader;
import grafico.Sonidos;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import objeto.Cannion;
import objeto.Constantes;
import objeto.Enemigos;
import objeto.Jugador;
import objeto.Message;

public class EstadoJuego {

    private Jugador jugador;//instancia la clase jugador
    ArrayList<Cannion> canniones;//creo un arraylist con objetos de tipo cañon
    private ArrayList<Enemigos> enemigos;//creo un arraylist con objetos de tipo enemigo
    ArrayList<Animacion> explosiones;
    //fuentes
    private ArrayList<Message> message = new ArrayList<>();
    
    //variable que define la cantidad de enemigos que van a iniciar
    private int puntuacion = 0;//puntaje actual
    private int numeroenemigos;
    private int numeroOleadas = 1;
    
    
    public EstadoJuego() {
        canniones = new ArrayList<>();
        explosiones = new ArrayList<>();
        numeroenemigos = 20;
    }

    //método que permite iniciar el jugador
    public void iniciarJugador(int x, int y) {
        jugador = new Jugador(x, y);
    }

    //método que me permite obtener el jugador
    public Jugador getJugador() {
        return jugador;
    }

    //metodo que permite pintar una nueva Imagen del jugador
    public void pintarJugador(Graphics g, Container c) {
        jugador.pintar(g, c);
        //pintarVidas(g, c);
    }

    //método que permite trasladar el jugador en el plano
    public void trasladarJugador(int x, int y) {
        jugador.trasladar(x, y);
    }

    //Método que permite iniciar todos los cañones para disparar
    public void iniciarCanniones() {
        int x = jugador.getCenter().getX();
        int y = jugador.getCenter().getY();

        canniones.add(new Cannion(x, y));
    }

    //Método que permite obtener los cañones
    public ArrayList<Cannion> getCanniones() {
        return canniones;
    }

    //Método que permite Trasladar todos los canniones
    public void transladarCanniones(int dx, int dy) {
        for (int i = 0; i < canniones.size(); i++) {
            canniones.get(i).trasladar(dx, dy);
            
            //si la bala de cañon se paso de los limites y no impacto con ningún enemigo lo removemos de la ista
            if(canniones.get(i).getX() > Constantes.WIDTH){
                canniones.remove(i);
            }
        }
    }

    //Método que permite Pintar todos los cañones en el plano
    public void pintarCanniones(Graphics g, Container c) {
        for (int i = 0; i < canniones.size(); i++) {
            canniones.get(i).pintarCannion(g, c);
        }
    }

    //Método que permite obtener el enemigo
    public ArrayList<Enemigos> getEnemigos() {
        return enemigos;
    }

    //Método que permite Trasladar todos los enemigos
    public void transladarEnemigos(int dx, int dy) {
        int numerointeraciones = 5;
        for (int i = 0; i < numerointeraciones; i++) {
            if(!enemigos.isEmpty()){
                enemigos.get(i).trasladar(dx, dy);
                //si el enemigo llego a la lista le quitara 10 puntos de vida a nuestro heroe
                if(enemigos.get(i).getX() == 60){
                    resVida(10, enemigos.get(i).getX(), enemigos.get(i).getY());
                    enemigos.remove(i);
                }else if(enemigos.get(i).getX() != 60){
                    colisionaEnemigoCannion(i);//detecto colision con el cañon
                    colisionEnemigoJugador();//detecto colision con el jugador
                }
            }
            if(enemigos.size() < numerointeraciones){
                numerointeraciones = enemigos.size();
            }
            
        }
    }

    //Método que permite Pintar todos los enemigo en el plano
    public void pintarEnemigos(Graphics g, Container c) {
        int numerointeraciones = 5;
        for (int i = 0; i < numerointeraciones; i++) {
            if(!enemigos.isEmpty()){
             enemigos.get(i).pintarEnemigo(g, c);
            }
            if(numerointeraciones > enemigos.size()){
                 numerointeraciones = enemigos.size();
             } 
        }
    }

    //método que permite iniciar una ola de enemigos
    public void iniciarOlaEnemigos(int x) {
        double y;
        enemigos = new ArrayList<>();
        //message.add(new Message(Constantes.WIDTH/2, Constantes.HEIGHT/2, true, "Inicio " + numeroOleadas, Color.WHITE, true, Loader.getFuente(42), this));
        try {
            for (int i = 0; i < numeroenemigos; i++) {
                y = Math.random() * Constantes.HEIGHT;
                enemigos.add(new Enemigos((int) x, (int) y));//agregar nuevo objeto al arreglo
            }
        } catch (Exception e) {
            System.err.println("Error al iniciar nueva ola");
        }
    }

    //método que permite saber si enemigo colisiona con el cañon
    public void colisionaEnemigoCannion(int indice){
        try {
            for(int i = 0; i < canniones.size(); i++){
                if(enemigos.get(indice).getRectangle().intersects(canniones.get(i).getRectangle())){
                    iniciarAnimaion(enemigos.get(indice).getX() + enemigos.get(indice).getImgEnemigo().getEnemigos()[0].getIconWidth()/2, enemigos.get(indice).getY() + enemigos.get(indice).getImgEnemigo().getEnemigos()[0].getIconHeight()/2);
                    //al destruir la embarcación enemiga puedo agregar 2 punto a mi puntaje
                    addPuntaje(Constantes.PUNTUACION_DESTRUIR_ENEMIGO, enemigos.get(indice).getX(), enemigos.get(indice).getY());
                    //Sonidos.sounds[3].play();//sonido de explosion
                    enemigos.remove(indice);//destruyo el enemigo
                    canniones.remove(i);//destruyo la bala de cañon   
                }
            }
            
        } catch (Exception e) {
            System.err.println("Han Ocurrido en la colision Enemigo <-> Cañones indice(" +indice +") " + e.getLocalizedMessage());
        }
    }
    
    //método que permite saber si enemigo colisiona con el cañon
    public void colisionEnemigoJugador(){
        
        for(int i = 0; i < enemigos.size(); i++){
            if(jugador.isModoResucitar()){
                return;
            }
            if(jugador.getRectangle().intersects(enemigos.get(i).getRectangle())){
                iniciarAnimaion(enemigos.get(i).getX() + enemigos.get(i).getImgEnemigo().getEnemigos()[0].getIconWidth()/2, enemigos.get(i).getY() + enemigos.get(i).getImgEnemigo().getEnemigos()[0].getIconHeight()/2);
                iniciarAnimaion(jugador.getX() + jugador.getImgJugador().getJugador()[0].getIconWidth()/2, jugador.getY() + jugador.getImgJugador().getJugador()[0].getIconWidth()/2);
                //disminuyo la vida
                resVida(Constantes.PUNTAJE_DISMINUIR_VIDAS, jugador.getX(), jugador.getY());
                //al destruir la embarcación enemiga puedo agregar 2 punto a mi puntaje
                addPuntaje(Constantes.PUNTUACION_DESTRUIR_ENEMIGO, enemigos.get(i).getX(), enemigos.get(i).getY());
                jugador.setModoResucitar(true);
                jugador.getTiempoReaparecer().run(Constantes.TIEMPO_REAPARECER);
                jugador.getTiempoParpadeo().run(Constantes.TIEMPO_PARPADEO);
                //trasladarJugador(0, -jugador.getY() + 5);//inicio desde el punto inicial
                Sonidos.sounds[3].play();//sonido de explosion
                enemigos.remove(i);//detruyo el enemigo
            }
        }
    }

    //Método que permite iniciar todo las animaciones se inicien
    public void iniciarAnimaion(int x , int y){
        explosiones.add(new Animacion(x, y, 80));
    }

    //Método que permite obtener todas las explosiones
    public ArrayList<Animacion> getExplosion() {
        return explosiones;
    }

    
    //método que permite actualizar la animacion
    public void actualizarExplosion(Graphics g, Container c){
        for(int i = 0; i < explosiones.size(); i++){
            explosiones.get(i).pintarExplosiones(g, c);
            
            if(!explosiones.get(i).isRunning()){
                explosiones.remove(i);
            }
        }
    }
    
    //método que permite actualizar cuando el jugador tiene colision
    public void actualizarResurrecion(){
        if(!jugador.getTiempoReaparecer().isCorriendo()){
            jugador.setModoResucitar(false);
            jugador.setVisible(true);
        }
        
        if(jugador.isModoResucitar()){
            if(!jugador.getTiempoParpadeo().isCorriendo()){
                jugador.getTiempoParpadeo().run(Constantes.TIEMPO_PARPADEO);
                jugador.setVisible(!jugador.isVisible());
            }
        }
        
        jugador.getTiempoReaparecer().update();
        jugador.getTiempoParpadeo().update();
    }
    
    //método que permite agregar valor a la puntuación
    public void addPuntaje(int valor, int x, int y){
        puntuacion += valor;
        message.add(new Message(x, y, true, "+" + valor + " puntos", Color.WHITE, false, Loader.getFuente(15), this));
    }
    
    //método que permite agregar el número de olas
    public void addWaves(){
        message.add(new Message(Constantes.WIDTH/2, Constantes.HEIGHT/2, true, " NIVEL +" +numeroOleadas, Color.WHITE, true, Loader.getFuente(70), this));
    }
    
    //método que permite disminuir las vidas del jugador
    public void resVida(int valor, int x, int y){
        jugador.setVidas(valor);
        message.add(new Message(x, y, true, "-" + valor + " Vidas", Color.RED, false, Loader.getFuente(15), this));
    }
    
    //método que permite pintar el puntaje
    public void pintarPuntaje(Graphics g, Container c){
        //agrego las posiciones donde voy se colocara el puntaje
        int x = 700;
        int y = 25;
        
        //como voy a cargar imagen en vez de texto es muy conveniente convertir los puntajes en string para saber que número voy a pintar
        String stringPuntaje = Integer.toString(puntuacion);
        
        //recorro el string creado
        for(int i = 0; i < stringPuntaje.length(); i++){
            g.drawImage(jugador.getImgJugador().getNumeros()[Integer.parseInt(stringPuntaje.substring(i,i+1))].getImage(), x, y, c);
            //modifico a x para que la imagen no se monte encima de la otra
            x = x + 20;
        }
        
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0; i < message.size(); i++){
            message.get(i).draw(g2d);
        }
    }
    
    //método que permite pintar las vidas
    public void pintarVidas(Graphics g, Container c){
        int x = 25;
        int y = 25;
        
        //TODO:dibujar el simbolo
        g.drawImage(jugador.getImgJugador().getOtros().getImage(), x + 10, y, c);
        
        //dibujar el valor  x
        g.drawImage(getJugador().getImgJugador().getNumeros()[10].getImage(), x + 40, y +5, c);
        
        String stringVidas = Integer.toString(jugador.getVidas() < 0 ? 0 : jugador.getVidas());
        //recorro el string creado
        for(int i = 0; i < stringVidas.length(); i++){
            int index = Integer.parseInt(stringVidas.substring(i,i+1));
            
            //valido la posibilidad de que no exista idas menores que 0
            if(index < 0){
               break;
            }
            
            g.drawImage(jugador.getImgJugador().getNumeros()[index].getImage(), x + 60, y + 5, c);
            //modifico a x para que la imagen no se monte encima de la otra
            x = x + 20;
        }
        
        //pinto el mensaje 
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0; i < message.size(); i++){
            message.get(i).draw(g2d);
        }
    }
    
    //método que permite pintar cuando el jugador se queda sin vida
    public void pintarTablaPuntuacion(Graphics g){
        message.add(new Message(Constantes.WIDTH/2, Constantes.HEIGHT/2, true, "GAME OVER", Color.BLACK, true, Loader.getFuente(42), this));
    }

    //getter y setter
    public int getNumeroOleadas() {
        return numeroOleadas;
    }

    public void setNumeroOleadas(int numeroOleadas) {
        this.numeroOleadas = numeroOleadas;
    }

    public ArrayList<Message> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<Message> message) {
        this.message = message;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getNumeroenemigos() {
        return numeroenemigos;
    }

    public void setNumeroenemigos(int numeroenemigos) {
        this.numeroenemigos = numeroenemigos;
    }
}
