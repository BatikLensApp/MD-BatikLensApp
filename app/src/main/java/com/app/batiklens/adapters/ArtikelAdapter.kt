package com.app.batiklens.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.batiklens.R
import com.app.batiklens.databinding.ArtikelListViewBinding
import com.app.batiklens.di.models.ArtikelModelItem
import com.app.batiklens.ui.user.berita.BeritaFragment
import com.bumptech.glide.Glide

class ArtikelAdapter: ListAdapter<ArtikelModelItem, ArtikelAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(ArtikelListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(getItem(position))
    }

    class ViewHolder(
        private val bind: ArtikelListViewBinding
    ): RecyclerView.ViewHolder(bind.root) {
        fun binding(item: ArtikelModelItem) {
            bind.apply {
                val context = itemView.context

                Glide.with(context).load(item.foto).into(fotoArtikel)
                tvJudul.text = item.judul
                tvArtikel.text = item.deskripsi

                itemView.setOnClickListener {
                    val fragment = BeritaFragment().apply {
                        arguments = Bundle().apply {
                            putInt(BeritaFragment.DETAIL_ID, item.id)
                        }
                    }

                    (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArtikelModelItem>() {
            override fun areItemsTheSame(
                oldItem: ArtikelModelItem,
                newItem: ArtikelModelItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ArtikelModelItem,
                newItem: ArtikelModelItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

}