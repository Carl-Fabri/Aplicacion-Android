package pe.trabajo1.appciberelectrik.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pe.trabajo1.appciberelectrik.R;
import pe.trabajo1.appciberelectrik.bean.Producto;

public class AdaptadorProducto extends BaseAdapter {

    private ArrayList<Producto> listaproducto;
    LayoutInflater layoutInflater;

    public AdaptadorProducto(Context contexto, ArrayList<Producto> aproducto) {
        this.listaproducto=aproducto;
        layoutInflater=LayoutInflater.from(contexto);
    }

    @Override
    public int getCount() {
        return listaproducto.size();
    }

    @Override
    public Object getItem(int i) {
        return listaproducto.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        //evaluamos que la vista nno sea nula
        if(view==null){
            view=layoutInflater.inflate(R.layout.elemento_lista_producto,viewGroup, false);
            Producto objproducto=(Producto) getItem(i);

            TextView lblCodPro=view.findViewById(R.id.lblCodPro);
            TextView lblNomPro=view.findViewById(R.id.lblNomPro);
            TextView lblPrePro=view.findViewById(R.id.lblPrePro);
            TextView lblCantPro=view.findViewById(R.id.lblCanPro);

            TextView lblCatPro=view.findViewById(R.id.lblCatPro);
            TextView lblEstPro=view.findViewById(R.id.lblEstPro);


            lblCodPro.setText(""+objproducto.getCodigo());
            lblNomPro.setText(""+objproducto.getNombre());

            lblPrePro.setText(""+objproducto.getPrecio());
            lblCantPro.setText(""+objproducto.getCantidad());
            lblCatPro.setText(""+objproducto.getCategoria().getNombre());

            if(objproducto.getEstado()==1){
                lblEstPro.setText("Habilitado");
            }else{
                lblEstPro.setText("Deshabilitado");
            }
        }

        return view;
    }
}
