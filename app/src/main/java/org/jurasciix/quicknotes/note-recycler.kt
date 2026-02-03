package org.jurasciix.quicknotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.jurasciix.quicknotes.databinding.ItemNoteBinding
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

class NoteItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemNoteBinding.bind(view)
}

class NoteAdapter : RecyclerView.Adapter<NoteItemViewHolder>() {
    private val formatter = DateTimeFormatter
        .ofLocalizedDate(FormatStyle.SHORT) // SHORT, MEDIUM, LONG или FULL
        .withLocale(Locale.getDefault())
        .withZone(ZoneId.systemDefault())

    private val items = mutableListOf<Note>()

    private var onClickListener: (Note) -> Unit = { }

    private var onCompleteChangeListener: (Note, Boolean) -> Unit = { _, _ -> }

    fun submitItems(items: List<Note>) {
        this.items.clear()
        this.items += items
        notifyDataSetChanged()
    }

    fun addItem(item: Note) {
        items += item
        notifyItemInserted(items.size - 1)
    }

    fun removeItem(position: Int): Note {
        val item = items.removeAt(position)
        notifyItemRemoved(position)
        return item
    }

    fun changeItem(position: Int, item: Note): Note {
        val oldItem = items[position]
        items[position] = item
        return oldItem
    }

    fun setOnClickListener(listener: (Note) -> Unit) {
        onClickListener = listener
    }

    fun setOnCompleteListener(listener: (Note, Boolean) -> Unit) {
        onCompleteChangeListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_note, parent, false)
        return NoteItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        val item = items[position]

        with(holder.binding) {
            title.text = item.title
            date.text = formatter.format(Instant.ofEpochSecond(item.created))
            deadline.text = formatter.format(Instant.ofEpochSecond(item.deadline))
            complete.isChecked = item.completed

            root.animateClick {
                onClickListener(item)
            }

            complete.setOnCheckedChangeListener { _, isChecked ->
                onCompleteChangeListener(item, isChecked)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}