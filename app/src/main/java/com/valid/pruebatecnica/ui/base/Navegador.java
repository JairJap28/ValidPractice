package com.valid.pruebatecnica.ui.base;

import android.content.Context;
import android.content.Intent;

import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.ui.components.detail.DetailsActivity;

public class Navegador {
    public static void mainToDetails(Context context, Track track) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_TRACK, track);
        context.startActivity(intent);
    }
}
