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
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import pe.trabajo1.appciberelectrik.R;
import pe.trabajo1.appciberelectrik.adaptadores.AdaptadorComboCategoria;
import pe.trabajo1.appciberelectrik.adaptadores.AdaptadorProducto;
import pe.trabajo1.appciberelectrik.bean.Categoria;
import pe.trabajo1.appciberelectrik.bean.Producto;
import pe.trabajo1.appciberelectrik.dao.CategoriaDAO;
import pe.trabajo1.appciberelectrik.dao.ProductoDAO;
import pe.trabajo1.appciberelectrik.imp.CategoriaImp;
import pe.trabajo1.appciberelectrik.imp.ProductoImp;
import pe.trabajo1.appciberelectrik.util.General;


public class ActividadProducto extends Fragment {



    public static final String TAG="Fragmento Producto";
    private TextView lblCodPro;
    private EditText txtNom, txtCan, txtPre;
    private CheckBox chkEst;
    private ListView lstProducto;
    private Spinner cboCategoria;
    private Button btnRegistrar, btnActualizar, btnEliminar;
    private int cod=0, codd=0, codp=0, est=-1, fila=1;
    private String nom="",pre="", cant="";

    private boolean res=false;
    private TextView lblCodCate;
    AdaptadorComboCategoria adaptadorComboCategoria;
    ArrayList<Categoria> registrocategoria;
    CategoriaDAO daocategoria=new CategoriaImp();
    ProductoDAO daoproducto = new ProductoImp();
    General objgeneral = new General();
    AdaptadorProducto adaptadorProducto;
    ArrayList<Producto> registroproducto;
    Producto objproducto= new Producto();
    Producto actproducto=new Producto();
    Categoria objcategoria=new Categoria();
    FragmentTransaction ft;





    public ActividadProducto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View raiz= inflater.inflate(R.layout.actividad_producto, container, false);


        //creamos los controles
        lblCodPro=raiz.findViewById(R.id.lblCodPro);
        txtNom=raiz.findViewById(R.id.txtNom);
        txtPre=raiz.findViewById(R.id.txtPre);
        txtCan=raiz.findViewById(R.id.txtCan);

        cboCategoria=raiz.findViewById(R.id.cboCategoria);

        lblCodCate=raiz.findViewById(R.id.lblCodCate);

        chkEst=raiz.findViewById(R.id.chkEst);
        lstProducto=raiz.findViewById(R.id.lstProducto);
        btnRegistrar=raiz.findViewById(R.id.btnRegistrar);
        btnActualizar=raiz.findViewById(R.id.btnActualizar);
        btnEliminar=raiz.findViewById(R.id.btnEliminar);


        registroproducto=new ArrayList<>();
        registrocategoria=new ArrayList<>();

        registroproducto=daoproducto.MostrarProducto(raiz.getContext());
        registrocategoria=daocategoria.MostrarCategoria(raiz.getContext());

        btnActualizar.setEnabled(false);

        if(registrocategoria!=null){
            //creamos el adaptador
            adaptadorComboCategoria=new AdaptadorComboCategoria(raiz.getContext(), registrocategoria);
            //asignamos el adaptador al combo
            cboCategoria.setAdapter(adaptadorComboCategoria);
        }
        if(registroproducto!=null){
            adaptadorProducto=new AdaptadorProducto(raiz.getContext(),registroproducto);
            lstProducto.setAdapter(adaptadorProducto);
        }


        cboCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int codCat=registrocategoria.get(i).getCodigo();
                lblCodCate.setText(""+codCat);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNom.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el nombre del producto" ,"Registran Empleado");
                    txtNom.requestFocus();
                    raiz.findViewById(R.id.frmEmpleado);
                }else if(cboCategoria.getSelectedItemPosition()==-1) {
                    objgeneral.Mensaje(raiz.getContext(),"Seleccione una Categoria" ,"Registran Categoria");
                    cboCategoria.requestFocus();
                    raiz.findViewById(R.id.frmEmpleado);
                }else{
                    //capturando valores

                    nom=txtNom.getText().toString();
                    pre=txtPre.getText().toString();
                    cant=txtCan.getText().toString();



                    codd=Integer.parseInt(lblCodCate.getText().toString());

                    if(chkEst.isChecked()){
                        est=1;
                    }else{
                        est=0;
                    }
                    //enviamos los valores a la clase

                    // valores de distrito
                    objcategoria.setCodigo(codd);




                    // valores de empleado
                    objproducto.setNombre(nom);
                    // seteando objetos de distrito y perfil en empl
                    objproducto.setCategoria(objcategoria);

                    // --
                    int entero = Integer.parseInt(cant);

                    objproducto.setCantidad(entero);

                    Double totalString = Double.parseDouble(pre);
                    objproducto.setPrecio(totalString);

                    objproducto.setEstado(est);


                    //agregamos los valores
                    res=daoproducto.RegistrarProducto(objproducto, raiz.getContext());
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(), "Se registro el producto correctamente","Registrar Producto");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstProducto.setAdapter(adaptadorProducto);
                        //Limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmProducto));
                        txtNom.requestFocus();
                        Log.e("Error",""+registrocategoria);

                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se registro el producto correctamente","Registrar Producto");
                        CargarFragmento();
                    }

                }
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNom.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el nombre del perfil" ,"Registran Cliente");
                    txtNom.requestFocus();
                    raiz.findViewById(R.id.frmProducto);
                }else if(cboCategoria.getSelectedItemPosition()==-1) {
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un categoria", "Registran categoria");
                    cboCategoria.requestFocus();
                    raiz.findViewById(R.id.frmProducto);
                }

                else{
                    //capturando valores

                    nom=txtNom.getText().toString();
                    pre=txtPre.getText().toString();
                    cant=txtCan.getText().toString();

                    cod = Integer.parseInt(lblCodPro.getText().toString());

                    codd=Integer.parseInt(lblCodCate.getText().toString());

                    if(chkEst.isChecked()){
                        est=1;
                    }else{
                        est=0;
                    }

                    // valores de cliente
                    objcategoria.setCodigo(codd);
                    objproducto.setNombre(nom);
                    // seteando objetos de distrito y perfil en empl
                    objproducto.setCategoria(objcategoria);
                    objproducto.setCodigo(actproducto.getCodigo());

                    // --
                    int entero = Integer.parseInt(cant);

                    objproducto.setCantidad(entero);

                    Double totalString = Double.parseDouble(pre);
                    objproducto.setPrecio(totalString);

                    objproducto.setEstado(est);

                    // valores de distrito



                    //agregamos los valores
                    res=daoproducto.ActualizarProducto(objproducto,raiz.getContext());
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(), "Se registro el producto correctamente","Registrar Producto");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstProducto.setAdapter(adaptadorProducto);
                        //Limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmProducto));
                        txtNom.requestFocus();
                        Log.e("Error",""+registrocategoria);

                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se registro el producto correctamente","Registrar Producto");
                        CargarFragmento();
                    }

                }
            }
        });
        lstProducto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //obtenemos el valor de la fila seleccionada
                fila=i;
                btnActualizar.setEnabled(true);
                //asignando  los valores a los controles
                actproducto = registroproducto.get(fila);
                lblCodPro.setText(""+registroproducto.get(fila).getCodigo());
                txtNom.setText(registroproducto.get(fila).getNombre());

                txtNom.setText(actproducto.getNombre());
                txtPre.setText(String.valueOf(actproducto.getPrecio()));
                txtCan.setText(String.valueOf(actproducto.getCantidad()));

                chkEst.setChecked(actproducto.getEstado() == 1);

                cboCategoria.setSelection((int) adaptadorComboCategoria.getItemId(actproducto.getCategoria().getCodigo()));



            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validando que seleccione un elemento de la lista
                if(fila>=0){
                    cod=Integer.parseInt(lblCodPro.getText().toString());
                    //enviamos el codigo
                    objproducto.setCodigo(cod);
                    //eliminamos
                    res=daoproducto.EliminarProducto(objproducto);
                    if (res) {
                        objgeneral.Mensaje(raiz.getContext(), "Se alimino el Producto correctamente", "Eliminar Producto");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstProducto.setAdapter(adaptadorProducto);
                        //limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmProducto));
                        txtNom.requestFocus();


                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se elimino el Producto correctamente", "Eliminar Producto");
                        CargarFragmento();
                    }


                }else{
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un elemento de la lista", "Eliminar Producto");
                    CargarFragmento();

                }

            }
        });

        return raiz;


    }

    public void CargarFragmento(){
        ActividadProducto fproducto= new ActividadProducto();
        ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor,fproducto, ActividadProducto.TAG);
        ft.addToBackStack(null);
        ft.commit();
    }
}