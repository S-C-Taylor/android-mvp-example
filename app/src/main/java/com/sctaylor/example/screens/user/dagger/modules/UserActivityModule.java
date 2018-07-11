package com.sctaylor.example.screens.user.dagger.modules;

import com.sctaylor.example.screens.user.UserActivity;
import com.sctaylor.example.screens.user.dagger.scopes.UserActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by simon on 6/27/2018.
 */

@Module
public class UserActivityModule {
    private final UserActivity userActivity;

    public UserActivityModule(UserActivity userActivity){
        this.userActivity= userActivity;
    }

    @Provides
    @UserActivityScope
    public UserActivity getUserActivity() {
        return this.userActivity;
    }
}
