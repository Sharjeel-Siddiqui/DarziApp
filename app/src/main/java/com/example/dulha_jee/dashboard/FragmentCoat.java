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
import com.example.dulha_jee.pojo.CoatRequestBody;
import com.example.dulha_jee.pojo.GetUserResponseBody;
import com.example.dulha_jee.pojo.HtmlResponseBody;
import com.example.dulha_jee.pojo.KurtaRequestBody;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class FragmentCoat extends Fragment implements DatePickerDialog.OnDateSetListener {
    Spinner dropdown_karegar_name, dropdown_coat_varieties;
    String[] karegarName = {" کاریگر کا نام", "ابرار ", "احمد ", "امین ", "عارف "};
    String[] coatVarieties = {"گون اسٹائل فرنٹ اوپن کوٹ ", "پرنس کوٹ ", "کوٹ "};
    Button submit_coat, chooseImage;
    NavController navController;
    ImageView iv_01;
    Uri imageUri;
    public static final int PICK_IMAGE = 1;
    Iapi iapi;
    SharedPreference sharedPreference;
    private WebView mWebView;
    String image_4_db;
    public String collar_image, sidepocket_image;
    public String html_url;
    Alerter alerter;


    @BindView(R.id.chooseOrderDate)
    Button chooseOrderDate;

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
    @BindView(R.id.is_most_urgent)
    EditText is_most_urgent;

    @BindView(R.id.urgent_order_date)
    EditText urgent_order_date;
    @BindView(R.id.urgent_order_time)
    EditText urgent_order_time;


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
        ButterKnife.bind(this, view);
        chooseImage = view.findViewById(R.id.chooseImage);
        iv_01 = view.findViewById(R.id.iv_01);
        iapi = ApiClient.getClient().create(Iapi.class);
        sharedPreference = new SharedPreference(getActivity());
        alerter = Alerter.create(getActivity());

        chooseOrderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.setTargetFragment(FragmentCoat.this, 0);
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
        dropdown_coat_varieties = view.findViewById(R.id.dropdown_coat_varieties);
        submit_coat = view.findViewById(R.id.submit_coat);

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, karegarName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_karegar_name.setAdapter(adapter);*/

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

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, coatVarieties);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_coat_varieties.setAdapter(adapter1);

        submit_coat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createCoatRequest();

            }
        });
    }

    private void createCoatRequest() {
        CoatRequestBody coatRequestBody = new CoatRequestBody();

        //et_fields....
        // customer fields..
        coatRequestBody.setCustomer_name(TextUtils.isEmpty(customer_name.getText().toString()) ? "" : customer_name.getText().toString() + ":  کسٹمر کا نام ");
        coatRequestBody.setMobile_number(TextUtils.isEmpty(customer_number.getText().toString()) ? "" : customer_number.getText().toString() + ": کسٹمر کا نمبر  ");
        coatRequestBody.setOrder_number(TextUtils.isEmpty(order_number.getText().toString()) ? "" : order_number.getText().toString() + ": آرڈر  نمبر  ");
        coatRequestBody.setOrder_date(TextUtils.isEmpty(order_date.getText().toString()) ? "" : " آرڈر کی تاریخ : " + order_date.getText().toString());


        coatRequestBody.setQuantity(TextUtils.isEmpty(quantity.getText().toString()) ? "" : quantity.getText().toString() + ": عدد ");
        coatRequestBody.setCollar(TextUtils.isEmpty(collar.getText().toString()) ? "" : collar.getText().toString() + " : کالر ");
        coatRequestBody.setSleeves(TextUtils.isEmpty(sleeves.getText().toString()) ? "" : sleeves.getText().toString() + ": آستین  ");
        coatRequestBody.setShoulder(TextUtils.isEmpty(shoulder.getText().toString()) ? "" : shoulder.getText().toString() + ": شولڈر  ");
        coatRequestBody.setHip(TextUtils.isEmpty(hip.getText().toString()) ? "" : hip.getText().toString() + " : ہپ تیار ");
        coatRequestBody.setGudda(TextUtils.isEmpty(gudda.getText().toString()) ? "" : gudda.getText().toString() + ": گڈہ تیار ");
        coatRequestBody.setLengthMade(TextUtils.isEmpty(lengthMade.getText().toString()) ? "" : lengthMade.getText().toString() + ": لمبائ ");
        coatRequestBody.setAbdomen(TextUtils.isEmpty(abdomen.getText().toString()) ? "" : abdomen.getText().toString() + " : پیٹ ");
        coatRequestBody.setChest(TextUtils.isEmpty(chest.getText().toString()) ? "" :  " سینہ : " + chest.getText().toString());
        coatRequestBody.setFull_back(TextUtils.isEmpty(full_back.getText().toString()) ? "" :  " : فل بیک " + full_back.getText().toString() );
        coatRequestBody.setHalfback(TextUtils.isEmpty(halfback.getText().toString()) ? "" : " : ہالف بیک " + halfback.getText().toString()  );
        coatRequestBody.setCrossfront(TextUtils.isEmpty(crossfront.getText().toString()) ? "" :  " : کراس فرنٹ " + crossfront.getText().toString()  );
        coatRequestBody.setPencil_length(TextUtils.isEmpty(pencil_length.getText().toString()) ? "" : "پینسل کی چوڑائی" + pencil_length.getText().toString() + "انچ کی رکھنی ہے");
        coatRequestBody.setChowk_length(TextUtils.isEmpty(chowk_length.getText().toString()) ? "" : "چاک کی لمبائی" + chowk_length.getText().toString() + "انچ کی رکھنی ہے");
        coatRequestBody.setCollar_thing(TextUtils.isEmpty(collar_thing.getText().toString()) ? "" : " کالر میں " + collar_thing.getText().toString() + " لگانی ہے ");
        coatRequestBody.setPencil_thing(TextUtils.isEmpty(pencil_thing.getText().toString()) ? "" : " پینسل میں " + pencil_thing.getText().toString() + " لگانی ہے");
        coatRequestBody.setShoulder_depth(TextUtils.isEmpty(shoulder_depth.getText().toString()) ? "" : " کوٹ کی گلے کی گہرائی شولڈر کی سلائ سے" + shoulder_depth.getText().toString() + "انچ کی رکھنی ہے");
        coatRequestBody.setFront_cadge(TextUtils.isEmpty(front_cadge.getText().toString()) ? "" : "سامنے پہ" + front_cadge.getText().toString() + "عدد کاج ہونگے");
        coatRequestBody.setRemarks(TextUtils.isEmpty(remarks.getText().toString()) ? "" : remarks.getText().toString());

        //urgent time and date...
        coatRequestBody.setUrgent_order_date(TextUtils.isEmpty(urgent_order_date.getText().toString()) ? "" : " ارجنٹ بروز " + urgent_order_date.getText().toString() + " کو چاہیے " + "آرڈر" + urgent_order_time.getText().toString() + " بجے تک لازمی");
        coatRequestBody.setUrgent_order_time(TextUtils.isEmpty(order_date_most_urgent.getText().toString()) ? "" : " انتہائ ارجنٹ بروز " + order_date_most_urgent.getText().toString() + " کو چاہیے " + "آرڈر" + is_most_urgent.getText().toString() + " بجے تک لازمی");


        //Check boxes field goes here ....
        coatRequestBody.setButton_style2(button_style2.isChecked() ? button_style2.getText().toString() : "");
        coatRequestBody.setSingle_button(single_button.isChecked() ? single_button.getText().toString() : "");
        coatRequestBody.setThree_button(three_button.isChecked() ? three_button.getText().toString() : "");
        coatRequestBody.setFour_button(four_button.isChecked() ? four_button.getText().toString() : "");
        coatRequestBody.setFive_button(five_button.isChecked() ? five_button.getText().toString() : "");
        coatRequestBody.setNo_button(no_button.isChecked() ? no_button.getText().toString() : "");
        coatRequestBody.setSide_chowk(side_chowk.isChecked() ? side_chowk.getText().toString() : "");
        coatRequestBody.setBack_chowk(back_chowk.isChecked() ? back_chowk.getText().toString() : "");
        coatRequestBody.setAmerican_pencil(american_pencil.isChecked() ? american_pencil.getText().toString() : "");
        coatRequestBody.setDinner_pencil(dinner_pencil.isChecked() ? dinner_pencil.getText().toString() : "");
        coatRequestBody.setPencil_long(pencil_long.isChecked() ? pencil_long.getText().toString() : "");
        coatRequestBody.setTaxido_style(taxido_style.isChecked() ? taxido_style.getText().toString() : "");
        coatRequestBody.setQub(qub.isChecked() ? qub.getText().toString() : "");
        coatRequestBody.setNo_meat(no_meat.isChecked() ? no_meat.getText().toString() : "");
        coatRequestBody.setWaist_coat_double(waist_coat_double.isChecked() ? waist_coat_double.getText().toString() : "");
        coatRequestBody.setPencil_waist_coat(pencil_waist_coat.isChecked() ? pencil_waist_coat.getText().toString() : "");
        coatRequestBody.setV_neck_waist_coat(v_neck_waist_coat.isChecked() ? v_neck_waist_coat.getText().toString() : "");
        coatRequestBody.setU_neck_waist_coat(u_neck_waist_coat.isChecked() ? u_neck_waist_coat.getText().toString() : "");
        coatRequestBody.setSame_as_image(same_as_image.isChecked() ? same_as_image.getText().toString() : "");
        coatRequestBody.setCoat_same_as_image(coat_same_as_image.isChecked() ? coat_same_as_image.getText().toString() : "");
        coatRequestBody.setWaist_coat_same_as_image(waist_coat_same_as_image.isChecked() ? waist_coat_same_as_image.getText().toString() : "");
        coatRequestBody.setInner_loozing(inner_loozing.isChecked() ? inner_loozing.getText().toString() : "");
        coatRequestBody.setSpecial_vip(special_vip.isChecked() ? special_vip.getText().toString() : "");

        //  coatRequestBody.setsleeveloozing(sleeves_loozing.isChecked() ? sleeves_loozing.getText().toString() : "");
        coatRequestBody.setDouble_bukram(double_bukram.isChecked() ? double_bukram.getText().toString() : "");
        coatRequestBody.setFull_bukram(full_bukram.isChecked() ? full_bukram.getText().toString() : "");
        coatRequestBody.setAstar_printed(astar_printed.isChecked() ? astar_printed.getText().toString() : "");
        coatRequestBody.setPrince_coat(prince_coat.isChecked() ? prince_coat.getText().toString() : "");
        coatRequestBody.setFancy_buttons(fancy_buttons.isChecked() ? fancy_buttons.getText().toString() : "");

        //checkboxes for general fields

        coatRequestBody.setCustomer_cloth(customer_cloth.isChecked() ? customer_cloth.getText().toString() : "");
        coatRequestBody.setChild_kurta_size(child_kurta_size.isChecked() ? child_kurta_size.getText().toString() : "");
        coatRequestBody.setOnly_sewing(only_sewing.isChecked() ? only_sewing.getText().toString() : "");
        coatRequestBody.setFinished_adjust(finished_adjust.isChecked() ? finished_adjust.getText().toString() : "");
        coatRequestBody.setSpecial_customer_order(special_customer_order.isChecked() ? special_customer_order.getText().toString() : "");
        coatRequestBody.setRegular_customer_order(regular_customer_order.isChecked() ? regular_customer_order.getText().toString() : "");
        coatRequestBody.setUrgent_order(urgent_order.isChecked() ? urgent_order.getText().toString() : "");
        coatRequestBody.setNo_label(no_label.isChecked() ? no_label.getText().toString() : "");
        coatRequestBody.setSpecial_order(special_order.isChecked() ? special_order.getText().toString() : "");
        coatRequestBody.setButton_should_be_strong(button_should_be_strong.isChecked() ? button_should_be_strong.getText().toString() : "");
        coatRequestBody.setLight_work_shoulder_down(light_work_shoulder_down.isChecked() ? light_work_shoulder_down.getText().toString() : "");
        coatRequestBody.setFull_shoulder_down(full_shoulder_down.isChecked() ? full_shoulder_down.getText().toString() : "");
        coatRequestBody.setStraight_shoulder(straight_shoulder.isChecked() ? straight_shoulder.getText().toString() : "");
        coatRequestBody.setRight_shoulder_down(right_shoulder_down.isChecked() ? right_shoulder_down.getText().toString() : "");
        coatRequestBody.setLeft_shoulder_down(left_shoulder_down.isChecked() ? left_shoulder_down.getText().toString() : "");
        coatRequestBody.setAltered_body(altered_body.isChecked() ? altered_body.getText().toString() : "");
        coatRequestBody.setDeep_body(deep_body.isChecked() ? deep_body.getText().toString() : "");
        coatRequestBody.setParty_label(party_label.isChecked() ? party_label.getText().toString() : "");
        coatRequestBody.setFancy_label(fancy_label.isChecked() ? fancy_label.getText().toString() : "");

        //image come here
        coatRequestBody.setCuff_image("");
        coatRequestBody.setCollar_image(TextUtils.isEmpty(collar_image) ? "" : collar_image.toString());
        coatRequestBody.setCustomer_image(TextUtils.isEmpty(image_4_db) ? "" : image_4_db);
        coatRequestBody.setSide_pocket_image(TextUtils.isEmpty(sidepocket_image) ? "" : sidepocket_image);


        // coatRequestBody.setShalwar(dropdown_shalwar_name.getSelectedItem().toString());
        coatRequestBody.setKarigar(dropdown_karegar_name.getSelectedItem().toString() + " :  کاریگر کا نام ");
        // coatRequestBody.setKurta_type(dropdown_kurta_varieties.getSelectedItem().toString());

        //Api call here...


        alerter.setTitle("انتطار فرمائیے۔۔۔")
                .setText("کسٹمر کا آرڈر بن رہا ہے۔۔۔")
                .setIcon(R.drawable.dulha_jee_logo)
                .setBackgroundColorRes(R.color.black).show();

        iapi.createCoat("Bearer " + sharedPreference.getToken(), coatRequestBody).enqueue(new Callback<HtmlResponseBody>() {
            @Override
            public void onResponse(Call<HtmlResponseBody> call, Response<HtmlResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Success..." + response.code(), Toast.LENGTH_SHORT).show();
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
