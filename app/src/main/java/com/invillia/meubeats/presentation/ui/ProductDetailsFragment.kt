package com.invillia.meubeats.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.invillia.meubeats.databinding.FragmentProductDetailsBinding
import com.invillia.meubeats.presentation.imagecaching.ImageCaching
import org.koin.android.ext.android.inject

class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by lazy { ProductDetailsFragmentArgs.fromBundle(requireArguments()) }
    private val imageCaching: ImageCaching by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        bindData()
    }

    private fun bindToolbar() {
        binding.tbProductDetails.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun bindData() {
        val receivedHeadphone = args.headphone
        with(binding) {
            tvModelName.text = receivedHeadphone.model
            tvConnection.text = receivedHeadphone.connection
            tvCompatibility.text = receivedHeadphone.compatibility
            tvCharging.text = receivedHeadphone.charge
            tvAutonomy.text = receivedHeadphone.autonomy
            tvHeight.text = receivedHeadphone.height
            tvCapture.text = receivedHeadphone.capture
            imageCaching.displayImage(
                context = root.context,
                url = receivedHeadphone.image,
                target = ivHeadphone
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}