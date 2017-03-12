package com.ahlberg.jacob.whatstheweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jacob on 2017-03-11.
 * What's The Weather?
 * Darksky API
 */

public class FavouriteReportViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.stateView)           TextView stateView;
    @BindView(R.id.latitudeResultView)  TextView latitudeResultView;
    @BindView(R.id.longitudeResultView) TextView longitudeResultView;
    @BindView(R.id.removeButton)        Button removeButton;



    public FavouriteReportViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }


}
