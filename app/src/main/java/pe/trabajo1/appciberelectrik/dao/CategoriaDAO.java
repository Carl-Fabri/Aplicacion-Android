package pe.trabajo1.appciberelectrik.dao;

import android.content.Context;

import java.util.ArrayList;

import pe.trabajo1.appciberelectrik.bean.Categoria;

public interface CategoriaDAO {
    public ArrayList<Categoria> MostrarCategoria(Context contexto);
    public boolean RegistrarCategoria(Categoria c, Context contexto);
    public boolean ActualizarCategoria(Categoria c);
    public boolean EliminarCategoria(Categoria c);
}
