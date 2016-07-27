package com.pissay.chatra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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
