package com.examples.core.ui.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.examples.core.ui.view_model.BaseViewModel
import com.examples.core.domain.entity.base.ErrorModel
import com.examples.core.domain.entity.base.ErrorStatus
import com.examples.core.utils.LoadingListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
abstract class BaseDialogFragment<VM : BaseViewModel> : DialogFragment() {

    private val TAG = BaseBottomSheetFragment::class.java.simpleName

    abstract var layoutResourceId: Int
    private var mLoader: LoadingListener? = null

    abstract val viewModel: VM

    open var isRoundedCorners = true

    @Inject
    lateinit var baseNetworkingDialog: BaseNetworkingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return inflater.inflate(layoutResourceId, container, false)
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

            ErrorStatus.EMPTY_RESPONSE ->baseNetworkingDialog.showDialog(
                requireContext(),
                it.errorStatus,
                it.message
            )
            else -> Toast.makeText(context, "${it?.message}", Toast.LENGTH_SHORT).show()
        }


    }



    override fun onStop() {
        super.onStop()
        with(viewModel) {
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
}
