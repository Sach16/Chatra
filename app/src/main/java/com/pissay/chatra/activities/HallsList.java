package com.pissay.chatra.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.pissay.chatra.R;
import com.pissay.chatra.baseclasses.EveBaseActivity;
import com.pissay.chatra.customadapters.CustomRecyclerAdapterForListOfHalls;
import com.pissay.chatra.interfaces.RecyclerHallsListener;
import com.pissay.chatra.macros.EveMacros;
import com.pissay.chatra.models.Evnts;
import com.pissay.chatra.network.Constants;
import com.pissay.chatra.network.RequestManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by S.K. Pissay on 11/7/16.
 */
public class HallsList extends EveBaseActivity implements View.OnClickListener, RecyclerHallsListener {

    @Nullable
    @BindView(R.id.CV_BANQ_CELL)
    CardView mcvBanqCell;

    @Nullable
    @BindView(R.id.RECYC_HALLS_LIST)
    RecyclerView m_cRecycHalls;

    private boolean m_cLoading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    LinearLayoutManager m_cLayoutManager;
    CustomRecyclerAdapterForListOfHalls m_cRecycAdHalls;
    ArrayList<Evnts> m_cHallsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halls_list);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        m_cHallsList = new ArrayList<>();
        m_cLayoutManager = new LinearLayoutManager(this);
        m_cLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        m_cRecycHalls.setLayoutManager(m_cLayoutManager);
        m_cRecycHalls.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        HashMap<String, String> lParams = new HashMap<>();
        if (!getIntent().getStringExtra(EveMacros.FILTER_SESSION).isEmpty())
            lParams.put(Constants.FILTER_SESSION, getIntent().getStringExtra(EveMacros.FILTER_SESSION));
        if (!getIntent().getStringExtra(EveMacros.FILTER_DATE).isEmpty())
            lParams.put(Constants.FILTER_DATE, getIntent().getStringExtra(EveMacros.FILTER_DATE));
        if (getIntent().getDoubleArrayExtra(EveMacros.FILTER_PLACE).length > 0)
            lParams.put(Constants.FILTER_PLACE, String.format("%f#%f", getIntent().getDoubleArrayExtra(EveMacros.FILTER_PLACE)[0],
                    getIntent().getDoubleArrayExtra(EveMacros.FILTER_PLACE)[1]));
        if (getIntent().getBooleanExtra(EveMacros.TYPE_WEDDING, false))
            lParams.put(Constants.TYPE_WEDDING, EveMacros.TYPE_WEDDING);
        if (getIntent().getBooleanExtra(EveMacros.TYPE_BIRTHDAY, false))
            lParams.put(Constants.TYPE_BIRTHDAY, EveMacros.TYPE_BIRTHDAY);
        if (getIntent().getBooleanExtra(EveMacros.TYPE_ENGAGEMENT, false))
            lParams.put(Constants.TYPE_ENGAGEMENT, EveMacros.TYPE_ENGAGEMENT);
        if (getIntent().getBooleanExtra(EveMacros.TYPE_NAMING, false))
            lParams.put(Constants.TYPE_NAMING, EveMacros.TYPE_NAMING);
        if (getIntent().getBooleanExtra(EveMacros.TYPE_CORPORATE, false))
            lParams.put(Constants.TYPE_CORPORATE, EveMacros.TYPE_CORPORATE);
        if (getIntent().getBooleanExtra(EveMacros.TYPE_PARTY, false))
            lParams.put(Constants.TYPE_PARTY, EveMacros.TYPE_PARTY);
        RequestManager.getInstance(this).placeRequest(Constants.HALLS_LIST, Evnts[].class, this, lParams, false);

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
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem register = menu.findItem(R.id.action_locate);
        register.setVisible(false);  //userRegistered is boolean, pointing if the user has registered or not.
        MenuItem lregister = menu.findItem(R.id.action_settings);
        lregister.setVisible(false);
        return true;
    }

    @Optional
    @OnClick({R.id.CV_BANQ_CELL})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CV_BANQ_CELL:
                break;
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
                m_cRecycAdHalls = new CustomRecyclerAdapterForListOfHalls(this, evntsList, this);
                m_cRecycHalls.setAdapter(m_cRecycAdHalls);
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

    @Override
    public void onInfoClick(int pPostion, Evnts pEvnts, View pView) {
        Gson gson = new Gson();
        String lJsonStr = gson.toJson(pEvnts);
        Intent lIntent = new Intent(this, CoordinateParlax.class);
        lIntent.putExtra(EveMacros.SINGLE_EVENT, lJsonStr);
        startActivity(lIntent);
    }
}
