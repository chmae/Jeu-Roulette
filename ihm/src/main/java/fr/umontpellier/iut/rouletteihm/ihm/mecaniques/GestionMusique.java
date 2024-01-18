package fr.umontpellier.iut.rouletteihm.ihm.mecaniques;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

/**
 * Classe permettant de gérer la musique
 */
public class GestionMusique {
    private MediaPlayer mediaPlayer;

    /**
     * Constructeur de la classe GestionMusique
     *
     * @param cheminFichier
     */
    public void setMusique(String cheminFichier) {
        Media sound = new Media(new File(cheminFichier).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
    }


    // Méthodes permettant de gérer la musique (lire, mettre en pause, arrêter, etc.)
    public void arreterMusique() {
        if (mediaPlayer != null) {
            final double fadeDurationInSeconds = 2.0;
            final double startVolume = mediaPlayer.getVolume(); // Volume de départ

            Timeline fadeOutTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(fadeDurationInSeconds), new KeyValue(mediaPlayer.volumeProperty(), 0))
            );

            fadeOutTimeline.setOnFinished(event -> {
                mediaPlayer.stop();
                mediaPlayer.setVolume(startVolume);
            });

            fadeOutTimeline.play();
        }
    }

    public void mettrePauseMusique() {
        if (mediaPlayer != null) {
            final double fadeDurationInSeconds = 2.0;
            final double startVolume = mediaPlayer.getVolume();

            Timeline fadeOutTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(fadeDurationInSeconds), new KeyValue(mediaPlayer.volumeProperty(), 0))
            );

            fadeOutTimeline.setOnFinished(event -> {
                mediaPlayer.pause();
                mediaPlayer.setVolume(startVolume);
            });

            fadeOutTimeline.play();
        }
    }

    public boolean getStatus() {
        return (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING);
    }

    public void lireMusique() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    public void lireMusiqueProgressivement(double maxValue) {
        if (mediaPlayer != null) {
            final double fadeDurationInSeconds = 3.0;
            final double targetVolume = maxValue;

            final double startVolume = mediaPlayer.getVolume();

            mediaPlayer.setVolume(0.0);

            Timeline fadeInTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(mediaPlayer.volumeProperty(), 0.0)),
                    new KeyFrame(Duration.seconds(fadeDurationInSeconds), new KeyValue(mediaPlayer.volumeProperty(), targetVolume))
            );

            fadeInTimeline.setOnFinished(event -> {
                mediaPlayer.setVolume(targetVolume);
            });

            mediaPlayer.setOnPlaying(() -> {
                if (mediaPlayer.getVolume() < targetVolume) {
                    mediaPlayer.setVolume(Math.min(targetVolume, mediaPlayer.getVolume() + 0.1));
                }
            });

            mediaPlayer.play();
            fadeInTimeline.play();

        }
    }


    public void mettreLaMusiqueEnBoucle(boolean activer) {
        if (mediaPlayer != null) {
            mediaPlayer.setCycleCount(activer ? MediaPlayer.INDEFINITE : 1);
        }
    }

    public void remettreMusiqueAuDebut() {
        if (mediaPlayer != null) {
            mediaPlayer.seek(Duration.ZERO); // Remettre la musique au début
        }
    }

    public double getVolume() {
        return mediaPlayer.getVolume();
    }

    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }
}

