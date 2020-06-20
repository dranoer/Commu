package com.nightmareinc.communere.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.nightmareinc.communere.R
import com.nightmareinc.communere.database.UserDatabase
import com.nightmareinc.communere.databinding.FragmentLoginBinding
import com.nightmareinc.communere.repository.UserRepository

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModelFactory = LoginViewModelFactory(UserRepository(dataSource))

        val loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

        /**Assign the loginViewModel binding variable to the loginViewModel.
         * i mean >> Set the variable in XML Layout which has access throw the binding object to the viewModel
         */
        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this // Binding can observe LiveData's updates


        binding.loginButton.setOnClickListener {
            loginViewModel.onLoginButtonClick(
                binding.usernameText.text.toString(),
//                binding.usernameText.text.toString(),
                binding.passwordText.text.toString()
//                binding.passwordText.text.toString()
            )
        }

        loginViewModel.navigateToUserList.observe(this, Observer{
            this.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToMainFragment(it))
        })

        ///////////////////////////////////////////////////////////////

        binding.signupButton.setOnClickListener {
            loginViewModel.onSignupButtonClick()
        }

        loginViewModel.navigateToSignup.observe(this, Observer {
            this.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToSignupFragment())
        })

        return binding.root
    }

}