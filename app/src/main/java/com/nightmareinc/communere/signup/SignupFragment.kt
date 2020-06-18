package com.nightmareinc.communere.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.amitshekhar.DebugDB
import com.nightmareinc.communere.R
import com.nightmareinc.communere.database.UserDatabase
import com.nightmareinc.communere.databinding.FragmentSignupBinding
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSignupBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_signup, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModelFactory = SignupViewModelFactory(dataSource, application)

        val signupViewModel = ViewModelProviders.of(this, viewModelFactory).get(SignupViewModel::class.java)


        binding.signupViewModel = signupViewModel
        binding.lifecycleOwner = this

        binding.newSignupButton.setOnClickListener {

            signupViewModel.onAddUser(fullname_text.text.toString(), email_text.text.toString(), password_text.text.toString())

            this.findNavController().navigate(
                SignupFragmentDirections.actionSignupFragmentToMainFragment(false))
        }

        // check database on browser by 'com.amitshekhar.android' library
        Log.i("myIp", DebugDB.getAddressLog())

        return binding.root
    }

}