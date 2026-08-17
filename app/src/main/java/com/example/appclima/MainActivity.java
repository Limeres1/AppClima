package com.example.appclima;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appclima.modelo.RespuestaClima;
import com.example.appclima.red.ApiClima;
import com.example.appclima.red.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCiudad;
    private Button btnBuscar;
    private TextView tvCiudad;
    private TextView tvTemperatura;
    private TextView tvDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCiudad = findViewById(R.id.editTextCiudad);
        btnBuscar = findViewById(R.id.btnBuscar);
        tvCiudad = findViewById(R.id.tvCiudad);
        tvTemperatura = findViewById(R.id.tvTemperatura);
        tvDescripcion = findViewById(R.id.tvDescripcion);

        btnBuscar.setOnClickListener(view -> {
            String ciudad = editTextCiudad.getText().toString().trim();

            if (ciudad.isEmpty()) {
                Toast.makeText(this, "Escribí una ciudad primero", Toast.LENGTH_SHORT).show();
                return;
            }

            buscarClima(ciudad);
        });
    }

    private void buscarClima(String ciudad) {
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
                if (response.isSuccessful() && response.body() != null) {
                    mostrarResultado(response.body());
                } else {
                    Toast.makeText(MainActivity.this, "No se encontró la ciudad", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RespuestaClima> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mostrarResultado(RespuestaClima respuesta) {
        tvCiudad.setText(respuesta.getName());
        tvTemperatura.setText(String.format("%.1f°C", respuesta.getMain().getTemp()));

        if (!respuesta.getWeather().isEmpty()) {
            tvDescripcion.setText(respuesta.getWeather().get(0).getDescription());
        }
    }
}