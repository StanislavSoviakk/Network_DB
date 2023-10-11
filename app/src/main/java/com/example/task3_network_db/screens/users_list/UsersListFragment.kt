package com.example.task3_network_db.screens.users_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.task3_network_db.R
import com.example.task3_network_db.databinding.FragmentUsersListBinding
import com.example.task3_network_db.domain.model.User
import com.example.task3_network_db.getAppComponent
import com.example.task3_network_db.screens.details.UserDetailsFragment
import com.example.task3_network_db.screens.users_list.adapter.MyUserListAdapter
import javax.inject.Inject

class UsersListFragment : Fragment() {

    private lateinit var binding: FragmentUsersListBinding

    @Inject
    lateinit var factory: UsersListViewModelFactory

    private val viewModel: UsersListViewModel by viewModels {
        factory
    }

    private val listAdapter by lazy {
        MyUserListAdapter(
            onItemClick = {
                showUserDetailsScreen(it.uuid)
            },
            onListEndReached = {
                viewModel.loadUsers()
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        getAppComponent().injectUsersListFragment(this)
        binding = FragmentUsersListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = listAdapter

        initObserver()
    }

    private fun initObserver() {
        viewModel.usersList.observe(viewLifecycleOwner) { usersList ->
            showUsers(usersList)
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                handleError(error)
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.loadingIndicator.isVisible = isLoading
        }
    }

    private fun showUsers(UsersList: List<User>) {
        listAdapter.submitList(UsersList)
    }

    private fun handleError(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    private fun showUserDetailsScreen(userId: String) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragmentContainerView, UserDetailsFragment.newInstance(userId))
            ?.addToBackStack(null)
            ?.commit()
    }
}