package gameObjects;

import abstracts.Drawable;
import java.awt.*;
import utils.Coordinates;
import utils.DrawingInformation;

public class Food extends Drawable {

    private boolean isEaten = false;

    public Food(Coordinates coords) {
        super(coords);
        //TODO: IMPLEMENT
    }

    @Override
    public void update() {}

    @Override
    public DrawingInformation draw() {
        //controllo se il food è stato "mangiato", in caso di false, mostro il food
        if (!isEaten) {
            return new DrawingInformation('f', new Color	(0, 255, 204));            
        }else{
            return new DrawingInformation(' ', Color.BLACK);
        }
    }

    public void markAsDeleted() {
        //dichiaro che il food è stato mangiato
        isEaten = true;
    }

    public boolean isDeleted() {
        //controllo se il food è stato mangiato o meno
        return isEaten;
    }

}
