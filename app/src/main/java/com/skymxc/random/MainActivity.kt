package com.skymxc.random

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.skymxc.random.adapter.RandomItemAdapter
import com.skymxc.random.databinding.ActivityMainBinding
import com.skymxc.random.entity.RandomItem
import com.skymxc.random.module.add.AddActivity
import com.skymxc.random.module.base.BaseActivity
import com.skymxc.random.observer.SimpleAsyncResultObserver

class MainActivity : BaseActivity() {

    private val model:MainActivityViewModel by lazy {
        ViewModelProvider(viewModelStore,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(MainActivityViewModel::class.java)
    }
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.activity =this
        binding.model =model
        binding.recycler.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        subscribe()
    }

    private fun subscribe(){
        model.listLiveData.observe(this,
            { t ->
                binding.btnRandom.isEnabled = t.isNotEmpty()
                val adapter = RandomItemAdapter(t,this@MainActivity){
                    model.delete(it)
                }
                binding.recycler.adapter =adapter
            })
        model.addLiveData.observe(this,
            object : SimpleAsyncResultObserver<Boolean>(this){
                override fun onSuccess(value: Boolean) {
                    if (value){
                        showMsg(getString(R.string.add_success))
                    }else{
                        showMsg(getString(R.string.save_fail))
                    }
                }
            })
        model.delLiveData.observe(this,
        object :SimpleAsyncResultObserver<Boolean>(this){
            override fun onSuccess(value: Boolean) {
                if (value){
                    showMsg(getString(R.string.delete_success))
                }else{
                    showMsg(getString(R.string.delete_fail))
                }
            }

        })
    }
}