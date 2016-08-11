package com.pissay.chatra;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pissay.chatra.utils.ToolBarHeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by S.K. Pissay on 9/7/16.
 */
public class CoordinateParlax extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    @Nullable
    @BindView(R.id.backdrop)
    ViewPager vp_backdrop;

    @Nullable
    @BindView(R.id.BT_COST_PER)
    Button bn_CostPer;

    @Nullable
    @BindView(R.id.BT_RESERVE_BOOK)
    Button bn_ResBook;

    @Nullable
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;

    @Nullable
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @BindView(R.id.toolbar_header_view)
    ToolBarHeaderView toolbarHeaderView;

    @Nullable
    @BindView(R.id.float_header_view)
    ToolBarHeaderView floatHeaderView;

    public CustomPagerAdapter mCustomPagerAdapter;

    private boolean isHideToolbarView = false;

    int[] mResources = {
            R.drawable.banquet,
            R.drawable.sample1,
            R.drawable.sample2,
            R.drawable.sample3,
            R.drawable.banquet,
            R.drawable.sample1
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout.setTitle(" ");

        toolbarHeaderView.bindTo("Star Banquets", "Church street, Bangalore");
        floatHeaderView.bindTo("Star Banquets", "Church street, Bangalore");

        appBarLayout.addOnOffsetChangedListener(this);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Star Banquets");
        getSupportActionBar().setSubtitle("Church street, Bangalore");*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCustomPagerAdapter = new CustomPagerAdapter(this);
        vp_backdrop.setAdapter(mCustomPagerAdapter);

//        Glide.with(this).load(R.drawable.sample2).centerCrop().into(vp_backdrop);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_locate:
                Uri gmmIntentUri = Uri.parse("geo:12.968575,77.607172?q="+getResources().getString(R.string.bkh_hall)+"@12.968575,77.607172");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        if (percentage == 1f && isHideToolbarView) {
            toolbarHeaderView.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;

        } else if (percentage < 1f && !isHideToolbarView) {
            toolbarHeaderView.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
        }
    }

    @Optional
    @OnClick({R.id.BT_COST_PER, R.id.BT_RESERVE_BOOK})
    public void onClick(View v) {
        Intent lObjInt;
        switch (v.getId()){
            case R.id.BT_COST_PER:
            case R.id.BT_RESERVE_BOOK:
                lObjInt = new Intent(this, SlotAvailCalendar.class);
                startActivity(lObjInt);
                break;
        }
    }

    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mResources.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageResource(mResources[position]);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

}
