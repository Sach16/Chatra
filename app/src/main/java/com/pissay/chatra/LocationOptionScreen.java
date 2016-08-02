package com.pissay.chatra;

import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by S.K. Pissay on 14/7/16.
 */
public class LocationOptionScreen extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "FragmentActivity";
    private static final int SLEEP = 1232;

    private static final String CLICKED = "CLICKED";
    private static final String UNCLICKED = "UNCLICKED";

    public UIHandler m_cObjUIHandler;
    /**
     * The Place autocomplete request code.
     */
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    @Nullable
    @BindView(R.id.LL_SEARCH)
    LinearLayout mLl_search;

    @Nullable
    @BindView(R.id.CV_NAMING)
    CardView mCv_naming;

    @Nullable
    @BindView(R.id.CV_TYPES)
    RelativeLayout mLLTypes;

    @Nullable
    @BindView(R.id.IV_WEDDING)
    ImageView mIvWedding;

    @Nullable
    @BindView(R.id.IV_BIRTHDAY)
    ImageView mIvBirthday;

    @Nullable
    @BindView(R.id.IV_ENGAGEMENT)
    ImageView mIvEngagement;

    @Nullable
    @BindView(R.id.IV_NAMING)
    ImageView mIvNaming;

    @Nullable
    @BindView(R.id.IV_CORPORATE)
    ImageView mIvCorporate;

    @Nullable
    @BindView(R.id.IV_PARTIES)
    ImageView mIvParties;

    @Nullable
    @BindView(R.id.FAB_GO_SEARCH)
    FloatingActionButton mIvFab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.function_types3);
        ButterKnife.bind(this);

        m_cObjUIHandler = new UIHandler();
        m_cObjUIHandler.sendEmptyMessageDelayed(SLEEP, 500);
    }

    protected void handleUIMessage(Message pObjMessage) {
        switch (pObjMessage.what) {
            case SLEEP:
                upAnimation();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIvWedding.setTag(R.id.CLICK, UNCLICKED);
        mIvBirthday.setTag(R.id.CLICK, UNCLICKED);
        mIvEngagement.setTag(R.id.CLICK, UNCLICKED);
        mIvNaming.setTag(R.id.CLICK, UNCLICKED);
        mIvCorporate.setTag(R.id.CLICK, UNCLICKED);
        mIvParties.setTag(R.id.CLICK, UNCLICKED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i(TAG, "Place: " + place.getName());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    private void upAnimation() {
        Animation bottomUp = AnimationUtils.loadAnimation(this,
                R.anim.bottom_up);
        bottomUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.w(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.w(TAG, "onAnimationEnd: ");
                mIvFab.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mLl_search.startAnimation(bottomUp);
        mLl_search.setVisibility(View.VISIBLE);
    }

    @Optional
    @OnClick({R.id.CV_BIRTHDAY, R.id.FAB_GO_SEARCH, R.id.IV_WEDDING, R.id.IV_BIRTHDAY, R.id.IV_ENGAGEMENT, R.id.IV_NAMING,
            R.id.IV_CORPORATE, R.id.IV_PARTIES})
    public void onClick(View v) {
        Intent lIntent;
        switch (v.getId()) {
            /*case R.id.ITEM_CARDVIEW:
                placeAuto();
                break;*/
            case R.id.CV_BIRTHDAY:
                lIntent = new Intent(this, CoordinateParlax.class);
                startActivity(lIntent);
                break;
            case R.id.FAB_GO_SEARCH:
                lIntent = new Intent(this, HallsList.class);
                startActivity(lIntent);
                break;
            //Image options
            case R.id.IV_WEDDING:
                if (((String) mIvWedding.getTag(R.id.CLICK)).equalsIgnoreCase(CLICKED)) {
                    mIvWedding.setTag(R.id.CLICK, UNCLICKED);
                    mIvWedding.setBackground(makeSelector(mIvWedding.getId(), false));
                } else {
                    mIvWedding.setTag(R.id.CLICK, CLICKED);
                    mIvWedding.setBackground(makeSelector(mIvWedding.getId(), true));
                }
                break;
            case R.id.IV_BIRTHDAY:
                if (((String) mIvBirthday.getTag(R.id.CLICK)).equalsIgnoreCase(CLICKED)) {
                    mIvBirthday.setTag(R.id.CLICK, UNCLICKED);
                    mIvBirthday.setBackground(makeSelector(mIvBirthday.getId(), false));
                } else {
                    mIvBirthday.setTag(R.id.CLICK, CLICKED);
                    mIvBirthday.setBackground(makeSelector(mIvBirthday.getId(), true));
                }
                break;
            case R.id.IV_ENGAGEMENT:
                if (((String) mIvEngagement.getTag(R.id.CLICK)).equalsIgnoreCase(CLICKED)) {
                    mIvEngagement.setTag(R.id.CLICK, UNCLICKED);
                    mIvEngagement.setBackground(makeSelector(mIvEngagement.getId(), false));
                } else {
                    mIvEngagement.setTag(R.id.CLICK, CLICKED);
                    mIvEngagement.setBackground(makeSelector(mIvEngagement.getId(), true));
                }
                break;
            case R.id.IV_NAMING:
                if (((String) mIvNaming.getTag(R.id.CLICK)).equalsIgnoreCase(CLICKED)) {
                    mIvNaming.setTag(R.id.CLICK, UNCLICKED);
                    mIvNaming.setBackground(makeSelector(mIvCorporate.getId(), false));
                } else {
                    mIvNaming.setTag(R.id.CLICK, CLICKED);
                    mIvNaming.setBackground(makeSelector(mIvCorporate.getId(), true));
                }
                break;
            case R.id.IV_CORPORATE:
                if (((String) mIvCorporate.getTag(R.id.CLICK)).equalsIgnoreCase(CLICKED)) {
                    mIvCorporate.setTag(R.id.CLICK, UNCLICKED);
                    mIvCorporate.setBackground(makeSelector(mIvCorporate.getId(), false));
                } else {
                    mIvCorporate.setTag(R.id.CLICK, CLICKED);
                    mIvCorporate.setBackground(makeSelector(mIvCorporate.getId(), true));
                }
                break;
            case R.id.IV_PARTIES:
                if (((String) mIvParties.getTag(R.id.CLICK)).equalsIgnoreCase(CLICKED)) {
                    mIvParties.setTag(R.id.CLICK, UNCLICKED);
                    mIvParties.setBackground(makeSelector(mIvParties.getId(), false));
                } else {
                    mIvParties.setTag(R.id.CLICK, CLICKED);
                    mIvParties.setBackground(makeSelector(mIvParties.getId(), true));
                }
                break;
        }
    }

    private void placeAuto() {
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }

    public StateListDrawable makeSelector(int pId, boolean pIsSelected) {
        StateListDrawable res = new StateListDrawable();
        res.setExitFadeDuration(400);
        switch (pId) {
            /*case R.id.IV_WEDDING:
                res.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(R.drawable.bride));
                res.addState(new int[]{android.R.attr.state_focused}, getResources().getDrawable(R.drawable.bride));
                res.addState(new int[]{}, getResources().getDrawable(R.drawable.bride_stroke));
                break;*/
            case R.id.IV_WEDDING:
                if (pIsSelected)
                    res.addState(new int[]{}, getResources().getDrawable(R.drawable.bride));
                else
                    res.addState(new int[]{}, getResources().getDrawable(R.drawable.bride_stroke));
                break;
            case R.id.IV_BIRTHDAY:
                if (pIsSelected)
                    res.addState(new int[]{}, getResources().getDrawable(R.drawable.cake));
                else
                    res.addState(new int[]{}, getResources().getDrawable(R.drawable.cake_stroke));
                break;
            case R.id.IV_ENGAGEMENT:
                if (pIsSelected)
                    res.addState(new int[]{}, getResources().getDrawable(R.drawable.diamond));
                else
                    res.addState(new int[]{}, getResources().getDrawable(R.drawable.diamond_stroke));
                break;
            case R.id.IV_NAMING:
                if (pIsSelected)
                    res.addState(new int[]{}, getResources().getDrawable(R.drawable.naming));
                else
                    res.addState(new int[]{}, getResources().getDrawable(R.drawable.naming_stroke));
                break;
            case R.id.IV_CORPORATE:
                if (pIsSelected)
                    res.addState(new int[]{}, getResources().getDrawable(R.drawable.handshake));
                else
                    res.addState(new int[]{}, getResources().getDrawable(R.drawable.handshake_stroke));
                break;
            case R.id.IV_PARTIES:
                if (pIsSelected)
                    res.addState(new int[]{}, getResources().getDrawable(R.drawable.champagne));
                else
                    res.addState(new int[]{}, getResources().getDrawable(R.drawable.champagne_stroke));
                break;
        }
//        res.addState(new int[]{}, new ColorDrawable(Color.TRANSPARENT));
        return res;
    }

    public final class UIHandler extends Handler {
        @Override
        public void handleMessage(Message pObjMessage) {
            handleUIMessage(pObjMessage);
        }
    }
}
