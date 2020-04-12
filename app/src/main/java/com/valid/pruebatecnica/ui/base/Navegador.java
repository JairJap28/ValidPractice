package com.valid.pruebatecnica.ui.base;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.ui.components.detail.DetailsActivity;

public class Navegador {
    // region Class methods
    public static void mainToDetails(Context context, Track track) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_TRACK, track);
        context.startActivity(intent);
    }

    public static void toBrowser(Context context, String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }
    // endregion
}
