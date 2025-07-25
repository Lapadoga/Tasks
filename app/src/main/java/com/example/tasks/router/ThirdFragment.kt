package com.example.tasks.router

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.tasks.R
import com.example.tasks.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    private val binding: FragmentThirdBinding by lazy {
        FragmentThirdBinding.inflate(layoutInflater)
    }
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentManager = parentFragmentManager

        binding.next.setOnClickListener {
            viewModel.changePage(
                fragmentManager,
                FirstFragment::class.java,
                R.id.fragment_container
            )
        }
        binding.previous.setOnClickListener {
            viewModel.changePage(
                fragmentManager,
                SecondFragment::class.java,
                R.id.fragment_container
            )
        }
    }
}