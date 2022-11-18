package com.deepakbarad.weatherapp.framework.base

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {
    open val loadingFlag = ObservableField<Boolean>()
    open val loadingMessage = ObservableField<String>()

    protected open val mErrorInfo: MutableLiveData<String> = MutableLiveData()
    open val errorInfo: LiveData<String> get() = mErrorInfo

}