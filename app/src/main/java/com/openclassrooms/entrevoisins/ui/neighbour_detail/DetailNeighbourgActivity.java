package com.openclassrooms.entrevoisins.ui.neighbour_detail;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourRepository;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNeighbourgActivity extends AppCompatActivity {

    private final static String SELECTED_NEIGHBOUR_ID = "SELECTED_NEIGHBOUR_ID";

    //public Neighbour mNeighbour;
    private NeighbourRepository mNeighbourRepository;

    // UI Components
    @BindView(R.id.upButton)
    ImageButton up;
    @BindView(R.id.editTextTextPersonName3)
    TextView name;
    @BindView(R.id.editTextTextPersonName2)
    TextView name2;
    @BindView(R.id.imageView)
    ImageView avatar;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton mFavoriteBtn;
    @BindView(R.id.editTextTextPostalAddress2)
    TextView address;
    @BindView(R.id.editTextPhone2)
    TextView phone;
    @BindView(R.id.editTextTextEmailAddress2)
    TextView email;
    @BindView(R.id.editTextTextMultiLine)
    TextView aboutMe;

    private Neighbour displayedNeighbour;
    private boolean isDisplayedFavorite;//is currently displayed neighbour favorite or not ?
    //private Neighbour favoriteNeighbourList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbourg);
        ButterKnife.bind(this);

        /** return to the list with button "up"
         */
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        long neighbourId = getIntent().getLongExtra(SELECTED_NEIGHBOUR_ID, -1l);

        if (neighbourId != -1l) {
            /** si le neighbourId est different de -1 je récupère un neighbour et j'affiche son detail
             * recuperer tous les parametres du voisin avec l'id
             */
            mNeighbourRepository = DI.getNeighbourApiService();
            displayedNeighbour = mNeighbourRepository.getNeighboursById(neighbourId);
            name2.setText(displayedNeighbour.getName());
            name.setText(displayedNeighbour.getName());
            phone.setText(displayedNeighbour.getPhoneNumber());
            address.setText(displayedNeighbour.getAddress());
            email.setText("www/facebook.fr/" + displayedNeighbour.getName().toLowerCase(Locale.ROOT));
            aboutMe.setText(displayedNeighbour.getAboutMe());
            Glide.with(getBaseContext())
                    .load(displayedNeighbour.getAvatarUrl().replace("/150", "/450"))
                    .into(avatar);
        }

        isDisplayedFavorite = displayedNeighbour.isFavorite();

        /**
         * afficher "étoile favorite" ou non
         */
//        if (isDisplayedFavorite) {
//            mFavoriteBtn.setImageResource(R.drawable.ic_star_white_24dp);
//        } else {
//            mFavoriteBtn.setImageResource(R.drawable.ic_star_border_white_24dp);
//        }
        setStar(); // gérer affichage voisin en fonction de favori ou pas

        /**
         * Au click FAB changement d'état étoile favorie ou non. Si neighbour est favori
         */

        mFavoriteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                if (isDisplayedFavorite) {
//                    isDisplayedFavorite = false;
//                    mFavoriteBtn.setImageResource(R.drawable.ic_star_border_white_24dp);
//                    mNeighbourRepository.removeFavorite(neighbourId);
//                } else {
//                    isDisplayedFavorite = true;
//                    mFavoriteBtn.setImageResource(R.drawable.ic_star_white_24dp);
//                    mNeighbourRepository.addFavorite(neighbourId);
//                    //Toast.makeText(DetailNeighbourgActivity.this.getApplicationContext(), (CharSequence) favoriteNeighbourList.getName()+" is cheched", Toast.LENGTH_SHORT).show();
//                }

                isDisplayedFavorite = !isDisplayedFavorite;
                setStar();
                if (isDisplayedFavorite) {
                    mNeighbourRepository.addFavorite(neighbourId);
                } else {
                    mNeighbourRepository.removeFavorite(neighbourId);
                }
            }
        });
    }

    private void setStar() {
        int starResource = isDisplayedFavorite ? R.drawable.ic_star_white_24dp : R.drawable.ic_star_border_white_24dp;
        mFavoriteBtn.setImageResource(starResource);
    }


    public static Intent navigate(AppCompatActivity caller, long id) {
        Intent intent = new Intent(caller, DetailNeighbourgActivity.class);
        intent.putExtra(SELECTED_NEIGHBOUR_ID, id);


        return intent;
    }

}