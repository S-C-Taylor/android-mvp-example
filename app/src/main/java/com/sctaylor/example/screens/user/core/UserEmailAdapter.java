package com.sctaylor.example.screens.user.core;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sctaylor.example.R;

/**
 * Created by simon on 7/24/2018.
 */

public class UserEmailAdapter extends RecyclerView.Adapter<UserEmailItemHolder> {

    private UserPresenter presenter;
    private UserEmailItemListener listener;

    public UserEmailAdapter(UserPresenter presenter, UserEmailItemListener listener) {
        this.presenter = presenter;
        this.listener = listener;
    }

    @Override
    public UserEmailItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_email, parent, false);

        return new UserEmailItemHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(UserEmailItemHolder holder, int position) {
        presenter.setEmailItem(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getEmailCount();
    }
}
