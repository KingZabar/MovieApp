package example.nanodegree.movieapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import example.nanodegree.movieapp.R;
import example.nanodegree.movieapp.fragment.MovieDetailsFragment;
import example.nanodegree.movieapp.fragment.MoviesGridFragment;


public class MainActivity extends AppCompatActivity implements MoviesGridFragment.MoviesFragmentListener {

   // static final String TAG = MainActivity.class.getSimpleName();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_layout_movie);
        initToolBar();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.containerList, new MoviesGridFragment(), "MoviesGridFragment").commit();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateToolbarTitle();
    }

    private void updateToolbarTitle() {
        if (toolbar != null) {
            String sortCriteria = PreferenceManager.getDefaultSharedPreferences(this)
                    .getString(getString(R.string.pref_sort_key), getString(R.string.most_popular));
            toolbar.setTitle(getString(R.string.movies) + " - " + sortCriteria.toLowerCase());
        }
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

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

    public boolean isDualPane() {
        return findViewById(R.id.containerDetails) != null;
    }

    @Override
    public void onMovieSelected(Bundle bundle) {

        if (isDualPane()) {

            MovieDetailsFragment frag = new MovieDetailsFragment();
            frag.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.containerDetails, frag, "MovieDetailsFragment").commit();

        } else {
            Intent intent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);

        }


    }
}
