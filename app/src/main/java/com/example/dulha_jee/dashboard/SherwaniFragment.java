package com.example.dulha_jee.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.dulha_jee.MainActivity;
import com.example.dulha_jee.R;
import com.example.dulha_jee.SharedPreference;
import com.tapadoo.alerter.Alerter;
import com.tapadoo.alerter.OnHideAlertListener;

public class SherwaniFragment extends Fragment {
    Spinner dropdown_karegar_name, dropdown_shalwar_name;
    String[] users = {"کرتا شلوار", "کرتا پاجامہ", "قمیص شلوار", "فرنٹ اوپن کرتا"};
    String[] shalwar = {"شلوار", "اسٹریٹ پاجامہ", "چوڑی ڈار پاجامہ", "پینٹ اسٹائل پاجامہ", "دھوتی شلوار", "بڑے گھیر والی شلوار"};
    String[] karegarName = {" کاریگر کا نام", "ابرار ", "احمد ", "امین ", "عارف "};
    ImageView chooseSidePocketImage;
    NavController navController;
    SharedPreference sharedPreference;
    public static boolean isComingFromSherwani , isComingFromShewaniBack;
    Button submit_sherwani;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sherwani, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbar("Sherwani");
        ((MainActivity)getActivity()).setToolbarVisibility(true);
        sharedPreference = new SharedPreference(getActivity());

        dropdown_karegar_name = view.findViewById(R.id.dropdown_karegar_name);
        navController = Navigation.findNavController(view);
        chooseSidePocketImage = view.findViewById(R.id.chooseSidePocketImage);
        submit_sherwani = view.findViewById(R.id.submit_sherwani);

        submit_sherwani.setOnClickListener(new View.OnClickListener() {
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
                                navController.navigate(R.id.action_sherwaniFragment_to_dashBoard,null, new NavOptions.Builder()
                                        .setPopUpTo(R.id.sherwaniFragment,
                                                true).build());
                            }
                        })
                        .show();
            }
        });

        chooseSidePocketImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_sherwaniFragment_to_fragmentSidePocketSelection);
                isComingFromSherwani = true;
            }
        });

        //this function set image of sherwani collar
        if(isComingFromShewaniBack){
            if (getArguments().getString("imageID").equals("1")) {
                chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_01));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }

            if (getArguments().getString("imageID").equals("2")) {
                chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_02));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }

            if (getArguments().getString("imageID").equals("3")) {
                chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_03));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("4")) {
                chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_04));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("5")) {
                chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_05));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("6")) {
                chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_06));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("7")) {
                chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_07));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("8")) {
                chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_08));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("9")) {
                chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_09));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("10")) {
                chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_10));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("11")) {
                chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_11));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("12")) {
                chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_12));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            isComingFromShewaniBack = false;
        }

        setSidePocketImagefromCache();


        if(getArguments().getString("new") != null && getArguments().getString("new").equals("N")){
            chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_choose));
            sharedPreference.remove();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, karegarName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_karegar_name.setAdapter(adapter);
    }

    private void setSidePocketImagefromCache() {
        if (sharedPreference.getSidePocketCollarImageNumber() == 1) {
            chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_01));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 2) {
            chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_02));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 3) {
            chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_03));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 4) {
            chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_04));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 5) {
            chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_05));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 6) {
            chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_06));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 7) {
            chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_07));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 8) {
            chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_08));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 9) {
            chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_09));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 10) {
            chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_10));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 11) {
            chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_11));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 12) {
            chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_12));
        }

    }
}
