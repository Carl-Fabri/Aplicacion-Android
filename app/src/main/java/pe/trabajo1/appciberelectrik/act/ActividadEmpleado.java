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
import pe.trabajo1.appciberelectrik.adaptadores.AdaptadorComboDistrito;
import pe.trabajo1.appciberelectrik.adaptadores.AdaptadorComboPerfil;
import pe.trabajo1.appciberelectrik.adaptadores.AdaptadorEmpleado;
import pe.trabajo1.appciberelectrik.bean.Distrito;
import pe.trabajo1.appciberelectrik.bean.Empleado;
import pe.trabajo1.appciberelectrik.bean.Perfil;
import pe.trabajo1.appciberelectrik.dao.DistritoDAO;
import pe.trabajo1.appciberelectrik.dao.EmpleadoDAO;
import pe.trabajo1.appciberelectrik.dao.PerfilDAO;
import pe.trabajo1.appciberelectrik.imp.DistritoImp;
import pe.trabajo1.appciberelectrik.imp.EmpleadoImp;
import pe.trabajo1.appciberelectrik.imp.PerfilImp;
import pe.trabajo1.appciberelectrik.util.General;



public class ActividadEmpleado extends Fragment {

    //creamos un objeto de la clase general
    General objgeneral= new General();
    //Creamos un objetpo de la clase Empleado
    Empleado objempleado= new Empleado();

    Empleado actEmpleado = new Empleado();

    //Implementamos la interfaz empleado
    EmpleadoDAO daoempleado= new EmpleadoImp();
    //creamos un adaptador de empleado
    AdaptadorEmpleado adaptador;
    AdaptadorEmpleado adaptadorEmpleado;
    //creamos una variable estatica
    public static final String TAG = "Fragmento Empleado";
    //creando controles
    private Spinner cboPerfil, cboDistrito;
    private EditText txtNom, txtApep, txtApem, txtDir, txtDni,
            txtTel, txtCel, txtCor, txtUsu, txtCla;
    private TextView lblCodDis, lblCodPer,lblCodEmp ;
    private RadioButton rbMas, rbFem, rbOtr;
    private RadioGroup rbgSex;
    private CheckBox chkEst;
    private ListView lstEmpleado;
    private Button btnRegistrar, btnActualizar, btnEliminar;
    //declaramos una variable para las transiciones del fragmento
    FragmentTransaction ft;
    //creamos un adaptador de tipo ComboPerfil
    AdaptadorComboPerfil adaptadorComboPerfil;
    //creamos un Arraylist de tipo Perfil
    ArrayList<Perfil> registroperfil;
    //implemento la interfaz Perfil
    PerfilDAO daoperfil = new PerfilImp();
    //creamos un Adaptador de tipo Distrito
    AdaptadorComboDistrito adaptadorComboDistrito;


    //cremoas un arreglo de tipo Distrito
    ArrayList<Distrito> registrodistrito;
    //implementamos la interfaz distrito
    DistritoDAO daodistrito = new DistritoImp();
    //declarando variables
    ArrayList<Empleado> registroempleado;

    private int cod=0, codd=0, codp=0, est=-1, fila=1;
    private String nom="",apep="", apem="", dni="", dir="",tel="",
            cel="",cor="",sex="",usu="",cla="";
    private boolean res=false;
    //creamos una clase de tipo distrito
    Distrito objdistrito= new Distrito();
    //creamos una clase de tipo Perfil
    Perfil objperfil= new Perfil();



    public ActividadEmpleado() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View raiz=inflater.inflate(R.layout.actividad_empleado, container, false);
        //creamos los controles
        lblCodEmp=raiz.findViewById(R.id.lblCodEmp);
        txtNom=raiz.findViewById(R.id.txtNom);
        txtApep=raiz.findViewById(R.id.txtApep);
        txtApem=raiz.findViewById(R.id.txtApem);
        txtDir=raiz.findViewById(R.id.txtDir);
        txtDni=raiz.findViewById(R.id.txtDni);
        txtTel=raiz.findViewById(R.id.txtTel);
        txtCel=raiz.findViewById(R.id.txtCel);
        txtCor=raiz.findViewById(R.id.txtCor);
        txtUsu=raiz.findViewById(R.id.txtUsu);
        txtCla=raiz.findViewById(R.id.txtCla);
        cboPerfil=raiz.findViewById(R.id.cboPerfil);
        cboDistrito=raiz.findViewById(R.id.cboDistrito);
        lblCodDis=raiz.findViewById(R.id.lblCodDis);
        lblCodPer=raiz.findViewById(R.id.lblCodEmp);
        rbMas=raiz.findViewById(R.id.rbMas);
        rbFem=raiz.findViewById(R.id.rbFem);
        rbOtr=raiz.findViewById(R.id.rbOtr);
        rbgSex=raiz.findViewById(R.id.rbgSex);
        chkEst=raiz.findViewById(R.id.chkEst);
        lstEmpleado=raiz.findViewById(R.id.lstEmpleado);
        btnRegistrar=raiz.findViewById(R.id.btnRegistrar);
        btnActualizar=raiz.findViewById(R.id.btnActualizar);
        btnEliminar=raiz.findViewById(R.id.btnEliminar);
        //creamos el arreglo
        registroperfil=new ArrayList<>();
        registrodistrito=new ArrayList<>();
        registroempleado=new ArrayList<>();
        //llamamos a la funcion mostrar perfiles
        registroperfil=daoperfil.MostrarPerfil(raiz.getContext());
        registrodistrito=daodistrito.MostrarDistrito(raiz.getContext());
        registroempleado=daoempleado.MostrarEmpleado(raiz.getContext());

