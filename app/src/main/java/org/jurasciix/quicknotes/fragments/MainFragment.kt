package org.jurasciix.quicknotes.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.jurasciix.quicknotes.R
import org.jurasciix.quicknotes.animateClick
import org.jurasciix.quicknotes.databinding.FragmentMainBinding

enum class Tabs(val resId: Int) {
    PENDING(R.string.tab_pending),
    COMPLETED(R.string.tab_completed)
}

class TabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment {
        return when (Tabs.entries[position]) {
            Tabs.PENDING -> NoteListFragment()
            Tabs.COMPLETED -> NoteListFragment()
        }
    }

    override fun getItemCount(): Int {
        return Tabs.entries.size
    }
}

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view).apply {
            pager.adapter = TabAdapter(this@MainFragment)

            val mediator = TabLayoutMediator(tabs, pager) { tab, position ->
                tab.setText(Tabs.entries[position].resId)
            }
            mediator.attach()

            add.animateClick {
                // todo
            }
        }
    }
}