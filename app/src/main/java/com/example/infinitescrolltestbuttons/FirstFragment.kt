package com.example.infinitescrolltestbuttons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infinitescrolltestbuttons.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar

/**
 * Fragment demonstrating infinite scrolling with 2 buttons on each side,
 * where item numbers increment indefinitely (Item #1, Item #2, Item #3...).
 */
class
FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: InfiniteScrollAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup adapter with click listeners for row item buttons
        adapter = InfiniteScrollAdapter { _, title, buttonName ->
            Snackbar.make(
                binding.root,
                "Clicked $buttonName on $title",
                Snackbar.LENGTH_SHORT,
            ).show()
        }

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        // Start at position 0 (Item #1)
        binding.recyclerView.scrollToPosition(0)

        // Screen Left Button 1: Scroll up
        binding.btnScreenLeft1.setOnClickListener {
            val currentPos = layoutManager.findFirstVisibleItemPosition()
            if (currentPos != RecyclerView.NO_POSITION) {
                val targetPos = (currentPos - 5).coerceAtLeast(0)
                binding.recyclerView.smoothScrollToPosition(targetPos)
            }
        }

        // Screen Left Button 2: Reset to Item #1 (position 0)
        binding.btnScreenLeft2.setOnClickListener {
            binding.recyclerView.smoothScrollToPosition(0)
            Snackbar.make(binding.root, "Reset to Item #1", Snackbar.LENGTH_SHORT).show()
        }

        // Screen Right Button 1: Scroll down
        binding.btnScreenRight1.setOnClickListener {
            val currentPos = layoutManager.findFirstVisibleItemPosition()
            if (currentPos != RecyclerView.NO_POSITION) {
                val targetPos = currentPos + 5
                binding.recyclerView.smoothScrollToPosition(targetPos)
            }
        }

        // Screen Right Button 2: Jump down 20 items
        binding.btnScreenRight2.setOnClickListener {
            val currentPos = layoutManager.findFirstVisibleItemPosition()
            if (currentPos != RecyclerView.NO_POSITION) {
                val targetPos = currentPos + 20
                binding.recyclerView.smoothScrollToPosition(targetPos)
                Snackbar.make(
                    binding.root,
                    "Jumped to Item #${targetPos + 1}",
                    Snackbar.LENGTH_SHORT,
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
