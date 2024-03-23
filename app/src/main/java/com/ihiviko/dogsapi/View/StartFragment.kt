package com.ihiviko.dogsapi.View

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
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
    private fun showAlertDialog(breed: String) {
        val alertDialog = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
            .setTitle("*************************************")
            .setMessage("La raza Seleccionada es : $breed")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()

        // Aplicar el estilo al subtexto
        val textView = alertDialog.findViewById<TextView>(android.R.id.message)
        textView?.setTextAppearance(R.style.CustomAlertDialogMessage)

        // Aplicar el estilo al botón "OK"
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.apply {
            setTextColor(ContextCompat.getColor(requireContext(), R.color.coral))
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 25f)
        }
}

    private fun launchImagesFragment(){
        val fragment = ImagesFragment()
    }
}