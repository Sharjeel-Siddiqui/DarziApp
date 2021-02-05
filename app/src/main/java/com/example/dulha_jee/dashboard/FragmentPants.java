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
import com.example.dulha_jee.pojo.PantPojo;
import com.example.dulha_jee.userlist.DatePickerFragment;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.tapadoo.alerter.Alert;
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

public class FragmentPants extends Fragment implements DatePickerDialog.OnDateSetListener {
    Spinner dropdown_karegar_name;
    String[] users = {"کرتا شلوار", "کرتا پاجامہ", "قمیص شلوار", "فرنٹ اوپن کرتا"};
    String[] karegarName = {" کاریگر کا نام", "ابرار ", "احمد ", "امین ", "عارف "};
    Button submit_pants, chooseImage;
    NavController navController;
    ImageView iv_01;
    Uri imageUri;
    public static final int PICK_IMAGE = 1;
    Iapi iapi;
    SharedPreference sharedPreference;
    private WebView mWebView;
    String image_4_db;
    Alerter alerter;
    public String collar_image, sidepocket_image;
    public String html_url;

    @BindView(R.id.quantity)
    EditText quantity;
    @BindView(R.id.abdomen)
    EditText abdomen;
    @BindView(R.id.hip)
    EditText hip;
    @BindView(R.id.lengthMade)
    EditText lengthMade;
    @BindView(R.id.fly)
    EditText fly;
    @BindView(R.id.thigh)
    EditText thigh;
    @BindView(R.id.knee)
    EditText knee;
    @BindView(R.id.bottom)
    EditText bottom;
    @BindView(R.id.order_date)
    EditText order_date;
    @BindView(R.id.order_date_most_urgent)
    EditText order_date_most_urgent;
    @BindView(R.id.remarks)
    EditText remarks;

    //CheckBoxes come here

    @BindView(R.id.without_plate)
    CheckBox without_plate;
    @BindView(R.id.one_plate_front)
    CheckBox one_plate_front;
    @BindView(R.id.two_plate_front)
    CheckBox two_plate_front;
    @BindView(R.id.straight_pocket)
    CheckBox straight_pocket;
    @BindView(R.id.cross_pocket)
    CheckBox cross_pocket;
    @BindView(R.id.jeans_style_pocket)
    CheckBox jeans_style_pocket;
    @BindView(R.id.one_back_pocket)
    CheckBox one_back_pocket;
    @BindView(R.id.two_back_pocket)
    CheckBox two_back_pocket;
    @BindView(R.id.flap_pocket)
    CheckBox flap_pocket;
    @BindView(R.id.flap_cadge)
    CheckBox flap_cadge;
    @BindView(R.id.cotton)
    CheckBox cotton;
    @BindView(R.id.watch_pocket)
    CheckBox watch_pocket;
    @BindView(R.id.back_pocket_cadge_button)
    CheckBox back_pocket_cadge_button;
    @BindView(R.id.eight_loobs)
    CheckBox eight_loobs;
    @BindView(R.id.loobs_inch)
    CheckBox loobs_inch;
    @BindView(R.id.loobs_inch_two)
    CheckBox loobs_inch_two;
    @BindView(R.id.back_pocket_loobs)
    CheckBox back_pocket_loobs;
    @BindView(R.id.inch_belt)
    CheckBox inch_belt;
    @BindView(R.id.belt_grip)
    CheckBox belt_grip;
    @BindView(R.id.pocket_thely)
    CheckBox pocket_thely;
    @BindView(R.id.zip_quality)
    CheckBox zip_quality;
    @BindView(R.id.pocket_dip)
    CheckBox pocket_dip;
    @BindView(R.id.folding_mori)
    CheckBox folding_mori;
    @BindView(R.id.turpayi_hand)
    CheckBox turpayi_hand;
    @BindView(R.id.long_loop)
    CheckBox long_loop;
    @BindView(R.id.long_nib)
    CheckBox long_nib;
    @BindView(R.id.paint_loozing)
    CheckBox paint_loozing;
    @BindView(R.id.special)
    CheckBox special;
    @BindView(R.id.same_as_image)
    CheckBox same_as_image;
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
    @BindView(R.id.customer_name)
    EditText customer_name;
    @BindView(R.id.customer_number)
    EditText customer_number;
    @BindView(R.id.order_number)
    EditText order_number;


    @BindView(R.id.chooseOrderDate)
    Button chooseOrderDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pant, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbar("Pants");
        ButterKnife.bind(this, view);
        navController = Navigation.findNavController(view);
        chooseImage = view.findViewById(R.id.chooseImage);
        dropdown_karegar_name = view.findViewById(R.id.dropdown_karegar_name);
        submit_pants = view.findViewById(R.id.submit_pants);
        chooseImage = view.findViewById(R.id.chooseImage);
        iv_01 = view.findViewById(R.id.iv_01);
        iapi = ApiClient.getClient().create(Iapi.class);
        sharedPreference = new SharedPreference(getActivity());
        alerter = Alerter.create(getActivity());
        sharedPreference = new SharedPreference(getActivity());

        chooseOrderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.setTargetFragment(FragmentPants.this, 0);
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

        submit_pants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPantRequest();
             /*   Alerter.create(getActivity())
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
                                navController.navigate(R.id.action_fragmentPants_to_dashBoard, null, new NavOptions.Builder()
                                        .setPopUpTo(R.id.fragmentPants,
                                                true).build());
                            }
                        })
                        .show();*/
            }
        });

       /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, karegarName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_karegar_name.setAdapter(adapter);*/
    }

    private void createPantRequest() {

        PantPojo pantPojo = new PantPojo();

        //et fields
        pantPojo.setCustomer_name(TextUtils.isEmpty(customer_name.getText().toString()) ? "" : customer_name.getText().toString() + "کسٹمر کا نام");
        pantPojo.setMobile_number(TextUtils.isEmpty(customer_number.getText().toString()) ? "" : customer_number.getText().toString() + "کسٹمر کا نمبر");
        pantPojo.setOrder_number(TextUtils.isEmpty(order_number.getText().toString()) ? "" : order_number.getText().toString() + "آرڈر  نمبر");
        pantPojo.setOrder_date(TextUtils.isEmpty(order_date.getText().toString()) ? "" : order_date.getText().toString() + "آرڈر کی تاریخ");

        pantPojo.setQuantity(TextUtils.isEmpty(quantity.getText().toString()) ? "" : quantity.getText().toString() + "عدد/Quantity  ");
        pantPojo.setAbdomen(TextUtils.isEmpty(abdomen.getText().toString()) ? "" : abdomen.getText().toString() + "ویسٹ/Waist ");
        pantPojo.setHip(TextUtils.isEmpty(hip.getText().toString()) ? "" : hip.getText().toString() + "ہپ/Hip");
        pantPojo.setLengthMade(TextUtils.isEmpty(lengthMade.getText().toString()) ? "" : lengthMade.getText().toString() + "لمبائ/Length ");
        pantPojo.setFly(TextUtils.isEmpty(fly.getText().toString()) ? "" : fly.getText().toString() + "فلائ/Fly");
        pantPojo.setThigh(TextUtils.isEmpty(thigh.getText().toString()) ? "" : thigh.getText().toString() + "ران/Thigh ");
        pantPojo.setKnee(TextUtils.isEmpty(knee.getText().toString()) ? "" : knee.getText().toString() + "گھٹنہ/Knee");
        pantPojo.setBottom(TextUtils.isEmpty(bottom.getText().toString()) ? "" : bottom.getText().toString() + "بوٹم/Bottom");


        pantPojo.setWithout_plate(without_plate.isChecked() ? without_plate.getText().toString() : "");
        pantPojo.setOne_plate_front(one_plate_front.isChecked() ? one_plate_front.getText().toString() : "");
        pantPojo.setTwo_plate_front(two_plate_front.isChecked() ? two_plate_front.getText().toString() : "");
        pantPojo.setStraight_pocket(straight_pocket.isChecked() ? straight_pocket.getText().toString() : "");
        pantPojo.setCross_pocket(cross_pocket.isChecked() ? cross_pocket.getText().toString() : "");
        pantPojo.setJeans_style_pocket(jeans_style_pocket.isChecked() ? jeans_style_pocket.getText().toString() : "");
        pantPojo.setOne_back_pocket(one_back_pocket.isChecked() ? one_back_pocket.getText().toString() : "");
        pantPojo.setTwo_back_pocket(two_back_pocket.isChecked() ? two_back_pocket.getText().toString() : "");
        pantPojo.setFlap_pocket(flap_pocket.isChecked() ? flap_pocket.getText().toString() : "");
        pantPojo.setFlap_cadge(flap_cadge.isChecked() ? flap_cadge.getText().toString() : "");
        pantPojo.setCotton(cotton.isChecked() ? cotton.getText().toString() : "");
        pantPojo.setWatch_pocket(watch_pocket.isChecked() ? watch_pocket.getText().toString() : "");
        pantPojo.setBack_pocket_cadge_button(back_pocket_cadge_button.isChecked() ? back_pocket_cadge_button.getText().toString() : "");
        pantPojo.setEight_loobs(eight_loobs.isChecked() ? eight_loobs.getText().toString() : "");
        pantPojo.setLoobs_inch(loobs_inch.isChecked() ? loobs_inch.getText().toString() : "");
        pantPojo.setLoobs_inch_two(loobs_inch_two.isChecked() ? loobs_inch_two.getText().toString() : "");
        pantPojo.setBack_pocket_loobs(back_pocket_loobs.isChecked() ? back_pocket_loobs.getText().toString() : "");
        pantPojo.setInch_belt(inch_belt.isChecked() ? inch_belt.getText().toString() : "");
        pantPojo.setBelt_grip(belt_grip.isChecked() ? belt_grip.getText().toString() : "");
        pantPojo.setPocket_thely(pocket_thely.isChecked() ? pocket_thely.getText().toString() : "");
        pantPojo.setZip_quality(zip_quality.isChecked() ? zip_quality.getText().toString() : "");
        pantPojo.setPocket_dip(pocket_dip.isChecked() ? pocket_dip.getText().toString() : "");
        pantPojo.setFolding_mori(folding_mori.isChecked() ? folding_mori.getText().toString() : "");
        pantPojo.setTurpayi_hand(turpayi_hand.isChecked() ? turpayi_hand.getText().toString() : "");
        pantPojo.setLong_loop(long_loop.isChecked() ? long_loop.getText().toString() : "");
        pantPojo.setLong_nib(long_nib.isChecked() ? long_nib.getText().toString() : "");
        pantPojo.setSpecial(special.isChecked() ? special.getText().toString() : "");
        pantPojo.setSame_as_image(same_as_image.isChecked() ? same_as_image.getText().toString() : "");


        //checkboxes for general fields

        pantPojo.setCustomer_cloth(customer_cloth.isChecked() ? customer_cloth.getText().toString() : "");
        pantPojo.setChild_kurta_size(child_kurta_size.isChecked() ? child_kurta_size.getText().toString() : "");
        pantPojo.setOnly_sewing(only_sewing.isChecked() ? only_sewing.getText().toString() : "");
        pantPojo.setFinished_adjust(finished_adjust.isChecked() ? finished_adjust.getText().toString() : "");
        pantPojo.setSpecial_customer_order(special_customer_order.isChecked() ? special_customer_order.getText().toString() : "");
        pantPojo.setRegular_customer_order(regular_customer_order.isChecked() ? regular_customer_order.getText().toString() : "");
        pantPojo.setUrgent_order(urgent_order.isChecked() ? urgent_order.getText().toString() : "");
        pantPojo.setNo_label(no_label.isChecked() ? no_label.getText().toString() : "");
        pantPojo.setSpecial_order(special_order.isChecked() ? special_order.getText().toString() : "");
        pantPojo.setButton_should_be_strong(button_should_be_strong.isChecked() ? button_should_be_strong.getText().toString() : "");
        pantPojo.setLight_work_shoulder_down(light_work_shoulder_down.isChecked() ? light_work_shoulder_down.getText().toString() : "");
        pantPojo.setFull_shoulder_down(full_shoulder_down.isChecked() ? full_shoulder_down.getText().toString() : "");
        pantPojo.setStraight_shoulder(straight_shoulder.isChecked() ? straight_shoulder.getText().toString() : "");
        pantPojo.setRight_shoulder_down(right_shoulder_down.isChecked() ? right_shoulder_down.getText().toString() : "");
        pantPojo.setLeft_shoulder_down(left_shoulder_down.isChecked() ? left_shoulder_down.getText().toString() : "");
        pantPojo.setAltered_body(altered_body.isChecked() ? altered_body.getText().toString() : "");
        pantPojo.setDeep_body(deep_body.isChecked() ? deep_body.getText().toString() : "");
        pantPojo.setParty_label(party_label.isChecked() ? party_label.getText().toString() : "");
        pantPojo.setFancy_label(fancy_label.isChecked() ? fancy_label.getText().toString() : "");

        //send image here

       // image_4_db;

        pantPojo.setCustomer_image(TextUtils.isEmpty(image_4_db) ? "" : image_4_db);

        alerter.setTitle("انتطار فرمائیے۔۔۔")
                .setText("کسٹمر کا آرڈر بن رہا ہے۔۔۔")
                .setIcon(R.drawable.dulha_jee_logo)
                .setBackgroundColorRes(R.color.black).show();



        pantPojo.setKarigar(dropdown_karegar_name.getSelectedItem().toString());

        //Api call here...
        iapi.createPant("Bearer "+ sharedPreference.getToken(),pantPojo).enqueue(new Callback<HtmlResponseBody>() {
            @Override
            public void onResponse(Call<HtmlResponseBody> call, Response<HtmlResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(), "Success..." + response.code(), Toast.LENGTH_SHORT).show();
                    Log.i("TAG", "onResponse: " + response.message());
                    Log.i("TAG", "onResponse: " + response.raw());
                    html_url = response.body().getUrl();
                    doWebViewPrint();
                    Alerter.hide();
                }else{
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
