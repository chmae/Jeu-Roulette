package fr.umontpellier.iut.rouletteihm.ihm.vues;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class VueRules extends Pane {

    public VueRules() {
        try {
            String lienPDF = "ihm/src/main/resources/documents/Casino-Rules.pdf";
            getChildren().add(createCenteredNode(createPDFLink(lienPDF)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Node createCenteredNode(Node content) {
        VBox centeredBox = new VBox(content);
        centeredBox.setAlignment(Pos.CENTER);
        return centeredBox;
    }

    private Node createPDFLink(String lienPDF) {
        Hyperlink pdfLink = new Hyperlink("Règles du jeu !");

        DropShadow dropShadow = new DropShadow();
        pdfLink.setEffect(dropShadow);

        pdfLink.setOnAction(event -> {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    if (desktop.isSupported(Desktop.Action.OPEN)) {
                        URI pdfURI = new File(lienPDF).toURI();
                        desktop.open(new File(pdfURI));
                    } else {
                        System.err.println("L'ouverture de fichiers n'est pas prise en charge sur cette plateforme.");
                    }
                } else {
                    System.err.println("Le bureau n'est pas pris en charge sur cette plateforme.");
                }
            } catch (IOException e) {
                System.err.println("Erreur lors de l'ouverture du fichier PDF : " + e.getMessage());
            }
        });
        pdfLink.setStyle("-fx-font-size: 20; -fx-text-fill: white; -fx-font-weight: bold;  -fx-border-width: 0; -fx-border-radius: 0;");

        pdfLink.setTranslateX(250);
        pdfLink.setTranslateY(150);

        return pdfLink;
    }
}
