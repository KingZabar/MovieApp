package example.nanodegree.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import example.nanodegree.movieapp.fragment.MovieDetailsFragment;
import example.nanodegree.movieapp.fragment.MoviesFragment;


public class MainActivity extends AppCompatActivity implements MoviesFragment.MoviesFragmentListener {

    static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_layout_movie);
        initToolBar();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.containerList, new MoviesFragment(), "MoviesFragment").commit();

            if (isDualPane())
                Toast.makeText(this, "isDualPane", Toast.LENGTH_SHORT).show();
        }
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.movies);

        toolbar.inflateMenu(R.menu.menu_main);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.action_settings) {
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    return true;
                }


                return false;
            }
        });
    }

    private boolean isDualPane() {
        if (findViewById(R.id.containerDetails) != null)
            return true;
        else return false;
    }

    @Override
    public void onMovieSelected(Bundle bundle) {

        if (isDualPane()) {

            MovieDetailsFragment frag = new MovieDetailsFragment();
            frag.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.containerDetails, frag, "MovieDetailsFragment").addToBackStack(null).commit();

        } else {
            Intent intent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);

        }


    }
}
