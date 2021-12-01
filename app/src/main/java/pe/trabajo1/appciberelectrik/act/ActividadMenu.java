package pe.trabajo1.appciberelectrik.act;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import pe.trabajo1.appciberelectrik.R;
import pe.trabajo1.appciberelectrik.databinding.ActivityMainBinding;

public class ActividadMenu extends AppCompatActivity {
    //declaramos una variable para las transisiones del fragmento
    FragmentTransaction ft;

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //creando el menu
        switch(id){
            case R.id.jmiPerfil:
                ActividadPerfil fperfil= new ActividadPerfil();
                ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor,fperfil, ActividadPerfil.TAG);
                ft.addToBackStack(null);
                ft.commit();
                return true;
            case R.id.jmiEmpleado:
                ActividadEmpleado fempleado= new ActividadEmpleado();
                ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor,fempleado, ActividadEmpleado.TAG);
                ft.addToBackStack(null);
                ft.commit();
                return true;
            case R.id.jmiCliente:
                ActividadCliente fcliente= new ActividadCliente();
                ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor,fcliente, ActividadCliente.TAG);
                ft.addToBackStack(null);
                ft.commit();
                return true;
            case R.id.jmiDistrito:
                ActividadDistrito fdistrito = new ActividadDistrito();
                ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor,fdistrito, ActividadDistrito.TAG);
                ft.addToBackStack(null);
                ft.commit();
                return true;

            case R.id.jmiCategoria:
                ActividadCategoria fcategoria=new ActividadCategoria();
                ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor,fcategoria, ActividadCategoria.TAG);
                ft.addToBackStack(null);
                ft.commit();
                return true;

            case R.id.jmiProducto:
                ActividadProducto fproducto=new ActividadProducto();
                ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor,fproducto, ActividadProducto.TAG);
                ft.addToBackStack(null);
                ft.commit();
                return true;



        }
        return super.onOptionsItemSelected(item);
    }


}