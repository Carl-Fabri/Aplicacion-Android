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
import pe.trabajo1.appciberelectrik.bean.Empleado;
import pe.trabajo1.appciberelectrik.bean.Perfil;
import pe.trabajo1.appciberelectrik.dao.EmpleadoDAO;

public class EmpleadoImp implements EmpleadoDAO {
    //creamos un objeto de la clase Conexion
    private Conexion objconexion;
    //general un ArrayList de tipo Distrito para los datos
    private  ArrayList<Empleado> registroempleado=null;
    //creamos una variable para trabajar con la base de datos
    private SQLiteDatabase db;
    //creamos un cursor -->ResultSet
    private Cursor cursor;
    //declarar una variable para edl manejar de los valores
    private ContentValues valores;
    //creamos una variable para la conexion
    private SQLiteOpenHelper xcon;
    //creamos variables para la creacion de la base de datos
    //la version de la base de datos
    private int version=1;
    //el nombre de la base de datos
    private String nombre="bdciberelectrik";
    //declaramos variables

    public  EmpleadoImp(){
        //creamos ArrayList
        registroempleado=new ArrayList<>();

    }

    @Override
    public ArrayList<Empleado> MostrarEmpleado(Context contexto) {
        try{
            //creamos la conexion
            objconexion=new Conexion(contexto,nombre,null,version);
            //creamos un cursor y realizamos la consulta a la base de datos
            db=objconexion.getWritableDatabase();
            cursor=db.rawQuery("select e.codemp,"+
                    "e.nomemp,"+
                    "e.apepemp,"+
                    "e.apememp,"+
                    "e.dniemp,"+
                    "e.diremp,"+
                    "d.nomdis,"+
                    "e.telemp,"+
                    "e.celemp,"+
                    "e.coremp,"+
                    "e.sexemp,"+
                    "e.usuemp,"+
                    "e.claemp,"+
                    "p.nomper,"+
                    "e.estemp"+
                    " from t_empleado e inner join t_distrito d"+
                    " on e.coddis=d.coddis inner join t_perfil p"+
                    " on e.codper=p.codper" ,null);
            //evaluamos que al menos exista un dato en el cursor
            if(cursor.getCount()>0){
                //llenamos el cursor
                while (cursor.moveToNext())
                {
                    Empleado objempleado=new Empleado();
                    Distrito objdistrito=new Distrito();
                    Perfil objperfil=new Perfil();
                    //le asignamos los valores  al objeto
                    objempleado.setCodigo(cursor.getInt(0));
                    objempleado.setNombre(cursor.getString(1));
                    objempleado.setApellidop(cursor.getString(2));
                    objempleado.setApellidom(cursor.getString(3));
                    objempleado.setDni(cursor.getString(4));
                    objempleado.setDireccion(cursor.getString(5));
                    //enviando el distrito
                    objdistrito.setNombre(cursor.getString(6));
                    objempleado.setDistrito(objdistrito);
                    objempleado.setTelefono(cursor.getString(7));
                    objempleado.setCelular(cursor.getString(8));
                    objempleado.setCorreo(cursor.getString(9));
                    objempleado.setSexo(cursor.getString(10));

                    objempleado.setUsuario(cursor.getString(11));
                    objempleado.setClave(cursor.getString(12));
                    //Enviando perfil
                    objperfil.setNombre(cursor.getString(13));
                    objempleado.setPerfil(objperfil);
                    objempleado.setEstado(cursor.getInt(14));
                    //enviamos el arreglo
                    registroempleado.add(objempleado);

                }
            }else{
                registroempleado=null;
            }
            return  registroempleado;
        }catch (Exception ex){
            Log.e("Error:",ex.toString());
            return null;
        }
    }

    @Override
    public boolean RegistrarEmpleado(Empleado e, Context contexto) {
        //creamos la conexion
        objconexion=new Conexion(contexto,nombre,null,version);
        //asignamos el valor para  que la base de datos se pueda escribir
        db=objconexion.getWritableDatabase();
        try{
            //creamos el ContentValues
            valores=new ContentValues();
            //asignamos los valores
            valores.put("nomemp",e.getNombre());
            valores.put("apepemp",e.getApellidop());
            valores.put("apememp",e.getApellidom());
            valores.put("dniemp",e.getDni());
            valores.put("diremp",e.getDireccion());
            valores.put("coddis",e.getDistrito().getCodigo());
            valores.put("telemp",e.getTelefono());
            valores.put("celemp",e.getCelular());
            valores.put("coremp",e.getCorreo());
            valores.put("sexemp",e.getSexo());
            valores.put("usuemp",e.getUsuario());
            valores.put("claemp",e.getClave());
            valores.put("codper",e.getPerfil().getCodigo());
            valores.put("estemp ",e.getEstado());

            //ejecutamos la insercion
            long res=db.insert("t_empleado",null,valores);
            if(res>0){
                Log.i("Log","Resultado "+res);
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
    public boolean ActualizarEmpleado(Empleado e, Context contexto) {
        //creamos la conexion
        objconexion=new Conexion(contexto,nombre,null,version);
        //asignamos el valor para  que la base de datos se pueda escribir
        db=objconexion.getWritableDatabase();
        try{
            //creamos el ContentValues
            valores=new ContentValues();
            //asignamos los valores

            valores.put("nomemp",e.getNombre());
            valores.put("apepemp",e.getApellidop());
            valores.put("apememp",e.getApellidom());
            valores.put("dniemp",e.getDni());
            valores.put("diremp",e.getDireccion());
            valores.put("coddis",e.getDistrito().getCodigo());

            valores.put("telemp",e.getTelefono());
            valores.put("celemp",e.getCelular());
            valores.put("coremp",e.getCorreo());
            valores.put("sexemp",e.getSexo());
            valores.put("usuemp",e.getUsuario());
            valores.put("claemp",e.getClave());
            valores.put("codper",e.getPerfil().getCodigo());
            valores.put("estemp",e.getEstado());


            //ejecutamos la insercion
            long res=db.update("t_empleado",valores, "codemp=?", new String[] {String.valueOf(e.getCodigo())});
            if(res>0){
                Log.i("Log","Resultado "+res);
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
    public boolean EliminarEmpleado(Empleado e) {
        try {
            //eliminar clasico--> Eliminar fisico
            //int res=db.delete("t_perfil","codper="+p.getCodigo(),null);
            //creamos los valores
            valores=new ContentValues();
            //asignamos los valores
            valores.put("estemp",0);
            int res=db.update("t_empleado",valores,"codemp="+e.getCodigo(),null);
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