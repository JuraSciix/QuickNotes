package org.jurasciix.quicknotes.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.jurasciix.quicknotes.R
import org.jurasciix.quicknotes.animateClick
import org.jurasciix.quicknotes.databinding.FragmentMainBinding

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NoteListFragment()
            1 -> NoteListFragment()
            else -> throw IllegalArgumentException("Position: $position")
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view).apply {
            pager.adapter = PagerAdapter(this@MainFragment)

            val mediator = TabLayoutMediator(tabs, pager) { tab, position ->
                tab.setText(when (position) {
                    0 -> R.string.tab_pending
                    1 -> R.string.tab_completed
                    else -> throw IllegalArgumentException("Position: $position")
                })
            }
            mediator.attach()

            add.animateClick {
                // todo
            }
        }
    }
}