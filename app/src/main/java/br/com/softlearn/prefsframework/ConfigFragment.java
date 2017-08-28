package br.com.softlearn.prefsframework;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;

/**
 * Created by Matioli on 02/08/2017.
 */

public class ConfigFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private SwitchPreference pontuar;
    private ListPreference nivel;
    private CheckBoxPreference publicar;
    private EditTextPreference username;
    private EditTextPreference senha;

    private Resources res;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Passo 0:
        addPreferencesFromResource(R.xml.config_layout);

        //Passo 3:
        res = getActivity().getResources();

        pontuar = (SwitchPreference) findPreference(res.getString(R.string.pontuar_key));
        nivel = (ListPreference) findPreference(res.getString(R.string.nivel_key));
        publicar =  (CheckBoxPreference) findPreference(res.getString(R.string.publicar_key));
        username = (EditTextPreference) findPreference(res.getString(R.string.username_key));
        senha = (EditTextPreference) findPreference(res.getString(R.string.senha_key));

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(this);
        boolean calcDefaultValue =  res.getBoolean(R.bool.pontuar_default);

        Boolean enabled = prefs.getBoolean(res.getString(R.string.pontuar_key), calcDefaultValue);

        Habilita(enabled);
    }

    private void Habilita(Boolean enabled){
        nivel.setEnabled(enabled);
        publicar.setEnabled(enabled);
        username.setEnabled(enabled);
        senha.setEnabled(enabled);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if(s.equals(pontuar.getKey())){
            boolean calcDefaultValue =  res.getBoolean(R.bool.pontuar_default);
            boolean enabled = sharedPreferences.getBoolean(s,calcDefaultValue);
            Habilita(enabled);
        }
    }
}
