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
import pe.trabajo1.appciberelectrik.bean.Producto;
import pe.trabajo1.appciberelectrik.dao.ProductoDAO;

public class ProductoImp implements ProductoDAO {


    private Conexion objconexion;
    private ArrayList<Producto> registroproducto=null;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ContentValues valores;
    private SQLiteOpenHelper xcon;
    private int version=1;
    private String nombre="bdciberelectrik";

    public ProductoImp() {
        registroproducto=new ArrayList<>();
    }

    @Override
    public ArrayList<Producto> MostrarProducto(Context contexto) {
        try {
            objconexion=new Conexion(contexto,nombre,null,version);
            db=objconexion.getWritableDatabase();
            cursor=db.rawQuery(
                    "select p.codpro, p.nompro, p.prepro,p.canpro,p.estpro,c.nomcat " +
                            "from t_producto p inner join t_categoria c on " +
                            "p.codcat=c.codcat where p.estpro=1",null);
            if(cursor.getCount()>0){
                while(cursor.moveToNext()){
                    Producto objproducto = new Producto();
                    Categoria objcategoria=new Categoria();
                    objproducto.setCodigo(cursor.getInt(0));
                    objproducto.setNombre(cursor.getString(1));
                    objproducto.setPrecio(cursor.getDouble(2));
                    objproducto.setCantidad(cursor.getInt(3));
                    objproducto.setEstado(cursor.getInt(4));
                    objcategoria.setNombre(cursor.getString(5));
                    objproducto.setCategoria(objcategoria);
                    registroproducto.add(objproducto);
                }
            }else{
                registroproducto=null;
            }
            return registroproducto;
        }catch (Exception ex){
            Log.e("Error:",ex.toString());
            return null;
        }
    }

    @Override
    public boolean RegistrarProducto(Producto p, Context contexto) {
        objconexion=new Conexion(contexto,nombre,null,version);
        db=objconexion.getWritableDatabase();
        try{
            valores=new ContentValues();
            valores.put("nompro",p.getNombre());
            valores.put("prepro",p.getPrecio());
            valores.put("canpro",p.getCantidad());
            valores.put("estpro",p.getEstado());
            valores.put("codcat",p.getCategoria().getCodigo());
            long res=db.insert("t_producto",null,valores);
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
    public boolean ActualizarProducto(Producto p,Context contexto) {
        objconexion=new Conexion(contexto,nombre,null,version);
        //asignamos el valor para  que la base de datos se pueda escribir
        db=objconexion.getWritableDatabase();
        try{
            valores=new ContentValues();
            valores.put("nompro",p.getNombre());
            valores.put("prepro",p.getPrecio());
            valores.put("canpro",p.getCantidad());
            valores.put("estpro",p.getEstado());
            valores.put("codcat",p.getCategoria().getCodigo());
            int res=db.update("t_producto",valores,"codpro="+p.getCodigo(),null);
            if(res>0){
                return true;
            }else{
                return false;
            }

        }catch (Exception ex){
            Log.e("Error:",ex.toString());
            return false;
        }
    }

    @Override
    public boolean EliminarProducto(Producto p) {
        try{
            valores=new ContentValues();
            valores.put("estpro",0);
            int res=db.update("t_producto",valores,"codpro="+p.getCodigo(),null);
            if(res==1){
                return true;
            }else{
                return false;
            }
        }catch (Exception ex){
            Log.e("Error:",ex.toString());
            return false;
        }
    }
}

