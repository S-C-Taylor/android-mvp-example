package com.sctaylor.example.screens.user.dagger.modules;

import android.content.SharedPreferences;
import com.sctaylor.example.application.network.ExampleService;
import com.sctaylor.example.screens.user.UserActivity;
import com.sctaylor.example.screens.user.core.UserContract;
import com.sctaylor.example.screens.user.core.UserPresenter;
import com.sctaylor.example.screens.user.dagger.scopes.UserActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by simon on 4/27/2018.
 */

@Module
public class UserModule {

    @UserActivityScope
    @Provides
    public UserPresenter provideUserPresenter(UserContract.UserView view, ExampleService service) {
        return new UserPresenter(view, service);
    }

    @UserActivityScope
    @Provides
    public UserContract.UserView provideUserView(UserActivity userActivity) {
        return userActivity;
    }

}