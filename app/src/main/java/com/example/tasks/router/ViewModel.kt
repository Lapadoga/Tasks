package com.example.tasks.router

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {

    fun <T : Fragment> changePage(
        fragmentManager: FragmentManager,
        fragmentClass: Class<T>,
        fragmentId: Int
    ) {
        fragmentManager.commit {
            replace(fragmentId, fragmentClass, null)
        }
    }
}