package com.example.loginarchivos.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.loginarchivos.model.Usuario;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {

//    private static SharedPreferences sp;

    private static File archivo;
//    private static SharedPreferences conectar(Context context) {
//        if (sp == null) {
//            sp = context.getSharedPreferences("datos.xml", 0);
//        }
//        return sp;
//    }

    private static File conectar (File dir){
        if(archivo == null){
            archivo = new File(dir,"personal.dat");
        }
        return archivo;
    }

//    public static void guardar(Context context, Usuario usuario) {
//        SharedPreferences sp = conectar(context);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putLong("dni", usuario.getDni());
//        editor.putString("apellido", usuario.getApellido());
//        editor.putString("nombre", usuario.getNombre());
//        editor.putString("mail", usuario.getMail());
//        editor.putString("pass", usuario.getPass());
//        editor.commit();
//    }

    public static void guardar(Context context, Usuario usuario){
        File archivo = conectar(context.getFilesDir());
        try{
            FileOutputStream fos=new FileOutputStream(archivo);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(usuario);
            oos.close();
            fos.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
//
//    public static Usuario leer(Context context) {
//        SharedPreferences sp = conectar(context);
//        Usuario usuario = new Usuario();
//        usuario.setDni(sp.getLong("dni", -1));
//        usuario.setApellido(sp.getString("apellido", "-1"));
//        usuario.setNombre(sp.getString("nombre", "-1"));
//        usuario.setMail(sp.getString("mail", "-1"));
//        usuario.setPass(sp.getString("pass", "-1"));
//        return usuario;
//    }

    public static Usuario leer(Context context){
        File archivo = conectar(context.getFilesDir());
        try{
            FileInputStream fis = new FileInputStream(archivo);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Usuario miUsuario = (Usuario)ois.readObject();
            ois.close();

            return miUsuario;
        } catch (IOException | ClassNotFoundException e){
            return null;
        }
    }

//    public static Usuario login(Context context, String mail, String pass) {
//        Usuario usuario = null;
//        SharedPreferences sp = conectar(context);
//        String email = sp.getString("mail", "-1");
//        String passw = sp.getString("pass", "-1");
//
//        if (!email.equals(mail) || !passw.equals(pass)) {
//            return usuario;
//        }
//
//        Long dni = sp.getLong("dni", -1);
//        String apellido = sp.getString("apellido", "-1");
//        String nombre = sp.getString("nombre", "-1");
//        usuario = new Usuario(dni, apellido, nombre, mail, pass);
//        return usuario;
//    }

    public static Usuario login(Context context, String mail, String pass){
        File archivo = conectar(context.getFilesDir());
        try{
            FileInputStream fis = new FileInputStream(archivo);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Usuario miUsuario = (Usuario) ois.readObject();
            ois.close();

            if(miUsuario.getMail().equals(mail) && miUsuario.getPass().equals(pass)){
                return miUsuario;
            }
            return null;
        } catch (IOException | ClassNotFoundException e){
            return null;
        }
    }
}
