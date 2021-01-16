package com.example.dulha_jee.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.dulha_jee.MainActivity;
import com.example.dulha_jee.R;
import com.tapadoo.alerter.Alerter;
import com.tapadoo.alerter.OnHideAlertListener;

public class FragmentCoat extends Fragment {
    Spinner dropdown_karegar_name, dropdown_coat_varieties;
    String[] karegarName = {"اندراج کرنے والے کاریگر کا نام", "ابرار ", "احمد ", "امین ", "عارف "};
    String[] coatVarieties = { "گون اسٹائل فرنٹ اوپن کوٹ ", "پرنس کوٹ ", "کوٹ "};
    Button submit_coat;
    NavController navController;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbar("Coat...");
        navController = Navigation.findNavController(view);

        dropdown_karegar_name = view.findViewById(R.id.dropdown_karegar_name);
        dropdown_coat_varieties = view.findViewById(R.id.dropdown_coat_varieties);
        submit_coat = view.findViewById(R.id.submit_coat);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, karegarName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_karegar_name.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, coatVarieties);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_coat_varieties.setAdapter(adapter1);

        submit_coat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alerter.create(getActivity())
                        .setTitle("انتطار فرمائیے۔۔۔")
                        .setText("کسٹمر کا آرڈر بن رہا ہے۔۔۔")
                        .setIcon(
                                R.drawable.dulha_jee_logo)
                        .setBackgroundColorRes(
                                R.color.black)
                        .setDuration(3000)
                        .setOnHideListener(new OnHideAlertListener() {
                            @Override
                            public void onHide() {
                                navController.navigate(R.id.action_fragmentCoat_to_dashBoard,null, new NavOptions.Builder()
                                        .setPopUpTo(R.id.fragmentCoat,
                                                true).build());
                            }
                        })
                        .show();
            }
        });
    }
}
