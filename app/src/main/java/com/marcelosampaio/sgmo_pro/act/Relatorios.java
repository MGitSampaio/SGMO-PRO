package com.marcelosampaio.sgmo_pro.act;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.marcelosampaio.sgmo_pro.R;
import com.marcelosampaio.sgmo_pro.dao.ClienteDao;
import com.marcelosampaio.sgmo_pro.dao.FuncionarioDao;
import com.marcelosampaio.sgmo_pro.dao.MaterialDao;
import com.marcelosampaio.sgmo_pro.dao.MovimentacaoDao;
import com.marcelosampaio.sgmo_pro.dao.OcorrenciaDao;
import com.marcelosampaio.sgmo_pro.dao.PostoDao;
import com.marcelosampaio.sgmo_pro.dao.RondaDao;
import com.marcelosampaio.sgmo_pro.dao.UniformeDao;
import com.marcelosampaio.sgmo_pro.dao.VencimentoDao;
import com.marcelosampaio.sgmo_pro.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_pro.model.Cliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Relatorios extends AppCompatActivity {
    //==============================================================================================
    // Declarando as Variáveis
    private TextInputEditText inicio;
    private Spinner spiRelatorios;
    private Button btnRelatorios;
    private PostoDao postoDao;
    private ClienteDao clienteDao;
    private FuncionarioDao funcionarioDao;
    private MaterialDao materialDao;
    private MovimentacaoDao movimentacaoDao;
    private OcorrenciaDao ocorrenciaDao;
    private RondaDao rondaDao;
    private UniformeDao uniformeDao;
    private VencimentoDao vencimentoDao;
    private DataHelper dataHelper = new DataHelper();

    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relatorios);


        //==========================================================================================
        //Mapeando os elementos do xml
        inicio = findViewById(R.id.etDtInicial);
        spiRelatorios = findViewById(R.id.spiRelatorios);
        btnRelatorios = findViewById(R.id.btnRelatorios);

        //==========================================================================================
        //Preenchendo o spinner

        String spinnerArray[] = {"Área","Clientes", "Funcionários", "Materiais","Movimentações",
                "Ocorrências","Rondas", "Uniformes", "Vencimentos"};

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(Relatorios.this,
                android.R.layout.simple_spinner_item, spinnerArray);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiRelatorios.setAdapter(spinnerAdapter);

        //==========================================================================================
        // Define o comportamento do btnRelatorios gerando os relatórios
        btnRelatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = spiRelatorios.getSelectedItem().toString();

                switch (item) {
                    case "Área": {
                        postoDao = new PostoDao(Relatorios.this);

                        try {
                            postoDao.reportPostos();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        String path = "content://com.android.providers.downloads.documents/document/raw%3A%2Fstorage%2Femulated%2F0%2FDownload%2Farea.html" ;
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
                        startActivity(intent);
                        break;
                    }

                    case "Clientes": {
                        clienteDao = new ClienteDao(Relatorios.this);
                        try {
                            clienteDao.reportClientes();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        String path = "content://com.android.providers.downloads.documents/document/raw%3A%2Fstorage%2Femulated%2F0%2FDownload%2Fclientes.html" ;
                        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(path));
                        startActivity(intent);

                        break;
                    }

                    case "Funcionários": {
                        funcionarioDao = new FuncionarioDao(Relatorios.this);
                        try {
                            funcionarioDao.reportFuncionarios();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String path = "content://com.android.providers.downloads.documents/document/raw%3A%2Fstorage%2Femulated%2F0%2FDownload%2Ffuncionarios.html" ;
                        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(path));
                        startActivity(intent);
                        break;
                    }

                    case "Materiais": {
                        materialDao = new MaterialDao(Relatorios.this);

                        try {
                            materialDao.reportMateriais();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String path = "content://com.android.providers.downloads.documents/document/raw%3A%2Fstorage%2Femulated%2F0%2FDownload%2Fmateriais.html" ;
                        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(path));
                        startActivity(intent);

                        break;
                    }

                    case "Movimentações": {
                        movimentacaoDao = new MovimentacaoDao(Relatorios.this);

                        try {
                            movimentacaoDao.reportMovimentacao(inicio.getText().toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String path = "content://com.android.providers.downloads.documents/document/raw%3A%2Fstorage%2Femulated%2F0%2FDownload%2Fmovimentacao.html" ;
                        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(path));
                        startActivity(intent);

                        break;
                    }

                    case "Ocorrências": {
                        ocorrenciaDao = new OcorrenciaDao(Relatorios.this);

                        try {
                            ocorrenciaDao.reportOcorrencia(inicio.getText().toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String path = "content://com.android.providers.downloads.documents/document/raw%3A%2Fstorage%2Femulated%2F0%2FDownload%2Focorrencia.html" ;
                        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(path));
                        startActivity(intent);
                        break;


                    }

                    case "Rondas": {

                        rondaDao = new RondaDao(Relatorios.this);

                        try {
                            rondaDao.reportRondas(inicio.getText().toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String path = "content://com.android.providers.downloads.documents/document/raw%3A%2Fstorage%2Femulated%2F0%2FDownload%2Frondas.html" ;
                        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(path));
                        startActivity(intent);

                        break;
                    }



                    case "Uniformes": {
                        uniformeDao = new UniformeDao(Relatorios.this);
                        try {
                            uniformeDao.reportUniformes();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String path = "content://com.android.providers.downloads.documents/document/raw%3A%2Fstorage%2Femulated%2F0%2FDownload%2Funiformes.html" ;
                        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(path));
                        startActivity(intent);
                        break;
                    }

                    case "Vencimentos": {
                        vencimentoDao = new VencimentoDao(Relatorios.this);
                        try {
                            vencimentoDao.reportVencimentos();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String path = "content://com.android.providers.downloads.documents/document/raw%3A%2Fstorage%2Femulated%2F0%2FDownload%2Fvencimentos.html" ;
                        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(path));
                        startActivity(intent);
                        break;
                    }

                    default:
                        throw new IllegalStateException("Unexpected value: " + item);
                }
            }
        });


        //==========================================================================================

    }


}
