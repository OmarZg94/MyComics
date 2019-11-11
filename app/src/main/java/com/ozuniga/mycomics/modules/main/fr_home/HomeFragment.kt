package com.ozuniga.mycomics.modules.main.fr_home


import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ozuniga.mycomics.R
import com.ozuniga.mycomics.databinding.FragmentHomeBinding
import com.ozuniga.mycomics.modules.main.MainActivity
import com.ozuniga.mycomics.utils.Utils


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var activity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.rcvComics.layoutManager = if (Utils.isTablet()) {
            if (activity.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                GridLayoutManager(activity, 3, RecyclerView.VERTICAL, false)
            } else {
                GridLayoutManager(activity, 5, RecyclerView.VERTICAL, false)
            }
        } else {
            GridLayoutManager(activity, 1, RecyclerView.VERTICAL, false)
        }
        return binding.root
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            GridLayoutManager(activity, 3, RecyclerView.VERTICAL, false)
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(activity, 5, RecyclerView.VERTICAL, false)
        }
        binding.rcvComics.adapter?.notifyDataSetChanged()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment HomeFragment.
         */
        @JvmStatic
        fun newInstance() =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
