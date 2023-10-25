package com.example.goodcounter;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> counter =new MutableLiveData<>();

    public void increaseCounter(View view){

        int currentValue=counter.getValue()==null?0:counter.getValue();
        counter.setValue(currentValue+1);

    }

    public MutableLiveData<Integer> getValue(){

        return counter;
    }
}
