package com.example.dulha_jee.dashboard;

import android.Manifest;
import android.app.Dialog;
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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.dulha_jee.MainActivity;
import com.example.dulha_jee.R;
import com.example.dulha_jee.SharedPreference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.tapadoo.alerter.Alerter;
import com.tapadoo.alerter.OnHideAlertListener;

import butterknife.*;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class SherwaniFragment extends Fragment {
    Spinner dropdown_karegar_name, dropdown_shalwar_name;
    String[] users = {"کرتا شلوار", "کرتا پاجامہ", "قمیص شلوار", "فرنٹ اوپن کرتا"};
    String[] shalwar = {"شلوار", "اسٹریٹ پاجامہ", "چوڑی ڈار پاجامہ", "پینٹ اسٹائل پاجامہ", "دھوتی شلوار", "بڑے گھیر والی شلوار"};
    String[] karegarName = {" کاریگر کا نام", "ابرار ", "احمد ", "امین ", "عارف "};
    CardView LL1, LL2, LL3, LL4, LL5, LL6, LL7, LL8, LL9, LL10, LL11, LL12;
    ImageView chooseSidePocketImage , iv_01;
    NavController navController;
    SharedPreference sharedPreference;
    public static boolean isComingFromSherwani, isComingFromShewaniBack;
    Button submit_sherwani , chooseImage;
    Uri imageUri;
    public static final int PICK_IMAGE = 1;

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
    @BindView(R.id.fullback)
    EditText fullback;
    @BindView(R.id.halfback)
    EditText halfback;
    @BindView(R.id.crossfront)
    EditText crossfront;
    @BindView(R.id.highlight_cadge_color)
    EditText highlight_cadge_color;
    @BindView(R.id.show_cadge_color)
    EditText show_cadge_color;
    @BindView(R.id.front_cadge_numbers)
    EditText front_cadge_numbers;
    @BindView(R.id.front_show_cadge_number)
    EditText front_show_cadge_number;
    @BindView(R.id.contrast_color_astar)
    EditText contrast_color_astar;
    @BindView(R.id.is_urgent)
    EditText is_urgent;
    @BindView(R.id.order_date)
    EditText order_date;
    @BindView(R.id.is_most_urgent)
    EditText is_most_urgent;
    @BindView(R.id.order_date_most_urgent)
    EditText order_date_most_urgent;
    @BindView(R.id.remarks)
    EditText remarks;


    //Checkboxes....

    @BindView(R.id.jinnah_style)
    CheckBox jinnah_style;
    @BindView(R.id.highlight_cadge_matching)
    CheckBox highlight_cadge_matching;
    @BindView(R.id.fancy_metal_button)
    CheckBox fancy_metal_button;
    @BindView(R.id.show_button)
    CheckBox show_button;
    @BindView(R.id.show_cadge_matching)
    CheckBox show_cadge_matching;
    @BindView(R.id.side_pocket_both_sides)
    CheckBox side_pocket_both_sides;
    @BindView(R.id.two_pockets)
    CheckBox two_pockets;
    @BindView(R.id.no_tal_pat)
    CheckBox no_tal_pat;
    @BindView(R.id.both_front_equal)
    CheckBox both_front_equal;
    @BindView(R.id.back_center_sew)
    CheckBox back_center_sew;
    @BindView(R.id.front_walnut_pocket)
    CheckBox front_walnut_pocket;
    @BindView(R.id.open_front_open_gown_style)
    CheckBox open_front_open_gown_style;
    @BindView(R.id.collar_karhayi)
    CheckBox collar_karhayi;
    @BindView(R.id.sleeves_karhayi)
    CheckBox sleeves_karhayi;
    @BindView(R.id.button_karhayi)
    CheckBox button_karhayi;
    @BindView(R.id.one_front_karhayi)
    CheckBox one_front_karhayi;
    @BindView(R.id.both_front_karhayi)
    CheckBox both_front_karhayi;
    @BindView(R.id.back_karhayi)
    CheckBox back_karhayi;
    @BindView(R.id.collar_sleeves_karhayi)
    CheckBox collar_sleeves_karhayi;
    @BindView(R.id.violet_pocket_karhayi)
    CheckBox violet_pocket_karhayi;
    @BindView(R.id.collar_sleeves_one_front_karhayi)
    CheckBox collar_sleeves_one_front_karhayi;
    @BindView(R.id.front_pocket_karhayi)
    CheckBox front_pocket_karhayi;
    @BindView(R.id.collar_sleeves_two_front_karhayi)
    CheckBox collar_sleeves_two_front_karhayi;
    @BindView(R.id.front_pocket_on_karhayi)
    CheckBox front_pocket_on_karhayi;
    @BindView(R.id.anger_khakhat_style_sherwani)
    CheckBox anger_khakhat_style_sherwani;
    @BindView(R.id.cross_style_sherwani)
    CheckBox cross_style_sherwani;
    @BindView(R.id.matching_color_astar)
    CheckBox matching_color_astar;
    @BindView(R.id.customer_cloth)
    CheckBox customer_cloth;
    @BindView(R.id.only_sewing)
    CheckBox only_sewing;
    @BindView(R.id.child_shewrwani_size)
    CheckBox child_shewrwani_size;
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
        View view = inflater.inflate(R.layout.fragment_sherwani, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbar("Sherwani");
        ((MainActivity) getActivity()).setToolbarVisibility(true);
        sharedPreference = new SharedPreference(getActivity());
        ButterKnife.bind(this, view);
        iv_01 = view.findViewById(R.id.iv_01);

        dropdown_karegar_name = view.findViewById(R.id.dropdown_karegar_name);
        navController = Navigation.findNavController(view);
        chooseSidePocketImage = view.findViewById(R.id.chooseSidePocketImage);
        submit_sherwani = view.findViewById(R.id.submit_sherwani);
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

        submit_sherwani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alerter.create(getActivity())
                        .setTitle("انتطار فرمائیے۔۔۔")
                        .setText("کسٹمر کا آرڈر بن رہا ہے۔۔۔")
                        .setIcon(R.drawable.dulha_jee_logo)
                        .setBackgroundColorRes(
                                R.color.black)
                        .setDuration(3000)
                        .setOnHideListener(new OnHideAlertListener() {
                            @Override
                            public void onHide() {
                                navController.navigate(R.id.action_sherwaniFragment_to_dashBoard, null, new NavOptions.Builder()
                                        .setPopUpTo(R.id.sherwaniFragment,
                                                true).build());
                            }
                        })
                        .show();
                String text = jinnah_style.isChecked() ? jinnah_style.getText().toString() : "no checked";
                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            }
        });

        chooseSidePocketImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
                // dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                dialog.setContentView(R.layout.dialog_side_pocket);
                initViews(view, dialog);

                LL1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_01));
                    }
                });
                LL2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_02));
                    }
                });
                LL3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_03));
                    }
                });
                LL4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_04));
                    }
                });
                LL5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_05));
                    }
                });
                LL6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_06));
                    }
                });
                LL7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_07));
                    }
                });
                LL8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_08));
                    }
                });
                LL9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_09));
                    }
                });
                LL10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_10));
                    }
                });
                LL11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_11));
                    }
                });
                LL12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_12));
                    }
                });

                dialog.show();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, karegarName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_karegar_name.setAdapter(adapter);

    }


    public void initViews(View view, Dialog dialog) {
        LL1 = dialog.findViewById(R.id.LL1);
        LL2 = dialog.findViewById(R.id.LL2);
        LL3 = dialog.findViewById(R.id.LL3);
        LL4 = dialog.findViewById(R.id.LL4);
        LL5 = dialog.findViewById(R.id.LL5);
        LL6 = dialog.findViewById(R.id.LL6);
        LL7 = dialog.findViewById(R.id.LL7);
        LL8 = dialog.findViewById(R.id.LL8);
        LL9 = dialog.findViewById(R.id.LL9);
        LL10 = dialog.findViewById(R.id.LL10);
        LL11 = dialog.findViewById(R.id.LL11);
        LL12 = dialog.findViewById(R.id.LL12);
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
