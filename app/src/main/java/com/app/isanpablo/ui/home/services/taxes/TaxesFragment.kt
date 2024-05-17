package com.app.isanpablo.ui.home.services.taxes

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.isanpablo.R
import com.app.isanpablo.databinding.HomeServicesTaxesBinding


class TaxesFragment : Fragment() {

    private var _binding: HomeServicesTaxesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeServicesTaxesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnRPT.setOnClickListener {
            showConfirmationDialog()
        }
        binding.btnOMP.setOnClickListener {
            val link ="https://www.landbank.com/"
            showConfirmation(link)
        }
        binding.btnLCR.setOnClickListener {
            val link ="https://www.landbank.com/"
            showConfirmation(link)
        }
        binding.btnCertification.setOnClickListener {
            val link ="https://www.landbank.com/"
            showConfirmation(link)
        }
        binding.imageButton40.setOnClickListener {
            val link ="https://www.landbank.com/"
            showConfirmation(link)
        }
        binding.imageButton36.setOnClickListener {
            val link ="https://www.landbank.com/"
            showConfirmation(link)
        }


        return root
    }

    private fun showConfirmationDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.home_business_investment)
        dialog.window?.setBackgroundDrawableResource(R.drawable.shape_rounded_dialog_border)
        val exitButton: Button = dialog.findViewById(R.id.btnCLose)
        val invest: Button = dialog.findViewById(R.id.btnInvest)
        val textView494: TextView = dialog.findViewById(R.id.textView494)
        val cdp: Button = dialog.findViewById(R.id.btnComp)

        invest.text ="Request Assessment"
        cdp.text = "Online Payment"
        textView494.text = "  RPT Payment"

        exitButton.setOnClickListener {
            dialog.dismiss() // Dismiss the dialog
        }
        invest.setOnClickListener {
            navigateToRPTPayment()
            dialog.dismiss()
        }
        cdp.setOnClickListener {
            val link: String ="https://www.landbank.com/"
            showConfirmation(link)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun navigateToRPTPayment() {
        findNavController().navigate(R.id.nav_home_services_taxes_rptpayment)
    }


    private fun showConfirmation(url: String) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.confirmation_dialog)
        val exitButton: Button = dialog.findViewById(R.id.btnCancel)
        val yesButton: Button = dialog.findViewById(R.id.btnOk)
        val textcontent: TextView = dialog.findViewById(R.id.textView490)
        val textTitle: TextView = dialog.findViewById(R.id.textView489)
        textTitle.text = "You’re leaving our app"
        textcontent.text =
            "The website you’re viewing is attempting to open an external app. Would you like to continue?"
        exitButton.setOnClickListener {
            dialog.dismiss() // Dismiss the dialog
        }
        yesButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
            dialog.dismiss()
        }
        dialog.show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
