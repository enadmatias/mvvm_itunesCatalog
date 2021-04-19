package com.mattdane.itunescatalog.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mattdane.itunescatalog.apis.TrackApi;
import com.mattdane.itunescatalog.model.SearchResponse;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrackRepository {

    private TrackApi trackApi;
    private MutableLiveData<SearchResponse> responseMutableLiveData;


    public TrackRepository() {
        responseMutableLiveData = new MutableLiveData<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        trackApi = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TrackApi.class);

    }


    public void searchTrack(String trackName, String media, String country) {
        trackApi.getArtistDetails(trackName, media, country)
              .enqueue(new Callback<SearchResponse>() {
                  @Override
                  public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                      if (response.body() != null) {
                          responseMutableLiveData.postValue(response.body());
                      }
                  }

                  @Override
                  public void onFailure(Call<SearchResponse> call, Throwable t) {
                          responseMutableLiveData.postValue(null);
                  }
              });
    }
    public LiveData<SearchResponse> getSearchResponseLiveData() {
        return responseMutableLiveData;
    }


}
