package com.mattdane.itunescatalog.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import android.app.Application;

import com.mattdane.itunescatalog.model.SearchResponse;
import com.mattdane.itunescatalog.repository.TrackRepository;

public class TrackViewModel extends AndroidViewModel {
    private TrackRepository trackRepository;
    private LiveData<SearchResponse> searchResponseLiveData;

    public TrackViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        trackRepository = new TrackRepository();
        searchResponseLiveData = trackRepository.getSearchResponseLiveData();
    }

    public void searchTrack(String keyword, String media, String country) {
        trackRepository.searchTrack(keyword,media,country);
    }

    public LiveData<SearchResponse> getVolumesResponseLiveData() {
        return searchResponseLiveData;
    }

}
