package com.petz.pros.ui.main.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.petz.pros.R;
import com.viewpagerindicator.CirclePageIndicator;

import me.angeldevil.autoscrollviewpager.AutoScrollViewPager;

public class AutoScrollPagerFragment extends Fragment {

    private String[] imgs = {"banner_two","banner_one", "banner_three"};
    private String[] titles = {"CHOOSE THE BEST\nSITTER FOR YOUR DOG","A loving pet care taker,\njust a click away.", "GOOD CARE FOR YOUR\nLOVELY PET" };

    private DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
            .cacheOnDisk(true).imageScaleType(ImageScaleType.IN_SAMPLE_INT).build();

    private Toast toast;

    public AutoScrollPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auto_scroll_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AutoScrollViewPager pager = (AutoScrollViewPager) getView().findViewById(R.id.scroll_pager);
        final TextView title = (TextView) getView().findViewById(R.id.title);
        CirclePageIndicator indicator = (CirclePageIndicator) getView().findViewById(R.id.indicator);

        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int i) {
                title.setText(titles[i]);
            }
        });

        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgs.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView view = new ImageView(container.getContext());
                view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(getActivity()).load(getImage(imgs[position])).into(view);
               // ImageLoader.getInstance().displayImage(imgs[position], view, options);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        indicator.setViewPager(pager);
        indicator.setSnap(true);

        pager.setScrollFactor(5);
        pager.setOffscreenPageLimit(4);
        pager.startAutoScroll(2000);
        pager.setOnPageClickListener(new AutoScrollViewPager.OnPageClickListener() {
            @Override
            public void onPageClick(AutoScrollViewPager autoScrollPager, int position) {
                if (toast != null) {
                    toast.cancel();
                }
                toast = Toast.makeText(getActivity(), "You clicked page: " + (position + 1),
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public int getImage(String imageName) {

        int drawableResourceId = this.getResources().getIdentifier(imageName, "drawable", getActivity().getPackageName());

        return drawableResourceId;
    }
}
