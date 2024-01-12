package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.plateau;


import java.util.*;

public class setNombres {

    private List<Nombres> numberSet = new ArrayList<Nombres>();

    public setNombres() {
        numberSet.add(new Nombres(0, "V", 180));
        numberSet.add(new Nombres(1, "R", 44));
        numberSet.add(new Nombres(2, "B", 238));
        numberSet.add(new Nombres(3, "R", 160));
        numberSet.add(new Nombres(4, "B", 219));
        numberSet.add(new Nombres(5, "R", 5));
        numberSet.add(new Nombres(6, "B", 277));
        numberSet.add(new Nombres(7, "R", 122));
        numberSet.add(new Nombres(8, "B", 336));
        numberSet.add(new Nombres(9, "R", 83));
        numberSet.add(new Nombres(10, "B", 355));
        numberSet.add(new Nombres(11, "B", 316));
        numberSet.add(new Nombres(12, "R", 141));
        numberSet.add(new Nombres(13, "B", 297));
        numberSet.add(new Nombres(14, "R", 63));
        numberSet.add(new Nombres(15, "B", 199));
        numberSet.add(new Nombres(16, "R", 24));
        numberSet.add(new Nombres(17, "B", 258));
        numberSet.add(new Nombres(18, "R", 102));
        numberSet.add(new Nombres(19, "R", 209));
        numberSet.add(new Nombres(20, "B", 54));
        numberSet.add(new Nombres(21, "R", 229));
        numberSet.add(new Nombres(22, "B", 92));
        numberSet.add(new Nombres(23, "R", 345));
        numberSet.add(new Nombres(24, "B", 15));
        numberSet.add(new Nombres(25, "R", 248));
        numberSet.add(new Nombres(26, "B", 170));
        numberSet.add(new Nombres(27, "R", 287));
        numberSet.add(new Nombres(28, "B", 131));
        numberSet.add(new Nombres(29, "B", 112));
        numberSet.add(new Nombres(30, "R", 326));
        numberSet.add(new Nombres(31, "B", 73));
        numberSet.add(new Nombres(32, "R", 190));
        numberSet.add(new Nombres(33, "B", 34));
        numberSet.add(new Nombres(34, "R", 268));
        numberSet.add(new Nombres(35, "B", 151));
        numberSet.add(new Nombres(36, "R", 306));
    }

    public List<Nombres> getNumberSet() {
        return numberSet;
    }

    public int getAngle(int num) {
        for (Nombres n : numberSet) if (n.getNumber() == num) return n.getAngle();
        return 0;
    }
}