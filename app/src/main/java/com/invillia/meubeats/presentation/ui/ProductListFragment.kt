package com.invillia.meubeats.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
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
import com.invillia.meubeats.presentation.util.EspressoIdlingResource
import com.invillia.meubeats.presentation.util.UiState
import com.invillia.meubeats.presentation.viewmodel.ProductListViewModel
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
        searchViewClickListener()
    }

    private fun searchViewClickListener(): Boolean {
        binding.tbProductList.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener {
            val searchView = it.actionView as SearchView
            searchView.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    Toast.makeText(context, query ?: "", Toast.LENGTH_SHORT).show()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
            return@OnMenuItemClickListener false
        })
        return false
    }

    private fun bindCollectors() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.headphoneState.collect(::getHeadphones)
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

    private fun getHeadphones(uiState: UiState) {
        when (uiState) {
            is UiState.EmptyList -> showEmptyList()
            is UiState.Loading -> showLoading(uiState.list)
            is UiState.Error -> showError(uiState.list, uiState.error)
            is UiState.Success -> showSuccess(uiState.list)
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

    private fun showLoading(list: List<Headphone>?) {
        EspressoIdlingResource.increment()
        dialog.dismiss()
        binding.apply {
            spinnerLoading.visibility = View.VISIBLE
            tvEmptyList.visibility = View.GONE
        }
        productAdapter.submitList(list)
        EspressoIdlingResource.decrement()
    }

    private fun showError(list: List<Headphone>?, error: String?) {
        binding.apply {
            spinnerLoading.visibility = View.GONE
            tvEmptyList.visibility = View.GONE
        }
        dialog.setMessage(error)
        dialog.show()
        productAdapter.submitList(list)
        EspressoIdlingResource.decrement()
    }

    private fun showEmptyList() {
        dialog.dismiss()
        binding.apply {
            spinnerLoading.visibility = View.GONE
            tvEmptyList.visibility = View.VISIBLE
        }
    }

    private fun showSuccess(headphoneList: List<Headphone>?) {
        dialog.dismiss()
        binding.apply {
            spinnerLoading.visibility = View.GONE
            tvEmptyList.visibility = View.GONE
        }
        productAdapter.submitList(headphoneList)
        EspressoIdlingResource.decrement()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}