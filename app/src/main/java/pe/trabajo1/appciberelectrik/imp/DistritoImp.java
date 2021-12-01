package pe.trabajo1.appciberelectrik.imp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import pe.trabajo1.appciberelectrik.bd.Conexion;
import pe.trabajo1.appciberelectrik.bean.Distrito;
import pe.trabajo1.appciberelectrik.dao.DistritoDAO;

public class DistritoImp implements DistritoDAO {

    //Creamos un objeto de la clase Conexion
    private Conexion objconexion;
    //generar un ArrayList
    ArrayList<Distrito> registrodistrito=null;
    //creamos una variable para trabajar con la base de datos
    private SQLiteDatabase db;
    //creamos un cursor
    private Cursor cursor;
    //declarar una variable para el manejo de valores
    private ContentValues valores;
    //Creamos una variable para la conexion
    private SQLiteOpenHelper xcon;
    //Creamos variables para la creacion de la base de datos
    private int version=1;
    //el nombre de la base de datos
    private String nombre="bdciberelectrik";


    public DistritoImp(){
        //creamos el ArrayList
        registrodistrito = new ArrayList<>();
    }

    @Override
    public ArrayList<Distrito> MostrarDistrito(Context contexto) {
        try{
            //creamos la conexion
            objconexion=new Conexion(contexto,nombre,null,version);
            //creamos un cursor y realizamos la consulta a la base de datos
            db=objconexion.getWritableDatabase();
            cursor=db.rawQuery("select * from t_distrito order by estdis", null);


            if(cursor.getCount()>0){
                //llenamos el cursor

                while(cursor.moveToNext()){
                    Distrito objdistrito =new Distrito();
                    //le asignamos los valores al objeto
                    objdistrito.setCodigo(cursor.getInt(0));
                    objdistrito.setNombre(cursor.getString(1));
                    objdistrito.setEstado(cursor.getInt(2));
                    //enviamos  los datos al arreglo
                    registrodistrito.add(objdistrito);
                }

            }else{

                registrodistrito=null;
            }
            return  registrodistrito;
        }catch (Exception ex){
            Log.e("Error:",ex.toString());
            return null;
        }

    }

    @Override
    public boolean RegistrarDistrito(Distrito d, Context contexto) {
        //creamos la conexion
        objconexion=new Conexion(contexto,nombre,null,version);
        //asignamos el valor para  que la base de datos se pueda escribir
        db=objconexion.getWritableDatabase();
        try{
            //creamos el ContentValues
            valores=new ContentValues();
            //asignamos los valores
            valores.put("nomdis",d.getNombre());
            valores.put("estdis",d.getEstado());
            //ejecutamos la insercion
            long res=db.insert("t_distrito",null,valores);
            if(res>0){

                return true;
            }else{
                return false;
            }


        }catch (Exception ex){
            Log.e("Error",ex.toString());
            return false;
        }
    }

    @Override
    public boolean ActualizarDistrito(Distrito d) {
        try{
            //creamos los valores
            valores=new ContentValues();
            //asignamos los valores
            valores.put("nomdis",d.getNombre());
            valores.put("estdis",d.getEstado());
            //ejecutamos la actualizacion
            int res=db.update("t_distrito",valores,"coddis="+d.getCodigo(),null);
            if(res==1){
                return true;
            }else {
                return false;
            }
        }catch (Exception ex){
            Log.e("Error:",ex.toString());
            return false;
        }
    }

    @Override
    public boolean EliminarDistrito(Distrito d) {
        try {
            //eliminar clasico--> Eliminar fisico
            //int res=db.delete("t_perfil","codper="+p.getCodigo(),null);
            //creamos los valores
            valores=new ContentValues();
            //asignamos los valores
            valores.put("estdis",0);
            int res=db.update("t_distrito",valores,"coddis="+d.getCodigo(),null);
            if (res==1){
                return true;
            }else {
                return false;
            }
        }catch (Exception ex){
            Log.e("Error:", ex.toString());
            return false;
        }
    }
}
