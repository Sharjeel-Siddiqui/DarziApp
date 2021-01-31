package com.example.dulha_jee.dashboard;

import android.Manifest;
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
import com.example.dulha_jee.SharedPreference;
import com.example.dulha_jee.api.ApiClient;
import com.example.dulha_jee.api.Iapi;
import com.example.dulha_jee.pojo.WaistCoatFragmentrequestBody;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.tapadoo.alerter.Alerter;
import com.tapadoo.alerter.OnHideAlertListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class WaistCoatFragment extends Fragment {
    Spinner dropdown_karegar_name;
    String[] users = {"کرتا شلوار", "کرتا پاجامہ", "قمیص شلوار", "فرنٹ اوپن کرتا"};
    String[] karegarName = {" کاریگر کا نام", "ابرار ", "احمد ", "امین ", "عارف "};
    Button submit_waistcoat, chooseImage;
    NavController navController;
    ImageView iv_01;
    Uri imageUri;
    public static final int PICK_IMAGE = 1;
    SharedPreference sharedPreference;
    private WebView mWebView;
    String image_4_db;

    Iapi iapi;

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
    @BindView(R.id.customer_name)
    EditText customer_name;
    @BindView(R.id.customer_number)
    EditText customer_number;
    @BindView(R.id.order_number)
    EditText order_number;
    @BindView(R.id.order_date_date)
    EditText order_date_date;


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
        iapi = ApiClient.getClient().create(Iapi.class);
        navController = Navigation.findNavController(view);
        submit_waistcoat = view.findViewById(R.id.submit_waistcoat);
        iv_01 = view.findViewById(R.id.iv_01);
        sharedPreference = new SharedPreference(getActivity());

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
                createWaistCoat();
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
                                navController.navigate(R.id.action_waistCoatFragment_to_dashBoard, null, new NavOptions.Builder()
                                        .setPopUpTo(R.id.waistCoatFragment,
                                                true).build());
                            }
                        })
                        .show();*/
            }
        });
    }

    private void createWaistCoat() {
        WaistCoatFragmentrequestBody waistCoatFragmentrequestBody = new WaistCoatFragmentrequestBody();

        //et fields
        waistCoatFragmentrequestBody.setCustomer_name(TextUtils.isEmpty(customer_name.getText().toString()) ? "" : customer_name.getText().toString());
        waistCoatFragmentrequestBody.setMobile_number(TextUtils.isEmpty(customer_number.getText().toString()) ? "" : customer_number.getText().toString());
        waistCoatFragmentrequestBody.setOrder_number(TextUtils.isEmpty(order_number.getText().toString()) ? "" : order_number.getText().toString());
        waistCoatFragmentrequestBody.setOrder_date(TextUtils.isEmpty(order_date.getText().toString()) ? "" : order_date.getText().toString());

        waistCoatFragmentrequestBody.setQuantity(TextUtils.isEmpty(quantity.getText().toString()) ? "" : quantity.getText().toString());
        waistCoatFragmentrequestBody.setCollar(TextUtils.isEmpty(collar.getText().toString()) ? "" : collar.getText().toString());
        waistCoatFragmentrequestBody.setSleeves(TextUtils.isEmpty(sleeves.getText().toString()) ? "" : sleeves.getText().toString());
        waistCoatFragmentrequestBody.setShoulder(TextUtils.isEmpty(shoulder.getText().toString()) ? "" : shoulder.getText().toString());
        waistCoatFragmentrequestBody.setHip(TextUtils.isEmpty(hip.getText().toString()) ? "" : hip.getText().toString());
        waistCoatFragmentrequestBody.setGudda(TextUtils.isEmpty(gudda.getText().toString()) ? "" : gudda.getText().toString());
        waistCoatFragmentrequestBody.setLengthMade(TextUtils.isEmpty(lengthMade.getText().toString()) ? "" : lengthMade.getText().toString());
        waistCoatFragmentrequestBody.setFront(TextUtils.isEmpty(front.getText().toString()) ? "" : front.getText().toString());
        waistCoatFragmentrequestBody.setAbdomen(TextUtils.isEmpty(abdomen.getText().toString()) ? "" : abdomen.getText().toString());

        waistCoatFragmentrequestBody.setThree_piece_style_cadge(TextUtils.isEmpty(three_piece_style_cadge.getText().toString()) ? "" : three_piece_style_cadge.getText().toString());
        waistCoatFragmentrequestBody.setCollar_width(TextUtils.isEmpty(collar_width.getText().toString()) ? "" : collar_width.getText().toString());
        waistCoatFragmentrequestBody.setViolet_pocket_width(TextUtils.isEmpty(violet_pocket_width.getText().toString()) ? "" : violet_pocket_width.getText().toString());
        waistCoatFragmentrequestBody.setExtra_buttons(TextUtils.isEmpty(extra_buttons.getText().toString()) ? "" : extra_buttons.getText().toString());
        waistCoatFragmentrequestBody.setLozing(TextUtils.isEmpty(lozing.getText().toString()) ? "" : lozing.getText().toString());
        // waistCoatFragmentrequestBody.setwaos(TextUtils.isEmpty(lozing.getText().toString()) ? "" : lozing.getText().toString()); //waist coat chawk length

        //Check boxes come here
        waistCoatFragmentrequestBody.setJawahir_cut_style(jawahir_cut_style.isChecked() ? jawahir_cut_style.getText().toString() : "");
        waistCoatFragmentrequestBody.setV_neck_style(v_neck_style.isChecked() ? v_neck_style.getText().toString() : "");
        waistCoatFragmentrequestBody.setU_neck_style(u_neck_style.isChecked() ? u_neck_style.getText().toString() : "");
        waistCoatFragmentrequestBody.setWaistcoat_back_backet(waistcoat_back_backet.isChecked() ? waistcoat_back_backet.getText().toString() : "");
        waistCoatFragmentrequestBody.setMetal_fancy_buttons(metal_fancy_buttons.isChecked() ? metal_fancy_buttons.getText().toString() : "");
        waistCoatFragmentrequestBody.setMatching_plastic_buttons(matching_plastic_buttons.isChecked() ? matching_plastic_buttons.getText().toString() : "");
        waistCoatFragmentrequestBody.setStraight_daman(straight_daman.isChecked() ? straight_daman.getText().toString() : "");
        waistCoatFragmentrequestBody.setRound_daman(round_daman.isChecked() ? round_daman.getText().toString() : "");
        waistCoatFragmentrequestBody.setCoat_style_round_daman(coat_style_round_daman.isChecked() ? coat_style_round_daman.getText().toString() : "");
        waistCoatFragmentrequestBody.setCollar_hala(collar_hala.isChecked() ? collar_hala.getText().toString() : "");
        waistCoatFragmentrequestBody.setCollar_pointed(collar_pointed.isChecked() ? collar_pointed.getText().toString() : "");
        waistCoatFragmentrequestBody.setDouble_bon_pocket(double_bon_pocket.isChecked() ? double_bon_pocket.getText().toString() : "");
        waistCoatFragmentrequestBody.setSingle_bon_pocket(single_bon_pocket.isChecked() ? single_bon_pocket.getText().toString() : "");
        waistCoatFragmentrequestBody.setViolet_pocket(violet_pocket.isChecked() ? violet_pocket.getText().toString() : "");
        waistCoatFragmentrequestBody.setPatch_pocket(patch_pocket.isChecked() ? patch_pocket.getText().toString() : "");
        waistCoatFragmentrequestBody.setCadge_button_pati(cadge_button_pati.isChecked() ? cadge_button_pati.getText().toString() : "");
        waistCoatFragmentrequestBody.setNo_lower_pocket(no_upper_pocket.isChecked() ? no_upper_pocket.getText().toString() : "");
        waistCoatFragmentrequestBody.setNo_lower_pocket(no_lower_pocket.isChecked() ? no_lower_pocket.getText().toString() : "");
        waistCoatFragmentrequestBody.setWaistcoat_style_like_image(waistcoat_style_like_image.isChecked() ? waistcoat_style_like_image.getText().toString() : "");
        waistCoatFragmentrequestBody.setTwo_pockets_inside(two_pockets_inside.isChecked() ? two_pockets_inside.getText().toString() : "");
        waistCoatFragmentrequestBody.setPocket_like_fabric(pocket_like_fabric.isChecked() ? pocket_like_fabric.getText().toString() : "");
        waistCoatFragmentrequestBody.setFull_fewsing(full_fewsing.isChecked() ? full_fewsing.getText().toString() : "");
        waistCoatFragmentrequestBody.setFront_fewsing(front_fewsing.isChecked() ? front_fewsing.getText().toString() : "");
        waistCoatFragmentrequestBody.setAstar_cherry(astar_cherry.isChecked() ? astar_cherry.getText().toString() : "");
        waistCoatFragmentrequestBody.setCustomer_stiff_waistcoat(customer_stiff_waistcoat.isChecked() ? customer_stiff_waistcoat.getText().toString() : "");
        waistCoatFragmentrequestBody.setCollar_karhayi(collar_karhayi.isChecked() ? collar_karhayi.getText().toString() : "");
        waistCoatFragmentrequestBody.setFront_pocket_karhayi(front_pocket_karhayi.isChecked() ? front_pocket_karhayi.getText().toString() : "");
        waistCoatFragmentrequestBody.setBoth_front_karhayi(both_front_karhayi.isChecked() ? both_front_karhayi.getText().toString() : "");
        waistCoatFragmentrequestBody.setOne_front_karhayi(one_front_karhayi.isChecked() ? one_front_karhayi.getText().toString() : "");
        waistCoatFragmentrequestBody.setBack_karhayi(back_karhayi.isChecked() ? back_karhayi.getText().toString() : "");
        waistCoatFragmentrequestBody.setCollar_buttons(collar_buttons.isChecked() ? collar_buttons.getText().toString() : "");
        waistCoatFragmentrequestBody.setCollar_lower_buttons(collar_lower_buttons.isChecked() ? collar_lower_buttons.getText().toString() : "");
        waistCoatFragmentrequestBody.setFront_pipine(front_pipine.isChecked() ? front_pipine.getText().toString() : "");
        waistCoatFragmentrequestBody.setPocket_pipine(pocket_pipine.isChecked() ? pocket_pipine.getText().toString() : "");
        waistCoatFragmentrequestBody.setCadge_contrast(cadge_contrast.isChecked() ? cadge_contrast.getText().toString() : "");
        waistCoatFragmentrequestBody.setExcersize_body(excersize_body.isChecked() ? excersize_body.getText().toString() : "");
        waistCoatFragmentrequestBody.setChild_size_waistcoat(child_size_waistcoat.isChecked() ? child_size_waistcoat.getText().toString() : "");
        waistCoatFragmentrequestBody.setMagzi_round_neck_waistcoat(magzi_round_neck_waistcoat.isChecked() ? magzi_round_neck_waistcoat.getText().toString() : "");
        waistCoatFragmentrequestBody.setBody_builder(body_builder.isChecked() ? body_builder.getText().toString() : "");
        waistCoatFragmentrequestBody.setCollar_soft_bukram(collar_soft_bukram.isChecked() ? collar_soft_bukram.getText().toString() : "");
        waistCoatFragmentrequestBody.setShape_body(shape_body.isChecked() ? shape_body.getText().toString() : "");

        //checkboxes for general fields

        waistCoatFragmentrequestBody.setCustomer_cloth(customer_cloth.isChecked() ? customer_cloth.getText().toString() : "");
        waistCoatFragmentrequestBody.setChild_kurta_size(child_kurta_size.isChecked() ? child_kurta_size.getText().toString() : "");
        waistCoatFragmentrequestBody.setOnly_sewing(only_sewing.isChecked() ? only_sewing.getText().toString() : "");
        waistCoatFragmentrequestBody.setFinished_adjust(finished_adjust.isChecked() ? finished_adjust.getText().toString() : "");
        waistCoatFragmentrequestBody.setSpecial_customer_order(special_customer_order.isChecked() ? special_customer_order.getText().toString() : "");
        waistCoatFragmentrequestBody.setRegular_customer_order(regular_customer_order.isChecked() ? regular_customer_order.getText().toString() : "");
        waistCoatFragmentrequestBody.setUrgent_order(urgent_order.isChecked() ? urgent_order.getText().toString() : "");
        waistCoatFragmentrequestBody.setNo_label(no_label.isChecked() ? no_label.getText().toString() : "");
        waistCoatFragmentrequestBody.setSpecial_order(special_order.isChecked() ? special_order.getText().toString() : "");
        waistCoatFragmentrequestBody.setButton_should_be_strong(button_should_be_strong.isChecked() ? button_should_be_strong.getText().toString() : "");
        waistCoatFragmentrequestBody.setLight_work_shoulder_down(light_work_shoulder_down.isChecked() ? light_work_shoulder_down.getText().toString() : "");
        waistCoatFragmentrequestBody.setFull_shoulder_down(full_shoulder_down.isChecked() ? full_shoulder_down.getText().toString() : "");
        waistCoatFragmentrequestBody.setStraight_shoulder(straight_shoulder.isChecked() ? straight_shoulder.getText().toString() : "");
        waistCoatFragmentrequestBody.setRight_shoulder_down(right_shoulder_down.isChecked() ? right_shoulder_down.getText().toString() : "");
        waistCoatFragmentrequestBody.setLeft_shoulder_down(left_shoulder_down.isChecked() ? left_shoulder_down.getText().toString() : "");
        waistCoatFragmentrequestBody.setAltered_body(altered_body.isChecked() ? altered_body.getText().toString() : "");
        waistCoatFragmentrequestBody.setDeep_body(deep_body.isChecked() ? deep_body.getText().toString() : "");
        waistCoatFragmentrequestBody.setParty_label(party_label.isChecked() ? party_label.getText().toString() : "");
        waistCoatFragmentrequestBody.setFancy_label(fancy_label.isChecked() ? fancy_label.getText().toString() : "");

        //sendimages here
        //image_4_db;

        //Api call here...


        iapi.createWaistCoat("Bearer " + sharedPreference.getToken(), waistCoatFragmentrequestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Success...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
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
            //imageUri = data.getData();
            //iv_01.setImageURI(imageUri);

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            iv_01.setImageBitmap(bitmap);

            image_4_db = ConvertBitmapToString(bitmap);
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

        webView.loadUrl("https://css4.pub/2017/newsletter/drylab.html");

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

    public void drawable_to_base64(int drawable){
        //encode image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),drawable);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String imageString =    Base64.encodeToString(imageBytes, Base64.DEFAULT);
        Log.i("TAG", "drawable_to_base64: " + imageString);
    }
}
