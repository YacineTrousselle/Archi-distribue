package fr.frcsbcn.soup.ui.player;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import fr.frcsbcn.soup.proxy.AudioPlayerProxy;

public class PlayerViewModel extends ViewModel {
    MutableLiveData<AudioPlayerProxy> audioPlayerProxy;

    public PlayerViewModel() {
        audioPlayerProxy = new MutableLiveData<>(null);
    }

    public void initAudioPlayerProxy(String id, Context context) {
        audioPlayerProxy.setValue(new AudioPlayerProxy(id, context));
        audioPlayerProxy.getValue().getPlayer().play();
    }

    public AudioPlayerProxy getAudioPlayerProxy() {
        return audioPlayerProxy.getValue();
    }

    public void play() {
        Log.d("SOUP", "play + " + (null != audioPlayerProxy.getValue()));
        if (null != audioPlayerProxy.getValue()) {
            audioPlayerProxy.getValue().play();
        }
    }

    public void pause() {
        Log.d("SOUP", "pause + " + (null != audioPlayerProxy.getValue()));
        if (null != audioPlayerProxy.getValue()) {
            audioPlayerProxy.getValue().pause();
        }
    }

    public void clear() {
        if (audioPlayerProxy.getValue() != null) {
            audioPlayerProxy.getValue().close();
            audioPlayerProxy.setValue(null);
        }
    }
}