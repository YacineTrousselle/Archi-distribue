package fr.frcsbcn.soup.proxy;

import android.util.Log;

import com.zeroc.Ice.ObjectPrx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.frcsbcn.soup.generated.SongData;
import fr.frcsbcn.soup.generated.SongDataModulePrx;

public class SongDataModuleProxy extends AbstractProxy {
    private static final String PROXY_NAME = "SongDataModule";

    public List<SongData> searchByTitle(String search) {
        try {
            ObjectPrx base = getBaseProxy(PROXY_NAME);
            SongDataModulePrx songDataModulePrx = SongDataModulePrx.checkedCast(base);
            SongData[] songsData = songDataModulePrx.searchByTitle(search);

            Log.d("SOUP", "searchByTitle: " + search + "  " + Arrays.toString(songsData));

            return Arrays.asList(songsData);
        } catch (Exception e) {
            Log.e("ICE", "searchByTitle: " + e.getMessage());
        }

        return new ArrayList<>();
    }
}
