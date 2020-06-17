package com.nightmareinc.communere.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.nightmareinc.communere.R
import com.nightmareinc.communere.database.UserDatabase
import com.nightmareinc.communere.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentMainBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModelFactory = MainViewModelFactory(dataSource, application)

        val mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)


        binding.mainViewModel = mainViewModel
        binding.lifecycleOwner = this

        /*signupViewModel.navigateToMainScreen.observe(this, Observer {
            user ->
            user?.let {
                this.findNavController().navigate(
                    SignupFragmentDirections

                )
            }
        })*/

        return binding.root
    }

}