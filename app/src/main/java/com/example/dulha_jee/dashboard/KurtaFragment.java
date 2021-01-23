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
import com.tapadoo.alerter.OnShowAlertListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KurtaFragment extends Fragment {
    static boolean isComingbackfromCollar_Kurta, isComingbackfromSidePocket;
    Spinner dropdown_kurta_varieties, dropdown_karegar_name, dropdown_shalwar_name;
    String[] users = {"کرتا شلوار", "کرتا پاجامہ", "قمیص شلوار", "فرنٹ اوپن کرتا"};
    String[] karegarName = {" کاریگر کا نام", "ابرار ", "احمد ", "امین ", "عارف "};
    String[] shalwar = {"شلوار کی اقسا م", "شلوار", "اسٹریٹ پاجامہ", "چوڑی ڈار پاجامہ", "پینٹ اسٹائل پاجامہ", "دھوتی شلوار", "بڑے گھیر والی شلوار"};
    NavController navController;
    ImageView chooseCollarImage, chooseSidePocket;
    Button submit_kurta;
    SharedPreference sharedPreference;

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
    @BindView(R.id.collar_width)
    EditText collar_width;
    @BindView(R.id.collar_point_length)
    EditText collar_point_length;
    @BindView(R.id.front_pati_length)
    EditText front_pati_length;
    @BindView(R.id.front_pati_cadge)
    EditText front_pati_cadge;
    @BindView(R.id.cover_pati_length)
    EditText cover_pati_length;
    @BindView(R.id.cuff_width)
    EditText cuff_width;
    @BindView(R.id.cuff_chowk_width)
    EditText cuff_chowk_width;
    @BindView(R.id.cuff_length)
    EditText cuff_length;
    @BindView(R.id.chowk_length)
    EditText chowk_length;
    @BindView(R.id.machine_sew_bail)
    EditText machine_sew_bail;
    @BindView(R.id.pocket_width)
    EditText pocket_width;
    @BindView(R.id.pocket_length)
    EditText pocket_length;
    @BindView(R.id.order_date)
    EditText order_date;
    @BindView(R.id.order_date_most_urgent)
    EditText order_date_most_urgent;
    @BindView(R.id.remarks)
    EditText remarks;

    //checkboxes
    @BindView(R.id.sherwani_collar)
    CheckBox sherwani_collar;
    @BindView(R.id.collar_front_round)
    CheckBox collar_front_round;
    @BindView(R.id.collar_pointed)
    CheckBox collar_pointed;
    @BindView(R.id.cut_collar)
    CheckBox cut_collar;
    @BindView(R.id.collar_on_collar)
    CheckBox collar_on_collar;
    @BindView(R.id.v_collar)
    CheckBox v_collar;
    @BindView(R.id.shirt_collar)
    CheckBox shirt_collar;
    @BindView(R.id.reverse_style_french_collar)
    CheckBox reverse_style_french_collar;
    @BindView(R.id.taxido_style_collar)
    CheckBox taxido_style_collar;
    @BindView(R.id.collar_style_small)
    CheckBox collar_style_small;
    @BindView(R.id.button_on_point)
    CheckBox button_on_point;
    @BindView(R.id.button_on_cadge_small)
    CheckBox button_on_cadge_small;
    @BindView(R.id.soft_bukram_on_collar)
    CheckBox soft_bukram_on_collar;
    @BindView(R.id.customer_collar_soft)
    CheckBox customer_collar_soft;
    @BindView(R.id.hard_bukram_on_collar)
    CheckBox hard_bukram_on_collar;
    @BindView(R.id.more_bukram_on_collar)
    CheckBox more_bukram_on_collar;
    @BindView(R.id.hidden_on_collar)
    CheckBox hidden_on_collar;
    @BindView(R.id.design_as_on_picture_collar)
    CheckBox design_as_on_picture_collar;
    @BindView(R.id.round_neck_magzi)
    CheckBox round_neck_magzi;
    @BindView(R.id.round_neck_magzi_with_finishing)
    CheckBox round_neck_magzi_with_finishing;
    @BindView(R.id.regular_pati)
    CheckBox regular_pati;
    @BindView(R.id.front_pati_width)
    CheckBox front_pati_width;
    @BindView(R.id.pointed_pati)
    CheckBox pointed_pati;
    @BindView(R.id.straight_pati)
    CheckBox straight_pati;
    @BindView(R.id.boat_sew_on_pati)
    CheckBox boat_sew_on_pati;
    @BindView(R.id.cover_pati)
    CheckBox cover_pati;
    @BindView(R.id.front_pati_style_as_image)
    CheckBox front_pati_style_as_image;
    @BindView(R.id.cut_sleeves)
    CheckBox cut_sleeves;
    @BindView(R.id.cut_cuff)
    CheckBox cut_cuff;
    @BindView(R.id.round_cuff)
    CheckBox round_cuff;
    @BindView(R.id.straight_cuff_pointed)
    CheckBox straight_cuff_pointed;
    @BindView(R.id.cuff_link_style_cuff)
    CheckBox cuff_link_style_cuff;
    @BindView(R.id.double_cuff)
    CheckBox double_cuff;
    @BindView(R.id.hard_bukram_in_cuff)
    CheckBox hard_bukram_in_cuff;
    @BindView(R.id.soft_bukram_in_cuff)
    CheckBox soft_bukram_in_cuff;
    @BindView(R.id.cuff_chowk_cadge)
    CheckBox cuff_chowk_cadge;
    @BindView(R.id.cuff_chowk_bukram)
    CheckBox cuff_chowk_bukram;
    @BindView(R.id.cuff_chowk_pointed)
    CheckBox cuff_chowk_pointed;
    @BindView(R.id.cuff_chowk_straight)
    CheckBox cuff_chowk_straight;
    @BindView(R.id.cuff_chowk_round)
    CheckBox cuff_chowk_round;
    @BindView(R.id.pointer_style_picture)
    CheckBox pointer_style_picture;
    @BindView(R.id.cuff_machine_karhayi)
    CheckBox cuff_machine_karhayi;
    @BindView(R.id.cuff_hand_karhayi)
    CheckBox cuff_hand_karhayi;
    @BindView(R.id.straight_kurta_daman)
    CheckBox straight_kurta_daman;
    @BindView(R.id.round_daman)
    CheckBox round_daman;
    @BindView(R.id.kamiz_style_daman)
    CheckBox kamiz_style_daman;
    @BindView(R.id.piyala_style_daman)
    CheckBox piyala_style_daman;
    @BindView(R.id.straight_daman_pointed_corner)
    CheckBox straight_daman_pointed_corner;
    @BindView(R.id.daman_double_sew)
    CheckBox daman_double_sew;
    @BindView(R.id.daman_finishing)
    CheckBox daman_finishing;
    @BindView(R.id.daman_as_image)
    CheckBox daman_as_image;
    @BindView(R.id.chakoti_american)
    CheckBox chakoti_american;
    @BindView(R.id.chakoti)
    CheckBox chakoti;
    @BindView(R.id.chakoti_contrast)
    CheckBox chakoti_contrast;
    @BindView(R.id.teere_piece)
    CheckBox teere_piece;
    @BindView(R.id.not_teere_piece)
    CheckBox not_teere_piece;
    @BindView(R.id.double_sew_full)
    CheckBox double_sew_full;
    @BindView(R.id.lab_sew)
    CheckBox lab_sew;
    @BindView(R.id.paki_lab_sew)
    CheckBox paki_lab_sew;
    @BindView(R.id.chanpa)
    CheckBox chanpa;
    @BindView(R.id.front_bunch)
    CheckBox front_bunch;
    @BindView(R.id.collar_machine_sew)
    CheckBox collar_machine_sew;
    @BindView(R.id.sleeves_machine_sew)
    CheckBox sleeves_machine_sew;
    @BindView(R.id.shoulder_machine_sew)
    CheckBox shoulder_machine_sew;
    @BindView(R.id.collar_pati_hand_sew)
    CheckBox collar_pati_hand_sew;
    @BindView(R.id.collar_hand_sew)
    CheckBox collar_hand_sew;
    @BindView(R.id.pati_hand_sew)
    CheckBox pati_hand_sew;
    @BindView(R.id.front_hand_sew)
    CheckBox front_hand_sew;
    @BindView(R.id.sleeve_hand_sew)
    CheckBox sleeve_hand_sew;
    @BindView(R.id.shoulder_hand_sew)
    CheckBox shoulder_hand_sew;
    @BindView(R.id.one_front_pocket_style)
    CheckBox one_front_pocket_style;
    @BindView(R.id.two_front_pocket)
    CheckBox two_front_pocket;
    @BindView(R.id.two_side_pocket)
    CheckBox two_side_pocket;
    @BindView(R.id.right_side_pocket)
    CheckBox right_side_pocket;
    @BindView(R.id.left_side_pocket)
    CheckBox left_side_pocket;
    @BindView(R.id.side_pocket_deep)
    CheckBox side_pocket_deep;
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
        return inflater.inflate(R.layout.fragment_kurta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        ((MainActivity) getActivity()).setToolbar("Kurta...");
        ((MainActivity) getActivity()).setToolbarVisibility(true);
        ButterKnife.bind(view);
        sharedPreference = new SharedPreference(getActivity());
        submit_kurta = view.findViewById(R.id.submit_kurta);
        dropdown_kurta_varieties = view.findViewById(R.id.dropdown_kurta_varieties);
        dropdown_karegar_name = view.findViewById(R.id.dropdown_karegar_name);
        dropdown_shalwar_name = view.findViewById(R.id.dropdown_shalwar_name);
        chooseCollarImage = view.findViewById(R.id.chooseCollarImage);
        chooseSidePocket = view.findViewById(R.id.chooseSidePocket);

        submit_kurta.setOnClickListener(new View.OnClickListener() {
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
                                navController.navigate(R.id.action_kurtaFragment_to_dashBoard, null, new NavOptions.Builder()
                                        .setPopUpTo(R.id.kurtaFragment,
                                                true).build());
                            }
                        })
                        .show();
            }
        });


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

        if (isComingbackfromCollar_Kurta) {
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

            isComingbackfromCollar_Kurta = false;
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
        if (getArguments().getString("new") != null && getArguments().getString("new").equals("N")) {
            chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_choose));
            chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.ic_choose));
            sharedPreference.remove();
        }
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
