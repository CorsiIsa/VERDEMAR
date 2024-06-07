package com.example.verdemar
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.verdemar.databinding.TarefasViewBinding

class TarefasAdapter : ListAdapter<Tarefas, TarefasAdapter.MyViewHolder>(DiffCallback())  {

    var onClick: (Tarefas) -> Unit ={

    }
    var onClickDeletar: (Tarefas) -> Unit ={

    }

    inner class MyViewHolder(val binding: TarefasViewBinding): RecyclerView.ViewHolder(binding.root){


        fun bind(Tarefas : Tarefas) {
            binding.lblNomePraia.text = Tarefas.nomePraia
            binding.lblStatus.text = Tarefas.status
            binding.imgEditar.setOnClickListener(){
                onClick(Tarefas)
            }
            binding.imgDeletar.setOnClickListener(){
                onClickDeletar(Tarefas)
            }

        }

    }

    class DiffCallback(): DiffUtil.ItemCallback<Tarefas>(){
        override fun areItemsTheSame(oldItem: Tarefas, newItem: Tarefas): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Tarefas, newItem: Tarefas): Boolean {
            return oldItem.nomePraia == newItem.nomePraia
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TarefasViewBinding.inflate(layoutInflater)
        val myViewHolder = MyViewHolder(binding)

        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}