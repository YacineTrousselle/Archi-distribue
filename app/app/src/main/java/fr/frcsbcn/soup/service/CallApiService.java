package fr.frcsbcn.soup.service;

import android.app.Activity;
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

            String log = "Unknown action";
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
                    if (null == response.song_scores || null == response.song_scores.get(0)) {
                        log = "No music found";
                        Log.d("SOUP", "callApi: No music found");
                        break;
                    }
                    Log.d("SOUP", "callApi: " + response.song_scores.get(0)[0]);

                    log = "Search music";
                    Bundle bundle = new Bundle();
                    bundle.putString("id", (String) response.song_scores.get(0)[0]);
                    Navigation
                            .findNavController(activity, R.id.nav_host_fragment_content_main)
                            .navigate(R.id.nav_player, bundle);
                    break;
                case "up":
                    Log.d("SOUP", "callApi: up");
                    log = "Increase volume";
                    audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
                    break;
                case "down":
                    Log.d("SOUP", "callApi: down");
                    log = "Decrease volume";
                    audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
                    audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
                    break;
            }

            return log;
        } catch (IOException e) {
            Log.e("SOUP", "callApi", e);
            throw e;
        }
    }

}