        btnActualizar.setEnabled(false);
        //evaluamos que haya elementos
        if(registroperfil!=null){
            //creamos el adaptador
            adaptadorComboPerfil=new AdaptadorComboPerfil(raiz.getContext(), registroperfil);
            //asignamos el adaptador al combo
            cboPerfil.setAdapter(adaptadorComboPerfil);
        }
        if(registrodistrito!=null){
            adaptadorComboDistrito=new AdaptadorComboDistrito(raiz.getContext(),registrodistrito);
            cboDistrito.setAdapter(adaptadorComboDistrito);
        }
        if(registroempleado!=null){
            adaptadorEmpleado=new AdaptadorEmpleado(raiz.getContext(),registroempleado);
            lstEmpleado.setAdapter(adaptadorEmpleado);
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
        //evento para el combo perfil
        cboPerfil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                int codper=registroperfil.get(i).getCodigo();
                lblCodPer.setText(""+codper);

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
                }else if(cboPerfil.getSelectedItemPosition()==-1) {
                    objgeneral.Mensaje(raiz.getContext(),"Seleccione un perfil" ,"Registran Empleado");
                    cboDistrito.requestFocus();
                    raiz.findViewById(R.id.frmEmpleado);
                }else if( !(rbMas.isChecked() || rbFem.isChecked() || rbOtr.isChecked())) {
                    objgeneral.Mensaje(raiz.getContext(),"Seleccione su Sexo" ,"Registran Empleado");
                    raiz.findViewById(R.id.frmEmpleado);
                }

