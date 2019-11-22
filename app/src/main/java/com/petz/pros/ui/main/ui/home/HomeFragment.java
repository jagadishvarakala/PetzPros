package com.petz.pros.ui.main.ui.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.petz.pros.R;
import com.petz.pros.data.network.pojo.FeedItem;
import com.petz.pros.ui.base.BaseFragment;
import com.petz.pros.ui.main.MainActivity;
import com.petz.pros.ui.main.MainMvpPresenter;
import com.petz.pros.ui.main.MainMvpView;
import com.petz.pros.ui.main.RssAdapter;
import com.petz.pros.utils.DividerItemDecoration;

import java.util.List;

import javax.inject.Inject;


public class HomeFragment extends BaseFragment implements HomeMvpView , RssAdapter.Callback{

    private HomeViewModel homeViewModel;
    private ViewPager contentPager;
    RecyclerView mRecyclerView;

    @Inject
    RssAdapter mRssAdapter;
    @Inject
    HomeMvpPresenter<HomeMvpView> mPresenter;

    LinearLayoutManager mLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getContext()).build();
        ImageLoader.getInstance().init(configuration);

        contentPager = root.findViewById(R.id.pager);
        contentPager.setOffscreenPageLimit(2);
        contentPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                if (i == 0) {
                    return new AutoScrollPagerFragment();
                }
                return TextFragment.newInstance("Fragment " + i);
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        getActivityComponent().inject(this);
        mPresenter.onAttach(HomeFragment.this);
        setUp(root);
        return root;
    }

    @Override
    protected void setUp(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerViewFeed);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        Drawable dividerDrawable = ContextCompat.getDrawable(getContext(), R.drawable.divider_drawable);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPresenter.onViewPrepared();
    }

    @Override
    public void updateFeed(List<FeedItem> feedItemList) {
        mRecyclerView.setAdapter(mRssAdapter);
        mRssAdapter.addItems(feedItemList);
    }

    @Override
    public void onEmptyViewRetryClick() {
        mPresenter.onViewPrepared();
    }
}