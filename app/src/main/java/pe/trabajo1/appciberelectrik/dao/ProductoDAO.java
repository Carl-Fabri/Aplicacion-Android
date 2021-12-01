package pe.trabajo1.appciberelectrik.dao;

import android.content.Context;

import java.util.ArrayList;

import pe.trabajo1.appciberelectrik.bean.Producto;

public interface ProductoDAO {
        public ArrayList<Producto> MostrarProducto(Context contexto);
        public boolean RegistrarProducto(Producto p, Context contexto);
        public boolean ActualizarProducto(Producto p,Context contexto);
        public boolean EliminarProducto(Producto p);

}
