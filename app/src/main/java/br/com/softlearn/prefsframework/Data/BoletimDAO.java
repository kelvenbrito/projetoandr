package br.com.softlearn.prefsframework.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BoletimDAO {

	private static BoletimDAO instance;

    private SQLiteDatabase db;

	public static BoletimDAO getInstance(Context context) {
		if (instance == null) {
			instance = new BoletimDAO(context.getApplicationContext());
		}
		return instance;
	}

	private BoletimDAO(Context context) {
		DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
	}

	public List<Boletim> list() {

        String[] columns = {
                BoletimConstants.Columns._ID,
                BoletimConstants.Columns.DISCIPLINA,
                BoletimConstants.Columns.MEDIA,
                BoletimConstants.Columns.FALTAS
        };

        List<Boletim> Boletims = new ArrayList<>();

        try (Cursor c = db.query(BoletimConstants.TABLE_NAME, columns, null, null, null, null, BoletimConstants.Columns.DISCIPLINA)) {
            if (c.moveToFirst()) {
                do {
                    Boletim p = BoletimDAO.fromCursor(c);
                    Boletims.add(p);
                } while (c.moveToNext());
            }

            return Boletims;
        }

	}

    private static Boletim fromCursor(Cursor c) {
        int id = c.getInt(c.getColumnIndex(BoletimConstants.Columns._ID));
        String disciplina = c.getString(c.getColumnIndex(BoletimConstants.Columns.DISCIPLINA));
        double media = c.getDouble(c.getColumnIndex(BoletimConstants.Columns.MEDIA));
        int faltas = c.getInt(c.getColumnIndex(BoletimConstants.Columns.FALTAS));
        return new Boletim(id, disciplina, media, faltas);
    }

	public void save(Boletim Boletim) {
        ContentValues values = new ContentValues();
        values.put(BoletimConstants.Columns.DISCIPLINA, Boletim.getDisciplina());
        values.put(BoletimConstants.Columns.MEDIA, Boletim.getMedia());
        values.put(BoletimConstants.Columns.FALTAS, Boletim.getFaltas());
        long id = db.insert(BoletimConstants.TABLE_NAME, null, values);
        Boletim.setId((int) id);
	}

	public void update(Boletim Boletim) {
        ContentValues values = new ContentValues();
        values.put(BoletimConstants.Columns.DISCIPLINA, Boletim.getDisciplina());
        values.put(BoletimConstants.Columns.MEDIA, Boletim.getMedia());
        values.put(BoletimConstants.Columns.FALTAS, Boletim.getFaltas());
        db.update(BoletimConstants.TABLE_NAME, values, BoletimConstants.Columns._ID + " = ?", new String[]{ String.valueOf(Boletim.getId()) });
	}

	public void delete(Boletim Boletim) {
        db.delete(BoletimConstants.TABLE_NAME, BoletimConstants.Columns._ID + " = ?", new String[]{ String.valueOf(Boletim.getId()) });
	}
}
