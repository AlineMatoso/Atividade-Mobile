package com.example.meuprimeiroapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReservaAdapter(
    private val reservas: List<Reserva>,
    private val onClick: (Reserva) -> Unit = {}
) : RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder>() {

    class ReservaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome: TextView = view.findViewById(R.id.tvNomeReserva)
        val dataLocal: TextView = view.findViewById(R.id.tvDataLocal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reserva, parent, false)
        return ReservaViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReservaViewHolder, position: Int) {
        val reserva = reservas[position]
        holder.nome.text = reserva.nome
        holder.dataLocal.text = "${reserva.data} • ${reserva.local}"
        holder.itemView.setOnClickListener { onClick(reserva) }
    }

    override fun getItemCount() = reservas.size
}
