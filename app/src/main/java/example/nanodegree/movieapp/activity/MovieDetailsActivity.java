package example.nanodegree.movieapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import example.nanodegree.movieapp.R;
import example.nanodegree.movieapp.fragment.MovieDetailsFragment;


public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_layout);

        initToolBar();

        if (savedInstanceState == null)
            initMoveDetailsFragment();


    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.movie_details);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initMoveDetailsFragment() {
        Bundle bundle = getIntent().getExtras();
        MovieDetailsFragment frag = new MovieDetailsFragment();
        frag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, frag, "MovieDetailsFragment").commit();

    }
}
