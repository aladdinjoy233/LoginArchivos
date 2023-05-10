package com.example.loginarchivos.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginarchivos.model.Usuario;
import com.example.loginarchivos.request.ApiClient;
import com.example.loginarchivos.ui.login.MainActivity;

public class RegistroViewModel extends AndroidViewModel {

    private Context context;

    MutableLiveData<Usuario> usuario;

    public RegistroViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuario() {
        if (usuario == null) {
            usuario = new MutableLiveData<>();
        }
        return usuario;
    }

    public void obtenerDatos(Intent intent) {
        if (intent == null) return;

        if (intent.getBooleanExtra("logeado", false)) {
            Usuario usuario = ApiClient.leer(context);
            this.usuario.setValue(usuario);
        }
    }

    public void registrar(Long dni, String nombre, String apellido, String mail, String pass) {
        Usuario usuario = new Usuario(dni, nombre, apellido, mail, pass);
        ApiClient.guardar(context, usuario);
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
