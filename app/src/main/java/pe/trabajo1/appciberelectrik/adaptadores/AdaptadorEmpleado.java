package pe.trabajo1.appciberelectrik.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pe.trabajo1.appciberelectrik.R;
import pe.trabajo1.appciberelectrik.bean.Empleado;

public class AdaptadorEmpleado extends BaseAdapter {
    //creamos un ArrayList de la clase Perfil
    private ArrayList<Empleado> listaempleado;
    //creamos un LayoutInflater --> para poder inflar o
    // cargar un layout dentro de otro
    LayoutInflater layoutInflater;

    public AdaptadorEmpleado(Context contexto, ArrayList<Empleado> aempleado){
        this.listaempleado=aempleado;
        layoutInflater=LayoutInflater.from(contexto);
    }
    @Override
    public int getCount() {
        return listaempleado.size();
    }

    @Override
    public Object getItem(int i) {
        return listaempleado.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //evaluamos que la vista nno sea nula
        if(view==null){
            view=layoutInflater.inflate(R.layout.elemento_lista_empleado,viewGroup, false);
            //creamos un objeto de la clase distrito
            Empleado objempleado=(Empleado) getItem(i);
            //creamos los controles
            TextView lblCodEm=view.findViewById(R.id.lblCodEm);
            TextView lblUsuEm=view.findViewById(R.id.lblUsuEm);
            TextView lblEstEm=view.findViewById(R.id.lblEstEm);
            //agregamos  los valores a los controles
            lblCodEm.setText(""+objempleado.getCodigo());
            lblUsuEm.setText(""+objempleado.getNombre());
            if(objempleado.getEstado()==1){
                lblEstEm.setText("Habilitado");
            }else {
                lblEstEm.setText("Deshabilitado");
            }

            /* Datos Extras por Mostrar */

            TextView lblNomEm=view.findViewById(R.id.lblNomEm);
            TextView lblApepEm=view.findViewById(R.id.lblApepEm);
            TextView lblApemEm=view.findViewById(R.id.lblApemEm);

            TextView lblTelEm =view.findViewById(R.id.lblTelEm);
            TextView lblCelEm=view.findViewById(R.id.lblCelEm);
            TextView lblCorEm=view.findViewById(R.id.lblCorEm);

            TextView lblSexEm=view.findViewById(R.id.lblSexEm);
            TextView lblClaEm=view.findViewById(R.id.lblClaEm);
            TextView lblDirEm=view.findViewById(R.id.lblDirEm);

            TextView lblCodDisEm=view.findViewById(R.id.lblCodDisEm);
            TextView lblDniEm=view.findViewById(R.id.lblDniEm);
            TextView lblPerfilEm=view.findViewById(R.id.lblPerfilEm);

            /* Agregando los valores de los controles */
            lblNomEm.setText(""+objempleado.getUsuario());
            lblApepEm.setText(""+objempleado.getApellidop());
            lblApemEm.setText(""+objempleado.getApellidom());

            lblTelEm.setText(""+objempleado.getTelefono());
            lblCelEm.setText(""+objempleado.getCelular());
            lblCorEm.setText(""+objempleado.getCorreo());

            lblSexEm.setText(""+objempleado.getSexo());
            lblClaEm.setText(""+objempleado.getClave());
            lblDirEm.setText(""+objempleado.getDireccion());

            lblCodDisEm.setText(""+objempleado.getDistrito().getNombre());
            lblDniEm.setText(""+objempleado.getDni());
            lblPerfilEm.setText(""+objempleado.getPerfil().getNombre());

        }


        return view;
    }
}
