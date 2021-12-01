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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import pe.trabajo1.appciberelectrik.R;
import pe.trabajo1.appciberelectrik.adaptadores.AdaptadorCliente;
import pe.trabajo1.appciberelectrik.adaptadores.AdaptadorComboDistrito;
import pe.trabajo1.appciberelectrik.bean.Cliente;
import pe.trabajo1.appciberelectrik.bean.Distrito;
import pe.trabajo1.appciberelectrik.dao.ClienteDAO;
import pe.trabajo1.appciberelectrik.dao.DistritoDAO;
import pe.trabajo1.appciberelectrik.dao.PerfilDAO;
import pe.trabajo1.appciberelectrik.imp.ClienteImp;
import pe.trabajo1.appciberelectrik.imp.DistritoImp;
import pe.trabajo1.appciberelectrik.imp.PerfilImp;
import pe.trabajo1.appciberelectrik.util.General;


public class ActividadCliente extends Fragment {
    //creamos un objeto de la clase general
    General objgeneral= new General();
    //Creamos un objetpo de la clase Cliente
    Cliente objcliente = new Cliente();
    //Creamos un objeto de la clase Cliente para actualizar
    Cliente actCliente = new Cliente();
    //creamos un adaptador de cliente
    AdaptadorCliente adaptadorCliente;
    AdaptadorCliente adaptor;
    //creamos una variable estatica
    public static final String TAG = "Fragmento Empleado";
    //creando controles
    private TextView lblCoddClie;
    private Spinner cboCategoria, cboDistrito;
    private EditText txtNom, txtApep, txtApem, txtDir, txtDni,
            txtTel, txtCel, txtCor, txtUsu, txtCla;
    private TextView lblCodDis, lblCodPer;
    private RadioButton rbMas, rbFem, rbOtr;
    private RadioGroup rbgSex;
    private CheckBox chkEst;
    private ListView lstCliente;
    private Button btnRegistrar, btnActualizar, btnEliminar;
    //declaramos una variable para las transiciones del fragmento
    FragmentTransaction ft;

    //implemento la interfaz Perfil
    PerfilDAO daoperfil = new PerfilImp();
    //creamos un Adaptador de tipo Distrito
    AdaptadorComboDistrito adaptadorComboDistrito;

    //creamos un Arraylist de tipo Perfil
    ArrayList<Cliente> registrocliente;
    //cremoas un arreglo de tipo Distrito
    ArrayList<Distrito> registrodistrito;

    //implementamos la interfaz distrito
    DistritoDAO daodistrito = new DistritoImp();
    ClienteDAO daocliente = new ClienteImp();
    //creamos una clase de tipo distrito
    Distrito objdistrito= new Distrito();

    //declarando variables
    private int cod=0, codd=0, codp=0, est=-1, fila=1;
    private String nom="",apep="", apem="", dni="", dir="",tel="",
            cel="",cor="",sex="",usu="",cla="";
    private boolean res=false;


    public ActividadCliente() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        final View raiz=inflater.inflate(R.layout.actividad_cliente, container, false);
        //creamos los controles
        lblCoddClie=raiz.findViewById(R.id.lblCoddClie);
        txtNom=raiz.findViewById(R.id.txtNom);
        txtApep=raiz.findViewById(R.id.txtApep);
        txtApem=raiz.findViewById(R.id.txtApem);
        txtDir=raiz.findViewById(R.id.txtDir);
        txtDni=raiz.findViewById(R.id.txtDni);
        txtTel=raiz.findViewById(R.id.txtTel);
        txtCel=raiz.findViewById(R.id.txtCel);
        txtCor=raiz.findViewById(R.id.txtCor);
        cboDistrito=raiz.findViewById(R.id.cboDistrito);
        lblCodDis=raiz.findViewById(R.id.lblCodDis);
        rbMas=raiz.findViewById(R.id.rbMas);
        rbFem=raiz.findViewById(R.id.rbFem);
        rbOtr=raiz.findViewById(R.id.rbOtr);
        rbgSex=raiz.findViewById(R.id.rbgSex);
        chkEst=raiz.findViewById(R.id.chkEst);
        btnRegistrar=raiz.findViewById(R.id.btnRegistrar);
        btnActualizar=raiz.findViewById(R.id.btnActualizar);
        btnEliminar=raiz.findViewById(R.id.btnEliminar);
        lstCliente=raiz.findViewById(R.id.lstCliente);
        //creamos el arreglo
        registrodistrito=new ArrayList<>();
        registrocliente=new ArrayList<>();
        //llamamos a la funcion mostrar perfiles
        registrodistrito=daodistrito.MostrarDistrito(raiz.getContext());
        registrocliente=daocliente.MostrarCliente(raiz.getContext());
        if(registrodistrito != null) {
            adaptadorComboDistrito = new AdaptadorComboDistrito(raiz.getContext(), registrodistrito);
            cboDistrito.setAdapter(adaptadorComboDistrito);
        }
        if(registrocliente != null) {
            adaptadorCliente = new AdaptadorCliente(raiz.getContext(), registrocliente);
            lstCliente.setAdapter(adaptadorCliente);
        }

