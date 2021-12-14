package com.ai.customcamera.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var mBinding: DB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWindow()
        initViewBinding()
        initView(savedInstanceState)
        observe()
    }

    private fun initViewBinding() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mBinding.lifecycleOwner = this
    }

    open fun initWindow() {}

    open fun getLayoutId(): Int = 0

    open fun initView(savedInstanceState: Bundle?) {}
    open fun observe(){}

    override fun onDestroy() {
        super.onDestroy()
        if(::mBinding.isInitialized){
            mBinding.unbind()
        }
    }
}