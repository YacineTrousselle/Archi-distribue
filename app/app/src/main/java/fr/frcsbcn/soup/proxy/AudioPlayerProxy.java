package fr.frcsbcn.soup.proxy;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.zeroc.Ice.ObjectPrx;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.util.Objects;

import fr.frcsbcn.soup.generated.AudioPlayerPrx;

public class AudioPlayerProxy extends AbstractProxy {
    private static final String PROXY_NAME = "AudioPlayer";
    private MediaPlayer player;

    private boolean isFinished = false;
    private String rtspUrl;

    public AudioPlayerProxy(String songId, Context context) {
        try {
            ObjectPrx base = getBaseProxy(PROXY_NAME);
            AudioPlayerPrx audioPlayerPrx = AudioPlayerPrx.checkedCast(base);
            rtspUrl = audioPlayerPrx.initializeAudioPlayer(songId);
            audioPlayerPrx.play(rtspUrl);
            Log.d("SOUP", "RTSP Url: " + rtspUrl.replace("127.0.0.1", "10.0.2.2"));

            LibVLC libVLC = new LibVLC(context);
            player = new MediaPlayer(libVLC);
            Media media = new Media(libVLC, Uri.parse(rtspUrl.replace("0.0.0.0", "10.0.2.2")));
            player.setMedia(media);

        } catch (Exception e) {
            Log.e("ICE", Objects.requireNonNull(e.getMessage()));
        }
    }

    public void playPause() {
        try {
            ObjectPrx base = getBaseProxy(PROXY_NAME);
            AudioPlayerPrx audioPlayerPrx = AudioPlayerPrx.checkedCast(base);

            if (player.isPlaying()) {
                audioPlayerPrx.pause(rtspUrl);
            } else {
                audioPlayerPrx.play(rtspUrl);
            }
        } catch (Exception e) {
            Log.e("ICE","ice error: " + e.getMessage());
        }
    }

    public void play() {
        try {
            ObjectPrx base = getBaseProxy(PROXY_NAME);
            AudioPlayerPrx audioPlayerPrx = AudioPlayerPrx.checkedCast(base);
            audioPlayerPrx.play(rtspUrl);
        } catch (Exception e) {
            Log.e("ICE","ice error: " + e.getMessage());
        }
    }

    public void pause() {
        try {
            ObjectPrx base = getBaseProxy(PROXY_NAME);
            AudioPlayerPrx audioPlayerPrx = AudioPlayerPrx.checkedCast(base);
            audioPlayerPrx.pause(rtspUrl);
        } catch (Exception e) {
            Log.e("ICE","ice error: " + e.getMessage());
        }
    }

    public void close() {
        player.release();
        try {
            ObjectPrx base = getBaseProxy(PROXY_NAME);
            AudioPlayerPrx audioPlayerPrx = AudioPlayerPrx.checkedCast(base);
            audioPlayerPrx.close(rtspUrl);
        } catch (Exception e) {
            Log.e("ICE","ice error: " + e.getMessage());
        }
    }

    public MediaPlayer getPlayer() {
        return player;
    }

    public boolean isPlaying() {
        return player.isPlaying();
    }

    public boolean isFinished() {
        return isFinished;
    }
}
