package com.nightmareinc.communere.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nightmareinc.communere.R
import com.nightmareinc.communere.database.UserDatabase
import com.nightmareinc.communere.databinding.FragmentUserListBinding
import com.nightmareinc.communere.repository.UserRepository

class UserListFragment : Fragment() {

    lateinit var binding: FragmentUserListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_user_list, container, false)

        val application = requireNotNull(this.activity).application

        val arguments = UserListFragmentArgs.fromBundle(arguments!!)

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModelFactory = UserListViewModelFactory(arguments.userAuthInfo, UserRepository(dataSource))

        val userListViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserListViewModel::class.java)

        binding.userListViewModel = userListViewModel
        binding.lifecycleOwner = this

        val manager = LinearLayoutManager(context)
        binding.userList.layoutManager = manager

        val adapter = UserAdapter(UserListener { userId ->
            userListViewModel.onUserClicked(userId)
        })
        binding.userList.adapter = adapter

        // Get user list
//        userListViewModel.viewStateLiveData.observe(this, Observer {
//            render(it)
//        })

        userListViewModel.usersLiveData.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        userListViewModel.navigateToUserDetail.observe(this, Observer { user ->
            user?.let {
                this.findNavController().navigate(
                    UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(user))
            }
        })

        return binding.root
    }

    /*fun render(userDetailViewState: UserDetailViewState) {

//        binding..text = userDetailViewState.user.fullname
        binding.usernameText.setText(userDetailViewState.user.fullname)
        binding.passwordText.setText(userDetailViewState.user.email)

        if (userDetailViewState.isAdmin) {
            binding.deleteButton.visibility = View.GONE
            binding.updateButton.visibility = View.GONE
        } else {
            binding.deleteButton.visibility = View.VISIBLE
            binding.updateButton.visibility = View.VISIBLE
        }
    }*/

}