package net.justdave.nwsweatheralertswidget

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import net.justdave.nwsweatheralertswidget.databinding.AlertsDisplayFragmentBinding

class AlertsDisplayFragment : Fragment() {

    private lateinit var viewModel: AlertsDisplayViewModel
    private lateinit var binding: AlertsDisplayFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = AlertsDisplayFragmentBinding.inflate(inflater, container, false)
        binding.parsedEvents.setEmptyView(binding.emptytext)

        Log.i("AlertsDisplayFragment", "loaded")
        viewModel = ViewModelProvider(requireActivity()).get(AlertsDisplayViewModel::class.java)
        viewModel.initializeRequestQueue(requireActivity().applicationContext)
        lifecycleScope.launch {
            try {
                viewModel.getEmptyText { response ->
                    binding.emptytext.setText(response, TextView.BufferType.NORMAL)
                }
            } finally {
                // foo
            }
        }

        return binding.root
    }

}

