package pe.trabajo1.appciberelectrik.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pe.trabajo1.appciberelectrik.R;
import pe.trabajo1.appciberelectrik.bean.Categoria;

public class AdaptadorComboCategoria extends BaseAdapter {
    private ArrayList<Categoria> listacategoria;
    private LayoutInflater layoutInflater;

    public AdaptadorComboCategoria(Context contexto, ArrayList<Categoria> acategoria) {
        this.listacategoria=acategoria;
        layoutInflater=LayoutInflater.from(contexto);
    }


    @Override
    public int getCount() {
        return listacategoria.size();
    }

    @Override
    public Object getItem(int i) {
        return listacategoria.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view=layoutInflater.inflate(R.layout.elemento_combo_categoria, viewGroup, false);
            Categoria objCategoria=(Categoria)getItem(i);
            TextView lblCategoria=view.findViewById(R.id.lblCategoria);
            lblCategoria.setText(objCategoria.getNombre());
        }
        return view;
    }
}
