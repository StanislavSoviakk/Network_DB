package com.example.task3_network_db.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.task3_network_db.R
import com.example.task3_network_db.data.local.DatabaseClient
import com.example.task3_network_db.data.repository.RandomUsersRepositoryImpl
import com.example.task3_network_db.databinding.FragmentUserDetailsBinding
import com.example.task3_network_db.domain.model.User
import com.example.task3_network_db.domain.use_case.GetUserByIdUseCase

private const val ARG_ID = "id"

class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding

    private val viewModel: UserDetailsViewModel by viewModels {
        UserDetailsViewModelFactory(
            GetUserByIdUseCase(
                RandomUsersRepositoryImpl(
                    DatabaseClient.createDatabase(
                        requireContext()
                    ).dao
                )
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        loadArguments()
    }

    private fun initObserver() {
        viewModel.user.observe(viewLifecycleOwner) { user ->
            bindUserData(user)
        }
    }

    private fun bindUserData(user: User) {
        with(binding) {
            imageProfile.load(user.picture)
            textName.text =
                context?.getString(R.string.user_full_name, user.firstName, user.lastName)
            textEmail.text = user.email
            textPhone.text = user.phone
        }
    }

    private fun loadArguments() {
        arguments?.getString(ARG_ID)?.let {
            viewModel.loadUserById(it)
        }
    }

    companion object {
        fun newInstance(userId: String): UserDetailsFragment {
            return UserDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ID, userId)
                }
            }
        }
    }

}