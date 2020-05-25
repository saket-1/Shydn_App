package com.shydn.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    private DrawerLayout navdraw;
    private HomePageAdapter adapter;
    private FirebaseFirestore firebaseFirestore;
    public static boolean KEY_VERIFY;

    private TextView header_name;

    /////Datalist
    /*
    private RecyclerView datalist;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mlayoutManager;
    */


    //////Datalist
    ///final Banner//
    private ViewPager slider;
    private List<Slider_model> sliderModelList;
    private  int currentpage = 2;
    private Timer timer;
    final  private  long Delay_Time = 3000;
    final  private  long  Period_time = 3000;

    ///final Banner//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        setContentView(R.layout.nav_drawer_layout);
       header_name = findViewById(R.id.nav_header_name_a);

        KEY_VERIFY = getIntent().getBooleanExtra("verify",false);


        //////datalist ff


        //GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);

//        List<DataModel>dataModelList = new ArrayList<>();
//        dataModelList.add(new DataModel(R.drawable.ic_home,"Samsung TV","8k tv","Rs.5000"));
//        dataModelList.add(new DataModel(R.drawable.ic_home,"Samsung TV","8k tv","Rs.5000"));
//        dataModelList.add(new DataModel(R.drawable.ic_home,"Samsung TV","8k tv","Rs.5000"));
//        dataModelList.add(new DataModel(R.drawable.ic_home,"Samsung TV","8k tv","Rs.5000"));
//        dataModelList.add(new DataModel(R.drawable.ic_home,"Samsung TV","8k tv","Rs.5000"));
//        dataModelList.add(new DataModel(R.drawable.ic_home,"Samsung TV","8k tv","Rs.5000"));
//        dataModelList.add(new DataModel(R.drawable.ic_home,"Samsung TV","8k tv","Rs.5000"));
//        dataModelList.add(new DataModel(R.drawable.ic_home,"Samsung TV","8k tv","Rs.5000"));
//        dataModelList.add(new DataModel(R.drawable.ic_home,"Samsung TV","8k tv","Rs.5000"));
//        dataModelList.add(new DataModel(R.drawable.ic_home,"Samsung TV","8k tv","Rs.5000"));

      /*  datalist = findViewById(R.id.datalist);
        mlayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        madapter = new DataAdapter(dataModelList);
        datalist.setLayoutManager(mlayoutManager);
        datalist.setAdapter(madapter);

*/
      ///////

        ///////prpductscrollmodel
        /*
        final List<ProductScrollModel> productScrollModelList = new ArrayList<>();
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_home,"SamsungTV","8K TV","Rs.50000"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_about_us,"SamsungTV","8K TV","Rs.5000"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_account_,"SamsungTV","8K TV","Rs.5000"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_logout,"SamsungTV","8K TV","Rs5000"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_lrandom,"SamsungTV","8K TV","Rs.5000"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_notification,"SamsungTV","8K TV","500"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_order,"SamsungTV","8K TV","500"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_wishlist,"SamsungTV","8K TV","500"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_lrandom,"SamsungTV","8K TV","500"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_notification,"SamsungTV","8K TV","500"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_order,"SamsungTV","8K TV","500"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_wishlist,"SamsungTV","8K TV","500"));

         */
        /////productscrollmodel
        //////
        /*

        final List<ProductScrollModel> List = new ArrayList<>();
        List.add(new ProductScrollModel(R.drawable.ic_home,"SonyTV","4K TV","Rs.10000"));
        List.add(new ProductScrollModel(R.drawable.ic_home,"SonyTV","4K TV","Rs.10000"));
        List.add(new ProductScrollModel(R.drawable.ic_home,"SonyTV","4K TV","Rs.10000"));
        List.add(new ProductScrollModel(R.drawable.ic_home,"SonyTV","4K TV","Rs.10000"));
        */

        //////datalist ff

        ////Final Banner
        slider = findViewById(R.id.banner_slider_viewpager);
        /*
        sliderModelList = new ArrayList<Slider_model>();

        sliderModelList.add(new Slider_model(R.drawable.ic_order,"#077AE4"));
        sliderModelList.add(new Slider_model(R.drawable.sh_bn,"#077AE4"));
        sliderModelList.add(new Slider_model(R.drawable.ic_home,"#077AE4"));

        sliderModelList.add(new Slider_model(R.drawable.ic_account_,"#077AE4"));
        sliderModelList.add(new Slider_model(R.drawable.ic_contact,"#077AE4"));
        sliderModelList.add(new Slider_model(R.drawable.ic_message,"#077AE4"));
        sliderModelList.add(new Slider_model(R.drawable.ic_notification,"#077AE4"));
        sliderModelList.add(new Slider_model(R.drawable.ic_about_us,"#077AE4"));
        sliderModelList.add(new Slider_model(R.drawable.ic_order,"#077AE4"));

        sliderModelList.add(new Slider_model(R.drawable.sh_bn,"#077AE4"));
        sliderModelList.add(new Slider_model(R.drawable.ic_home,"#077AE4"));
        sliderModelList.add(new Slider_model(R.drawable.ic_account_,"#077AE4"));

         */
        ///Final Banner

        /////Final Banner
        RecyclerView homepageRecyclerView = findViewById(R.id.testing);
        LinearLayoutManager testinglayoutmanager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        homepageRecyclerView.setLayoutManager(testinglayoutmanager);

        final List <HomePageModel> homePageModelList = new ArrayList<>();
        /*
        homePageModelList.add(new HomePageModel(0,sliderModelList));

         */





         adapter = new HomePageAdapter(homePageModelList);
        homepageRecyclerView.setAdapter(adapter);
        firebaseFirestore.collection("Home").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        if((long)documentSnapshot.get("view_type") == 0){
                            List<Slider_model>sliderModelList = new ArrayList<>();
                            long no_of_slider =(long)documentSnapshot.get("no_of_sliders");
                            for(long x=1; x<=no_of_slider;x++){
                                sliderModelList.add(new Slider_model(documentSnapshot.get("slider_"+x).toString()
                                        ,documentSnapshot.get("slider_"+x+"_background").toString()));
                            }
                            homePageModelList.add(new HomePageModel(0,sliderModelList));

                        }
                        for (long y=1;y<=4;y++) {
                            if ((long) documentSnapshot.get("view_type") == y) {
                                List<ProductScrollModel> productScrollModelList = new ArrayList<>();
                                for (long x = 1; x <= 4; x++) {
                                    productScrollModelList.add(new ProductScrollModel(documentSnapshot.get("product_image_" + x).toString()
                                            , documentSnapshot.get("product_title_" + x).toString(), documentSnapshot.get("product_description_" + x).toString()
                                            , documentSnapshot.get("product_price_" + x).toString()));
                                }

                                homePageModelList.add(new HomePageModel(1, documentSnapshot.get("heading_"+y).toString(), productScrollModelList));

                            }
                        }
                        /*
                        else if((long)documentSnapshot.get("view_type") == 2){
                            List<ProductScrollModel>productScrollModelList = new ArrayList<>();
                            for(long x=1;x<=4;x++ ){
                                productScrollModelList.add(new ProductScrollModel(documentSnapshot.get("product_image_"+x).toString()
                                        ,documentSnapshot.get("product_title_"+x).toString(),documentSnapshot.get("product_description_"+x).toString()
                                        ,documentSnapshot.get("product_price_"+x).toString()));
                            }
                            homePageModelList.add(new HomePageModel(1,documentSnapshot.get("heading_2").toString(),productScrollModelList));

                        }
                        else if((long)documentSnapshot.get("view_type") == 3){
                            List<ProductScrollModel>productScrollModelList = new ArrayList<>();
                            for(long x=1;x<=4;x++ ){
                                productScrollModelList.add(new ProductScrollModel(documentSnapshot.get("product_image_"+x).toString()
                                        ,documentSnapshot.get("product_title_"+x).toString(),documentSnapshot.get("product_description_"+x).toString()
                                        ,documentSnapshot.get("product_price_"+x).toString()));
                            }
                            homePageModelList.add(new HomePageModel(1,documentSnapshot.get("heading_3").toString(),productScrollModelList));

                        }
                        else if((long)documentSnapshot.get("view_type") == 4){
                            List<ProductScrollModel>productScrollModelList = new ArrayList<>();
                            for(long x=1;x<=4;x++ ){
                                productScrollModelList.add(new ProductScrollModel(documentSnapshot.get("product_image_"+x).toString()
                                        ,documentSnapshot.get("product_title_"+x).toString(),documentSnapshot.get("product_description_"+x).toString()
                                        ,documentSnapshot.get("product_price_"+x).toString()));
                            }
                            homePageModelList.add(new HomePageModel(1,documentSnapshot.get("heading_4").toString(),productScrollModelList));

                        }

                         */



                    }
                    adapter.notifyDataSetChanged();
                }else {

                }

            }
        });




        ////Final Banner
        ////toolbar
        Toolbar toolbar =(Toolbar) findViewById(R.id.main_tool_bar);
        setSupportActionBar(toolbar);
        ///toolbar
        ////Navigation Drawer
        navdraw = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,navdraw,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
          navdraw.addDrawerListener(toggle);
          toggle.syncState();
          navigationView.setNavigationItemSelectedListener(this);
          ////Navigation Drawer

    }


    @Override
    public void onBackPressed() {
        if(navdraw.isDrawerOpen(GravityCompat.START)){
            navdraw.closeDrawer(GravityCompat.START);
        }

        else {
            //Intent intent = new Intent(MainActivity.this,LoginActivity.class);
          //  startActivity(intent);
           // finish();
            super.onBackPressed();
        }


    }


/*
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null)
        {
            Intent startintent = new Intent(MainActivity.this,ProfileActivity.class);
            startActivity(startintent);
            finish();
        }

    }

 */

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).addToBackStack(null).commit();
                Toast.makeText(getApplicationContext(),"Yet to be implemented",Toast.LENGTH_LONG).show();

                break;
            case R.id.nav_home:
                Intent intent =  new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_products:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProductFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_order:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new OrderFragment()).addToBackStack(null).commit();
                //Toast.makeText(this, "This Features will be soon out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_login:
               Intent intenta = new Intent(this,PhoneActivity.class);
               startActivity(intenta);
               finish();
               // Toast.makeText(this, "Some changes are happening. This Features will be soon out", Toast.LENGTH_SHORT).show();
               break;
            case R.id.nav_contact:
                Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_about_us:
                Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LogoutFragment()).addToBackStack(null).commit();
                break;


        }
        navdraw.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().signOut();
    }
}

/*
private textview layouttitle;
private recyclerview VerticalRecylerview

 */