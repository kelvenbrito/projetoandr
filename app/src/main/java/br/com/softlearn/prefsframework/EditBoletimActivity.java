package br.com.softlearn.prefsframework;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.softlearn.prefsframework.Data.Boletim;
import br.com.softlearn.prefsframework.Data.BoletimDAO;

public class EditBoletimActivity extends Activity {

	private BoletimDAO boletimDAO;
	private EditText edtDisciplina;
	private EditText edtMedia;
	private EditText edtFaltas;
	private Boletim boletim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_editboletim);
		
		edtDisciplina = (EditText) findViewById(R.id.edt_disciplina);
		edtMedia = (EditText) findViewById(R.id.edt_media);
		edtFaltas = (EditText) findViewById(R.id.edt_faltas);

		boletimDAO = BoletimDAO.getInstance(this);
		
		boletim = (Boletim) getIntent().getSerializableExtra("boletim");
		
		if (boletim != null) {
			edtDisciplina.setText(boletim.getDisciplina());
			edtMedia.setText(String.valueOf(boletim.getMedia()));
			edtFaltas.setText(String.valueOf(boletim.getFaltas()));
		}
	}
	
	public void cancel(View view) {
		setResult(RESULT_CANCELED);
		finish();
	}
	
	public void process(View view) {
		String disciplina = edtDisciplina.getText().toString();
		double media = Double.parseDouble(edtMedia.getText().toString());
		int faltas = Integer.parseInt(edtFaltas.getText().toString());
		String msg;
		
		if (boletim == null) {
			Boletim boletim = new Boletim(disciplina, media, faltas);
			boletimDAO.save(boletim);
			msg = "Boletim gravado com ID = " + boletim.getId();
		
		} else {
			boletim.setDisciplina(disciplina);
			boletim.setMedia(media);
			boletim.setFaltas(faltas);
			boletimDAO.update(boletim);
			msg = "Boletim atualizado com ID = " + boletim.getId();
		}
		
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		setResult(RESULT_OK);
		finish();
	}
}
