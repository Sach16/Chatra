package com.pissay.chatra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by S.K. Pissay on 11/7/16.
 */
public class HallsList extends AppCompatActivity implements View.OnClickListener{

    @Nullable
    @BindView(R.id.CV_BANQ_CELL)
    CardView mcvBanqCell;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_card_cell);
        ButterKnife.bind(this);

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

    @OnClick({R.id.CV_BANQ_CELL})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.CV_BANQ_CELL:
                Intent lIntent = new Intent(this, CoordinateParlax.class);
                startActivity(lIntent);
                break;
        }
    }
}
