package com.sctaylor.example.screens.user.core;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sctaylor.example.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by simon on 7/24/2018.
 */

public class UserEmailItemHolder extends RecyclerView.ViewHolder implements UserContract.EmailItemHolder {

    private UserEmailItemListener listener;

    @BindView(R.id.textViewSender)
    TextView sender;

    @BindView(R.id.textViewContent)
    TextView content;

    @BindView(R.id.emailItemMainLayout)
    View container;

    public UserEmailItemHolder(View itemView, final UserEmailItemListener listener) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        this.listener = listener;

        this.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemClicked(getAdapterPosition());
            }
        });
    }

    @Override
    public void setSender(String sender) {
        this.sender.setText(sender);
    }

    @Override
    public void setContent(String content) {
        this.content.setText(content);
    }
}
