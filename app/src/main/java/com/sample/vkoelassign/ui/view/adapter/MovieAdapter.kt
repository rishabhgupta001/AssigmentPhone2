package com.sample.vkoelassign.ui.view.adapter

import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sample.vkoelassign.databinding.ItemMovieBinding
import com.sample.vkoelassign.ui.model.Venues
import com.sample.vkoelassign.ui.view.XListFragmentDirections
import com.sample.vkoelassign.utility.Utils

class MovieAdapter(private var data: ArrayList<Venues>, private val dummyList: ArrayList<Venues>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private lateinit var binding: ItemMovieBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(private val itemBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemView.setOnClickListener {
                itemView.isEnabled = false
                Handler().postDelayed({
                    itemView.isEnabled = true
                    data?.get(adapterPosition).let {
                        val action = XListFragmentDirections.actionXDetail()
                        action.venues = it
                        Navigation.findNavController(itemBinding.root).navigate(action)
                    }
                }, 100)
            }
        }

        fun bindItem(itemData: Venues) {
            //itemBinding.titleTxtView.text = itemData.title
            Utils.showFadeInAnimOnText(
                itemView.context,
                itemBinding.title,
                itemData.name!!
            )

            if (!itemData.address.isNullOrEmpty())
                Utils.showBounceAnimOnText(
                    itemView.context,
                    itemBinding.orderIdTxtView,
                    itemData.address!!
                )

            else
                Utils.showBounceAnimOnText(
                    itemView.context,
                    itemBinding.orderIdTxtView,
                    "NA"
                )

            Utils.showBounceAnimOnText(
                itemView.context,
                itemBinding.cityTxtView,
                itemData.city!!
            )



            Handler().postDelayed({
                if (!itemData.imageURL.isNullOrEmpty()) {
                    Utils.setImage(itemBinding.productImageView, itemData.imageURL!!)
                } else {
                    Log.d(
                        "DEBUG",
                        "NewsAdapter itemData.urlToImage is ${itemData.imageURL}"
                    )
                    val dummyUrl =
                        "https://ichef.bbci.co.uk/news/1024/branded_news/D268/production/_118046835_screenshot2021-04-13at22.37.06.png"
                    Utils.setImage(itemBinding.productImageView, dummyUrl)
                }
            }, 100)

        }

    }

    fun updateList(dummyList: ArrayList<Venues>) {
        data = dummyList
        notifyDataSetChanged()
    }
}