package com.pissay.chatra.activities;

import android.app.DatePickerDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.pissay.chatra.R;
import com.pissay.chatra.macros.EveMacros;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by S.K. Pissay on 14/7/16.
 */
public class LocationOptionScreen extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

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

    @Nullable
    @BindView(R.id.TV_CHECKIN_DATE)
    TextView mCheckinDateTv;

    @Nullable
    @BindView(R.id.SP_TIMESLOT)
    Spinner mSpTimeSlot;

    private Calendar m_cCalendar;
    private DatePickerDialog m_cDatePickerDialog;
    public static final int DATE_PICKER_ID = 102;

    private static final String TAG = "FragmentActivity";
    private static final int SLEEP = 1232;

    private static final String CLICKED = "CLICKED";
    private static final String UNCLICKED = "UNCLICKED";

    public UIHandler m_cObjUIHandler;
    private ArrayList<String> mTimeSlotList;

    private ArrayAdapter mSpinAdapter;
    private int m_cSessPos;
    private LatLng m_cLatLng;
    /**
     * The Place autocomplete request code.
     */
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        mSpTimeSlot.setOnItemSelectedListener(this);
        mTimeSlotList = new ArrayList<>();
        mTimeSlotList.add("Full Day");
        mTimeSlotList.add("Morning Session - Lunch");
        mTimeSlotList.add("Evening Session - Dinner");
        mSpinAdapter = new ArrayAdapter(this, R.layout.spinner_text_lay, mTimeSlotList);
        mSpTimeSlot.setAdapter(mSpinAdapter);

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
                m_cLatLng = place.getLatLng();
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
            R.id.IV_CORPORATE, R.id.IV_PARTIES, R.id.TV_CHECKIN_DATE})
    public void onClick(View v) {
        Intent lIntent;
        switch (v.getId()) {
            case R.id.TV_CHECKIN_DATE:
                showDatePickerDialog(DATE_PICKER_ID);
                break;
            case R.id.CV_BIRTHDAY:
                lIntent = new Intent(this, CoordinateParlax.class);
                startActivity(lIntent);
                break;
            case R.id.FAB_GO_SEARCH:
                lIntent = new Intent(this, HallsList.class);
                if (m_cSessPos > -1)
                    lIntent.putExtra(EveMacros.FILTER_SESSION, mTimeSlotList.get(m_cSessPos));
                if (!mCheckinDateTv.getText().toString().isEmpty())
                    lIntent.putExtra(EveMacros.FILTER_DATE, mCheckinDateTv.getText().toString());
                if (m_cLatLng != null)
                    lIntent.putExtra(EveMacros.FILTER_PLACE, new Double[]{m_cLatLng.latitude, m_cLatLng.longitude});
                if (((String) mIvWedding.getTag(R.id.CLICK)).equalsIgnoreCase(CLICKED))
                    lIntent.putExtra(EveMacros.TYPE_WEDDING, true);
                if (((String) mIvBirthday.getTag(R.id.CLICK)).equalsIgnoreCase(CLICKED))
                    lIntent.putExtra(EveMacros.TYPE_BIRTHDAY, true);
                if (((String) mIvEngagement.getTag(R.id.CLICK)).equalsIgnoreCase(CLICKED))
                    lIntent.putExtra(EveMacros.TYPE_ENGAGEMENT, true);
                if (((String) mIvNaming.getTag(R.id.CLICK)).equalsIgnoreCase(CLICKED))
                    lIntent.putExtra(EveMacros.TYPE_NAMING, true);
                if (((String) mIvCorporate.getTag(R.id.CLICK)).equalsIgnoreCase(CLICKED))
                    lIntent.putExtra(EveMacros.TYPE_CORPORATE, true);
                if (((String) mIvParties.getTag(R.id.CLICK)).equalsIgnoreCase(CLICKED))
                    lIntent.putExtra(EveMacros.TYPE_PARTY, true);
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
                    mIvNaming.setBackground(makeSelector(mIvNaming.getId(), false));
                } else {
                    mIvNaming.setTag(R.id.CLICK, CLICKED);
                    mIvNaming.setBackground(makeSelector(mIvNaming.getId(), true));
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
                m_cLatLng = place.getLatLng();
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (position > -1)
            m_cSessPos = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public final class UIHandler extends Handler {
        @Override
        public void handleMessage(Message pObjMessage) {
            handleUIMessage(pObjMessage);
        }
    }

    private void showDatePickerDialog(int pId) {
        m_cCalendar = Calendar.getInstance();
        switch (pId) {
            case DATE_PICKER_ID:
                m_cDatePickerDialog = new DatePickerDialog(this, myBankDateListener, m_cCalendar.get(Calendar.YEAR),
                        m_cCalendar.get(Calendar.MONTH), m_cCalendar.get(Calendar.DAY_OF_MONTH));
                m_cDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                break;
        }
        m_cDatePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener myBankDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int month, int day) {
            String lmonth = String.format("%02d", month + 1);
            String lday = String.format("%02d", day);
            mCheckinDateTv.setText(lday + "-" + lmonth + "-" + year);
            m_cDatePickerDialog.dismiss();
        }
    };
}
