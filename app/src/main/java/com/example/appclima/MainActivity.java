package com.example.appclima;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaramos las variables de las Views como atributos de la clase
    // así están disponibles en todos los métodos, no solo en onCreate
    private EditText editTextCiudad;
    private Button btnBuscar;
    private TextView tvCiudad;
    private TextView tvTemperatura;
    private TextView tvDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Conectamos cada variable Java con su View del XML,
        // usando el android:id que definimos en activity_main.xml
        editTextCiudad = findViewById(R.id.editTextCiudad);
        btnBuscar = findViewById(R.id.btnBuscar);
        tvCiudad = findViewById(R.id.tvCiudad);
        tvTemperatura = findViewById(R.id.tvTemperatura);
        tvDescripcion = findViewById(R.id.tvDescripcion);

        // Listener: qué pasa cuando el usuario toca el botón
        btnBuscar.setOnClickListener(view -> {
            String ciudad = editTextCiudad.getText().toString();

            // Por ahora, solo mostramos lo que el usuario escribió (sin API todavía)
            tvCiudad.setText(ciudad);
            tvTemperatura.setText("-- °C");
            tvDescripcion.setText("Acá va a ir la descripción real");
        });
    }
}