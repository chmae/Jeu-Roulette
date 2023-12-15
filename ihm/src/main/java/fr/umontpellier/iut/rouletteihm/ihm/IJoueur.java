package fr.umontpellier.iut.rouletteihm.ihm;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public interface IJoueur {
    int getSolde();

    int getMiseTotale();

    IntegerProperty getMiseTotaleProperty();

    String getNom();

    IntegerProperty soldeProperty();

    void miseAJourBanque(int amount);

    StringProperty nomProperty();

    IntegerProperty getMiseActuelleProperty();

    int getMiseActuelle();

    void setMiseActuelle(int mise);

    void setMiseTotale(int mise);
}