                else{
                    //capturando valores
                    nom=txtNom.getText().toString();
                    codd=Integer.parseInt(lblCodDis.getText().toString());
                    codp=Integer.parseInt(lblCodPer.getText().toString());

                    apem=txtApem.getText().toString();
                    apep=txtApep.getText().toString();
                    dir=txtDir.getText().toString();
                    dni=txtDni.getText().toString();
                    tel=txtTel.getText().toString();

                    /* Llamando al rbg Seleccionado */
                    int rbgSelected=rbgSex.getCheckedRadioButtonId();
                    View dwe = rbgSex.findViewById(rbgSelected);
                    int idSeleccionado = rbgSex.indexOfChild(dwe);

                    RadioButton rbgSel = (RadioButton) rbgSex.getChildAt(idSeleccionado);
                    sex= rbgSel.getText().toString();

                    /* */


                    cel=txtCel.getText().toString();
                    cor=txtCor.getText().toString();
                    usu=txtUsu.getText().toString();
                    cla=txtCla.getText().toString();


                    if(chkEst.isChecked()){
                        est=1;
                    }else{
                        est=0;
                    }
                    //enviamos los valores a la clase
                    objempleado.setNombre(nom);
                    objdistrito.setCodigo(codd);
                    objempleado.setDistrito(objdistrito);
                    objperfil.setCodigo(codp);
                    objempleado.setPerfil(objperfil);
                    objempleado.setEstado(est);

                    /* Enviando valores extraños */

                    objempleado.setApellidom(apem);
                    objempleado.setApellidop(apep);
                    objempleado.setDireccion(dir);
                    objempleado.setDni(dni);
                    objempleado.setTelefono(tel);
                    objempleado.setCelular(cel);
                    objempleado.setCorreo(cor);
                    objempleado.setSexo(sex);
                    objempleado.setUsuario(usu);
                    objempleado.setClave(cla);



                    //agregamos los valores
                    res=daoempleado.RegistrarEmpleado(objempleado, raiz.getContext());
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(), "Se registro el perfil correctamente","Registrar Perfil");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstEmpleado.setAdapter(adaptador);
                        //Limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmEmpleado));
                        txtNom.requestFocus();
                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se registro el perfil correctamente","Registrar Perfil");
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
                    raiz.findViewById(R.id.frmEmpleado);
                }else if(cboDistrito.getSelectedItemPosition()==-1) {
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un disrito", "Registran Cliente");
                    cboDistrito.requestFocus();
                    raiz.findViewById(R.id.frmEmpleado);
                }

                else if(rbgSex.getCheckedRadioButtonId() == -1) {
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un sexo", "Registran Cliente");
                    rbgSex.requestFocus();
                    raiz.findViewById(R.id.frmEmpleado);
                }else if(cboPerfil.getSelectedItemPosition()==-1) {
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un Perfil", "Registran Cliente");
                    cboPerfil.requestFocus();
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
                    usu=txtUsu.getText().toString();
                    cla=txtCla.getText().toString();
                    cod = Integer.parseInt(lblCodEmp.getText().toString());



                    int radioButtonID = rbgSex.getCheckedRadioButtonId();
                    View radioButton = rbgSex.findViewById(radioButtonID);
                    int idx = rbgSex.indexOfChild(radioButton);
                    RadioButton r = (RadioButton) rbgSex.getChildAt(idx);

                    sex = r.getText().toString();

                    codd=Integer.parseInt(lblCodDis.getText().toString());
                    codp=Integer.parseInt(lblCodPer.getText().toString());

                    if(chkEst.isChecked()){
                        est=1;
                    }else{
                        est=0;
                    }

                    // valores de cliente
                    objperfil.setCodigo(codp);
                    objempleado.setPerfil(objperfil);
                    objempleado.setNombre(nom);
                    objempleado.setCodigo(actEmpleado.getCodigo());
                    // valores de distrito
                    objdistrito.setCodigo(codd);
                    objempleado.setDistrito(objdistrito);
                    objempleado.setCelular(cel);
                    objempleado.setDni(dni);

                    objempleado.setDireccion(dir);
                    objempleado.setApellidop(apep);
                    objempleado.setCorreo(cor);
                    objempleado.setTelefono(tel);
                    objempleado.setApellidom(apem);
                    objempleado.setSexo(sex);
                    objempleado.setEstado(est);
                    objempleado.setClave(cla);
                    objempleado.setUsuario(usu);


                    //agregamos los valores
                    res=daoempleado.ActualizarEmpleado(objempleado, raiz.getContext());
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(), "Se actualizo el cliente correctamente","Actualizar Cliente");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstEmpleado.setAdapter(adaptador);
                        //Limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmEmpleado));
                        txtNom.requestFocus();
                        Log.i(">",""+registroempleado);

                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se actualizo el cliente correctamente","Actualizar Cliente");
                        CargarFragmento();
                    }

                }
            }
        });





        lstEmpleado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                fila=i;
                //Habilitar Boton
                btnActualizar.setEnabled(true);

                actEmpleado = registroempleado.get(fila);
                lblCodEmp.setText(""+registroempleado.get(fila).getCodigo());
                txtNom.setText(registroempleado.get(fila).getNombre());

                txtNom.setText(actEmpleado.getNombre());
                txtApep.setText(actEmpleado.getApellidop());
                txtApem.setText(actEmpleado.getApellidom());
                txtDni.setText(actEmpleado.getDni());
                txtDir.setText(actEmpleado.getDireccion());
                txtTel.setText(actEmpleado.getTelefono());
                txtCel.setText(actEmpleado.getCelular());
                txtCor.setText(actEmpleado.getCorreo());
                chkEst.setChecked(actEmpleado.getEstado() == 1);
                txtUsu.setText(actEmpleado.getUsuario());
                txtCla.setText(actEmpleado.getClave());
                cboDistrito.setSelection((int) adaptadorComboDistrito.getItemId(actEmpleado.getDistrito().getCodigo()));
                cboPerfil.setSelection((int) adaptadorComboPerfil.getItemId(actEmpleado.getPerfil().getCodigo()));
                /*
                if (actEmpleado.getSexo().concat(toString()) == "Masculino" ){
                    rbMas.setChecked(true);
                }

               c if (actEmpleado.getSexo() != "Masculino"){
                    lblCodEmp.setText(actEmpleado.getSexo());
                }
                else if (actEmpleado.getSexo() == "Mujer"){
                    rbMas.setChecked(true);
                }
                else if (actEmpleado.getSexo() == "Otro"){
                    rbOtr.setChecked(true);
               }
                */
                /*
                //asignamos los valores a los controles
                lblCodEm.setText(""+registroempleado.get(fila).getCodigo());
                txtNom.setText(registroempleado.get(fila).getNombre());
                if (registroempleado.get(fila).getEstado()==1){
                    chkEst.setChecked(true);
                }else{
                    chkEst.setChecked(false);
                }

                 */
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validando que seleccione un elemento de la lista
                if(fila>=0){
                    cod=Integer.parseInt(lblCodEmp.getText().toString());
                    //enviamos el codigo
                    objempleado.setCodigo(cod);
                    //eliminamos
                    res=daoempleado.EliminarEmpleado(objempleado);
                    if (res) {
                        objgeneral.Mensaje(raiz.getContext(), "Se alimino el Distrito correctamente", "Eliminar Distrito");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstEmpleado.setAdapter(adaptador);
                        //limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmEmpleado));
                        txtNom.requestFocus();


                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se elimino el empleado correctamente", "Eliminar Empleado");
                        CargarFragmento();
                    }


                }else{
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un elemento de la lista", "Eliminar Empleado");
                    CargarFragmento();

                }

            }
        });

        return raiz;

    }




    public void CargarFragmento(){
        ActividadEmpleado fempleado= new ActividadEmpleado();
        ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor,fempleado, ActividadEmpleado.TAG);
        ft.addToBackStack(null);
        ft.commit();
    }

}
