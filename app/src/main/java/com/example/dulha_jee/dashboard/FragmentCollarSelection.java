package com.example.dulha_jee.dashboard;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.dulha_jee.R;

public class FragmentCollarSelection extends Fragment implements View.OnClickListener {
    com.wang.avi.AVLoadingIndicatorView avLoadingIndicatorView;
    NavController navController;
    CardView LL1, LL2, LL3, LL4, LL5, LL6, LL7, LL8, LL9, LL10;
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collar_selection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bundle = new Bundle();
        avLoadingIndicatorView = view.findViewById(R.id.avi);
        initViews(view);
    }

    public void initViews(View view) {
        navController = Navigation.findNavController(view);
        LL1 = view.findViewById(R.id.LL1);
        LL2 = view.findViewById(R.id.LL2);
        LL3 = view.findViewById(R.id.LL3);
        LL4 = view.findViewById(R.id.LL4);
        LL5 = view.findViewById(R.id.LL5);
        LL6 = view.findViewById(R.id.LL6);
        LL7 = view.findViewById(R.id.LL7);
        LL8 = view.findViewById(R.id.LL8);
        LL9 = view.findViewById(R.id.LL9);
        LL10 = view.findViewById(R.id.LL10);

        LL1.setOnClickListener(this);
        LL2.setOnClickListener(this);
        LL3.setOnClickListener(this);
        LL4.setOnClickListener(this);
        LL5.setOnClickListener(this);
        LL6.setOnClickListener(this);
        LL7.setOnClickListener(this);
        LL8.setOnClickListener(this);
        LL9.setOnClickListener(this);
        LL10.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.LL1:
                if (FragmentShirt.isComingFromShirt) {
                    bundle.putString("imageID", "1");
                    navController.navigate(R.id.action_fragmentCollarSelection_to_fragmentShirt, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                    FragmentShirt.isComingFromShirt = false;
                    FragmentShirt.isComingFromShirtBack = true;
                } else {
                    bundle.putString("imageID", "1");
                    KurtaFragment.isComingbackfromCollar_Kurta = true;
                    // navController.navigate(R.id.action_fragmentCollarSelection_to_kurtaFragment, bundle);
                    navController.navigate(R.id.action_fragmentCollarSelection_to_kurtaFragment, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                }
                break;
            case R.id.LL2:
                if (FragmentShirt.isComingFromShirt) {
                    bundle.putString("imageID", "2");
                    navController.navigate(R.id.action_fragmentCollarSelection_to_fragmentShirt, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                    FragmentShirt.isComingFromShirt = false;
                    FragmentShirt.isComingFromShirtBack = true;
                } else {
                    bundle.putString("imageID", "2");
                    KurtaFragment.isComingbackfromCollar_Kurta = true;
                    navController.navigate(R.id.action_fragmentCollarSelection_to_kurtaFragment, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                }
                break;
            case R.id.LL3:
                if (FragmentShirt.isComingFromShirt) {
                    bundle.putString("imageID", "3");
                    navController.navigate(R.id.action_fragmentCollarSelection_to_fragmentShirt, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                    FragmentShirt.isComingFromShirt = false;
                    FragmentShirt.isComingFromShirtBack = true;
                } else {
                    bundle.putString("imageID", "3");
                    KurtaFragment.isComingbackfromCollar_Kurta = true;
                    navController.navigate(R.id.action_fragmentCollarSelection_to_kurtaFragment, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                }
                break;
            case R.id.LL4:
                if (FragmentShirt.isComingFromShirt) {
                    bundle.putString("imageID", "4");
                    navController.navigate(R.id.action_fragmentCollarSelection_to_fragmentShirt, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                    FragmentShirt.isComingFromShirt = false;
                    FragmentShirt.isComingFromShirtBack = true;
                } else {
                    bundle.putString("imageID", "4");
                    KurtaFragment.isComingbackfromCollar_Kurta = true;
                    navController.navigate(R.id.action_fragmentCollarSelection_to_kurtaFragment, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                }
                break;
            case R.id.LL5:
                if (FragmentShirt.isComingFromShirt) {
                    bundle.putString("imageID", "5");
                    navController.navigate(R.id.action_fragmentCollarSelection_to_fragmentShirt, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                    FragmentShirt.isComingFromShirt = false;
                    FragmentShirt.isComingFromShirtBack = true;
                } else {
                    bundle.putString("imageID", "5");
                    KurtaFragment.isComingbackfromCollar_Kurta = true;
                    navController.navigate(R.id.action_fragmentCollarSelection_to_kurtaFragment, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                }
                break;
            case R.id.LL6:
                if (FragmentShirt.isComingFromShirt) {
                    bundle.putString("imageID", "6");
                    navController.navigate(R.id.action_fragmentCollarSelection_to_fragmentShirt, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                    FragmentShirt.isComingFromShirt = false;
                    FragmentShirt.isComingFromShirtBack = true;
                } else {
                    bundle.putString("imageID", "6");
                    KurtaFragment.isComingbackfromCollar_Kurta = true;
                    navController.navigate(R.id.action_fragmentCollarSelection_to_kurtaFragment, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                }
                break;
            case R.id.LL7:
                if (FragmentShirt.isComingFromShirt) {
                    bundle.putString("imageID", "7");
                    navController.navigate(R.id.action_fragmentCollarSelection_to_fragmentShirt, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                    FragmentShirt.isComingFromShirt = false;
                    FragmentShirt.isComingFromShirtBack = true;
                } else {
                    bundle.putString("imageID", "7");
                    KurtaFragment.isComingbackfromCollar_Kurta = true;
                    navController.navigate(R.id.action_fragmentCollarSelection_to_kurtaFragment, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                }
                break;
            case R.id.LL8:
                if (FragmentShirt.isComingFromShirt) {
                    bundle.putString("imageID", "8");
                    navController.navigate(R.id.action_fragmentCollarSelection_to_fragmentShirt, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                    FragmentShirt.isComingFromShirt = false;
                    FragmentShirt.isComingFromShirtBack = true;
                } else {
                    bundle.putString("imageID", "8");
                    KurtaFragment.isComingbackfromCollar_Kurta = true;
                    navController.navigate(R.id.action_fragmentCollarSelection_to_kurtaFragment, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                }
                break;
            case R.id.LL9:
                if (FragmentShirt.isComingFromShirt) {
                    bundle.putString("imageID", "9");
                    navController.navigate(R.id.action_fragmentCollarSelection_to_fragmentShirt, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                    FragmentShirt.isComingFromShirt = false;
                    FragmentShirt.isComingFromShirtBack = true;
                } else {
                    bundle.putString("imageID", "9");
                    KurtaFragment.isComingbackfromCollar_Kurta = true;
                    navController.navigate(R.id.action_fragmentCollarSelection_to_kurtaFragment, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                }
                break;
            case R.id.LL10:
                if (FragmentShirt.isComingFromShirt) {
                    bundle.putString("imageID", "10");
                    navController.navigate(R.id.action_fragmentCollarSelection_to_fragmentShirt, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                    FragmentShirt.isComingFromShirt = false;
                    FragmentShirt.isComingFromShirtBack = true;
                } else {
                    bundle.putString("imageID", "10");
                    KurtaFragment.isComingbackfromCollar_Kurta = true;
                    navController.navigate(R.id.action_fragmentCollarSelection_to_kurtaFragment, bundle, new NavOptions.Builder()
                            .setPopUpTo(R.id.fragmentCollarSelection,
                                    true).build());
                }
                break;
        }
    }
}
