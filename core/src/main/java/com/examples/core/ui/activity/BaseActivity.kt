package com.examples.core.ui.activity

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.examples.core.R
import com.examples.core.utils.LanguageUtils
import com.examples.core.utils.LoadingListener
import com.examples.core.utils.LocaleUtils
import com.examples.core.utils.VersionUtils
import kotlinx.android.synthetic.main.activity_base.*

/**
 *  Created by Shehab Elsarky
 */
abstract class BaseActivity : AppCompatActivity(), LoadingListener, ToolbarListener {
    private val TAG = BaseActivity::class.java.simpleName

    private lateinit var navFragment: NavHostFragment
    lateinit var navController: NavController

    abstract var navGraphResourceId: Int
    protected lateinit var bundle: Bundle

    lateinit var loadingDialog: Dialog

    @LayoutRes
    open var layoutRes = R.layout.activity_base

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        initLoadingAlertDialog()
        setNavFragment()
    }

    private fun setNavFragment() {
        navFragment =
            supportFragmentManager.findFragmentById(R.id.common_nav_fragment) as NavHostFragment
        navController = navFragment.navController
        if (::bundle.isInitialized) navController.setGraph(navGraphResourceId, bundle)
        else navController.setGraph(navGraphResourceId)
    }


    override fun showActivityToolbar() {
        toolbarBase?.visibility = View.VISIBLE
    }

    override fun hideActivityToolbar() {
        toolbarBase?.visibility = View.GONE
    }


    override fun setActivityToolbarTitle(title: String, gravity: Int?) {
        tv_title?.text = title
        gravity?.let {
            tv_title?.gravity = it
        }
    }


    fun setFragmentDestination(@IdRes resId: Int, bundle: Bundle?) =
        navController.navigate(resId, bundle)

    fun popupFragment() = navController.popBackStack()

    override fun showLoading(show: Boolean) {
        if (show && loadingDialog != null)
            loadingDialog.show() else loadingDialog.dismiss()
    }

    private fun initLoadingAlertDialog() {
        loadingDialog = Dialog(this, R.style.CustomLoadingDialogStyle)
        loadingDialog.setContentView(R.layout.dialog_loading)
        loadingDialog.setCancelable(false)
    }

}