        //agregamos eventos
        //evento para el combo distrito
        cboDistrito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int coddis=registrodistrito.get(i).getCodigo();
                lblCodDis.setText(""+coddis);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //evento boton registrar
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNom.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el nombre del perfil" ,"Registran Empleado");
                    txtNom.requestFocus();
                    raiz.findViewById(R.id.frmEmpleado);
                }else if(cboDistrito.getSelectedItemPosition()==-1) {
                    objgeneral.Mensaje(raiz.getContext(),"Seleccione un disrito" ,"Registran Empleado");
                    cboDistrito.requestFocus();
                    raiz.findViewById(R.id.frmEmpleado);
                } else if(rbgSex.getCheckedRadioButtonId() == -1) {
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un sexo", "Registran Cliente");
                    rbgSex.requestFocus();
                    raiz.findViewById(R.id.frmEmpleado);
                }
                else{
                    //capturando valores

                    nom=txtNom.getText().toString();
                    apep=txtApep.getText().toString();
                    apem=txtApem.getText().toString();
                    dni=txtDni.getText().toString();
                    dir=txtDir.getText().toString();
                    tel=txtTel.getText().toString();
                    cel=txtCel.getText().toString();
                    cor=txtCor.getText().toString();



                    int radioButtonID = rbgSex.getCheckedRadioButtonId();
                    View radioButton = rbgSex.findViewById(radioButtonID);
                    int idx = rbgSex.indexOfChild(radioButton);
                    RadioButton r = (RadioButton) rbgSex.getChildAt(idx);

                    sex = r.getText().toString();

                    codd=Integer.parseInt(lblCodDis.getText().toString());
                    if(chkEst.isChecked()){
                        est=1;
                    }else{
                        est=0;
                    }
                    //enviamos los valores a la clase

                    // valores de distrito
                    objdistrito.setCodigo(codd);


                    // valores de empleado
                    objcliente.setNombre(nom);
                    // seteando objetos de distrito y perfil en empl
                    objcliente.setDistrito(objdistrito);
                    // --
                    objcliente.setCelular(cel);
                    objcliente.setDni(dni);
                    objcliente.setDireccion(dir);
                    objcliente.setApellidop(apep);
                    objcliente.setCorreo(cor);
                    objcliente.setTelefono(tel);
                    objcliente.setApellidom(apem);
                    objcliente.setSexo(sex);
                    objcliente.setEstado(est);


                    //agregamos los valores
                    res=daocliente.RegistrarCliente(objcliente, raiz.getContext());
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(), "Se registro el cliente correctamente","Registrar Cliente");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstCliente.setAdapter(adaptor);
                        //Limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmCliente));
                        txtNom.requestFocus();
                        Log.i(">",""+registrocliente);

                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se registro el cliente correctamente","Registrar Cliente");
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
                    raiz.findViewById(R.id.frmCliente);
                }else if(cboDistrito.getSelectedItemPosition()==-1) {
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un disrito", "Registran Cliente");
                    cboDistrito.requestFocus();
                    raiz.findViewById(R.id.frmCliente);
                }
                else if(rbgSex.getCheckedRadioButtonId() == -1) {
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un sexo", "Registran Cliente");
                    rbgSex.requestFocus();
                    raiz.findViewById(R.id.frmCliente);
                }
                else{
                    //capturando valores

                    nom=txtNom.getText().toString();
                    apep=txtApep.getText().toString();
                    apem=txtApem.getText().toString();
                    dni=txtDni.getText().toString();
                    dir=txtDir.getText().toString();
                    tel=txtTel.getText().toString();
                    cel=txtCel.getText().toString();
                    cor=txtCor.getText().toString();
                    cod = Integer.parseInt(lblCoddClie.getText().toString());



                    int radioButtonID = rbgSex.getCheckedRadioButtonId();
                    View radioButton = rbgSex.findViewById(radioButtonID);
                    int idx = rbgSex.indexOfChild(radioButton);
                    RadioButton r = (RadioButton) rbgSex.getChildAt(idx);

                    sex = r.getText().toString();

                    codd=Integer.parseInt(lblCodDis.getText().toString());

                    if(chkEst.isChecked()){
                        est=1;
                    }else{
                        est=0;
                    }

                    // valores de cliente
                    objcliente.setNombre(nom);
                    objcliente.setCodigo(actCliente.getCodigo());
                    // valores de distrito
                    objdistrito.setCodigo(codd);
                    objcliente.setDistrito(objdistrito);
                    objcliente.setCelular(cel);
                    objcliente.setDni(dni);
                    objcliente.setDireccion(dir);
                    objcliente.setApellidop(apep);
                    objcliente.setCorreo(cor);
                    objcliente.setTelefono(tel);
                    objcliente.setApellidom(apem);
                    objcliente.setSexo(sex);
                    objcliente.setEstado(est);


                    //agregamos los valores
                    res=daocliente.ActualizarCliente(objcliente, raiz.getContext());
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(), "Se actualizo el cliente correctamente","Actualizar Cliente");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstCliente.setAdapter(adaptor);
                        //Limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmCliente));
                        txtNom.requestFocus();
                        Log.i(">",""+registrocliente);

                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se actualizo el cliente correctamente","Actualizar Cliente");
                        CargarFragmento();
                    }

                }
            }
        });

        lstCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //obtenemos el valor de la fila seleccionada
                fila=i;
                btnActualizar.setEnabled(true);
                //asignando  los valores a los controles
                actCliente = registrocliente.get(fila);
                lblCoddClie.setText(""+registrocliente.get(fila).getCodigo());




                txtNom.setText(actCliente.getNombre());
                txtApep.setText(actCliente.getApellidop());
                txtApem.setText(actCliente.getApellidom());
                txtDni.setText(actCliente.getDni());
                txtDir.setText(actCliente.getDireccion());
                txtTel.setText(actCliente.getTelefono());
                txtCel.setText(actCliente.getCelular());
                txtCor.setText(actCliente.getCorreo());
                chkEst.setChecked(actCliente.getEstado() == 1);
                cboDistrito.setSelection((int) adaptadorComboDistrito.getItemId(actCliente.getDistrito().getCodigo()));
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validando que seleccione un elemento de la lista
                if(fila>=0){
                    cod=Integer.parseInt(lblCoddClie.getText().toString());
                    //enviamos el codigo
                    objcliente.setCodigo(cod);
                    //eliminamos
                    res=daocliente.EliminarCliente(objcliente);
                    if (res) {
                        objgeneral.Mensaje(raiz.getContext(), "Se alimino el Distrito correctamente", "Eliminar Distrito");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstCliente.setAdapter(adaptor);
                        //limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmCliente));
                        txtNom.requestFocus();


                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se elimino el cliente correctamente", "Eliminar Cliente");
                        CargarFragmento();
                    }


                }else{
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un elemento de la lista", "Eliminar Cliente");
                    CargarFragmento();

                }

            }
        });


        return raiz;

    }

    public void CargarFragmento(){
        ActividadCliente fcliente= new ActividadCliente();
        ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor,fcliente, ActividadCliente.TAG);
        ft.addToBackStack(null);
        ft.commit();
    }
}