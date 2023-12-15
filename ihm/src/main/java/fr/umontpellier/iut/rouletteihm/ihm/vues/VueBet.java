package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.ihm.IJeu;
import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;

public class VueBet extends GridPane {
    private IJeu jeu;
    private ImageView valider;

    @FXML
    private Label LabelInstruction;

    public Label getLabelInstruction() {
        return LabelInstruction;
    }

    public boolean isValidation() {
        return validation.get();
    }

    public BooleanProperty validationProperty() {
        return validation;
    }

    private BooleanProperty validation;
    private boolean ok;

    public void setOk(boolean bool) {
        ok = bool;
    }

    public VueBet(IJeu jeu) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/VueBet.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            HBox Jetons = (HBox) root.lookup("#Jetons");
            Jetons.setAlignment(Pos.CENTER);

            ImageView token1 = (ImageView) Jetons.lookup("#Token1");
            creerBidingsJeton(token1, 1);
            HoverImage(token1);

            ImageView token5 = (ImageView) Jetons.lookup("#Token5");
            creerBidingsJeton(token5, 5);
            HoverImage(token5);

            ImageView token25 = (ImageView) Jetons.lookup("#Token25");
            creerBidingsJeton(token25, 25);
            HoverImage(token25);

            ImageView token100 = (ImageView) Jetons.lookup("#Token100");
            creerBidingsJeton(token100, 100);
            HoverImage(token100);

            ImageView token500 = (ImageView) Jetons.lookup("#Token500");
            creerBidingsJeton(token500, 500);
            HoverImage(token500);

            ImageView token1000 = (ImageView) Jetons.lookup("#Token1000");
            creerBidingsJeton(token1000, 1000);
            HoverImage(token1000);
            Pane InstructionBox = (Pane) root.lookup("#InstructionBox");
            ImageView valider = (ImageView) InstructionBox.lookup("#valider");
            HoverImageValider(valider);
            creerBindingValider(valider);
            getChildren().addAll(root);

            validation = new SimpleBooleanProperty(false);


        } catch (IOException e) {
            e.printStackTrace();
        }
        this.jeu = jeu;
        setId("vueBet");
        LabelInstruction.setText("Quel somme voulez-vous miser ?");
        ok = false;
    }

    private void HoverImage(ImageView imageView) {
        DropShadow dropShadow = new DropShadow();
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), imageView);

        imageView.setOnMouseEntered(event -> {
            imageView.setEffect(dropShadow);
            scaleTransition.setFromX(1.0);
            scaleTransition.setToX(1.2);
            scaleTransition.setFromY(1.0);
            scaleTransition.setToY(1.2);
            scaleTransition.playFromStart();
        });

        imageView.setOnMouseExited(event -> {
            imageView.setEffect(null);
            scaleTransition.setFromX(1.2);
            scaleTransition.setToX(1.0);
            scaleTransition.setFromY(1.2);
            scaleTransition.setToY(1.0);
            scaleTransition.playFromStart();
        });
    }

    private void creerBidingsJeton(ImageView jeton, int valeurJeton) {
        EventHandler<javafx.scene.input.MouseEvent> miseJoueurChange = mouseEvent -> {
            if (jeu.joueurCourantProperty().get().soldeProperty().getValue() - jeu.joueurCourantProperty().get().getMiseTotale() < valeurJeton) {
                LabelInstruction.setText("Vous n'avez pas assez d'argent pour faire ce paris !");
            } else {
                jeu.joueurCourantProperty().get().setMiseActuelle(valeurJeton);
                LabelInstruction.setText("La valeur de la mise a été changée à : " + valeurJeton);
            }
        };

        jeton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, miseJoueurChange);

    }

    private void HoverImageValider(ImageView imageView) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.GOLD);
        dropShadow.setRadius(11);
        dropShadow.setSpread(0.6);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), imageView);
        FillTransition fillTransition = new FillTransition(Duration.millis(200));

        imageView.setOnMouseEntered(event -> {
            imageView.setEffect(dropShadow);
            scaleTransition.setFromX(1.0);
            scaleTransition.setToX(1.2);
            scaleTransition.setFromY(1.0);
            scaleTransition.setToY(1.2);
            scaleTransition.playFromStart();
            fillTransition.setFromValue(Color.TRANSPARENT);
            fillTransition.setToValue(Color.GOLD);
            fillTransition.playFromStart();
        });

        imageView.setOnMouseExited(event -> {
            imageView.setEffect(null);

            scaleTransition.setFromX(1.2);
            scaleTransition.setToX(1.0);
            scaleTransition.setFromY(1.2);
            scaleTransition.setToY(1.0);
            scaleTransition.playFromStart();

            fillTransition.setFromValue(Color.GOLD);
            fillTransition.setToValue(Color.TRANSPARENT);
            fillTransition.playFromStart();
        });
    }

    private void creerBindingValider(ImageView valider) {
        EventHandler<javafx.scene.input.MouseEvent> validationJoueur = mouseEvent -> {
            if (jeu.joueurCourantProperty().get().soldeProperty().getValue() < jeu.joueurCourantProperty().get().getMiseTotale()) {
                LabelInstruction.setText("Vous n'avez pas assez d'argent pour faire ce paris !");
            } else if (jeu.joueurCourantProperty().get().getMiseTotale() == 0 && ok) {
                LabelInstruction.setText("Vous n'avez pas encore parié !");
            } else {
                LabelInstruction.setText("Paris confirmé(s), roulette lancée ! ");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                validation.set(true);
            }
        };

        valider.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, validationJoueur);
    }

    public void creerBinding() {
        jeu.joueurCourantProperty().addListener((observableValue, iJoueur, t1) -> {
            validation.set(false);
            jeu.tournerTour();
        });
    }
}