package com.nightmareinc.communere.userdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.nightmareinc.communere.R
import com.nightmareinc.communere.database.UserDatabase
import com.nightmareinc.communere.databinding.FragmentUserDetailBinding
import com.nightmareinc.communere.repository.UserRepository
import com.nightmareinc.communere.signup.SignupFragmentDirections

class UserDetailFragment : Fragment() {

    lateinit var binding: FragmentUserDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_user_detail, container, false)

        val application = requireNotNull(this.activity).application

        val arguments = UserDetailFragmentArgs.fromBundle(arguments!!)

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModelFactory = UserDetailViewModelFactory(arguments.userEvent, UserRepository(dataSource))

        val userDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserDetailViewModel::class.java)

        binding.userDetailViewModel = userDetailViewModel
        binding.lifecycleOwner = this

        // Get current user details
        userDetailViewModel.viewStateLiveData.observe(this, Observer {
            render(it)
        })

        // Delete current user
        binding.deleteButton.setOnClickListener {
            userDetailViewModel.deleteUser()
        }

        userDetailViewModel.navigateToSignup.observe(this, Observer {
            this.findNavController().navigate(
                UserDetailFragmentDirections.actionUserDetailFragmentToSignupFragment()
            )
        })


        return binding.root
    }

    fun render(userDetailViewState: UserDetailViewState) {
        binding.userFullName.text = userDetailViewState.user.fullname
        binding.usernameText.setText(userDetailViewState.user.fullname)
        binding.passwordText.setText(userDetailViewState.user.email)

        if (userDetailViewState.isAdmin) {
            binding.deleteButton.visibility = View.GONE
            binding.updateButton.visibility = View.GONE
        } else {
            binding.deleteButton.visibility = View.VISIBLE
            binding.updateButton.visibility = View.VISIBLE
        }
    }

}