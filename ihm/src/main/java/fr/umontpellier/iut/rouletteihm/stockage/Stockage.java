package fr.umontpellier.iut.rouletteihm.stockage;

import java.util.List;

/**
 * Interface générique pour le stockage des données
 * @param <T> Type des éléments stockés
 */
public interface Stockage<T> {

    public void create(T element);

    public void update(T element);

    public void deleteById(int id);

    public T getById(int id);

    public List<T> getAll();

    public T findByEmail(String email);
}


