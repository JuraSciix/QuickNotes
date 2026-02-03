package org.jurasciix.quicknotes.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import org.jurasciix.quicknotes.MainViewModel
import org.jurasciix.quicknotes.NoteAdapter
import org.jurasciix.quicknotes.R
import org.jurasciix.quicknotes.animateClick
import org.jurasciix.quicknotes.databinding.FragmentNoteListBinding

class NoteListFragment : Fragment(R.layout.fragment_note_list) {
    private val viewModel: MainViewModel by activityViewModels()

    private val noteAdapter = NoteAdapter()

    private lateinit var binding: FragmentNoteListBinding

    init {
        noteAdapter.setOnClickListener { note ->
            // todo
        }

        noteAdapter.setOnCompleteListener { note, isCompleted ->
            // todo
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNoteListBinding.bind(requireView()).apply {
            // todo: ItemDecorator с отступами между элементами
            noteRecycler.adapter = noteAdapter

            add.animateClick {
                // todo
            }
        }

        // todo: Skeleton?
    }

    override fun onStart() {
        super.onStart()

        viewModel.notes.observe(this) { items ->
            if (items.isEmpty()) {
                switchTip()
            }
            noteAdapter.submitItems(items)
        }
    }

    private fun switchTip() {
        with(binding) {
            tip.isVisible = true
        }
    }
}