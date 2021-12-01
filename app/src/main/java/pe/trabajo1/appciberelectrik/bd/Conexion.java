package pe.trabajo1.appciberelectrik.bd;

import android.content.Context;
import android.database.sqlite.*;

import androidx.annotation.Nullable;


public class Conexion extends SQLiteOpenHelper{

    //declaras atributos para la conexion
    private SQLiteDatabase xcon;

    //creamos vatiables para cada tabla
    String t_perfil="",t_distrito="",t_empleado="",t_cliente="",t_categoria="",t_producto="";

    public Conexion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //creamos la conexion
        xcon=this.getWritableDatabase();
        //getWritableDatabase() <- te permite creary escribir en la base de datos


    }

    @Override
    public void onCreate(SQLiteDatabase db /* <- Esto es el caracter con el que llamaremos la BD*/) {

        //instanciamos las tablas
        t_perfil ="create table t_perfil("+ "codper integer primary key autoincrement," +
                "nomper text not null, "+ "estper integer  not null)";
        t_distrito="create table t_distrito("+ "coddis integer primary key autoincrement,"
        +"nomdis text not null,"+"estdis integer not null)";
        t_empleado="create table t_empleado("+
                "codemp integer primary key autoincrement," +
                "nomemp text not null," +
                "apepemp text not null," +
                "apememp text not null," +
                "dniemp text not null," +
                "diremp text not null," +
                "coddis integer not null," +
                "telemp text," +
                "celemp text not null," +
                "coremp text not null," +
                "sexemp text not null," +
                "usuemp text not null," +
                "claemp text not null," +
                "codper integer not null," +
                "estemp integer not null,"+
                "foreign key (codper) references t_perfil(codper)," +
                "foreign key (coddis) references t_distrito(coddis))";


        t_cliente="create table t_cliente(" +
                "codcli integer primary key autoincrement," +
                "nomcli text not null," +
                "apepcli text not null," +
                "apemcli text not null," +
                "dnicli text not null," +
                "dircli text not null," +
                "coddis integer not null," +
                "telcli text," +
                "celcli text not null," +
                "corcli text not null," +
                "sexcli text not null," +
                "estcli integer not null," +
                "foreign key (coddis) references t_distrito(coddis))";
        t_categoria="CREATE TABLE t_categoria(" +
                "codcat INTEGER primary key AUTOINCREMENT ," +
                "nomcat VARCHAR(40) NOT NULL," +
                "estcat BIT NOT NULL" +
                ");";
        t_producto="CREATE TABLE t_producto(" +
                "codpro INTEGER primary key AUTOINCREMENT," +
                "nompro text NOT NULL," +
                "prepro DOUBLE NOT NULL," +
                "canpro INTEGER NOT NULL," +
                "estpro integer NOT NULL," +
                "codcat INTEGER NOT NULL," +
                "foreign key (codcat) references t_categoria(codcat)" +
                ")";
        //ejecutamos los comandos para crear la tabla
        db.execSQL(t_perfil);
        db.execSQL(t_distrito);
        db.execSQL(t_cliente);
        db.execSQL(t_empleado);
        db.execSQL(t_categoria);
        db.execSQL(t_producto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists t_perfil");
        db.execSQL("drop table if exists t_distrito");
        db.execSQL("drop table if exists t_empleado");
        db.execSQL("drop table if exists t_cliente");
        db.execSQL("drop table if exists t_categoria");
    }
}
