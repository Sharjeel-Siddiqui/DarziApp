package com.example.dulha_jee.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.dulha_jee.R;

public class DashBoard extends Fragment implements View.OnClickListener {

    CardView card_kurta, card_shirwani, card_coat, card_waistCoat, card_pant, card_shirt;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view) {
        navController = Navigation.findNavController(view);
        card_kurta = view.findViewById(R.id.card_kurta);
        card_shirwani = view.findViewById(R.id.card_sherwani);
        card_waistCoat = view.findViewById(R.id.card_waistcoat);
        card_coat = view.findViewById(R.id.card_coat);
        card_pant = view.findViewById(R.id.card_pant);
        card_shirt = view.findViewById(R.id.card_shirt);

        card_kurta.setOnClickListener(this);
        card_shirwani.setOnClickListener(this);
        card_waistCoat.setOnClickListener(this);
        card_coat.setOnClickListener(this);
        card_pant.setOnClickListener(this);
        card_shirt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_kurta:
                navController.navigate(R.id.action_dashBoard_to_kurtaFragment);
                break;
            case R.id.card_sherwani:
                navController.navigate(R.id.action_dashBoard_to_sherwaniFragment);
                break;
            case R.id.card_waistcoat:
                navController.navigate(R.id.action_dashBoard_to_waistCoatFragment);
                break;
            case R.id.card_coat:
                break;
            case R.id.card_pant:
                navController.navigate(R.id.action_dashBoard_to_fragmentPants);
                break;
            case R.id.card_shirt:
                navController.navigate(R.id.action_dashBoard_to_fragmentShirt);
                break;
            default:
                //do nothing
        }
    }
}
