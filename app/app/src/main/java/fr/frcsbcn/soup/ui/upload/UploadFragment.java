package fr.frcsbcn.soup.ui.upload;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import fr.frcsbcn.soup.R;
import fr.frcsbcn.soup.databinding.FragmentUploadBinding;
import fr.frcsbcn.soup.generated.SongData;
import fr.frcsbcn.soup.proxy.FileUploaderProxy;

public class UploadFragment extends Fragment {
    private static final int REQUEST_CODE_SELECT_FILE = 123;

    private EditText titleEditText;
    private EditText artistsEditText;
    private Button selectFileButton;
    private Button submitButton;

    private FragmentUploadBinding binding;
    private UploadViewModel uploadViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        uploadViewModel = new ViewModelProvider(this).get(UploadViewModel.class);

        binding = FragmentUploadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        titleEditText = root.findViewById(R.id.titleEdit);
        artistsEditText = root.findViewById(R.id.artistsEdit);
        selectFileButton = root.findViewById(R.id.selectFileButton);
        submitButton = root.findViewById(R.id.submitButton);

        selectFileButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("audio/*");
            startActivityForResult(Intent.createChooser(intent, "Select a MP3 file"), REQUEST_CODE_SELECT_FILE);
        });

        submitButton.setOnClickListener(v -> {
            if (titleEditText.getText().toString().trim().isEmpty() || artistsEditText.getText().toString().trim().isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
                return;
            }
            if (uploadViewModel.getData() == null || uploadViewModel.getData().length == 0) {
                Toast.makeText(requireContext(), "Please select a file", Toast.LENGTH_LONG).show();
                return;
            }
            SongData songData = new SongData();
            songData.title = titleEditText.getText().toString().trim();
            songData.artists = artistsEditText.getText().toString().trim().split(",");

            FileUploaderProxy fileUploaderProxy = new FileUploaderProxy();
            fileUploaderProxy.uploadMusic(songData, uploadViewModel.getData());
            Toast.makeText(requireContext(), "Music uploaded", Toast.LENGTH_LONG).show();

            Navigation.findNavController(root).navigate(R.id.nav_home);
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SELECT_FILE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedFileUri = data.getData();
                Log.d("SOUP", "onActivityResult: " + selectedFileUri);
                try {
                    InputStream inputStream = getContext().getContentResolver().openInputStream(selectedFileUri);
                    byte[] inputData = getBytes(inputStream);
                    uploadViewModel.setData(inputData);
                } catch (IOException e) {
                    Log.e("SOUP", "onActivityResult", e);
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        return byteBuffer.toByteArray();
    }
}