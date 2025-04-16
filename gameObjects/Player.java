package gameObjects;

import abstracts.Controllable;
import java.awt.*;
import utils.Coordinates;
import utils.Direction;
import utils.DrawingInformation;

public class Player extends Controllable {

    private static final int INITIAL_LIFE = 3; // Costanti

    // controllo se c'è un muro
    public int currentRow = coordinates.getRow();
    public int currentCol = coordinates.getCol();

    // varibaili per tener conto dell'ultima posizione
    public int lastRow;
    public int lastCol;

    // variabile per tenere in memoria la posizione iniziale
    private final Coordinates startCoords;

    private Direction currentDirection; // direzione corrente

    private int life; // variabile per tener conto delle vite

    private boolean winner = false; // variabile da ritornare per sapere se la partita è vinta. False = no; True =
                                    // WIN!

    public char lastKey = 'p'; // char per settare l'ultimo tasto cliccato: P = stay; W = UP; S = DOWN; D =
                               // RIGT; S = LEFT

    private char previousKey = 'p'; // Memorizza il tasto precedente

    public Player(Coordinates coordinates) {
        super(coordinates);
        this.startCoords = new Coordinates(coordinates.getRow(), coordinates.getCol());
        this.currentDirection = Direction.STAY;
        this.life = INITIAL_LIFE;
    }

    @Override
    public void move(Direction direction) {
        this.currentDirection = direction;
    }

    @Override
    public void update() {
        currentRow = coordinates.getRow();
        currentCol = coordinates.getCol();
        lastRow = currentRow;
        lastCol = currentCol;

        // Salva il valore corrente di lastKey in previousKey
        previousKey = lastKey;

        // Prova a muoverti nella direzione corrente
        switch (currentDirection) {
            case UP -> {
                coordinates = new Coordinates(currentRow - 1, currentCol);
                lastKey = 'w';
            }
            case DOWN -> {
                coordinates = new Coordinates(currentRow + 1, currentCol);
                lastKey = 's';
            }
            case RIGHT -> {
                coordinates = new Coordinates(currentRow, currentCol + 1);
                lastKey = 'd';
            }
            case LEFT -> {
                coordinates = new Coordinates(currentRow, currentCol - 1);
                lastKey = 'a';
            }
            case STAY -> stayMovement();
            default -> {
            }
        }
    }

    public boolean isWinner() {
        return this.winner; // ritorniamo winner. Se false, si prosegue il gioco, se true la partita è vinta
    }

    public boolean isLooser() {
        return getLife() == 0; // se le vite sono = 0, la partita è persa
    }

    public void markAsWinner() {
        this.winner = true; // nel caso in cui si vinca, si setta winner a true
    }

    public void handleDamage() {
        // in caso si viene mangiati, si ritorna alle coordinate iniziali, si sta fermi
        // e si perde una vita
        coordinates = new Coordinates(startCoords.getRow(), startCoords.getCol());
        lastKey = 'p';
        life--;

        Ghost.resetAllGhosts(); //chiamo ghost per resettare la loro posizione
    }

    @Override
    public DrawingInformation draw() {
        // settiamo il colore per il player
        return new DrawingInformation('P', new Color(255, 242, 0));
    }

    public void resetCoordinates() {
        coordinates = new Coordinates(lastRow, lastCol); // resetto coordinate quando arrivo a un muro

        if (lastKey != previousKey) {
            lastKey = previousKey; // resetto l'ultimo tasto premuto
            stayMovement(); // porto direttamente a stayMovement cosi non ho problemi di lag
        } else {
            lastKey = 'p'; // se non è cambiato il tasto, lo setto a p
        }
    }

    public int getLife() {
        return life; // funzione per ritornare il numero delle vite
    }

    public void stayMovement() {
        // nel caso non venga premuto alcun tasto, si utilizza il vecchio tasto per
        // continuare il movimento
        switch (lastKey) {
            case 'w' -> coordinates = new Coordinates(currentRow - 1, currentCol);
            case 's' -> coordinates = new Coordinates(currentRow + 1, currentCol);
            case 'd' -> coordinates = new Coordinates(currentRow, currentCol + 1);
            case 'a' -> coordinates = new Coordinates(currentRow, currentCol - 1);
            case 'p' -> {
            }
            default -> {
            }
        }
    }
}
