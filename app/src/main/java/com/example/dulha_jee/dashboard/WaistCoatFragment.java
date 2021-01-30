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

public class WaistCoatFragment extends Fragment {
    Spinner dropdown_karegar_name;
    String[] users = {"کرتا شلوار", "کرتا پاجامہ", "قمیص شلوار", "فرنٹ اوپن کرتا"};
    String[] karegarName = {" کاریگر کا نام", "ابرار ", "احمد ", "امین ", "عارف "};
    Button submit_waistcoat , chooseImage;
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
    @BindView(R.id.front)
    EditText front;
    @BindView(R.id.lengthMade)
    EditText lengthMade;
    @BindView(R.id.order_date)
    EditText order_date;
    @BindView(R.id.order_date_most_urgent)
    EditText order_date_most_urgent;
    @BindView(R.id.remarks)
    EditText remarks;
    @BindView(R.id.three_piece_style_cadge)
    EditText three_piece_style_cadge;
    @BindView(R.id.collar_width)
    EditText collar_width;
    @BindView(R.id.violet_pocket_width)
    EditText violet_pocket_width;
    @BindView(R.id.extra_buttons)
    EditText extra_buttons;
    @BindView(R.id.lozing)
    EditText lozing;
    @BindView(R.id.waistcoat_chowk_length)
    EditText waistcoat_chowk_length;

    //checkBoxes

    @BindView(R.id.jawahir_cut_style)
    CheckBox jawahir_cut_style;
    @BindView(R.id.v_neck_style)
    CheckBox v_neck_style;
    @BindView(R.id.u_neck_style)
    CheckBox u_neck_style;
    @BindView(R.id.waistcoat_back_backet)
    CheckBox waistcoat_back_backet;
    @BindView(R.id.metal_fancy_buttons)
    CheckBox metal_fancy_buttons;
    @BindView(R.id.matching_plastic_buttons)
    CheckBox matching_plastic_buttons;
    @BindView(R.id.straight_daman)
    CheckBox straight_daman;
    @BindView(R.id.round_daman)
    CheckBox round_daman;
    @BindView(R.id.coat_style_round_daman)
    CheckBox coat_style_round_daman;
    @BindView(R.id.collar_hala)
    CheckBox collar_hala;
    @BindView(R.id.collar_pointed)
    CheckBox collar_pointed;
    @BindView(R.id.double_bon_pocket)
    CheckBox double_bon_pocket;
    @BindView(R.id.single_bon_pocket)
    CheckBox single_bon_pocket;
    @BindView(R.id.violet_pocket)
    CheckBox violet_pocket;
    @BindView(R.id.patch_pocket)
    CheckBox patch_pocket;
    @BindView(R.id.cadge_button_pati)
    CheckBox cadge_button_pati;
    @BindView(R.id.no_upper_pocket)
    CheckBox no_upper_pocket;
    @BindView(R.id.no_lower_pocket)
    CheckBox no_lower_pocket;
    @BindView(R.id.waistcoat_style_like_image)
    CheckBox waistcoat_style_like_image;
    @BindView(R.id.two_pockets_inside)
    CheckBox two_pockets_inside;
    @BindView(R.id.pocket_like_fabric)
    CheckBox pocket_like_fabric;
    @BindView(R.id.full_fewsing)
    CheckBox full_fewsing;
    @BindView(R.id.front_fewsing)
    CheckBox front_fewsing;
    @BindView(R.id.astar_cherry)
    CheckBox astar_cherry;
    @BindView(R.id.customer_stiff_waistcoat)
    CheckBox customer_stiff_waistcoat;
    @BindView(R.id.collar_karhayi)
    CheckBox collar_karhayi;
    @BindView(R.id.front_pocket_karhayi)
    CheckBox front_pocket_karhayi;
    @BindView(R.id.both_front_karhayi)
    CheckBox both_front_karhayi;
    @BindView(R.id.one_front_karhayi)
    CheckBox one_front_karhayi;
    @BindView(R.id.back_karhayi)
    CheckBox back_karhayi;
    @BindView(R.id.collar_buttons)
    CheckBox collar_buttons;
    @BindView(R.id.collar_lower_buttons)
    CheckBox collar_lower_buttons;
    @BindView(R.id.front_pipine)
    CheckBox front_pipine;
    @BindView(R.id.pocket_pipine)
    CheckBox pocket_pipine;
    @BindView(R.id.cadge_contrast)
    CheckBox cadge_contrast;
    @BindView(R.id.excersize_body)
    CheckBox excersize_body;
    @BindView(R.id.child_size_waistcoat)
    CheckBox child_size_waistcoat;
    @BindView(R.id.magzi_round_neck_waistcoat)
    CheckBox magzi_round_neck_waistcoat;
    @BindView(R.id.body_builder)
    CheckBox body_builder;
    @BindView(R.id.collar_soft_bukram)
    CheckBox collar_soft_bukram;
    @BindView(R.id.shape_body)
    CheckBox shape_body;
    @BindView(R.id.fabric_customer)
    CheckBox fabric_customer;
    @BindView(R.id.finishing)
    CheckBox finishing;
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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_waistcoat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbar("WaistCoat");
        ((MainActivity) getActivity()).setToolbarVisibility(true);
        ButterKnife.bind(this, view);
        navController = Navigation.findNavController(view);
        submit_waistcoat = view.findViewById(R.id.submit_waistcoat);
        iv_01 = view.findViewById(R.id.iv_01);

        dropdown_karegar_name = view.findViewById(R.id.dropdown_karegar_name);
        chooseImage = view.findViewById(R.id.chooseImage);

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


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, karegarName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_karegar_name.setAdapter(adapter);

        submit_waistcoat.setOnClickListener(new View.OnClickListener() {
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
                                navController.navigate(R.id.action_waistCoatFragment_to_dashBoard, null, new NavOptions.Builder()
                                        .setPopUpTo(R.id.waistCoatFragment,
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
