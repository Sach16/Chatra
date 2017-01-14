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

import com.pissay.chatra.R;
import com.pissay.chatra.interfaces.RecyclerImagesListener;
import com.pissay.chatra.models.Attachments;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by S.K. Pissay on 30/8/16.
 */
public class CustomRecyclerAdapterForListOfMenuItems extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private static RecyclerImagesListener m_cClickListener;
    private static List<Attachments> m_cObjJson;
    private Context m_cObjContext;

    public CustomRecyclerAdapterForListOfMenuItems(Context pContext, List<Attachments> pJsonLeads, RecyclerImagesListener pListener) {
        this.m_cObjContext = pContext;
        this.m_cObjJson = pJsonLeads;
        this.m_cClickListener = pListener;
    }

    @Override
    public int getItemCount() {
        return m_cObjJson.size();
    }

    @Override
    public int getItemViewType(int position) {
        return m_cObjJson.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View lView;
        // paging logic
        if (viewType == VIEW_ITEM) {
            lView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_image_cell, parent, false);
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
        @BindView(R.id.MENU_IMG)
        ImageView menuImg;

        public DataObjectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Optional
        @OnClick({R.id.CV_BANQ_CELL})
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.CV_BANQ_CELL:
                    m_cClickListener.onImgClick(getPosition(), m_cObjJson.get(getPosition()), v);
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
                Picasso.with(m_cObjContext)
                        .load(m_cObjJson.get(position).getUri())
                        .into(((DataObjectHolder) holder).menuImg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

}

