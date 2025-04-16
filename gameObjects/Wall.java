package gameObjects;

import abstracts.Drawable;
import java.awt.*;
import utils.Coordinates;
import utils.DrawingInformation;

public class Wall extends Drawable {


    public Wall(Coordinates coords) {
        super(coords);
        //TODO IMPLEMENT
    }


    @Override
    public void update() {
        //TODO IMPLEMENT
    }

    @Override
    public DrawingInformation draw() {
        //TODO IMPLEMENT
        return new DrawingInformation('w', new Color(51, 0, 102));
    }

}
