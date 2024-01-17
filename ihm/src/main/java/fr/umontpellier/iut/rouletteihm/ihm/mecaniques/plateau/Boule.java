package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.plateau;

import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.*;


// Classe représentant la boule de la roulette
public class Boule {

    // Attribut représentant la boule
    public Circle ball = new Circle(0, 0, 6);

    // Constructeur de la boule
    public Boule() {
        ball.setFill(Color.GOLD);
//        ball.setTranslateY(-190);
//        ball.setTranslateX(-310);
//        ball.getTransforms().add(new Translate(0, 240));
    }

    // Méthode pour obtenir la forme (Circle) de la boule
    public Circle getShape() {
        return ball;
    }

    // Méthode pour définir la visibilité de la boule
    public void setVisibility(boolean b) {
        ball.setVisible(b);
    }
}