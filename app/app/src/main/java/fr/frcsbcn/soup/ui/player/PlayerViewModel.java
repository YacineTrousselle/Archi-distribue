package fr.frcsbcn.soup.ui.player;

import android.content.Context;

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
        if (null != audioPlayerProxy.getValue()) {
            audioPlayerProxy.getValue().getPlayer().play();
        }
    }

    public void pause() {
        if (null != audioPlayerProxy.getValue()) {
            audioPlayerProxy.getValue().getPlayer().pause();
        }
    }

    public void clear() {
        if (audioPlayerProxy.getValue() != null) {
            audioPlayerProxy.getValue().close();
            audioPlayerProxy.setValue(null);
        }
    }
}