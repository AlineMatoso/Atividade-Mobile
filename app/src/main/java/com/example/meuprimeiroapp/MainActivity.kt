package com.example.meuprimeiroapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ajustamos para android.R.id.content para não dar o erro vermelho do "R.id.main"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // MISSÃO 03: Encontrando o botão pelo ID correto que está no XML
        val btnComecar = findViewById<Button>(R.id.btnComecar)

        btnComecar.setOnClickListener {
            // Cria a intenção de abrir a tela de Login e a executa
            val intent = android.content.Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnComecar.setOnClickListener {
            val intent = android.content.Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        }
    }
