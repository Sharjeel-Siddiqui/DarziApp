package com.example.dulha_jee.dashboard;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.dulha_jee.MainActivity;
import com.example.dulha_jee.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.tapadoo.alerter.Alerter;
import com.tapadoo.alerter.OnHideAlertListener;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class FragmentCoat extends Fragment {
    Spinner dropdown_karegar_name, dropdown_coat_varieties;
    String[] karegarName = {" کاریگر کا نام", "ابرار ", "احمد ", "امین ", "عارف "};
    String[] coatVarieties = {"گون اسٹائل فرنٹ اوپن کوٹ ", "پرنس کوٹ ", "کوٹ "};
    Button submit_coat  , chooseImage;
    NavController navController;
    ImageView iv_01;
    Uri imageUri;
    public static final int PICK_IMAGE = 1;

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
    @BindView(R.id.chest)
    EditText chest;
    @BindView(R.id.lengthMade)
    EditText lengthMade;
    @BindView(R.id.full_back)
    EditText full_back;
    @BindView(R.id.halfback)
    EditText halfback;
    @BindView(R.id.crossfront)
    EditText crossfront;
    @BindView(R.id.chowk_length)
    EditText chowk_length;
    @BindView(R.id.pencil_length)
    EditText pencil_length;
    @BindView(R.id.collar_thing)
    EditText collar_thing;
    @BindView(R.id.pencil_thing)
    EditText pencil_thing;
    @BindView(R.id.shoulder_depth)
    EditText shoulder_depth;
    @BindView(R.id.front_cadge)
    EditText front_cadge;
    @BindView(R.id.order_date)
    EditText order_date;
    @BindView(R.id.order_date_most_urgent)
    EditText order_date_most_urgent;
    @BindView(R.id.remarks)
    EditText remarks;


    //checkboxes
    @BindView(R.id.button_style2)
    CheckBox button_style2;
    @BindView(R.id.single_button)
    CheckBox single_button;
    @BindView(R.id.three_button)
    CheckBox three_button;
    @BindView(R.id.four_button)
    CheckBox four_button;
    @BindView(R.id.five_button)
    CheckBox five_button;
    @BindView(R.id.no_button)
    CheckBox no_button;
    @BindView(R.id.side_chowk)
    CheckBox side_chowk;
    @BindView(R.id.back_chowk)
    CheckBox back_chowk;
    @BindView(R.id.american_pencil)
    CheckBox american_pencil;
    @BindView(R.id.dinner_pencil)
    CheckBox dinner_pencil;
    @BindView(R.id.pencil_long)
    CheckBox pencil_long;
    @BindView(R.id.taxido_style)
    CheckBox taxido_style;
    @BindView(R.id.double_style_collar)
    CheckBox double_style_collar;
    @BindView(R.id.qub)
    CheckBox qub;
    @BindView(R.id.no_meat)
    CheckBox no_meat;
    @BindView(R.id.waist_coat_double)
    CheckBox waist_coat_double;
    @BindView(R.id.pencil_waist_coat)
    CheckBox pencil_waist_coat;
    @BindView(R.id.v_neck_waist_coat)
    CheckBox v_neck_waist_coat;
    @BindView(R.id.u_neck_waist_coat)
    CheckBox u_neck_waist_coat;
    @BindView(R.id.same_as_image)
    CheckBox same_as_image;
    @BindView(R.id.coat_same_as_image)
    CheckBox coat_same_as_image;
    @BindView(R.id.waist_coat_same_as_image)
    CheckBox waist_coat_same_as_image;
    @BindView(R.id.inner_loozing)
    CheckBox inner_loozing;
    @BindView(R.id.special_vip)
    CheckBox special_vip;
    @BindView(R.id.sleeves_loozing)
    CheckBox sleeves_loozing;
    @BindView(R.id.double_bukram)
    CheckBox double_bukram;
    @BindView(R.id.full_bukram)
    CheckBox full_bukram;
    @BindView(R.id.astar_printed)
    CheckBox astar_printed;
    @BindView(R.id.prince_coat)
    CheckBox prince_coat;
    @BindView(R.id.fancy_buttons)
    CheckBox fancy_buttons;
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
    @BindView(R.id.altered_body)
    CheckBox altered_body;
    @BindView(R.id.deep_body)
    CheckBox deep_body;
    @BindView(R.id.party_label)
    CheckBox party_label;
    @BindView(R.id.fancy_label)
    CheckBox fancy_label;
    @BindView(R.id.customer_name)
    EditText customer_name;
    @BindView(R.id.customer_number)
    EditText customer_number;
    @BindView(R.id.order_number)
    EditText order_number;
    @BindView(R.id.order_date_date)
    EditText order_date_date;


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
        chooseImage = view.findViewById(R.id.chooseImage);
        iv_01 = view.findViewById(R.id.iv_01);

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withContext(getActivity())
                        .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                openGallery();
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                if (response.isPermanentlyDenied()) {
                                    Toast.makeText(getActivity(), "Permission required to take picture from gallery...", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                            }
                        }).check();
            }
        });

        ButterKnife.bind(this,view);
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
                                navController.navigate(R.id.action_fragmentCoat_to_dashBoard, null, new NavOptions.Builder()
                                        .setPopUpTo(R.id.fragmentCoat,
                                                true).build());
                            }
                        })
                        .show();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            iv_01.setImageURI(imageUri);
        }
    }
}
