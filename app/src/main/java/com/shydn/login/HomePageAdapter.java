package com.shydn.login;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;



import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {
    private List<HomePageModel> homePageModelList;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (homePageModelList.get(position).getType()) {
            case 0:
                return HomePageModel.BANNER_SLIDER;
            case 1:
                return HomePageModel.GRID_PRODUCT_VIEW;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case HomePageModel.BANNER_SLIDER:
                View bannersliderview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sliding_ad_layout, viewGroup, false);
                return new BannerSliderViewholder(bannersliderview);
            case HomePageModel.GRID_PRODUCT_VIEW:
                View gridproductview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_product_layout, viewGroup, false);
                return new GridproductViewholder(gridproductview);

            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (homePageModelList.get(position).getType()) {
            case HomePageModel.BANNER_SLIDER:
                List<Slider_model> sliderModelList = homePageModelList.get(position).getSliderModelList();
                ((BannerSliderViewholder) viewHolder).setbannersliderviewpager(sliderModelList);
                break;
            case HomePageModel.GRID_PRODUCT_VIEW:
                String title = homePageModelList.get(position).getTitle();
                  List<ProductScrollModel>GridproductScrollModelList =homePageModelList.get(position).getProductScrollModelList();
                 ((GridproductViewholder)viewHolder).setGridProductLayout(GridproductScrollModelList,title);
                break;
            default:
                return;
        }

    }

    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }

    public class BannerSliderViewholder extends RecyclerView.ViewHolder {
        private ViewPager slider;
        private int currentpage ;
        private Timer timer;
        final private long Delay_Time = 3000;
        final private long Period_time = 3000;
        private List<Slider_model>arrangeList;

        public BannerSliderViewholder(@NonNull View itemView) {
            super(itemView);
            slider = itemView.findViewById(R.id.banner_slider_viewpager);
        }

        private void setbannersliderviewpager(final List<Slider_model> sliderModelList) {
            currentpage=2;
            if(timer!=null){
                timer.cancel();
            }
            arrangeList = new ArrayList<>();
            for(int x = 0; x < sliderModelList.size();x++){
                arrangeList.add(x,sliderModelList.get(x));
            }
            arrangeList.add(0,sliderModelList.get(sliderModelList.size()-2));
            arrangeList.add(1,sliderModelList.get(sliderModelList.size()-1));
            arrangeList.add(sliderModelList.get(0));
            arrangeList.add(sliderModelList.get(1));
            Slider_Adapter slider_adapter = new Slider_Adapter(arrangeList);
            slider.setAdapter(slider_adapter);
            slider.setClipToPadding(false);
            slider.setPageMargin(20);

            slider.setCurrentItem(currentpage);

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
                    if (position == ViewPager.SCROLL_STATE_IDLE) {
                        pagelooper(arrangeList);
                    }

                }
            };
            slider.addOnPageChangeListener(onPageChangeListener);
            startbannerSlideshow(arrangeList);
            slider.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    pagelooper(arrangeList);
                    stopbannerSlideshow();
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        startbannerSlideshow(arrangeList);
                    }
                    return false;
                }
            });
        }

        private void pagelooper(List<Slider_model> sliderModelList) {
            if (currentpage == sliderModelList.size() - 2) {
                currentpage = 2;
                slider.setCurrentItem(currentpage, false);
            }
            if (currentpage == 1) {
                currentpage = sliderModelList.size() - 3;
                slider.setCurrentItem(currentpage, false);
            }

        }

        private void startbannerSlideshow(final List<Slider_model> sliderModelList) {
            final Handler handler = new Handler();
            final Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (currentpage >= sliderModelList.size()) {
                        currentpage = 1;
                    }
                    slider.setCurrentItem(currentpage++, true);
                }
            };
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, Delay_Time, Period_time);
        }

        private void stopbannerSlideshow() {
            timer.cancel();
        }
    }

    public class GridproductViewholder extends RecyclerView.ViewHolder {
        private TextView gridlayoutTitle;
        private Button gridLayoutViewAll;
        private GridView gridView;

        public GridproductViewholder(@NonNull View itemView) {
            super(itemView);
            gridlayoutTitle = itemView.findViewById(R.id.grid_layout_title);
            gridLayoutViewAll = itemView.findViewById(R.id.grid_layout_button);
            gridView = itemView.findViewById(R.id.grid_product_gridview);

        }

        private void setGridProductLayout(List<ProductScrollModel> productScrollModelList, String title) {
            gridlayoutTitle.setText(title);
            gridView.setAdapter(new GridProductLayoutAdapter(productScrollModelList));
        }
    }

   /* public class DataProductViewholder extends RecyclerView.ViewHolder {
        private TextView layouttitle;
        private RecyclerView vrecyclerView;

        public DataProductViewholder(@NonNull View itemView) {
            super(itemView);
            layouttitle = itemView.findViewById(R.id.vertical_scroll_product);
            vrecyclerView = itemView.findViewById(R.id.Vertical_scroll_recyclerview);


        }

        private void setDataRecyclerView(List<DataModel> dataModelList, String title) {
            layouttitle.setText(title);
            DataAdapter dataAdapter = new DataAdapter(dataModelList);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(itemView.getContext(),2,GridLayoutManager.VERTICAL,false);
            vrecyclerView.setLayoutManager(gridLayoutManager);
            vrecyclerView.setAdapter(dataAdapter);
            dataAdapter.notifyDataSetChanged();
        }
    } */
}
