package com.anonstudio.quizpert.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.net.Uri;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ActionMenuView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.anonstudio.quizpert.BeatTheClock;
import com.anonstudio.quizpert.CategorySelection;
import com.anonstudio.quizpert.ClosedButtonListener;
import com.anonstudio.quizpert.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.billingclient.api.BillingClient.SkuType.INAPP;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.*;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.*;

public class ShowDialog extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener, PurchasesUpdatedListener, ClosedButtonListener.ButtonListener {

    Context context;
    int layout_resource;
    int layout_button;

    public int PROCESS_NUMBER = 0;
    private ShowDialogListener listener;
    Preferences preferences;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private Animation animBlink;
    private int month_position = 0;
    private int year_position = 0;
    private BillingClient billingClient;
    private Activity activity;
    private ImageView gems_image_1;
    private ImageView gems_image_2;
    private ImageView gems_image_3;
    private ImageView gems_image_4;
    private AlertDialog.Builder builder;
    private LayoutInflater inflater;
    private View dialogView;
    private AlertDialog alertDialog;
    private String product_bought = "";
    private SkuDetails itemInfo1;
    private SkuDetails itemInfo2;
    private SkuDetails itemInfo3;
    private SkuDetails itemInfo4;
    private SkuDetails itemInfo5;
    private SkuDetails itemInfo6;
    private int list_size = 0;
    private int quiz_score = 0;
    private int quiz_highscore = 0;
    ImageView close_button;
    ClosedButtonListener closedButtonListener;
    private CountDownTimer countDownTimer = null;




    @Override
    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {


    }

    @Override
    public void setClosedButtonCode(int CLOSED_BUTTON_CODE) {
        if(CLOSED_BUTTON_CODE == 200) {
            if(alertDialog != null) {
                alertDialog.dismiss();
            }
        }
    }


    class CustomTextView {
        private final TextView view;
        private int textViewID = 0;
        private String[] urls;
        private String[] attributes;

        public CustomTextView(TextView textView,int textViewID) {
            this.view = textView;
            this.textViewID = textViewID;
            this.urls = fetchUrls();
            this.attributes = fetchAttributes();
        }

        private String[] fetchAttributes() {
            String[] hold_attributes;
            String title = "";
            String author = "";
            String license = "";
            String connection_two = context.getString(R.string.is_license_under);

            if(textViewID == R.id.link_one) {

                title = context.getString(R.string.the_swan);
                author = context.getString(R.string.author_the_swan);
                license = context.getString(R.string.license_the_swan);

            } else if(textViewID == R.id.link_two) {

                title = context.getString(R.string.air);
                author = context.getString(R.string.author_air);
                license = context.getString(R.string.license_air);
                connection_two = context.getString(R.string.is_in_the);

            } else if(textViewID == R.id.link_three) {

                title = context.getString(R.string.nocturne);
                author = context.getString(R.string.author_nocturne);
                license = context.getString(R.string.license_nocturne);
                connection_two = context.getString(R.string.is_in_the);

            } else if(textViewID == R.id.link_four) {

                title = context.getString(R.string.liebestraum);
                author = context.getString(R.string.author_liebestraum);
                license = context.getString(R.string.license_liebestraum);

            } else if(textViewID == R.id.link_five) {

                title = context.getString(R.string.gnossienne);
                author = context.getString(R.string.author_gnossienne);
                license = context.getString(R.string.license_gnossienne);

            } else if(textViewID == R.id.link_six) {

                title = context.getString(R.string.gymnopedie);
                author = context.getString(R.string.author_gymnopedie);
                license = context.getString(R.string.license_gymnopedie);

            }


            hold_attributes = new String[]{title, author, license, connection_two};
            return hold_attributes;


        }

        private String[] fetchUrls() {
            String[] hold_urls;
            String title_url = "";
            String author_url = "";
            String license_url = "";

            if(textViewID == R.id.link_one) {

                title_url = context.getString(R.string.the_swan_title_url);
                author_url = context.getString(R.string.the_swan_author_url);
                license_url = context.getString(R.string.the_swan_license_url);

            } else if(textViewID == R.id.link_two) {

                title_url = context.getString(R.string.air_title_url);
                author_url = context.getString(R.string.air_author_url);
                license_url = context.getString(R.string.air_license_url);


            } else if(textViewID == R.id.link_three) {

                title_url = context.getString(R.string.nocturne_title_url);
                author_url = context.getString(R.string.nocturne_author_url);
                license_url = context.getString(R.string.nocturne_license_url);


            } else if(textViewID == R.id.link_four) {

                title_url = context.getString(R.string.liebestraum_ttle_url);
                author_url = context.getString(R.string.liebestraum_author_url);
                license_url = context.getString(R.string.liebestraum_license_url);

            } else if(textViewID == R.id.link_five) {

                title_url = context.getString(R.string.gnossienne_title_url);
                author_url = context.getString(R.string.gnossienne_author_url);
                license_url = context.getString(R.string.gnossienne_license_url);

            } else if(textViewID == R.id.link_six) {

                title_url = context.getString(R.string.gymnopedie_title_url);
                author_url = context.getString(R.string.gymnopedie_author_urk);
                license_url = context.getString(R.string.gymnopedie_license_url);

            }


            hold_urls = new String[]{title_url, author_url, license_url};
            return hold_urls;
        }

        private void goToUrl(String url) {
            Uri uri = Uri.parse(url);
            context.startActivity(new Intent(Intent.ACTION_VIEW,uri));
        }

        public void setCustomTextView() {
            SpannableStringBuilder spanTxt = new SpannableStringBuilder();
            spanTxt.append(attributes[0]);
            int start = 0;
            spanTxt.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    goToUrl(urls[0]);
                    //Toast.makeText(context, "Terms of services Clicked",
                           // Toast.LENGTH_SHORT).show();
                }
            }, start, spanTxt.length(), 0);
            spanTxt.append(" by ");
            //spanTxt.append(" and");
            //spanTxt.setSpan(new ForegroundColorSpan(Color.BLACK), spanTxt.length(), spanTxt.length(), 0);
            spanTxt.append(attributes[1]);
            spanTxt.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    goToUrl(urls[1]);
                    //Toast.makeText(context, "Privacy Policy Clicked",
                            //Toast.LENGTH_SHORT).show();
                }
            }, spanTxt.length() - attributes[1].length(), spanTxt.length(), 0);//start = 0 end= spanTxt.length
            spanTxt.append(attributes[3]);
            spanTxt.append(attributes[2]);
            spanTxt.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    goToUrl(urls[2]);
                    //Toast.makeText(context, "Privacy Policy Clicked",
                            //Toast.LENGTH_SHORT).show();
                }
            }, spanTxt.length() - attributes[2].length(), spanTxt.length(), 0);
            view.setMovementMethod(LinkMovementMethod.getInstance());
            view.setText(spanTxt, TextView.BufferType.SPANNABLE);
        }

    }


    public ShowDialog(Context context, int layout_resource, int layout_button, Activity activity) {

        this.context = context;
        this.layout_resource = layout_resource;
        this.layout_button = layout_button;
        this.activity = activity;
        this.preferences = new Preferences(context,APP_PREF_FILE);
        sp = preferences.getPreferenceObject();
        editor = preferences.getPreferenceEditorObject();


    }


    public ShowDialog(Context context, int layout_resource, int layout_button, int quiz_score, int quiz_highscore) {

        this.context = context;
        this.layout_resource = layout_resource;
        this.layout_button = layout_button;
        this.quiz_score = quiz_score;
        this.quiz_highscore = quiz_highscore;
        this.preferences = new Preferences(context,APP_PREF_FILE);
        sp = preferences.getPreferenceObject();
        editor = preferences.getPreferenceEditorObject();


    }




    public ShowDialog(Context context, int layout_resource, int layout_button) {

         this.context = context;
         this.layout_resource = layout_resource;
         this.layout_button = layout_button;
         this.preferences = new Preferences(context,APP_PREF_FILE);
         sp = preferences.getPreferenceObject();
         editor = preferences.getPreferenceEditorObject();


     }

