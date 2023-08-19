package be.tftic.web2023.demo11_async_webrequest.ui.demorequest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import be.tftic.web2023.demo11_async_webrequest.databinding.FragmentDemoRequestBinding
import be.tftic.web2023.demo11_async_webrequest.models.FinalFantasyCharacter
import be.tftic.web2023.demo11_async_webrequest.requests.FinalFantasyRequest2
import kotlinx.coroutines.launch

class DemoRequestFragment2 : Fragment(), FinalFantasyRequest2.OnRequestResultListener {

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
            FinalFantasyRequest2(this@DemoRequestFragment2).searchRandomCharacter()
        }
    }
    override fun onRequestSuccessResult(character: FinalFantasyCharacter) {
        Toast.makeText(requireContext(), character.name + " " + character.origin, Toast.LENGTH_LONG).show()
    }
    override fun onRequestErrorResult(message: String) {
       Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}