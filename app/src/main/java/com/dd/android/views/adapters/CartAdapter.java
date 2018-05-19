package com.dd.android.views.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.provider.BaseColumns;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dd.android.DDWineApp;
import com.dd.android.R;
import com.dd.android.model.CartItem;
import com.dd.android.model.Constants;
import com.dd.android.model.Order;
import com.dd.android.provider.DbOrders;
import com.dd.android.provider.DbProvider;
import com.dd.android.utils.ItemSlideHelper;
import com.dd.android.utils.RecyclerViewClickListener;
import com.dd.android.views.fragments.CartFragment;

import java.util.List;


/**
 * Created by 57248 on 2016/8/22.
 */
public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> implements ItemSlideHelper.Callback{

    private DDWineApp mApplication;
    private List<Order> orderList;
    private List<CartItem> itemList;
    private View.OnClickListener listener;
    private RecyclerView mRecyclerView;
    private View mView;
    private TextView totalText;
    private TextView discountText;
    private TextView benefitText;
    private DbProvider provider;

    public CartAdapter(View view, List<CartItem> itemList, DDWineApp mApplication, List<Order> orderList) {
        this.mView = view;
        this.itemList = itemList;
        this.mApplication = mApplication;
        this.orderList = orderList;
        this.totalText = (TextView) mView.findViewById(R.id.cart_total);
        this.discountText = (TextView) mView.findViewById(R.id.cart_discount);
        this.benefitText = (TextView) mView.findViewById(R.id.cart_benefit);
    }

    public void setRecyclerListener(View.OnClickListener listener) {
        this.listener = listener;
    }
    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);

        provider = new DbProvider();
        provider.init(mView.getContext());
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartViewHolder holder, final int position) {
        holder.coverImageView.setImageResource(itemList.get(position).getPicId());
        holder.title.setText(itemList.get(position).getTitle());
        holder.disc.setText(itemList.get(position).getDisc());
        holder.price.setText(String.valueOf(itemList.get(position).getPrice()));
        holder.num.setText(String.valueOf(itemList.get(position).getNum()));
        double currentCount = itemList.get(position).getPrice() * itemList.get(position).getNum();
        holder.count.setText(String.valueOf(Constants.getCount(currentCount)));
        holder.discount.setText(String.valueOf(Constants.getDiscount(currentCount)));
        holder.benefit.setText(String.valueOf(Constants.getBenefit(currentCount)));

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentNum = itemList.get(position).getNum();
                double currentCount = itemList.get(position).getCount();
                if(itemList.get(position).getNum() > 1) {
                    currentCount -= itemList.get(position).getPrice();
                    currentNum -= 1;

                    itemList.get(position).setNum(currentNum);
                    itemList.get(position).setCount(currentCount);

                    holder.num.setText(String.valueOf(currentNum));
                    holder.count.setText(String.valueOf(Constants.getCount(currentCount)));
                    holder.discount.setText(String.valueOf(Constants.getDiscount(currentCount)));
                    holder.benefit.setText(String.valueOf(Constants.getBenefit(currentCount)));
                    updateOrder(position, currentNum);
                    updateTotalText(false, itemList.get(position).getPrice(), 1);
                }
            }
        });

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int currentNum2 = itemList.get(position).getNum() + 1;
                double currentCount2 = itemList.get(position).getCount() + itemList.get(position).getPrice();

                itemList.get(position).setNum(currentNum2);
                itemList.get(position).setCount(currentCount2);

                holder.num.setText(String.valueOf(currentNum2));
                holder.count.setText(String.valueOf(Constants.getCount(currentCount2)));
                holder.discount.setText(String.valueOf(Constants.getDiscount(currentCount2)));
                holder.benefit.setText(String.valueOf(Constants.getBenefit(currentCount2)));
                updateOrder(position, currentNum2);
                updateTotalText(true, itemList.get(position).getPrice(), 1);
            }
        });

        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.getLayoutPosition();
                updateTotalText(false, itemList.get(n).getPrice(), itemList.get(n).getNum());
                provider.delete(DbOrders.CONTENT_URI, "product_id = ?",
                        new String[]{String.valueOf(orderList.get(n).getProductId())});
                orderList.remove(n);
                itemList.remove(n);
                notifyItemRemoved(n);



            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void updateOrder(int position, int num) {
        orderList.get(position).setNumber(num);
        ContentValues values = new ContentValues();
        values.put(DbOrders.Order.PRODUCT_ID, orderList.get(position).getProductId());
        values.put(DbOrders.Order.NUMBER, num);
        provider.update(DbOrders.CONTENT_URI, values, "product_id = ?",
                new String[]{String.valueOf(orderList.get(position).getProductId())});
        //mApplication.updateOrderList(orderList);
    }

    public void updateTotalText(Boolean isAdd, double price, int num) {
        double totalNum = Double.parseDouble((String) totalText.getText());
        if (isAdd) {
            totalNum += price * num;
            totalText.setText(String.valueOf(Constants.getCount(totalNum)));
            discountText.setText(String.valueOf(Constants.getDiscount(totalNum)));
            benefitText.setText(String.valueOf(Constants.getBenefit(totalNum)));
        } else {
            totalNum -= price*num;
            totalText.setText(String.valueOf(Constants.getCount(totalNum)));
            discountText.setText(String.valueOf(Constants.getDiscount(totalNum)));
            benefitText.setText(String.valueOf(Constants.getBenefit(totalNum)));
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRecyclerView = recyclerView;
        mRecyclerView.addOnItemTouchListener(new ItemSlideHelper(mRecyclerView.getContext(), this));
    }

    @Override
    public int getHorizontalRange(RecyclerView.ViewHolder holder) {
        if (holder.itemView instanceof LinearLayout) {
            ViewGroup viewGroup = (ViewGroup) holder.itemView;
            if (viewGroup.getChildCount() == 2) {
                return viewGroup.getChildAt(1).getLayoutParams().width;
            }
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder getChildViewHolder(View childView) {
        return mRecyclerView.getChildViewHolder(childView);
    }

    @Override
    public View findTargetView(float x, float y) {
        return mRecyclerView.findChildViewUnder(x, y);
    }



}

class CartViewHolder extends RecyclerView.ViewHolder {
    ImageView coverImageView;
    TextView title;
    TextView disc;
    TextView price;
    TextView num;
    TextView count;
    TextView discount;
    TextView benefit;
    ImageView add;
    ImageView minus;

    TextView removeBtn;


    public CartViewHolder(View itemView) {
        super(itemView);

        coverImageView = (ImageView) itemView.findViewById(R.id.cart_item_img);
        title = (TextView) itemView.findViewById(R.id.cart_item_title);
        disc = (TextView) itemView.findViewById(R.id.cart_item_disc);
        price = (TextView) itemView.findViewById(R.id.cart_item_price);
        num = (TextView) itemView.findViewById(R.id.cart_item_num);
        count =  (TextView) itemView.findViewById(R.id.cart_item_count);
        discount = (TextView) itemView.findViewById(R.id.cart_item_discount);
        benefit = (TextView) itemView.findViewById(R.id.cart_item_benefit);


        add = (ImageView) itemView.findViewById(R.id.cart_item_img_add);
        minus = (ImageView) itemView.findViewById(R.id.cart_item_img_minus);

        removeBtn = (TextView) itemView.findViewById(R.id.cart_item_delete);
    }

}
