package com.example.appclima;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appclima.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    // binding nos da acceso directo a las Views del XML por su ID,
    // generado automáticamente por Android a partir de fragment_first.xml
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Acá "inflamos" el layout: convertimos el XML en objetos Java reales
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Acá SÍ es seguro trabajar con las Views (ya existen en pantalla)
        binding.btnBuscar.setOnClickListener(v -> {
            String ciudad = binding.editTextCiudad.getText().toString();

            // Por ahora mostramos lo escrito, sin API todavía
            binding.tvCiudad.setText(ciudad);
            binding.tvTemperatura.setText("-- °C");
            binding.tvDescripcion.setText("Acá va a ir la descripción real");
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Liberamos la referencia para evitar fugas de memoria (memory leaks)
        binding = null;
    }
}