package com.petz.pros.ui.main.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.petz.pros.R;
import com.petz.pros.data.network.pojo.FeedItem;
import com.petz.pros.ui.base.BaseFragment;
import com.petz.pros.ui.bottomsheet.BottomSheetFragment;
import com.petz.pros.ui.caretackerlist.PetCareTackerListActivity;
import com.petz.pros.ui.main.RssAdapter;
import com.petz.pros.ui.main.ui.home.card.CardItem;
import com.petz.pros.ui.main.ui.home.card.CardPagerAdapter;
import com.petz.pros.ui.main.ui.home.card.ShadowTransformer;

import java.util.List;

import javax.inject.Inject;


public class HomeFragment extends BaseFragment implements HomeMvpView , CardPagerAdapter.Callback{

    private HomeViewModel homeViewModel;
    private ViewPager contentPager;
    RecyclerView mRecyclerView;

    @Inject
    RssAdapter mRssAdapter;
    @Inject
    HomeMvpPresenter<HomeMvpView> mPresenter;


    private ViewPager mViewPager;

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;

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
      //  setUp(root);
        return root;
    }

    @Override
    protected void setUp(View view) {

        mViewPager = (ViewPager)view. findViewById(R.id.viewPager);
        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.setCallback(this);
        mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1,1,getActivity().getResources().getDrawable(R.drawable.dog_walking)));
        mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_2,2,getActivity().getResources().getDrawable(R.drawable.dog_boarding)));
        mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_3,3,getActivity().getResources().getDrawable(R.drawable.dog_sitting)));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_4,4,getActivity().getResources().getDrawable(R.drawable.dog_day_care)));
        mCardAdapter.addCardItem(new CardItem(R.string.title_5, R.string.text_5,5,getActivity().getResources().getDrawable(R.drawable.cat_boarding)));
        mCardAdapter.addCardItem(new CardItem(R.string.title_6, R.string.text_6,6,getActivity().getResources().getDrawable(R.drawable.cat_sitting)));
        mCardAdapter.addCardItem(new CardItem(R.string.title_7, R.string.text_7,7,getActivity().getResources().getDrawable(R.drawable.dog_walking)));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
       // mPresenter.onViewPrepared();
    }

    @Override
    public void updateFeed(List<FeedItem> feedItemList) {
        mRecyclerView.setAdapter(mRssAdapter);
        mRssAdapter.addItems(feedItemList);
    }
    private CardItem cardItem;
    @Override
    public void openBottomSheet(CardItem item) {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        bottomSheetFragment.setClicklisterner(this);
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
    }

    @Override
    public void onClickProceed(int mSelectedYear, int mSelectedMonth, int mSelectedDay, int mSelectedStartHour, int mSelectedStartMinute, int mSelectedEndHour, int mSelectedEndMinte) {
        startActivity(PetCareTackerListActivity.getStartIntent(getActivity(),cardItem.getServiceId(), mSelectedYear,  mSelectedMonth,  mSelectedDay,  mSelectedStartHour,  mSelectedStartMinute,  mSelectedEndHour,  mSelectedEndMinte));
    }


    @Override
    public void onEmptyViewRetryClick() {

    }

    @Override
    public void onServiceClick(CardItem item) {
        cardItem = item;
        openBottomSheet(item);
    }

}