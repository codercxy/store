package com.dd.android.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.android.R;
import com.dd.android.model.WineList;
import com.dd.android.utils.RecyclerViewClickListener;

import java.util.List;


public class MainAdapter extends RecyclerView.Adapter<MainViewHolder>{

    private Context mContext;
    private String[] titles;
    private Integer[] picRes;
    private RecyclerViewClickListener listener;


    public MainAdapter(String[] titles, Integer[] picRes) {
        this.titles = titles;
        this.picRes = picRes;
    }

    public WineList getList() {
        WineList wineList = new WineList();
        wineList.setPics(picRes);
        wineList.setTitles(titles);

        return wineList;
    }

    public void setRecyclerListListener(RecyclerViewClickListener listListener) {
        this.listener = listListener;
    }
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wine, parent, false);

        this.mContext = parent.getContext();
        return new MainViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder,final int position) {

        holder.coverImageView.setImageResource(picRes[position]);
        holder.titleTextView.setText(titles[position]);

    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}

class MainViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener{

    private final RecyclerViewClickListener onClickListener;
    ImageView coverImageView;
    TextView titleTextView;

    public MainViewHolder(View itemView, RecyclerViewClickListener onClickListener) {
        super(itemView);

        titleTextView = (TextView) itemView.findViewById(R.id.item_main_title);
        coverImageView = (ImageView) itemView.findViewById(R.id.item_main_cover);
        coverImageView.setDrawingCacheEnabled(true);
        coverImageView.setOnTouchListener(this);
        this.onClickListener = onClickListener;


    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP && event.getAction() != MotionEvent.ACTION_MOVE) {

            onClickListener.onClick(view, getPosition());
        }
        return true;
    }
}
