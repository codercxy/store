package com.dd.android.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.android.R;
import com.dd.android.model.CartItem;
import com.dd.android.model.Constants;
import com.dd.android.utils.RecyclerViewClickListener;

import java.util.List;

/**
 * Created by 57248 on 2016/8/23.
 */
public class OrderAdaper extends RecyclerView.Adapter<OrderViewHolder> {
    private List<CartItem> itemList;
    private RecyclerViewClickListener listener;

    public OrderAdaper(List<CartItem> itemList) {
        this.itemList = itemList;
    }

    public void setRecyclerListener(RecyclerViewClickListener listener) {
        this.listener = listener;
    }
    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        holder.coverImageView.setImageResource(itemList.get(position).getPicId());
        holder.title.setText(itemList.get(position).getTitle());
        holder.disc.setText(itemList.get(position).getDisc());
        holder.price.setText(String.valueOf(itemList.get(position).getPrice()));
        holder.num.setText(String.valueOf(itemList.get(position).getNum()));
        holder.subTotal.setText(String.valueOf(Constants.getCount(itemList.get(position).getCount())));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}

class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
    private final RecyclerViewClickListener onClickListener;
    ImageView coverImageView;
    TextView title;
    TextView disc;
    TextView price;
    TextView num;
    TextView subTotal;


    public OrderViewHolder(View itemView, RecyclerViewClickListener listener) {
        super(itemView);

        coverImageView = (ImageView) itemView.findViewById(R.id.order_item_img);
        title = (TextView) itemView.findViewById(R.id.order_item_title);
        disc = (TextView) itemView.findViewById(R.id.order_item_disc);
        price = (TextView) itemView.findViewById(R.id.order_item_price);
        num = (TextView) itemView.findViewById(R.id.order_item_num);
        subTotal = (TextView) itemView.findViewById(R.id.order_item_count);

        this.onClickListener = listener;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP && event.getAction() != MotionEvent.ACTION_MOVE) {

            onClickListener.onClick(view, getPosition());
        }
        return true;
    }
}
