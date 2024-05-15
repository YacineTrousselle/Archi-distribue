package fr.frcsbcn.soup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import fr.frcsbcn.soup.databinding.ActivityMainBinding;
import fr.frcsbcn.soup.service.CallApiService;
import fr.frcsbcn.soup.ui.player.PlayerViewModel;

public class MainActivity extends AppCompatActivity {

    public static Communicator COMMUNICATOR = null;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private MediaRecorder mediaRecorder;
    private String outputFile;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        COMMUNICATOR = Util.initialize();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FloatingActionButton fab = binding.getRoot().findViewById(R.id.fab);
        fab.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.RECORD_AUDIO}, 10);
                } else {
                    startRecording();
                    Toast.makeText(this, mediaRecorder == null ? "An error occured: can't record" : "Recording...", Toast.LENGTH_SHORT).show();
                }
            }
            if (event.getAction() == MotionEvent.ACTION_UP && null != mediaRecorder) {
                stopRecording();
                Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
            }

            return false;
        });

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_search, R.id.nav_upload)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != COMMUNICATOR) {
            COMMUNICATOR.shutdown();
        }
    }

    private void startRecording() {
        outputFile = getExternalCacheDir().getAbsolutePath() + "/recording.mp3";
        mediaRecorder = new MediaRecorder(this);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediaRecorder.setOutputFile(outputFile);
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            Log.e("SOUP", "startRecording: ", e);
            mediaRecorder = null;

            return;
        }
        mediaRecorder.start();
    }

    private void stopRecording() {
        try {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            File file = new File(outputFile);
            if (file.exists()) {
                try {
                    sendAudioToApi(file);
                } catch (Exception e) {
                    Toast.makeText(this, "An error occured when calling the api", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Record file not found", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
        }
    }

    private void sendAudioToApi(File audioFile) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        PlayerViewModel playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);

        Future<String> future = executor.submit(() -> new CallApiService(
                (AudioManager) getSystemService(Context.AUDIO_SERVICE),
                playerViewModel,
                this
        ).callApi(audioFile));

        executor.execute(() -> {
            try {
                String result = future.get();
                Log.d("SOUP", "API Response: " + result);
                Looper.prepare();
                Toast.makeText(MainActivity.this, "API response: " + result, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e("SOUP", "Error while processing audio: ", e);
            }
        });
    }
}