package com.invillia.meubeats.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.invillia.meubeats.R
import com.invillia.meubeats.databinding.FragmentProductListBinding
import com.invillia.meubeats.domain.model.Headphone
import com.invillia.meubeats.presentation.adapter.ProductListAdapter
import com.invillia.meubeats.presentation.extension.createDialog
import com.invillia.meubeats.presentation.viewmodel.ProductListViewModel
import com.invillia.meubeats.presentation.viewmodel.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<ProductListViewModel>()

    private val productAdapter by lazy {
        ProductListAdapter { headphone ->
            viewModel.onProductClicked(headphone)
        }
    }
    private val dialog by lazy { requireContext().createDialog() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        bindCollectors()
        setupRecyclerView()
    }

    private fun bindToolbar() {
        binding.tbProductList.inflateMenu(R.menu.menu_product_list)
    }

    private fun bindCollectors() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collect(::getHeadphones)
                }
                launch {
                    viewModel.navigateToProductDetails.collect(::navigateToDetailsScreen)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = productAdapter
        }
    }

    private fun getHeadphones(state: State) {
        when (state) {
            is State.Loading -> showLoading()
            is State.Error -> showError(state.throwable)
            is State.EmptyList -> showEmptyList()
            is State.Success -> showSuccess(state.headphoneList)
        }
    }

    private fun navigateToDetailsScreen(headphone: Headphone?) {
        headphone?.let {
            this.findNavController().navigate(
                ProductListFragmentDirections.actionProductListFragmentToProductDetailsFragment(
                    headphone = it
                )
            )
        }
        viewModel.doneNavigatingToProductDetails()
    }

    private fun showLoading() {
        dialog.dismiss()
        binding.apply {
            spinnerLoading.visibility = View.VISIBLE
            tvEmptyList.visibility = View.GONE
        }
    }

    private fun showError(throwable: Throwable) {
        binding.apply {
            spinnerLoading.visibility = View.GONE
            tvEmptyList.visibility = View.GONE
        }
        dialog.setMessage(throwable.message)
        dialog.show()
    }

    private fun showEmptyList() {
        dialog.dismiss()
        binding.apply {
            spinnerLoading.visibility = View.GONE
            tvEmptyList.visibility = View.VISIBLE
        }
    }

    private fun showSuccess(headphoneList: List<Headphone>) {
        dialog.dismiss()
        binding.apply {
            spinnerLoading.visibility = View.GONE
            tvEmptyList.visibility = View.GONE
        }
        productAdapter.submitList(headphoneList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}