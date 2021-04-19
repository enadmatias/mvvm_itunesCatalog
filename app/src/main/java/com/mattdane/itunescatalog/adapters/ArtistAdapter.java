package com.mattdane.itunescatalog.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mattdane.itunescatalog.R;
import com.mattdane.itunescatalog.model.ArtistModel;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {
    Context context;
    List<ArtistModel> artistModels;

    public ArtistAdapter(Context context, List<ArtistModel> artistModels) {
        this.context = context;
        this.artistModels = artistModels;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_artist_list, parent, false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     ArtistModel artistModel = artistModels.get(position);

       String price = String.valueOf(artistModel.collectionPrice);
        holder.trackTXT.setText(artistModel.trackName);
        holder.artistTXT.setText(artistModel.artistName);
        holder.genreTXT.setText(artistModel.primaryGenreName);
        holder.priceTXT.setText(price);
        Glide.with(context)
                .load(artistModel.artworkUrl100)
                .apply(new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.no_photo)
                        .error(R.drawable.no_photo))
                .into(holder.artWorkIMG);
    }

    @Override
    public int getItemCount() {
        return artistModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView artWorkIMG;
        TextView trackTXT;
        TextView artistTXT;
        TextView genreTXT;
        TextView priceTXT;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            artWorkIMG = itemView.findViewById(R.id.artWorkIMG);
            trackTXT = itemView.findViewById(R.id.trackTXT);
            artistTXT = itemView.findViewById(R.id.artistTXT);
            genreTXT = itemView.findViewById(R.id.genreTXT);
            priceTXT = itemView.findViewById(R.id.priceTXT);

            artWorkIMG.setClipToOutline(true);
        }
    }
}
