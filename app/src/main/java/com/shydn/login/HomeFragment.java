package com.shydn.login;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }
    /////Data List///


    ////Data List///
    ////////// Slider
    private ViewPager slider;
    private List<Slider_model> sliderModelList;
    private  int currentpage = 2;
    private Timer timer;
    final  private  long Delay_Time = 3000;
    final  private  long  Period_time = 3000;
    ///grid


////////// Slider
    /////Strip AD
    private ImageView stripImage;
    private ConstraintLayout stripAdcont;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ////Slider
       slider = view.findViewById(R.id.banner_slider_viewpager);
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

/*
        Slider_Adapter slider_adapter = new Slider_Adapter(sliderModelList);
        slider.setAdapter(slider_adapter);
        slider.setClipToPadding(false);
        slider.setPageMargin(20);

        slider.setCurrentItem(currentpage);
*/
/*
        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentpage = position;

            }

            @Override
            public void onPageScrollStateChanged(int position) {
                if (position == ViewPager.SCROLL_STATE_IDLE){
                    pagelooper();
                }

            }
        };
        slider.addOnPageChangeListener(onPageChangeListener);
        startbannerSlideshow();
        slider.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                pagelooper();
                stopbannerSlideshow();
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    startbannerSlideshow();
                }
                return false;
            }
        });
*/
        ////slider

        /////strip

        ////strip

        ///////prpductscrollmodel
        /*
        List<ProductScrollModel> productScrollModelList = new ArrayList<>();
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_home,"SamsungTV","8K TV","500"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_about_us,"SamsungTV","8K TV","500"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_account_,"SamsungTV","8K TV","500"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_logout,"SamsungTV","8K TV","500"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_lrandom,"SamsungTV","8K TV","500"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_notification,"SamsungTV","8K TV","500"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_order,"SamsungTV","8K TV","500"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_wishlist,"SamsungTV","8K TV","500"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_lrandom,"SamsungTV","8K TV","500"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_notification,"SamsungTV","8K TV","500"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_order,"SamsungTV","8K TV","500"));
        productScrollModelList.add(new ProductScrollModel(R.drawable.ic_wishlist,"SamsungTV","8K TV","500"));

                 */
        /////productscrollmodel
        ////Grid Product Layout
        /*
        TextView gridlayoutTitle = view.findViewById(R.id.grid_layout_title);
        Button gridLayoutViewAll = view.findViewById(R.id.grid_layout_button);
        GridView gridView = view.findViewById(R.id.grid_product_gridview);
        gridView.setAdapter(new GridProductLayoutAdapter(productScrollModelList));
*/
        ///Grid Product Layout

        /////Recycler view
        /*
        RecyclerView testing = view.findViewById(R.id.testing);
        LinearLayoutManager testinglayoutmanager = new LinearLayoutManager(getContext());
        testinglayoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testinglayoutmanager);

        List <HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(1,"Saket",productScrollModelList));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        ////Recycler view
        ////Data list//

*/
        ////Data list///
        return view;
    }
    /////Banner
      private  void pagelooper (){
        if(currentpage == sliderModelList.size() -2){
            currentpage =2;
            slider.setCurrentItem(currentpage,false);
        }
        if(currentpage == 1){
            currentpage = sliderModelList.size() -3;
            slider.setCurrentItem(currentpage,false);
        }

      }

      private void startbannerSlideshow(){
          final Handler handler = new Handler();
          final Runnable update = new Runnable() {
              @Override
              public void run() {
                  if(currentpage >= sliderModelList.size()){
                      currentpage =1;
                  }
                  slider.setCurrentItem(currentpage++,true);
              }
          };
          timer = new Timer();
          timer.schedule(new TimerTask() {
              @Override
              public void run() {
                  handler.post(update);
              }
          },Delay_Time,Period_time);
      }
      private  void stopbannerSlideshow(){
        timer.cancel();
      }
    /////Banner
}
