package com.example.meuprimeiroapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ReservaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Conecta o arquivo Kotlin ao layout visual que acabamos de criar
        setContentView(R.layout.activity_reserva)

        // 2. Encontra os botões das salas pelo ID
        val btnSala1 = findViewById<Button>(R.id.btnSala1)
        val btnSala2 = findViewById<Button>(R.id.btnSala2)
        val btnSala3 = findViewById<Button>(R.id.btnSala3)

        // 3. Adiciona a ação de clique para simular a verificação de lugares
        btnSala1.setOnClickListener {
            val intent = android.content.Intent(this, NovaReservaActivity::class.java)
            startActivity(intent)
        }

        btnSala2.setOnClickListener {
            Toast.makeText(this, "Carregando mesas da Equipe de projetos...", Toast.LENGTH_SHORT).show()
        }

        btnSala3.setOnClickListener {
            Toast.makeText(this, "Carregando mesas da Sala de Reuniões...", Toast.LENGTH_SHORT).show()
        }
    }
}