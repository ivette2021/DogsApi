package com.ihiviko.dogsapi.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ihiviko.dogsapi.DogAdapter
import com.ihiviko.dogsapi.Model.Local.Entities.DogEntity
import com.ihiviko.dogsapi.R
import com.ihiviko.dogsapi.ViewModel.DogViewModel
import com.ihiviko.dogsapi.databinding.FragmentStartBinding

class StartFragment : Fragment(){
    private lateinit var mBinding: FragmentStartBinding
    private val viewModel : DogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentStartBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DogAdapter(requireContext())
        adapter.setOnBreedSelectedListener(object : DogAdapter.OnBreedSelectedListener {
            override fun onBreedSelected(breed: DogEntity) {
                showAlertDialog(breed.breed)
            }
        })
        mBinding.rv.adapter = adapter


        mBinding.rv.layoutManager = GridLayoutManager(requireContext(),1)
        viewModel.getBreedList().observe(viewLifecycleOwner) {
            it?.let {
                adapter.update(it)
            }
        }

        adapter.selectedBreed().observe(viewLifecycleOwner) {
            it?.let {
                val bundle = Bundle()
                bundle.putString("breed",it.breed)
                val imagesFragment = ImagesFragment()
                imagesFragment.arguments = bundle


                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame, imagesFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
    // Implementación de la interfaz OnBreedSelectedListener
    override fun onBreedSelected(breed: DogEntity) {
        // Mostrar AlertDialog
        AlertDialog.Builder(requireContext())
            .setTitle("Seleccionaste ${breed.breed}")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun launchImagesFragment(){
        val fragment = ImagesFragment()
    }
}