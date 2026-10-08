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

        btnSalvar.setOnClickListener {
            // Cria um objeto Reserva com o que o usuário digitou
            val reserva = Reserva(
                nome = etNome.text.toString().trim(),
                data = etData.text.toString().trim(),
                local = etLocal.text.toString().trim(),
                descricao = etDescricao.text.toString().trim()
            )

            // Valida se o nome não está vazio
            if (reserva.nome.isEmpty()) {
                etNome.error = "Informe o seu nome"
                return@setOnClickListener
            }

            // Chama o BancoHelper para salvar a reserva
            val banco = BancoHelper(this)
            banco.inserir(reserva)

            // Avisa o usuário e fecha a tela
            Toast.makeText(this, "Reserva salva com sucesso!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}