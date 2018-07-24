package com.sctaylor.example.screens.user.core;

import com.sctaylor.example.application.network.ExampleService;
import com.sctaylor.example.models.Email;
import com.sctaylor.example.models.User;
import com.sctaylor.example.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by simon on 7/11/2018.
 */

public class UserPresenter extends BasePresenter<UserContract.UserView> implements UserContract.UserPresenter {

    private ExampleService service;
    private List<Email> emailList = new ArrayList<Email>();


    public UserPresenter(UserContract.UserView view, ExampleService service) {
        super(view);

        this.service = service;
    }

    @Override
    public Email getEmail(int position) {
        return emailList.get(position);
    }

    @Override
    public void loadUser() {

        view.showProgress();

        addDisposable(service.getUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        Timber.tag("User").i("Successfully got user: " + user.toString());
                        view.setFirstName(user.getFirstName());
                        view.setLastName(user.getLastName());
                        view.setEmail(user.getEmail());
                        view.setGender(user.getGender());
                        view.setIPAddress(user.getIpAddress());
                        view.setImage(user.getImage());

                        emailList.clear();
                        emailList.addAll(user.getEmails());
                        view.updateEmailList();

                        view.hideProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.tag("User").e(throwable);
                        view.hideProgress();
                    }
                }));
    }

    @Override
    public void setEmailItem(UserContract.EmailItemHolder holder, int position) {
        Email email = emailList.get(position);

        holder.setContent(email.getContent());
        holder.setSender(email.getSender());
    }

    @Override
    public int getEmailCount() {
        return emailList.size();
    }
}
