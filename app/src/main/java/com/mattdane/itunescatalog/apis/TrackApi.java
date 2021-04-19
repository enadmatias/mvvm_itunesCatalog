package com.mattdane.itunescatalog.apis;

import com.mattdane.itunescatalog.model.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TrackApi {


    @GET("search")
    Call<SearchResponse> getArtistDetails(
            @Query("term") String  trackName,
            @Query("media") String  media,
            @Query("country") String  country
    );
}
