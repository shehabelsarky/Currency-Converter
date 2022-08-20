package com.examples.core.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.examples.core.ui.activity.ToolbarListener
import com.examples.core.ui.view_model.BaseViewModel
import com.examples.core.domain.entity.base.ErrorModel
import com.examples.core.domain.entity.base.ErrorStatus
import com.examples.core.utils.LoadingListener
import kotlinx.coroutines.ExperimentalCoroutinesApi

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

@ExperimentalCoroutinesApi
abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel, HelperClass : BaseUiHelper?>(
    private val inflate: Inflate<VB>
) : NetworkBaseFragment() {

    //added line to view binding
    private var _binding: VB? = null
    val binding get() = _binding!!

    private val TAG = BaseFragment::class.java.simpleName

    private var mLoader: LoadingListener? = null

    abstract val viewModel: VM

    abstract val fragmentHelper: HelperClass

    protected var toolbarListener: ToolbarListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleState()
    }


    open fun onViewModelError() {}


    fun setActivityToolbarTitle(title: String, gravity: Int? = null) {
        toolbarListener?.setActivityToolbarTitle(title, gravity)
    }

    fun showActivityToolbar(){
        toolbarListener?.showActivityToolbar()
    }

    fun hideActivityToolbar() {
        toolbarListener?.hideActivityToolbar()
    }


    override fun onDestroyView() {
        showLoading(false)
        super.onDestroyView()
        _binding = null
    }


    open fun showLoading(show: Boolean) {
        if (mLoader != null)
            mLoader?.showLoading(show)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            if (it is LoadingListener) mLoader = it

            if (it is ToolbarListener) toolbarListener = it

        }
    }

    override fun onDetach() {
        super.onDetach()
        mLoader = null
        toolbarListener = null
    }

    open fun handleState() {
        with(viewModel) {
            errorLiveData.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context, "${it?.message}", Toast.LENGTH_LONG).show()
            })

            cancellationMsgLiveData.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            })

            isLoadingLiveData.observe(viewLifecycleOwner, Observer {
                showLoading(it)
                Log.d(TAG,"Loading observer is called")
            })
        }
    }

    private fun handleErrorStatus(it: ErrorModel) {
        when (it.errorStatus) {
            ErrorStatus.NO_DATA -> {
            }
            ErrorStatus.NO_CONNECTION -> {
                baseNetworkingDialog.showDialog(
                    requireContext(),
                    it.errorStatus
                )
            }
            ErrorStatus.UNAUTHORIZED -> {}
            ErrorStatus.INTERNAL_SERVER_ERROR -> baseNetworkingDialog.showDialog(
                requireContext(),
                it.errorStatus
            )
            ErrorStatus.UNKNOWN_HOST -> baseNetworkingDialog.showDialog(
                requireContext(),
                it.errorStatus
            )
            ErrorStatus.FORRBIDEN, ErrorStatus.NOT_FOUND -> Toast.makeText(
                context,
                "Something went wrong",
                Toast.LENGTH_SHORT
            ).show()

            ErrorStatus.EMPTY_RESPONSE -> baseNetworkingDialog.showDialog(
                requireContext(),
                it.errorStatus,
                it.message
            )
            else -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
        }
        onViewModelError()
    }


}