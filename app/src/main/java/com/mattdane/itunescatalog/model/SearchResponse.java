package com.mattdane.itunescatalog.model;

import com.google.gson.annotations.SerializedName;
import com.mattdane.itunescatalog.model.ArtistModel;

import java.util.List;

public class SearchResponse {
    @SerializedName("resultCount")
    public String resultCount;
    @SerializedName("results")
    public List<ArtistModel> results;
}
