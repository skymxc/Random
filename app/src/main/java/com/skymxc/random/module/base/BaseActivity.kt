package com.skymxc.random.module.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.skymxc.random.R
import java.lang.Exception

/**
 * <p>
 *
 * </p>
 *
 * @author 孟祥超
 * <p>
 * date: 2021/2/2
 */
open class BaseActivity() : AppCompatActivity() {

    lateinit var waitDialog:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        waitDialog = ProgressDialog(this)
        waitDialog.setMessage(getString(R.string.loading))
    }
    fun showError(exception: Exception) = showMsg(exception.message!!)
    fun showMsg(msg: String) {
        closeProgress()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.prompt))
            .setMessage(msg)
            .setNegativeButton(getString(R.string.ok), null)
            .show()
    }

    fun showProgress(){
        waitDialog.show()
    }
    fun closeProgress(){
        waitDialog.dismiss()
    }
}