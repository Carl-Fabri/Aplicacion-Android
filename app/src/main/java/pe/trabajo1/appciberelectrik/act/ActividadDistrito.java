package pe.trabajo1.appciberelectrik.act;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import pe.trabajo1.appciberelectrik.R;
import pe.trabajo1.appciberelectrik.adaptadores.AdaptadorDistrito;
import pe.trabajo1.appciberelectrik.bean.Distrito;
import pe.trabajo1.appciberelectrik.dao.DistritoDAO;
import pe.trabajo1.appciberelectrik.imp.DistritoImp;
import pe.trabajo1.appciberelectrik.util.General;


public class ActividadDistrito extends Fragment {

    public static final String TAG="Fragmento Distrito";
    private TextView lblCod;
    private EditText txtNom;
    private CheckBox chkEst;
    private ListView lstDistrito;
    private Button btnRegistrar, btnActualizar, btnEliminar;
    //declaramos varible
    private int cod=0, fila=1, est=0;
    private String nom="";
    private boolean res=false;


    //interfaz
    DistritoDAO daodistrito = new DistritoImp();
    General objgeneral = new General();
    AdaptadorDistrito adaptador;

    ArrayList<Distrito> registrodistrito;
    Distrito objdistrito = new Distrito();
    FragmentTransaction ft;


    public ActividadDistrito() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        final View raiz=inflater.inflate(R.layout.actividad_distrito, container, false);

        lblCod=raiz.findViewById(R.id.lblCod);
        txtNom=raiz.findViewById(R.id.txtNom);
        chkEst=raiz.findViewById(R.id.chkEst);
        lstDistrito=raiz.findViewById(R.id.lstDistrito); ///////////
        btnRegistrar=raiz.findViewById(R.id.btnRegistrar);
        btnActualizar=raiz.findViewById(R.id.btnActualizar);
        btnEliminar=raiz.findViewById(R.id.btnEliminar);

        registrodistrito=new ArrayList<Distrito>();

        registrodistrito=daodistrito.MostrarDistrito(raiz.getContext());

        //Desabilitar el boton de Actualizar
        btnActualizar.setEnabled(false);

        if(registrodistrito!=null){

            adaptador=new AdaptadorDistrito(raiz.getContext(),registrodistrito);

            lstDistrito.setAdapter(adaptador);

        }


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNom.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el nombre de distrito","Registrar Distrito");
                    txtNom.requestFocus();
                    raiz.findViewById(R.id.frmDistrito);
                }else {
                    //capturando valores
                    nom=txtNom.getText().toString();
                    if (chkEst.isChecked()){
                        est=1;
                    }else{
                        est=0;

                    }

                    objdistrito.setNombre(nom);
                    objdistrito.setEstado(est);
                    //agregamos los valores
                    res=daodistrito.RegistrarDistrito(objdistrito, raiz.getContext());
                    Log.e("Error",""+res);
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(),"Se registro el distrito correctamente","Registrar Distrito");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstDistrito.setAdapter(adaptador);
                        //limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmDistrito));
                        txtNom.requestFocus();

                    }else {
                        objgeneral.Mensaje(raiz.getContext(),"No se registro el distrito correctamente","Registrar Distrito");
                        CargarFragmento();

                    }
                }


            }
        });
        lstDistrito.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Habilitar Boton
                btnActualizar.setEnabled(true);
                //obtenemos el valor de la fila
                fila=i;
                //asignamos los valores a los controles
                lblCod.setText(""+registrodistrito.get(fila).getCodigo());
                txtNom.setText(registrodistrito.get(fila).getNombre());
                if (registrodistrito.get(fila).getEstado()==1){
                    chkEst.setChecked(true);
                }else{
                    chkEst.setChecked(false);
                }
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Validar para qe se seleccione un elemento de la lista
                if (fila>=0){
                    cod=Integer.parseInt(lblCod.getText().toString());
                    nom=txtNom.getText().toString();
                    if (chkEst.isChecked()){
                        est=1;
                    }else{
                        est=0;
                    }
                    //enviamos los datos al objeto
                    objdistrito.setCodigo(cod);
                    objdistrito.setNombre(nom);
                    objdistrito.setEstado(est);

                    //agregamos los valores para actualizar
                    res=daodistrito.ActualizarDistrito(objdistrito);
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(),"Se actualizo el distrito correctamente",
                                "Actualiza Distrito");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstDistrito.setAdapter(adaptador);
                        //Limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmDistrito));
                        txtNom.requestFocus();

                        //Desabilitar Boton
                        btnActualizar.setEnabled(false);

                    }else{
                        objgeneral.Mensaje(raiz.getContext(),"No se actualizo el distrito correctamente",
                                "Actualizar Distrito");
                        CargarFragmento();
                    }
                }else {
                    objgeneral.Mensaje(raiz.getContext(), "Seleccion un elemento de la lista",
                            "Actualizar Distrito");
                    CargarFragmento();
                }

            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validamo0s que se seleccione un elemento de la lista
                if (fila>=0){
                    cod=Integer.parseInt(lblCod.getText().toString());
                    //enviamos el codigo
                    objdistrito.setCodigo(cod);
                    //eliminamos
                    res=daodistrito.EliminarDistrito(objdistrito);

                    if (res){
                        objgeneral.Mensaje(raiz.getContext(), "Se elimino el distrito correctamente",
                                "Eliminar Distrito");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstDistrito.setAdapter(adaptador);
                        //Limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmDistrito));
                        txtNom.requestFocus();
                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se elimino el distrito corrrectamente",
                                "Eliminar Distrito");
                        CargarFragmento();
                    }
                }else {
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un elemento de la lista",
                            "Eliminar Distrito");
                    CargarFragmento();
                }
            }
        });

        return raiz;

    }
    public void CargarFragmento(){
        ActividadDistrito fdistrito=new ActividadDistrito();
        ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor, fdistrito,ActividadDistrito.TAG);
        ft.addToBackStack(null);
        ft.commit();
    }

}