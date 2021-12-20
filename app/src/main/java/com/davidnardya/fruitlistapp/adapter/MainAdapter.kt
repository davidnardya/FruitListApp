package com.davidnardya.fruitlistapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.davidnardya.fruitlistapp.R
import com.davidnardya.fruitlistapp.databinding.FruitItemLayoutBinding
import com.davidnardya.fruitlistapp.fragment.SingleFruitFragment
import com.davidnardya.fruitlistapp.model.FruitItem

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    //Properties
    var fruitList = emptyList<FruitItem>()

    //ViewHolder
    inner class ViewHolder(val binding: FruitItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fruitItem: FruitItem) {
            binding.fruitTextView.text = fruitItem.name

            Glide.with(binding.fruitImageView)
                .asBitmap()
                .load(fruitItem.image)
                .into(binding.fruitImageView)

            binding.fruitLayoutContainer.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val activity = v!!.context as AppCompatActivity
                    val singleFruitFragment = SingleFruitFragment(fruitItem)
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.main_activity, singleFruitFragment).addToBackStack(null)
                        .commit()
                }
            })
        }
    }

    //RV Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FruitItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(fruitList[position])
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    //Public methods
    fun setData(newList: List<FruitItem>) {
        fruitList = newList
        notifyDataSetChanged()
    }
}