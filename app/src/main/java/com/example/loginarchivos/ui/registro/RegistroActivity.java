package com.example.loginarchivos.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.loginarchivos.R;
import com.example.loginarchivos.databinding.ActivityRegistroBinding;
import com.example.loginarchivos.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private ActivityRegistroBinding binding;
    private RegistroViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroViewModel.class);

        viewModel.getUsuario().observe(this, usuario -> {
            binding.etDni.setText(usuario.getDni() + "");
            binding.etNombre.setText(usuario.getNombre());
            binding.etApellido.setText(usuario.getApellido());
            binding.etMail.setText(usuario.getMail());
            binding.etPass.setText(usuario.getPass());
        });

        binding.btGuardar.setOnClickListener(v -> {
            viewModel.registrar(
                Long.parseLong(binding.etDni.getText().toString()),
                binding.etNombre.getText().toString(),
                binding.etApellido.getText().toString(),
                binding.etMail.getText().toString().trim(),
                binding.etPass.getText().toString()
            );
        });

        viewModel.obtenerDatos(getIntent());
    }
}