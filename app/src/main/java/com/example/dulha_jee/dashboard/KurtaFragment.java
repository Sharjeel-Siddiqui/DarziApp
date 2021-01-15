package com.example.dulha_jee.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.dulha_jee.MainActivity;
import com.example.dulha_jee.R;
import com.example.dulha_jee.SharedPreference;

public class KurtaFragment extends Fragment {
    static boolean isComingbackfromCollar, isComingbackfromSidePocket;
    Spinner dropdown_kurta_varieties, dropdown_karegar_name, dropdown_shalwar_name;
    String[] users = {"کرتا شلوار", "کرتا پاجامہ", "قمیص شلوار", "فرنٹ اوپن کرتا"};
    String[] karegarName = {"اندراج کرنے والے کاریگر کا نام", "ابرار ", "احمد ", "امین ", "عارف "};
    String[] shalwar = {"شلوار کی اقسا م", "شلوار", "اسٹریٹ پاجامہ", "چوڑی ڈار پاجامہ", "پینٹ اسٹائل پاجامہ", "دھوتی شلوار", "بڑے گھیر والی شلوار"};
    NavController navController;
    ImageView chooseCollarImage, chooseSidePocket;

    SharedPreference sharedPreference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kurta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        ((MainActivity) getActivity()).setToolbar("Kurta...");
        sharedPreference = new SharedPreference(getActivity());

        dropdown_kurta_varieties = view.findViewById(R.id.dropdown_kurta_varieties);
        dropdown_karegar_name = view.findViewById(R.id.dropdown_karegar_name);
        dropdown_shalwar_name = view.findViewById(R.id.dropdown_shalwar_name);
        chooseCollarImage = view.findViewById(R.id.chooseCollarImage);
        chooseSidePocket = view.findViewById(R.id.chooseSidePocket);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_kurta_varieties.setAdapter(adapter);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, karegarName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_karegar_name.setAdapter(adapter3);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, shalwar);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_shalwar_name.setAdapter(adapter1);

        chooseCollarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_kurtaFragment_to_fragmentCollarSelection);
            }
        });

        chooseSidePocket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_kurtaFragment_to_fragmentSidePocketSelection);
            }
        });


        if (isComingbackfromCollar) {
            if (getArguments().getString("imageID").equals("1")) {
                chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_buttondown));
                sharedPreference.setCollarImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("2")) {
                chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_classic));
                sharedPreference.setCollarImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("3")) {
                chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_club));
                sharedPreference.setCollarImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("4")) {
                chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_cutaway));
                sharedPreference.setCollarImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("5")) {
                chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_mandarin));
                sharedPreference.setCollarImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("6")) {
                chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_medium));
                sharedPreference.setCollarImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("7")) {
                chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_pinned));
                sharedPreference.setCollarImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("8")) {
                chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_spread));
                sharedPreference.setCollarImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("9")) {
                chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_tab));
                sharedPreference.setCollarImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("10")) {
                chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_tuxedo));
                sharedPreference.setCollarImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }

            isComingbackfromCollar = false;
        }


        if (isComingbackfromSidePocket) {
            if (getArguments().getString("imageID").equals("1")) {
                chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_01));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }

            if (getArguments().getString("imageID").equals("2")) {
                chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_02));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }

            if (getArguments().getString("imageID").equals("3")) {
                chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_03));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("4")) {
                chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_04));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("5")) {
                chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_05));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("6")) {
                chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_06));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("7")) {
                chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_07));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("8")) {
                chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_08));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("9")) {
                chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_09));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("10")) {
                chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_10));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("11")) {
                chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_11));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("12")) {
                chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_12));
                sharedPreference.setSidePocketImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            isComingbackfromSidePocket = false;
        }


        setCollarImagefromCache();

        setSidePocketImagefromCache();


    }

    private void setCollarImagefromCache() {
        if (sharedPreference.getCollarImageNumber() == 1) {
            chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_buttondown));
        }

        if (sharedPreference.getCollarImageNumber() == 2) {
            chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_classic));
        }
        if (sharedPreference.getCollarImageNumber() == 3) {
            chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_club));
        }
        if (sharedPreference.getCollarImageNumber() == 4) {
            chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_cutaway));
        }
        if (sharedPreference.getCollarImageNumber() == 5) {
            chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_mandarin));
        }
        if (sharedPreference.getCollarImageNumber() == 6) {
            chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_medium));
        }
        if (sharedPreference.getCollarImageNumber() == 7) {
            chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_pinned));
        }
        if (sharedPreference.getCollarImageNumber() == 8) {
            chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_spread));
        }
        if (sharedPreference.getCollarImageNumber() == 9) {
            chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_tab));
        }
        if (sharedPreference.getCollarImageNumber() == 10) {
            chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_tuxedo));
        }

    }

    private void setSidePocketImagefromCache() {
        if (sharedPreference.getSidePocketCollarImageNumber() == 1) {
            chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_01));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 2) {
            chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_02));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 3) {
            chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_03));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 4) {
            chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_04));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 5) {
            chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_05));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 6) {
            chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_06));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 7) {
            chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_07));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 8) {
            chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_08));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 9) {
            chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_09));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 10) {
            chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_10));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 11) {
            chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_11));
        }
        if (sharedPreference.getSidePocketCollarImageNumber() == 12) {
            chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_12));
        }

    }
}
