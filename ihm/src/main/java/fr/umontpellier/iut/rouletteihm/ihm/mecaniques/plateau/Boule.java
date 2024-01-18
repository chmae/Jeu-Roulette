package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.plateau;

import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.*;


public class Boule {

    public Circle ball = new Circle(0, 0, 6);

    public Boule() {
        ball.setFill(Color.GOLD);
//        ball.setTranslateY(-190);
//        ball.setTranslateX(-310);
//        ball.getTransforms().add(new Translate(0, 240));
    }

    public Circle getShape() {
        return ball;
    }

    public void setVisibility(boolean b) {
        ball.setVisible(b);
    }
}
