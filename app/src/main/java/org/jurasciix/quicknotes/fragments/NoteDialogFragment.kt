package org.jurasciix.quicknotes.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.jurasciix.quicknotes.R
import org.jurasciix.quicknotes.databinding.DialogNoteBinding

class NoteDialogFragment : DialogFragment(R.layout.dialog_note) {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = requireContext()

        val view = onCreateView(layoutInflater, null, savedInstanceState)!!
        val binding = DialogNoteBinding.bind(view).apply {
            chooseDeadline.setOnClickListener {
                val picker = MaterialDatePicker.Builder.datePicker()
                    .build()
                picker.addOnPositiveButtonClickListener {
                    chooseDeadline.text = picker.headerText
                }
                picker.show(childFragmentManager, null)
            }
        }

        return with(MaterialAlertDialogBuilder(context)) {
            setTitle("Создание заметки")
            setView(binding.root)
            setPositiveButton("Создать") { _, _ ->
                // todo
            }
            setNegativeButton("Отмена", null)
            create()
        }
    }
}