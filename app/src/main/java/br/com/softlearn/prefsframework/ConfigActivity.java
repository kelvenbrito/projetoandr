package br.com.softlearn.prefsframework;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class ConfigActivity extends Activity {
    private ConfigFragment configFrag;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Passo 1:
        configFrag = new ConfigFragment();

        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content,configFrag,"configFragment")
                .commit();
        //-------------------------Fim Passo 1 ---------------------------
    }
}
