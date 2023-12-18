package fr.umontpellier.iut.rouletteihm.ihm.vues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DonneesGraphiques {

    public final static Map<String, ArrayList<Coordonnees>> cheval;
    public final static Map<String, ArrayList<Coordonnees>> carre;
    public final static Map<String, ArrayList<Coordonnees>> transversale;
    public final static Map<String, ArrayList<Coordonnees>> sixain;
    public final static Map<String, ArrayList<Coordonnees>> cases;

    static {
        cheval = new LinkedHashMap<>();

        //Coordonnées des chevaux en 0
        cheval.put("0-3", new ArrayList<>() {{
            add(new Coordonnees(398.5, 435.0, 401.5, 493.0));
        }});
        cheval.put("0-2", new ArrayList<>() {{
            add(new Coordonnees(398.5, 501.0, 401.5, 559.0));
        }});
        cheval.put("0-1", new ArrayList<>() {{
            add(new Coordonnees(398.5, 567.0, 401.5, 625.0));
        }});

        //Coordonnées des chevaux ligne 1
        cheval.put("1-4", new ArrayList<>() {{
            add(new Coordonnees(438.5, 567.0, 441.5, 625.0));
        }});
        cheval.put("4-7", new ArrayList<>() {{
            add(new Coordonnees(478.5, 567.0, 481.5, 625.0));
        }});
        cheval.put("7-10", new ArrayList<>() {{
            add(new Coordonnees(518.5, 567.0, 521.5, 625.0));
        }});
        cheval.put("10-13", new ArrayList<>() {{
            add(new Coordonnees(558.5, 567.0, 561.5, 625.0));
        }});
        cheval.put("13-16", new ArrayList<>() {{
            add(new Coordonnees(598.5, 567.0, 601.5, 625.0));
        }});
        cheval.put("16-19", new ArrayList<>() {{
            add(new Coordonnees(638.5, 567.0, 641.5, 625.0));
        }});
        cheval.put("19-22", new ArrayList<>() {{
            add(new Coordonnees(678.5, 567.0, 681.5, 625.0));
        }});
        cheval.put("22-25", new ArrayList<>() {{
            add(new Coordonnees(718.5, 567.0, 721.5, 625.0));
        }});
        cheval.put("25-28", new ArrayList<>() {{
            add(new Coordonnees(758.5, 567.0, 761.5, 625.0));
        }});
        cheval.put("28-31", new ArrayList<>() {{
            add(new Coordonnees(798.5, 567.0, 801.5, 625.0));
        }});
        cheval.put("31-34", new ArrayList<>() {{
            add(new Coordonnees(838.5, 567.0, 841.5, 625.0));
        }});

        //Coordonnées des chevaux ligne 2
        cheval.put("2-5", new ArrayList<>() {{
            add(new Coordonnees(438.5, 501.0, 441.5, 559.0));
        }});
        cheval.put("5-8", new ArrayList<>() {{
            add(new Coordonnees(478.5, 501.0, 481.5, 559.0));
        }});
        cheval.put("8-11", new ArrayList<>() {{
            add(new Coordonnees(518.5, 501.0, 521.5, 559.0));
        }});
        cheval.put("11-14", new ArrayList<>() {{
            add(new Coordonnees(558.5, 501.0, 561.5, 559.0));
        }});
        cheval.put("14-17", new ArrayList<>() {{
            add(new Coordonnees(598.5, 501.0, 601.5, 559.0));
        }});
        cheval.put("17-20", new ArrayList<>() {{
            add(new Coordonnees(638.5, 501.0, 641.5, 559.0));
        }});
        cheval.put("20-23", new ArrayList<>() {{
            add(new Coordonnees(678.5, 501.0, 681.5, 559.0));
        }});
        cheval.put("23-26", new ArrayList<>() {{
            add(new Coordonnees(718.5, 501.0, 721.5, 559.0));
        }});
        cheval.put("26-29", new ArrayList<>() {{
            add(new Coordonnees(758.5, 501.0, 761.5, 559.0));
        }});
        cheval.put("29-32", new ArrayList<>() {{
            add(new Coordonnees(798.5, 501.0, 801.5, 559.0));
        }});
        cheval.put("32-35", new ArrayList<>() {{
            add(new Coordonnees(838.5, 501.0, 841.5, 559.0));
        }});

        //Coordonnées des chevaux ligne 3
        cheval.put("3-6", new ArrayList<>() {{
            add(new Coordonnees(438.5, 435.0, 441.5, 493.0));
        }});
        cheval.put("6-9", new ArrayList<>() {{
            add(new Coordonnees(478.5, 435.0, 481.5, 493.0));
        }});
        cheval.put("9-12", new ArrayList<>() {{
            add(new Coordonnees(518.5, 435.0, 521.5, 493.0));
        }});
        cheval.put("12-15", new ArrayList<>() {{
            add(new Coordonnees(558.5, 435.0, 561.5, 493.0));
        }});
        cheval.put("15-18", new ArrayList<>() {{
            add(new Coordonnees(598.5, 435.0, 601.5, 493.0));
        }});
        cheval.put("18-21", new ArrayList<>() {{
            add(new Coordonnees(638.5, 435.0, 641.5, 493.0));
        }});
        cheval.put("21-24", new ArrayList<>() {{
            add(new Coordonnees(678.5, 435.0, 681.5, 493.0));
        }});
        cheval.put("24-27", new ArrayList<>() {{
            add(new Coordonnees(718.5, 435.0, 721.5, 493.0));
        }});
        cheval.put("27-30", new ArrayList<>() {{
            add(new Coordonnees(758.5, 435.0, 761.5, 493.0));
        }});
        cheval.put("30-33", new ArrayList<>() {{
            add(new Coordonnees(798.5, 435.0, 801.5, 493.0));
        }});
        cheval.put("33-36", new ArrayList<>() {{
            add(new Coordonnees(838.5, 435.0, 841.5, 493.0));
        }});

        //Coordonnées des chevaux ligne 1-2
        cheval.put("1-2", new ArrayList<>() {{
            add(new Coordonnees(403.0, 561.5, 436.5, 564.5));
        }});
        cheval.put("4-5", new ArrayList<>() {{
            add(new Coordonnees(442.5, 561.5, 475.5, 564.5));
        }});
        cheval.put("7-8", new ArrayList<>() {{
            add(new Coordonnees(482.5, 561.5, 515.5, 564.5));
        }});
        cheval.put("10-11", new ArrayList<>() {{
            add(new Coordonnees(522.5, 561.5, 555.5, 564.5));
        }});
        cheval.put("13-14", new ArrayList<>() {{
            add(new Coordonnees(562.5, 561.5, 595.5, 564.5));
        }});
        cheval.put("16-17", new ArrayList<>() {{
            add(new Coordonnees(602.5, 561.5, 635.5, 564.5));
        }});
        cheval.put("19-20", new ArrayList<>() {{
            add(new Coordonnees(642.5, 561.5, 675.5, 564.5));
        }});
        cheval.put("22-23", new ArrayList<>() {{
            add(new Coordonnees(682.5, 561.5, 715.5, 564.5));
        }});
        cheval.put("25-26", new ArrayList<>() {{
            add(new Coordonnees(722.5, 561.5, 755.5, 564.5));
        }});
        cheval.put("28-29", new ArrayList<>() {{
            add(new Coordonnees(762.5, 561.5, 795.5, 564.5));
        }});
        cheval.put("31-32", new ArrayList<>() {{
            add(new Coordonnees(802.5, 561.5, 835.5, 564.5));
        }});
        cheval.put("34-35", new ArrayList<>() {{
            add(new Coordonnees(842.5, 561.5, 875.5, 564.5));
        }});

        //Coordonnées des chevaux ligne 2-3
        cheval.put("2-3", new ArrayList<>() {{
            add(new Coordonnees(403.0, 496.5, 436.5, 499.5));
        }});
        cheval.put("5-6", new ArrayList<>() {{
            add(new Coordonnees(442.5, 496.5, 475.5, 499.5));
        }});
        cheval.put("8-9", new ArrayList<>() {{
            add(new Coordonnees(482.5, 496.5, 515.5, 499.5));
        }});
        cheval.put("11-12", new ArrayList<>() {{
            add(new Coordonnees(522.5, 496.5, 555.5, 499.5));
        }});
        cheval.put("14-15", new ArrayList<>() {{
            add(new Coordonnees(562.5, 496.5, 595.5, 499.5));
        }});
        cheval.put("17-18", new ArrayList<>() {{
            add(new Coordonnees(602.5, 496.5, 635.5, 499.5));
        }});
        cheval.put("20-21", new ArrayList<>() {{
            add(new Coordonnees(642.5, 496.5, 675.5, 499.5));
        }});
        cheval.put("23-24", new ArrayList<>() {{
            add(new Coordonnees(682.5, 496.5, 715.5, 499.5));
        }});
        cheval.put("26-27", new ArrayList<>() {{
            add(new Coordonnees(722.5, 496.5, 755.5, 499.5));
        }});
        cheval.put("29-30", new ArrayList<>() {{
            add(new Coordonnees(762.5, 496.5, 795.5, 499.5));
        }});
        cheval.put("32-33", new ArrayList<>() {{
            add(new Coordonnees(802.5, 496.5, 835.5, 499.5));
        }});
        cheval.put("35-36", new ArrayList<>() {{
            add(new Coordonnees(842.5, 496.5, 875.5, 499.5));
        }});
    }

    static {
        carre = new LinkedHashMap<>();

        //Coordonnées des carrés ligne 1-2
        carre.put("1-2-4-5", new ArrayList<>() {{
            add(new Coordonnees(438.5, 560.0, 441.5, 566.0));
        }});
        carre.put("4-5-7-8", new ArrayList<>() {{
            add(new Coordonnees(478.5, 560.0, 481.5, 566.0));
        }});
        carre.put("7-8-10-11", new ArrayList<>() {{
            add(new Coordonnees(518.5, 560.0, 521.5, 566.0));
        }});
        carre.put("10-11-13-14", new ArrayList<>() {{
            add(new Coordonnees(558.5, 560.0, 561.5, 566.0));
        }});
        carre.put("13-14-16-17", new ArrayList<>() {{
            add(new Coordonnees(598.5, 560.0, 601.5, 566.0));
        }});
        carre.put("16-17-19-20", new ArrayList<>() {{
            add(new Coordonnees(638.5, 560.0, 641.5, 566.0));
        }});
        carre.put("19-20-22-23", new ArrayList<>() {{
            add(new Coordonnees(678.5, 560.0, 681.5, 566.0));
        }});
        carre.put("22-23-25-26", new ArrayList<>() {{
            add(new Coordonnees(718.5, 560.0, 721.5, 566.0));
        }});
        carre.put("25-26-28-29", new ArrayList<>() {{
            add(new Coordonnees(758.5, 560.0, 761.5, 566.0));
        }});
        carre.put("28-29-31-32", new ArrayList<>() {{
            add(new Coordonnees(798.5, 560.0, 801.5, 566.0));
        }});
        carre.put("31-32-34-35", new ArrayList<>() {{
            add(new Coordonnees(838.5, 560.0, 841.5, 566.0));
        }});


        //Coordonnées des carrés ligne 3-2
        carre.put("2-3-5-6", new ArrayList<>() {{
            add(new Coordonnees(438.5, 494.0, 441.5, 500.0));
        }});
        carre.put("5-6-8-9", new ArrayList<>() {{
            add(new Coordonnees(478.5, 494.0, 481.5, 500.0));
        }});
        carre.put("8-9-11-12", new ArrayList<>() {{
            add(new Coordonnees(518.5, 494.0, 521.5, 500.0));
        }});
        carre.put("11-12-14-15", new ArrayList<>() {{
            add(new Coordonnees(558.5, 494.0, 561.5, 500.0));
        }});
        carre.put("14-15-17-18", new ArrayList<>() {{
            add(new Coordonnees(598.5, 494.0, 601.5, 500.0));
        }});
        carre.put("17-18-20-21", new ArrayList<>() {{
            add(new Coordonnees(638.5, 494.0, 641.5, 500.0));
        }});
        carre.put("20-21-23-24", new ArrayList<>() {{
            add(new Coordonnees(678.5, 494.0, 681.5, 500.0));
        }});
        carre.put("23-24-26-27", new ArrayList<>() {{
            add(new Coordonnees(718.5, 494.0, 721.5, 500.0));
        }});
        carre.put("26-27-29-30", new ArrayList<>() {{
            add(new Coordonnees(758.5, 494.0, 761.5, 500.0));
        }});
        carre.put("29-30-32-33", new ArrayList<>() {{
            add(new Coordonnees(798.5, 494.0, 801.5, 500.0));
        }});
        carre.put("32-33-35-36", new ArrayList<>() {{
            add(new Coordonnees(838.5, 494.0, 841.5, 500.0));
        }});

    }

    static {
        transversale = new LinkedHashMap<>();

        //Coordonnées transversales 0
        transversale.put("0-1-2", new ArrayList<>() {{
            add(new Coordonnees(398.5, 560.0, 401.5, 566.0));
        }});
        transversale.put("0-2-3", new ArrayList<>() {{
            add(new Coordonnees(398.5, 494.0, 401.5, 500.0));
        }});


        //Coordonnées transervales bottom/top
        transversale.put("1-2-3", new ArrayList<>() {{
            add(new Coordonnees(403.0, 625.5, 436.5, 628.5));
            add(new Coordonnees(403.0, 432.0, 436.5, 435.0));
        }});
        transversale.put("4-5-6", new ArrayList<>() {{
            add(new Coordonnees(442.5, 625.5, 475.5, 628.5));
            add(new Coordonnees(442.5, 432.0, 475.5, 435.0));
        }});
        transversale.put("7-8-9", new ArrayList<>() {{
            add(new Coordonnees(482.5, 625.5, 515.5, 628.5));
            add(new Coordonnees(482.5, 432.0, 515.5, 435.0));
        }});
        transversale.put("10-11-12", new ArrayList<>() {{
            add(new Coordonnees(522.5, 625.5, 555.5, 628.5));
            add(new Coordonnees(522.5, 432.0, 555.5, 435.0));
        }});
        transversale.put("13-14-15", new ArrayList<>() {{
            add(new Coordonnees(562.5, 625.5, 595.5, 628.5));
            add(new Coordonnees(562.5, 432.0, 595.5, 435.0));
        }});
        transversale.put("16-17-18", new ArrayList<>() {{
            add(new Coordonnees(602.5, 625.5, 635.5, 628.5));
            add(new Coordonnees(602.5, 432.0, 635.5, 435.0));
        }});
        transversale.put("19-20-21", new ArrayList<>() {{
            add(new Coordonnees(642.5, 625.5, 675.5, 628.5));
            add(new Coordonnees(642.5, 432.0, 675.5, 435.0));
        }});
        transversale.put("22-23-24", new ArrayList<>() {{
            add(new Coordonnees(682.5, 625.5, 715.5, 628.5));
            add(new Coordonnees(682.5, 432.0, 715.5, 435.0));
        }});
        transversale.put("25-26-27", new ArrayList<>() {{
            add(new Coordonnees(722.5, 625.5, 755.5, 628.5));
            add(new Coordonnees(722.5, 432.0, 755.5, 435.0));
        }});
        transversale.put("28-29-30", new ArrayList<>() {{
            add(new Coordonnees(762.5, 625.5, 795.5, 628.5));
            add(new Coordonnees(762.5, 432.0, 795.5, 435.0));
        }});
        transversale.put("31-32-33", new ArrayList<>() {{
            add(new Coordonnees(802.5, 625.5, 835.5, 628.5));
            add(new Coordonnees(802.5, 432.0, 835.5, 435.0));
        }});
        transversale.put("34-35-36", new ArrayList<>() {{
            add(new Coordonnees(842.5, 625.5, 875.5, 628.5));
            add(new Coordonnees(842.5, 432.0, 875.5, 435.0));
        }});
    }

    static {
        sixain = new LinkedHashMap<>();

        //Coordonnées sixain Bottom/top
        sixain.put("1-2-3-4-5-6", new ArrayList<>() {{
            add(new Coordonnees(436.5, 625.5, 442.5, 628.5));
            add(new Coordonnees(442.5, 432.0, 475.5, 435.0));
        }});
        sixain.put("4-5-6-7-8-9", new ArrayList<>() {{
            add(new Coordonnees(475.5, 625.5, 482.5, 628.5));
            add(new Coordonnees(475.5, 432.0, 482.5, 435.0));
        }});
        sixain.put("7-8-9-10-11-12", new ArrayList<>() {{
            add(new Coordonnees(515.5, 625.5, 522.5, 628.5));
            add(new Coordonnees(515.5, 432.0, 522.5, 435.0));
        }});
        sixain.put("10-11-12-13-14-15", new ArrayList<>() {{
            add(new Coordonnees(555.5, 625.5, 562.5, 628.5));
            add(new Coordonnees(555.5, 432.0, 562.5, 435.0));
        }});
        sixain.put("13-14-15-16-17-18", new ArrayList<>() {{
            add(new Coordonnees(595.5, 625.5, 602.5, 628.5));
            add(new Coordonnees(595.5, 432.0, 602.5, 435.0));
        }});
        sixain.put("16-17-18-19-20-21", new ArrayList<>() {{
            add(new Coordonnees(635.5, 625.5, 642.5, 628.5));
            add(new Coordonnees(635.5, 432.0, 642.5, 435.0));
        }});
        sixain.put("19-20-21-22-23-24", new ArrayList<>() {{
            add(new Coordonnees(675.5, 625.5, 682.5, 628.5));
            add(new Coordonnees(675.5, 432.0, 682.5, 435.0));
        }});
        sixain.put("22-23-24-25-26-27", new ArrayList<>() {{
            add(new Coordonnees(715.5, 625.5, 722.5, 628.5));
            add(new Coordonnees(715.5, 432.0, 722.5, 435.0));
        }});
        sixain.put("25-26-27-28-29-30", new ArrayList<>() {{
            add(new Coordonnees(755.5, 625.5, 762.5, 628.5));
            add(new Coordonnees(755.5, 432.0, 762.5, 435.0));
        }});
        sixain.put("28-29-30-31-32-33", new ArrayList<>() {{
            add(new Coordonnees(795.5, 625.5, 802.5, 628.5));
            add(new Coordonnees(795.5, 432.0, 802.5, 435.0));
        }});
        sixain.put("31-32-33-34-35-36", new ArrayList<>() {{
            add(new Coordonnees(835.5, 625.5, 842.5, 628.5));
            add(new Coordonnees(835.5, 432.0, 842.5, 435.0));
        }});
    }

    static {
        cases = new LinkedHashMap<>();

        //Number
        cases.put("0", new ArrayList<>() {{
            add(new Coordonnees(356.5, 530.5, 356.5, 530.5));
        }});
        cases.put("1", new ArrayList<>() {{
            add(new Coordonnees(419.5, 594.5, 419.5, 594.5));
        }});
        cases.put("2", new ArrayList<>() {{
            add(new Coordonnees(419.5, 530.5, 419.5, 530.5));
        }});
        cases.put("3", new ArrayList<>() {{
            add(new Coordonnees(419.5, 464.5, 419.5, 464.5));
        }});
        cases.put("4", new ArrayList<>() {{
            add(new Coordonnees(460.5, 594.5, 460.5, 594.5));
        }});
        cases.put("5", new ArrayList<>() {{
            add(new Coordonnees(460.5, 530.5, 460.5, 530.5));
        }});
        cases.put("6", new ArrayList<>() {{
            add(new Coordonnees(460.5, 464.5, 460.5, 464.5));
        }});
        cases.put("7", new ArrayList<>() {{
            add(new Coordonnees(501.5, 594.5, 501.5, 594.5));
        }});
        cases.put("8", new ArrayList<>() {{
            add(new Coordonnees(501.5, 530.5, 501.5, 530.5));
        }});
        cases.put("9", new ArrayList<>() {{
            add(new Coordonnees(501.5, 464.5, 501.5, 464.5));
        }});
        cases.put("10", new ArrayList<>() {{
            add(new Coordonnees(542.5, 594.5, 542.5, 594.5));
        }});
        cases.put("11", new ArrayList<>() {{
            add(new Coordonnees(542.5, 530.5, 542.5, 530.5));
        }});
        cases.put("12", new ArrayList<>() {{
            add(new Coordonnees(542.5, 464.5, 542.5, 464.5));
        }});
        cases.put("13", new ArrayList<>() {{
            add(new Coordonnees(583.5, 594.5, 583.5, 594.5));
        }});
        cases.put("14", new ArrayList<>() {{
            add(new Coordonnees(583.5, 530.5, 583.5, 530.5));
        }});
        cases.put("15", new ArrayList<>() {{
            add(new Coordonnees(583.5, 464.5, 583.5, 464.5));
        }});
        cases.put("16", new ArrayList<>() {{
            add(new Coordonnees(624.5, 594.5, 624.5, 594.5));
        }});
        cases.put("17", new ArrayList<>() {{
            add(new Coordonnees(624.5, 530.5, 624.5, 530.5));
        }});
        cases.put("18", new ArrayList<>() {{
            add(new Coordonnees(624.5, 464.5, 624.5, 464.5));
        }});
        cases.put("19", new ArrayList<>() {{
            add(new Coordonnees(665.5, 594.5, 665.5, 594.5));
        }});
        cases.put("20", new ArrayList<>() {{
            add(new Coordonnees(665.5, 530.5, 665.5, 530.5));
        }});
        cases.put("21", new ArrayList<>() {{
            add(new Coordonnees(665.5, 464.5, 665.5, 464.5));
        }});
        cases.put("22", new ArrayList<>() {{
            add(new Coordonnees(706.5, 594.5, 706.5, 594.5));
        }});
        cases.put("23", new ArrayList<>() {{
            add(new Coordonnees(706.5, 530.5, 706.5, 530.5));
        }});
        cases.put("24", new ArrayList<>() {{
            add(new Coordonnees(706.5, 464.5, 706.5, 464.5));
        }});
        cases.put("25", new ArrayList<>() {{
            add(new Coordonnees(747.5, 594.5, 747.5, 594.5));
        }});
        cases.put("26", new ArrayList<>() {{
            add(new Coordonnees(747.5, 530.5, 747.5, 530.5));
        }});
        cases.put("27", new ArrayList<>() {{
            add(new Coordonnees(747.5, 464.5, 747.5, 464.5));
        }});
        cases.put("28", new ArrayList<>() {{
            add(new Coordonnees(788.5, 594.5, 788.5, 594.5));
        }});
        cases.put("29", new ArrayList<>() {{
            add(new Coordonnees(788.5, 530.5, 788.5, 530.5));
        }});
        cases.put("30", new ArrayList<>() {{
            add(new Coordonnees(788.5, 464.5, 788.5, 464.5));
        }});
        cases.put("31", new ArrayList<>() {{
            add(new Coordonnees(829.5, 594.5, 829.5, 594.5));
        }});
        cases.put("32", new ArrayList<>() {{
            add(new Coordonnees(829.5, 530.5, 829.5, 530.5));
        }});
        cases.put("33", new ArrayList<>() {{
            add(new Coordonnees(829.5, 464.5, 829.5, 464.5));
        }});
        cases.put("34", new ArrayList<>() {{
            add(new Coordonnees(870.5, 594.5, 870.5, 594.5));
        }});
        cases.put("35", new ArrayList<>() {{
            add(new Coordonnees(870.5, 530.5, 870.5, 530.5));
        }});
        cases.put("36", new ArrayList<>() {{
            add(new Coordonnees(870.5, 464.5, 870.5, 464.5));
        }});

        //Bottom
        cases.put("1 à 18", new ArrayList<>() {{
            add(new Coordonnees(437.5, 649.5, 437.5, 649.5));
        }});
        cases.put("impair", new ArrayList<>() {{
            add(new Coordonnees(520.5, 649.5, 520.5, 649.5));
        }});
        cases.put("rouge", new ArrayList<>() {{
            add(new Coordonnees(600.5, 649.5, 600.5, 649.5));
        }});
        cases.put("noir", new ArrayList<>() {{
            add(new Coordonnees(680.5, 649.5, 680.5, 649.5));
        }});
        cases.put("pair", new ArrayList<>() {{
            add(new Coordonnees(760.5, 649.5, 760.5, 649.5));
        }});
        cases.put("19 à 36", new ArrayList<>() {{
            add(new Coordonnees(840.5, 649.5, 840.5, 649.5));
        }});

        //Top
        cases.put("1-12", new ArrayList<>() {{
            add(new Coordonnees(477.5, 414, 477.5, 414));
        }});
        cases.put("13-24", new ArrayList<>() {{
            add(new Coordonnees(640.5, 414, 640.5, 414));
        }});
        cases.put("25-36", new ArrayList<>() {{
            add(new Coordonnees(803.5, 414, 803.5, 414));
        }});

        //Right
        cases.put("2pour1-1", new ArrayList<>() {{
            add(new Coordonnees(901.5, 594.5, 901.5, 594.5));
        }});
        cases.put("2pour1-2", new ArrayList<>() {{
            add(new Coordonnees(901.5, 530.5, 901.5, 530.5));
        }});
        cases.put("2pour1-3", new ArrayList<>() {{
            add(new Coordonnees(901.5, 464.5, 901.5, 464.5));
        }});
    }

    public static class Coordonnees {
        private double xStart;
        private double yStart;
        private double xEnd;
        private double yEnd;

        public Coordonnees(double xStart, double yStart, double xEnd, double yEnd) {
            this.xStart = xStart;
            this.yStart = yStart;
            this.xEnd = xEnd;
            this.yEnd = yEnd;
        }

        public double getxStart() {
            return xStart;
        }

        public double getyStart() {
            return yStart;
        }

        public double getxEnd() {
            return xEnd;
        }

        public double getyEnd() {
            return yEnd;
        }
    }
}
