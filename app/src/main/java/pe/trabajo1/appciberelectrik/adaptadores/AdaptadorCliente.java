package pe.trabajo1.appciberelectrik.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pe.trabajo1.appciberelectrik.R;
import pe.trabajo1.appciberelectrik.bean.Cliente;;

public class AdaptadorCliente extends BaseAdapter {

    //creamos un ArrayList de la clase Perfil
    private ArrayList<Cliente> listacliente;
    //creamos un LayoutInflater --> para poder inflar o
    // cargar un layout dentro de otro
    LayoutInflater layoutInflater;


    public AdaptadorCliente(Context context, ArrayList<Cliente> registrocliente) {
        this.listacliente=registrocliente;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listacliente.size();
    }

    @Override
    public Object getItem(int i) {
        return listacliente.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view=layoutInflater.inflate(R.layout.elemento_lista_cliente,viewGroup, false);
            //creamos un objeto de la clase perfil
            Cliente objcliente=(Cliente)getItem(i);
            //creamos los controles
            TextView lblCodCli=view.findViewById(R.id.lblCodCli);
            TextView lblEstCli=view.findViewById(R.id.lblEstCli);

            //-------------------------------------
            TextView lblNomCli=view.findViewById(R.id.lblNomCli);
            TextView lblApepCli=view.findViewById(R.id.lblApepCli);
            TextView lblApemCli=view.findViewById(R.id.lblApemCli);
            TextView lblDirCli=view.findViewById(R.id.lblDirCli);
            TextView lblCoddCli=view.findViewById(R.id.lblCoddCli);
            TextView lblDniCli=view.findViewById(R.id.lblDniCli);
            TextView lblTelCli=view.findViewById(R.id.lblTelCli);
            TextView lblCelCli=view.findViewById(R.id.lblCelCli);
            TextView lblCorCli=view.findViewById(R.id.lblCorCli);
            TextView lblSexCli=view.findViewById(R.id.lblSexCli);





            //agregamos  los valores a los controles
            lblCodCli.setText(""+objcliente.getCodigo());
            if(objcliente.getEstado()==1){
                lblEstCli.setText("Habilitado");
            }else {
                lblEstCli.setText("Deshabilitado");
            }
            //-------------------------------------------

            lblNomCli.setText(""+objcliente.getNombre());
            lblApepCli.setText(""+objcliente.getApellidop());
            lblApemCli.setText(""+objcliente.getApellidom());
            lblDirCli.setText(""+objcliente.getDireccion());
            lblCoddCli.setText(""+objcliente.getDistrito().getNombre());
            lblDniCli.setText(""+objcliente.getDni());
            lblTelCli.setText(""+objcliente.getTelefono());
            lblCelCli.setText(""+objcliente.getCelular());
            lblCorCli.setText(""+objcliente.getCorreo());
            lblSexCli.setText(""+objcliente.getSexo());

        }
        return view;
    }
}
