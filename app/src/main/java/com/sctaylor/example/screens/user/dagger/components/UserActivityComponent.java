package com.sctaylor.example.screens.user.dagger.components;

import com.sctaylor.example.application.dagger.components.ExampleApplicationComponent;
import com.sctaylor.example.screens.user.UserActivity;
import com.sctaylor.example.screens.user.dagger.modules.UserActivityModule;
import com.sctaylor.example.screens.user.dagger.modules.UserModule;
import com.sctaylor.example.screens.user.dagger.scopes.UserActivityScope;

import dagger.Component;

/**
 * Created by simon on 6/27/2018.
 */

@UserActivityScope
@Component(modules = {UserActivityModule.class, UserModule.class}, dependencies = {ExampleApplicationComponent.class})
public interface UserActivityComponent {

    void injectUserActivity(UserActivity userActivity);

}