package com.example.cryptoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoapp.R
import com.example.cryptoapp.dao.MonedaDao
import com.example.cryptoapp.databinding.ItemRowBinding
import com.example.cryptoapp.model.Moneda
import com.squareup.picasso.Picasso

class MonedaAdapter: RecyclerView.Adapter<MonedaAdapter.CustomViewHolder>() {

    private var lista : List<Moneda> = ArrayList()
    lateinit var listener: OnClickListener

    class CustomViewHolder(itemView: View, var listener: OnClickListener) : RecyclerView.ViewHolder(itemView) {
        private val b = ItemRowBinding.bind(itemView)
        fun bindData(moneda: Moneda) {
            with(b)
            {
                tvMoneda.text = moneda.name
                tvDescripcion.text = moneda.status
                Glide.with(itemView).load(moneda.logo_url).into(ivImageArt)

                itemView.setOnClickListener {
                    listener.onClickItem(moneda)

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_row,parent,false)
        return CustomViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindData(lista[position])
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun setMoneda(monedas:List<Moneda>)
    {
        lista = monedas as ArrayList<Moneda>
        notifyDataSetChanged()
    }

    interface OnClickListener{
        fun onClickItem(article: Moneda)
    }

    fun setOnItemClickListener(listener: OnClickListener)
    {
        this.listener = listener
    }

}