package pe.trabajo1.appciberelectrik.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class General {

    FragmentTransaction ft;
    //declarando variables
    private AlertDialog.Builder dialogo;
    //creamos un procedimiento que nos permita mostrar un cuadro de dialogo
    //cada vez que vamos a salir del sistema
    public void SalirSistema(final Context contexto){
        //Creando el cuadro de dialogo
        dialogo= new AlertDialog.Builder(contexto);
        //agregando un titulo al cuadro de dialogo
        dialogo.setTitle("Saliendo del Sistema");
        //agregamos el mensaje que se mostrara en el cuadro de dialogo
        dialogo.setMessage("¿Estas seguro que quiers salir del sistema?");
        //deshabilitamos la funcion
        dialogo.setCancelable(false);
        //programamos el boton si
        dialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {                //creamos el Intent
                ((Activity)contexto).finish();
            }
        });
        //programando el boton no
        dialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        //mostramos los cuadros de dialogo
        dialogo.show();
    }


    public void Mensaje(final Context contexto,String mensaje,String titulo){
        //Creando el cuadro de dialogo
        dialogo= new AlertDialog.Builder(contexto);
        //agregando un titulo al cuadro de dialogo
        dialogo.setTitle(titulo);
        //agregamos el mensaje que se mostrara en el cuadro de dialogo
        dialogo.setMessage(mensaje);
        //deshabilitamos la funcion
        dialogo.setCancelable(false);
        //programamos el boton si
        dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {                //creamos el Intent

            }
        });
        //mostramos los cuadros de dialogo
        dialogo.show();
    }

    public void Limpiar(ViewGroup group){
        for (int i=0, count=group.getChildCount();i<count;i++){
            View view=group.getChildAt(i);
            if(view instanceof EditText){
                ((EditText)view).setText("");
            }
            if(view instanceof RadioGroup){
                ((RadioGroup)view).clearCheck();
            }
            if(view instanceof CheckBox){
                ((CheckBox)view).setChecked(false);
            }
            if(view instanceof Spinner){
                ((Spinner)view).setSelection(0);

            }
            if(view instanceof  ViewGroup && ((ViewGroup)view).getChildCount()>0){
                Limpiar(((ViewGroup) view));
            }
        }
    }

}
