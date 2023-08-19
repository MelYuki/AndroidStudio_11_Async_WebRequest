package be.tftic.web2023.demo11_async_webrequest.ui.demoasync

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import be.tftic.web2023.demo11_async_webrequest.R
import be.tftic.web2023.demo11_async_webrequest.databinding.FragmentDemoAsyncBinding
import be.tftic.web2023.demo11_async_webrequest.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class DemoAsyncFragment : Fragment() {

    private lateinit var binding : FragmentDemoAsyncBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDemoAsyncBinding.inflate(inflater, container, false)

        // Listener on big pink bouton
        binding.btnFragAsyncStart.setOnClickListener { startCounterAsync2() }
        binding.btnFragAsyncLife.setOnClickListener {
            Toast.makeText(requireContext(), "Ui active :o", Toast.LENGTH_SHORT).show()
        }

        // Return view
        return binding.root
    }

    private fun startCounterAsync() {

        lifecycleScope.launch(Dispatchers.Default) {
            val after = LocalDateTime.now().plusSeconds(30)
            while (after > LocalDateTime.now()) {

            }
        }

        // ↓ Thread async (IO)
        lifecycleScope.launch(Dispatchers.IO) {

            var counter = 0

            while(counter <= 10) {

                // ↓ Thread Main (UI)
                lifecycleScope.launch(Dispatchers.Main){
                    binding.tvFragAsyncCounter.text = getString(R.string.incr_counter, counter)
                }

                delay(1_000)
                counter++
            }
        }
    }


    private fun startCounterAsync2() {
        lifecycleScope.launch {
            runCounter()

            binding.tvFragAsyncCounter.setText(getString(R.string.counter_end))
        }
    }

    // Le mot clef "suspend" permet de créer un fonction async
    private suspend fun runCounter() {
        // ← Ici, on est dans le thread de la coroutine qui utilise la fonction

        // ↓ On déplace l'execution du code suivant dans un autre thread (IO)
        withContext(Dispatchers.IO) {
            // ← Ici, on est dans le thread IO
            var counter = 0

            while(counter <= 10) {

                // Mise à jours du visuel
                updateCounter(counter)

                counter++
                delay(1_000)
            }
        }
    }

    private suspend fun updateCounter(counter : Int) {
        withContext(Dispatchers.Main) {
            binding.tvFragAsyncCounter.text = getString(R.string.incr_counter, counter)
        }
    }
}