package com.pissay.chatra.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.pissay.chatra.R;
import com.pissay.chatra.baseclasses.EveBaseActivity;
import com.pissay.chatra.macros.EveMacros;
import com.pissay.chatra.models.User;
import com.pissay.chatra.network.Constants;
import com.pissay.chatra.network.RequestManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by S.K. Pissay on 15/7/16.
 */
public class Otp extends EveBaseActivity implements View.OnClickListener{

    private static final String TAG = "FragmentActivity";

    private static final int SLEEP = 1232;
    public UIHandler m_cObjUIHandler;

    @Nullable
    @BindView(R.id.SUBMIT)
    TextView mtv_Submit;

    @Nullable
    @BindView(R.id.PHONE_TXT)
    TextView mtv_Phone;

    @Nullable
    @BindView(R.id.OTP_TXT)
    TextView mtv_Otp;

    @Nullable
    @BindView(R.id.OTP_LAY)
    LinearLayout mtv_Otp_lay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opt_screen);
        ButterKnife.bind(this);

        m_cObjUIHandler = new UIHandler();
        m_cObjUIHandler.sendEmptyMessageDelayed(SLEEP, 500);
    }

    @Optional
    @OnClick({R.id.SUBMIT})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.SUBMIT:
                displayProgressBar(-1, getResources().getString(R.string.app_name));
                HashMap<String, String> lParams = new HashMap<>();
                lParams.put(Constants.PHONE, mtv_Phone.getText().toString());
                lParams.put(Constants.OTP, mtv_Otp.getText().toString());
                RequestManager.getInstance(this).placeRequest(Constants.LOGIN, User.class, this, lParams, false);
                break;
        }
    }

    protected void handleUIMessage(Message pObjMessage){
        switch (pObjMessage.what){
            case SLEEP:
                upAnimation();
                break;
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
                mtv_Submit.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mtv_Otp_lay.startAnimation(bottomUp);
        mtv_Otp_lay.setVisibility(View.VISIBLE);
    }

    public final class UIHandler extends Handler {
        @Override
        public void handleMessage(Message pObjMessage) {
            handleUIMessage(pObjMessage);
        }
    }

    @Override
    public void onAPIResponse(Object response, String apiMethod) {
        switch (apiMethod){
            case Constants.LOGIN:
                if (EveMacros.getLoginAuth(this) == null) {
                    User lUser = (User) response;
                    EveMacros.saveLoginAuth(this, lUser.getToken());
                }
                Intent lIntent = new Intent(this, LocationOptionScreen.class);
                startActivity(lIntent);
                break;
        }
        hideDialog();
    }

    @Override
    public void onErrorResponse(VolleyError error, String apiMethod) {
        hideDialog();
    }
}
