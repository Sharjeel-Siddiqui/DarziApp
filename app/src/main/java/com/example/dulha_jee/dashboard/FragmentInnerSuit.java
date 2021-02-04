package com.example.dulha_jee.dashboard;

import android.Manifest;
import android.app.DatePickerDialog;
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
import com.example.dulha_jee.pojo.InnerSuitRequestBody;
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

public class FragmentInnerSuit extends Fragment implements DatePickerDialog.OnDateSetListener {

    Spinner dropdown_karegar_name, dropdown_shalwar_name;
    NavController navController;
    String[] shalwar = {"شلوار", "اسٹریٹ پاجامہ", "چوڑی ڈار پاجامہ", "پینٹ اسٹائل پاجامہ", "دھوتی شلوار", "بڑے گھیر والی شلوار"};
    String[] karegarName = {" کاریگر کا نام", "ابرار ", "احمد ", "امین ", "عارف "};
    Button submit_innersuit;
    ImageView iv_01, chooseImage;
    Uri imageUri;
    public static final int PICK_IMAGE = 1;
    Iapi iapi;
    SharedPreference sharedPreference;
    private WebView mWebView;
    String image_4_db;
    public String collar_image, sidepocket_image;
    public String html_url;


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

    @BindView(R.id.chooseOrderDate)
    Button chooseOrderDate;

    Alerter alerter;


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
        iapi = ApiClient.getClient().create(Iapi.class);
        alerter = Alerter.create(getActivity());
        sharedPreference = new SharedPreference(getActivity());
        dropdown_karegar_name = view.findViewById(R.id.dropdown_karegar_name);
        dropdown_shalwar_name = view.findViewById(R.id.dropdown_shalwar_name);

        chooseOrderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getChildFragmentManager(), "date picker");
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

        submit_innersuit = view.findViewById(R.id.submit_innersuit);
        submit_innersuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createInnerSuitRequest();
            }
        });

        //karegar names
      /*  ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, karegarName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_karegar_name.setAdapter(adapter);
*/
        //drop down names Shalwar...
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, shalwar);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_shalwar_name.setAdapter(adapter1);
    }

    private void createInnerSuitRequest() {
        InnerSuitRequestBody innerSuitRequestBody = new InnerSuitRequestBody();

        //et fields
        innerSuitRequestBody.setCustomer_name(TextUtils.isEmpty(customer_name.getText().toString()) ? "" : customer_name.getText().toString() + "کسٹمر کا نام");
        innerSuitRequestBody.setMobile_number(TextUtils.isEmpty(mobile_number.getText().toString()) ? "" : mobile_number.getText().toString() + "کسٹمر کا نمبر");
        innerSuitRequestBody.setOrder_number(TextUtils.isEmpty(order_number.getText().toString()) ? "" : order_number.getText().toString() + "آرڈر  نمبر");
        innerSuitRequestBody.setOrder_date(TextUtils.isEmpty(order_date.getText().toString()) ? "" : order_date.getText().toString() + "آرڈر کی تاریخ");

        innerSuitRequestBody.setQuantity(TextUtils.isEmpty(quantity.getText().toString()) ? "" : quantity.getText().toString() + "عدد/Quantity");
        innerSuitRequestBody.setCollar(TextUtils.isEmpty(collar.getText().toString()) ? "" : collar.getText().toString() + "کالر/Collar ");
        innerSuitRequestBody.setSleeves(TextUtils.isEmpty(sleeves.getText().toString()) ? "" : sleeves.getText().toString() + "آستین/Sleeves");
        innerSuitRequestBody.setShoulder(TextUtils.isEmpty(shoulder.getText().toString()) ? "" : shoulder.getText().toString() + "شولڈر/Shoulder");
        innerSuitRequestBody.setHip(TextUtils.isEmpty(hip.getText().toString()) ? "" : hip.getText().toString() + "ہپ تیار/Hip Ready \"");
        innerSuitRequestBody.setGudda(TextUtils.isEmpty(gudda.getText().toString()) ? "" : gudda.getText().toString() + "گڈہ تیار ");
        innerSuitRequestBody.setFront(TextUtils.isEmpty(front.getText().toString()) ? "" : front.getText().toString() + "سامنا تیار/Front Ready");
        innerSuitRequestBody.setLengthMade(TextUtils.isEmpty(lengthMade.getText().toString()) ? "" : lengthMade.getText().toString() + "لمبائ/Length");
        innerSuitRequestBody.setAbdomen(TextUtils.isEmpty(abdomen.getText().toString()) ? "" : abdomen.getText().toString() + "پیٹ تیار/Waist Ready ");
        innerSuitRequestBody.setShalwar_gher(TextUtils.isEmpty(shalwar_gher.getText().toString()) ? "" : "\"انچ کا چاہیے تیار" + shalwar_gher.getText().toString() + "شلوار کا گھیر ایک سائیڈ کا");
        innerSuitRequestBody.setShalwar_asan(TextUtils.isEmpty(shalwar_asan.getText().toString()) ? "" : "انچ کا چاہیے تیار" + shalwar_asan.getText().toString() + "شلوار کاآسن");
        innerSuitRequestBody.setPajama_inner_fold(TextUtils.isEmpty(pajama_inner_fold.getText().toString()) ? "" : "انچ چوڑی فولڈ کرنی ہے باہر کی طرف" + pajama_inner_fold.getText().toString() + "پاجامے کی سوری");
        innerSuitRequestBody.setPajama_outer_fold(TextUtils.isEmpty(pajama_outer_fold.getText().toString()) ? "" : "انچ چوڑی فولڈ کرنی ہے اندر کی طرف" + pajama_outer_fold.getText().toString() + "پاجامے کی سوری");
        innerSuitRequestBody.setRemarks(TextUtils.isEmpty(remarks.getText().toString()) ? "" : remarks.getText().toString());

        //urgent time and date...
        innerSuitRequestBody.setUrgent_order_date(TextUtils.isEmpty(urgent_order_date.getText().toString()) ? "" : "کو چاہیے" + urgent_order_date.getText().toString() + "ارجنٹ بروز");
        innerSuitRequestBody.setUrgent_order_time(TextUtils.isEmpty(urgent_order_time.getText().toString()) ? "" : " بجے تک لازمی" + urgent_order_time.getText().toString() + "آرڈر ");


        //CheckBOxes Come here

        innerSuitRequestBody.setOff_white_color(off_white_colorl.isChecked() ? off_white_colorl.getText().toString() : "");
        innerSuitRequestBody.setBlack_color(black_color.isChecked() ? black_color.getText().toString() : "");
        innerSuitRequestBody.setMehroon_color(mehroon_color.isChecked() ? mehroon_color.getText().toString() : "");
        innerSuitRequestBody.setRedish_maroon(redish_maroon.isChecked() ? redish_maroon.getText().toString() : "");
        innerSuitRequestBody.setGolden_color(golden_color.isChecked() ? golden_color.getText().toString() : "");
        innerSuitRequestBody.setCream_color(cream_color.isChecked() ? cream_color.getText().toString() : "");
        innerSuitRequestBody.setCopper_color(copper_color.isChecked() ? copper_color.getText().toString() : "");
        innerSuitRequestBody.setDark_brown_color(dark_brown_color.isChecked() ? dark_brown_color.getText().toString() : "");
        innerSuitRequestBody.setGray_color(gray_color.isChecked() ? gray_color.getText().toString() : "");
        innerSuitRequestBody.setMatching_button(matching_button.isChecked() ? matching_button.getText().toString() : "");
        innerSuitRequestBody.setBrass_button(brass_button.isChecked() ? brass_button.getText().toString() : "");
        innerSuitRequestBody.setCopper_color_button(copper_color_button.isChecked() ? copper_color_button.getText().toString() : "");
        innerSuitRequestBody.setSilver_color_button(silver_color_button.isChecked() ? silver_color_button.getText().toString() : "");
        innerSuitRequestBody.setGold_color_button(gold_color_button.isChecked() ? gold_color_button.getText().toString() : "");
        innerSuitRequestBody.setPurple_color_button(purple_color_button.isChecked() ? purple_color_button.getText().toString() : "");
        innerSuitRequestBody.setRound_button(round_button.isChecked() ? round_button.getText().toString() : "");
        innerSuitRequestBody.setFancy_button(fancy_button.isChecked() ? fancy_button.getText().toString() : "");
        innerSuitRequestBody.setStrong_button(strong_button.isChecked() ? strong_button.getText().toString() : "");
        innerSuitRequestBody.setContrass_button(contrass_button.isChecked() ? contrass_button.getText().toString() : "");
        innerSuitRequestBody.setContrass_cadge(contrass_cadge.isChecked() ? contrass_cadge.getText().toString() : "");
        innerSuitRequestBody.setNaifa_chirya(naifa_chirya.isChecked() ? naifa_chirya.getText().toString() : "");
        innerSuitRequestBody.setMatching_zip(matching_zip.isChecked() ? matching_zip.getText().toString() : "");
        innerSuitRequestBody.setQuality_zip(quality_zip.isChecked() ? quality_zip.getText().toString() : "");
        innerSuitRequestBody.setQameez_zip(qameez_zip.isChecked() ? qameez_zip.getText().toString() : "");
        innerSuitRequestBody.setShalwar_romali(shalwar_romali.isChecked() ? shalwar_romali.getText().toString() : "");
        innerSuitRequestBody.setPaincha_style(paincha_style.isChecked() ? paincha_style.getText().toString() : "");
        innerSuitRequestBody.setPaint_style(paint_style.isChecked() ? paint_style.getText().toString() : "");
        innerSuitRequestBody.setSide_pocket(side_pocket.isChecked() ? side_pocket.getText().toString() : "");
        innerSuitRequestBody.setHalf_lastic(half_lastic.isChecked() ? half_lastic.getText().toString() : "");
        innerSuitRequestBody.setFull_lastic(full_lastic.isChecked() ? full_lastic.getText().toString() : "");
        innerSuitRequestBody.setHalf_lastic_with_kamarband(half_lastic_with_kamarband.isChecked() ? half_lastic_with_kamarband.getText().toString() : "");
        innerSuitRequestBody.setPajama_as_pic(pajama_as_pic.isChecked() ? pajama_as_pic.getText().toString() : "");
        innerSuitRequestBody.setPajama_roomali(pajama_roomali.isChecked() ? pajama_roomali.getText().toString() : "");
        innerSuitRequestBody.setChooridar_pajama(chooridar_pajama.isChecked() ? chooridar_pajama.getText().toString() : "");
        innerSuitRequestBody.setArha_pajama(arha_pajama.isChecked() ? arha_pajama.getText().toString() : "");
        innerSuitRequestBody.setStraight_pajama(straight_pajama.isChecked() ? straight_pajama.getText().toString() : "");
        innerSuitRequestBody.setCustomer_fat(customer_fat.isChecked() ? customer_fat.getText().toString() : "");
        innerSuitRequestBody.setPajama_fit(pajama_fit.isChecked() ? pajama_fit.getText().toString() : "");
        innerSuitRequestBody.setCustomer_slim(customer_slim.isChecked() ? customer_slim.getText().toString() : "");


        //checkboxes for general fields

        innerSuitRequestBody.setCustomer_cloth(customer_cloth.isChecked() ? customer_cloth.getText().toString() : "");
        innerSuitRequestBody.setChild_kurta_size(child_kurta_size.isChecked() ? child_kurta_size.getText().toString() : "");
        innerSuitRequestBody.setOnly_sewing(only_sewing.isChecked() ? only_sewing.getText().toString() : "");
        innerSuitRequestBody.setFinished_adjust(finished_adjust.isChecked() ? finished_adjust.getText().toString() : "");
        innerSuitRequestBody.setSpecial_customer_order(special_customer_order.isChecked() ? special_customer_order.getText().toString() : "");
        innerSuitRequestBody.setRegular_customer_order(regular_customer_order.isChecked() ? regular_customer_order.getText().toString() : "");
        innerSuitRequestBody.setUrgent_order(urgent_order.isChecked() ? urgent_order.getText().toString() : "");
        innerSuitRequestBody.setNo_label(no_label.isChecked() ? no_label.getText().toString() : "");
        innerSuitRequestBody.setSpecial_order(special_order.isChecked() ? special_order.getText().toString() : "");
        innerSuitRequestBody.setButton_should_be_strong(button_should_be_strong.isChecked() ? button_should_be_strong.getText().toString() : "");
        innerSuitRequestBody.setLight_work_shoulder_down(light_work_shoulder_down.isChecked() ? light_work_shoulder_down.getText().toString() : "");
        innerSuitRequestBody.setFull_shoulder_down(full_shoulder_down.isChecked() ? full_shoulder_down.getText().toString() : "");
        innerSuitRequestBody.setStraight_shoulder(straight_shoulder.isChecked() ? straight_shoulder.getText().toString() : "");
        innerSuitRequestBody.setRight_shoulder_down(right_shoulder_down.isChecked() ? right_shoulder_down.getText().toString() : "");
        innerSuitRequestBody.setLeft_shoulder_down(left_shoulder_down.isChecked() ? left_shoulder_down.getText().toString() : "");
        innerSuitRequestBody.setAltered_body(altered_body.isChecked() ? altered_body.getText().toString() : "");
        innerSuitRequestBody.setDeep_body(deep_body.isChecked() ? deep_body.getText().toString() : "");
        innerSuitRequestBody.setParty_label(party_label.isChecked() ? party_label.getText().toString() : "");
        innerSuitRequestBody.setFancy_label(fancy_label.isChecked() ? fancy_label.getText().toString() : "");

        //send images here
        // image_4_db

        innerSuitRequestBody.setCustomer_image(TextUtils.isEmpty(image_4_db) ? "" : image_4_db);


        innerSuitRequestBody.setShalwar(dropdown_shalwar_name.getSelectedItem().toString());
        innerSuitRequestBody.setKarigar(dropdown_karegar_name.getSelectedItem().toString());

        alerter.setTitle("انتطار فرمائیے۔۔۔")
                .setText("کسٹمر کا آرڈر بن رہا ہے۔۔۔")
                .setIcon(R.drawable.dulha_jee_logo)
                .setBackgroundColorRes(R.color.black).show();


        //Api call here...
        iapi.createInnerSuit("Bearer " + sharedPreference.getToken(), innerSuitRequestBody).enqueue(new Callback<HtmlResponseBody>() {
            @Override
            public void onResponse(Call<HtmlResponseBody> call, Response<HtmlResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Success..." + response.code(), Toast.LENGTH_SHORT).show();
                    Log.i("TAG", "onResponse: " + response.message());
                    Log.i("TAG", "onResponse: " + response.raw());
                    //response.body().getUrl();
                    html_url = response.body().getUrl();
                    Alerter.hide();
                    doWebViewPrint();
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
}
