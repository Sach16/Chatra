package com.pissay.chatra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by S.K. Pissay on 8/8/16.
 */
public class BookReserveNow extends AppCompatActivity implements View.OnClickListener{

    @Nullable
    @BindView(R.id.REL_LAY_GRP)
    RelativeLayout rl_LayGrp;

    @Nullable
    @BindView(R.id.BT_RESERVE_BOOK)
    Button ll_finalBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_reserve_now);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle(getResources().getString(R.string.review_bookings));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @OnClick({R.id.BT_RESERVE_BOOK})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BT_RESERVE_BOOK:
                Snackbar.make(rl_LayGrp, "Thankyou have a wonderful eve", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
