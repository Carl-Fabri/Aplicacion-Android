package pe.trabajo1.appciberelectrik.remote;

import java.util.List;

import pe.trabajo1.appciberelectrik.modelo.Categoria;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoriaService {

    //metodo para mostrar la categoria
    @GET("/categoria")
    Call<List<Categoria>> MostrarCategoria();

    //metodo para agregar la categoria
    @POST("/categoria")
    Call<Categoria> AgregarCategoria(@Body Categoria c);

    //metodo para agregar la categoria
    @PUT("/categoria/{id}")
    Call<Categoria> ActualizarCategoria(@Path("id") int id, @Body Categoria c);

    //metodo para eliminar categoria
    @DELETE("/categoria/{id}")
    Call<Categoria> EliminarCategoria(@Path("id") int id);
}
