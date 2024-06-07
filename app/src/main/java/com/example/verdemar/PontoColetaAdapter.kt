package com.example.verdemar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.verdemar.databinding.PontocoletaViewBinding
import com.example.verdemar.databinding.TarefasViewBinding

class PontoColetaAdapter : ListAdapter<PontoColeta, PontoColetaAdapter.MyViewHolder>(PontoColetaAdapter.DiffCallback())  {

    var onClick: (PontoColeta) -> Unit ={

    }

    inner class MyViewHolder(val binding: PontocoletaViewBinding): RecyclerView.ViewHolder(binding.root){


        fun bind(PontoColeta : PontoColeta) {
            binding.lblNomePonto.text = PontoColeta.nome
            binding.lblEndereco.text = PontoColeta.descricao
            binding.lblHorarioFunc.text = PontoColeta.horarioFuncionament

        }

    }

    class DiffCallback(): DiffUtil.ItemCallback<PontoColeta>(){
        override fun areItemsTheSame(oldItem: PontoColeta, newItem: PontoColeta): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PontoColeta, newItem: PontoColeta): Boolean {
            return oldItem.nome == newItem.nome
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PontocoletaViewBinding.inflate(layoutInflater)
        val myViewHolder = MyViewHolder(binding)

        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}