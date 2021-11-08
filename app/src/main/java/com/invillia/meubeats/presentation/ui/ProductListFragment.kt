package com.invillia.meubeats.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.invillia.meubeats.R
import com.invillia.meubeats.databinding.FragmentProductListBinding
import com.invillia.meubeats.domain.model.Headphone
import com.invillia.meubeats.presentation.adapter.ProductListAdapter
import com.invillia.meubeats.presentation.viewmodel.ProductListViewModel
import com.invillia.meubeats.presentation.viewmodel.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<ProductListViewModel>()
    private val productAdapter by lazy { ProductListAdapter() }

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
        bindObserver()
        setupRecyclerView()
    }


    private fun bindToolbar() {
        binding.tbProductList.inflateMenu(R.menu.menu_product_list)
    }

    private fun bindObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect(::getHeadphones)
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

    private fun showLoading() {
        Timber.d("Loading")
    }

    private fun showError(throwable: Throwable) {
        Timber.d("Error: ${throwable.message}")
    }

    private fun showEmptyList() {
        Timber.d("Empty")
    }

    private fun showSuccess(headphoneList: List<Headphone>) {
        productAdapter.submitList(headphoneList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}