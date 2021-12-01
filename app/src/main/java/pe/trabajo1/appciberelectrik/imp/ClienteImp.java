package pe.trabajo1.appciberelectrik.imp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import pe.trabajo1.appciberelectrik.bd.Conexion;
import pe.trabajo1.appciberelectrik.bean.Cliente;
import pe.trabajo1.appciberelectrik.bean.Distrito;
import pe.trabajo1.appciberelectrik.dao.ClienteDAO;

public class ClienteImp implements ClienteDAO {

    //creamos un objeto de la clase Conexion
    private Conexion objconexion;
    //general un ArrayList de tipo Distrito para los datos
    private ArrayList<Cliente> registroCliente=null;
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


    public ClienteImp() {
        this.registroCliente = new ArrayList<>();
    }

    @Override
    public ArrayList<Cliente> MostrarCliente(Context contexto) {
        try {
            //creamos la conexion
            objconexion=new Conexion(contexto,nombre,null,version);
            //creamos un cursor y realizamos la consulta a la base de datos
            db=objconexion.getWritableDatabase();
            cursor=db.rawQuery("select c.codcli,"+
                    "c.nomcli,"+
                    "c.apepcli,"+
                    "c.apemcli,"+
                    "c.dnicli,"+
                    "c.dircli,"+
                    "d.nomdis,"+
                    "c.telcli,"+
                    "c.celcli,"+
                    "c.corcli,"+
                    "c.sexcli,"+
                    "c.estcli"+
                    " from t_cliente c inner join t_distrito d"+
                    " on c.coddis=d.coddis" ,null);

            if(cursor.getCount()>0) {
                //llenamos el cursor
                while (cursor.moveToNext()) {
                    Log.e("tag: ", "cursor: " + cursor.getString(1));
                    Cliente objcliente = new Cliente();
                    Distrito objdistrito = new Distrito();
                    //le asignamos los valores  al objeto
                    objcliente.setCodigo(cursor.getInt(0));
                    objcliente.setNombre(cursor.getString(1));
                    objcliente.setApellidop(cursor.getString(2));
                    objcliente.setApellidom(cursor.getString(3));
                    objcliente.setDni(cursor.getString(4));
                    objcliente.setDireccion(cursor.getString(5));
                    //enviando el distrito
                    objdistrito.setNombre(cursor.getString(6));
                    objcliente.setDistrito(objdistrito);

                    objcliente.setTelefono(cursor.getString(7));
                    objcliente.setCelular(cursor.getString(8));
                    objcliente.setCorreo(cursor.getString(9));
                    objcliente.setSexo(cursor.getString(10));
                    objcliente.setEstado(cursor.getInt(11));
                    //enviamos el arreglo
                    registroCliente.add(objcliente);
                }
            } else {
                registroCliente = null;
            }
            return registroCliente;
        } catch (Exception ex) {
            Log.e("Error:",ex.toString());
            return null;
        }
    }

    @Override
    public boolean RegistrarCliente(Cliente c, Context contexto) {
        //creamos la conexion
        objconexion=new Conexion(contexto,nombre,null,version);
        //asignamos el valor para  que la base de datos se pueda escribir
        db=objconexion.getWritableDatabase();
        try{
            //creamos el ContentValues
            valores=new ContentValues();
            //asignamos los valores

            valores.put("nomcli",c.getNombre());
            valores.put("apepcli",c.getApellidop());
            valores.put("apemcli",c.getApellidom());
            valores.put("dnicli",c.getDni());
            valores.put("dircli",c.getDireccion());
            valores.put("coddis",c.getDistrito().getCodigo());

            valores.put("telcli",c.getTelefono());
            valores.put("celcli",c.getCelular());
            valores.put("corcli",c.getCorreo());
            valores.put("sexcli",c.getSexo());
            valores.put("estcli",c.getEstado());


            //ejecutamos la insercion
            long res=db.insert("t_cliente",null,valores);
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
    public boolean ActualizarCliente(Cliente c, Context contexto) {
        //creamos la conexion
        objconexion=new Conexion(contexto,nombre,null,version);
        //asignamos el valor para  que la base de datos se pueda escribir
        db=objconexion.getWritableDatabase();

        try {
            valores = new ContentValues();

            valores.put("nomcli",c.getNombre());
            valores.put("apepcli",c.getApellidop());
            valores.put("apemcli",c.getApellidom());
            valores.put("dnicli",c.getDni());
            valores.put("dircli",c.getDireccion());
            valores.put("coddis",c.getDistrito().getCodigo());

            valores.put("telcli",c.getTelefono());
            valores.put("celcli",c.getCelular());
            valores.put("corcli",c.getCorreo());
            valores.put("sexcli",c.getSexo());
            valores.put("estcli",c.getEstado());
            Log.e("valueof: ", String.valueOf(c.getCodigo()));
            long res = db.update("t_cliente", valores, "codcli=?", new String[] {String.valueOf(c.getCodigo())});

            if(res>0){
                Log.i("Log","Resultado "+res);
                return true;
            }else{
                return false;
            }

        } catch (Exception ex) {
            Log.e("Error",ex.toString());
            return false;
        }

    }

    @Override
    public boolean EliminarCliente(Cliente c) {
        try {
            //eliminar clasico--> Eliminar fisico
            //int res=db.delete("t_perfil","codper="+p.getCodigo(),null);
            //creamos los valores
            valores=new ContentValues();
            //asignamos los valores
            valores.put("estcli",0);
            int res=db.update("t_cliente",valores,"codcli="+c.getCodigo(),null);
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
