package com.example.meuprimeiroapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NovaReservaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_reserva)

        val etNome = findViewById<EditText>(R.id.etNome)
        val etData = findViewById<EditText>(R.id.etData)
        val etLocal = findViewById<EditText>(R.id.etLocal)
        val etDescricao = findViewById<EditText>(R.id.etDescricao)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        // Verifica se veio uma sala pré-selecionada da tela anterior
        val salaEscolhida = intent.getStringExtra("sala_escolhida")
        if (salaEscolhida != null) {
            etLocal.setText(salaEscolhida)
            etLocal.isEnabled = false // Desativa para evitar redundância
        }

        // Verifica se veio um ID na Intent (Modo Edição)
        val idRecebido = intent.getIntExtra("reserva_id", -1)
        val emModoEdicao = idRecebido != -1

        if (emModoEdicao) {
            // Busca a reserva antiga no banco e preenche as caixas de texto
            val reservaAntiga = BancoHelper(this).buscarPorId(idRecebido)
            if (reservaAntiga != null) {
                etNome.setText(reservaAntiga.nome)
                etData.setText(reservaAntiga.data)
                etLocal.setText(reservaAntiga.local)
                etLocal.isEnabled = true // Permite editar se necessário
                etDescricao.setText(reservaAntiga.descricao)
                btnSalvar.text = "ATUALIZAR RESERVA"
            }
        }

        btnSalvar.setOnClickListener {
            val reserva = Reserva(
                id = if (emModoEdicao) idRecebido else 0,
                nome = etNome.text.toString().trim(),
                data = etData.text.toString().trim(),
                local = etLocal.text.toString().trim(),
                descricao = etDescricao.text.toString().trim()
            )

            if (reserva.nome.isEmpty()) {
                etNome.error = "Informe o seu nome"
                return@setOnClickListener
            }

            val banco = BancoHelper(this)

            if (emModoEdicao) {
                banco.atualizar(reserva)
                Toast.makeText(this, "Reserva atualizada!", Toast.LENGTH_SHORT).show()
            } else {
                banco.inserir(reserva)
                Toast.makeText(this, "Reserva salva com sucesso!", Toast.LENGTH_SHORT).show()
            }

            finish()
        }

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}
