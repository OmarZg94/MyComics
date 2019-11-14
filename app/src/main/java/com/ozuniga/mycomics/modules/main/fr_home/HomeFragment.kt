package com.ozuniga.mycomics.modules.main.fr_home


import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ozuniga.mycomics.R
import com.ozuniga.mycomics.databinding.FragmentHomeBinding
import com.ozuniga.mycomics.helpers.SnackbarHelper
import com.ozuniga.mycomics.modules.main.MainActivity
import com.ozuniga.mycomics.utils.Utils

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val RCV_STATE_KEY = "rcv_state_key"

class HomeFragment : Fragment(), HomeContracts.Presenter, HomeContracts.OnComicClickListener {

    private val iterator = lazy { HomeIterator(this) }
    private lateinit var binding: FragmentHomeBinding
    private lateinit var activity: MainActivity
    private var rcvState: Parcelable? = null

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
        iterator.value.getComics()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (rcvState != null)
            binding.rcvComics.layoutManager?.onRestoreInstanceState(rcvState)
    }

    override fun onComicsReady() {
        binding.rcvComics.adapter = HomeAdapter(iterator.value.comics, this, activity)
    }

    override fun onError(msg: String) {
        SnackbarHelper.showErrorSnackBar(activity, msg, Snackbar.LENGTH_SHORT)
    }

    override fun onComicClick(view: View, position: Int) {

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        binding.rcvComics.layoutManager = if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            GridLayoutManager(activity, 3, RecyclerView.VERTICAL, false)
        } else {
            GridLayoutManager(activity, 5, RecyclerView.VERTICAL, false)
        }
        binding.rcvComics.adapter = HomeAdapter(iterator.value.comics, this, activity)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        rcvState = binding.rcvComics.layoutManager?.onSaveInstanceState()
        outState.putParcelable(RCV_STATE_KEY, rcvState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null)
            rcvState = savedInstanceState.getParcelable(RCV_STATE_KEY)
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

