package br.com.vinip.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.vinip.orgs.R
import br.com.vinip.orgs.dao.ProdutosDao
import br.com.vinip.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val btnSalvar = findViewById<Button>(R.id.botao_salvar)

        configuraBotaoSalvar(btnSalvar)


    }

    private fun configuraBotaoSalvar(btnSalvar: Button) {
        btnSalvar.setOnClickListener {
            val produto = CriaProduto()
            val dao = ProdutosDao()
            dao.adiciona(produto)

            Log.i("FormularioProduto", "Oncreate: ${dao.buscaTodos()}")
            finish()
        }
    }

    private fun CriaProduto(): Produto {
        val campoNome = findViewById<EditText>(R.id.produto_item_nome)
        val nome = campoNome.text.toString()

        val campoDescricao = findViewById<EditText>(R.id.descricao)
        val descricao = campoDescricao.text.toString()

        val campoValor = findViewById<EditText>(R.id.valor)
        val valorEmTexto = campoValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(nome, descricao, valor)
    }
}