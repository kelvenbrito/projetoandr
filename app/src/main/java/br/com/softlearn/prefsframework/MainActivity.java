package br.com.softlearn.prefsframework;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.softlearn.prefsframework.Adapter.BoletimAdapter;
import br.com.softlearn.prefsframework.Data.Boletim;
import br.com.softlearn.prefsframework.Data.BoletimDAO;
import br.com.softlearn.prefsframework.Dialog.DeleteDialog;

public class MainActivity extends ListActivity implements AdapterView.OnItemLongClickListener, DeleteDialog.OnDeleteListener {


    private static final int REQ_EDIT = 100;

    private BoletimDAO boletimDAO;
    private BoletimAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new BoletimAdapter(this);
        setListAdapter(adapter);

        getListView().setOnItemLongClickListener(this);

        boletimDAO = BoletimDAO.getInstance(this);

        updateList();

    }

    private void updateList() {
        List<Boletim> boletins = boletimDAO.list();
        adapter.setItems(boletins);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mnuConfig){
            Intent intent = new Intent(this,ConfigActivity.class);
            startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), EditBoletimActivity.class);
            startActivityForResult(intent, REQ_EDIT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_EDIT && resultCode == RESULT_OK) {
            updateList();
        }
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), EditBoletimActivity.class);
        intent.putExtra("boletim", adapter.getItem(position));
        startActivityForResult(intent, REQ_EDIT);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
        Boletim boletim = adapter.getItem(position);

        DeleteDialog dialog = new DeleteDialog();
        dialog.setBoletim(boletim);
        dialog.show(getFragmentManager(), "deleteDialog");
        return true;
    }

    @Override
    public void onDelete(Boletim boletim) {
        boletimDAO.delete(boletim);
        updateList();
    }

}
