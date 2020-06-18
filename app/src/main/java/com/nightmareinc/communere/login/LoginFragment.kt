package com.nightmareinc.communere.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.nightmareinc.communere.Constant
import com.nightmareinc.communere.R
import com.nightmareinc.communere.database.UserDatabase
import com.nightmareinc.communere.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModelFactory = LoginViewModelFactory(dataSource, application)

        val loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

        /**Assign the loginViewModel binding variable to the loginViewModel.
         * i mean >> Set the variable in XML Layout which has access throw the binding object to the viewModel
         */
        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this // Binding can observe LiveData's updates

        binding.signupButton.setOnClickListener {
            Log.i("naz", "worked")
            this.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToSignupFragment())
        }

        binding.loginButton.setOnClickListener {

            val constants = Constant()
            // Admin Login
            if (binding.usernameText.text.toString() == constants.ADMIN_USER
                && binding.passwordText.text.toString() == constants.ADMIN_PASSWORD) {
                this.findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToMainFragment(true))
            } else { // User Login
                this.findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToMainFragment(false))
            }

        }

        return binding.root
    }

}