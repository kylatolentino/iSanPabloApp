package com.app.isanpablo.ui.home.business.doingbusiness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.isanpablo.R
import com.app.isanpablo.databinding.HomeBusinessDoingbusinessBinding



class DoingBusinessFragment : Fragment() {

    private var _binding: HomeBusinessDoingbusinessBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeBusinessDoingbusinessBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.btnNewBusiness.setOnClickListener {
            findNavController().navigate(R.id.nav_home_business_doingbusiness_newbusiness)
        }
        binding.btnPermit.setOnClickListener {
            findNavController().navigate(R.id.nav_home_business_doingbusiness_permitrenew)
        }
        binding.btnTric.setOnClickListener {
            findNavController().navigate(R.id.nav_home_business_doingbusiness_tric)
        }


        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}
