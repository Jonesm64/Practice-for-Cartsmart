package com.example.listingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listingapp.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productAdapter = ProductAdapter()
        binding.recyclerViewProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewProducts.adapter = productAdapter

        fetchProductsFromApi()
    }

    private fun fetchProductsFromApi() {
        // TODO: Replace with actual API call / network repository integration
        val placeholderItems = listOf(
            ProductItem(1L, "Sample Product 1", "This is a placeholder description for product 1."),
            ProductItem(2L, "Sample Product 2", "This is a placeholder description for product 2."),
            ProductItem(3L, "Sample Product 3", "This is a placeholder description for product 3.")
        )

        binding.recyclerViewProducts.postDelayed({
            productAdapter.submitList(placeholderItems)
        }, 500)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
