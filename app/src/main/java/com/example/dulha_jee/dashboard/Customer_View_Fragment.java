package com.example.dulha_jee.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dulha_jee.R;

public class Customer_View_Fragment extends Fragment {

    WebView mWebView;
    Spinner dropdown_status;
    String[] status = {"آرڈر آگیا", "کام جاری ہے", "آرڈر بن گیا", "کسٹمر کو پہنچ گیا", "پینٹ اسٹائل پاجامہ", "دھوتی شلوار", "بڑے گھیر والی شلوار"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.customer_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWebView = view.findViewById(R.id.mWebView);


        dropdown_status = view.findViewById(R.id.dropdown_status);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, status);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_status.setAdapter(adapter1);


        if(getArguments() != null){
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            mWebView.loadUrl(getArguments().getString("url"));
        }else{
            Toast.makeText(getActivity(), "NO url found", Toast.LENGTH_SHORT).show();
        }



    }

}
