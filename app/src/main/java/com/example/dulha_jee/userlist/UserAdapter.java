package com.example.dulha_jee.userlist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dulha_jee.R;
import com.example.dulha_jee.pojo.SearchResponseBody;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserVH> {

    Context myCtx;
    List<SearchResponseBody.user> users;
    NavController navController;
    IonDeleteOrder ionDeleteOrder;

    public UserAdapter(Context myCtx, List<SearchResponseBody.user> userPojos, NavController navController , IonDeleteOrder ionDeleteOrder) {
        this.myCtx = myCtx;
        this.users = userPojos;
        this.navController = navController;
        this.ionDeleteOrder = ionDeleteOrder;
    }

    @NonNull
    @Override
    public UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_user_list_detail, parent, false);
        return new UserVH(view,this.ionDeleteOrder);
    }

    @Override
    public void onBindViewHolder(@NonNull UserVH holder, int position) {


        holder.userName.setText(users.get(position).getCustomer_name());
        holder.orderNumber.setText(users.get(position).getOrder_number());

        holder.orderStatus.setText(users.get(position).getOrder_status() == null ? "" : users.get(position).getOrder_status());
        holder.number.setText(users.get(position).getMobile_number());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(myCtx, "View", Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("url", users.get(position).getHtml());
                bundle.putString("ordernumber", users.get(position).getOrder_number());
                bundle.putString("orderstat", users.get(position).getOrder_status());
                navController.navigate(R.id.action_userList_to_customer_View_Fragment, bundle);
            }
        });

        if(users.get(position).getOrder_number() == null){
            holder.deleteCustomer.setVisibility(View.GONE);
        }else{
            holder.deleteCustomer.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserVH extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView userName, orderNumber, orderStatus , number;
        ImageView edit, view , deleteCustomer;
        IonDeleteOrder ionDeleteOrder1;

        public UserVH(@NonNull View itemView, IonDeleteOrder ionDeleteOrder) {
            super(itemView);
            orderStatus = itemView.findViewById(R.id.orderStatus);
            userName = itemView.findViewById(R.id.userName);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            deleteCustomer = itemView.findViewById(R.id.deleteCustomer);
            view = itemView.findViewById(R.id.viewCustomer);
            number = itemView.findViewById(R.id.number);
            ionDeleteOrder1 = ionDeleteOrder;
            itemView.setOnClickListener(this);
            deleteCustomer.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.deleteCustomer:
                    String order_number = users.get(getAdapterPosition()).getOrder_number() == null ? "123" :  users.get(getAdapterPosition()).getOrder_number();
                    ionDeleteOrder1.onDeleteOrder(order_number,getAdapterPosition());
                    break;
            }
        }
    }
}
