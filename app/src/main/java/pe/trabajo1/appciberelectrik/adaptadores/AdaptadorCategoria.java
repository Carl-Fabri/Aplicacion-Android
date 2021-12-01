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

public class AdaptadorCategoria extends BaseAdapter {

    private ArrayList<Categoria> listacategoria;
    LayoutInflater layoutInflater;

    public AdaptadorCategoria(Context contexto, ArrayList<Categoria> acategoria) {
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
        //evaluamos que la vista nno sea nula
        if(view==null){
            view=layoutInflater.inflate(R.layout.elemento_lista_categoria,viewGroup, false);
            Categoria objcategoria=(Categoria)getItem(i);
            TextView lblCodCat=view.findViewById(R.id.lblCodCat);
            TextView lblNomCat=view.findViewById(R.id.lblNomCat);
            TextView lblEstCat=view.findViewById(R.id.lblEstCat);
            //agregamos  los valores a los controles
            lblCodCat.setText(""+objcategoria.getCodigo());
            lblNomCat.setText(""+objcategoria.getNombre());
            if(objcategoria.getEstado()==1){
                lblEstCat.setText("Habilitado");
            }else {
                lblEstCat.setText("Deshabilitado");
            }
        }
        return view;
    }
}
