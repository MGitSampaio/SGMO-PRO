package com.marcelosampaio.sgmo_pro.act;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.marcelosampaio.sgmo_pro.R;
import com.marcelosampaio.sgmo_pro.dao.EscalaDao;
import com.marcelosampaio.sgmo_pro.model.Escala;

import java.util.ArrayList;
import java.util.List;

public class ListaEscala extends AppCompatActivity {
    private final List<Escala> escalaFiltrada = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_escala);

        ListView lvEscalas = findViewById(R.id.lvEscalas);

        EscalaDao escalaDao = new EscalaDao(this);
        List<Escala> listaEscala = escalaDao.listarEscalas();
        escalaFiltrada.addAll(listaEscala);

        ArrayAdapter<Escala> adaptador = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, escalaFiltrada);
        lvEscalas.setAdapter(adaptador);
        registerForContextMenu(lvEscalas);
    }
}
