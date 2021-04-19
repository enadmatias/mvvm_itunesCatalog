package com.mattdane.itunescatalog.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mattdane.itunescatalog.R;
import com.mattdane.itunescatalog.adapters.ArtistAdapter;
import com.mattdane.itunescatalog.model.ArtistModel;
import com.mattdane.itunescatalog.viewmodel.TrackViewModel;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    List<ArtistModel> artistModelList = new ArrayList<>();
    ArtistAdapter artistAdapter;
    TrackViewModel trackViewModel;
    RecyclerView artistRV;
    TextView totalTXT;
    EditText searchEDT;
    Spinner mediaSpinner;
    Spinner countrySpinner;
    Button searchBTN;
    View resultVIEW;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        artistRV = findViewById(R.id.artistRV);
        totalTXT = findViewById(R.id.totalTXT);
        searchEDT = findViewById(R.id.searchEDT);
        countrySpinner = findViewById(R.id.countrySpinner);
        mediaSpinner = findViewById(R.id.mediaSpinner);
        searchBTN = findViewById(R.id.searchBTN);
        resultVIEW = findViewById(R.id.resultVIEW);
        searchBTN.setOnClickListener(this);

        // Spinner Drop down elements for media
        List<String> categories = new ArrayList<String>();
        categories.add("all");
        categories.add("movie");
        categories.add("podcast");
        categories.add("music");
        categories.add("musicVideo");
        categories.add("audiobook");
        categories.add("shortFilm");
        categories.add("tvShow");
        categories.add("software");
        categories.add("ebook");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        mediaSpinner.setAdapter(dataAdapter);


        // Spinner Drop down elements for media
        List<String> country = new ArrayList<String>();
        country.add("au");
        country.add("nz");
        country.add("ph");
        categories.add("us");


        // Creating adapter for spinner
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, country);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        countrySpinner.setAdapter(countryAdapter);

        trackViewModel = ViewModelProviders.of(this).get(TrackViewModel.class);
        trackViewModel.init();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (artistAdapter == null) {
            artistAdapter = new ArtistAdapter(MainActivity.this, artistModelList);
            artistRV.setLayoutManager(new LinearLayoutManager(this));
            artistRV.setAdapter(artistAdapter);
            artistRV.setNestedScrollingEnabled(true);
        } else {
            artistAdapter.notifyDataSetChanged();
        }

    }

    public String getValue(){
        String value="";
        if(mediaSpinner.getSelectedItem().equals("all")){
            value  = "all";
        }else if(mediaSpinner.getSelectedItem().equals("movie")){
            value = "movie";
        }
        else if(mediaSpinner.getSelectedItem().equals("podcast")){
            value = "podcast";
        }
        else if(mediaSpinner.getSelectedItem().equals("audiobook")){
            value = "audiobook";
        }
        else if(mediaSpinner.getSelectedItem().equals("shortFilm")){
            value = "shortFilm";
        }
        else if(mediaSpinner.getSelectedItem().equals("tvShow")){
            value = "tvShow";
        }
        else if(mediaSpinner.getSelectedItem().equals("software")){
            value = "software";
        }
        else if(mediaSpinner.getSelectedItem().equals("ebook")){
            value = "ebook";
        }
        return  value;
    }
    public String getCountry(){
        String value = "";
        if(countrySpinner.getSelectedItem().equals("au")){
            value  = "au";
        }else if(mediaSpinner.getSelectedItem().equals("nz")){
            value = "nz";
        }
        else if(mediaSpinner.getSelectedItem().equals("ph")){
            value = "ph";
        }
        else if(mediaSpinner.getSelectedItem().equals("us")){
            value = "us";
        }
        return  value;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.searchBTN:
                 if(!searchEDT.getText().toString().isEmpty() ){
                     dialog("Searching Track ...");
                     artistModelList.clear();
                     trackViewModel.searchTrack(searchEDT.getText().toString(),getValue(),getCountry());
                     trackViewModel.getVolumesResponseLiveData().observe(this, trackResponse -> {
                         List<ArtistModel> artistModels = trackResponse.results;
                         totalTXT.setText("Results("+ artistModels.size()+")");
                         if(artistModels.size() >0) {
                             progressDialog.dismiss();
                             artistRV.setVisibility(View.VISIBLE);
                             resultVIEW.setVisibility(View.GONE);
                             artistModelList.addAll(artistModels);
                             artistAdapter.notifyDataSetChanged();
                         }
                         else {
                             progressDialog.dismiss();;
                             resultVIEW.setVisibility(View.VISIBLE);
                         }
                     });
                 }
                 else if(searchEDT.getText().toString().isEmpty()){
                     searchEDT.setError("Field is Required!");
                 }

                break;
        }

    }

    public void dialog(String message){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage(message);
        progressDialog.show();
    }
}