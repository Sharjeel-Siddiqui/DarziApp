package com.example.dulha_jee.userlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dulha_jee.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class UserListFragnment extends Fragment {

    RecyclerView recyclerView;
    UserAdapter userAdapter;
    List<UserPojo> userPojos;
    FloatingActionButton add;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_userlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_customerList);
        userPojos = new ArrayList<>();
        userPojos.add(new UserPojo(R.drawable.ic_user, "Sharjeel", "123456789"));
        userPojos.add(new UserPojo(R.drawable.ic_user, "Sharjeel", "123456789"));
        userPojos.add(new UserPojo(R.drawable.ic_user, "Sharjeel", "123456789"));
        userPojos.add(new UserPojo(R.drawable.ic_user, "Sharjeel", "123456789"));
        userPojos.add(new UserPojo(R.drawable.ic_user, "Sharjeel", "123456789"));

        userAdapter = new UserAdapter(getActivity(), userPojos);
        recyclerView.setAdapter(userAdapter);

        add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_userList_to_userCreation);
            }
        });

    }
}
