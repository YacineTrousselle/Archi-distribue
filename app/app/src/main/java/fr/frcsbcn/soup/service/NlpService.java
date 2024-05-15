package fr.frcsbcn.soup.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NlpService {
    public NlpResponse callApi(String content) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .callTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8010/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        NlpApi nlpApi = retrofit.create(NlpApi.class);

        Call<NlpResponse> call = nlpApi.process(content);
        Response<NlpResponse> response = call.execute();

        return response.isSuccessful() ? response.body() : null;
    }

    public static class NlpResponse {
        public String action = "unknown";
        public List<Object[]> song_scores;
    }
}

interface NlpApi {
    @GET("/process")
    Call<NlpService.NlpResponse> process(@Query("data") String data);
}
