package com.example.task3_network_db.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.task3_network_db.R
import com.example.task3_network_db.databinding.FragmentUserDetailsBinding
import com.example.task3_network_db.domain.model.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val ARG_ID = "id"

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding

    @Inject
    lateinit var userDetailsViewModelFactory: UserDetailsViewModel.AssistedFactory

    private val viewModel: UserDetailsViewModel by viewModels {
        UserDetailsViewModel.provideFactory(
            userDetailsViewModelFactory, arguments?.getString(ARG_ID)
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