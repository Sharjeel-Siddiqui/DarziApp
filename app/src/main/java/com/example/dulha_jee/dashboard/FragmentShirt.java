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
import com.example.dulha_jee.pojo.ShirtRequestBody;
import com.example.dulha_jee.userlist.DatePickerFragment;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.tapadoo.alerter.Alerter;
import com.tapadoo.alerter.OnHideAlertListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

public class FragmentShirt extends Fragment implements DatePickerDialog.OnDateSetListener {
    Spinner dropdown_karegar_name;
    Button submit_shirt, chooseImage;
    ImageView chooseCollarImage, chooseCuffImage, iv_01;
    String[] downOptions = {"شولڈر کا انتخاب کیجئے", " شولڈر ڈاؤن", "ہلکا کم شولڈر ڈاون ", "فل شولڈر ڈاؤن ہیں", "اسٹریٹ سیدھے شولڈر ہیں", "سیدھے ہاتھ کا شولڈر ڈاؤن ہے", "الٹے بائیں ہاتھ کا شولڈر ڈاؤن ہے "};
    NavController navController;
    SharedPreference sharedPreference;
    CardView LL1, LL2, LL3, LL4, LL5, LL6, LL7, LL8, LL9, LL10, LL11, LL12;
    public static boolean isComingFromShirt, isComingFromShirtBack, isComingFromShirtBack2;
    Uri imageUri;
    public static final int PICK_IMAGE = 1;
    private WebView mWebView;
    String image_4_db;
    public String collar_image, cuff_image;
    public String html_url;


    String[] users = {"کرتا شلوار", "کرتا پاجامہ", "قمیص شلوار", "فرنٹ اوپن کرتا"};
    String[] karegarName = {" کاریگر کا نام", "ابرار ", "احمد ", "امین ", "عارف "};
    Iapi iapi;
    @BindView(R.id.dropdown_down_shoulder_varieties)
    Spinner dropdown_down_shoulder_varieties;
    //fields to bind view
    @BindView(R.id.quantity)
    EditText quantity;
    @BindView(R.id.chest)
    EditText chest;
    @BindView(R.id.karigar_name)
    EditText karigar_name;
    @BindView(R.id.is_most_urgent)
    EditText is_most_urgent;
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
    Alerter alerter;
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

    @BindView(R.id.customer_name)
    EditText customer_name;
    @BindView(R.id.order_number)
    EditText order_number;
    @BindView(R.id.mobile_number)
    EditText mobile_number;


    @BindView(R.id.urgent_order_date)
    EditText urgent_order_date;
    @BindView(R.id.urgent_order_time)
    EditText urgent_order_time;

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

    @BindView(R.id.chooseOrderDate)
    Button chooseOrderDate;

    //newly added field effective from 21 - feb - 2021
    @BindView(R.id.cuff_chowk_patti)
    CheckBox cuff_chowk_patti;
    @BindView(R.id.soft_bukram_on_collar)
    CheckBox soft_bukram_on_collar;
    @BindView(R.id.hard_bukram_on_collar)
    CheckBox hard_bukram_on_collar;
    @BindView(R.id.collar_kaj_button)
    CheckBox collar_kaj_button;
    @BindView(R.id.double_sew)
    CheckBox double_sew;
    @BindView(R.id.double_cuff_soft_bukram)
    CheckBox double_cuff_soft_bukram;
    @BindView(R.id.double_kaj_link_style)
    CheckBox double_kaj_link_style;

    @BindView(R.id.cuff_width)
    EditText cuff_width;
    @BindView(R.id.coller_width_point)
    EditText coller_width_point;
    @BindView(R.id.commando_silaye_color)
    EditText commando_silaye_color;


    @BindView(R.id.kurta)
    EditText kurta;
    @BindView(R.id.pajama_shalwar)
    EditText pajama_shalwar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shirt, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbar("Shirt");
        chooseImage = view.findViewById(R.id.chooseImage);
        iv_01 = view.findViewById(R.id.iv_01);
        iapi = ApiClient.getClient().create(Iapi.class);
        alerter = Alerter.create(getActivity());
        ButterKnife.bind(this, view);
        sharedPreference = new SharedPreference(getActivity());

        chooseOrderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.setTargetFragment(FragmentShirt.this, 0);
                datePicker.show(getFragmentManager(), "date picker");
            }
        });


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

        ButterKnife.bind(this, view);
        dropdown_karegar_name = view.findViewById(R.id.dropdown_karegar_name);
        chooseCollarImage = view.findViewById(R.id.chooseCollarImage);
        chooseCuffImage = view.findViewById(R.id.chooseCuffImage);
        navController = Navigation.findNavController(view);
        sharedPreference = new SharedPreference(getActivity());
        submit_shirt = view.findViewById(R.id.submit_shirt);

        submit_shirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createShirtRequest();
            }
        });

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, karegarName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_karegar_name.setAdapter(adapter);*/

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
        chooseCuffImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
                dialog.setContentView(R.layout.dialog_cuff);
                initViews(view, dialog);

                LL1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_1_button_angle));
                        drawable_to_base64_cuff(R.drawable.cuff_1_button_angle);
                    }
                });
                LL2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_1_button_rounded));
                        drawable_to_base64_cuff(R.drawable.cuff_1_button_rounded);
                    }
                });
                LL3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_french_square));
                        drawable_to_base64_cuff(R.drawable.cuff_french_square);
                    }
                });
                LL4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_2_button_angle));
                        drawable_to_base64_cuff(R.drawable.cuff_2_button_angle);
                    }
                });
                LL5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_2_button_square));
                        drawable_to_base64_cuff(R.drawable.cuff_2_button_square);
                    }
                });
                LL6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_2_button_round));
                        drawable_to_base64_cuff(R.drawable.cuff_2_button_round);
                    }
                });
                LL7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_3_button_angle));
                        drawable_to_base64_cuff(R.drawable.cuff_3_button_angle);
                    }
                });
                LL8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_3_button_square));
                        drawable_to_base64_cuff(R.drawable.cuff_3_button_square);
                    }
                });
                LL9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_3_button_rounded));
                        drawable_to_base64_cuff(R.drawable.cuff_3_button_rounded);
                    }
                });
                LL10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_french_angle));
                        drawable_to_base64_cuff(R.drawable.cuff_french_angle);
                    }
                });
                LL11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_french_square));
                        drawable_to_base64_cuff(R.drawable.cuff_french_square);
                    }
                });
                LL12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseCuffImage.setImageDrawable(getResources().getDrawable(R.drawable.cuff_french_round));
                        drawable_to_base64_cuff(R.drawable.cuff_french_round);
                    }
                });

                dialog.show();
            }
        });

        ArrayAdapter<String> adap = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, (downOptions));
        dropdown_down_shoulder_varieties.setAdapter(adap);
    }

    private void createShirtRequest() {
        ShirtRequestBody shirtRequestBody = new ShirtRequestBody();

        //et fields...
        // customer fields..
        shirtRequestBody.setCustomer_name(TextUtils.isEmpty(customer_name.getText().toString()) ? "" : customer_name.getText().toString() + ":  کسٹمر کا نام ");
        shirtRequestBody.setMobile_number(TextUtils.isEmpty(mobile_number.getText().toString()) ? "" : mobile_number.getText().toString() + ": کسٹمر کا نمبر  ");
        shirtRequestBody.setOrder_number(TextUtils.isEmpty(order_number.getText().toString()) ? "" : order_number.getText().toString() + ": آرڈر  نمبر  ");
        shirtRequestBody.setOrder_date(TextUtils.isEmpty(order_date.getText().toString()) ? "" : " آرڈر کی تاریخ : " + order_date.getText().toString());

        //et fields
        shirtRequestBody.setQuantity(TextUtils.isEmpty(quantity.getText().toString()) ? "" : " Quantity : " + quantity.getText().toString());
        shirtRequestBody.setCollar(TextUtils.isEmpty(collar.getText().toString()) ? "" : " Collar : " + collar.getText().toString());
        shirtRequestBody.setSleeves(TextUtils.isEmpty(sleeves.getText().toString()) ? "" : " Sleeves : " + sleeves.getText().toString());
        shirtRequestBody.setShoulder(TextUtils.isEmpty(shoulder.getText().toString()) ? "" : " Shoulder : " + shoulder.getText().toString());
        shirtRequestBody.setHip(TextUtils.isEmpty(hip.getText().toString()) ? "" : " Ready Hips  : " + hip.getText().toString());
        shirtRequestBody.setGudda(TextUtils.isEmpty(gudda.getText().toString()) ? "" : " Ready Abdomen  : " + gudda.getText().toString());
        shirtRequestBody.setFront(TextUtils.isEmpty(front.getText().toString()) ? "" : " Front : " + front.getText().toString());
        shirtRequestBody.setLengthMade(TextUtils.isEmpty(lengthMade.getText().toString()) ? "" : " Length : " + lengthMade.getText().toString());
        shirtRequestBody.setAbdomen(TextUtils.isEmpty(abdomen.getText().toString()) ? "" : " Ready Waist  : " + abdomen.getText().toString());
        shirtRequestBody.setChest(TextUtils.isEmpty(chest.getText().toString()) ? "" : " Ready Chest  : " + chest.getText().toString());

        shirtRequestBody.setKurta(TextUtils.isEmpty(kurta.getText().toString()) ? "" : " Kurta : " + kurta.getText().toString());
        shirtRequestBody.setPajama_shalwar(TextUtils.isEmpty(pajama_shalwar.getText().toString()) ? "" : " Pajama / Shalwar : " + pajama_shalwar.getText().toString());


        /*shirtRequestBody.setQuantity(TextUtils.isEmpty(quantity.getText().toString()) ? "" : quantity.getText().toString() + ": عدد ");
        shirtRequestBody.setCollar(TextUtils.isEmpty(collar.getText().toString()) ? "" : collar.getText().toString() + " : کالر ");
        shirtRequestBody.setSleeves(TextUtils.isEmpty(sleeves.getText().toString()) ? "" : sleeves.getText().toString() + ": آستین  ");
        shirtRequestBody.setShoulder(TextUtils.isEmpty(shoulder.getText().toString()) ? "" : shoulder.getText().toString() + ": شولڈر  ");
        shirtRequestBody.setHip(TextUtils.isEmpty(hip.getText().toString()) ? "" : hip.getText().toString() + " : ہپ تیار ");
        shirtRequestBody.setGudda(TextUtils.isEmpty(gudda.getText().toString()) ? "" : gudda.getText().toString() + ": گڈہ تیار ");
        shirtRequestBody.setFront(TextUtils.isEmpty(front.getText().toString()) ? "" : front.getText().toString() + " : سامنا تیار  ");
        shirtRequestBody.setLengthMade(TextUtils.isEmpty(lengthMade.getText().toString()) ? "" : lengthMade.getText().toString() + ": لمبائ ");
        shirtRequestBody.setAbdomen(TextUtils.isEmpty(abdomen.getText().toString()) ? "" : abdomen.getText().toString() + " : پیٹ/waist ");*/

        shirtRequestBody.setPatti_ki_chorayi(TextUtils.isEmpty(patti_ki_chorayi.getText().toString()) ? "" : " پٹی کی چوڑائ " + patti_ki_chorayi.getText().toString() + "نچ رکھنی ہے");
        shirtRequestBody.setRemarks(TextUtils.isEmpty(remarks.getText().toString()) ? "" : remarks.getText().toString());

        //urgent time and date...
        shirtRequestBody.setUrgent_order_date(TextUtils.isEmpty(urgent_order_date.getText().toString()) ? "" : " ارجنٹ بروز " + urgent_order_date.getText().toString() + " کو چاہیے " + "آرڈر" + urgent_order_time.getText().toString() + " بجے تک لازمی");
        shirtRequestBody.setUrgent_order_time(TextUtils.isEmpty(order_date_most_urgent.getText().toString()) ? "" : " انتہائ ارجنٹ بروز " + order_date_most_urgent.getText().toString() + " کو چاہیے " + "آرڈر" + is_most_urgent.getText().toString() + " بجے تک لازمی");


        //Check BOXES COME HERE....
        shirtRequestBody.setIs_shirt(is_shirt.isChecked() ? is_shirt.getText().toString() : "");
        shirtRequestBody.setMake_coverpati_style(make_coverpati_style.isChecked() ? make_coverpati_style.getText().toString() : "");
        shirtRequestBody.setRegular_polo_pati(regular_polo_pati.isChecked() ? regular_polo_pati.getText().toString() : "");
        shirtRequestBody.setSimple_pati_style(simple_pati_style.isChecked() ? simple_pati_style.getText().toString() : "");
        shirtRequestBody.setNot_regular_polo_pat(not_regular_polo_pat.isChecked() ? not_regular_polo_pat.getText().toString() : "");
        shirtRequestBody.setBack_dart(back_dart.isChecked() ? back_dart.getText().toString() : "");
        shirtRequestBody.setAmerican_style_round_deep(american_style_round_deep.isChecked() ? american_style_round_deep.getText().toString() : "");
        shirtRequestBody.setReadymade_shirt_style_chakooti(readymade_shirt_style_chakooti.isChecked() ? readymade_shirt_style_chakooti.getText().toString() : "");


        //checkboxes for general fields

        shirtRequestBody.setCustomer_cloth(customer_cloth.isChecked() ? customer_cloth.getText().toString() : "");
        shirtRequestBody.setChild_kurta_size(child_kurta_size.isChecked() ? child_kurta_size.getText().toString() : "");
        shirtRequestBody.setOnly_sewing(only_sewing.isChecked() ? only_sewing.getText().toString() : "");
        shirtRequestBody.setFinished_adjust(finished_adjust.isChecked() ? finished_adjust.getText().toString() : "");
        shirtRequestBody.setSpecial_customer_order(special_customer_order.isChecked() ? special_customer_order.getText().toString() : "");
        shirtRequestBody.setRegular_customer_order(regular_customer_order.isChecked() ? regular_customer_order.getText().toString() : "");
        shirtRequestBody.setUrgent_order(urgent_order.isChecked() ? urgent_order.getText().toString() : "");
        shirtRequestBody.setNo_label(no_label.isChecked() ? no_label.getText().toString() : "");
        shirtRequestBody.setSpecial_order(special_order.isChecked() ? special_order.getText().toString() : "");
        shirtRequestBody.setButton_should_be_strong(button_should_be_strong.isChecked() ? button_should_be_strong.getText().toString() : "");

        shirtRequestBody.setFull_shoulder_down(dropdown_down_shoulder_varieties.getSelectedItem().toString().equals("شولڈر کا انتخاب کیجئے") ? "" : dropdown_down_shoulder_varieties.getSelectedItem().toString());

        shirtRequestBody.setAltered_body(altered_body.isChecked() ? altered_body.getText().toString() : "");
        shirtRequestBody.setDeep_body(deep_body.isChecked() ? deep_body.getText().toString() : "");
        shirtRequestBody.setParty_label(party_label.isChecked() ? party_label.getText().toString() : "");
        shirtRequestBody.setFancy_label(fancy_label.isChecked() ? fancy_label.getText().toString() : "");

        //newly added fields
        shirtRequestBody.setLight_work_shoulder_down(cuff_chowk_patti.isChecked() ? cuff_chowk_patti.getText().toString() : "");
        shirtRequestBody.setStraight_shoulder(soft_bukram_on_collar.isChecked() ? soft_bukram_on_collar.getText().toString() : "");
        shirtRequestBody.setRight_shoulder_down(hard_bukram_on_collar.isChecked() ? hard_bukram_on_collar.getText().toString() : "");
        shirtRequestBody.setLeft_shoulder_down(collar_kaj_button.isChecked() ? collar_kaj_button.getText().toString() : "");

        shirtRequestBody.setDouble_sew(double_sew.isChecked() ? double_sew.getText().toString() : "");
        shirtRequestBody.setDouble_cuff_soft_bukram(double_cuff_soft_bukram.isChecked() ? double_cuff_soft_bukram.getText().toString() : "");
        shirtRequestBody.setDouble_kaj_link_style(double_kaj_link_style.isChecked() ? double_kaj_link_style.getText().toString() : "");
        shirtRequestBody.setCuff_width(TextUtils.isEmpty(cuff_width.getText().toString()) ? "" : cuff_width.getText().toString());
        shirtRequestBody.setColler_width_point(TextUtils.isEmpty(coller_width_point.getText().toString()) ? "" : coller_width_point.getText().toString());
        shirtRequestBody.setCommando_silaye_color(TextUtils.isEmpty(commando_silaye_color.getText().toString()) ? "" : commando_silaye_color.getText().toString());
        //newly added 10 fields...

        //send Images...

        shirtRequestBody.setCuff_image(TextUtils.isEmpty(cuff_image) ? "" : cuff_image.toString());
        shirtRequestBody.setCollar_image(TextUtils.isEmpty(collar_image) ? "" : collar_image.toString());
        shirtRequestBody.setCustomer_image(TextUtils.isEmpty(image_4_db) ? "" : image_4_db);
        shirtRequestBody.setSide_pocket_image("");


        //   shirtRequestBody.setShalwar(dropdown_shalwar_name.getSelectedItem().toString());
        shirtRequestBody.setKarigar(TextUtils.isEmpty(karigar_name.getText().toString()) ? "" : karigar_name.getText().toString() + " :  کاریگر کا نام ");
        //  shirtRequestBody.setKurta_type(dropdown_kurta_varieties.getSelectedItem().toString());

        alerter.setTitle("انتطار فرمائیے۔۔۔")
                .setText("کسٹمر کا آرڈر بن رہا ہے۔۔۔")
                .setIcon(R.drawable.dulha_jee_logo)
                .setBackgroundColorRes(R.color.black).show();


        //Api call here...


        iapi.createShirt("Bearer " + sharedPreference.getToken(), shirtRequestBody).enqueue(new Callback<HtmlResponseBody>() {
            @Override
            public void onResponse(Call<HtmlResponseBody> call, Response<HtmlResponseBody> response) {
                if (response.isSuccessful()) {
                    // Toast.makeText(getActivity(), "Success..." + response.code(), Toast.LENGTH_SHORT).show();
                    Log.i("TAG", "onResponse: " + response.message());
                    Log.i("TAG", "onResponse: " + response.raw());
                    html_url = response.body().getUrl();
                    doWebViewPrint();
                    Alerter.hide();
                } else {
                    Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                    Alerter.hide();
                }
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

    public void drawable_to_base64_cuff(int drawable) {
        //encode image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawable);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        cuff_image = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        Log.i("TAG", "drawable_to_base64: " + cuff_image);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH, i1);
        c.set(Calendar.DAY_OF_MONTH, i2);
        String currentDateString = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
        order_date.setText(currentDateString);
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
