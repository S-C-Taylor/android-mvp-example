package com.sctaylor.example.screens.user.core;

/**
 * Created by simon on 7/11/2018.
 */

public interface UserContract {

    interface UserPresenter {
        void loadUser();
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
    }
}
