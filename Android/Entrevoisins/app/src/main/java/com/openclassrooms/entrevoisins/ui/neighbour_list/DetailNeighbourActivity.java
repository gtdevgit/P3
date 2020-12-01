package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class DetailNeighbourActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.openclassrooms.entrevoisins.EXTRA_TEXT";

    private Neighbour neighbour;

    @BindView(R.id.detail_avatar)
    ImageView avatar;
    @BindView(R.id.detail_titre)
    TextView titre;
    @BindView(R.id.detail_name)
    TextView name;
    @BindView(R.id.detail_address)
    TextView address;
    @BindView(R.id.detail_phone_number)
    TextView phoneNumber;
    @BindView(R.id.detail_web)
    TextView web;
    @BindView(R.id.detail_about_me)
    TextView aboutMe;
    @BindView(R.id.floatingActionButtonFavorite)
    FloatingActionButton buttonFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        long id = intent.getLongExtra(DetailNeighbourActivity.EXTRA_ID, 0);
        Log.d("mydebug", "DetailNeighbourActivity.onCreate() Id = " + id);

        this.neighbour = DI.getNeighbourApiService().findNeighbourById(id);
        if (neighbour != null)
        {
            //this.avatar = neighbour.getAvatarUrl();
            this.titre.setText(neighbour.getName());
            this.name.setText(neighbour.getName());
            this.address.setText(neighbour.getAddress());
            this.phoneNumber.setText(neighbour.getPhoneNumber());
            this.aboutMe.setText(neighbour.getAboutMe());
            this.web.setText("");

            Glide.with(avatar.getContext())
                    .load(neighbour.getAvatarUrl())
                    .into(avatar);
            Log.i("mydebug", "DetailNeighbourActivity.onCreate() isFavorite = " + neighbour.isFavorite());
            refreshFavoriteColor();
        }
    }

    private void refreshFavoriteColor(){
        int color;
        if (neighbour.isFavorite()) {
             color = ContextCompat.getColor(getApplicationContext(), R.color.colorFavorite);
        } else {
            color = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        }
        buttonFavorite.setColorFilter(color);
    }

    @OnClick(R.id.floatingActionButtonFavorite)
    void favorite(){
        neighbour.toggleFavoriteStatus();
        refreshFavoriteColor();
    }

    public static void navigate(Context context, Neighbour neighbour) {
        Long id = neighbour.getId();
        Intent intent = new Intent(context, DetailNeighbourActivity.class);
        intent.putExtra(DetailNeighbourActivity.EXTRA_ID, id);
        ActivityCompat.startActivity(context, intent, null);
    }
}