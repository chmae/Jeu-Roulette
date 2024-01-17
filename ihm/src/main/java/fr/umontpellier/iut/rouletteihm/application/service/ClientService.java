package fr.umontpellier.iut.rouletteihm.application.service;

import com.gasquet.hrepositories.api.EntityRepository;
import com.gasquet.hrepositories.utils.DBUtils;
import com.gasquet.hrepositories.utils.RepositoryManager;
import fr.umontpellier.iut.rouletteihm.application.controller.client.ControllerClient;
import fr.umontpellier.iut.rouletteihm.application.service.chiffrement.HasheurSHA256;
import fr.umontpellier.iut.rouletteihm.application.service.exception.ServiceException;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Joueur;
import fr.umontpellier.iut.rouletteihm.metier.entite.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class ClientService {
    /**
     * Classe de service qui permet de gérer différents étudiants
     * Singleton
     */

    private final static ClientService INSTANCE = new ClientService();
    private HasheurSHA256 hasher = new HasheurSHA256();
    private EntityRepository<Client> repository = RepositoryManager.getRepository(Client.class);

    private ClientService() {
    }

    public static ClientService getInstance() {
        return INSTANCE;
    }

    /**
     * Instancie un objet {@link Client} puis le sauvegarde dans la source de données via le repository
     *
     * @param nom             : Nom du client
     * @param prenom          : Prénom du client
     * @param email           : Email du client
     * @param password        : Mot de passe du client
     * @param paswwordConfirm : Confirmation du mot de passe du client
     */
    public int createClient(String nom, String prenom, String email, String password, String paswwordConfirm, int solde) throws ServiceException {
        String motDePasseChiffre = hasher.hasherSHA256(password);

        if (!motDePasseChiffre.equals(hasher.hasherSHA256(paswwordConfirm))) {
            throw new ServiceException("Les mots de passe ne correspondent pas.");
        }

        try {
            Client client = new Client(nom, prenom, email, motDePasseChiffre, motDePasseChiffre, solde);
            repository.create(client);

            return client.getIdClient();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Erreur lors de la création du client.");
        }
    }


    /**
     * Récupère une instance de {@link Client} depuis la source de données, met à jour son nom, son prénom,
     * son email, son password et son paswwordConfirm pas puis enregistre la mise à jour de l'entité via le repository.
     *
     * @param nom             : Nom du client
     * @param prenom          : Prénom du client
     * @param email           : Email du client
     * @param password        : Mot de passe du client
     * @param paswwordConfirm : Confirmation du mot de passe du client
     */
    public void updateClient(int idClient, String nom, String prenom, String email, String password, String paswwordConfirm, int solde) {
        Client client = repository.findByID(idClient);
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setEmail(email);

        if (!client.getPassword().equals(password)) {
            String motDePasseChiffre = hasher.hasherSHA256(password);
            client.setPassword(motDePasseChiffre);
        }
        if (!client.getPaswwordConfirm().equals(paswwordConfirm)) {
            String motDePasseConfirmChiffre = hasher.hasherSHA256(paswwordConfirm);
            client.setPaswwordConfirm(motDePasseConfirmChiffre);
        }

        client.setSolde(solde);

        System.out.println("Client mis à jour : " + client);

        repository.update(client);
    }

    /**
     * Met à jour le solde du joueur après une partie.
     *
     * @param idClient     L'identifiant du joueur.
     * @param nouveauSolde Le montant gagné par le joueur (positif) ou perdu (négatif).
     */
    public void mettreAJourSolde(int idClient, int nouveauSolde) throws ServiceException {
        try {
            if (idClient <= 0) {
                throw new ServiceException("ID client incorrect pour la mise à jour du solde.");
            }

            Session session = DBUtils.getSession();
            Transaction transaction = session.beginTransaction();

            Client client = repository.findByID(idClient);
            if (client != null) {
                client.setSolde(nouveauSolde);
                repository.update(client);
            } else {
                throw new ServiceException("Client non trouvé pour l'ID : " + idClient);
            }

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur lors de la mise à jour du solde.");
        }
    }

    /**
     * Met à jour le prénom d'un client dans la base de données.
     *
     * @param idClient      L'identifiant du client dont on veut mettre à jour le prénom.
     * @param nouveauPrenom Le nouveau prénom à enregistrer.
     * @throws ServiceException Si une erreur survient lors de la mise à jour du prénom.
     */
    public void updatePrenom(int idClient, String nouveauPrenom) throws ServiceException {
        try (Session session = DBUtils.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Client client = repository.findByID(idClient);
                if (client != null) {
                    client.setPrenom(nouveauPrenom);
                    repository.update(client);
                    transaction.commit();
                } else {
                    throw new ServiceException("Client non trouvé pour l'ID : " + idClient);
                }
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
                throw new ServiceException("Erreur lors de la mise à jour du prénom.");
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur lors de la création de la session Hibernate.");
        }
    }

    /**
     * Récupère le solde d'un client depuis la base de données.
     *
     * @param clientId L'identifiant du client dont on veut récupérer le solde.
     * @return Le solde du client.
     * @throws ServiceException Si une erreur survient lors de la récupération du solde.
     */
    public int getSolde(int clientId) throws ServiceException {
        try (Session session = DBUtils.getSession()) {
            Client client = session.get(Client.class, clientId);
            if (client != null) {
                return client.getSolde();
            } else {
                throw new ServiceException("Client non trouvé avec l'ID : " + clientId);
            }
        } catch (HibernateException e) {
            throw new ServiceException("Erreur lors de la récupération du solde depuis la base de données.");
        }
    }


    /**
     * Supprime un {@link Client} sur la source de données via le repository
     *
     * @param idClient : identifiant du client à supprimer
     */
    public void deleteEtudiant(int idClient) {
        repository.deleteById(idClient);
    }

    /**
     * Récupère une instance d'un {@link Client} depuis la source de données via le repository
     *
     * @param idClient : identifiant du client à récupérer
     * @return L'instance de {@link Client} correspondant à l'identifiant
     */
    public Client getClient(int idClient) {
        return repository.findByID(idClient);
    }

    /**
     * Récupère une liste de tous les clients depuis la source de données via le repository
     *
     * @return La liste de tous les clients.
     */
    public List<Client> getClients() {
        return repository.findAll();
    }


}


