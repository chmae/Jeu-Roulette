package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.ihm.IJeu;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.GestionMusique;
import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    public VueBet(IJeu jeu, IntegerProperty langueProperty) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/VueBet.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            HBox Jetons = (HBox) root.lookup("#Jetons");
            Jetons.setAlignment(Pos.CENTER);

            ImageView token1 = (ImageView) Jetons.lookup("#Token1");
            creerBindingsJeton(token1, 1, langueProperty);
            HoverImage(token1);

            ImageView token5 = (ImageView) Jetons.lookup("#Token5");
            creerBindingsJeton(token5, 5, langueProperty);
            HoverImage(token5);

            ImageView token25 = (ImageView) Jetons.lookup("#Token25");
            creerBindingsJeton(token25, 25, langueProperty);
            HoverImage(token25);

            ImageView token100 = (ImageView) Jetons.lookup("#Token100");
            creerBindingsJeton(token100, 100, langueProperty);
            HoverImage(token100);

            ImageView token500 = (ImageView) Jetons.lookup("#Token500");
            creerBindingsJeton(token500, 500, langueProperty);
            HoverImage(token500);

            ImageView token1000 = (ImageView) Jetons.lookup("#Token1000");
            creerBindingsJeton(token1000, 1000, langueProperty);
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
        if (langueProperty.getValue() == 0){
            LabelInstruction.setText("Combien voulez-vous miser ?");
        }
        else if (langueProperty.getValue() == 1){
            LabelInstruction.setText("How much do you want to bet ?");
        }
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

    private void creerBindingsJeton(ImageView jeton, int valeurJeton, IntegerProperty langueProperty) {
        EventHandler<MouseEvent> miseJoueurChange = mouseEvent -> {
            if (jeu.joueurCourantProperty().get().soldeProperty().getValue() - jeu.joueurCourantProperty().get().getMiseTotale() < valeurJeton) {
                if (langueProperty.getValue() == 0) {
                    LabelInstruction.setText("Vous n'avez pas assez d'argent pour miser cette somme !");
                }
                else if (langueProperty.getValue() == 1){
                    LabelInstruction.setText("You don't have enough money make this bet !");
                }
            } else {
                jeu.joueurCourantProperty().get().setMiseActuelle(valeurJeton);
                if (langueProperty.getValue() == 0) {
                    LabelInstruction.setText("Vous avez misé " + valeurJeton);
                }
                else if (langueProperty.getValue() == 1){
                    LabelInstruction.setText("You bet " + valeurJeton);
                }
            }
        };

        jeton.addEventHandler(MouseEvent.MOUSE_CLICKED, miseJoueurChange);

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

            // sons bouton valider //
            GestionMusique sonsBoutonValider= new GestionMusique();
            String cheminAudioBouton = "ihm/src/main/resources/musique/sonsValider.mp3";
            sonsBoutonValider.setMusique(cheminAudioBouton);
            sonsBoutonValider.setVolume(0.2);
            sonsBoutonValider.lireMusique();
            sonsBoutonValider.remettreMusiqueAuDebut();

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

        valider.addEventHandler(MouseEvent.MOUSE_CLICKED, validationJoueur);
    }

    public void creerBinding(IntegerProperty langueProperty) {
        jeu.joueurCourantProperty().addListener((observableValue, iJoueur, t1) -> {
            validation.set(false);
            jeu.tournerTour();
        });

        langueProperty.addListener(observable -> {
            if (langueProperty.getValue()==1) {
                getLabelInstruction().setText("The language has been changed !");
            } else {
                getLabelInstruction().setText("La langue a été changée !");
            }
        });
    }
}