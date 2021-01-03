package com.example.dulha_jee.userlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dulha_jee.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserVH> {

    Context myCtx;
    List<UserPojo> userPojos;

    public UserAdapter(Context myCtx, List<UserPojo> userPojos) {
        this.myCtx = myCtx;
        this.userPojos = userPojos;
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
        holder.userName.setText(userPojos.get(position).getUserName());
        holder.orderNumber.setText(userPojos.get(position).getUserOrderNumber());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(myCtx, "Edit", Toast.LENGTH_SHORT).show();
            }
        });

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(myCtx, "View", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userPojos.size();
    }

    public class UserVH extends RecyclerView.ViewHolder {
        TextView userName, orderNumber;
        ImageView edit, view;

        public UserVH(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            edit = itemView.findViewById(R.id.editCustomer);
            view = itemView.findViewById(R.id.viewCustomer);
        }
    }
}
