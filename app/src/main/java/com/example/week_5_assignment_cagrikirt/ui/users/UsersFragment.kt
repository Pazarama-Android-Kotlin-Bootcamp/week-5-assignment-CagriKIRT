package com.example.week_5_assignment_cagrikirt.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.week_5_assignment_cagrikirt.databinding.FragmentUserBinding
import com.example.week_5_assignment_cagrikirt.ui.model.DataState
import com.example.week_5_assignment_cagrikirt.ui.users.adapter.UsersAdapter
import com.example.week_5_assignment_cagrikirt.ui.users.viewmodel.UserViewEvent
import com.example.week_5_assignment_cagrikirt.ui.users.viewmodel.UsersViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private val viewModel by viewModels<UsersViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentUserBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.userLiveData.observe(viewLifecycleOwner){
            when(it){
                is DataState.Success -> {

                    it.data?.let { safeData->
                        binding.userRecyclerview.adapter = UsersAdapter().apply {
                            submitList(safeData)
                        }
                    } ?: run {
                        Toast.makeText(requireContext(),"No data", Toast.LENGTH_SHORT).show()
                    }
                }
                is DataState.Error -> {

                    Snackbar.make(binding.root,it.message, Snackbar.LENGTH_LONG).show()

                }
                is DataState.Loading -> {

                }
            }
        }

        viewModel.eventStateLiveData.observe(viewLifecycleOwner){
            when (it) {
                is UserViewEvent.ShowMessage -> {}
                is UserViewEvent.NavigateToDetail -> {}
            }
        }
    }
}