package fr.frcsbcn.soup.service;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;

import androidx.navigation.Navigation;

import java.io.File;
import java.io.IOException;

import fr.frcsbcn.soup.R;
import fr.frcsbcn.soup.ui.player.PlayerViewModel;

public class CallApiService {
    private final AudioManager audioManager;
    private final PlayerViewModel playerViewModel;
    private final Activity activity;
    private final AsrService asrService = new AsrService();
    private final NlpService nlpService = new NlpService();

    public CallApiService(AudioManager audioManager, PlayerViewModel playerViewModel, Activity activity) {
        this.audioManager = audioManager;
        this.playerViewModel = playerViewModel;
        this.activity = activity;
    }

    public String callApi(File audioFile) throws IOException {
        try {
            String content = asrService.callApi(audioFile);
            Log.d("SOUP", "callApi: " + content);
            if (null == content) {
                throw new IOException("Error occured when ASR API was called");
            }

            NlpService.NlpResponse response = nlpService.callApi(content);
            Log.d("SOUP", "callApi: " + response);
            if (null == response) {
                throw new IOException("Error occured when NLP API was called");
            }

            String log = "Action non reconnue";
            switch (response.action) {
                case "play":
                    Log.d("SOUP", "callApi: play");
                    log = "Play";
                    playerViewModel.play();
                    break;
                case "pause":
                    Log.d("SOUP", "callApi: pause");
                    log = "Pause";
                    playerViewModel.pause();
                    break;
                case "search":
                    Log.d("SOUP", "callApi: search");
                    Log.d("SOUP", "callApi: " + response.song_scores.get(0));
                    log = "Search music";
//                    Bundle bundle = new Bundle();
//                    bundle.putString("id", response.song_scores.get(0));
//                    Navigation
//                            .findNavController(activity, R.id.nav_home)
//                            .navigate(R.id.nav_player, bundle);
                    break;
                case "up":
                    Log.d("SOUP", "callApi: up");
                    log = "Monter le son";
                    audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                    break;
                case "down":
                    Log.d("SOUP", "callApi: down");
                    log = "Baisser le son";
                    audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
                    break;
            }

            return log;
        } catch (IOException e) {
            Log.e("SOUP", "callApi", e);
            throw e;
        }
    }

}
