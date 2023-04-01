package it.macgood.justnav

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewParent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

fun Fragment.navigate(action: Int) {
    try {
        val activity = requireActivity() as Activity
        var parent = requireView().parent
        parent = findContainer(parent)
        val navController = activity.findNavController(parent.id)
        navController.navigate(action)
    } catch (e: Exception) {
        error("Navigation Error")
    }
}

fun RecyclerView.ViewHolder.navigate(action: Int) {
    try {
        val activity = itemView.context as Activity
        var parent = itemView.parent
        parent = findContainer(parent)
        val navController = activity.findNavController(parent.id)
        navController.navigate(action)
    } catch (e: java.lang.Exception) {
        error("Navigation Error")
    }
}

private fun findContainer(view: ViewParent): FragmentContainerView {
    var parent = view
    while (parent !is FragmentContainerView) {
        parent = parent.parent
    }
    return parent
}

inline fun <T : ViewBinding> Activity.viewBinding(crossinline binding: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        binding.invoke(layoutInflater)
    }