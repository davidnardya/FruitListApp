package com.davidnardya.fruitlistapp.fragment

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.davidnardya.fruitlistapp.databinding.FragmentSingleFruitBinding
import com.davidnardya.fruitlistapp.model.FruitItem

class SingleFruitFragment(private val fruitItem: FruitItem) : Fragment() {

    //Properties
    lateinit var name: String
    lateinit var description: String
    lateinit var price: String
    lateinit var binding: FragmentSingleFruitBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleFruitBinding.inflate(inflater,container,false)

        setData()

        return binding.root
    }

    //Private methods
    private fun setData() {
        defineStrings()
        setTVText()
        setImageView()

    }

    private fun setImageView() {
        Glide.with(binding.fullFruitImageView)
            .asBitmap()
            .load(fruitItem.image)
            .into(binding.fullFruitImageView)
    }

    private fun setTVText() {
        binding.fullFruitNameTextView.text = name
        binding.fullFruitNameTextView.gravity = Gravity.CENTER
        binding.fullFruitDescriptionTextView.text = description
        binding.fullFruitDescriptionTextView.gravity = Gravity.CENTER
        binding.fullFruitPriceTextView.text = price
        binding.fullFruitPriceTextView.gravity = Gravity.CENTER
    }

    private fun defineStrings() {
        name = "Name:\n${fruitItem.name}"
        description = "Description:\n${fruitItem.description}"
        price = "Price:\n${fruitItem.price}"
    }
}