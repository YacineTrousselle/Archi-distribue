package fr.frcsbcn.soup.ui.upload;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UploadViewModel extends ViewModel {
    private MutableLiveData<byte[]> data = new MutableLiveData<>();
    private MutableLiveData<String> selectedFilePath = new MutableLiveData<>(null);

    public byte[] getData() {
        return data.getValue();
    }

    public void setData(byte[] data) {
        this.data.setValue(data);
    }

    public void setSelectedFile(String selectedFilePath) {
        Log.d("SOUP", "getSelectedFile: " + selectedFilePath);

        this.selectedFilePath.setValue(selectedFilePath);
    }

    public String getSelectedFile() {
        return selectedFilePath.getValue();
    }
}