package com.example.dulha_jee.dashboard;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.dulha_jee.MainActivity;
import com.example.dulha_jee.R;
import com.example.dulha_jee.SharedPreference;
import com.example.dulha_jee.api.ApiClient;
import com.example.dulha_jee.api.Iapi;
import com.example.dulha_jee.pojo.GetUserResponseBody;
import com.example.dulha_jee.pojo.HtmlResponseBody;
import com.example.dulha_jee.pojo.KurtaRequestBody;
import com.example.dulha_jee.userlist.DatePickerFragment;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.tapadoo.alerter.Alerter;
import com.tapadoo.alerter.OnHideAlertListener;
import com.tapadoo.alerter.OnShowAlertListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class KurtaFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    static boolean isComingbackfromCollar_Kurta, isComingbackfromSidePocket;
    Spinner dropdown_kurta_varieties, dropdown_karegar_name, dropdown_shalwar_name;
    String[] users = {"کرتا شلوار", "کرتا پاجامہ", "قمیص شلوار", "فرنٹ اوپن کرتا"};
    String[] karegarName = {" کاریگر کا نام", "ابرار ", "احمد ", "امین ", "عارف "};
    String[] shalwar = {"شلوار کی اقسا م", "شلوار", "اسٹریٹ پاجامہ", "چوڑی ڈار پاجامہ", "پینٹ اسٹائل پاجامہ", "دھوتی شلوار", "بڑے گھیر والی شلوار"};
    String[] downOptions = {"شولڈر کا انتخاب کیجئے", " شولڈر ڈاؤن", "ہلکا کم شولڈر ڈاون ", "فل شولڈر ڈاؤن ہیں", "اسٹریٹ سیدھے شولڈر ہیں", "سیدھے ہاتھ کا شولڈر ڈاؤن ہے", "الٹے بائیں ہاتھ کا شولڈر ڈاؤن ہے "};
    String[] buttonName = {"بٹن منتخب کریں", "براس کے بٹن", "کاپر کلر بٹن", "سلور کلر بٹن", "مہندی گولڈ کلر بٹن", "سرمئ کلر بٹن", "گول بٹن", "فینسی بٹن لگانے ہیں"};

    NavController navController;
    CardView LL1, LL2, LL3, LL4, LL5, LL6, LL7, LL8, LL9, LL10, LL11, LL12;
    ImageView chooseCollarImage, chooseSidePocket, iv_01;
    Button submit_kurta, chooseImage;
    SharedPreference sharedPreference;
    Uri imageUri;
    public static final int PICK_IMAGE = 1;
    String image_4_db;
    private WebView mWebView;
    Iapi iapi;
    public String collar_image, sidepocket_image;
    public String html_url;

    @BindView(R.id.dropdown_down_shoulder_varieties)
    Spinner dropdown_down_shoulder_varieties;
    @BindView(R.id.dropdown_choose_button)
    Spinner dropdown_choose_button;

    @BindView(R.id.chooseOrderDate)
    Button chooseOrderDate;

    //fields to bind view
    @BindView(R.id.quantity)
    EditText quantity;
    @BindView(R.id.karigar_name)
    EditText karigar_name;
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
    @BindView(R.id.is_most_urgent)
    EditText is_most_urgent;
    @BindView(R.id.order_date_most_urgent)
    EditText order_date_most_urgent;
    @BindView(R.id.remarks)
    EditText remarks;

    @BindView(R.id.customer_name)
    EditText customer_name;
    @BindView(R.id.order_number)
    EditText order_number;
    @BindView(R.id.mobile_number)
    EditText mobile_number;
    @BindView(R.id.order_date)
    EditText order_date;

    @BindView(R.id.urgent_order_date)
    EditText urgent_order_date;
    @BindView(R.id.urgent_order_time)
    EditText urgent_order_time;

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
    EditText front_pati_width;
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

    Alerter alerter;

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
        ButterKnife.bind(this, view);
        iapi = ApiClient.getClient().create(Iapi.class);
        sharedPreference = new SharedPreference(getActivity());
        submit_kurta = view.findViewById(R.id.submit_kurta);
        dropdown_kurta_varieties = view.findViewById(R.id.dropdown_kurta_varieties);
        dropdown_karegar_name = view.findViewById(R.id.dropdown_karegar_name);
        dropdown_shalwar_name = view.findViewById(R.id.dropdown_shalwar_name);
        chooseCollarImage = view.findViewById(R.id.chooseCollarImage);
        chooseSidePocket = view.findViewById(R.id.chooseSidePocket);
        chooseImage = view.findViewById(R.id.chooseImage);
        iv_01 = view.findViewById(R.id.iv_01);
        alerter = Alerter.create(getActivity());


        chooseOrderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.setTargetFragment(KurtaFragment.this, 0);
                datePicker.show(getFragmentManager(), "date picker");
            }
        });


        /*iapi.getUsers("Bearer " + sharedPreference.getToken()).enqueue(new Callback<GetUserResponseBody>() {
            @Override
            public void onResponse(Call<GetUserResponseBody> call, Response<GetUserResponseBody> response) {
                GetUserResponseBody getUserResponseBody = response.body();
                // getUserResponseBody.getData();

                if (getUserResponseBody.getData() != null) {
                    String[] arr = getUserResponseBody.getData().toArray(new String[getUserResponseBody.getData().size()]);
                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arr);
                    adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dropdown_karegar_name.setAdapter(adapter3);
                }
                //Toast.makeText(getActivity(), "Success" + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<GetUserResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed...", Toast.LENGTH_SHORT).show();
            }
        });*/

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

                createKurtaRequest();

            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, users);
        dropdown_kurta_varieties.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, shalwar);
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
                        drawable_to_base64(R.drawable.collar_buttondown);
                    }
                });
                LL2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_classic));
                        drawable_to_base64(R.drawable.collar_classic);
                    }
                });
                LL3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_club));
                        drawable_to_base64(R.drawable.collar_club);
                    }
                });
                LL4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_cutaway));
                        drawable_to_base64(R.drawable.collar_cutaway);
                    }
                });
                LL5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_mandarin));
                        drawable_to_base64(R.drawable.collar_mandarin);
                    }
                });
                LL6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_medium));
                        drawable_to_base64(R.drawable.collar_medium);
                    }
                });
                LL7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_tab));
                        drawable_to_base64(R.drawable.collar_tab);
                    }
                });
                LL7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCollarImage.setImageDrawable(getResources().getDrawable(R.drawable.collar_tuxedo));
                        drawable_to_base64(R.drawable.collar_tuxedo);
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
                        drawable_to_base64_side_pocket(R.drawable.side_pocket_01);
                    }
                });
                LL2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_02));
                        drawable_to_base64_side_pocket(R.drawable.side_pocket_02);
                    }
                });
                LL3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_03));
                        drawable_to_base64_side_pocket(R.drawable.side_pocket_03);
                    }
                });
                LL4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_04));
                        drawable_to_base64_side_pocket(R.drawable.side_pocket_04);
                    }
                });
                LL5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_05));
                        drawable_to_base64_side_pocket(R.drawable.side_pocket_05);
                    }
                });
                LL6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_06));
                        drawable_to_base64_side_pocket(R.drawable.side_pocket_06);
                    }
                });
                LL7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_07));
                        drawable_to_base64_side_pocket(R.drawable.side_pocket_07);
                    }
                });
                LL8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_08));
                        drawable_to_base64_side_pocket(R.drawable.side_pocket_08);
                    }
                });
                LL9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_09));
                        drawable_to_base64_side_pocket(R.drawable.side_pocket_09);
                    }
                });
                LL10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_10));
                        drawable_to_base64_side_pocket(R.drawable.side_pocket_10);
                    }
                });
                LL11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_11));
                        drawable_to_base64_side_pocket(R.drawable.side_pocket_11);
                    }
                });
                LL12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocket.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_12));
                        drawable_to_base64_side_pocket(R.drawable.side_pocket_12);
                    }
                });

                dialog.show();
            }
        });

        ArrayAdapter<String> adap = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, (downOptions));
        dropdown_down_shoulder_varieties.setAdapter(adap);

        ArrayAdapter<String> are = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, (buttonName));
        dropdown_choose_button.setAdapter(are);

    }

    private void createKurtaRequest() {


        //all checked field goes here
        KurtaRequestBody kurtaRequestBody = new KurtaRequestBody();
        //et fields
        kurtaRequestBody.setCustomer_name(TextUtils.isEmpty(customer_name.getText().toString()) ? "" : customer_name.getText().toString() + ":  کسٹمر کا نام ");
        kurtaRequestBody.setMobile_number(TextUtils.isEmpty(mobile_number.getText().toString()) ? "" : mobile_number.getText().toString() + ": کسٹمر کا نمبر  ");
        kurtaRequestBody.setOrder_number(TextUtils.isEmpty(order_number.getText().toString()) ? "" : order_number.getText().toString() + ": آرڈر  نمبر  ");
        kurtaRequestBody.setOrder_date(TextUtils.isEmpty(order_date.getText().toString()) ? "" : " آرڈر کی تاریخ : " + order_date.getText().toString());

 /*       kurtaRequestBody.setQuantity(TextUtils.isEmpty(quantity.getText().toString()) ? "" : quantity.getText().toString() + ": عدد ");
        kurtaRequestBody.setCollar(TextUtils.isEmpty(collar.getText().toString()) ? "" : collar.getText().toString() + " : کالر ");
        kurtaRequestBody.setSleeves(TextUtils.isEmpty(sleeves.getText().toString()) ? "" : sleeves.getText().toString() + ": آستین  ");
        kurtaRequestBody.setShoulder(TextUtils.isEmpty(shoulder.getText().toString()) ? "" : shoulder.getText().toString() + ": شولڈر  ");
        kurtaRequestBody.setHip(TextUtils.isEmpty(hip.getText().toString()) ? "" : hip.getText().toString() + " : ہپ تیار ");
        kurtaRequestBody.setGudda(TextUtils.isEmpty(gudda.getText().toString()) ? "" : gudda.getText().toString() + ": گڈہ تیار ");
        kurtaRequestBody.setFront(TextUtils.isEmpty(front.getText().toString()) ? "" : front.getText().toString() + " : سامنا تیار  ");
        kurtaRequestBody.setLengthMade(TextUtils.isEmpty(lengthMade.getText().toString()) ? "" : lengthMade.getText().toString() + ": لمبائ ");*/

        kurtaRequestBody.setQuantity(TextUtils.isEmpty(quantity.getText().toString()) ? "" : " Quantity : " +  quantity.getText().toString()  );
        kurtaRequestBody.setCollar(TextUtils.isEmpty(collar.getText().toString()) ? "" : " Collar : " +  collar.getText().toString()  );
        kurtaRequestBody.setSleeves(TextUtils.isEmpty(sleeves.getText().toString()) ? "" : " Sleeves : " + sleeves.getText().toString() );
        kurtaRequestBody.setShoulder(TextUtils.isEmpty(shoulder.getText().toString()) ? "" : " Shoulder : " + shoulder.getText().toString() );
        kurtaRequestBody.setHip(TextUtils.isEmpty(hip.getText().toString()) ? "" : " Hip : " + hip.getText().toString() );
        kurtaRequestBody.setGudda(TextUtils.isEmpty(gudda.getText().toString()) ? "" : " Gudda : " + gudda.getText().toString() );
        kurtaRequestBody.setFront(TextUtils.isEmpty(front.getText().toString()) ? "" : " Front : " + front.getText().toString() );
        kurtaRequestBody.setLengthMade(TextUtils.isEmpty(lengthMade.getText().toString()) ? "" : "Length : " + lengthMade.getText().toString() );

        //urgent time and date...
        kurtaRequestBody.setUrgent_order_date(TextUtils.isEmpty(urgent_order_date.getText().toString()) ? "" : " ارجنٹ بروز " + urgent_order_date.getText().toString() + " کو چاہیے " + "آرڈر" + urgent_order_time.getText().toString() + " بجے تک لازمی");
        kurtaRequestBody.setUrgent_order_time(TextUtils.isEmpty(order_date_most_urgent.getText().toString()) ? "" : " انتہائ ارجنٹ بروز " + order_date_most_urgent.getText().toString() + " کو چاہیے " + "آرڈر" + is_most_urgent.getText().toString() + " بجے تک لازمی");


        kurtaRequestBody.setLeft_shoulder_down(dropdown_choose_button.getSelectedItem().equals("بٹن منتخب کریں") ? "" : dropdown_choose_button.getSelectedItem().toString());


        //collar style image field

        kurtaRequestBody.setCollar_width(TextUtils.isEmpty(collar_width.getText().toString()) ? "" : " کالر کی چوڑائ " + collar_width.getText().toString() + " انچ رکھنی ہے ");
        kurtaRequestBody.setCollar_point_length(TextUtils.isEmpty(collar_point_length.getText().toString()) ? "" : " کالر کے پر کئ نوک " + collar_point_length.getText().toString() + "انچ رکھنی ہے");
        kurtaRequestBody.setFront_pati_length(TextUtils.isEmpty(front_pati_length.getText().toString()) ? "" : " سامنے کی ریگولر پٹی کی لمبائ " + front_pati_length.getText().toString() + "انچ رکھنی ہے");
        // kurtaRequestBody.setFront_pati_length(TextUtils.isEmpty(front_pati_length.getText().toString()) ? "" : front_pati_length.getText().toString());
        kurtaRequestBody.setFront_pati_width(TextUtils.isEmpty(front_pati_width.getText().toString()) ? "" : " سامنے کے ریگولر پٹی کے چوڑائ " + front_pati_width.getText().toString() + " انچ رکھنی ہے ");
        kurtaRequestBody.setFront_pati_cadge(TextUtils.isEmpty(front_pati_cadge.getText().toString()) ? "" : " سامنے کے پٹی میں " + front_pati_cadge.getText().toString() + " عدد کاج ہونگے ");
        kurtaRequestBody.setCover_pati_length(TextUtils.isEmpty(cover_pati_length.getText().toString()) ? "" : " کور پٹی کی لمبائ " + cover_pati_length.getText().toString() + "انچ رکھنی ہے");
        kurtaRequestBody.setCuff_width(TextUtils.isEmpty(cuff_width.getText().toString()) ? "" : "کف کی چوڑائ" + cuff_width.getText().toString() + " انچ رکھنی ہے ");
        kurtaRequestBody.setCuff_chowk_width(TextUtils.isEmpty(cuff_chowk_width.getText().toString()) ? "" : " کف کی چاک پٹی کی چوڑائ " + cuff_chowk_width.getText().toString() + " انچ رکھنی ہے ");
        kurtaRequestBody.setCuff_length(TextUtils.isEmpty(cuff_length.getText().toString()) ? "" : " کف کی لمبائ " + cuff_length.getText().toString() + " انچ رکھنی ہے ");
        // kurtaRequestBody.setCuff_length(TextUtils.isEmpty(cuff_length.getText().toString()) ? "" : cuff_length.getText().toString());
        kurtaRequestBody.setChowk_length(TextUtils.isEmpty(chowk_length.getText().toString()) ? "" : " چاک " + chowk_length.getText().toString() + " انچ کے رکھنے ہیں ");
        kurtaRequestBody.setMachine_sew_bail(TextUtils.isEmpty(machine_sew_bail.getText().toString()) ? "" : " سامنے پہ مشین کی کڑھائ کی بیل بنانی ہے" + machine_sew_bail.getText().toString() + " انچ چوڑی ");
        kurtaRequestBody.setPocket_length(TextUtils.isEmpty(pocket_length.getText().toString()) ? "" : " جیب کی لمبائ " + pocket_length.getText().toString() + " انچ رکھنی ہے ");
        kurtaRequestBody.setPocket_width(TextUtils.isEmpty(pocket_width.getText().toString()) ? "" : " جیب کی چوڑائ " + pocket_width.getText().toString() + " انچ رکھنی ہے ");
        kurtaRequestBody.setRemarks(TextUtils.isEmpty(remarks.getText().toString()) ? "" : remarks.getText().toString());

        //check boxes field
        kurtaRequestBody.setSherwani_collar(sherwani_collar.isChecked() ? sherwani_collar.getText().toString() : "");
        kurtaRequestBody.setCollar_front_round(collar_front_round.isChecked() ? collar_front_round.getText().toString() : "");
        kurtaRequestBody.setCollar_pointed(collar_pointed.isChecked() ? collar_pointed.getText().toString() : "");
        kurtaRequestBody.setCut_collar(cut_collar.isChecked() ? cut_collar.getText().toString() : "");
        kurtaRequestBody.setCollar_on_collar(collar_on_collar.isChecked() ? collar_on_collar.getText().toString() : "");
        kurtaRequestBody.setV_collar(v_collar.isChecked() ? v_collar.getText().toString() : "");
        kurtaRequestBody.setShirt_collar(shirt_collar.isChecked() ? shirt_collar.getText().toString() : "");
        kurtaRequestBody.setReverse_style_french_collar(reverse_style_french_collar.isChecked() ? reverse_style_french_collar.getText().toString() : "");
        kurtaRequestBody.setTaxido_style_collar(taxido_style_collar.isChecked() ? taxido_style_collar.getText().toString() : "");
        kurtaRequestBody.setCollar_style_small(collar_style_small.isChecked() ? collar_style_small.getText().toString() : "");
        kurtaRequestBody.setButton_on_point(button_on_point.isChecked() ? button_on_point.getText().toString() : "");
        kurtaRequestBody.setButton_on_cadge_small(button_on_cadge_small.isChecked() ? button_on_cadge_small.getText().toString() : "");
        kurtaRequestBody.setSoft_bukram_on_collar(soft_bukram_on_collar.isChecked() ? soft_bukram_on_collar.getText().toString() : "");
        kurtaRequestBody.setCustomer_collar_soft(customer_collar_soft.isChecked() ? customer_collar_soft.getText().toString() : "");
        kurtaRequestBody.setHard_bukram_on_collar(hard_bukram_on_collar.isChecked() ? hard_bukram_on_collar.getText().toString() : "");
        kurtaRequestBody.setMore_bukram_on_collar(more_bukram_on_collar.isChecked() ? more_bukram_on_collar.getText().toString() : "");
        kurtaRequestBody.setMore_bukram_on_collar(more_bukram_on_collar.isChecked() ? more_bukram_on_collar.getText().toString() : "");
        kurtaRequestBody.setHidden_on_collar(hidden_on_collar.isChecked() ? hidden_on_collar.getText().toString() : "");
        kurtaRequestBody.setDesign_as_on_picture_collar(design_as_on_picture_collar.isChecked() ? design_as_on_picture_collar.getText().toString() : "");
        kurtaRequestBody.setRound_neck_magzi(round_neck_magzi.isChecked() ? round_neck_magzi.getText().toString() : "");
        kurtaRequestBody.setRound_neck_magzi_with_finishing(round_neck_magzi_with_finishing.isChecked() ? round_neck_magzi_with_finishing.getText().toString() : "");
        kurtaRequestBody.setRegular_pati(regular_pati.isChecked() ? regular_pati.getText().toString() : "");
        kurtaRequestBody.setPointed_pati(pointed_pati.isChecked() ? pointed_pati.getText().toString() : "");
        kurtaRequestBody.setStraight_pati(straight_pati.isChecked() ? straight_pati.getText().toString() : "");
        kurtaRequestBody.setBoat_sew_on_pati(boat_sew_on_pati.isChecked() ? boat_sew_on_pati.getText().toString() : "");
        kurtaRequestBody.setCover_pati(cover_pati.isChecked() ? cover_pati.getText().toString() : "");
        kurtaRequestBody.setFront_pati_style_as_image(front_pati_style_as_image.isChecked() ? front_pati_style_as_image.getText().toString() : "");
        kurtaRequestBody.setCut_sleeves(cut_sleeves.isChecked() ? cut_sleeves.getText().toString() : "");
        kurtaRequestBody.setCut_cuff(cut_cuff.isChecked() ? cut_cuff.getText().toString() : "");
        kurtaRequestBody.setRound_cuff(round_cuff.isChecked() ? round_cuff.getText().toString() : "");
        kurtaRequestBody.setStraight_cuff_pointed(straight_cuff_pointed.isChecked() ? straight_cuff_pointed.getText().toString() : "");
        kurtaRequestBody.setCuff_link_style_cuff(cuff_link_style_cuff.isChecked() ? cuff_link_style_cuff.getText().toString() : "");
        kurtaRequestBody.setDouble_cuff(double_cuff.isChecked() ? double_cuff.getText().toString() : "");
        kurtaRequestBody.setHard_bukram_in_cuff(hard_bukram_in_cuff.isChecked() ? hard_bukram_in_cuff.getText().toString() : "");
        kurtaRequestBody.setHard_bukram_in_cuff(hard_bukram_in_cuff.isChecked() ? hard_bukram_in_cuff.getText().toString() : "");
        kurtaRequestBody.setSoft_bukram_in_cuff(soft_bukram_in_cuff.isChecked() ? soft_bukram_in_cuff.getText().toString() : "");
        kurtaRequestBody.setCuff_chowk_cadge(cuff_chowk_cadge.isChecked() ? cuff_chowk_cadge.getText().toString() : "");
        kurtaRequestBody.setCuff_chowk_bukram(cuff_chowk_bukram.isChecked() ? cuff_chowk_bukram.getText().toString() : "");
        kurtaRequestBody.setCuff_chowk_pointed(cuff_chowk_pointed.isChecked() ? cuff_chowk_pointed.getText().toString() : "");
        kurtaRequestBody.setCuff_chowk_straight(cuff_chowk_straight.isChecked() ? cuff_chowk_straight.getText().toString() : "");
        kurtaRequestBody.setCuff_chowk_round(cuff_chowk_round.isChecked() ? cuff_chowk_round.getText().toString() : "");
        kurtaRequestBody.setPointer_style_picture(pointer_style_picture.isChecked() ? pointer_style_picture.getText().toString() : "");
        kurtaRequestBody.setCuff_machine_karhayi(cuff_machine_karhayi.isChecked() ? cuff_machine_karhayi.getText().toString() : "");
        kurtaRequestBody.setCuff_hand_karhayi(cuff_hand_karhayi.isChecked() ? cuff_hand_karhayi.getText().toString() : "");
        kurtaRequestBody.setStraight_kurta_daman(straight_kurta_daman.isChecked() ? straight_kurta_daman.getText().toString() : "");
        kurtaRequestBody.setRound_daman(round_daman.isChecked() ? round_daman.getText().toString() : "");
        kurtaRequestBody.setKamiz_style_daman(kamiz_style_daman.isChecked() ? kamiz_style_daman.getText().toString() : "");
        kurtaRequestBody.setPiyala_style_daman(piyala_style_daman.isChecked() ? piyala_style_daman.getText().toString() : "");
        kurtaRequestBody.setStraight_daman_pointed_corner(straight_daman_pointed_corner.isChecked() ? straight_daman_pointed_corner.getText().toString() : "");
        kurtaRequestBody.setDaman_double_sew(daman_double_sew.isChecked() ? daman_double_sew.getText().toString() : "");
        kurtaRequestBody.setDaman_finishing(daman_finishing.isChecked() ? daman_finishing.getText().toString() : "");
        kurtaRequestBody.setDaman_as_image(daman_as_image.isChecked() ? daman_as_image.getText().toString() : "");
        kurtaRequestBody.setChakoti_american(chakoti_american.isChecked() ? chakoti_american.getText().toString() : "");
        kurtaRequestBody.setChakoti(chakoti.isChecked() ? chakoti.getText().toString() : "");
        kurtaRequestBody.setChakoti_contrast(chakoti_contrast.isChecked() ? chakoti_contrast.getText().toString() : "");
        kurtaRequestBody.setTeere_piece(teere_piece.isChecked() ? teere_piece.getText().toString() : "");
        kurtaRequestBody.setNot_teere_piece(not_teere_piece.isChecked() ? not_teere_piece.getText().toString() : "");
        kurtaRequestBody.setDouble_sew_full(double_sew_full.isChecked() ? double_sew_full.getText().toString() : "");
        kurtaRequestBody.setLab_sew(lab_sew.isChecked() ? lab_sew.getText().toString() : "");
        kurtaRequestBody.setPaki_lab_sew(paki_lab_sew.isChecked() ? paki_lab_sew.getText().toString() : "");
        kurtaRequestBody.setChanpa(chanpa.isChecked() ? chanpa.getText().toString() : "");
        kurtaRequestBody.setFront_bunch(front_bunch.isChecked() ? front_bunch.getText().toString() : "");
        kurtaRequestBody.setCollar_machine_sew(collar_machine_sew.isChecked() ? collar_machine_sew.getText().toString() : "");
        kurtaRequestBody.setSleeves_machine_sew(sleeves_machine_sew.isChecked() ? sleeves_machine_sew.getText().toString() : "");
        kurtaRequestBody.setShoulder_machine_sew(shoulder_machine_sew.isChecked() ? shoulder_machine_sew.getText().toString() : "");
        kurtaRequestBody.setCollar_pati_hand_sew(collar_pati_hand_sew.isChecked() ? collar_pati_hand_sew.getText().toString() : "");
        kurtaRequestBody.setCollar_hand_sew(collar_hand_sew.isChecked() ? collar_hand_sew.getText().toString() : "");
        kurtaRequestBody.setPati_hand_sew(pati_hand_sew.isChecked() ? pati_hand_sew.getText().toString() : "");
        kurtaRequestBody.setFront_hand_sew(front_hand_sew.isChecked() ? front_hand_sew.getText().toString() : "");
        kurtaRequestBody.setSleeve_hand_sew(sleeve_hand_sew.isChecked() ? sleeve_hand_sew.getText().toString() : "");
        kurtaRequestBody.setShoulder_hand_sew(shoulder_hand_sew.isChecked() ? shoulder_hand_sew.getText().toString() : "");
        kurtaRequestBody.setOne_front_pocket_style(one_front_pocket_style.isChecked() ? one_front_pocket_style.getText().toString() : "");
        kurtaRequestBody.setTwo_front_pocket(two_front_pocket.isChecked() ? two_front_pocket.getText().toString() : "");
        kurtaRequestBody.setTwo_side_pocket(two_side_pocket.isChecked() ? two_side_pocket.getText().toString() : "");
        kurtaRequestBody.setRight_side_pocket(right_side_pocket.isChecked() ? right_side_pocket.getText().toString() : "");
        kurtaRequestBody.setLeft_side_pocket(left_side_pocket.isChecked() ? left_side_pocket.getText().toString() : "");
        kurtaRequestBody.setSide_pocket_deep(side_pocket_deep.isChecked() ? side_pocket_deep.getText().toString() : "");

        //checkboxes for general fields

        kurtaRequestBody.setCustomer_cloth(customer_cloth.isChecked() ? customer_cloth.getText().toString() : "");
        kurtaRequestBody.setChild_kurta_size(child_kurta_size.isChecked() ? child_kurta_size.getText().toString() : "");
        kurtaRequestBody.setOnly_sewing(only_sewing.isChecked() ? only_sewing.getText().toString() : "");
        kurtaRequestBody.setFinished_adjust(finished_adjust.isChecked() ? finished_adjust.getText().toString() : "");
        kurtaRequestBody.setSpecial_customer_order(special_customer_order.isChecked() ? special_customer_order.getText().toString() : "");
        kurtaRequestBody.setRegular_customer_order(regular_customer_order.isChecked() ? regular_customer_order.getText().toString() : "");
        kurtaRequestBody.setUrgent_order(urgent_order.isChecked() ? urgent_order.getText().toString() : "");
        kurtaRequestBody.setNo_label(no_label.isChecked() ? no_label.getText().toString() : "");
        kurtaRequestBody.setSpecial_order(special_order.isChecked() ? special_order.getText().toString() : "");
        kurtaRequestBody.setButton_should_be_strong(button_should_be_strong.isChecked() ? button_should_be_strong.getText().toString() : "");
        kurtaRequestBody.setLight_work_shoulder_down(light_work_shoulder_down.isChecked() ? light_work_shoulder_down.getText().toString() : "");
        kurtaRequestBody.setFull_shoulder_down(dropdown_down_shoulder_varieties.getSelectedItem().toString().equals("شولڈر کا انتخاب کیجئے") ? "" : dropdown_down_shoulder_varieties.getSelectedItem().toString());
        kurtaRequestBody.setStraight_shoulder(straight_shoulder.isChecked() ? straight_shoulder.getText().toString() : "");
        kurtaRequestBody.setRight_shoulder_down(right_shoulder_down.isChecked() ? right_shoulder_down.getText().toString() : "");
        kurtaRequestBody.setAltered_body(altered_body.isChecked() ? altered_body.getText().toString() : "");
        kurtaRequestBody.setDeep_body(deep_body.isChecked() ? deep_body.getText().toString() : "");
        kurtaRequestBody.setParty_label(party_label.isChecked() ? party_label.getText().toString() : "");
        kurtaRequestBody.setFancy_label(fancy_label.isChecked() ? fancy_label.getText().toString() : "");


        //send Images...

        kurtaRequestBody.setCuff_image("");
        kurtaRequestBody.setCollar_image(TextUtils.isEmpty(collar_image) ? "" : collar_image.toString());
        kurtaRequestBody.setCustomer_image(TextUtils.isEmpty(image_4_db) ? "" : image_4_db);
        kurtaRequestBody.setSide_pocket_image(TextUtils.isEmpty(sidepocket_image) ? "" : sidepocket_image);


        kurtaRequestBody.setShalwar(dropdown_shalwar_name.getSelectedItem().toString());
        kurtaRequestBody.setKarigar(TextUtils.isEmpty(karigar_name.getText().toString()) ? "" : karigar_name.getText().toString() + " :  کاریگر کا نام ");
        kurtaRequestBody.setKurta_type(dropdown_kurta_varieties.getSelectedItem().toString());


        String value = kurtaRequestBody.getCollar_image();
        //Api call here...

        alerter.setTitle("انتطار فرمائیے۔۔۔")
                .setText("کسٹمر کا آرڈر بن رہا ہے۔۔۔")
                .setIcon(R.drawable.dulha_jee_logo)
                .setBackgroundColorRes(R.color.black).show();


        iapi.createKurta("Bearer " + sharedPreference.getToken(), kurtaRequestBody).enqueue(new Callback<HtmlResponseBody>() {
            @Override
            public void onResponse(Call<HtmlResponseBody> call, Response<HtmlResponseBody> response) {
                //  Toast.makeText(getActivity(), "Success..." + response.code(), Toast.LENGTH_SHORT).show();
                Log.i("TAG", "onResponse: " + response.message());
                Log.i("TAG", "onResponse: " + response.raw());
                //response.body().getUrl();
                html_url = response.body().getUrl();
                Alerter.hide();
                doWebViewPrint();

            }

            @Override
            public void onFailure(Call<HtmlResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed...", Toast.LENGTH_SHORT).show();
                Alerter.hide();
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
            // iv_01.setImageURI(imageUri);

            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                iv_01.setImageBitmap(bitmap);
                image_4_db = ConvertBitmapToString(bitmap);
                Log.i("TAG", "base 64 image" + image_4_db);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }

    public static String ConvertBitmapToString(Bitmap bitmap) {
        String encodedImage = "";

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        try {
            encodedImage = URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return encodedImage;
    }

    private void doWebViewPrint() {

        WebView webView = new WebView(getActivity());
        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i("TAG", "page finished loading " + url);
                createWebPrintJob(view);
                mWebView = null;
            }
        });

        webView.loadUrl(html_url);

        mWebView = webView;
    }

    private void createWebPrintJob(WebView webView) {

        // Get a PrintManager instance
        PrintManager printManager = (PrintManager) getActivity().getSystemService(Context.PRINT_SERVICE);

        String jobName = getString(R.string.app_name) + " Document";

        // Get a print adapter instance
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(jobName);

        // Create a print job with name and adapter instance
        PrintJob printJob = printManager.print(jobName, printAdapter,
                new PrintAttributes.Builder().build());

        // Save the job object for later status checking
    }

    public void drawable_to_base64(int drawable) {
        //encode image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawable);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        collar_image = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        Log.i("TAG", "drawable_to_base64: " + collar_image);
    }

    public void drawable_to_base64_side_pocket(int drawable) {
        //encode image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawable);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        sidepocket_image = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        Log.i("TAG", "drawable_to_base64: " + sidepocket_image);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH, i1);
        c.set(Calendar.DAY_OF_MONTH, i2);
        String currentDateString1 = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
        Log.i("TAG", "onDateSet: " + currentDateString1);
        order_date.setText(currentDateString1);
    }

    public boolean checkValidation() {
        if (TextUtils.isEmpty(karigar_name.getText().toString())) {
            Toast.makeText(getActivity(), "کاریگر کا نام درکار ہے۔۔۔", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(customer_name.getText().toString())) {
            Toast.makeText(getActivity(), "کسٹمر کا نام درکار ہے۔۔۔", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(mobile_number.getText().toString())) {
            Toast.makeText(getActivity(), "کسٹمر کا نمبر درکار ہے۔۔۔", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