/*
    private void customTextView(TextView view) {
        SpannableStringBuilder spanTxt = new SpannableStringBuilder(
                "I agree to the ");
        spanTxt.append("Term of services");
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(getApplicationContext(), "Terms of services Clicked",
                        Toast.LENGTH_SHORT).show();
            }
        }, spanTxt.length() - "Term of services".length(), spanTxt.length(), 0);
        spanTxt.append(" and");
        spanTxt.setSpan(new ForegroundColorSpan(Color.BLACK), 32, spanTxt.length(), 0);
        spanTxt.append(" Privacy Policy");
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(getApplicationContext(), "Privacy Policy Clicked",
                        Toast.LENGTH_SHORT).show();
            }
        }, spanTxt.length() - " Privacy Policy".length(), spanTxt.length(), 0);
        view.setMovementMethod(LinkMovementMethod.getInstance());
        view.setText(spanTxt, BufferType.SPANNABLE);
    }
*/
//Correct Method
    /*private void customTextView(TextView view) {
        SpannableStringBuilder spanTxt = new SpannableStringBuilder();
        spanTxt.append(context.getString(R.string.the_swan));
        int start = 0;
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(context, "Terms of services Clicked",
                        Toast.LENGTH_SHORT).show();
            }
        }, start, spanTxt.length(), 0);
        spanTxt.append(" by ");
        //spanTxt.append(" and");
        //spanTxt.setSpan(new ForegroundColorSpan(Color.BLACK), spanTxt.length(), spanTxt.length(), 0);
        spanTxt.append(context.getString(R.string.author_the_swan));
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(context, "Privacy Policy Clicked",
                        Toast.LENGTH_SHORT).show();
            }
        }, spanTxt.length() - context.getString(R.string.author_the_swan).length(), spanTxt.length(), 0);//start = 0 end= spanTxt.length
        spanTxt.append(" is licensed under ");
        spanTxt.append(context.getString(R.string.license_the_swan));
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(context, "Privacy Policy Clicked",
                        Toast.LENGTH_SHORT).show();
            }
        }, spanTxt.length() - context.getString(R.string.license_the_swan).length(), spanTxt.length(), 0);
        view.setMovementMethod(LinkMovementMethod.getInstance());
        view.setText(spanTxt, TextView.BufferType.SPANNABLE);
    }*/

    /*private void customTextView(TextView view, int titl, int autho, int b, int licens, int licens) {
       String title = getResources().getString(R.string.the_swan);
       String by = context.getString(R.string.by);
       String author = context.getString(R.string.author_the_swan);
       String licensed_under = context.getString(R.string.is_license_under);
       String license = context.getString(R.string.license_the_swan);

       TextView textView = null;
       textView.setText(String.format(
               getString(R.string.message),
               title,
               author,
               license)
       );

    }*/

    public void getV22ShowDialog() {
        onAttach(context);
        AlertDialog.Builder v2builder = new AlertDialog.Builder(context);
        LayoutInflater v2inflater = LayoutInflater.from(context);
        View v2dialogView = v2inflater.inflate(layout_resource,null);
        v2builder.setView(v2dialogView);
        AlertDialog v2alertDialog = v2builder.create();
        v2alertDialog.show();
        v2alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


       // int your_highscore = 4567;




        v2dialogView.findViewById(layout_button).setOnClickListener(v -> v2alertDialog.dismiss());

    }

    private void setImage(int number, ImageView view) {
        switch(number) {
            case zero:
                view.setImageResource(R.drawable.zeroxxxhdpi);
                break;
            case one:
                view.setImageResource(R.drawable.onexxxhdpi);
                break;
            case two:
                view.setImageResource(R.drawable.twoxxxhdpi);
                break;
            case three:
                view.setImageResource(R.drawable.threexxxhdpi);
                break;
            case four:
                view.setImageResource(R.drawable.fourxxxhdpi);
                break;
            case five:
                view.setImageResource(R.drawable.fivexxxhdpi);
                break;
            case six:
                view.setImageResource(R.drawable.sixxxxhdpi);
                break;
            case seven:
                view.setImageResource(R.drawable.sevenxxxhdpi);
                break;
            case eight:
                view.setImageResource(R.drawable.eightxxxhdpi);
                break;
            case nine:
                view.setImageResource(R.drawable.ninexxxhdpi);
                break;



        }
    }


    public void getV2ShowDialog() {
         onAttach(context);
         AlertDialog.Builder v2builder = new AlertDialog.Builder(context);
         LayoutInflater v2inflater = LayoutInflater.from(context);
         View v2dialogView = v2inflater.inflate(layout_resource,null);
         v2builder.setView(v2dialogView);
         AlertDialog v2alertDialog = v2builder.create();
         v2alertDialog.show();
         v2alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);





         v2dialogView.findViewById(layout_button).setOnClickListener(v -> v2alertDialog.dismiss());

     }

     /*public void getPayGemsDialog(String chapter_num, String table) {
         onAttach(context);
         AlertDialog.Builder builder = new AlertDialog.Builder(context);
         LayoutInflater inflater = LayoutInflater.from(context);
         View dialogView = inflater.inflate(layout_resource,null);
         builder.setView(dialogView);
         AlertDialog alertDialog = builder.create();
         alertDialog.show();
         alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

         if(layout_resource == R.layout.go_to_store) {
             dialogView.findViewById(layout_button).setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     listener.setTransactionCode(CHECK_AVAILABLE_GEMS_CODE);
                     alertDialog.dismiss();

                 }
             });
         }

     }*/


    /*public void consume(View view) {
        //check if service is already connected
        if (billingClient.isReady()) {
            initiatePurchase();
        }
        //else reconnect service
        else {
            billingClient = BillingClient.newBuilder(context
            ).enablePendingPurchases().setListener(this).build();
            billingClient.startConnection(new BillingClientStateListener() {
                @Override
                public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        initiatePurchase();
                    } else {
                        Toast.makeText(context, "Error " + billingResult.getDebugMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onBillingServiceDisconnected() {
                }
            });
        }
    }
*/

    private void billing_client() {

        billingClient = BillingClient.newBuilder(context).setListener(new PurchasesUpdatedListener() {
            @Override
            public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> purchases) {
                //here you can process the new purchases.

                //if item newly purchased
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {

                    handlePurchases(purchases);

                } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
                    Toast.makeText(context,"Purchase Canceled",Toast.LENGTH_SHORT).show();
                }





            }
        }).enablePendingPurchases().build();
        connectToGooglePlayBilling();

    }

    private void connectToGooglePlayBilling() {

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
                //you can restart your client here.
                connectToGooglePlayBilling();
            }

            @Override
            public void onBillingSetupFinished(@NonNull @NotNull BillingResult billingResult) {


                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){

                    //here billing Client is ready to be used.
                    productDetails();

                } else {
                    Toast.makeText(context, "Connection Error", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }



    void handlePurchases(List<Purchase>  purchases) {
        int count = -1;
        int num_of_purchases = purchases.size();

        for(Purchase purchase:purchases) {

            if(count == num_of_purchases - 1) {
                count = num_of_purchases - 1;
            }

            final int index= purchaseItemIDs.indexOf(purchase.getSkus().get(count + 1));

            count = count + 1;
            //purchase found
            if(index>-1) {
                //if item is purchased
                if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED)
                {


                    if (!verifyValidSignature(purchase.getOriginalJson(), purchase.getSignature())) {
                        // Invalid purchase
                        // show error to user
                        Toast.makeText(context, "Error : Invalid Purchase", Toast.LENGTH_SHORT).show();
                        continue;//skip current iteration only because other items in purchase list must be checked if present
                    } else {

                        switch(purchase.getSkus().get(count)) {
                            case product_one:
                                Gems gems_one = new Gems(SET_GEMS_ONE,context);
                                gems_one.setGemAmount();
                                listener.setMusicPlayer(ten);
                                break;
                            case product_two:
                                Gems gems_two = new Gems(SET_GEMS_TWO,context);
                                gems_two.setGemAmount();
                                listener.setMusicPlayer(ten);
                                break;
                            case product_three:
                                Gems gems_three = new Gems(SET_GEMS_THREE,context);
                                gems_three.setGemAmount();
                                listener.setMusicPlayer(ten);
                                break;
                            case product_four:
                                Gems gems_four = new Gems(SET_GEMS_FOUR,context);
                                gems_four.setGemAmount();
                                listener.setMusicPlayer(ten);
                                break;
                            case product_five:
                                int hearts_five = sp.getInt("hearts",two);
                                editor.putInt("hearts", hearts_five + five).apply();
                                listener.setMusicPlayer(nine);
                                break;
                            case product_six:
                                int hearts_ten = sp.getInt("hearts",two);
                                editor.putInt("hearts", hearts_ten + ten).apply();
                                listener.setMusicPlayer(nine);
                                break;

                        }
                       // Gems gems = new Gems(context);
                        //TextView gems_amount_textview = dialogView.findViewById(R.id.show_gems_amount);
                       // gems_amount_textview.setText(String.valueOf(gems.getGemAmount()));

                        ShowDialog showPurchaseThanksDialog = new ShowDialog(context, R.layout.purchased_gems_popup,R.id.close_purchased_gems_popup);
                        showPurchaseThanksDialog.getV2ShowDialog();

                    }
                    // else purchase is valid
                    //if item is purchased and not consumed
                    if (!purchase.isAcknowledged()) {
                        ConsumeParams consumeParams = ConsumeParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();
                        billingClient.consumeAsync(consumeParams, new ConsumeResponseListener() {
                            @Override
                            public void onConsumeResponse(@NotNull BillingResult billingResult, @NotNull String purchaseToken) {
                                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {


                                    int consume_count_value = sp.getInt(purchaseItemIDs.get(index),0) + one;
                                    editor.putInt(purchaseItemIDs.get(index),consume_count_value).apply();
                                    //Toast.makeText(context, "Item "+ purchaseItemIDs.get(index) +"Consumed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
                //if purchase is pending
                else if(  purchase.getPurchaseState() == Purchase.PurchaseState.PENDING)
                {

                    Toast.makeText(context,
                            "Purchase is Pending. Please stay on current page until you complete the transaction", Toast.LENGTH_LONG).show();
                }
                //if purchase is refunded or unknown
                else if( purchase.getPurchaseState() == Purchase.PurchaseState.UNSPECIFIED_STATE)
                {
                    Toast.makeText(context, "Purchase Status Unknown", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    /**
     * Verifies that the purchase was signed correctly for this developer's public key.
     * &lt;p>Note: It's strongly recommended to perform such check on your backend since hackers can
     * replace this method with "constant true" if they decompile/rebuild your app.
     * &lt;/p>
     */
    private boolean verifyValidSignature(String signedData, String signature) {
        try {
// To get key go to Developer Console > Select your app > Development Tools > Services &amp; APIs.
            String energy_booster = context.getString(R.string.pk);
            return Security.verifyPurchase(energy_booster, signedData, signature);
        } catch (IOException e) {
            return false;
        }
    }


    //note add unique product ids
//use same id for preference key
    private final ArrayList<String> purchaseItemIDs = new ArrayList() {{
        add("gems100");
        add("gems500");
        add("gems1000");
        add("gems3000");
        add("hearts5");
        add("hearts10");
    }};

    private ArrayList<String> purchaseItemDisplay = new ArrayList();



    private void productDetails() {

        List<String> product_ids = new ArrayList<>();
        product_ids.add(context.getString(R.string.product_id_one));
        product_ids.add(context.getString(R.string.product_id_two));
        product_ids.add(context.getString(R.string.product_id_three));
        product_ids.add(context.getString(R.string.product_id_four));
        product_ids.add(context.getString(R.string.product_id_five));
        product_ids.add(context.getString(R.string.product_id_six));

        SkuDetailsParams getProductDetailsQuery = SkuDetailsParams
                .newBuilder()
                .setSkusList(product_ids)
                .setType(INAPP)
                .build();

        billingClient.querySkuDetailsAsync(
                getProductDetailsQuery,

                new SkuDetailsResponseListener() {

                    @Override
                    public void onSkuDetailsResponse(@NonNull BillingResult billingResult, @Nullable List<SkuDetails> list) {


                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && list != null) {

                            itemInfo1 = list.get(0);
                            itemInfo2 = list.get(3);
                            itemInfo3 = list.get(1);
                            itemInfo4 = list.get(2);
                            itemInfo5 = list.get(5);
                            itemInfo6 = list.get(4);

                            list_size = list.size();




                           /* ImageView imageView = dialogView.findViewById(R.id.gems100);
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.d("cheese","here");
                                }
                            });

                            dialogView.findViewById(R.id.gems500).setOnClickListener(v -> {
                                Log.d("cheese","here");
                                Toast.makeText(context, "Privacy Policy Clicked2",
                                        Toast.LENGTH_SHORT).show();
                               *//* product_bought = itemInfo2.getTitle().trim();
                                billingClient.launchBillingFlow(
                                        activity,
                                        BillingFlowParams.newBuilder().setSkuDetails(itemInfo2).build()
                                );*//*
                            });

                            dialogView.findViewById(R.id.gems1000).setOnClickListener(v -> {
                               *//* product_bought = itemInfo3.getTitle().trim();
                                billingClient.launchBillingFlow(
                                        activity,
                                        BillingFlowParams.newBuilder().setSkuDetails(itemInfo3).build()
                                );*//*
                            });

                            dialogView.findViewById(R.id.gems3000).setOnClickListener(v -> {
                               *//* product_bought = itemInfo4.getTitle().trim();
                                billingClient.launchBillingFlow(
                                        activity,
                                        BillingFlowParams.newBuilder().setSkuDetails(itemInfo4).build()
                                );*//*
                            });
*/
                        }
                    }
                }

        );

    }


    public static class Security {
        private static final String TAG = "IABUtil/Security";

        private static final String KEY_FACTORY_ALGORITHM = "RSA";
        private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

         /** Verifies that the data was signed with the given signature, and returns the verified
         * purchase.
         * @param base64PublicKey the base64-encoded public key to use for verifying.
         * @param signedData the signed JSON string (signed, not encrypted)
         * @param signature the signature for the data, signed with the private key
         * @throws IOException if encoding algorithm is not supported or key specification
         * is invalid*/

        public static boolean verifyPurchase(String base64PublicKey, String signedData,
                                             String signature) throws IOException {
            if (TextUtils.isEmpty(signedData) || TextUtils.isEmpty(base64PublicKey)
                    || TextUtils.isEmpty(signature)) {
                //Purchase verification failed: missing data
                return false;
            }

            PublicKey key = generatePublicKey(base64PublicKey);
            return verify(key, signedData, signature);
        }


        /* * Generates a PublicKey instance from a string containing the Base64-encoded public key.
         *
         * @param encodedPublicKey Base64-encoded public key
         * @throws IOException if encoding algorithm is not supported or key specification
         * is invalid*/


        public static PublicKey generatePublicKey(String encodedPublicKey) throws IOException {
            try {
                byte[] decodedKey = Base64.decode(encodedPublicKey, Base64.DEFAULT);
                KeyFactory keyFactory = KeyFactory.getInstance(KEY_FACTORY_ALGORITHM);
                return keyFactory.generatePublic(new X509EncodedKeySpec(decodedKey));
            } catch (NoSuchAlgorithmException e) {
                // "RSA" is guaranteed to be available.
                throw new RuntimeException(e);
            } catch (InvalidKeySpecException e) {
                String msg = "Invalid key specification: " + e;
                throw new IOException(msg);
            }
        }


        /* * Verifies that the signature from the server matches the computed signature on the data.
         * Returns true if the data is correctly signed.
         *
         * @param publicKey public key associated with the developer account
         * @param signedData signed data from server
         * @param signature server signature
         * @return true if the data and signature match*/

        public static boolean verify(PublicKey publicKey, String signedData, String signature) {
            byte[] signatureBytes;
            try {
                signatureBytes = Base64.decode(signature, Base64.DEFAULT);
            } catch (IllegalArgumentException e) {
                //Base64 decoding failed
                return false;
            }
            try {
                Signature signatureAlgorithm = Signature.getInstance(SIGNATURE_ALGORITHM);
                signatureAlgorithm.initVerify(publicKey);
                signatureAlgorithm.update(signedData.getBytes());
                if (!signatureAlgorithm.verify(signatureBytes)) {
                    //Signature verification failed
                    return false;
                }
                return true;
            } catch (NoSuchAlgorithmException e) {
                // "RSA" is guaranteed to be available
                throw new RuntimeException(e);
            } catch (InvalidKeyException e) {
                //Invalid key specification
            } catch (SignatureException e) {
                //Signature exception
            }
            return false;
        }
    }


/*
    private Map<String, SkuDetails> mSkuDetailsMap = new HashMap<>();

    private void querySkuDetails() {
        SkuDetailsParams.Builder skuDetailsParamsBuilder
                = SkuDetailsParams.newBuilder();
        ArrayList<String> list = new ArrayList<>();
        list.add(inAppItem1);
        skuDetailsParamsBuilder.setSkusList(list);
        skuDetailsParamsBuilder.setType(BillingClient.SkuType.INAPP);
        billingClient.querySkuDetailsAsync(skuDetailsParamsBuilder.build(), new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(@NonNull @NotNull BillingResult billingResult, @Nullable @org.jetbrains.annotations.Nullable List<SkuDetails> list) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){
                    for (SkuDetails skuDetails : list) {
                        mSkuDetailsMap.put(skuDetails.getSku(), skuDetails);
                    }
                }
            }
        });
    }

    private void startPurchase() {
        if (mSkuDetailsMap.size() > 0) {
            BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                    .setSkuDetails(mSkuDetailsMap.get(inAppItem1))
                    .build();

            billingClient.launchBillingFlow(activity, billingFlowParams);
        }
    }

    protected void RestoringPurchases(){
        //To Query you have to provide skuType which is "BillingClient.SkuType.INAPP" or "BillingClient.SkuType.SUBS"
        billingClient.queryPurchasesAsync(BillingClient.SkuType.INAPP, new PurchasesResponseListener() {
            @Override
            public void onQueryPurchasesResponse(@NonNull @NotNull BillingResult billingResult, @NonNull @NotNull List<Purchase> list) {
                //here you can process your purchases.
            }
        });
    }
}*/



   /* private void initiatePurchase() {
        List<String> skuList = new ArrayList<>();
        skuList.add(PRODUCT_ID);
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(INAPP);
        billingClient.querySkuDetailsAsync(params.build(),
                new SkuDetailsResponseListener() {
                    @Override
                    public void onSkuDetailsResponse(@NonNull BillingResult billingResult,
                                                     List<SkuDetails> skuDetailsList) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            if (skuDetailsList != null && skuDetailsList.size() > 0) {
                                BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                                        .setSkuDetails(skuDetailsList.get(0))
                                        .build();
                                billingClient.launchBillingFlow(activity, flowParams);
                            }
                            else{
                                //try to add item/product id "consumable" inside managed product in google play console
                                Toast.makeText(context,"Purchase Item not Found",Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context,
                                    " Error "+billingResult.getDebugMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> purchases) {
        //if item newly purchased
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
            handlePurchases(purchases);
        }
        //if item already purchased then check and reflect changes
        else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
            Purchase.PurchasesResult queryAlreadyPurchasesResult = billingClient.queryPurchases(INAPP);
            List<Purchase> alreadyPurchases = queryAlreadyPurchasesResult.getPurchasesList();
            if(alreadyPurchases!=null){
                handlePurchases(alreadyPurchases);
            }
        }
        //if purchase cancelled
        else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            Toast.makeText(context,"Purchase Canceled",Toast.LENGTH_SHORT).show();
        }
        // Handle any other error msgs
        else {
            Toast.makeText(context,"Error "+billingResult.getDebugMessage(),Toast.LENGTH_SHORT).show();
        }
    }
*/

    private void toastConnectionError() {
        Toast.makeText(context, context.getString(R.string.connection_issue),
                Toast.LENGTH_SHORT).show();
    }


    private void startTimer() {

        //  countDownTimer.start();

    }

    private void cancelTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public void getShowDialog() {
         onAttach(context);
         builder = new AlertDialog.Builder(context);
         inflater = LayoutInflater.from(context);
         dialogView = inflater.inflate(layout_resource,null);
         builder.setView(dialogView);
         alertDialog = builder.create();
         alertDialog.show();
         alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


         if(layout_resource == R.layout.game_continue) {

             countDownTimer = new CountDownTimer(countdown_timer_start,countdown_timer_interval) {
                 @Override
                 public void onTick(long millisUntilFinished) {
                     TextView countdown = dialogView.findViewById(R.id.continue_countDown);
                     countdown.setText(String.valueOf(millisUntilFinished/countdown_timer_interval));
                     if(millisUntilFinished/countdown_timer_interval <= countdown_timer_warning) {
                         countdown.setTextColor(Color.parseColor(quiz_red_color));

                     } else {
                         countdown.setTextColor(Color.parseColor(start_blue_color));
                     }
                 }

                 @Override
                 public void onFinish() {
                   cancelTimer();
                   alertDialog.dismiss();


                   ShowDialog quizOverDialog = new ShowDialog(context,R.layout.quiz_over,R.id.close_quiz_over_popup,quiz_score,quiz_highscore);
                   quizOverDialog.getShowDialog();

                 }
             };
             countDownTimer.start();


             dialogView.findViewById(R.id.pay_heart).setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                    if(sp.getInt("hearts",two) > zero) {
                        cancelTimer();
                        alertDialog.dismiss();
                        int hearts_num = sp.getInt("hearts",two);

                        hearts_num = hearts_num - one;

                        editor.putInt("hearts",hearts_num).apply();
                        listener.setMusicPlayer(six);
                    }
                 }
             });
     /*        dialogView.findViewById(R.id.watch_ad_continue).setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                         cancelTimer();
                         alertDialog.dismiss();
                         listener.setMusicPlayer(six);

                 }
             });*/

             dialogView.findViewById(layout_button).setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     cancelTimer();
                     alertDialog.dismiss();
                     Log.d("quiz_over","shown_layoutbutton");
                     ShowDialog showDialog = new ShowDialog(context,R.layout.quiz_over,R.id.close_quiz_over_popup,quiz_score,quiz_highscore);
                     showDialog.getShowDialog();
                 }
             });

         }


        if(layout_resource == R.layout.quiz_over) {
            alertDialog.setCancelable(false);


            dialogView.findViewById(layout_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                    listener.setMusicPlayer(seven);

                }
            });

            dialogView.findViewById(R.id.play_again).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                    listener.setMusicPlayer(eight);

                }
            });


            ImageView your_score_one = dialogView.findViewById(R.id.your_score_one);
            ImageView your_score_two = dialogView.findViewById(R.id.your_score_two);
            ImageView your_score_three = dialogView.findViewById(R.id.your_score_three);
            ImageView your_score_four = dialogView.findViewById(R.id.your_score_four);
            //ImageView[] hold_scores = {your_score_one,your_score_two};

            ImageView your_highscore_one = dialogView.findViewById(R.id.your_highscore_one);
            ImageView your_highscore_two = dialogView.findViewById(R.id.your_highscore_two);
            ImageView your_highscore_three = dialogView.findViewById(R.id.your_highscore_three);
            ImageView your_highscore_four = dialogView.findViewById(R.id.your_highscore_four);


            String score_str = String.valueOf(quiz_score);
            String highscore_str = String.valueOf(quiz_highscore);

            ConstraintLayout constraintLayout = dialogView.findViewById(R.id.quiz_over);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);

            switch(score_str.length()) {

                case two:


                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(R.id.your_score_one,ConstraintSet.END,R.id.your_score_two,ConstraintSet.START,0);
                    constraintSet.connect(R.id.your_score_two,ConstraintSet.END,R.id.guidelinev65_percent,ConstraintSet.START,0);
                    constraintSet.applyTo(constraintLayout);
                    break;
                case three:

                    constraintSet.connect(R.id.your_score_one,ConstraintSet.END,R.id.your_score_two,ConstraintSet.START,0);
                    constraintSet.connect(R.id.your_score_two,ConstraintSet.END,R.id.your_score_three,ConstraintSet.START,0);
                    constraintSet.connect(R.id.your_score_three,ConstraintSet.END,R.id.guidelinev65_percent,ConstraintSet.START,0);
                    constraintSet.applyTo(constraintLayout);
                    break;
                case four:

                    constraintSet.connect(R.id.your_score_one,ConstraintSet.END,R.id.your_score_two,ConstraintSet.START,0);
                    constraintSet.connect(R.id.your_score_two,ConstraintSet.END,R.id.your_score_three,ConstraintSet.START,0);
                    constraintSet.connect(R.id.your_score_three,ConstraintSet.END,R.id.your_score_four,ConstraintSet.START,0);
                    constraintSet.connect(R.id.your_score_four,ConstraintSet.END,R.id.guidelinev65_percent,ConstraintSet.START,0);
                    constraintSet.applyTo(constraintLayout);
                    break;
            }

            switch(highscore_str.length()) {

                case two:


                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(R.id.your_highscore_one,ConstraintSet.END,R.id.your_highscore_two,ConstraintSet.START,0);
                    constraintSet.connect(R.id.your_highscore_two,ConstraintSet.END,R.id.guidelinev65_percent,ConstraintSet.START,0);
                    constraintSet.applyTo(constraintLayout);
                    break;
                case three:

                    constraintSet.connect(R.id.your_highscore_one,ConstraintSet.END,R.id.your_highscore_two,ConstraintSet.START,0);
                    constraintSet.connect(R.id.your_highscore_two,ConstraintSet.END,R.id.your_highscore_three,ConstraintSet.START,0);
                    constraintSet.connect(R.id.your_highscore_three,ConstraintSet.END,R.id.guidelinev65_percent,ConstraintSet.START,0);
                    constraintSet.applyTo(constraintLayout);
                    break;
                case four:

                    constraintSet.connect(R.id.your_highscore_one,ConstraintSet.END,R.id.your_highscore_two,ConstraintSet.START,0);
                    constraintSet.connect(R.id.your_highscore_two,ConstraintSet.END,R.id.your_highscore_three,ConstraintSet.START,0);
                    constraintSet.connect(R.id.your_highscore_three,ConstraintSet.END,R.id.your_highscore_four,ConstraintSet.START,0);
                    constraintSet.connect(R.id.your_highscore_four,ConstraintSet.END,R.id.guidelinev65_percent,ConstraintSet.START,0);
                    constraintSet.applyTo(constraintLayout);
                    break;
            }


            // String your_highscore_str = String.valueOf(your_highscore);
            //i represents position of individual cgaracter in string
            //eg. "431" 4 - zero 3 - one 1 - two
            for(int i = 0; i < score_str.length(); i++) {

                String num = String.valueOf(score_str.charAt(i));

                switch(i) {
                    case zero:
                        setImage(Integer.parseInt(num),your_score_one);
                        break;
                    case one:
                        setImage(Integer.parseInt(num),your_score_two);
                        break;
                    case two:
                        setImage(Integer.parseInt(num),your_score_three);
                        break;
                    case three:
                        setImage(Integer.parseInt(num),your_score_four);
                        break;
                }

            }

            for(int i = 0; i < highscore_str.length(); i++) {

                String num = String.valueOf(highscore_str.charAt(i));

                switch(i) {
                    case zero:
                        setImage(Integer.parseInt(num),your_highscore_one);
                        break;
                    case one:
                        setImage(Integer.parseInt(num),your_highscore_two);
                        break;
                    case two:
                        setImage(Integer.parseInt(num),your_highscore_three);
                        break;
                    case three:
                        setImage(Integer.parseInt(num),your_highscore_four);
                        break;
                }

            }



        }




        if(layout_resource == R.layout.modes_popup) {
            alertDialog.setCancelable(false);
            close_button = dialogView.findViewById(layout_button);
            closedButtonListener = new ClosedButtonListener(
                    close_button,
                    layout_button,
                    context,
                    this
            );
            close_button.setOnTouchListener(closedButtonListener);

            dialogView.findViewById(R.id.mode_button_one).setOnClickListener(v -> {

                listener.setMusicPlayer(3);
                alertDialog.dismiss();

            });

            dialogView.findViewById(R.id.mode_button_two).setOnClickListener(v -> {

                listener.setMusicPlayer(4);
                alertDialog.dismiss();

            });

            dialogView.findViewById(R.id.mode_button_three).setOnClickListener(v -> {

                listener.setMusicPlayer(5);
                alertDialog.dismiss();

            });
        }


         if(layout_resource == R.layout.neutral_age_screen) {
             alertDialog.setCancelable(false);

             Spinner months_dropdown = dialogView.findViewById(R.id.months_dropdown);
             ArrayAdapter<CharSequence> months_adapter = ArrayAdapter.createFromResource(context,
                     R.array.months, R.layout.birth_spinner);
             months_adapter.setDropDownViewResource(R.layout.spinner_item);
             months_dropdown.setAdapter(months_adapter);
             months_dropdown.setOnItemSelectedListener(this);

             dialogView.findViewById(layout_button).setOnClickListener(v -> {
                 EditText month_editText = dialogView.findViewById(R.id.month_born);
                 EditText editText = dialogView.findViewById(R.id.year_born);

                 Editable user_month_born = month_editText.getText();
                 Editable user_year_born = editText.getText();

                 int user_month_born_len = user_month_born.length();
                 String string_user_month_born = String.valueOf(user_month_born);
                 boolean month_digits = TextUtils.isDigitsOnly(user_month_born);

                 int user_year_born_len = user_year_born.length();
                 String string_user_year_born = String.valueOf(user_year_born);
                 boolean digits = TextUtils.isDigitsOnly(user_year_born);
                 boolean month_successful = false;
                 boolean year_successful = false;




                 if(user_year_born_len == four) {

                     if(digits) {

                         if(string_user_year_born.startsWith("19") || string_user_year_born.startsWith("20")) {
                             year_successful = true;
                             if ((Integer.parseInt(string_user_year_born) > context.getResources().getInteger(R.integer.current_year))) {
                                         year_successful = false;
                             }

                         }
                     }

                 }


                 if(month_digits) {
                     if (user_month_born_len == two) {
                         if(string_user_month_born.startsWith("1") || string_user_month_born.startsWith("2") || string_user_month_born.startsWith("3")) {
                             int month_born = Integer.parseInt(string_user_month_born);
                             switch(month_position) {
                                 case zero:
                                 case two:
                                 case four:
                                 case six:
                                 case seven:
                                 case nine:
                                 case eleven:
                                     month_successful = month_born <= 31;
                                     break;
                                 case three:
                                 case five:
                                 case eight:
                                 case ten:
                                     month_successful = month_born <= 30;
                                     break;
                                 case one:
                                     int current_year = context.getResources().getInteger(R.integer.current_year);
                                     month_successful = month_born <= 28;
                                     if(year_successful) {
                                         for (int i = 1900; i <= current_year; i = i + 4) {
                                             if (Integer.parseInt(string_user_year_born) == i) {
                                                 month_successful = month_born <= 29;
                                                 break;
                                             }
                                         }
                                     }
                                     break;
                             }

                         }
                     } else if(user_month_born_len == one) {
                        month_successful = string_user_month_born.matches("^[1-9]");
                     }
                 }




                 ImageView date_of_birth_error = dialogView.findViewById(R.id.date_of_birth_error);

                 animBlink = AnimationUtils.loadAnimation(context,R.anim.blink);
                 if(!month_successful && !year_successful) {
                     date_of_birth_error.setImageResource(R.drawable.ic_correct_year_month);
                     month_editText.setBackground(ContextCompat.getDrawable(context,R.drawable.birth_border_red));
                     month_editText.setAnimation(animBlink);


                     editText.setBackground(ContextCompat.getDrawable(context,R.drawable.birth_border_red));
                     editText.setAnimation(animBlink);

                 } else if(!month_successful) {
                     editText.clearAnimation();
                     editText.setBackground(ContextCompat.getDrawable(context,R.drawable.birth_border));

                     month_editText.setBackground(ContextCompat.getDrawable(context,R.drawable.birth_border_red));
                     month_editText.setAnimation(animBlink);
                     date_of_birth_error.setImageResource(R.drawable.ic_correct__month);
                 } else if(!year_successful) {
                     month_editText.clearAnimation();
                     month_editText.setBackground(ContextCompat.getDrawable(context,R.drawable.birth_border));
                     date_of_birth_error.setImageResource(R.drawable.ic_correct_year);
                     editText.setBackground(ContextCompat.getDrawable(context,R.drawable.birth_border_red));
                     editText.setAnimation(animBlink);
                 } else if(year_successful && month_successful) {
                         int year_born = Integer.parseInt(string_user_year_born);
                         int current_year = context.getResources().getInteger(R.integer.current_year);
                         int user_age = current_year - year_born;
                         editor.putInt("usra", user_age).apply();
                         editor.putBoolean("avc",true).apply();
                         listener.birthDateCompleted(one);
                         alertDialog.dismiss();

                 } 

             });
         }





        if(layout_resource == R.layout.settings) {
            dialogView.findViewById(R.id.music_credits).setOnClickListener(v -> {

                alertDialog.dismiss();
                ShowDialog showCreditsDialog = new ShowDialog(context,R.layout.credits,R.id.close_credits_popup);
                showCreditsDialog.getShowDialog();




            });

            dialogView.findViewById(layout_button).setOnClickListener(v ->  alertDialog.dismiss());
        }


      /*   if(layout_resource == R.layout.about_menu) {
             dialogView.findViewById(R.id.go_to_music_credits).setOnClickListener(v -> {

                     alertDialog.dismiss();
                     ShowDialog showCreditsDialog = new ShowDialog(context,R.layout.music_licenses,R.id.next_music_settings);
                     showCreditsDialog.getShowDialog();




             });

             dialogView.findViewById(layout_button).setOnClickListener(v ->  alertDialog.dismiss());
         }*/

    /*     if(layout_resource == R.layout.music_licenses) {

             TextView text_view_link_one = dialogView.findViewById(R.id.link_one);
             CustomTextView custom_link_one = new CustomTextView(text_view_link_one, R.id.link_one);
             custom_link_one.setCustomTextView();

             TextView text_view_link_two = dialogView.findViewById(R.id.link_two);
             CustomTextView custom_link_two = new CustomTextView(text_view_link_two, R.id.link_two);
             custom_link_two.setCustomTextView();

             TextView text_view_link_three = dialogView.findViewById(R.id.link_three);
             CustomTextView custom_link_three = new CustomTextView(text_view_link_three, R.id.link_three);
             custom_link_three.setCustomTextView();

            *//* TextView text_view_link_four = dialogView.findViewById(R.id.link_four);
             CustomTextView custom_link_four = new CustomTextView(text_view_link_four, R.id.link_four);
             custom_link_four.setCustomTextView();

             TextView text_view_link_five = dialogView.findViewById(R.id.link_five);
             CustomTextView custom_link_five = new CustomTextView(text_view_link_five, R.id.link_five);
             custom_link_five.setCustomTextView();

             TextView text_view_link_six = dialogView.findViewById(R.id.link_six);
             CustomTextView custom_link_six = new CustomTextView(text_view_link_six, R.id.link_six);
             custom_link_six.setCustomTextView();
*//*
             //customTextView(textView);
             dialogView.findViewById(layout_button).setOnClickListener(v -> {
                 alertDialog.dismiss();
                 ShowDialog showCreditsDialogTwo = new ShowDialog(context,R.layout.music_licenses_two,R.id.back_to_settings);
                 showCreditsDialogTwo.getShowDialog();
                     });
         }*/

         if(layout_resource == R.layout.credits) {

             alertDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
             TextView text_view_link_one = dialogView.findViewById(R.id.link_one);
             CustomTextView custom_link_one = new CustomTextView(text_view_link_one, R.id.link_one);
             custom_link_one.setCustomTextView();

             TextView text_view_link_two = dialogView.findViewById(R.id.link_two);
             CustomTextView custom_link_two = new CustomTextView(text_view_link_two, R.id.link_two);
             custom_link_two.setCustomTextView();

             TextView text_view_link_three = dialogView.findViewById(R.id.link_three);
             CustomTextView custom_link_three = new CustomTextView(text_view_link_three, R.id.link_three);
             custom_link_three.setCustomTextView();

             TextView text_view_link_four = dialogView.findViewById(R.id.link_four);
             CustomTextView custom_link_four = new CustomTextView(text_view_link_four, R.id.link_four);
             custom_link_four.setCustomTextView();

             TextView text_view_link_five = dialogView.findViewById(R.id.link_five);
             CustomTextView custom_link_five = new CustomTextView(text_view_link_five, R.id.link_five);
             custom_link_five.setCustomTextView();

             TextView text_view_link_six = dialogView.findViewById(R.id.link_six);
             CustomTextView custom_link_six = new CustomTextView(text_view_link_six, R.id.link_six);
             custom_link_six.setCustomTextView();

             //customTextView(textView);
             dialogView.findViewById(layout_button).setOnClickListener(v -> {
                 alertDialog.dismiss();
                 /*ShowDialog showCreditsDialogTwo = new ShowDialog(context,R.layout.music_licenses_two,R.id.back_to_settings);
                 showCreditsDialogTwo.getShowDialog();*/
             });
         }



        if(layout_resource == R.layout.updated_menu_helper) {

            close_button = dialogView.findViewById(layout_button);
            closedButtonListener = new ClosedButtonListener(
                    close_button,
                    layout_button,
                    context,
                    this
            );
            close_button.setOnTouchListener(closedButtonListener);

        }




         if(layout_resource == R.layout.success_chapter_bought) {
             dialogView.findViewById(layout_button).setOnClickListener(v -> {

                 listener.setTransactionCode(RESTART_ACTIVITY);
                 alertDialog.dismiss();

             });
         }


        if(layout_resource == R.layout.store) {
            alertDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

            alertDialog.setCancelable(false);


            billing_client();


            dialogView.findViewById(R.id.gems100).setOnClickListener(v -> {

                if(list_size == six) {
                    product_bought = itemInfo1.getTitle().trim();
                    queryPurchase();
                    billingClient.launchBillingFlow(
                            activity,
                            BillingFlowParams.newBuilder().setSkuDetails(itemInfo1).build()
                    );
                } else {
                    billing_client();
                    toastConnectionError();
                }

            });

            dialogView.findViewById(R.id.gems500).setOnClickListener(v -> {
                if(list_size == six) {
                    product_bought = itemInfo2.getTitle().trim();
                    queryPurchase();

                    billingClient.launchBillingFlow(
                            activity,
                            BillingFlowParams.newBuilder().setSkuDetails(itemInfo2).build()
                    );
                } else {
                    billing_client();
                    toastConnectionError();
                }
            });

            dialogView.findViewById(R.id.gems1000).setOnClickListener(v -> {
                if(list_size == six) {
                    product_bought = itemInfo3.getTitle().trim();
                    queryPurchase();
                    billingClient.launchBillingFlow(
                            activity,
                            BillingFlowParams.newBuilder().setSkuDetails(itemInfo3).build()
                    );
                } else {
                    billing_client();
                    toastConnectionError();
                }
            });

            dialogView.findViewById(R.id.gems3000).setOnClickListener(v -> {
                if(list_size == six) {
                    product_bought = itemInfo4.getTitle().trim();
                    queryPurchase();
                    billingClient.launchBillingFlow(
                            activity,
                            BillingFlowParams.newBuilder().setSkuDetails(itemInfo4).build()
                    );
                } else {
                    billing_client();
                    toastConnectionError();
                }
            });

            dialogView.findViewById(R.id.hearts5).setOnClickListener(v -> {
                if(list_size == six) {
                    product_bought = itemInfo5.getTitle().trim();
                    queryPurchase();
                    billingClient.launchBillingFlow(
                            activity,
                            BillingFlowParams.newBuilder().setSkuDetails(itemInfo5).build()
                    );
                } else {
                    billing_client();
                    toastConnectionError();
                }
            });

            dialogView.findViewById(R.id.hearts10).setOnClickListener(v -> {
                if(list_size == six) {
                    product_bought = itemInfo6.getTitle().trim();
                    queryPurchase();
                    billingClient.launchBillingFlow(
                            activity,
                            BillingFlowParams.newBuilder().setSkuDetails(itemInfo6).build()
                    );
                } else {
                    billing_client();
                    toastConnectionError();
                }
            });


            dialogView.findViewById(layout_button).setOnClickListener(v -> alertDialog.dismiss());
        }


        if(layout_resource == R.layout.buy_chapter) {
            dialogView.findViewById(R.id.pay_500_gems).setOnClickListener(v -> {

                listener.setTransactionCode(CHECK_AVAILABLE_GEMS_CODE);
                alertDialog.dismiss();

            });

            dialogView.findViewById(layout_button).setOnClickListener(v -> {

                alertDialog.dismiss();

            });
        }


        if(layout_resource == R.layout.not_enough_gems_popup) {
            dialogView.findViewById(R.id.go_to_store).setOnClickListener(v -> {

                listener.setTransactionCode(GTS_CODE);
                alertDialog.dismiss();

            });

            dialogView.findViewById(layout_button).setOnClickListener(v -> {


                alertDialog.dismiss();

            });
        }

        if(layout_resource == R.layout.purchased_gems_popup) {
            dialogView.findViewById(layout_button).setOnClickListener(v -> {

                alertDialog.dismiss();

            });
        }


        if(layout_resource == R.layout.settings) {


            ImageView music_icon = dialogView.findViewById(R.id.music_icon);
            ImageView sound_icon = dialogView.findViewById(R.id.sound_icon);
            if (sp.getBoolean(context.getString(R.string.music_pref_key), true)) {
                music_icon.setImageResource(R.drawable.music_iconxxxhdpi);

            } else if (!(sp.getBoolean(context.getString(R.string.music_pref_key), true))) {
                music_icon.setImageResource(R.drawable.no_music_iconxxxhdpi);

            }

            if (sp.getBoolean(context.getString(R.string.sound_pref_key), true)) {
                sound_icon.setImageResource(R.drawable.sound_iconxxxhdpi);

            } else if (!(sp.getBoolean(context.getString(R.string.sound_pref_key), true))) {
                sound_icon.setImageResource(R.drawable.no_sound_iconxxxhdpi);

            }


            dialogView.findViewById(layout_button).setOnClickListener(v -> alertDialog.dismiss());

        }

         if(layout_resource == R.layout.quizpert_table) {

             Spinner category_dropdown = dialogView.findViewById(R.id.category_dropdown);
             ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                     R.array.category_scores, R.layout.spinner);
             adapter.setDropDownViewResource(R.layout.spinner_item);
             category_dropdown.setAdapter(adapter);
             category_dropdown.setOnItemSelectedListener(this);

            dialogView.findViewById(R.id.close_score_menu).setOnClickListener(v -> alertDialog.dismiss());
         }

        if(layout_resource == R.layout.settings) {

            dialogView.findViewById(R.id.sound_icon).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ImageView sound_icon = dialogView.findViewById(R.id.sound_icon);

                    if (sp.getBoolean(context.getString(R.string.sound_pref_key), true)) {

                        PROCESS_NUMBER = 4;
                        editor.putBoolean(context.getString(R.string.sound_pref_key), false).apply();
                        sound_icon.setImageResource(R.drawable.no_sound_iconxxxhdpi);

                    } else {

                        PROCESS_NUMBER = 3;
                        editor.putBoolean(context.getString(R.string.sound_pref_key), true).apply();
                        sound_icon.setImageResource(R.drawable.sound_iconxxxhdpi);

                    }
                    editSharedPreference(PROCESS_NUMBER);
                }


            });

            dialogView.findViewById(R.id.music_icon).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ImageView music_icon = dialogView.findViewById(R.id.music_icon);

                    if (sp.getBoolean(context.getString(R.string.music_pref_key), true)) {

                        PROCESS_NUMBER = 2;
                        editor.putBoolean(context.getString(R.string.music_pref_key), false).apply();
                        music_icon.setImageResource(R.drawable.no_music_iconxxxhdpi);

                    } else {

                        PROCESS_NUMBER = 1;
                        editor.putBoolean(context.getString(R.string.music_pref_key), true).apply();
                        music_icon.setImageResource(R.drawable.music_iconxxxhdpi);

                    }
                    editSharedPreference(PROCESS_NUMBER);
                    listener.setMusicPlayer(PROCESS_NUMBER);

                }

            });

            dialogView.findViewById(layout_button).setOnClickListener(v -> alertDialog.dismiss());

        }



     }


     private void editSharedPreference(int music_code) {
       // SharedPreferences  sp = PreferenceManager.getDefaultSharedPreferences(context);
        //SharedPreferences.Editor editor = sp.edit();

        switch (music_code) {
            case 1:
                editor.putBoolean(context.getString(R.string.music_pref_key),true).apply();
                break;
            case 2:
                editor.putBoolean(context.getString(R.string.music_pref_key),false).apply();
                break;
            case 3:
                editor.putBoolean(context.getString(R.string.sound_pref_key),true).apply();
                break;
            case 4:
                editor.putBoolean(context.getString(R.string.sound_pref_key),false).apply();
                break;

        }


     }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ShowDialogListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        parent.getItemAtPosition(position);

        if(layout_resource == R.layout.neutral_age_screen) {
            month_position = position;
        }


        if(layout_resource == R.layout.quizpert_table) {


            String[] category_attempts = {};
            String[] category_highscore = {};
            String[] category_trophy = {};


            switch (position) {
                case 0:

                    category_attempts = context.getResources().getStringArray(R.array.sci_category_attempts);
                    category_highscore = context.getResources().getStringArray(R.array.sci_category_highscore);
                    category_trophy = context.getResources().getStringArray(R.array.sci_category_trophy);

                    break;
                case 1:

                    category_attempts = context.getResources().getStringArray(R.array.mus_category_attempts);
                    category_highscore = context.getResources().getStringArray(R.array.mus_category_highscore);
                    category_trophy = context.getResources().getStringArray(R.array.mus_category_trophy);

                    break;
                case 2:

                    category_attempts = context.getResources().getStringArray(R.array.his_category_attempts);
                    category_highscore = context.getResources().getStringArray(R.array.his_category_highscore);
                    category_trophy = context.getResources().getStringArray(R.array.his_category_trophy);

                    break;
                case 3:

                    category_attempts = context.getResources().getStringArray(R.array.math_category_attempts);
                    category_highscore = context.getResources().getStringArray(R.array.math_category_highscore);
                    category_trophy = context.getResources().getStringArray(R.array.math_category_trophy);

                    break;
                case 4:

                    category_attempts = context.getResources().getStringArray(R.array.art_category_attempts);
                    category_highscore = context.getResources().getStringArray(R.array.art_category_highscore);
                    category_trophy = context.getResources().getStringArray(R.array.art_category_trophy);

                    break;
                case 5:

                    category_attempts = context.getResources().getStringArray(R.array.geo_category_attempts);
                    category_highscore = context.getResources().getStringArray(R.array.geo_category_highscore);
                    category_trophy = context.getResources().getStringArray(R.array.geo_category_trophy);

                    break;

            }

            int[] attempts = {R.id.attempt_one, R.id.attempt_two, R.id.attempt_three, R.id.attempt_four, R.id.attempt_five,
                    R.id.attempt_six, R.id.attempt_seven, R.id.attempt_eight};

            int[] highscores = {R.id.highscore_one, R.id.highscore_two, R.id.highscore_three, R.id.highscore_four,
                    R.id.highscore_five, R.id.highscore_six, R.id.highscore_seven, R.id.highscore_eight};

            int[] trophy = {R.id.trophy_one, R.id.trophy_two, R.id.trophy_three, R.id.trophy_four,
                    R.id.trophy_five, R.id.trophy_six, R.id.trophy_seven, R.id.trophy_eight};

            int count = 0;
            while (count < category_attempts.length) {

                TextView attribute_one = parent.getRootView().findViewById(attempts[count]);
                TextView attribute_two = parent.getRootView().findViewById(highscores[count]);
                ImageView attribute_three = parent.getRootView().findViewById(trophy[count]);

                attribute_one.setText(sp.getString(category_attempts[count], dash));
                attribute_two.setText(sp.getString(category_highscore[count], dash));
                if (sp.getString(category_trophy[count], dash).equals(yes)) {
                    attribute_three.setImageResource(R.drawable.ic_yes_trophy);
                } else {
                    attribute_three.setImageResource(R.drawable.ic_no_trophy);
                }

                //attribute_three.setText(sp.getString(category_trophy[count],"-"));
                count++;
            }

        }


    }//

    private void queryPurchase() {
        billingClient.queryPurchasesAsync(
                INAPP,
                new PurchasesResponseListener() {
                    @Override
                    public void onQueryPurchasesResponse(@NonNull BillingResult billingResult, @NonNull List<Purchase> list) {
                        if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {

                            int count = -1;
                            int num_of_purchases = list.size();


                            for(Purchase purchase: list) {
                                if(count == num_of_purchases - 1) {
                                    count = num_of_purchases - 1;
                                }

                                int index= purchaseItemIDs.indexOf(purchase.getSkus().get(count + 1));
                                count = count + 1;
                                if(index > - 1) {
                                    if (!purchase.isAcknowledged()) {
                                        ConsumeParams consumeParams = ConsumeParams.newBuilder()
                                                .setPurchaseToken(purchase.getPurchaseToken())
                                                .build();
                                        billingClient.consumeAsync(consumeParams, new ConsumeResponseListener() {
                                            @Override
                                            public void onConsumeResponse(@NotNull BillingResult billingResult, @NotNull String purchaseToken) {
                                                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {


                                                    int consume_count_value = sp.getInt(purchaseItemIDs.get(index), 0) + one;
                                                    editor.putInt(purchaseItemIDs.get(index), consume_count_value).apply();
                                                    Toast.makeText(context, "Item " + purchaseItemIDs.get(index) + "Consumed", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                        }
                    }
                }
        );
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    public interface ShowDialogListener  {
        void setMusicPlayer(int MUSIC_PLAYER_CODE);
        void setTransactionCode(int TRANSACTION_CODE);
        void birthDateCompleted(int BIRTH_DATE_CODE);
    }

    @Override
    public void onResume() {
        queryPurchase();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
