package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.plateau;

import fr.umontpellier.iut.rouletteihm.ihm.IJeu;
import fr.umontpellier.iut.rouletteihm.ihm.vues.DonneesGraphiques;
import fr.umontpellier.iut.rouletteihm.ihm.vues.VueBet;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.*;


public class CreationTable {

    private IJeu jeu;
    private Group table = new Group();
    private setNombres setNombres = new setNombres();
    private int hoverIndex = -1;

    private static final double LARGEUR = 40;
    private static final double HAUTEUR = 65;
    private static final double START_X = 400;
    private static final double START_Y = 562.5;

    private static final ArrayList<String> bottomLine = new ArrayList<String>(Arrays.asList("1-18", "IMPAIR", "ROUGE", "NOIR", "PAIR", "19-36"));
    private static final ArrayList<String> topLine = new ArrayList<String>(Arrays.asList("1-12", "13-24", "25-36"));
    private List<Pair<Rectangle, Paint>> originalColors = new ArrayList<>();
    private Label labelInstructions;
    private IntegerProperty langueChoisie;
    private ArrayList<Integer> listeParis;
    private ArrayList<Integer> multiplicateursParis;
    private ArrayList<Integer> montantParis;
    private VueBet vueBet;
    private Map<Integer, Rectangle> rectangleMap = new HashMap<>();
    private List<Rectangle> espacesEntreCases = new ArrayList<>();


    public CreationTable(IJeu jeu, Label labelInstructions, VueBet vueBet, IntegerProperty langueChoisie) {
        this.vueBet = vueBet;
        dessinerGrille();
        this.jeu = jeu;
        this.labelInstructions = labelInstructions;
        this.langueChoisie = langueChoisie;
        listeParis = new ArrayList<>();
        multiplicateursParis = new ArrayList<>();
        for (int i = 0; i < 37; i++) {
            multiplicateursParis.add(0);
        }
        montantParis = new ArrayList<>();
        for (int i = 0; i < 37; i++) {
            montantParis.add(0);
        }

        //TODO : supprimer la fonction suite à la fin de la création des DonneesGraphiques
//        table.setOnMouseMoved(event -> {
//            double x = event.getX();
//            double y = event.getY();
//            System.out.println("Coordonnées du curseur : (" + x + ", " + y + ")");
//        });
    }

    //TODO : supprimer la fonction suite à la fin de la création des DonneesGraphiques
//    private void dessinerLigne(double startX, double startY, double endX, double endY) {
//        Line ligne = new Line(startX, startY, endX, endY);
//        ligne.setStrokeWidth(2);
//        ligne.setStroke(Color.WHITE);
//        ligne.setFill(Color.WHITE);
//        table.getChildren().add(ligne);
//    }


    public Group getTable() {
        return table;
    }

    public ArrayList<Integer> getListeParis(int numeroJoueur) {
        System.out.println(listeParis.toString());
        return listeParis;
    }

    public ObservableList<Integer> getListeParisObserver() {
        return FXCollections.observableList(listeParis);
    }


    public void viderListeParis() {
        listeParis.clear();
    }

    public ArrayList<Integer> getMultiplicateursParis() {
        return multiplicateursParis;
    }

    public void viderMultiplicateursParis() {
        for (int i = 0; i < 37; i++) {
            multiplicateursParis.set(i, 0);
        }
    }

    public ArrayList<Integer> getMontantParis() {
        return montantParis;
    }

    public void viderMontantsParis() {
        for (int i = 0; i < 37; i++) {
            montantParis.set(i, 0);
        }
    }

