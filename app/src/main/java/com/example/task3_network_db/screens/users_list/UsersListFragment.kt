package com.example.task3_network_db.screens.users_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.task3_network_db.databinding.FragmentUsersListBinding
import com.example.task3_network_db.domain.model.User
import com.example.task3_network_db.screens.users_list.adapter.MyUserListAdapter

class UsersListFragment : Fragment() {

    private lateinit var binding: FragmentUsersListBinding

    private val viewModel: UsersListViewModel by viewModels()

    private val listAdapter by lazy {
        MyUserListAdapter {
            onItemClick(it)
        }
    }

    private fun onItemClick(user: User) {
        showUserDetailsScreen(user)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = listAdapter

        initObserver()
    }

    private fun initObserver() {
        viewModel.itemsList.observe(viewLifecycleOwner) { itemsList ->
            showItems(itemsList)
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                handleError(error)
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.loadingIndicator.visibility = if (isLoading){
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun showItems(itemsList: List<User>) {
        listAdapter.submitList(itemsList)
    }

    private fun handleError(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    private fun showUserDetailsScreen(user: User) {
//        activity?.supportFragmentManager
//            ?.beginTransaction()
//            ?.replace(R.id.fragmentContainerView, UserDetailsFragment.newInstance(user))
//            ?.addToBackStack(null)
//            ?.commit()
    }
}