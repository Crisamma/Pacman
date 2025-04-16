package gameObjects;

import abstracts.Drawable;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import utils.Coordinates;
import utils.Direction;
import utils.DrawingInformation;

public class Ghost extends Drawable {

    private ArrayList<Direction> availDirections; // salvare posizioni dei fantasmi

    Random random = new Random(); // random per il movimento dei fantasmi
    private Direction lastDir; // salvo l'ultima direzione del ghost

    public Ghost(Coordinates coordinates) {
        super(coordinates);
        // TODO: IMPLEMENT
    }

    public void setAvailDirections(ArrayList<Direction> availDirections) {
        this.availDirections = availDirections;
    }

    @Override
    public void update() {
        if (availDirections != null && !availDirections.isEmpty()) {
            // Scegli una direzione casuale DALLA LISTA delle direzioni disponibili
            Direction randomDirection = availDirections.get(random.nextInt(availDirections.size()));

            // se ha piu strade, il randomDirection non deve essere == al suo opposto, cosi
            // non torna indietro
            if (randomDirection != Direction.getOpposite(lastDir)) {

                // passo alla funzione move il random direction, che NON sarà uguale al suo
                // opposto. La sua ultima posizione sarà quindi randomDirection.
                move(randomDirection);
                this.lastDir = randomDirection;
            } else if (availDirections.size() == 1) {

                /*
                 * passo alla funzione move il random direction, che sarà uguale al suo opposto
                 * (cosi puo tornare indietro).
                 * La sua ultima posizione sarà quindi randomDirection.
                 */
                move(randomDirection);
                this.lastDir = randomDirection;
            }

        }
    }

    private void move(Direction randomDirection) {
        switch (randomDirection) {
            // Muovi il fantasma usando la direzione scelta
            case UP ->
                coordinates = new Coordinates(coordinates.getRow() - 1, coordinates.getCol());
            case DOWN ->
                coordinates = new Coordinates(coordinates.getRow() + 1, coordinates.getCol());
            case RIGHT ->
                coordinates = new Coordinates(coordinates.getRow(), coordinates.getCol() + 1);
            case LEFT ->
                coordinates = new Coordinates(coordinates.getRow(), coordinates.getCol() - 1);
            default -> {
            }
        }
    }

    @Override
    public DrawingInformation draw() {
        return new DrawingInformation('G', new Color(255, 60, 0));
    }

}
