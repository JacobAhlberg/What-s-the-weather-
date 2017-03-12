package com.ahlberg.jacob.whatstheweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.ahlberg.jacob.whatstheweather.model.Favourites;

import java.util.ArrayList;

/**
 * Created by Jacob on 2017-03-11.
 * What's The Weather?
 * Darksky API
 */

public class FavouritesAdapter extends RecyclerView.Adapter<FavouriteReportViewHolder> {
    private Context context;
    private ArrayList<Favourites> favourites;

    public FavouritesAdapter(ArrayList<Favourites> favourites, Context context) {
        this.context = context;
        this.favourites = favourites;
    }

    @Override
    public FavouriteReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(FavouriteReportViewHolder holder, int position) {
        Favourites favouriteCards = favourites.get(position);
        holder.stateView.setText(favouriteCards.getState());
        String latitude = "" + favouriteCards.getLatitude();
        holder.latitudeResultView.setText(latitude);
        String longitude = "" + favouriteCards.getLongitude();
        holder.longitudeResultView.setText(longitude);

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
