package com.example.dulha_jee.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentShirt extends Fragment {
    Spinner dropdown_karegar_name;
    Button submit_shirt;
    ImageView chooseCollarImage, chooseCuffImage;
    NavController navController;
    SharedPreference sharedPreference;
    public static boolean isComingFromShirt, isComingFromShirtBack, isComingFromShirtBack2;

    String[] users = {"کرتا شلوار", "کرتا پاجامہ", "قمیص شلوار", "فرنٹ اوپن کرتا"};
    String[] karegarName = {" کاریگر کا نام", "ابرار ", "احمد ", "امین ", "عارف "};

    //fields to bind view
    @BindView(R.id.quantity)
    EditText quantity;
    @BindView(R.id.collar)
    EditText collar;
    @BindView(R.id.sleeves)
    EditText sleeves;
    @BindView(R.id.shoulder)
    EditText shoulder;
    @BindView(R.id.hip)
    EditText hip;
    @BindView(R.id.abdomen)
    EditText abdomen;
    @BindView(R.id.gudda)
    EditText gudda;
    @BindView(R.id.front)
    EditText front;
    @BindView(R.id.lengthMade)
    EditText lengthMade;
    @BindView(R.id.patti_ki_chorayi)
    EditText patti_ki_chorayi;
    @BindView(R.id.order_date)
    EditText order_date;
    @BindView(R.id.order_date_most_urgent)
    EditText order_date_most_urgent;
    @BindView(R.id.remarks)
    EditText remarks;

    //CheckBoxes
    @BindView(R.id.is_shirt)
    CheckBox is_shirt;
    @BindView(R.id.make_coverpati_style)
    CheckBox make_coverpati_style;
    @BindView(R.id.regular_polo_pati)
    CheckBox regular_polo_pati;
    @BindView(R.id.simple_pati_style)
    CheckBox simple_pati_style;
    @BindView(R.id.not_regular_polo_pat)
    CheckBox not_regular_polo_pat;
    @BindView(R.id.back_dart)
    CheckBox back_dart;
    @BindView(R.id.american_style_round_deep)
    CheckBox american_style_round_deep;
    @BindView(R.id.readymade_shirt_style_chakooti)
    CheckBox readymade_shirt_style_chakooti;
    @BindView(R.id.customer_cloth)
    CheckBox customer_cloth;
    @BindView(R.id.only_sewing)
    CheckBox only_sewing;
    @BindView(R.id.child_kurta_size)
    CheckBox child_kurta_size;
    @BindView(R.id.finished_adjust)
    CheckBox finished_adjust;
    @BindView(R.id.special_customer_order)
    CheckBox special_customer_order;
    @BindView(R.id.regular_customer_order)
    CheckBox regular_customer_order;
    @BindView(R.id.urgent_order)
    CheckBox urgent_order;
    @BindView(R.id.no_label)
    CheckBox no_label;
    @BindView(R.id.special_order)
    CheckBox special_order;
    @BindView(R.id.button_should_be_strong)
    CheckBox button_should_be_strong;
    @BindView(R.id.shoulder_down)
    CheckBox shoulder_down;
    @BindView(R.id.light_work_shoulder_down)
    CheckBox light_work_shoulder_down;
    @BindView(R.id.full_shoulder_down)
    CheckBox full_shoulder_down;
    @BindView(R.id.straight_shoulder)
    CheckBox straight_shoulder;
    @BindView(R.id.right_shoulder_down)
    CheckBox right_shoulder_down;
    @BindView(R.id.left_shoulder_down)
    CheckBox left_shoulder_down;
    @BindView(R.id.deep_body)
    CheckBox deep_body;
    @BindView(R.id.altered_body)
    CheckBox altered_body;
    @BindView(R.id.party_label)
    CheckBox party_label;
    @BindView(R.id.fancy_label)
    CheckBox fancy_label;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shirt, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbar("Shirt");

        ButterKnife.bind(this,view);
        dropdown_karegar_name = view.findViewById(R.id.dropdown_karegar_name);
        chooseCollarImage = view.findViewById(R.id.chooseCollarImage);
        chooseCuffImage = view.findViewById(R.id.chooseCuffImage);
        navController = Navigation.findNavController(view);
        sharedPreference = new SharedPreference(getActivity());
        submit_shirt = view.findViewById(R.id.submit_shirt);

        submit_shirt.setOnClickListener(new View.OnClickListener() {
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
                                navController.navigate(R.id.action_fragmentShirt_to_dashBoard, null, new NavOptions.Builder()
                                        .setPopUpTo(R.id.fragmentShirt,
                                                true).build());
                            }
                        })
                        .show();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, karegarName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_karegar_name.setAdapter(adapter);

        chooseCollarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_fragmentShirt_to_fragmentCollarSelection, null, new NavOptions.Builder()
                        .setPopUpTo(R.id.fragmentShirt,
                                true).build());
                isComingFromShirt = true;
            }
        });

        chooseCuffImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_fragmentShirt_to_fragmentCuffSelection, null, new NavOptions.Builder()
                        .setPopUpTo(R.id.fragmentShirt,
                                true).build());
                isComingFromShirt = true;
            }
        });

        //this is set image for collar shirt
        if (isComingFromShirtBack) {
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

            isComingFromShirtBack = false;
        }

        //this will set image for shirt cuff
        if (isComingFromShirtBack2) {
            if (getArguments().getString("imageID").equals("1")) {
                chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_1_button_angle));
                sharedPreference.setCuffImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("2")) {
                chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_1_button_rounded));
                sharedPreference.setCuffImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("3")) {
                chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_1_button_square));
                sharedPreference.setCuffImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("4")) {
                chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_2_button_angle));
                sharedPreference.setCuffImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("5")) {
                chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_2_button_round));
                sharedPreference.setCuffImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("6")) {
                chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_2_button_square));
                sharedPreference.setCuffImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("7")) {
                chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_3_button_angle));
                sharedPreference.setCuffImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("8")) {
                chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_3_button_rounded));
                sharedPreference.setCuffImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("9")) {
                chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_3_button_square));
                sharedPreference.setCuffImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("10")) {
                chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_french_angle));
                sharedPreference.setCuffImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("10")) {
                chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_french_round));
                sharedPreference.setCuffImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            if (getArguments().getString("imageID").equals("10")) {
                chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_french_square));
                sharedPreference.setCuffImageNumber(Integer.parseInt(getArguments().getString("imageID")));
            }
            isComingFromShirtBack2 = false;
        }

        if (getArguments().getString("new") != null && getArguments().getString("new").equals("N")) {
            chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_choose));
            chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_choose));
            sharedPreference.remove();
        }

        setCollarImagefromCache();
        setCuffImagefromCache();


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

    private void setCuffImagefromCache() {
        if (sharedPreference.getCuffImageNumber() == 1) {
            chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_1_button_angle));
        }
        if (sharedPreference.getCuffImageNumber() == 2) {
            chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_1_button_rounded));
        }
        if (sharedPreference.getCuffImageNumber() == 3) {
            chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_1_button_square));
        }
        if (sharedPreference.getCuffImageNumber() == 4) {
            chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_2_button_angle));
        }
        if (sharedPreference.getCuffImageNumber() == 5) {
            chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_2_button_round));
        }
        if (sharedPreference.getCuffImageNumber() == 6) {
            chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_2_button_square));
        }
        if (sharedPreference.getCuffImageNumber() == 7) {
            chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_3_button_angle));
        }
        if (sharedPreference.getCuffImageNumber() == 8) {
            chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_3_button_rounded));
        }
        if (sharedPreference.getCuffImageNumber() == 9) {
            chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_3_button_square));
        }
        if (sharedPreference.getCuffImageNumber() == 10) {
            chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_french_angle));
        }
        if (sharedPreference.getCuffImageNumber() == 11) {
            chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_french_round));
        }
        if (sharedPreference.getCuffImageNumber() == 12) {
            chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_french_square));
        }
    }

}
