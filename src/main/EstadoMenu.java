package main;

import grafico.Imagenes;
import grafico.Sonidos;
import java.awt.Container;
import java.awt.Graphics;
import java.util.ArrayList;
import objeto.AccionBoton;
import objeto.Botones;
import objeto.Constantes;

//clase que tendra toda la logica del menú pricipal
public class EstadoMenu {

    //agrego un arreglo para contener todo los botones
    private ArrayList<Botones> botones;
    private Imagenes image;//llamo a la clase que contiene las imagenes
    private boolean empezarjuego;//indica si se puede empezar el juego o no
    private boolean iniciarInstrucciones;//indica cuando se puede ejecutar la ventana de instrucciones
    private boolean iniciarHistoria;//indica cuanco se puede ejecutar la historia del juego

    //constructor 
    public EstadoMenu() {
        empezarjuego = false;
        iniciarInstrucciones = false;
        iniciarHistoria = false;
        image = new Imagenes();
        botones = new ArrayList<>();

        //agrego el boton de jugar
        botones.add(new Botones(
                Constantes.WIDTH / 2 - image.getButtonGray().getIconWidth() / 2,
                (Constantes.HEIGHT / 2 - image.getButtonGray().getIconHeight()) - 40,
                "Jugar",
                new AccionBoton() {
                @Override
                public void crearAccion() {
                    empezarjuego = true;
                }
        }
        ));

        //agrego el boton de Instrucciones
        botones.add(new Botones(
                Constantes.WIDTH / 2 - image.getButtonGray().getIconWidth() / 2,
                (Constantes.HEIGHT / 2 + image.getButtonGray().getIconHeight() / 2) - 30,
                "Instrucciones",
                new AccionBoton() {
                @Override
                public void crearAccion() {
                    iniciarInstrucciones = true;
                }
        }
        ));

        //agrego el boton de Historia
        botones.add(new Botones(
                Constantes.WIDTH / 2 - image.getButtonGray().getIconWidth() / 2,
                (Constantes.HEIGHT / 2 + image.getButtonGray().getIconHeight() / 2) + 40,
                "Historia",
                new AccionBoton() {
                    @Override
                    public void crearAccion() {
                        iniciarHistoria = true;
                    }
                }
        ));

        //agrego el boton de salir
        botones.add(new Botones(
                Constantes.WIDTH / 2 - image.getButtonGray().getIconWidth() / 2,
                (Constantes.HEIGHT / 2 + image.getButtonGray().getIconHeight() / 2) + 120,
                "Salir",
                new AccionBoton() {
            @Override
            public void crearAccion() {
                System.exit(0);//salgo del juego
            }
        }
        ));
    }

    //metodo que permite actualizar todos los botones que estan en el arreglo
    public void actualizarTodosBotones() {
        //creo un foreach
        for (Botones b : botones) {
            b.actualizar();
        }
    }

    //método que permite pintar todos los botones que estan en el arreglo 
    public void pintarTodosBotones(Graphics g, Container c) {
        //creo un foreach
        for (Botones b : botones) {
            b.pintarBotones(g, c);
        }
    }

    //getter y setter
    public boolean isEmpezarjuego() {
        return empezarjuego;
    }

    public void setEmpezarjuego(boolean empezarjuego) {
        this.empezarjuego = empezarjuego;
    }

    public boolean isIniciarInstrucciones() {
        return iniciarInstrucciones;
    }

    public void setIniciarInstrucciones(boolean iniciarInstrucciones) {
        this.iniciarInstrucciones = iniciarInstrucciones;
    }

    public boolean isIniciarHistoria() {
        return iniciarHistoria;
    }

    public void setIniciarHistoria(boolean iniciarHistoria) {
        this.iniciarHistoria = iniciarHistoria;
    }
    
}
