package com.ronny.bancovirtual.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ronny.bancovirtual.R
import com.ronny.bancovirtual.model.Movimiento

class MovimientoAdapter(private val lista: ArrayList<Movimiento>) :
    RecyclerView.Adapter<MovimientoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDesc: TextView = view.findViewById(R.id.tvDescripcion)
        val tvFecha: TextView = view.findViewById(R.id.tvFecha)
        val tvMonto: TextView = view.findViewById(R.id.tvMonto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movimiento, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]

        holder.tvDesc.text = item.descripcion
        holder.tvFecha.text = item.fecha

        // Formato simple de moneda
        holder.tvMonto.text = "S/ ${String.format("%.2f", item.monto)}"

        // Lógica visual simple: Rojo si es egreso, Verde si es ingreso
        if (item.tipo == "EGRESO") {
            holder.tvMonto.setTextColor(Color.parseColor("#D32F2F")) // Rojo
            holder.tvMonto.text = "- S/ ${String.format("%.2f", item.monto)}"
        } else {
            holder.tvMonto.setTextColor(Color.parseColor("#388E3C")) // Verde
        }
    }

    override fun getItemCount(): Int = lista.size
}