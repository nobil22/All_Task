package com.example.task_addplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.task_addplayer.databinding.FragmentListBinding


class List_Fragment : Fragment() {
lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentListBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        return binding.root
    }


}