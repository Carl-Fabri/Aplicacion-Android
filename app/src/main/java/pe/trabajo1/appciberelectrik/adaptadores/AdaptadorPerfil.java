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

public class AdaptadorPerfil extends BaseAdapter{


    private ArrayList<Perfil> listaperfil;

    LayoutInflater layoutInflater;


    public AdaptadorPerfil(Context contexto, ArrayList<Perfil> aperfil) {
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
        //evaluamos que la vista nno sea nula
        if(view==null){
            view=layoutInflater.inflate(R.layout.elemento_lista_perfil,viewGroup, false);
            //creamos un objeto de la clase perfil
            Perfil objperfil=(Perfil)getItem(i);
            //creamos los controles
            TextView lblCodPer=view.findViewById(R.id.lblCodPer);
            TextView lblNomPer=view.findViewById(R.id.lblUsuPer);
            TextView lblEstPer=view.findViewById(R.id.lblEstPer);
            //agregamos  los valores a los controles
            lblCodPer.setText(""+objperfil.getCodigo());
            lblNomPer.setText(""+objperfil.getNombre());
            if(objperfil.getEstado()==1){
                lblEstPer.setText("Habilitado");
            }else {
                lblEstPer.setText("Deshabilitado");
            }
        }
        return view;
    }

}
