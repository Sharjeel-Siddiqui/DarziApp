package com.example.dulha_jee.dashboard;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.dulha_jee.R;

public class FragmentCollarSelection extends Fragment {
    com.wang.avi.AVLoadingIndicatorView avLoadingIndicatorView;
    NavController navController;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collar_selection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        avLoadingIndicatorView = view.findViewById(R.id.avi);

       /* navController = Navigation.findNavController(view);

        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
                avLoadingIndicatorView.setVisibility(View.VISIBLE);
            }

            public void onFinish() {
                avLoadingIndicatorView.setVisibility(View.GONE);
                KurtaFragment.isComingback = true;
                Bundle bundle = new Bundle();
                bundle.putString("imageId", "1");
                navController.navigate(R.id.action_fragmentCollarSelection_to_kurtaFragment);
            }

        }.start();*/
    }
}
