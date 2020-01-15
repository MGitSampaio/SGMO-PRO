package com.marcelosampaio.sgmo_pro.act;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.marcelosampaio.sgmo_pro.R;
import com.marcelosampaio.sgmo_pro.dao.UsuarioDao;
import com.marcelosampaio.sgmo_pro.model.Usuario;


public class CadastroUsuario extends AppCompatActivity {

    private long idUsuario = 0;
    private TextInputEditText etUsuario;
    private TextInputEditText etArea;
    private Usuario usuarioModel = null;
    private UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        usuarioDao = new UsuarioDao(this);

        etUsuario = findViewById(R.id.etUsuario);
        etArea = findViewById(R.id.etArea);


        etUsuario.setText(usuarioDao.BuscaUsuario().getUsuario());
        etArea.setText(usuarioDao.BuscaUsuario().getArea());

    }

    //Função que cria o menu de cadastro
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    //==============================================================================================
    //Função que salva os dados
    public void salvar(MenuItem item) {
        Usuario usuarioSalvar = getDataForm();

        if (idUsuario == 0) {
            long id = usuarioDao.inserir(usuarioSalvar);
            if (id > 0) {
                Toast.makeText(this, "Registro Salvo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Falha ao Salvar", Toast.LENGTH_SHORT).show();
            }
        } else {
            long id = usuarioDao.atualizar(usuarioSalvar);
            if (id > 0) {
                Toast.makeText(this, "Registro Alterado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Falha ao Alterar", Toast.LENGTH_SHORT).show();
            }
        }

        String usuario = usuarioSalvar.getUsuario();
        String area = usuarioSalvar.getArea();

        System.out.println(usuario + " - " + area);
        this.finish();

    }

    //==============================================================================================

    //Função para pegar os dados do formulário
    private Usuario getDataForm() {
        this.usuarioModel = new Usuario();
        this.usuarioModel.setIdUsuario((int) this.idUsuario);
        this.usuarioModel.setUsuario(this.etUsuario.getText().toString());
        this.usuarioModel.setArea(this.etArea.getText().toString());

        return usuarioModel;
    }

}
