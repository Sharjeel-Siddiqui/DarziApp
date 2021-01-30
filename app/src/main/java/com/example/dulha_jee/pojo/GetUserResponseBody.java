package com.example.dulha_jee.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetUserResponseBody {

    private List<String> data;

    public void setData(List<String> data){
        this.data = data;
    }
    public List<String> getData(){
        return this.data;
    }

}

