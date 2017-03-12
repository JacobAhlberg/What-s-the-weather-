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
    @BindView(R.id.removeButton)            Button removeBtn;
    @BindView(R.id.cityView)                TextView cityView;
    @BindView(R.id.latitudeResultView)      TextView latitudeResultView;
    @BindView(R.id.longitudeResultView)     TextView longitudeResultView;

    public FavouritesViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        ButterKnife.bind(mView);
    }

    public void setCity(String city){

    }

    public void setLatitudeResult(double latitudeResult){

    }

    public void setLongitudeResult(double longitudeResult){

    }


}
