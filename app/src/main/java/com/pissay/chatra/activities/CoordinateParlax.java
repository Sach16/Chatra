package com.pissay.chatra.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.pissay.chatra.R;
import com.pissay.chatra.baseclasses.EveBaseActivity;
import com.pissay.chatra.customadapters.CustomRecyclerAdapterForListOfHallsHor;
import com.pissay.chatra.interfaces.RecyclerHallsListener;
import com.pissay.chatra.macros.EveMacros;
import com.pissay.chatra.models.Evnts;
import com.pissay.chatra.network.Constants;
import com.pissay.chatra.network.RequestManager;
import com.pissay.chatra.utils.ToolBarHeaderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by S.K. Pissay on 9/7/16.
 */
public class CoordinateParlax extends EveBaseActivity implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener, RecyclerHallsListener {

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

    @Nullable
    @BindView(R.id.RECYC_HALLS_LIST_HOR)
    RecyclerView m_cRecycHallsHor;

    private boolean m_cLoading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    LinearLayoutManager m_cLayoutManager;
    CustomRecyclerAdapterForListOfHallsHor m_cRecycAdHalls;
    ArrayList<Evnts> m_cHallsList;

    private Evnts m_cEvnts;

    public CustomPagerAdapter mCustomPagerAdapter;

    private boolean isHideToolbarView = false;

    private ArrayList<String> mImgResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

//        Glide.with(this).load(R.drawable.sample2).centerCrop().into(vp_backdrop);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        Gson gson = new Gson();
        m_cEvnts = gson.fromJson(getIntent().getStringExtra(EveMacros.SINGLE_EVENT), Evnts.class);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout.setTitle(" ");

        toolbarHeaderView.bindTo(m_cEvnts.getFirstName() + " " + m_cEvnts.getLastName(), m_cEvnts.getAddress().getAlphaStreet() + " " + m_cEvnts.getAddress().getBetaStreet());
        floatHeaderView.bindTo(m_cEvnts.getFirstName() + " " + m_cEvnts.getLastName(), m_cEvnts.getAddress().getAlphaStreet() + " " + m_cEvnts.getAddress().getBetaStreet());

        appBarLayout.addOnOffsetChangedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mImgResources = new ArrayList<>();
        mImgResources.add(m_cEvnts.getImageUrl());
        mImgResources.add(m_cEvnts.getImageUrl());
        mImgResources.add(m_cEvnts.getImageUrl());
        mImgResources.add(m_cEvnts.getImageUrl());
        mImgResources.add(m_cEvnts.getImageUrl());

        mCustomPagerAdapter = new CustomPagerAdapter(this, mImgResources);
        vp_backdrop.setAdapter(mCustomPagerAdapter);

        m_cHallsList = new ArrayList<>();
        m_cLayoutManager = new LinearLayoutManager(this);
        m_cLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        m_cRecycHallsHor.setLayoutManager(m_cLayoutManager);
        m_cRecycHallsHor.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = m_cLayoutManager.getChildCount();
                    totalItemCount = m_cLayoutManager.getItemCount();
                    pastVisiblesItems = m_cLayoutManager.findFirstVisibleItemPosition();

//                    int page = totalItemCount / 15;
                    if (m_cLoading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            m_cLoading = false;
                            Log.v("...", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
//                            int lpage = page + 1;
//                            page = lpage;
//                            doPagination(lpage);
                        }
                    }
                }
            }
        });

        displayProgressBar(-1, getResources().getString(R.string.app_name));
        RequestManager.getInstance(this).placeRequest(Constants.HALLS_LIST, Evnts[].class, this, null, false);

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
                Uri gmmIntentUri = Uri.parse("geo:12.968575,77.607172?q=" + getResources().getString(R.string.bkh_hall) + "@12.968575,77.607172");
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
        switch (v.getId()) {
            case R.id.BT_COST_PER:
            case R.id.BT_RESERVE_BOOK:
                lObjInt = new Intent(this, SlotAvailCalendar.class);
                startActivity(lObjInt);
                break;
        }
    }

    @Override
    public void onInfoClick(int pPostion, Evnts pEvnts, View pView) {

    }

    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;
        ArrayList<String> mImgResources;

        public CustomPagerAdapter(Context context, ArrayList<String> mImgResources) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.mImgResources = mImgResources;
        }

        @Override
        public int getCount() {
            return mImgResources.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            Picasso.with(CoordinateParlax.this)
                    .load(mImgResources.get(position))
                    .into(imageView);
//            imageView.setImageResource(mImgResources[position]);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

    @Override
    protected void handleUIMessage(Message pObjMessage) {
    }

    @Override
    public void onAPIResponse(Object response, String apiMethod) {
        switch (apiMethod) {
            case Constants.HALLS_LIST:
//                Gson gson = new Gson();
//                Evnts[] arr = gson.fromJson(response, Evnts[].class);
                List<Evnts> evntsList = Arrays.asList((Evnts[]) response);
                m_cRecycAdHalls = new CustomRecyclerAdapterForListOfHallsHor(this, evntsList, this);
                m_cRecycHallsHor.setAdapter(m_cRecycAdHalls);
                hideDialog();
                break;
        }
    }

    @Override
    public void onErrorResponse(VolleyError error, String apiMethod) {
        switch (apiMethod) {
            case Constants.HALLS_LIST:
                break;
        }
        hideDialog();
    }
}
