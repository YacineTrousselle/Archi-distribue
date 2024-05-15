package fr.frcsbcn.soup.service;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class AsrService {
    public String callApi(File audioFile) throws IOException, SocketTimeoutException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .callTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8020/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        AsrApi asrApi = retrofit.create(AsrApi.class);

        MultipartBody.Part filePart = MultipartBody.Part.createFormData(
                "audio",
                audioFile.getName(),
                RequestBody.create(MediaType.parse("audio/mp3"), audioFile)
        );

        Call<String> call = asrApi.speechToText(filePart);
        Response<String> response = call.execute();

        return response.isSuccessful() ? response.body() : null;
    }
}

interface AsrApi {
    @Multipart
    @POST("speech-to-text")
    Call<String> speechToText(@Part MultipartBody.Part file);
}