    private void dessinerGrille() {
        double x = START_X;
        double y = START_Y;
        int cnt = 1;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 3; j++) {
                creerNumeros(cnt, x, y);
                cnt++;
                y -= HAUTEUR;
            }
            y = START_Y;
            x += LARGEUR;
        }
        creerTopLine();
        creerTriangleZero();
        creerBottomLine();
        creerDeuxPourUn();
    }

    private void creerDeuxPourUn() {
        double x = START_X + (LARGEUR * 12);
        double y = START_Y;

        ArrayList<Integer> premiereColonne = new ArrayList<Integer>(Arrays.asList(1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34));
        ArrayList<Integer> deuxiemeColonne = new ArrayList<Integer>(Arrays.asList(2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35));
        ArrayList<Integer> troisiemeColonne = new ArrayList<Integer>(Arrays.asList(3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36));
        for (int i = 0; i < 3; i++) {
            Rectangle r = new Rectangle(x, y, LARGEUR, HAUTEUR);
            r.setStroke(Color.WHITE);
            r.setStrokeWidth(3);
            r.setFill(Color.color(0.12156862745098039, 0.12156862745098039, 0.11764705882352941, 0.4));
            r.setArcHeight(10);
            r.setArcWidth(10);
            table.getChildren().add(r);

            Text textNode = new Text("2 pour 1-" + (i + 1));
            textNode.yProperty().bind(r.yProperty().add(37));
            textNode.xProperty().bind(r.xProperty().subtract(5));
            textNode.scaleXProperty().bind(r.widthProperty().divide(45));
            textNode.scaleYProperty().bind(r.heightProperty().divide(35));
            textNode.setRotate(-90);
            textNode.setFill(Color.WHITE);
            table.getChildren().add(textNode);

            creerBindingParis(r, textNode);

            y -= HAUTEUR;

            ArrayList<Integer> numeros = null;
            if (i == 0) {
                numeros = premiereColonne;
            } else if (i == 1) {
                numeros = deuxiemeColonne;
            } else if (i == 2) {
                numeros = troisiemeColonne;
            }

            final ArrayList<Integer> numerosPourUn = numeros;
            List<Pair<Rectangle, Paint>> originalColors = new ArrayList<>();

            textNode.setOnMouseEntered(event -> {

                for (Node node : table.getChildren()) {
                    if (node instanceof Rectangle && node != r) {
                        Rectangle rectangle = (Rectangle) node;
                        originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                        Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                        if (nextNode instanceof Text) {
                            Text textNodeNum = (Text) nextNode;
                            try {
                                int numero = Integer.parseInt(textNodeNum.getText());
                                if (numerosPourUn.contains(numero)) {
                                    rectangle.setFill(Color.GREY);
                                    rectangle.setOpacity(0.7);
                                }
                            } catch (NumberFormatException e) {
                            }
                        }
                    }
                }
            });


            textNode.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> pair : originalColors) {
                    pair.getKey().setFill(pair.getValue());
                    pair.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });

            r.setOnMouseEntered(event -> {
                for (Node node : table.getChildren()) {
                    if (node instanceof Rectangle && node != r) {
                        Rectangle rectangle = (Rectangle) node;
                        originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                        Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                        if (nextNode instanceof Text) {
                            Text textNodeNum = (Text) nextNode;
                            try {
                                int numero = Integer.parseInt(textNodeNum.getText());
                                if (numerosPourUn.contains(numero)) {
                                    rectangle.setFill(Color.GREY);
                                    rectangle.setOpacity(0.7);
                                }
                            } catch (NumberFormatException e) {
                            }
                        }
                    }
                }
            });

            r.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> pair : originalColors) {
                    pair.getKey().setFill(pair.getValue());
                    pair.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });

        }
    }


    private void creerBottomLine() {
        double x = START_X;
        double y = START_Y + HAUTEUR;

// 1 à 18
        Rectangle oneToEitheen = new Rectangle(x, y, LARGEUR * 2, 40);
        oneToEitheen.setFill(Color.color(0.12156862745098039, 0.12156862745098039, 0.11764705882352941, 0.4));
        oneToEitheen.setArcHeight(10);
        oneToEitheen.setArcWidth(10);

        Line ligneHaut1 = new Line(x, y, x + (LARGEUR * 2), y);
        ligneHaut1.setStroke(Color.WHITE);
        ligneHaut1.setStrokeWidth(3);

        Line ligneBas1 = new Line(x, y + 40, x + (LARGEUR * 2), y + 40);
        ligneBas1.setStroke(Color.WHITE);
        ligneBas1.setStrokeWidth(3);

        Line ligneGauche1 = new Line(x, y, x, y + 40);
        ligneGauche1.setStroke(Color.WHITE);
        ligneGauche1.setStrokeWidth(3);

        Text textNode1 = new Text("1 à 18");
        textNode1.setFill(Color.WHITE);
        textNode1.xProperty().bind(oneToEitheen.xProperty().add(25));
        textNode1.yProperty().bind(oneToEitheen.yProperty().add(26));
        textNode1.scaleXProperty().bind(oneToEitheen.widthProperty().divide(60));
        textNode1.scaleYProperty().bind(oneToEitheen.heightProperty().divide(45));

        table.getChildren().addAll(oneToEitheen, ligneHaut1, ligneBas1, ligneGauche1, textNode1);
        creerBindingParis(oneToEitheen, textNode1);


        oneToEitheen.setOnMouseEntered(event -> {
            List<Pair<Rectangle, Paint>> originalColors = new ArrayList<>();

            for (Node node : table.getChildren()) {
                if (node instanceof Rectangle && node != oneToEitheen) {
                    Rectangle rectangle = (Rectangle) node;
                    originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                    Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                    if (nextNode instanceof Text) {
                        Text textNode = (Text) nextNode;
                        try {
                            int numero = Integer.parseInt(textNode.getText());
                            if (numero >= 1 && numero <= 18) {
                                rectangle.setFill(Color.GREY);
                                rectangle.setOpacity(0.7);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }

            oneToEitheen.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> pair : originalColors) {
                    pair.getKey().setFill(pair.getValue());
                    pair.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });
        });
        textNode1.setOnMouseEntered(event -> {


            for (Node node : table.getChildren()) {
                if (node instanceof Rectangle && node != textNode1) {
                    Rectangle rectangle = (Rectangle) node;
                    originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                    Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                    if (nextNode instanceof Text) {
                        Text textNode = (Text) nextNode;
                        try {
                            int numero = Integer.parseInt(textNode.getText());
                            if (numero >= 1 && numero <= 18) {
                                rectangle.setFill(Color.GREY);
                                rectangle.setOpacity(0.7);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }

            textNode1.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> pair : originalColors) {
                    pair.getKey().setFill(pair.getValue());
                    pair.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });
        });

        // impair
        x += LARGEUR * 2;
        Rectangle impair = new Rectangle(x, y, LARGEUR * 2, 40);
        impair.setFill(Color.color(0.12156862745098039, 0.12156862745098039, 0.11764705882352941, 0.4));
        impair.setArcHeight(10);
        impair.setArcWidth(10);

        Line ligneHaut2 = new Line(x, y, x + (LARGEUR * 2), y);
        ligneHaut2.setStroke(Color.WHITE);
        ligneHaut2.setStrokeWidth(3);

        Line ligneBas2 = new Line(x, y + 40, x + (LARGEUR * 2), y + 40);
        ligneBas2.setStroke(Color.WHITE);
        ligneBas2.setStrokeWidth(3);

        Line ligneGauche2 = new Line(x, y, x, y + 40);
        ligneGauche2.setStroke(Color.WHITE);
        ligneGauche2.setStrokeWidth(3);

        Text textNode2 = new Text("IMPAIR");
        textNode2.setFill(Color.WHITE);
        textNode2.xProperty().bind(impair.xProperty().add(19));
        textNode2.yProperty().bind(impair.yProperty().add(26));
        textNode2.scaleXProperty().bind(impair.widthProperty().divide(60));
        textNode2.scaleYProperty().bind(impair.heightProperty().divide(45));

        table.getChildren().addAll(impair, ligneHaut2, ligneBas2, ligneGauche2, textNode2);

        creerBindingParis(impair, textNode2);

        impair.setOnMouseEntered(event -> {
            List<Pair<Rectangle, Paint>> originalColors = new ArrayList<>();

            for (Node node : table.getChildren()) {
                if (node instanceof Rectangle && node != impair) {
                    Rectangle rectangle = (Rectangle) node;
                    originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                    Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                    if (nextNode instanceof Text) {
                        Text textNode = (Text) nextNode;
                        try {
                            int numero = Integer.parseInt(textNode.getText());
                            if (numero % 2 != 0) {
                                rectangle.setFill(Color.GREY);
                                rectangle.setOpacity(0.7);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }

            impair.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> pair : originalColors) {
                    pair.getKey().setFill(pair.getValue());
                    pair.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });
        });
        textNode2.setOnMouseEntered(event -> {
            List<Pair<Rectangle, Paint>> originalColors = new ArrayList<>();

            for (Node node : table.getChildren()) {
                if (node instanceof Rectangle && node != textNode2) {
                    Rectangle rectangle = (Rectangle) node;
                    originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                    Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                    if (nextNode instanceof Text) {
                        Text textNode = (Text) nextNode;
                        try {
                            int numero = Integer.parseInt(textNode.getText());
                            if (numero % 2 != 0) {
                                rectangle.setFill(Color.GREY);
                                rectangle.setOpacity(0.7);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }

            textNode2.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> pair : originalColors) {
                    pair.getKey().setFill(pair.getValue());
                    pair.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });
        });


        // Rouge
        x += LARGEUR * 2;
        Rectangle rouge = new Rectangle(x, y, LARGEUR * 2, 40);
        Rectangle losangeRouge = new Rectangle(x + 10, y + 10, 10, 10);
        losangeRouge.getTransforms().add(new Rotate(45, x + 10, y + 15));
        losangeRouge.setFill(Color.RED);
        losangeRouge.xProperty().bind(rouge.xProperty().add(9.7));
        losangeRouge.yProperty().bind(rouge.yProperty().add(1));
        losangeRouge.scaleXProperty().bind(rouge.widthProperty().divide(16));
        losangeRouge.scaleYProperty().bind(rouge.heightProperty().divide(16));
        rouge.setFill(Color.color(0.12156862745098039, 0.12156862745098039, 0.11764705882352941, 0.4));
        rouge.setArcHeight(10);
        rouge.setArcWidth(10);

        Line ligneHaut3 = new Line(x, y, x + (LARGEUR * 2), y);
        ligneHaut3.setStroke(Color.WHITE);
        ligneHaut3.setStrokeWidth(3);

        Line ligneBas3 = new Line(x, y + 40, x + (LARGEUR * 2), y + 40);
        ligneBas3.setStroke(Color.WHITE);
        ligneBas3.setStrokeWidth(3);

        Line ligneGauche3 = new Line(x, y, x, y + 40);
        ligneGauche3.setStroke(Color.WHITE);
        ligneGauche3.setStrokeWidth(3);

        table.getChildren().addAll(rouge, ligneHaut3, ligneBas3, ligneGauche3);
        table.getChildren().add(losangeRouge);

        creerBindingParis(rouge, new Text("rouge"));
        creerBindingParis(losangeRouge, new Text("rouge"));

        losangeRouge.setOnMouseEntered(event -> {
            List<Pair<Rectangle, Paint>> originalColors = new ArrayList<>();
            for (Node node : table.getChildren()) {
                if (node instanceof Rectangle && node != losangeRouge) {
                    Rectangle rectangle = (Rectangle) node;
                    originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                    Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                    if (nextNode instanceof Text) {
                        Text textNode = (Text) nextNode;
                        try {
                            if (rectangle.getFill() == Color.RED) {
                                rectangle.setFill(Color.GREY);
                                rectangle.setOpacity(0.7);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }


            losangeRouge.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> pair : originalColors) {
                    pair.getKey().setFill(pair.getValue());
                    pair.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });
        });


        // Noir
        x += LARGEUR * 2;
        Rectangle losangeNoir = new Rectangle(x + 10, y + 10, 10, 10);
        losangeNoir.getTransforms().add(new Rotate(45, x + 10, y + 15));

        Rectangle noir = new Rectangle(x, y, LARGEUR * 2, 40);
        noir.setFill(Color.color(0.12156862745098039, 0.12156862745098039, 0.11764705882352941, 0.4));
        noir.setArcHeight(10);
        noir.setArcWidth(10);
        losangeNoir.xProperty().bind(noir.xProperty().add(9.7));
        losangeNoir.yProperty().bind(noir.yProperty().add(1));
        losangeNoir.scaleXProperty().bind(noir.widthProperty().divide(16));
        losangeNoir.scaleYProperty().bind(noir.heightProperty().divide(16));

        Line ligneHaut4 = new Line(x, y, x + (LARGEUR * 2), y);
        ligneHaut4.setStroke(Color.WHITE);
        ligneHaut4.setStrokeWidth(3);

        Line ligneBas4 = new Line(x, y + 40, x + (LARGEUR * 2), y + 40);
        ligneBas4.setStroke(Color.WHITE);
        ligneBas4.setStrokeWidth(3);

        Line ligneGauche4 = new Line(x, y, x, y + 40);
        ligneGauche4.setStroke(Color.WHITE);
        ligneGauche4.setStrokeWidth(3);

        table.getChildren().addAll(noir, ligneHaut4, ligneBas4, ligneGauche4);
        table.getChildren().add(losangeNoir);

        creerBindingParis(noir, new Text("noir"));
        creerBindingParis(losangeNoir, new Text("noir"));

        losangeNoir.setOnMouseEntered(event -> {
            List<Pair<Rectangle, Paint>> originalColors = new ArrayList<>();
            for (Node node : table.getChildren()) {
                if (node instanceof Rectangle && node != losangeNoir) {
                    Rectangle rectangle = (Rectangle) node;
                    originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                    Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                    if (nextNode instanceof Text) {
                        Text textNode = (Text) nextNode;
                        try {
                            if (rectangle.getFill() == Color.BLACK) {
                                rectangle.setFill(Color.GREY);
                                rectangle.setOpacity(0.7);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }


            losangeNoir.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> pair : originalColors) {
                    pair.getKey().setFill(pair.getValue());
                    pair.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });
        });


        // Pair
        x += LARGEUR * 2;
        Rectangle pair = new Rectangle(x, y, LARGEUR * 2, 40);
        pair.setFill(Color.color(0.12156862745098039, 0.12156862745098039, 0.11764705882352941, 0.4));
        pair.setArcHeight(10);
        pair.setArcWidth(10);

        Line ligneHaut5 = new Line(x, y, x + (LARGEUR * 2), y);
        ligneHaut5.setStroke(Color.WHITE);
        ligneHaut5.setStrokeWidth(3);

        Line ligneBas5 = new Line(x, y + 40, x + (LARGEUR * 2), y + 40);
        ligneBas5.setStroke(Color.WHITE);
        ligneBas5.setStrokeWidth(3);

        Line ligneGauche5 = new Line(x, y, x, y + 40);
        ligneGauche5.setStroke(Color.WHITE);
        ligneGauche5.setStrokeWidth(3);

        Text textNode3 = new Text("PAIR");
        textNode3.setFill(Color.WHITE);
        textNode3.xProperty().bind(pair.xProperty().add(29));
        textNode3.yProperty().bind(pair.yProperty().add(26));
        textNode3.scaleXProperty().bind(pair.widthProperty().divide(60));
        textNode3.scaleYProperty().bind(pair.heightProperty().divide(45));

        table.getChildren().addAll(pair, ligneHaut5, ligneBas5, ligneGauche5, textNode3);

        creerBindingParis(pair, textNode3);

        pair.setOnMouseEntered(event -> {
            List<Pair<Rectangle, Paint>> originalColors = new ArrayList<>();

            for (Node node : table.getChildren()) {
                if (node instanceof Rectangle && node != pair) {
                    Rectangle rectangle = (Rectangle) node;
                    originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                    Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                    if (nextNode instanceof Text) {
                        Text textNode = (Text) nextNode;
                        try {
                            int numero = Integer.parseInt(textNode.getText());
                            if (numero % 2 == 0) {
                                rectangle.setFill(Color.GREY);
                                rectangle.setOpacity(0.7);
                            }
                        } catch (NumberFormatException e) {

                        }
                    }
                }
            }

            pair.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> paiir : originalColors) {
                    paiir.getKey().setFill(paiir.getValue());
                    paiir.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });
        });

        textNode3.setOnMouseEntered(event -> {
            List<Pair<Rectangle, Paint>> originalColors = new ArrayList<>();

            for (Node node : table.getChildren()) {
                if (node instanceof Rectangle && node != textNode3) {
                    Rectangle rectangle = (Rectangle) node;
                    originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                    Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                    if (nextNode instanceof Text) {
                        Text textNode = (Text) nextNode;
                        try {
                            int numero = Integer.parseInt(textNode.getText());
                            if (numero % 2 == 0) {
                                rectangle.setFill(Color.GREY);
                                rectangle.setOpacity(0.7);
                            }
                        } catch (NumberFormatException e) {

                        }
                    }
                }
            }

            textNode3.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> paiir : originalColors) {
                    paiir.getKey().setFill(paiir.getValue());
                    paiir.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });
        });


        // 19 à 36
        x += LARGEUR * 2;
        Rectangle nineteenToThirtySix = new Rectangle(x, y, LARGEUR * 2, 40);
        nineteenToThirtySix.setFill(Color.color(0.12156862745098039, 0.12156862745098039, 0.11764705882352941, 0.4));
        nineteenToThirtySix.setArcHeight(10);
        nineteenToThirtySix.setArcWidth(10);

        Line ligneHaut6 = new Line(x, y, x + (LARGEUR * 2), y);
        ligneHaut6.setStroke(Color.WHITE);
        ligneHaut6.setStrokeWidth(3);

        Line ligneBas6 = new Line(x, y + 40, x + (LARGEUR * 2), y + 40);
        ligneBas6.setStroke(Color.WHITE);
        ligneBas6.setStrokeWidth(3);

        Line ligneGauche6 = new Line(x, y, x, y + 40);
        ligneGauche6.setStroke(Color.WHITE);
        ligneGauche6.setStrokeWidth(3);

        Line ligneDroite6 = new Line(x + (LARGEUR * 2), y, x + (LARGEUR * 2), y + 40);
        ligneDroite6.setStroke(Color.WHITE);
        ligneDroite6.setStrokeWidth(3);

        Text textNode4 = new Text("19 à 36");
        textNode4.setFill(Color.WHITE);
        textNode4.xProperty().bind(nineteenToThirtySix.xProperty().add(20));
        textNode4.yProperty().bind(nineteenToThirtySix.yProperty().add(26));
        textNode4.scaleXProperty().bind(nineteenToThirtySix.widthProperty().divide(60));
        textNode4.scaleYProperty().bind(nineteenToThirtySix.heightProperty().divide(45));

        table.getChildren().addAll(nineteenToThirtySix, ligneHaut6, ligneBas6, ligneGauche6, ligneDroite6, textNode4);


        creerBindingParis(nineteenToThirtySix, textNode4);

        x = 20;
        y += 10;
        nineteenToThirtySix.setOnMouseEntered(event -> {
            List<Pair<Rectangle, Paint>> originalColors = new ArrayList<>();

            for (Node node : table.getChildren()) {
                if (node instanceof Rectangle && node != nineteenToThirtySix) {
                    Rectangle rectangle = (Rectangle) node;
                    originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                    Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                    if (nextNode instanceof Text) {
                        Text textNode = (Text) nextNode;
                        try {
                            int numero = Integer.parseInt(textNode.getText());
                            if (numero >= 19 && numero <= 36) {
                                rectangle.setFill(Color.GREY);
                                rectangle.setOpacity(0.7);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }

            nineteenToThirtySix.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> paiir : originalColors) {
                    paiir.getKey().setFill(paiir.getValue());
                    paiir.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });
        });

        textNode4.setOnMouseEntered(event -> {
            List<Pair<Rectangle, Paint>> originalColors = new ArrayList<>();

            for (Node node : table.getChildren()) {
                if (node instanceof Rectangle && node != textNode4) {
                    Rectangle rectangle = (Rectangle) node;
                    originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                    Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                    if (nextNode instanceof Text) {
                        Text textNode = (Text) nextNode;
                        try {
                            int numero = Integer.parseInt(textNode.getText());
                            if (numero >= 19 && numero <= 36) {
                                rectangle.setFill(Color.GREY);
                                rectangle.setOpacity(0.7);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }

            textNode4.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> paiir : originalColors) {
                    paiir.getKey().setFill(paiir.getValue());
                    paiir.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });
        });
    }

    private void creerTriangleZero() {
        //créer un triangle isocèle de base 200 et de hauteur 100
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(new Double[]{
                0.0, 0.0,
                195.0, 0.0,
                100.0, 100.0});
        triangle.rotateProperty().set(90);
        triangle.setFill(Color.GREEN);
        triangle.setStroke(Color.WHITE);
        triangle.setStrokeWidth(3);
        triangle.setTranslateX(START_X - 153);
        triangle.setTranslateY(START_Y - 82);
        table.getChildren().add(triangle);
        Text textNode = new Text("0");
        textNode.xProperty().bind(triangle.translateXProperty().add(105));
        textNode.yProperty().bind(triangle.translateYProperty().add(55));
        textNode.scaleXProperty().bind(triangle.scaleXProperty().multiply(2.5));
        textNode.scaleYProperty().bind(triangle.scaleYProperty().multiply(2.5));
        textNode.setFill(Color.WHITE);
        table.getChildren().add(textNode);
        ajouterEffetHover(triangle);

        creerBindingParis(triangle, textNode);
    }

    private void creerNumeros(int i, double x, double y) {
        Rectangle r = new Rectangle(x, y, LARGEUR, HAUTEUR);

        for (Nombres n : setNombres.getNumberSet()) {
            if (n.getNumber() == i) {
                r.setFill(n.getColor().equals("R") ? Color.RED : Color.BLACK);

                Text textNode = new Text("" + i);
                if (i < 10) {
                    textNode.xProperty().bind(r.xProperty().add(16));
                    textNode.yProperty().bind(r.yProperty().add(37));
                } else {
                    textNode.xProperty().bind(r.xProperty().add(12));
                    textNode.yProperty().bind(r.yProperty().add(37));
                }
                textNode.scaleXProperty().bind(r.widthProperty().divide(40));
                textNode.scaleYProperty().bind(r.heightProperty().divide(40));
                textNode.setFill(Color.WHITE);

                ajouterEffetHover(r);
                creerBindingParis(r, new Text(String.valueOf(i)));

                table.getChildren().addAll(r, textNode);
                rectangleMap.put(i, r);

                if (i > 1) {
                    survolerEspaceEntreCases(i - 1, i);
                }

                // Lignes horizontales
                Line ligneHaut = new Line(x, y, x + LARGEUR, y);
                Line ligneBas = new Line(x, y + HAUTEUR, x + LARGEUR, y + HAUTEUR);

                // Lignes verticales
                Line ligneGauche = new Line(x, y, x, y + HAUTEUR);
                Line ligneDroite = new Line(x + LARGEUR, y, x + LARGEUR, y + HAUTEUR);

                ligneHaut.setStroke(Color.WHITE);
                ligneHaut.setStrokeWidth(3);
                ligneBas.setStroke(Color.WHITE);
                ligneBas.setStrokeWidth(3);
                ligneGauche.setStroke(Color.WHITE);
                ligneGauche.setStrokeWidth(3);
                ligneDroite.setStroke(Color.WHITE);
                ligneDroite.setStrokeWidth(3);

                // Ajout des lignes au groupe parent
                table.getChildren().addAll(ligneHaut, ligneBas, ligneGauche, ligneDroite);
            }
        }
    }



    private void survolerEspaceEntreCases(int numeroCase1, int numeroCase2) {
        Rectangle case1 = rectangleMap.get(numeroCase1);
        Rectangle case2 = rectangleMap.get(numeroCase2);

        if (case1 != null && case2 != null) {
            double x = case1.getX() + LARGEUR;
            double y = Math.min(case1.getY(), case2.getY());
            double width = case2.getX() - case1.getX() - LARGEUR;
            double height = Math.max(case1.getHeight(), case2.getHeight());

            Rectangle espaceEntreCases = new Rectangle(x, y, width, height);
            espaceEntreCases.setFill(Color.TRANSPARENT);

            ajouterEffetHover(espaceEntreCases);

            espaceEntreCases.setOnMouseEntered(event -> {
                case1.setFill(Color.GREY);
                case2.setFill(Color.GREY);
            });

            espaceEntreCases.setOnMouseExited(event -> {
                case1.setFill(getCouleurPourNumero(numeroCase1));
                case2.setFill(getCouleurPourNumero(numeroCase2));
            });

            espacesEntreCases.add(espaceEntreCases);
            table.getChildren().add(espaceEntreCases);

            ajouterEffetHover(case1);
            ajouterEffetHover(case2);
        }
    }

    private void creerTopLine() {
        double x = START_X;
        double y = START_Y - (HAUTEUR * 3) + 25;

        // Rectangle
        Rectangle r1 = new Rectangle(x, y, LARGEUR * 4, 40);
        r1.setFill(Color.color(0.12156862745098039, 0.12156862745098039, 0.11764705882352941, 0.4));
        r1.setArcHeight(10);
        r1.setArcWidth(10);

        // Lignes horizontales
        Line ligneHaut = new Line(x, y, x + (LARGEUR * 4), y);
        ligneHaut.setStroke(Color.WHITE);
        ligneHaut.setStrokeWidth(3);

        Line ligneBas = new Line(x, y + 40, x + (LARGEUR * 4), y + 40);
        ligneBas.setStroke(Color.WHITE);
        ligneBas.setStrokeWidth(3);

        // Ligne verticale
        Line ligneGauche = new Line(x, y, x, y + 40);
        ligneGauche.setStroke(Color.WHITE);
        ligneGauche.setStrokeWidth(3);

        // Texte
        Text textNode1 = new Text("1-12");
        textNode1.setFill(Color.WHITE);
        textNode1.xProperty().bind(r1.xProperty().add(65));
        textNode1.yProperty().bind(r1.yProperty().add(25));
        textNode1.scaleXProperty().bind(r1.widthProperty().divide(60));
        textNode1.scaleYProperty().bind(r1.heightProperty().divide(45));

        table.getChildren().addAll(r1, ligneHaut, ligneBas, ligneGauche, textNode1);
        creerBindingParis(r1, textNode1);

        r1.setOnMouseEntered(event -> {
            List<Pair<Rectangle, Paint>> originalColors = new ArrayList<>();

            for (Node node : table.getChildren()) {
                if (node instanceof Rectangle && node != r1) {
                    Rectangle rectangle = (Rectangle) node;
                    originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                    Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                    if (nextNode instanceof Text) {
                        Text textNode = (Text) nextNode;
                        try {
                            int numero = Integer.parseInt(textNode.getText());
                            if (numero >= 1 && numero <= 12) {
                                rectangle.setFill(Color.GREY);
                                rectangle.setOpacity(0.7);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }

            r1.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> paiir : originalColors) {
                    paiir.getKey().setFill(paiir.getValue());
                    paiir.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });
        });
        textNode1.setOnMouseEntered(event -> {
            List<Pair<Rectangle, Paint>> originalColors = new ArrayList<>();

            for (Node node : table.getChildren()) {
                if (node instanceof Rectangle && node != textNode1) {
                    Rectangle rectangle = (Rectangle) node;
                    originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                    Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                    if (nextNode instanceof Text) {
                        Text textNode = (Text) nextNode;
                        try {
                            int numero = Integer.parseInt(textNode.getText());
                            if (numero >= 1 && numero <= 12) {
                                rectangle.setFill(Color.GREY);
                                rectangle.setOpacity(0.7);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }

            textNode1.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> paiir : originalColors) {
                    paiir.getKey().setFill(paiir.getValue());
                    paiir.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });
        });


        //2e 12
        x += LARGEUR * 4;
        Rectangle r2 = new Rectangle(x, y, LARGEUR * 4, 40);
        r2.setFill(Color.color(0.12156862745098039, 0.12156862745098039, 0.11764705882352941, 0.4));
        r2.setArcHeight(10);
        r2.setArcWidth(10);

        Line ligneHaut2 = new Line(x, y, x + (LARGEUR * 4), y);
        ligneHaut2.setStroke(Color.WHITE);
        ligneHaut2.setStrokeWidth(3);

        Line ligneBas2 = new Line(x, y + 40, x + (LARGEUR * 4), y + 40);
        ligneBas2.setStroke(Color.WHITE);
        ligneBas2.setStrokeWidth(3);

        Line ligneGauche2 = new Line(x, y, x, y + 40);
        ligneGauche2.setStroke(Color.WHITE);
        ligneGauche2.setStrokeWidth(3);

        Line ligneDroite2 = new Line(x + (LARGEUR * 4), y, x + (LARGEUR * 4), y + 40);
        ligneDroite2.setStroke(Color.WHITE);
        ligneDroite2.setStrokeWidth(3);

        Text textNode2 = new Text("13-24");
        textNode2.setFill(Color.WHITE);
        textNode2.xProperty().bind(r2.xProperty().add(65));
        textNode2.yProperty().bind(r2.yProperty().add(25));
        textNode2.scaleXProperty().bind(r2.widthProperty().divide(60));
        textNode2.scaleYProperty().bind(r2.heightProperty().divide(45));

        table.getChildren().addAll(r2, ligneHaut2, ligneBas2, ligneGauche2, ligneDroite2, textNode2);
        creerBindingParis(r2, textNode2);

        r2.setOnMouseEntered(event -> {
            List<Pair<Rectangle, Paint>> originalColors = new ArrayList<>();

            for (Node node : table.getChildren()) {
                if (node instanceof Rectangle && node != r2) {
                    Rectangle rectangle = (Rectangle) node;
                    originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                    Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                    if (nextNode instanceof Text) {
                        Text textNode = (Text) nextNode;
                        try {
                            int numero = Integer.parseInt(textNode.getText());
                            if (numero >= 13 && numero <= 24) {
                                rectangle.setFill(Color.GREY);
                                rectangle.setOpacity(0.7);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }

            r2.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> paiir : originalColors) {
                    paiir.getKey().setFill(paiir.getValue());
                    paiir.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });
        });

        textNode2.setOnMouseEntered(event -> {
            List<Pair<Rectangle, Paint>> originalColors = new ArrayList<>();

            for (Node node : table.getChildren()) {
                if (node instanceof Rectangle && node != textNode2) {
                    Rectangle rectangle = (Rectangle) node;
                    originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                    Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                    if (nextNode instanceof Text) {
                        Text textNode = (Text) nextNode;
                        try {
                            int numero = Integer.parseInt(textNode.getText());
                            if (numero >= 13 && numero <= 24) {
                                rectangle.setFill(Color.GREY);
                                rectangle.setOpacity(0.7);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }

            textNode2.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> paiir : originalColors) {
                    paiir.getKey().setFill(paiir.getValue());
                    paiir.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });
        });

        //3e 12
        x += LARGEUR * 4;
        Rectangle r3 = new Rectangle(x, y, LARGEUR * 4, 40);
        r3.setFill(Color.color(0.12156862745098039, 0.12156862745098039, 0.11764705882352941, 0.4));
        r3.setArcHeight(10);
        r3.setArcWidth(10);

        Line ligneHaut3 = new Line(x, y, x + (LARGEUR * 4), y);
        ligneHaut3.setStroke(Color.WHITE);
        ligneHaut3.setStrokeWidth(3);

        Line ligneBas3 = new Line(x, y + 40, x + (LARGEUR * 4), y + 40);
        ligneBas3.setStroke(Color.WHITE);
        ligneBas3.setStrokeWidth(3);

        Line ligneGauche3 = new Line(x, y, x, y + 40);
        ligneGauche3.setStroke(Color.WHITE);
        ligneGauche3.setStrokeWidth(3);

        Line ligneDroite3 = new Line(x + (LARGEUR * 4), y, x + (LARGEUR * 4), y + 40);
        ligneDroite3.setStroke(Color.WHITE);
        ligneDroite3.setStrokeWidth(3);

        Text textNode3 = new Text("25-36");
        textNode3.setFill(Color.WHITE);
        textNode3.xProperty().bind(r3.xProperty().add(65));
        textNode3.yProperty().bind(r3.yProperty().add(25));
        textNode3.scaleXProperty().bind(r3.widthProperty().divide(60));
        textNode3.scaleYProperty().bind(r3.heightProperty().divide(45));

        table.getChildren().addAll(r3, ligneHaut3, ligneBas3, ligneGauche3, ligneDroite3, textNode3);
        creerBindingParis(r3, textNode3);

        r3.setOnMouseEntered(event -> {
            List<Pair<Rectangle, Paint>> originalColors = new ArrayList<>();

            for (Node node : table.getChildren()) {
                if (node instanceof Rectangle && node != r3) {
                    Rectangle rectangle = (Rectangle) node;
                    originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                    Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                    if (nextNode instanceof Text) {
                        Text textNode = (Text) nextNode;
                        try {
                            int numero = Integer.parseInt(textNode.getText());
                            if (numero >= 25 && numero <= 36) {
                                rectangle.setFill(Color.GREY);
                                rectangle.setOpacity(0.7);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }

            r3.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> paiir : originalColors) {
                    paiir.getKey().setFill(paiir.getValue());
                    paiir.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });
        });

        textNode3.setOnMouseEntered(event -> {
            List<Pair<Rectangle, Paint>> originalColors = new ArrayList<>();

            for (Node node : table.getChildren()) {
                if (node instanceof Rectangle && node != textNode3) {
                    Rectangle rectangle = (Rectangle) node;
                    originalColors.add(new Pair<>(rectangle, rectangle.getFill()));
                    Node nextNode = table.getChildren().get(table.getChildren().indexOf(node) + 1);
                    if (nextNode instanceof Text) {
                        Text textNode = (Text) nextNode;
                        try {
                            int numero = Integer.parseInt(textNode.getText());
                            if (numero >= 25 && numero <= 36) {
                                rectangle.setFill(Color.GREY);
                                rectangle.setOpacity(0.7);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }

            textNode3.setOnMouseExited(e -> {
                for (Pair<Rectangle, Paint> paiir : originalColors) {
                    paiir.getKey().setFill(paiir.getValue());
                    paiir.getKey().setOpacity(1.0);
                }
                originalColors.clear();
            });
        });


        x = START_X + LARGEUR + 20;
    }

    private boolean caseCliquee = false;
    private Map<Node, Boolean> casesJetonPlace = new HashMap<>();

    private void ajouterEffetHover(Node node) {
        final ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100));
        final FadeTransition fadeTransition = new FadeTransition(Duration.millis(100));

        scaleTransition.setToX(1.05);
        scaleTransition.setToY(1.05);
        fadeTransition.setToValue(0.8);

        casesJetonPlace.put(node, false);

        node.setOnMouseEntered(event -> {
            if (!casesJetonPlace.get(node)) {
                scaleTransition.setNode(node);
                fadeTransition.setNode(node);

                scaleTransition.playFromStart();
                fadeTransition.playFromStart();

                if (node instanceof Shape) {
                    Shape shape = (Shape) node;
                    Paint fill = shape.getFill();
                    if (fill instanceof Color) {
                        Color couleurOriginale = (Color) fill;
                        shape.setUserData(couleurOriginale);
                        shape.setFill(Color.GREY);
                    }
                }
            }
        });

        node.setOnMouseExited(event -> {
            if (!casesJetonPlace.get(node)) {
                scaleTransition.setNode(node);
                fadeTransition.setNode(node);

                scaleTransition.playFromStart();
                fadeTransition.playFromStart();

                if (node instanceof Shape) {
                    Shape shape = (Shape) node;
                    Paint fill = (Paint) shape.getUserData();
                    if (fill instanceof Color) {
                        Color couleurOriginale = (Color) fill;
                        shape.setFill(couleurOriginale);
                    }
                }
            }
        });

//            node.setOnMouseClicked(event -> {
//                scaleTransition.stop();
//                fadeTransition.stop();
//
//                casesJetonPlace.put(node, true);
//
//                if (node instanceof Rectangle) {
//                    placerJeton((Rectangle) node);
//                } else if (node instanceof Polygon) {
//                    placerJetonZero((Polygon) node);
//                }
//            });
    }


    private void ajouterParis(ArrayList<Integer> paris) {
        for (int i : paris) {
            if (!listeParis.contains(i)) {
                listeParis.add(i);
            }
        }
        System.out.println(listeParis.toString());
    }

    private void ajouterNombreParis(int nb) {
        if (!listeParis.contains(nb)) {
            listeParis.add(nb);
        }
    }

    private void changerLabelInstructions(String pari) {
        if (langueChoisie.intValue()==0){
            labelInstructions.setText("Paris sur " + pari);
        }
        else{
            labelInstructions.setText("Bet on " + pari);
        }
    }

    private void multimontant(ArrayList<Integer> nombresGagnants) {
        for (int nombre : nombresGagnants) {
            multiplicateursParis.set(nombre, multiplicateursParis.get(nombre) + 3);
            montantParis.set(nombre, jeu.joueurCourantProperty().get().getMiseActuelle() + montantParis.get(nombre));
        }
    }

    private void bimontant(ArrayList<Integer> nombresGagnants) {
        for (int nombre : nombresGagnants) {
            multiplicateursParis.set(nombre, multiplicateursParis.get(nombre) + 2);
            montantParis.set(nombre, jeu.joueurCourantProperty().get().getMiseActuelle() + montantParis.get(nombre));
        }
    }

    private void unimontant(int nombreGagnant) {
        multiplicateursParis.set(nombreGagnant, multiplicateursParis.get(nombreGagnant) + 36);
        montantParis.set(nombreGagnant, jeu.joueurCourantProperty().get().getMiseActuelle() + montantParis.get(nombreGagnant));
    }


    public void placerJeton(String caseInt) {
        ImageView imageCase =  new ImageView();
        switch (jeu.joueurCourantProperty().get().getMiseActuelle()) {
            case 1:
                imageCase = new ImageView(new Image("images/token_1_2.png"));
                break;
            case 5:
                imageCase = new ImageView(new Image("images/token_5_2.png"));
                break;
            case 25:
                imageCase = new ImageView(new Image("images/token_25_2.png"));
                break;
            case 100:
                imageCase = new ImageView(new Image("images/token_100_2.png"));
                break;
            case 500:
                imageCase = new ImageView(new Image("images/token_500_2.png"));
                break;
            case 1000:
                imageCase = new ImageView(new Image("images/token_1k_2.png"));
                break;
            default:
                break;
        }
        DonneesGraphiques.Coordonnees coordonnees = DonneesGraphiques.cases.get(caseInt).get(0);
        double startX = coordonnees.getxStart();
        double startY = coordonnees.getyStart();
        imageCase.setFitWidth(38);
        imageCase.setFitHeight(38);
        imageCase.setPreserveRatio(false);
        imageCase.setLayoutX(startX - 19);
        imageCase.setLayoutY(startY - 19);
        table.getChildren().add(imageCase);
    }


//    private void placerJetonZero(Polygon p) {
//        ImagePattern imageCase;
//        switch (jeu.joueurCourantProperty().get().getMiseTotale()) {
//            case 1:
//                imageCase = new ImagePattern(new Image("images/token_1_2.png"));
//                p.setFill(imageCase);
//                break;
//            case 5:
//                imageCase = new ImagePattern(new Image("images/token_5_2.png"));
//                p.setFill(imageCase);
//                break;
//            case 25:
//                imageCase = new ImagePattern(new Image("images/token_25_2.png"));
//                p.setFill(imageCase);
//                break;
//            case 100:
//                imageCase = new ImagePattern(new Image("images/token_100_2.png"));
//                p.setFill(imageCase);
//                break;
//            case 500:
//                imageCase = new ImagePattern(new Image("images/token_500_2.png"));
//                p.setFill(imageCase);
//                break;
//            case 1000:
//                imageCase = new ImagePattern(new Image("images/token_1k_2.png"));
//                p.setFill(imageCase);
//                break;
//            default:
//                break;
//        }
//    }


    private void creerBindingParis(Node n, Text textNode) {
        EventHandler<javafx.scene.input.MouseEvent> parisJoueur = mouseEvent -> {
            System.out.println(jeu.getResultatTourActuel().getCouleur());
            System.out.println(jeu.getResultatTourActuel().getValeur());
            if (jeu.joueurCourantProperty().get().getMiseActuelle() != 0) {
                switch (textNode.getText()) {
                    default:
                        if (langueChoisie.intValue()==0){
                            labelInstructions.setText("Paris Incorret");
                        }
                        else{
                            labelInstructions.setText("Bet are Incorrect");
                        }
                        break;

                    case "2 pour 1-1":
                        ArrayList<Integer> nombresGagnants2pour11 = new ArrayList<>(Arrays.asList(1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34));
                        ajouterParis(nombresGagnants2pour11);
                        multimontant(nombresGagnants2pour11);
                        changerLabelInstructions("2 pour 1-1");
                        placerJeton("2pour1-1");
                        break;

                    case "2 pour 1-2":
                        ArrayList<Integer> nombresGagnants2pour12 = new ArrayList<>(Arrays.asList(2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35));
                        ajouterParis(nombresGagnants2pour12);
                        multimontant(nombresGagnants2pour12);
                        changerLabelInstructions("2 pour 1-2");
                        placerJeton("2pour1-2");
                        break;

                    case "2 pour 1-3":
                        ArrayList<Integer> nombresGagnants2pour13 = new ArrayList<>(Arrays.asList(3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36));
                        ajouterParis(nombresGagnants2pour13);
                        multimontant(nombresGagnants2pour13);
                        changerLabelInstructions("2 pour 1-3");
                        placerJeton("2pour1-3");
                        break;

                    case "1 à 18":
                        ArrayList<Integer> nombresGagnants1a18 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18));
                        ajouterParis(nombresGagnants1a18);
                        bimontant(nombresGagnants1a18);
                        changerLabelInstructions("1 à 18");
                        placerJeton("1 à 18");
                        break;

                    case "19 à 36":
                        ArrayList<Integer> nombresGagnants19a36 = new ArrayList<>(Arrays.asList(19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36));
                        ajouterParis(nombresGagnants19a36);
                        bimontant(nombresGagnants19a36);
                        changerLabelInstructions("19 à 36");
                        placerJeton("19 à 36");
                        break;

                    case "1-12":
                        ArrayList<Integer> nombresGagnants1a12 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
                        ajouterParis(nombresGagnants1a12);
                        changerLabelInstructions("1-12");
                        multimontant(nombresGagnants1a12);
                        placerJeton("1-12");
                        break;

                    case "13-24":
                        ArrayList<Integer> nombresGagnants13a24 = new ArrayList<>(Arrays.asList(13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24));
                        ajouterParis(nombresGagnants13a24);
                        multimontant(nombresGagnants13a24);
                        changerLabelInstructions("13-24");
                        placerJeton("13-24");
                        break;

                    case "25-36":
                        ArrayList<Integer> nombresGagnants25a36 = new ArrayList<>(Arrays.asList(25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36));
                        ajouterParis(nombresGagnants25a36);
                        multimontant(nombresGagnants25a36);
                        changerLabelInstructions("25-36");
                        placerJeton("25-36");
                        break;

                    case "IMPAIR":
                        ArrayList<Integer> impaire = new ArrayList<>();
                        for (int i = 1; i < 37; i += 2) {
                            impaire.add(i);
                        }
                        ajouterParis(impaire);
                        bimontant(impaire);
                        changerLabelInstructions("IMPAIR");
                        placerJeton("impair");
                        break;

                    case "PAIR":
                        ArrayList<Integer> paire = new ArrayList<>();
                        for (int i = 0; i < 37; i += 2) {
                            paire.add(i);
                        }
                        ajouterParis(paire);
                        bimontant(paire);
                        changerLabelInstructions("PAIR");
                        placerJeton("pair");
                        break;

                    case "rouge":
                        ArrayList<Integer> rouge = new ArrayList<>();
                        for (int i = 1; i < 37; i++) {
                            if (getCouleurPourNumero(i).equals(Color.RED)) {
                                rouge.add(i);
                            }
                        }
                        ajouterParis(rouge);
                        bimontant(rouge);
                        changerLabelInstructions("rouge");
                        placerJeton("rouge");
                        break;

                    case "noir":
                        ArrayList<Integer> noir = new ArrayList<>();
                        for (int i = 1; i < 37; i++) {
                            if (getCouleurPourNumero(i).equals(Color.BLACK)) {
                                noir.add(i);
                            }
                        }
                        ajouterParis(noir);
                        bimontant(noir);
                        changerLabelInstructions("noir");
                        placerJeton("noir");
                        break;


                    case "0":
                        ajouterNombreParis(0);
                        unimontant(0);
                        changerLabelInstructions("0");
                        placerJeton("0");
                        break;

                    case "1":
                        ajouterNombreParis(1);
                        unimontant(1);
                        changerLabelInstructions("1");
                        placerJeton("1");
                        break;

                    case "2":
                        ajouterNombreParis(2);
                        unimontant(2);
                        changerLabelInstructions("2");
                        placerJeton("2");
                        break;

                    case "3":
                        ajouterNombreParis(3);
                        unimontant(3);
                        changerLabelInstructions("3");
                        placerJeton("3");
                        break;

                    case "4":
                        ajouterNombreParis(4);
                        unimontant(4);
                        changerLabelInstructions("4");
                        placerJeton("4");
                        break;

                    case "5":
                        ajouterNombreParis(5);
                        unimontant(5);
                        changerLabelInstructions("5");
                        placerJeton("5");
                        break;

                    case "6":
                        ajouterNombreParis(6);
                        unimontant(6);
                        changerLabelInstructions("6");
                        placerJeton("6");
                        break;

                    case "7":
                        ajouterNombreParis(7);
                        unimontant(7);
                        changerLabelInstructions("7");
                        placerJeton("7");
                        break;

                    case "8":
                        ajouterNombreParis(8);
                        unimontant(8);
                        changerLabelInstructions("8");
                        placerJeton("8");
                        break;

                    case "9":
                        ajouterNombreParis(9);
                        unimontant(9);
                        changerLabelInstructions("9");
                        placerJeton("9");
                        break;

                    case "10":
                        ajouterNombreParis(10);
                        unimontant(10);
                        changerLabelInstructions("10");
                        placerJeton("10");
                        break;

                    case "11":
                        ajouterNombreParis(11);
                        unimontant(11);
                        changerLabelInstructions("11");
                        placerJeton("11");
                        break;

                    case "12":
                        ajouterNombreParis(12);
                        unimontant(12);
                        changerLabelInstructions("12");
                        placerJeton("12");
                        break;

                    case "13":
                        ajouterNombreParis(13);
                        unimontant(13);
                        changerLabelInstructions("13");
                        placerJeton("13");
                        break;

                    case "14":
                        ajouterNombreParis(14);
                        unimontant(14);
                        changerLabelInstructions("14");
                        placerJeton("14");
                        break;

                    case "15":
                        ajouterNombreParis(15);
                        unimontant(15);
                        changerLabelInstructions("15");
                        placerJeton("15");
                        break;

                    case "16":
                        ajouterNombreParis(16);
                        unimontant(16);
                        changerLabelInstructions("16");
                        placerJeton("16");
                        break;

                    case "17":
                        ajouterNombreParis(17);
                        unimontant(17);
                        changerLabelInstructions("17");
                        placerJeton("17");
                        break;

                    case "18":
                        ajouterNombreParis(18);
                        unimontant(18);
                        changerLabelInstructions("18");
                        placerJeton("18");
                        break;

                    case "19":
                        ajouterNombreParis(19);
                        unimontant(19);
                        changerLabelInstructions("19");
                        placerJeton("19");
                        break;

                    case "20":
                        ajouterNombreParis(20);
                        unimontant(20);
                        changerLabelInstructions("20");
                        placerJeton("20");
                        break;

                    case "21":
                        ajouterNombreParis(21);
                        unimontant(21);
                        changerLabelInstructions("21");
                        placerJeton("21");
                        break;

                    case "22":
                        ajouterNombreParis(22);
                        unimontant(22);
                        changerLabelInstructions("22");
                        placerJeton("22");
                        break;

                    case "23":
                        ajouterNombreParis(23);
                        unimontant(23);
                        changerLabelInstructions("23");
                        placerJeton("23");
                        break;

                    case "24":
                        ajouterNombreParis(24);
                        unimontant(24);
                        changerLabelInstructions("24");
                        placerJeton("24");
                        break;

                    case "25":
                        ajouterNombreParis(25);
                        unimontant(25);
                        changerLabelInstructions("25");
                        placerJeton("25");
                        break;

                    case "26":
                        ajouterNombreParis(26);
                        unimontant(26);
                        changerLabelInstructions("26");
                        placerJeton("26");
                        break;

                    case "27":
                        ajouterNombreParis(27);
                        unimontant(27);
                        changerLabelInstructions("27");
                        placerJeton("27");
                        break;

                    case "28":
                        ajouterNombreParis(28);
                        unimontant(28);
                        changerLabelInstructions("28");
                        placerJeton("28");
                        break;

                    case "29":
                        ajouterNombreParis(29);
                        unimontant(29);
                        changerLabelInstructions("29");
                        placerJeton("29");
                        break;

                    case "30":
                        ajouterNombreParis(30);
                        unimontant(30);
                        changerLabelInstructions("30");
                        placerJeton("30");
                        break;

                    case "31":
                        ajouterNombreParis(31);
                        unimontant(31);
                        changerLabelInstructions("31");
                        placerJeton("31");
                        break;

                    case "32":
                        ajouterNombreParis(32);
                        unimontant(32);
                        changerLabelInstructions("32");
                        placerJeton("32");
                        break;

                    case "33":
                        ajouterNombreParis(33);
                        unimontant(33);
                        changerLabelInstructions("33");
                        placerJeton("33");
                        break;

                    case "34":
                        ajouterNombreParis(34);
                        unimontant(34);
                        changerLabelInstructions("34");
                        placerJeton("34");
                        break;

                    case "35":
                        ajouterNombreParis(35);
                        unimontant(35);
                        changerLabelInstructions("35");
                        placerJeton("35");
                        break;

                    case "36":
                        ajouterNombreParis(36);
                        unimontant(36);
                        changerLabelInstructions("36");
                        placerJeton("36");
                        break;

                }
            }

            if (textNode.getText().equals("0")) {
                vueBet.validationProperty().addListener((observable, oldValue, newValue) -> {
                    Polygon polygon = (Polygon) n;
                    polygon.setFill(Color.GREEN);
                    polygon.setOpacity(0.7);
                });

            } else {
                boolean nombre = false;
                for (int i = 1; i < 37; i++) {
                    if (textNode.getText().equals(String.valueOf(i))) {
                        nombre = true;
                        break;
                    }
                }

                if (nombre) {
                    vueBet.validationProperty().addListener((observable, oldValue, newValue) -> {
                        Rectangle rectangle = (Rectangle) n;
                        if (getCouleurPourNumero(Integer.parseInt(textNode.getText())).equals(Color.RED)) {
                            rectangle.setFill(Color.RED);
                        } else {
                            rectangle.setFill(Color.BLACK);
                        }
                        rectangle.setOpacity(0.7);
                    });
                } else if (textNode.getText().equals("noir") || textNode.getText().equals("rouge")) {
                    double x = START_X + (LARGEUR * 12);
                    double y = START_Y;
                    if (textNode.getText().equals("rouge")) {
                        vueBet.validationProperty().addListener((observable, oldValue, newValue) -> {
                            Rectangle rouge = (Rectangle) n;
                            Rectangle losangeRouge = new Rectangle(x + 10, y + 10, 10, 10);
                            losangeRouge.getTransforms().add(new Rotate(45, x + 10, y + 15));
                            losangeRouge.setFill(Color.RED);
                            losangeRouge.xProperty().bind(rouge.xProperty().add(9.7));
                            losangeRouge.yProperty().bind(rouge.yProperty().add(1));
                            losangeRouge.scaleXProperty().bind(rouge.widthProperty().divide(16));
                            losangeRouge.scaleYProperty().bind(rouge.heightProperty().divide(16));
                        });
                    } else {
                        Rectangle noir = (Rectangle) n;
                        Rectangle losangeNoir = new Rectangle(x + 10, y + 10, 10, 10);
                        losangeNoir.getTransforms().add(new Rotate(45, x + 10, y + 15));
                        losangeNoir.setFill(Color.BLACK);
                        losangeNoir.xProperty().bind(noir.xProperty().add(9.7));
                        losangeNoir.yProperty().bind(noir.yProperty().add(1));
                        losangeNoir.scaleXProperty().bind(noir.widthProperty().divide(16));
                        losangeNoir.scaleYProperty().bind(noir.heightProperty().divide(16));
                    }
                } else {
                    vueBet.validationProperty().addListener((observable, oldValue, newValue) -> {
                        Rectangle rectangle = (Rectangle) n;
                        rectangle.setFill(Color.color(0.12156862745098039, 0.12156862745098039, 0.11764705882352941, 0.4));
                        rectangle.setOpacity(0.7);
                    });
                }
            }

            jeu.joueurCourantProperty().get().setMiseTotale(jeu.joueurCourantProperty().get().getMiseTotale() + jeu.joueurCourantProperty().get().getMiseActuelle());
            jeu.joueurCourantProperty().get().setMiseActuelle(0);
        };
        n.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, parisJoueur);
    }


    private Color getCouleurPourNumero(int numero) {
        for (Nombres n : setNombres.getNumberSet()) {
            if (n.getNumber() == numero) {
                return n.getColor().equals("R") ? Color.RED : Color.BLACK;
            }
        }
        return Color.BLACK;
    }
}
