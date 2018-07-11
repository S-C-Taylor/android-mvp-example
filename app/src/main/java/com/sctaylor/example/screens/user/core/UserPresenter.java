package com.sctaylor.example.screens.user.core;

import com.sctaylor.example.application.network.ExampleService;
import com.sctaylor.example.models.User;
import com.sctaylor.example.mvp.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by simon on 7/11/2018.
 */

public class UserPresenter extends BasePresenter<UserContract.UserView> implements UserContract.UserPresenter {

    private ExampleService service;


    public UserPresenter(UserContract.UserView view, ExampleService service) {
        super(view);

        this.service = service;
    }

    @Override
    public void loadUser() {

        getView().showProgress();

        addDisposable(service.getUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        Timber.tag("User").i("Successfully got user: " + user.toString());
                        getView().setFirstName(user.getFirstName());
                        getView().setLastName(user.getLastName());
                        getView().setEmail(user.getEmail());
                        getView().setGender(user.getGender());
                        getView().setIPAddress(user.getIpAddress());
                        getView().setImage(user.getImage());

                        getView().hideProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.tag("User").e(throwable);
                        getView().hideProgress();
                    }
                }));
    }
}
