package com.example.appclima;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appclima.databinding.FragmentFirstBinding;
import com.example.appclima.modelo.RespuestaClima;
import com.example.appclima.red.ApiClima;
import com.example.appclima.red.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Log de diagnóstico temporal - lo sacamos después de confirmar que anda
        Toast.makeText(getContext(), "FRAGMENT CARGADO OK", Toast.LENGTH_SHORT).show();

        binding.btnBuscar.setOnClickListener(v -> {
            String ciudad = binding.editTextCiudad.getText().toString().trim();

            if (ciudad.isEmpty()) {
                Toast.makeText(getContext(), "Escribí una ciudad primero", Toast.LENGTH_SHORT).show();
                return;
            }

            buscarClima(ciudad);
        });
    }

    private void buscarClima(String ciudad) {
        android.util.Log.d("DEBUG_CLIMA", "Entrando a buscarClima con ciudad: " + ciudad);

        ApiClima api = RetrofitClient.getInstance().create(ApiClima.class);

        Call<RespuestaClima> llamada = api.obtenerClima(
                ciudad,
                BuildConfig.WEATHER_API_KEY,
                "metric",
                "es"
        );

        llamada.enqueue(new Callback<RespuestaClima>() {
            @Override
            public void onResponse(@NonNull Call<RespuestaClima> call, @NonNull Response<RespuestaClima> response) {
                android.util.Log.d("DEBUG_CLIMA", "onResponse llegó. Código: " + response.code());

                if (response.isSuccessful() && response.body() != null) {
                    mostrarResultado(response.body());
                } else {
                    Toast.makeText(getContext(), "No se encontró la ciudad", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RespuestaClima> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mostrarResultado(RespuestaClima respuesta) {
        binding.tvCiudad.setText(respuesta.getName());
        binding.tvTemperatura.setText(String.format("%.1f°C", respuesta.getMain().getTemp()));

        if (!respuesta.getWeather().isEmpty()) {
            binding.tvDescripcion.setText(respuesta.getWeather().get(0).getDescription());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}