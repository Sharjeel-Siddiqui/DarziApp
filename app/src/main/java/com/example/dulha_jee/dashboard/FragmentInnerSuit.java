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

public class FragmentInnerSuit extends Fragment {

    Spinner dropdown_karegar_name, dropdown_shalwar_name;
    NavController navController;
    String[] shalwar = {"شلوار", "اسٹریٹ پاجامہ", "چوڑی ڈار پاجامہ", "پینٹ اسٹائل پاجامہ", "دھوتی شلوار", "بڑے گھیر والی شلوار"};
    String[] karegarName = {" کاریگر کا نام", "ابرار ", "احمد ", "امین ", "عارف "};
    Button submit_innersuit;
    ImageView iv_01, chooseImage;
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
    @BindView(R.id.shalwar_gher)
    EditText shalwar_gher;
    @BindView(R.id.shalwar_asan)
    EditText shalwar_asan;
    @BindView(R.id.pajama_inner_fold)
    EditText pajama_inner_fold;
    @BindView(R.id.pajama_outer_fold)
    EditText pajama_outer_fold;
    @BindView(R.id.order_date)
    EditText order_date;
    @BindView(R.id.order_date_most_urgent)
    EditText order_date_most_urgent;

    //CheckBoxes come here
    @BindView(R.id.off_white_color)
    CheckBox off_white_colorl;
    @BindView(R.id.black_color)
    CheckBox black_color;
    @BindView(R.id.mehroon_color)
    CheckBox mehroon_color;
    @BindView(R.id.redish_maroon)
    CheckBox redish_maroon;
    @BindView(R.id.golden_color)
    CheckBox golden_color;
    @BindView(R.id.cream_color)
    CheckBox cream_color;
    @BindView(R.id.copper_color)
    CheckBox copper_color;
    @BindView(R.id.dark_brown_color)
    CheckBox dark_brown_color;
    @BindView(R.id.gray_color)
    CheckBox gray_color;
    @BindView(R.id.matching_button)
    CheckBox matching_button;
    @BindView(R.id.brass_button)
    CheckBox brass_button;
    @BindView(R.id.copper_color_button)
    CheckBox copper_color_button;
    @BindView(R.id.silver_color_button)
    CheckBox silver_color_button;
    @BindView(R.id.gold_color_button)
    CheckBox gold_color_button;
    @BindView(R.id.purple_color_button)
    CheckBox purple_color_button;
    @BindView(R.id.round_button)
    CheckBox round_button;
    @BindView(R.id.fancy_button)
    CheckBox fancy_button;
    @BindView(R.id.strong_button)
    CheckBox strong_button;
    @BindView(R.id.contrass_button)
    CheckBox contrass_button;
    @BindView(R.id.contrass_cadge)
    CheckBox contrass_cadge;
    @BindView(R.id.naifa_chirya)
    CheckBox naifa_chirya;
    @BindView(R.id.matching_zip)
    CheckBox matching_zip;
    @BindView(R.id.quality_zip)
    CheckBox quality_zip;
    @BindView(R.id.qameez_zip)
    CheckBox qameez_zip;
    @BindView(R.id.shalwar_romali)
    CheckBox shalwar_romali;
    @BindView(R.id.paincha_style)
    CheckBox paincha_style;
    @BindView(R.id.paint_style)
    CheckBox paint_style;
    @BindView(R.id.side_pocket)
    CheckBox side_pocket;
    @BindView(R.id.half_lastic)
    CheckBox half_lastic;
    @BindView(R.id.full_lastic)
    CheckBox full_lastic;
    @BindView(R.id.half_lastic_with_kamarband)
    CheckBox half_lastic_with_kamarband;
    @BindView(R.id.pajama_as_pic)
    CheckBox pajama_as_pic;
    @BindView(R.id.pajama_roomali)
    CheckBox pajama_roomali;
    @BindView(R.id.chooridar_pajama)
    CheckBox chooridar_pajama;
    @BindView(R.id.arha_pajama)
    CheckBox arha_pajama;
    @BindView(R.id.straight_pajama)
    CheckBox straight_pajama;
    @BindView(R.id.customer_fat)
    CheckBox customer_fat;
    @BindView(R.id.pajama_fit)
    CheckBox pajama_fit;
    @BindView(R.id.customer_slim)
    CheckBox customer_slim;
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
        return inflater.inflate(R.layout.fragment_innersuit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        ((MainActivity) getActivity()).setToolbar("InnerSuit...");
        navController = Navigation.findNavController(view);
        chooseImage = view.findViewById(R.id.chooseImage);
        iv_01 = view.findViewById(R.id.iv_01);

        dropdown_karegar_name = view.findViewById(R.id.dropdown_karegar_name);
        dropdown_shalwar_name = view.findViewById(R.id.dropdown_shalwar_name);

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

        submit_innersuit = view.findViewById(R.id.submit_innersuit);
        submit_innersuit.setOnClickListener(new View.OnClickListener() {
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
                                navController.navigate(R.id.action_fragmentInnerSuit_to_dashBoard, null, new NavOptions.Builder()
                                        .setPopUpTo(R.id.fragmentInnerSuit,
                                                true).build());
                            }
                        })
                        .show();
            }
        });

        //karegar names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, karegarName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_karegar_name.setAdapter(adapter);

        //drop down names Shalwar...
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, shalwar);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_shalwar_name.setAdapter(adapter1);
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
