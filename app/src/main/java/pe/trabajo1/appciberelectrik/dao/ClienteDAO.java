package pe.trabajo1.appciberelectrik.dao;

import android.content.Context;

import java.util.ArrayList;

import pe.trabajo1.appciberelectrik.bean.Cliente;

public interface ClienteDAO {
    public ArrayList<Cliente> MostrarCliente(Context contexto);
    public boolean RegistrarCliente(Cliente c, Context contexto);
    public boolean ActualizarCliente(Cliente c, Context contexto);
    public boolean EliminarCliente(Cliente c);
}
