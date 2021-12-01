package pe.trabajo1.appciberelectrik.imp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import pe.trabajo1.appciberelectrik.bd.Conexion;
import pe.trabajo1.appciberelectrik.bean.Categoria;
import pe.trabajo1.appciberelectrik.dao.CategoriaDAO;


public class CategoriaImp implements CategoriaDAO{

    private Conexion objconexion;
    private ArrayList<Categoria> registrocategoria=null;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ContentValues valores;
    private SQLiteOpenHelper xcon;
    private int version=1;
    private String nombre="bdciberelectrik";


    public CategoriaImp() {
        registrocategoria = new ArrayList<>();
    }

    @Override
    public ArrayList<Categoria> MostrarCategoria(Context contexto) {
        try{
            //creamos la conexion
            objconexion=new Conexion(contexto,nombre,null,version);
            //creamos un cursor y realizamos la consulta a la base de datos
            db=objconexion.getWritableDatabase();
            cursor=db.rawQuery("select * from t_categoria  where estcat=1", null);

            if(cursor.getCount()>0){

                while (cursor.moveToNext()){
                    Categoria objcategoria=new Categoria();
                    //le asignamos los valores al objeto
                    objcategoria.setCodigo(cursor.getInt(0));
                    objcategoria.setNombre(cursor.getString(1));
                    objcategoria.setEstado(cursor.getInt(2));
                    //enviamos  los datos al arreglo
                    registrocategoria.add(objcategoria);
                };

            }else{
                registrocategoria=null;
            }
            return  registrocategoria;
        }catch (Exception ex){
            Log.e("Error:",ex.toString());
            return null;
        }
    }


    @Override
    public boolean RegistrarCategoria(Categoria c, Context contexto) {
        objconexion=new Conexion(contexto,nombre,null,version);
        db=objconexion.getWritableDatabase();
        try{
            valores=new ContentValues();
            valores.put("nomcat",c.getNombre());
            valores.put("estcat",c.getEstado());
            long res=db.insert("t_categoria",null,valores);
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
    public boolean ActualizarCategoria(Categoria c) {
        try{
            //creamos los valores
            valores=new ContentValues();
            //asignamos los valores
            valores.put("nomcat",c.getNombre());
            valores.put("estcat",c.getEstado());
            //ejecutamos la actualizacion
            int res=db.update("t_categoria",valores,"codcat="+c.getCodigo(),null);
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
    public boolean EliminarCategoria(Categoria c){
        try {
            //eliminar clasico--> Eliminar fisico
            //int res=db.delete("t_perfil","codper="+p.getCodigo(),null);
            //creamos los valores
            valores=new ContentValues();
            //asignamos los valores
            valores.put("estcat",0);
            int res=db.update("t_categoria",valores,"codcat="+c.getCodigo(),null);
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

