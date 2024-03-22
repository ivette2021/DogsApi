package com.ihiviko.dogsapi.View

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import com.ihiviko.dogsapi.ViewModel.DogViewModel
import com.ihiviko.dogsapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityMainBinding
    val viewModel : DogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        launchFragment()

    }

    private fun launchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(mBinding.frame.id, StartFragment())
            .addToBackStack(null)
            .commit()
    }


}