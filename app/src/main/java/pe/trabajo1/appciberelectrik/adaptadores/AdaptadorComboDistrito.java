package pe.trabajo1.appciberelectrik.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pe.trabajo1.appciberelectrik.R;
import pe.trabajo1.appciberelectrik.bean.Distrito;

public class AdaptadorComboDistrito extends BaseAdapter {
    private ArrayList<Distrito> listadistrito;
    private LayoutInflater layoutInflater;
    public AdaptadorComboDistrito(Context contexto, ArrayList<Distrito> adistrito){
        this.listadistrito=adistrito;
        layoutInflater=LayoutInflater.from(contexto);

    }
    @Override
    public int getCount() {
        return listadistrito.size();
    }

    @Override
    public Object getItem(int i) {
        return listadistrito.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view=layoutInflater.inflate(R.layout.elemento_combo_distrito, viewGroup, false);
            Distrito objDistrito=(Distrito)getItem(i);
            TextView lblDistrito=view.findViewById(R.id.lblDistrito);
            lblDistrito.setText(objDistrito.getNombre());
        }
        return view;
    }
}

