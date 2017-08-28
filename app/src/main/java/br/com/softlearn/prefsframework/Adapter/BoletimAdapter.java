package br.com.softlearn.prefsframework.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.softlearn.prefsframework.Data.Boletim;
import br.com.softlearn.prefsframework.R;

public class BoletimAdapter extends BaseAdapter {
	Locale locale  = new Locale("pt", "BR");
	String pattern = "##.00";
	DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
	DecimalFormat df = new DecimalFormat(pattern, symbols);
	NumberFormat nf = NumberFormat.getInstance(locale);

	private Context context;
	private List<Boletim> Boletims = new ArrayList<>();

	public BoletimAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return Boletims.size();
	}

	@Override
	public Boletim getItem(int position) {
		return Boletims.get(position);
	}

	@Override
	public long getItemId(int position) {
		return Boletims.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;

		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.adapter_boletim, parent, false);
			holder = new ViewHolder();
			holder.txtDisciplina = (TextView) view.findViewById(R.id.txt_disciplina);
			holder.txtMedia = (TextView) view.findViewById(R.id.txt_media);
			holder.txtFaltas = (TextView) view.findViewById(R.id.txt_faltas);
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}

		Boletim Boletim = Boletims.get(position);

		holder.txtDisciplina.setText(Boletim.getDisciplina());
		holder.txtMedia.setText(df.format(Boletim.getMedia()));
		holder.txtFaltas.setText(nf.format(Boletim.getFaltas()));

		return view;
	}

	public void setItems(List<Boletim> Boletims) {
		this.Boletims = Boletims;
		notifyDataSetChanged();
	}

	private static class ViewHolder {
		public TextView txtDisciplina;
		public TextView txtMedia;
		public TextView txtFaltas;
	}
}
