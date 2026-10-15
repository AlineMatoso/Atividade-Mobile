package com.example.meuprimeiroapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetalheReservaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalhe_reserva)
        // Pega o ID que veio "na mochila" da tela anterior
        val id = intent.getIntExtra("reserva_id", -1)

        // Busca a reserva completa no banco de dados
        val reserva = BancoHelper(this).buscarPorId(id)

        // Se encontrou a reserva, preenche as caixas de texto
        if (reserva != null) {
            findViewById<android.widget.TextView>(R.id.tvDetalheNome).text = reserva.nome
            findViewById<android.widget.TextView>(R.id.tvDetalheInfo).text = "${reserva.data} • ${reserva.local}"
            findViewById<android.widget.TextView>(R.id.tvDetalheDescricao).text = reserva.descricao

            // Lógica do botão Favoritar
            val btnFavoritar = findViewById<android.widget.Button>(R.id.btnFavoritar)
            var isFavorito = reserva.favorito

            // Arruma o texto inicial do botão
            btnFavoritar.text = if (isFavorito) "REMOVER DOS FAVORITOS" else "FAVORITAR"

            btnFavoritar.setOnClickListener {
                // Inverte o status
                isFavorito = !isFavorito

                // Cria uma cópia da reserva com o novo status
                val reservaAtualizada = reserva.copy(favorito = isFavorito)

                // Salva no banco de dados
                BancoHelper(this).atualizar(reservaAtualizada)

                // Atualiza o texto do botão
                btnFavoritar.text = if (isFavorito) "REMOVER DOS FAVORITOS" else "FAVORITAR"

                android.widget.Toast.makeText(this, if (isFavorito) "Favoritado!" else "Removido dos favoritos", android.widget.Toast.LENGTH_SHORT).show()
            }
        }

        val btnExcluir = findViewById<android.widget.Button>(R.id.btnExcluir)

        btnExcluir.setOnClickListener {
            // Chama o banco e manda excluir usando o 'id' da reserva atual
            val banco = BancoHelper(this)
            banco.excluir(id)

            // Avisa o usuário e fecha a tela de detalhes
            android.widget.Toast.makeText(this, "Reserva cancelada!", android.widget.Toast.LENGTH_SHORT).show()
            finish()
        }

        val btnVoltar = findViewById<android.widget.Button>(R.id.btnVoltar)
        btnVoltar.setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnEditar = findViewById<android.widget.Button>(R.id.btnEditar)

        btnEditar.setOnClickListener {
            // Abre a tela de Nova Reserva, mas manda o ID na mochila para avisar que é edição
            val intentEditar = android.content.Intent(this, NovaReservaActivity::class.java)
            intentEditar.putExtra("reserva_id", id)
            startActivity(intentEditar)
            finish() // Fecha a tela de detalhes atual
        }
    }
}
