package com.ahlberg.jacob.whatstheweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jacob on 2017-03-12.
 * What's The Weather?
 * Darksky API
 */

public class FavouritesViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public FavouritesViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setCity(String city){
        TextView cityView = (TextView)mView.findViewById(R.id.cityView);
        cityView.setText(city);
    }

    public void setLatitudeResult(double latitudeResult){
        TextView latitudeResultView = (TextView)mView.findViewById(R.id.latitudeResultView);
        String latitude = "" + latitudeResult;
        latitudeResultView.setText(latitude);
    }

    public void setLongitudeResult(double longitudeResult){
        TextView longitudeResultView = (TextView)mView.findViewById(R.id.longitudeResultView);
        String longitude = "" + longitudeResult;
        longitudeResultView.setText(longitude);
    }


}
