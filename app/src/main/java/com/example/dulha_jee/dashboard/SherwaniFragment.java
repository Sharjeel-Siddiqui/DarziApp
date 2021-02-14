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
import com.example.dulha_jee.pojo.SherwaniRequestBody;
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
import java.util.List;

import butterknife.*;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class SherwaniFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    Spinner dropdown_karegar_name, dropdown_shalwar_name;
    String[] users = {"کرتا شلوار", "کرتا پاجامہ", "قمیص شلوار", "فرنٹ اوپن کرتا"};
    String[] shalwar = {"شلوار", "اسٹریٹ پاجامہ", "چوڑی ڈار پاجامہ", "پینٹ اسٹائل پاجامہ", "دھوتی شلوار", "بڑے گھیر والی شلوار"};
    String[] karegarName = {" کاریگر کا نام", "ابرار ", "احمد ", "امین ", "عارف "};
    String[] downOptions = {"شولڈر کا انتخاب کیجئے", " شولڈر ڈاؤن", "ہلکا کم شولڈر ڈاون ", "فل شولڈر ڈاون شولڈر ڈاون ", "اسٹریٹ سیدھے شولڈر ", "سیدھے ہاتھ کا شولڈر ڈاؤن", "الٹے بائیں ہاتھ کا شولڈر ڈاؤن "};

    CardView LL1, LL2, LL3, LL4, LL5, LL6, LL7, LL8, LL9, LL10, LL11, LL12;
    ImageView chooseSidePocketImage, iv_01;
    NavController navController;
    SharedPreference sharedPreference;
    public static boolean isComingFromSherwani, isComingFromShewaniBack;
    Button submit_sherwani, chooseImage;
    Uri imageUri;
    public static final int PICK_IMAGE = 1;
    Iapi iapi;
    private WebView mWebView;
    String image_4_db;
    public static String pocket_string;
    public String html_url;
    public String collar_image, sidepocket_image;

    @BindView(R.id.dropdown_down_shoulder_varieties)
    Spinner dropdown_down_shoulder_varieties;

    @BindView(R.id.chooseOrderDate)
    Button chooseOrderDate;
    @BindView(R.id.karigar_name)
    EditText karigar_name;

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

    Alerter alerter;

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
        iapi = ApiClient.getClient().create(Iapi.class);
        dropdown_karegar_name = view.findViewById(R.id.dropdown_karegar_name);
        navController = Navigation.findNavController(view);
        chooseSidePocketImage = view.findViewById(R.id.chooseSidePocketImage);
        submit_sherwani = view.findViewById(R.id.submit_sherwani);
        chooseImage = view.findViewById(R.id.chooseImage);
        alerter = Alerter.create(getActivity());

        chooseOrderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.setTargetFragment(SherwaniFragment.this, 0);
                datePicker.show(getFragmentManager(), "date picker");
            }
        });


        iapi.getUsers("Bearer " + sharedPreference.getToken()).enqueue(new Callback<GetUserResponseBody>() {
            @Override
            public void onResponse(Call<GetUserResponseBody> call, Response<GetUserResponseBody> response) {
                GetUserResponseBody getUserResponseBody = response.body();
                getUserResponseBody.getData();

                String[] arr = getUserResponseBody.getData().toArray(new String[getUserResponseBody.getData().size()]);
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arr);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dropdown_karegar_name.setAdapter(adapter3);

                //Toast.makeText(getActivity(), "Success" + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<GetUserResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed...", Toast.LENGTH_SHORT).show();
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


        submit_sherwani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidation()) {
                    createSherwaniRequest();
                }
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
                        drawable_to_base64(R.drawable.side_pocket_01);
                    }
                });
                LL2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_02));
                        drawable_to_base64(R.drawable.side_pocket_02);

                    }
                });
                LL3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_03));
                        drawable_to_base64(R.drawable.side_pocket_03);
                    }
                });
                LL4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_04));
                        drawable_to_base64(R.drawable.side_pocket_04);
                    }
                });
                LL5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_05));
                        drawable_to_base64(R.drawable.side_pocket_05);
                    }
                });
                LL6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_06));
                        drawable_to_base64(R.drawable.side_pocket_06);
                    }
                });
                LL7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_07));
                        drawable_to_base64(R.drawable.side_pocket_07);
                    }
                });
                LL8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_08));
                        drawable_to_base64(R.drawable.side_pocket_08);
                    }
                });
                LL9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_09));
                        drawable_to_base64(R.drawable.side_pocket_09);
                    }
                });
                LL10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_10));
                        drawable_to_base64(R.drawable.side_pocket_10);
                    }
                });
                LL11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_11));
                        drawable_to_base64(R.drawable.side_pocket_11);
                    }
                });
                LL12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        chooseSidePocketImage.setImageDrawable(getResources().getDrawable(R.drawable.side_pocket_12));
                        drawable_to_base64(R.drawable.side_pocket_12);
                    }
                });

                dialog.show();
            }
        });

        ArrayAdapter<String> adap = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, (downOptions));
        adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_down_shoulder_varieties.setAdapter(adap);

    }

    private void createSherwaniRequest() {

        SherwaniRequestBody sherwaniRequestBody = new SherwaniRequestBody();


        // customer fields..
        sherwaniRequestBody.setCustomer_name(TextUtils.isEmpty(customer_name.getText().toString()) ? "" : customer_name.getText().toString() + ":  کسٹمر کا نام ");
        sherwaniRequestBody.setMobile_number(TextUtils.isEmpty(mobile_number.getText().toString()) ? "" : mobile_number.getText().toString() + ": کسٹمر کا نمبر  ");
        sherwaniRequestBody.setOrder_number(TextUtils.isEmpty(order_number.getText().toString()) ? "" : order_number.getText().toString() + ": آرڈر  نمبر  ");
        sherwaniRequestBody.setOrder_date(TextUtils.isEmpty(order_date.getText().toString()) ? "" : " آرڈر کی تاریخ : " + order_date.getText().toString());


        //et fields
        sherwaniRequestBody.setQuantity(TextUtils.isEmpty(quantity.getText().toString()) ? "" : quantity.getText().toString() + ": عدد ");
        sherwaniRequestBody.setCollar(TextUtils.isEmpty(collar.getText().toString()) ? "" : collar.getText().toString() + " : کالر ");
        sherwaniRequestBody.setSleeves(TextUtils.isEmpty(sleeves.getText().toString()) ? "" : sleeves.getText().toString() + ": آستین  ");
        sherwaniRequestBody.setShoulder(TextUtils.isEmpty(shoulder.getText().toString()) ? "" : shoulder.getText().toString() + ": شولڈر  ");
        sherwaniRequestBody.setHip(TextUtils.isEmpty(hip.getText().toString()) ? "" : hip.getText().toString() + " : ہپ تیار ");
        sherwaniRequestBody.setGudda(TextUtils.isEmpty(gudda.getText().toString()) ? "" : gudda.getText().toString() + ": گڈہ تیار ");
        //sherwaniRequestBody.setFront(TextUtils.isEmpty(front.getText().toString()) ? "" : front.getText().toString() + " : سامنا تیار  ");
        sherwaniRequestBody.setLengthMade(TextUtils.isEmpty(lengthMade.getText().toString()) ? "" : lengthMade.getText().toString() + ": لمبائ ");

        sherwaniRequestBody.setChest(TextUtils.isEmpty(chest.getText().toString()) ? "" : chest.getText().toString() + " : سینہ");
        sherwaniRequestBody.setAbdomen(TextUtils.isEmpty(abdomen.getText().toString()) ? "" : abdomen.getText().toString() + " : پیٹ ");
        sherwaniRequestBody.setFullback(TextUtils.isEmpty(fullback.getText().toString()) ? "" : fullback.getText().toString() + " : فل بیک ");
        sherwaniRequestBody.setHalfback(TextUtils.isEmpty(halfback.getText().toString()) ? "" : halfback.getText().toString() + " :  ہالف بیک ");
        sherwaniRequestBody.setCrossfront(TextUtils.isEmpty(crossfront.getText().toString()) ? "" : crossfront.getText().toString() + " : کراس فرنٹ ");
        sherwaniRequestBody.setHighlight_cadge_color(TextUtils.isEmpty(highlight_cadge_color.getText().toString()) ? "" : "کلر سے" + highlight_cadge_color.getText().toString() + " : سامنے پہ ہائ لیٹ کاج ہوگے");
        sherwaniRequestBody.setShow_cadge_color(TextUtils.isEmpty(show_cadge_color.getText().toString()) ? "" : " : کلر کے شو کاج ہوں گے" + show_cadge_color.getText().toString());
        sherwaniRequestBody.setFront_cadge_numbers(TextUtils.isEmpty(front_cadge_numbers.getText().toString()) ? "" : " سامنے پہ " + front_cadge_numbers.getText().toString() + " عدد کاج ہونگے ");
        sherwaniRequestBody.setContrast_color_astar(TextUtils.isEmpty(contrast_color_astar.getText().toString()) ? "" : " کنٹراس " + contrast_color_astar.getText().toString() + "کلر کا استر لگانا ہے (ایک نمبر) چیری");

        //urgent time and date...
        sherwaniRequestBody.setUrgent_order_date(TextUtils.isEmpty(urgent_order_date.getText().toString()) ? "" : " ارجنٹ بروز " + urgent_order_date.getText().toString() + " کو چاہیے " + "آرڈر" + urgent_order_time.getText().toString() + " بجے تک لازمی");
        sherwaniRequestBody.setUrgent_order_time(TextUtils.isEmpty(order_date_most_urgent.getText().toString()) ? "" : " انتہائ ارجنٹ بروز " + order_date_most_urgent.getText().toString() + " کو چاہیے " + "آرڈر" + is_most_urgent.getText().toString() + " بجے تک لازمی");


        //check boxes field
        sherwaniRequestBody.setJinnah_style(jinnah_style.isChecked() ? jinnah_style.getText().toString() : "");
        sherwaniRequestBody.setHighlight_cadge_color(highlight_cadge_matching.isChecked() ? highlight_cadge_matching.getText().toString() : "");
        sherwaniRequestBody.setFancy_metal_button(fancy_metal_button.isChecked() ? fancy_metal_button.getText().toString() : "");
        sherwaniRequestBody.setShow_button(show_button.isChecked() ? show_button.getText().toString() : "");
        sherwaniRequestBody.setShow_cadge_color(show_cadge_matching.isChecked() ? show_cadge_matching.getText().toString() : "");
        sherwaniRequestBody.setSide_pocket_both_sides(side_pocket_both_sides.isChecked() ? side_pocket_both_sides.getText().toString() : "");
        sherwaniRequestBody.setTwo_pockets(two_pockets.isChecked() ? two_pockets.getText().toString() : "");
        sherwaniRequestBody.setNo_tal_pat(no_tal_pat.isChecked() ? no_tal_pat.getText().toString() : "");
        sherwaniRequestBody.setBoth_front_equal(both_front_equal.isChecked() ? both_front_equal.getText().toString() : "");
        sherwaniRequestBody.setBack_center_sew(back_center_sew.isChecked() ? back_center_sew.getText().toString() : "");
        sherwaniRequestBody.setFront_walnut_pocket(front_walnut_pocket.isChecked() ? front_walnut_pocket.getText().toString() : "");
        sherwaniRequestBody.setOpen_front_open_gown_style(open_front_open_gown_style.isChecked() ? open_front_open_gown_style.getText().toString() : "");
        sherwaniRequestBody.setCollar_karhayi(collar_karhayi.isChecked() ? collar_karhayi.getText().toString() : "");
        sherwaniRequestBody.setSleeves_karhayi(sleeves_karhayi.isChecked() ? sleeves_karhayi.getText().toString() : "");
        sherwaniRequestBody.setButton_karhayi(button_karhayi.isChecked() ? button_karhayi.getText().toString() : "");
        sherwaniRequestBody.setOne_front_karhayi(one_front_karhayi.isChecked() ? one_front_karhayi.getText().toString() : "");
        sherwaniRequestBody.setBoth_front_karhayi(both_front_karhayi.isChecked() ? both_front_karhayi.getText().toString() : "");
        sherwaniRequestBody.setBack_karhayi(back_karhayi.isChecked() ? back_karhayi.getText().toString() : "");
        sherwaniRequestBody.setCollar_sleeves_karhayi(collar_sleeves_karhayi.isChecked() ? collar_sleeves_karhayi.getText().toString() : "");
        sherwaniRequestBody.setViolet_pocket_karhayi(violet_pocket_karhayi.isChecked() ? violet_pocket_karhayi.getText().toString() : "");
        sherwaniRequestBody.setCollar_sleeves_one_front_karhayi(collar_sleeves_one_front_karhayi.isChecked() ? collar_sleeves_one_front_karhayi.getText().toString() : "");
        sherwaniRequestBody.setFront_pocket_karhayi(front_pocket_karhayi.isChecked() ? front_pocket_karhayi.getText().toString() : "");
        sherwaniRequestBody.setCollar_sleeves_two_front_karhayi(collar_sleeves_two_front_karhayi.isChecked() ? collar_sleeves_two_front_karhayi.getText().toString() : "");
        sherwaniRequestBody.setFront_pocket_on_karhayi(front_pocket_on_karhayi.isChecked() ? front_pocket_on_karhayi.getText().toString() : "");
        sherwaniRequestBody.setAnger_khakhat_style_sherwani(anger_khakhat_style_sherwani.isChecked() ? anger_khakhat_style_sherwani.getText().toString() : "");
        sherwaniRequestBody.setCross_style_sherwani(cross_style_sherwani.isChecked() ? cross_style_sherwani.getText().toString() : "");
        sherwaniRequestBody.setMatching_color_astar(matching_color_astar.isChecked() ? matching_color_astar.getText().toString() : "");

        //checkboxes for general fields

        sherwaniRequestBody.setCustomer_cloth(customer_cloth.isChecked() ? customer_cloth.getText().toString() : "");
        sherwaniRequestBody.setChild_shewrwani_size(child_shewrwani_size.isChecked() ? child_shewrwani_size.getText().toString() : "");
        sherwaniRequestBody.setOnly_sewing(only_sewing.isChecked() ? only_sewing.getText().toString() : "");
        sherwaniRequestBody.setFinished_adjust(finished_adjust.isChecked() ? finished_adjust.getText().toString() : "");
        sherwaniRequestBody.setSpecial_customer_order(special_customer_order.isChecked() ? special_customer_order.getText().toString() : "");
        sherwaniRequestBody.setRegular_customer_order(regular_customer_order.isChecked() ? regular_customer_order.getText().toString() : "");
        sherwaniRequestBody.setUrgent_order(urgent_order.isChecked() ? urgent_order.getText().toString() : "");
        sherwaniRequestBody.setNo_label(no_label.isChecked() ? no_label.getText().toString() : "");
        sherwaniRequestBody.setSpecial_order(special_order.isChecked() ? special_order.getText().toString() : "");
        sherwaniRequestBody.setButton_should_be_strong(button_should_be_strong.isChecked() ? button_should_be_strong.getText().toString() : "");
        sherwaniRequestBody.setLight_work_shoulder_down(light_work_shoulder_down.isChecked() ? light_work_shoulder_down.getText().toString() : "");
        sherwaniRequestBody.setFull_shoulder_down(dropdown_down_shoulder_varieties.getSelectedItem().toString().equals("شولڈر کا انتخاب کیجئے") ? "" : dropdown_down_shoulder_varieties.getSelectedItem().toString());
        sherwaniRequestBody.setStraight_shoulder(straight_shoulder.isChecked() ? straight_shoulder.getText().toString() : "");
        sherwaniRequestBody.setRight_shoulder_down(right_shoulder_down.isChecked() ? right_shoulder_down.getText().toString() : "");
        sherwaniRequestBody.setLeft_shoulder_down(left_shoulder_down.isChecked() ? left_shoulder_down.getText().toString() : "");
        sherwaniRequestBody.setAltered_body(altered_body.isChecked() ? altered_body.getText().toString() : "");
        sherwaniRequestBody.setDeep_body(deep_body.isChecked() ? deep_body.getText().toString() : "");
        sherwaniRequestBody.setParty_label(party_label.isChecked() ? party_label.getText().toString() : "");
        sherwaniRequestBody.setFancy_label(fancy_label.isChecked() ? fancy_label.getText().toString() : "");

        //sendimages

        //send Images...

        sherwaniRequestBody.setCuff_image("");
        sherwaniRequestBody.setCollar_image(TextUtils.isEmpty(collar_image) ? "" : collar_image.toString());
        sherwaniRequestBody.setCustomer_image(TextUtils.isEmpty(image_4_db) ? "" : image_4_db);
        sherwaniRequestBody.setSide_pocket_image(TextUtils.isEmpty(sidepocket_image) ? "" : sidepocket_image);


        //sherwaniRequestBody.setShalwar(dropdown_shalwar_name.getSelectedItem().toString());
        sherwaniRequestBody.setKarigar(TextUtils.isEmpty(karigar_name.getText().toString()) ? "" : karigar_name.getText().toString() + " :  کاریگر کا نام ");
        //    sherwaniRequestBody.setKurta_type(dropdown_kurta_varieties.getSelectedItem().toString());
        //chooseSidePocketImage ; image_4_db;

        alerter.setTitle("انتطار فرمائیے۔۔۔")
                .setText("کسٹمر کا آرڈر بن رہا ہے۔۔۔")
                .setIcon(R.drawable.dulha_jee_logo)
                .setBackgroundColorRes(R.color.black).show();


        //Api call here...

        iapi.createSherwani("Bearer " + sharedPreference.getToken(), sherwaniRequestBody).enqueue(new Callback<HtmlResponseBody>() {
            @Override
            public void onResponse(Call<HtmlResponseBody> call, Response<HtmlResponseBody> response) {
                if (response.isSuccessful()) {
                    //  Toast.makeText(getActivity(), "Success...", Toast.LENGTH_SHORT).show();
                    html_url = response.body().getUrl();
                    doWebViewPrint();
                    Alerter.hide();
                } else {
                    Toast.makeText(getActivity(), response.message().toString(), Toast.LENGTH_SHORT).show();
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
