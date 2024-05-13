package fr.frcsbcn.soup.service;

public class CallApiService {

    public static callApi() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse> call = apiInterface.sendData(requestBody);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    ApiResponse apiResponse = response.body();
                    Log.d(TAG, "API call successful: " + apiResponse.getMessage());
                } else {
                    // Handle unsuccessful response
                    Log.e(TAG, "API call failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Handle failure
                Log.e(TAG, "API call failed: " + t.getMessage());
            }
        });
    }

}
