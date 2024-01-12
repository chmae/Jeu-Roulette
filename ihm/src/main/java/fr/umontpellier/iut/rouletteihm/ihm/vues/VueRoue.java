/* Objet graphique pour la roulette */
package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.plateau.Boule;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.GestionMusique;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.plateau.Nombres;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.plateau.setNombres;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static javafx.scene.control.PopupControl.USE_PREF_SIZE;

public class VueRoue {
    private Pane Roue = new Pane();
    private Boule boule = new Boule();
    private Group rotateBouleSegment = new Group();
    private static final double START_ANGLE = 85.135135135f;
    private setNombres numberSet = new setNombres();
    private int angleRotation = 0;
    private static final double angle = 9.72972973f;
    private static final int rotation = 1800;
    private ArrayList<Double> listeX = new ArrayList<>();
    private ArrayList<Double> listeY = new ArrayList<>();
    private Map<Integer, Coordonnees> coordonneesChiffres;
    private GestionMusique musique = new GestionMusique();


    public VueRoue() {
        Roue.setPrefSize(220, 220);
        Roue.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        Roue.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);

        drawDecorationBottom();
        drawSegments();

        coordonneesChiffres = new HashMap<>();

        for (int i = 0; i < 37; i++) {
            listeX.add(0.0);
            listeY.add(0.0);
        }

        drawNumbers();
        drawDecorationTop();

        Circle wire = new Circle(110, 110, 80);
        boule.getShape().setCenterX(110);
        boule.getShape().setCenterY(29);
        wire.setFill(Color.TRANSPARENT);
        wire.setStroke(Color.GOLD);
        rotateBouleSegment.getChildren().addAll(wire, boule.getShape());
        Roue.getChildren().add(rotateBouleSegment);

        //coordonnes 0 => 0,-160;
        //centre de rotation 0,-80

    }

    public Pane getRoue() {
        return Roue;
    }

    // dessine les segments de la roue en utilisant les angles
    private void drawSegments() {
        Group segments = new Group();
        double startAngle = START_ANGLE;
        for (int i = 0; i < 37; i++) {
            Arc arc = new Arc();
            arc.setStroke(Color.GOLD);
            if (i == 0) arc.setFill(Color.GREEN);
            else if (i % 2 == 0) arc.setFill(Color.RED);
            else arc.setFill(Color.BLACK);
            arc.setCenterX(110.0f);
            arc.setCenterY(110.0f);
            arc.setRadiusX(110.0f);
            arc.setRadiusY(110.0f);
            arc.setStartAngle(startAngle);
            arc.setLength(angle);
            arc.setType(ArcType.ROUND);
            segments.getChildren().add(arc);

            startAngle += angle;
        }
        Roue.getChildren().add(segments);
    }

    // dessine les nombres sur la roue
    private void drawNumbers() {
        Group text = new Group();
        double x = 0;
        double y = 0;
        for (Nombres n : numberSet.getNumberSet()) {
            Text textNode = new Text("" + n.getNumber());
            int centreX = 110;
            int centreY = 100;
            int radius = 90;
            double angleText = (180 + n.getAngle());
            textNode.setFill(Color.WHITE);
            textNode.setFont(Font.font("Tahoma", 12));
            if (n.getNumber() < 10) {
                x = centreX + radius * (Math.cos((n.getAngle() + 88) * Math.PI / 180f));
                y = centreY + radius * (Math.sin((n.getAngle() + 88) * Math.PI / 180f));
            } else if (n.getNumber() < 20) {
                x = centreX + radius * (Math.cos((n.getAngle() + 85.5) * Math.PI / 180f));
                y = centreY + radius * (Math.sin((n.getAngle() + 85.5) * Math.PI / 180f));
            } else {
                x = centreX + radius * (Math.cos((n.getAngle() + 85) * Math.PI / 180f));
                y = centreY + radius * (Math.sin((n.getAngle() + 85) * Math.PI / 180f));
            }
            textNode.relocate(x, y);
            textNode.getTransforms().add(new Rotate(angleText));
            text.getChildren().add(textNode);
            Coordonnees coordonnees = new Coordonnees(x, y);
            coordonneesChiffres.put(n.getNumber(), coordonnees);
            listeX.set(n.getNumber(), x);
            listeY.set(n.getNumber(), y);
        }
        Roue.getChildren().add(text);
    }

    // Dessine la décoration autour de la roulette.
    private void drawDecorationBottom() {
        Group decoration = new Group();

        Circle outerBorder = new Circle(110, 110, 93);
        outerBorder.setFill(Color.SADDLEBROWN);
        outerBorder.setStroke(Color.GOLD);
        outerBorder.setEffect(dropShadow());

        decoration.getChildren().add(outerBorder);

        Roue.getChildren().add(decoration);
    }

    // Dessine la décoration sur le dessus de la roulette.
    private void drawDecorationTop() {
        Group decoration = new Group();

        Circle wheelCentre = new Circle(110, 110, 60);
        wheelCentre.setStroke(Color.GOLD);
        wheelCentre.setFill(Color.SADDLEBROWN);
        wheelCentre.setEffect(dropShadow());
        decoration.getChildren().add(wheelCentre);

        Circle circle = new Circle(110, 110, 25);
        Rectangle rec1 = new Rectangle(105, 100, 5, 50);
        Rectangle rec2 = new Rectangle(100, 105, 50, 5);
        Stop[] stops = new Stop[]{new Stop(0, Color.WHITE), new Stop(1, Color.GOLD)};
        LinearGradient lg1 = new LinearGradient(0, 0, 50, 50, false, CycleMethod.REFLECT, stops);
        Shape rec = Shape.union(rec1, rec2);
        Shape centerJobby = Shape.union(rec, circle);
        centerJobby.setFill(lg1);
        centerJobby.setEffect(dropShadow());
        decoration.getChildren().add(centerJobby);
        Roue.getChildren().add(decoration);
    }

    private DropShadow dropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(40.0);
        return dropShadow;
    }

    public void animation(Nombres gagnant) {

        String sonsRoulette = "ihm/src/main/resources/musique/sonsRoulette.mp3";
        musique.setMusique(sonsRoulette);
        musique.setVolume(0.2);
        musique.lireMusique();

        Circle path = new Circle(110, -130, 80);

        double numberAngle = numberSet.getAngle(gagnant.getNumber());
        int finalAngleRotation = (int) (360 * 2.5 + numberAngle);

        RotateTransition rotateTransitionBoule = new RotateTransition(Duration.seconds(1.5), rotateBouleSegment);
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1.5), Roue);
        ParallelTransition parallelTransition = new ParallelTransition();

        rotateTransitionBoule.setFromAngle(angleRotation);
        rotateTransitionBoule.setToAngle(finalAngleRotation);
        rotateTransition.setFromAngle(angleRotation);
        rotateTransition.setToAngle(finalAngleRotation);
        parallelTransition.getChildren().addAll(rotateTransitionBoule, rotateTransition);
        parallelTransition.play();

        parallelTransition.setOnFinished(event -> {
            angleRotation = finalAngleRotation;
        });
    }

    public Map<Integer, Coordonnees> getCoordonneesChiffres() {
        return coordonneesChiffres;
    }

    //=====================================================================
// Classe interne pour représenter les nombres sur la roue
    public class Coordonnees {
        private double x;
        private double y;

        public Coordonnees(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }
}