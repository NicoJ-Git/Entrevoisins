package com.openclassrooms.entrevoisins.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourRepository;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNeighbourgActivity extends AppCompatActivity {

    private final static String DUMMY_NEIGHBOURS = "DUMMY_NEIGHBOURS";

    //public Neighbour mNeighbour;
    private NeighbourRepository mNeighbourRepository;

    // UI Components
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

    private boolean favorite;
    private boolean backToList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbourg);
        ButterKnife.bind(this);

        long userId = getIntent().getLongExtra(DUMMY_NEIGHBOURS, -1l);

        if (userId != -1l) {
            /** si le userId est different de -1 je récupère un neighbour et j'affiche son detail
             * recuperer tous les parametres du voisin avec l'id
             */
            mNeighbourRepository = DI.getNeighbourApiService();
            Neighbour neighbour = mNeighbourRepository.getNeighboursById(userId);
            name2.setText(neighbour.getName());
            name.setText(neighbour.getName());
            phone.setText(neighbour.getPhoneNumber());
            address.setText(neighbour.getAddress());
            email.setText("www/facebook.fr/" + neighbour.getName().toLowerCase(Locale.ROOT));
            aboutMe.setText(neighbour.getAboutMe());
            Glide.with(getBaseContext())
                    .load(neighbour.getAvatarUrl().replace("/150", "/450"))
                    .into(avatar);
        }

        /**
         * afficher "étoile favorie" ou non si le neighbour est favori ou non
         */
        if (favorite) {
            mFavoriteBtn.setImageResource(R.drawable.ic_star_white_24dp);
        } else {
            mFavoriteBtn.setImageResource(R.drawable.ic_star_border_white_24dp);
        }

        /**
         * Au click FAB changement d'état étoile favorie ou non. Si neighbour est favori
         */

        mFavoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favorite) {
                    favorite = false;
                    mFavoriteBtn.setImageResource(R.drawable.ic_star_border_white_24dp);
                } else {
                    favorite = true;
                    mFavoriteBtn.setImageResource(R.drawable.ic_star_white_24dp);
                }
            }
        });
    }

    public static Intent navigate(AppCompatActivity caller, long id) {
        Intent intent = new Intent(caller, DetailNeighbourgActivity.class);
        intent.putExtra(DUMMY_NEIGHBOURS, id);


        return intent;
    }

}