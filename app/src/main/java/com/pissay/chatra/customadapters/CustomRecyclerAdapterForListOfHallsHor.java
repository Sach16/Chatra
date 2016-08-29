package com.pissay.chatra.customadapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pissay.chatra.R;
import com.pissay.chatra.interfaces.RecyclerHallsListener;
import com.pissay.chatra.models.Evnts;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by S.K. Pissay on 29/8/16.
 */
public class CustomRecyclerAdapterForListOfHallsHor extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private static RecyclerHallsListener m_cClickListener;
    private static List<Evnts> m_cObjJsonLeads;
    private Context m_cObjContext;

    public CustomRecyclerAdapterForListOfHallsHor(Context pContext, List<Evnts> pJsonLeads, RecyclerHallsListener pListener) {
        this.m_cObjContext = pContext;
        this.m_cObjJsonLeads = pJsonLeads;
        this.m_cClickListener = pListener;
    }

    @Override
    public int getItemCount() {
        return m_cObjJsonLeads.size();
    }

    @Override
    public int getItemViewType(int position) {
        return m_cObjJsonLeads.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View lView;
        // paging logic
        if (viewType == VIEW_ITEM) {
            lView = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_more_cell, parent, false);
            DataObjectHolder ldataObjectHolder = new DataObjectHolder(lView);
            vh = ldataObjectHolder;
        } else {
            lView = LayoutInflater.from(parent.getContext()).inflate(R.layout.progressdialog_paging, parent, false);
            ProgressViewHolder lprogressViewHolder = new ProgressViewHolder(lView);
            vh = lprogressViewHolder;
        }
        return vh;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Nullable
        @BindView(R.id.CV_BANQ_CELL)
        CardView cvBanqCell;

        @Nullable
        @BindView(R.id.BANKQ_NAME)
        TextView fullNametxt;

        @Nullable
        @BindView(R.id.DISTANCE_KM_TXT)
        TextView distanceKmtxt;

        @Nullable
        @BindView(R.id.ADDRESS_LOC_TXT)
        TextView addrtxt;

        @Nullable
        @BindView(R.id.COST_PER_PLATE_TXT)
        TextView costPerPlateTxt;

        @Nullable
        @BindView(R.id.BANQ_IMG)
        ImageView banqImg;

        public DataObjectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Optional
        @OnClick({R.id.CV_BANQ_CELL})
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.CV_BANQ_CELL:
                    m_cClickListener.onInfoClick(getPosition(), m_cObjJsonLeads.get(getPosition()), v);
                    break;
            }
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        @BindView(R.id.progressBar1)
        ProgressBar progressBar;

        public ProgressViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof DataObjectHolder) {
            try {
                ((DataObjectHolder) holder).fullNametxt.setText(m_cObjJsonLeads.get(position).getFirstName() + " " + m_cObjJsonLeads.get(position).getLastName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                ((DataObjectHolder) holder).addrtxt.setText(m_cObjJsonLeads.get(position).getAddress().getAlphaStreet() + " " + m_cObjJsonLeads.get(position).getAddress().getBetaStreet());
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                ((DataObjectHolder) holder).costPerPlateTxt.setText(m_cObjJsonLeads.get(position).getFoodMenu().get(0).getCost().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Picasso.with(m_cObjContext)
                        .load(m_cObjJsonLeads.get(position).getImageUrl().toString())
                        .into(((DataObjectHolder) holder).banqImg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

}

