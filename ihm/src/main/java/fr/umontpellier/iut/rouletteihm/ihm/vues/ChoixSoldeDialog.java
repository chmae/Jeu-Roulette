package fr.umontpellier.iut.rouletteihm.ihm.vues;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Boîte de dialogue pour choisir le solde initial
 */
public class ChoixSoldeDialog extends Dialog<Integer> {

    /**
     * Constructeur
     */
    public ChoixSoldeDialog() {
        setTitle("Choix du Solde");
        setHeaderText("Veuillez choisir votre solde initial.");

        TextField soldeField = new TextField();
        soldeField.setPromptText("Entrez votre solde");

        // Mise en forme de la boîte de dialogue
        GridPane grid = new GridPane();
        grid.add(new Label("Solde :"), 0, 0);
        grid.add(soldeField, 1, 0);

        getDialogPane().setContent(grid);

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Récupération du solde entré par l'utilisateur
        setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                try {
                    int solde = Integer.parseInt(soldeField.getText());
                    return solde;
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        });
    }
}
