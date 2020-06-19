package com.nightmareinc.communere.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.amitshekhar.DebugDB
import com.nightmareinc.communere.R
import com.nightmareinc.communere.UserEvent
import com.nightmareinc.communere.database.UserDatabase
import com.nightmareinc.communere.databinding.FragmentSignupBinding
import com.nightmareinc.communere.login.LoginFragmentDirections
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSignupBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_signup, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModelFactory = SignupViewModelFactory(dataSource)

        val signupViewModel = ViewModelProviders.of(this, viewModelFactory).get(SignupViewModel::class.java)


        binding.signupViewModel = signupViewModel
        binding.lifecycleOwner = this

        binding.newSignupButton.setOnClickListener {
            signupViewModel.onSignupButtonClick(fullname_text.text.toString(), email_text.text.toString(), password_text.text.toString())
        }

        signupViewModel.navigateToUserDetail.observe(this, Observer {
            SignupFragmentDirections.actionSignupFragmentToMainFragment(it)
        })


        // check database on browser by 'com.amitshekhar.android' library
        Log.i("myIp", DebugDB.getAddressLog())

        return binding.root
    }

}