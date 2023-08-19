package be.tftic.web2023.demo11_async_webrequest.ui.demorequest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import be.tftic.web2023.demo11_async_webrequest.R
import be.tftic.web2023.demo11_async_webrequest.databinding.FragmentDemoRequestBinding
import be.tftic.web2023.demo11_async_webrequest.models.FinalFantasyCharacter
import be.tftic.web2023.demo11_async_webrequest.requests.FinalFantasyRequest
import be.tftic.web2023.demo11_async_webrequest.requests.FinalFantasyRequest2
import kotlinx.coroutines.launch

class DemoRequestFragment : Fragment() {

    private lateinit var binding : FragmentDemoRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentDemoRequestBinding.inflate(inflater, container, false)

        // Listener
        binding.btnFragRequestCall.setOnClickListener { searchRandomCharactereFF() }

        // Return view
        return binding.root
    }

    private fun searchRandomCharactereFF() {
        lifecycleScope.launch {
            try {
                val character: FinalFantasyCharacter = FinalFantasyRequest().searchRandomCharacter()
                Toast.makeText(requireContext(), character.name + " " + character.origin, Toast.LENGTH_LONG).show()
            }
            catch (error : Exception) {
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}