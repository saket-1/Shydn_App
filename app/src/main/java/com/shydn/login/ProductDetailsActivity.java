package com.shydn.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView productPrice,productModel,productTitle,productDescription;
    private ImageView productImage;
    private ElegantNumberButton numberButton;
    private Button addToCartBtn;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference orderBookRef = db.collection("OrderBook");
    private DocumentReference orderRef;
    private long y;
    private long x;
    private String product_title;
    private String product_model;
    private String product_price;
    private String product_image;
    private String product_description;
    private String number;
    private FirebaseAuth firebaseAuth;
    public static String KEY_TITLE;
    public static String KEY_MODEL;
    public static String KEY_PRICE;
    public static String KEY_QUANTITY;
   // private static int a;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        firebaseFirestore = FirebaseFirestore.getInstance();
        addToCartBtn = (Button) findViewById(R.id.add_to_cart_btn);
        numberButton = (ElegantNumberButton) findViewById(R.id.number_btn);
        productImage = (ImageView) findViewById(R.id.cart_product_image_detail);
        productTitle = (TextView) findViewById(R.id.cart_product_title);
        productModel = (TextView) findViewById(R.id.cart_product_model);
        productPrice = (TextView) findViewById(R.id.cart_product_price_detail);
        productDescription = (TextView) findViewById(R.id.cart_product_description);
        SharedPreferences sharedPreferences =getSharedPreferences("MyData",MODE_PRIVATE);
        number = sharedPreferences.getString("mobile","");
        //orderRef = db.document("OrderBook/"+number);

        final Intent intent = getIntent();
        //String product_title = intent.getStringExtra("PRODUCT_TITLE");
        //String product_description = intent.getStringExtra("PRODUCT_DESCRIPTION");
        //String product_image = intent.getStringExtra("PRODUCT_IMAGE");
        //String product_price = intent.getStringExtra("PRODUCT_PRICE");
        long product_position = intent.getLongExtra("PRODUCT_POSITION",0);
        y = intent.getLongExtra("POSITION_OF_CATEGORY1",0);
        x = product_position+1;
if(MainActivity.KEY_VERIFY) {
    firebaseFirestore.collection("product_item").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                    if ((long) documentSnapshot.get("view_type") == y) {
                        product_image = documentSnapshot.get("product_image_" + x).toString();
                        product_title = documentSnapshot.get("product_title_" + x).toString();
                        product_model = documentSnapshot.get("product_model_" + x).toString();
                        product_price = documentSnapshot.get("product_price_" + x).toString();
                        product_description = documentSnapshot.get("product_description_" + x).toString();
                        getSupportActionBar().setTitle(documentSnapshot.get("heading").toString());

                    }

                }


                Glide.with(productImage.getContext()).load(product_image).apply(new RequestOptions().placeholder(R.drawable.ic_home)).into(productImage);
                productTitle.setText(product_title);
                productModel.setText(product_model);
                productPrice.setText(product_price);
                productDescription.setText(product_description);

                KEY_TITLE = product_title;
                KEY_MODEL = product_model;
                KEY_PRICE = product_price;

                ;

            } else {
                Toast.makeText(ProductDetailsActivity.this, "Internet issue", Toast.LENGTH_SHORT).show();

            }

        }
    });
} else{
    firebaseFirestore.collection("product_item").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                    if ((long) documentSnapshot.get("view_type") == y) {
                        product_image = documentSnapshot.get("product_image_" + x).toString();
                        product_title = documentSnapshot.get("product_title_" + x).toString();
                        product_model = documentSnapshot.get("product_model_" + x).toString();
                        product_price = documentSnapshot.get("product_price_" + x).toString();
                        product_description = documentSnapshot.get("product_description_" + x).toString();
                        getSupportActionBar().setTitle(documentSnapshot.get("heading").toString());

                    }

                }


                Glide.with(productImage.getContext()).load(product_image).apply(new RequestOptions().placeholder(R.drawable.ic_home_white)).into(productImage);
                productTitle.setText(product_title);
                productModel.setText(product_model);
                productPrice.setText("");
                productDescription.setText(product_description);

                KEY_TITLE = product_title;
                KEY_MODEL = product_model;
                KEY_PRICE = product_price;

                ;

            } else {
                Toast.makeText(ProductDetailsActivity.this, "Internet issue", Toast.LENGTH_SHORT).show();

            }

        }
    });
}


        numberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = numberButton.getNumber();
                KEY_QUANTITY = num;

            }
        });


    }

    public void saveOrder(){
        String title;
        String model;
        String price;
        String quantity;
        String number;
        long timeStamp;

        title =KEY_TITLE;
        model = KEY_MODEL;
        price = KEY_PRICE;
        quantity = KEY_QUANTITY;
        number = PhoneActivity.KEY_NUMBER;
        timeStamp = SystemClock.currentThreadTimeMillis();
        OrderBook orderBook = new OrderBook(title,model,price,quantity,timeStamp);
        orderBookRef.document(number).collection(number).document().set(orderBook)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ProductDetailsActivity.this, "Successfully Added to Cart", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProductDetailsActivity.this, "Could not add to cart", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void addOrder(View v){
        if(MainActivity.KEY_VERIFY) {
            saveOrder();
        } else {
            Toast.makeText(this, "Please Log in to order", Toast.LENGTH_SHORT).show();
        }


    }



}
