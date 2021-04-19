package com.mattdane.itunescatalog.model;

import com.google.gson.annotations.SerializedName;

public class ArtistModel {


    @SerializedName("wrapperType")
    public String wrapperType;
    @SerializedName("kind")
    public String kind;
    @SerializedName("trackId")
    public int trackId;
    @SerializedName("artistName")
    public String artistName;
    @SerializedName("trackName")
    public String trackName;
    @SerializedName("trackCensoredName")
    public String trackCensoredName;
    @SerializedName("trackViewUrl")
    public String trackViewUrl;
    @SerializedName("previewUrl")
    public String previewUrl;
    @SerializedName("artworkUrl30")
    public String artworkUrl30;
    @SerializedName("artworkUrl60")
    public String artworkUrl60;
    @SerializedName("artworkUrl100")
    public String artworkUrl100;
    @SerializedName("collectionPrice")
    public double collectionPrice;
    @SerializedName("trackPrice")
    public double trackPrice;
    @SerializedName("trackRentalPrice")
    public double trackRentalPrice;
    @SerializedName("collectionHdPrice")
    public double collectionHdPrice;
    @SerializedName("trackHdPrice")
    public double trackHdPrice;
    @SerializedName("trackHdRentalPrice")
    public double trackHdRentalPrice;
    @SerializedName("releaseDate")
    public String releaseDate;
    @SerializedName("collectionExplicitness")
    public String collectionExplicitness;
    @SerializedName("trackExplicitness")
    public String trackExplicitness;
    @SerializedName("trackTimeMillis")
    public int trackTimeMillis;
    @SerializedName("country")
    public String country;
    @SerializedName("currency")
    public String currency;
    @SerializedName("primaryGenreName")
    public String primaryGenreName;
    @SerializedName("contentAdvisoryRating")
    public String contentAdvisoryRating;
    @SerializedName("shortDescription")
    public String shortDescription;
    @SerializedName("longDescription")
    public String longDescription;
    @SerializedName("hasITunesExtras")
    public boolean hasITunesExtras;
}
