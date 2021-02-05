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

    public UserAdapter(Context myCtx, List<SearchResponseBody.user> userPojos, NavController navController) {
        this.myCtx = myCtx;
        this.users = userPojos;
        this.navController = navController;
    }

    @NonNull
    @Override
    public UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_user_list_detail, parent, false);
        return new UserVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserVH holder, int position) {


        holder.userName.setText(users.get(position).getCustomer_name());
        holder.orderNumber.setText(users.get(position).getOrder_number());

        holder.orderStatus.setText(users.get(position).getOrder_status() == null ? "" : users.get(position).getOrder_status());


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(myCtx, "View", Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("url", users.get(position).getHtml());
                bundle.putString("ordernumber", users.get(position).getOrder_number());
                navController.navigate(R.id.action_userList_to_customer_View_Fragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserVH extends RecyclerView.ViewHolder {
        TextView userName, orderNumber, orderStatus;
        ImageView edit, view;

        public UserVH(@NonNull View itemView) {
            super(itemView);
            orderStatus = itemView.findViewById(R.id.orderStatus);
            userName = itemView.findViewById(R.id.userName);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            edit = itemView.findViewById(R.id.editCustomer);
            view = itemView.findViewById(R.id.viewCustomer);
        }
    }
}
