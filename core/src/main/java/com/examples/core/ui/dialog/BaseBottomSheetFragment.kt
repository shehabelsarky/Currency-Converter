package com.examples.core.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.examples.core.R
import com.examples.core.ui.fragment.BaseUiHelper
import com.examples.core.ui.fragment.Inflate
import com.examples.core.ui.view_model.BaseViewModel
import com.examples.core.domain.entity.base.ErrorModel
import com.examples.core.domain.entity.base.ErrorStatus
import com.examples.core.utils.LoadingListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
abstract class BaseBottomSheetFragment<VB : ViewBinding, VM : BaseViewModel, HelperClass : BaseUiHelper?>
    (private val inflate: Inflate<VB>) :
    BottomSheetDialogFragment() {

    private val TAG = BaseBottomSheetFragment::class.java.simpleName

    @Inject
    lateinit var baseNetworkingDialog: BaseNetworkingDialog

    private var _binding: VB? = null
    val binding get() = _binding!!

    private var mLoader: LoadingListener? = null

    abstract val viewModel: VM

    open var isRoundedCorners = true

    abstract val fragmentHelper: HelperClass


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            window?.attributes?.windowAnimations = R.style.dialog_animation
            setOnShowListener { setupBottomSheet(it) }
        }
    }

    open fun setupBottomSheet(dialogInterface: DialogInterface) {
        val bottomSheetDialog = dialogInterface as BottomSheetDialog
        val bottomSheet = bottomSheetDialog.findViewById<View>(
            com.google.android.material.R.id.design_bottom_sheet
        ) ?: return

        if (isRoundedCorners)
            bottomSheet.setBackgroundResource(R.drawable.bg_rounded_sheet_white)
    }

    override fun onStop() {
        super.onStop()
        with(viewModel) {
          //  errorLiveData.removeObservers(viewLifecycleOwner)
           // cancellationMsgLiveData.removeObservers(viewLifecycleOwner)
            showLoading(false)
        }
    }


    open fun showLoading(show: Boolean) {
        mLoader?.showLoading(show)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            if (it is LoadingListener) mLoader = it
        }
    }

    override fun onDetach() {
        super.onDetach()
        mLoader = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

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

            ErrorStatus.EMPTY_RESPONSE -> Toast.makeText(
                context,
                "${it?.message}",
                Toast.LENGTH_SHORT
            ).show()
            else -> Toast.makeText(context, "${it?.message}", Toast.LENGTH_SHORT).show()
        }


    }

}
