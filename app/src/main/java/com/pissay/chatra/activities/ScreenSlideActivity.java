package com.pissay.chatra.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.pissay.chatra.R;
import com.pissay.chatra.fragments.ScreenSlidePageFragment;
import com.pissay.chatra.fragments.ScreenSlidePageFragment2;
import com.pissay.chatra.fragments.ScreenSlidePageFragment3;
import com.pissay.chatra.fragments.ScreenSlidePageFragment4;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by S.K. Pissay on 15/7/16.
 */
public class ScreenSlideActivity  extends AppCompatActivity implements View.OnClickListener {

    @Nullable
    @BindView(R.id.START_BUTTON)
    TextView mTv_start;

    @Nullable
    @BindView(R.id.VIDEO_VIEW)
    VideoView mVideoView;

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 4;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_screen_slide);
        ButterKnife.bind(this);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        indicator.setViewPager(mPager);
        init();
    }

    private void init() {
        //old
        String uriPath = "android.resource://"+getPackageName()+"/raw/start_video2";
        Uri uri = Uri.parse(uriPath);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mVideoView.getLayoutParams();
        params.width = metrics.widthPixels;
        params.height = metrics.heightPixels;
        mVideoView.setLayoutParams(params);
        mVideoView.setVideoURI(uri);
        mVideoView.requestFocus();
        mVideoView.seekTo(0);
        mVideoView.setOnPreparedListener (new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //below line used to mute audio
                mp.setVolume(0f, 0f);
                mp.setLooping(true);
            }
        });
        mVideoView.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!mVideoView.isPlaying()) {
            mVideoView.start();
        }
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Optional
    @OnClick({R.id.START_BUTTON})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.START_BUTTON:
                Intent lIntent = new Intent(this, Otp.class);
                startActivity(lIntent);
                break;
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment lFragment = null;
            switch (position){
                case 0:
                    lFragment =  new ScreenSlidePageFragment();
                    break;
                case 1:
                    lFragment =  new ScreenSlidePageFragment2();
                    break;
                case 2:
                    lFragment =  new ScreenSlidePageFragment3();
                    break;
                case 3:
                    lFragment =  new ScreenSlidePageFragment4();
                    break;
            }
            return lFragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        } else {
            return manufacturer + "" + model;
        }
    }
}
