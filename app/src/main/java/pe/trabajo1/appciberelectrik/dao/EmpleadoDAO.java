package pe.trabajo1.appciberelectrik.dao;

import android.content.Context;

import java.util.ArrayList;

import pe.trabajo1.appciberelectrik.bean.Empleado;

public interface EmpleadoDAO {
    public ArrayList<Empleado> MostrarEmpleado(Context contexto);
    public boolean RegistrarEmpleado(Empleado e, Context contexto);
    public boolean ActualizarEmpleado(Empleado e, Context contexto);
    public boolean EliminarEmpleado(Empleado e);

}
