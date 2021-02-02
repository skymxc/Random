package com.skymxc.random.module.add

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.skymxc.random.MainActivityViewModel
import com.skymxc.random.R
import com.skymxc.random.databinding.ActivityAddBinding
import com.skymxc.random.entity.AsyncResult
import com.skymxc.random.module.base.BaseActivity
import com.skymxc.random.observer.SimpleAsyncResultObserver

/**
 * <p>
 *
 * </p>
 *
 * @author 孟祥超
 * <p>
 * date: 2021/2/2
 */
class AddActivity(): BaseActivity() {

    private val viewModel:AddActivityViewModel by lazy {
        ViewModelProvider(viewModelStore,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            AddActivityViewModel::class.java)
    }

    lateinit var binding:ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add)
        binding.model = viewModel
        subscribe()
    }

    private fun subscribe(){
        viewModel.saveLiveData.observe(this,
        object :SimpleAsyncResultObserver<Boolean>(this){
            override fun onSuccess(value: Boolean) {
                if (value){
                    closeProgress()
                }else{
                    showMsg(getString(R.string.save_fail))
                }
            }

        })
    }
}