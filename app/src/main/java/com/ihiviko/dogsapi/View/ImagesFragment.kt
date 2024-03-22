package com.ihiviko.dogsapi.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ihiviko.dogsapi.ImagesAdapter
import com.ihiviko.dogsapi.R
import com.ihiviko.dogsapi.ViewModel.DogViewModel
import com.ihiviko.dogsapi.databinding.FragmentImagesBinding

class ImagesFragment : Fragment() {
    private lateinit var binding : FragmentImagesBinding
    private val viewModel : DogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImagesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val breed = arguments?.getString("breed") ?: ""
        val adapter = ImagesAdapter()
        binding.tbImages.setTitle("Imagenes de razas: $breed")
        binding.rvImages.adapter = adapter
        binding.rvImages.layoutManager = GridLayoutManager(requireContext(), 1)
        viewModel.getImagesByBreedFromInternet(breed)
        viewModel.getImages().observe(viewLifecycleOwner) {
            it?.let {adapter.update(it)  }
        }

        binding.fabBack.setOnClickListener { parentFragmentManager.popBackStack() }
    }


}