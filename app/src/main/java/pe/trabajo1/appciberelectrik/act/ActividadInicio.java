package pe.trabajo1.appciberelectrik.act;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import pe.trabajo1.appciberelectrik.R;
import pe.trabajo1.appciberelectrik.util.General;

public class ActividadInicio extends AppCompatActivity {

    //Creamos un objeto de la clase General
    General objgeneral=new General();

    private EditText txtUsu,txtCla;

    private Button btnIngresar, btnSalir;

    //declaramos una variable para Intent
    private Intent formulario;
    //declaramos una variable para el contexto
    private Context contexto;

    //declarando variables
    private AlertDialog.Builder dialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_inicio);
        //definimos el contexto
        contexto=this;


        //Creamos los controles
        txtUsu=findViewById(R.id.txtUsu);
        txtCla=findViewById(R.id.txtCla);
        btnIngresar=findViewById(R.id.btnIngresar);
        btnSalir=findViewById(R.id.btnSalir);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                MensajeIngreso(contexto);

            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objgeneral.SalirSistema(contexto);
            }
        });

    }

    //creamos un procedimineto para que nos muestren los mensajes al ingreso de sistema
    public void MensajeIngreso(final Context contexto){
        //Creando el cuadro de dialogo
        dialogo= new AlertDialog.Builder(contexto);
        //agregando un titulo al cuadro de dialogo
        dialogo.setTitle("Ingresando al Sistema");
        //agregamos el mensaje que se mostrara en el cuadro de dialogo
        dialogo.setMessage("Bienvenido al Sistema");
        //deshabilitamos la funcion cancelar
        dialogo.setCancelable(false);


        //programamos el boton si
        dialogo.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                formulario=new Intent(contexto, ActividadMenu.class);
                startActivity(formulario);
                finish();
            }
        });

//        //programando el boton no
//        dialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });

        //mostramos los cuadros de dialogo
        dialogo.show();
    }
}