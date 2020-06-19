package com.nightmareinc.communere.userdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.nightmareinc.communere.R
import com.nightmareinc.communere.database.UserDatabase
import com.nightmareinc.communere.databinding.FragmentUserDetailBinding

class UserDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentUserDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_user_detail, container, false)

        val application = requireNotNull(this.activity).application

        val arguments = UserDetailFragmentArgs.fromBundle(arguments!!)

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModelFactory = UserDetailViewModelFactory(arguments.userEvent, dataSource)

        val userDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserDetailViewModel::class.java)

        binding.userDetailViewModel = userDetailViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}