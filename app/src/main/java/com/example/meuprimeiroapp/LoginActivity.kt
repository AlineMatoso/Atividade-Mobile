package com.example.meuprimeiroapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Micromissão: Fazer o botão voltar fechar a Activity atual
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)
        btnVoltar.setOnClickListener {
            finish()
        }

        // Missão 05: Referenciando o formulário
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etSenha = findViewById<EditText>(R.id.etSenha)
        val btnFazerLogin = findViewById<Button>(R.id.btnFazerLogin)

        btnFazerLogin.setOnClickListener {
            // Captura o texto digitado e remove espaços com trim()
            val email = etEmail.text.toString().trim()
            val senha = etSenha.text.toString().trim()

            // Validação de campos vazios
            if (email.isEmpty()) {
                etEmail.error = "Informe o seu e-mail"
            } else if (senha.isEmpty()) {
                etSenha.error = "Informe sua senha"
            } else {
                Toast.makeText(this, "Acessando WorkSpot...", Toast.LENGTH_SHORT).show()

                // O comando de navegação que estava faltando:
                val intent = android.content.Intent(this, ReservaActivity::class.java)
                startActivity(intent)
            }
        }
    }
}