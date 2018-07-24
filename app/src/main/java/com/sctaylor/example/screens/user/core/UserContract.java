package com.sctaylor.example.screens.user.core;

import com.sctaylor.example.models.Email;

/**
 * Created by simon on 7/11/2018.
 */

public interface UserContract {

    interface UserPresenter {
        void loadUser();
        int getEmailCount();
        void setEmailItem(EmailItemHolder holder, int position);
        Email getEmail(int position);
    }

    interface UserView {
        void setFirstName(String firstName);
        void setLastName(String lastName);
        void setEmail(String email);
        void setGender(String gender);
        void setIPAddress(String ip);
        void setImage(String imageUrl);
        void showProgress();
        void hideProgress();
        void updateEmailList();
    }

    interface EmailItemHolder {
        void setSender(String sender);
        void setContent(String content);
    }
}
