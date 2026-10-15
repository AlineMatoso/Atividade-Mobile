package com.example.meuprimeiroapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReservaActivity : AppCompatActivity() {
    private lateinit var rvReservas: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva)

        val btnSala1 = findViewById<Button>(R.id.btnSala1)
        val btnSala2 = findViewById<Button>(R.id.btnSala2)
        val btnSala3 = findViewById<Button>(R.id.btnSala3)
        val btnVerReservas = findViewById<Button>(R.id.btnVerReservas)
        rvReservas = findViewById(R.id.rvReservas)

        rvReservas.layoutManager = LinearLayoutManager(this)

        btnSala1.setOnClickListener {
            val intent = Intent(this, NovaReservaActivity::class.java)
            intent.putExtra("sala_escolhida", "Sala 01 - Escritório aberto")
            startActivity(intent)
        }

        btnSala2.setOnClickListener {
            val intent = Intent(this, NovaReservaActivity::class.java)
            intent.putExtra("sala_escolhida", "Sala 02 - Equipe de projetos")
            startActivity(intent)
        }

        btnSala3.setOnClickListener {
            val intent = Intent(this, NovaReservaActivity::class.java)
            intent.putExtra("sala_escolhida", "Sala 03 - Reuniões")
            startActivity(intent)
        }

        btnVerReservas.setOnClickListener {
            val banco = BancoHelper(this)
            val reservas = banco.listar()

            if (reservas.isEmpty()) {
                Toast.makeText(this, "Nenhuma sala ou mesa reservada ainda.", Toast.LENGTH_SHORT).show()
            } else {
                val mensagem = StringBuilder()
                for (r in reservas) {
                    mensagem.append("• Local: ${r.local}\n")
                    mensagem.append("  Responsável: ${r.nome}\n")
                    mensagem.append("  Data: ${r.data}\n")
                    if (r.descricao.isNotEmpty()) {
                        mensagem.append("  Obs: ${r.descricao}\n")
                    }
                    mensagem.append("\n")
                }

                AlertDialog.Builder(this)
                    .setTitle("Salas e Mesas Reservadas")
                    .setMessage(mensagem.toString().trim())
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        carregarReservas()
    }

    private fun carregarReservas() {
        rvReservas = findViewById(R.id.rvReservas)
        val banco = BancoHelper(this)
        val listaDeReservas = banco.listar()

        rvReservas.layoutManager = LinearLayoutManager(this)
        rvReservas.adapter = ReservaAdapter(listaDeReservas) { reservaClicada ->
            val intent = Intent(this, DetalheReservaActivity::class.java)
            intent.putExtra("reserva_id", reservaClicada.id)
            startActivity(intent)
        }
    }
}
