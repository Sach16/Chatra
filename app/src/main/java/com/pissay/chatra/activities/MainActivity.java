package com.pissay.chatra.activities;

import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.pissay.chatra.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by S.K. Pissay on 9/7/16.
 */

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    private static final String TAG = "FragmentActivity";
    /**
     * The Place autocomplete request code.
     */
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    /*@Nullable
    @BindView(R.id.ITEM_CARDVIEW)
    CardView mCView;*/

    @Nullable
    @BindView(R.id.CV_BIRTHDAY)
    CardView mCv_view;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lInt = new Intent(MainActivity.this, LocationOptionScreen.class);
                startActivity(lInt);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mLLTypes.startAnimation(bottomUp);
        mLLTypes.setVisibility(View.VISIBLE);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_camera:
                // Handle the camera action
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Optional
    @OnClick({R.id.CV_BIRTHDAY, R.id.CV_NAMING})
    public void onClick(View v) {
        Intent lIntent;
        switch (v.getId()){
            /*case R.id.ITEM_CARDVIEW:
                placeAuto();
                break;*/
            case R.id.CV_BIRTHDAY:
                lIntent = new Intent(this, CoordinateParlax.class);
                startActivity(lIntent);
                break;
            case R.id.CV_NAMING:
                lIntent = new Intent(this, HallsList.class);
                startActivity(lIntent);
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

    public StateListDrawable makeSelector(int pId) {
        StateListDrawable res = new StateListDrawable();
        res.setExitFadeDuration(400);
        switch (pId){
            case R.id.IV_WEDDING:
                res.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(R.drawable.bride));
                res.addState(new int[]{android.R.attr.state_focused}, getResources().getDrawable(R.drawable.bride));
                res.addState(new int[]{android.R.attr.state_selected}, getResources().getDrawable(R.drawable.bride));
                res.addState(new int[] { }, getResources().getDrawable(R.drawable.bride_stroke));
                break;
            case R.id.IV_BIRTHDAY:
                res.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(R.drawable.cake));
                res.addState(new int[]{android.R.attr.state_focused}, getResources().getDrawable(R.drawable.cake));
                res.addState(new int[]{android.R.attr.state_selected}, getResources().getDrawable(R.drawable.cake));
                res.addState(new int[] { }, getResources().getDrawable(R.drawable.cake_stroke));
                break;
            case R.id.IV_ENGAGEMENT:
                res.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(R.drawable.diamond));
                res.addState(new int[]{android.R.attr.state_focused}, getResources().getDrawable(R.drawable.diamond));
                res.addState(new int[]{android.R.attr.state_selected}, getResources().getDrawable(R.drawable.diamond));
                res.addState(new int[] { }, getResources().getDrawable(R.drawable.diamond_stroke));
                break;
            case R.id.IV_NAMING:
                res.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(R.drawable.naming));
                res.addState(new int[]{android.R.attr.state_focused}, getResources().getDrawable(R.drawable.naming));
                res.addState(new int[]{android.R.attr.state_selected}, getResources().getDrawable(R.drawable.naming));
                res.addState(new int[] { }, getResources().getDrawable(R.drawable.naming_stroke));
                break;
            case R.id.IV_CORPORATE:
                res.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(R.drawable.handshake));
                res.addState(new int[]{android.R.attr.state_focused}, getResources().getDrawable(R.drawable.handshake));
                res.addState(new int[]{android.R.attr.state_selected}, getResources().getDrawable(R.drawable.handshake));
                res.addState(new int[] { }, getResources().getDrawable(R.drawable.handshake_stroke));
                break;
            case R.id.IV_PARTIES:
                res.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(R.drawable.champagne));
                res.addState(new int[]{android.R.attr.state_focused}, getResources().getDrawable(R.drawable.champagne));
                res.addState(new int[]{android.R.attr.state_selected}, getResources().getDrawable(R.drawable.champagne));
                res.addState(new int[] { }, getResources().getDrawable(R.drawable.champagne_stroke));
                break;
        }
//        res.addState(new int[]{}, new ColorDrawable(Color.TRANSPARENT));
        return res;
    }
}
