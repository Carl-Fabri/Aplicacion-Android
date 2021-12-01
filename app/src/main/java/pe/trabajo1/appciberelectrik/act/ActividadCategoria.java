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
import pe.trabajo1.appciberelectrik.adaptadores.AdaptadorCategoria;
import pe.trabajo1.appciberelectrik.bean.Categoria;
import pe.trabajo1.appciberelectrik.dao.CategoriaDAO;
import pe.trabajo1.appciberelectrik.imp.CategoriaImp;
import pe.trabajo1.appciberelectrik.util.General;


public class ActividadCategoria extends Fragment {
    public static final String TAG = "Fragmento Categoria";
    private TextView lblCod;
    private EditText txtNom;
    private CheckBox chkEst;
    private ListView lstCategoria;
    private Button btnRegistrar, btnActualizar, btnEliminar;
    private int cod = 0, fila = 1, est = 0;
    private String nom = "";
    private boolean res = false;
    CategoriaDAO daocategoria = new CategoriaImp();
    General objgeneral = new General();
    AdaptadorCategoria adaptador;
    ArrayList<Categoria> registrocategoria;
    Categoria objcategoria = new Categoria();
    Categoria actCategoria = new Categoria();
    FragmentTransaction ft;

    public ActividadCategoria() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View raiz = inflater.inflate(R.layout.actividad_categoria, container, false);
        //creamos los controles
        lblCod = raiz.findViewById(R.id.lblCodCat);
        txtNom = raiz.findViewById(R.id.txtNom);
        chkEst = raiz.findViewById(R.id.chkEst);
        lstCategoria = raiz.findViewById(R.id.lstCategoria);
        btnRegistrar = raiz.findViewById(R.id.btnRegistrar);
        btnActualizar = raiz.findViewById(R.id.btnActualizar);
        btnEliminar = raiz.findViewById(R.id.btnEliminar);
        //creamos el ArrayList de tipo Perfil
        registrocategoria = new ArrayList<>();
        //asiganmos al ArrayList los valores que s evan mostrar
        registrocategoria = daocategoria.MostrarCategoria(raiz.getContext());

        btnActualizar.setEnabled(false);

        if (registrocategoria != null) {
            //creamos el AdaptadorPerfil
            adaptador = new AdaptadorCategoria(raiz.getContext(), registrocategoria);
            //asignamos los valores a la lista
            lstCategoria.setAdapter(adaptador);
        }
        //agregamos los eventos
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNom.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el nombre de la categoria" ,"Registran Perfil");
                    txtNom.requestFocus();
                    raiz.findViewById(R.id.frmCategoria);
                }else{
                    nom=txtNom.getText().toString();
                    if(chkEst.isChecked()){
                        est=1;
                    }else{
                        est=0;
                    }
                    objcategoria.setNombre(nom);
                    objcategoria.setEstado(est);
                    Log.e("valores: ",""+objcategoria.getNombre());
                    Log.e("valores: ",""+objcategoria.getEstado());
                    res= daocategoria.RegistrarCategoria(objcategoria, raiz.getContext());
                    Log.e("Error: ",""+res);
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(), "Se registro la categoria correctamente","Registrar Perfil");
                        CargarFragmento();
                        lstCategoria.setAdapter(adaptador);
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmCategoria));
                        txtNom.requestFocus();
                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se registro la categoria correctamente","Registrar Perfil");
                        CargarFragmento();
                    }
                }
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validar para que se selecciona un elemento de la lista
                if(fila>=0) {
                    cod = Integer.parseInt(lblCod.getText().toString());
                    nom = txtNom.getText().toString();
                    if (chkEst.isChecked()) {
                        est = 1;
                    } else {
                        est = 0;

                    }
                    //enviamos los datos al objeto
                    objcategoria.setCodigo(cod);
                    objcategoria.setNombre(nom);
                    objcategoria.setEstado(est);
                    //agregamos los valores para actualizar
                    res = daocategoria.ActualizarCategoria(objcategoria);
                    if (res) {
                        objgeneral.Mensaje(raiz.getContext(), "No se actualizo el Categoria correctamente", "Actualizar Categoria");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstCategoria.setAdapter(adaptador);
                        //limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmCategoria));
                        txtNom.requestFocus();


                    } else {
                        objgeneral.Mensaje(raiz.getContext(), "No se actualizo el perfil correctamente", "Actualizar Perfil");
                        CargarFragmento();
                    }

                }else{
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un elemento de la lista", "Actualizar Perfil");
                    CargarFragmento();
                }

            }
        });

        lstCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //obtenemos el valor de la fila seleccionada
                fila=i;

                btnActualizar.setEnabled(true);
                //asignando  los valores a los controles
                actCategoria = registrocategoria.get(fila);
                lblCod.setText(""+registrocategoria.get(fila).getCodigo());

                txtNom.setText(actCategoria.getNombre());
                chkEst.setChecked(actCategoria.getEstado() == 1);
                if (registrocategoria.get(fila).getEstado()==1){
                    chkEst.setChecked(true);
                }else{
                    chkEst.setChecked(false);
                }
            }
        });



        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validando que seleccione un elemento de la lista
                if(fila>=0){
                    cod=Integer.parseInt(lblCod.getText().toString());
                    //enviamos el codigo
                    objcategoria.setCodigo(cod);
                    //eliminamos
                    res=daocategoria.EliminarCategoria(objcategoria);
                    if (res) {
                        objgeneral.Mensaje(raiz.getContext(), "Se alimino el categoria correctamente", "Eliminar categoria");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstCategoria.setAdapter(adaptador);
                        //limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmCategoria));
                        txtNom.requestFocus();


                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se elimino el categoria correctamente", "Eliminar categoria");
                        CargarFragmento();
                    }


                }else{
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un elemento de la lista", "Eliminar Perfil");
                    CargarFragmento();

                }

            }
        });

        return raiz;



    }

    public void CargarFragmento(){
        ActividadCategoria fcategoria= new ActividadCategoria();
        ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor,fcategoria, ActividadCategoria.TAG);
        ft.addToBackStack(null);
        ft.commit();
    }
}