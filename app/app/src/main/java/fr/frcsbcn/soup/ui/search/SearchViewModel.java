package fr.frcsbcn.soup.ui.search;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import fr.frcsbcn.soup.generated.SongData;
import fr.frcsbcn.soup.proxy.SongDataModuleProxy;

public class SearchViewModel extends ViewModel {

    private final SongDataModuleProxy songDataModuleProxy = new SongDataModuleProxy();
    private final MutableLiveData<String> search = new MutableLiveData<>();
    private final MutableLiveData<List<SongData>> songs = new MutableLiveData<>();

    public void setSearch(String search) {
        this.search.setValue(search);
    }

    public LiveData<String> getSearch() {
        return search;
    }

    public LiveData<List<SongData>> getSongs() {
        return songs;
    }

    public void fetchData() {
        Log.d("SOUP", "fetchData");
        songs.setValue(songDataModuleProxy.searchByTitle(search.getValue()));
    }
}