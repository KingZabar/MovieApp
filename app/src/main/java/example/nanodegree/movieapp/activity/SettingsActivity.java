package example.nanodegree.movieapp.activity;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.nanodegree.movieapp.R;


public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_layout);
        initToolBar();
        getFragmentManager().beginTransaction().replace(R.id.container, new MyPrefFragment()).commit();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.action_settings);
    }


    public static class MyPrefFragment extends PreferenceFragment {

        public MyPrefFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_settings);
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            setUpPref();
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        private void setUpPref() {
            ListPreference sortCriteriaPref = (ListPreference) findPreference(getString(R.string.pref_sort_key));
            sortCriteriaPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    preference.setSummary(newValue.toString());
                    return true;
                }
            });
            sortCriteriaPref.setSummary(sortCriteriaPref.getValue());
        }


    }
}
