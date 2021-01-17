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

import com.example.dulha_jee.MainActivity;
import com.example.dulha_jee.R;

public class DashBoard extends Fragment implements View.OnClickListener {

    CardView card_kurta, card_shirwani, card_coat, card_waistCoat, card_pant, card_shirt, card_innersuit;
    NavController navController;
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((MainActivity) getActivity()).setToolbar("DashBoard");
        ((MainActivity)getActivity()).setToolbarVisibility(true);
        bundle = new Bundle();
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
        card_innersuit = view.findViewById(R.id.card_innersuit);

        card_kurta.setOnClickListener(this);
        card_shirwani.setOnClickListener(this);
        card_waistCoat.setOnClickListener(this);
        card_coat.setOnClickListener(this);
        card_pant.setOnClickListener(this);
        card_shirt.setOnClickListener(this);
        card_innersuit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_kurta:
                bundle.putString("new", "N");
                navController.navigate(R.id.action_dashBoard_to_kurtaFragment,bundle);
                break;
            case R.id.card_sherwani:
                bundle.putString("new", "N");
                navController.navigate(R.id.action_dashBoard_to_sherwaniFragment,bundle);
                break;
            case R.id.card_waistcoat:
                bundle.putString("new", "N");
                navController.navigate(R.id.action_dashBoard_to_waistCoatFragment,bundle);
                break;
            case R.id.card_coat:
                bundle.putString("new", "N");
                navController.navigate(R.id.action_dashBoard_to_fragmentCoat,bundle);
                break;
            case R.id.card_pant:
                bundle.putString("new", "N");
                navController.navigate(R.id.action_dashBoard_to_fragmentPants,bundle);
                break;
            case R.id.card_shirt:
                bundle.putString("new", "N");
                navController.navigate(R.id.action_dashBoard_to_fragmentShirt,bundle);
                break;
            case R.id.card_innersuit:
                bundle.putString("new", "N");
                navController.navigate(R.id.action_dashBoard_to_fragmentInnerSuit,bundle);
                break;
            default:
                //do nothing
        }
    }
}
