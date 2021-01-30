package com.example.dulha_jee.dashboard;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import com.tapadoo.alerter.OnShowAlertListener;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class KurtaFragment extends Fragment {
    static boolean isComingbackfromCollar_Kurta, isComingbackfromSidePocket;
    Spinner dropdown_kurta_varieties, dropdown_karegar_name, dropdown_shalwar_name;
    String[] users = {"کرتا شلوار", "کرتا پاجامہ", "قمیص شلوار", "فرنٹ اوپن کرتا"};
    String[] karegarName = {" کاریگر کا نام", "ابرار ", "احمد ", "امین ", "عارف "};
    String[] shalwar = {"شلوار کی اقسا م", "شلوار", "اسٹریٹ پاجامہ", "چوڑی ڈار پاجامہ", "پینٹ اسٹائل پاجامہ", "دھوتی شلوار", "بڑے گھیر والی شلوار"};
    NavController navController;
    CardView LL1, LL2, LL3, LL4, LL5, LL6, LL7, LL8, LL9, LL10, LL11, LL12;
    ImageView chooseCollarImage, chooseSidePocket, iv_01;
    Button submit_kurta, chooseImage;
    SharedPreference sharedPreference;
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
                Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
                dialog.setContentView(R.layout.dialog_collar_style);
                initViews(view, dialog);

                LL1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_buttondown));
                    }
                });
                LL2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_classic));
                    }
                });
                LL3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_club));
                    }
                });
                LL4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_cutaway));
                    }
                });
                LL5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_mandarin));
                    }
                });
                LL6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_medium));
                    }
                });
                LL7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_tab));
                    }
                });
                LL7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_tuxedo));
                    }
                });

                dialog.show();
                // navController.navigate(R.id.action_kurtaFragment_to_fragmentCollarSelection);
            }
        });

        chooseSidePocket.setOnClickListener(new View.OnClickListener() {
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
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_01));
                    }
                });
                LL2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_02));
                    }
                });
                LL3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_03));
                    }
                });
                LL4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_04));
                    }
                });
                LL5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_05));
                    }
                });
                LL6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_06));
                    }
                });
                LL7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_07));
                    }
                });
                LL8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_08));
                    }
                });
                LL9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_09));
                    }
                });
                LL10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_10));
                    }
                });
                LL11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_11));
                    }
                });
                LL12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_12));
                    }
                });

                dialog.show();
            }
        });


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
