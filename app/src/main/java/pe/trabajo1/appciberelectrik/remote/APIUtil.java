package pe.trabajo1.appciberelectrik.remote;

public class APIUtil {
    private APIUtil(){
    }

    public static final String API_URL="http://26.220.117.240:8484/api-ciberelectrik";

    public static CategoriaService getCategoriaService(){
        return  RetrofitClient.getClient(API_URL).create(CategoriaService.class);
    }
}
