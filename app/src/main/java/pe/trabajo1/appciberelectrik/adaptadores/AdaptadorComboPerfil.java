package pe.trabajo1.appciberelectrik.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pe.trabajo1.appciberelectrik.R;
import pe.trabajo1.appciberelectrik.bean.Perfil;

public class AdaptadorComboPerfil extends BaseAdapter {
    private ArrayList<Perfil> listaperfil;
    private LayoutInflater layoutInflater;

    public AdaptadorComboPerfil(Context contexto, ArrayList<Perfil> aperfil) {
        this.listaperfil=aperfil;
        layoutInflater=LayoutInflater.from(contexto);
    }

    @Override
    public int getCount() {
        return listaperfil.size();
    }

    @Override
    public Object getItem(int i) {
        return listaperfil.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view=layoutInflater.inflate(R.layout.elemento_combo_perfil, viewGroup, false);
            Perfil objperfil=(Perfil)getItem(i);
            TextView lblPerfil=view.findViewById(R.id.lblPerfil);
            lblPerfil.setText(objperfil.getNombre());
        }
        return view;
    }
}