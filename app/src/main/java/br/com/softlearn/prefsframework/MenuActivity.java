package br.com.softlearn.prefsframework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Matioli on 02/08/2017.
 */

public class MenuActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_menu);
        Toast.makeText(this,"MENU!!!",Toast.LENGTH_LONG).show();
    }

    public void cmdConfig(View v){
        Toast.makeText(this,"Configuração!!!",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,ConfigActivity.class);
        startActivity(intent);
    }

    public void cmdSobre(View v){
        Toast.makeText(this,"SObre!!!",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,SobreActivity.class);
        startActivity(intent);
    }
}
