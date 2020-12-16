package com.nicolas.buscacep.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.nicolas.buscacep.R;
import com.nicolas.buscacep.api.ApiService;
import com.nicolas.buscacep.api.ICepService;
import com.nicolas.buscacep.model.Cep;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private TextView nomeRua, nomeCidade, nomeEstado, nomeBairro;
    private Button buttonBuscarCep;
    private TextInputEditText infoCep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();

        buttonBuscarCep.setOnClickListener(v -> {

            infoCep = findViewById(R.id.input_cep);
            String cepInformado = infoCep.getText().toString();

            if (!cepInformado.isEmpty()) {

                ApiService.getiCepService().obterCep(cepInformado).enqueue(new Callback<Cep>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<Cep> call, Response<Cep> response) {

                        if (response.isSuccessful() && !Objects.requireNonNull(response.body()).isErro()) {

                            Cep cep = response.body();
                            nomeRua.setText(cep.getLogradouro());
                            nomeBairro.setText(cep.getBairro());
                            nomeCidade.setText(cep.getLocalidade());
                            nomeEstado.setText(cep.getUf());
                            infoCep.setText("");

                        } else { Toast.makeText(MainActivity.this, "Cep não existe", Toast.LENGTH_SHORT).show(); }
                    }

                    @Override
                    public void onFailure(Call<Cep> call, Throwable t) {
                    }
                });
            } else {
                Toast.makeText(this, "Favor informe o cep !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void inicializarComponentes() {
        nomeRua = findViewById(R.id.set_rua);
        nomeCidade = findViewById(R.id.set_cidade);
        nomeEstado = findViewById(R.id.set_estado);
        nomeBairro = findViewById(R.id.set_bairro);
        buttonBuscarCep = findViewById(R.id.button_buscar_cep);
    }
}