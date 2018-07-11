package com.sctaylor.example.application.dagger.components;

import com.sctaylor.example.application.dagger.modules.ExampleServiceModule;
import com.sctaylor.example.application.dagger.modules.PicassoModule;
import com.sctaylor.example.application.dagger.scopes.ExampleApplicationScope;
import com.sctaylor.example.application.network.ExampleService;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by simon on 6/27/2018.
 */

@ExampleApplicationScope
@Component(modules = {ExampleServiceModule.class, PicassoModule.class})
public interface ExampleApplicationComponent {

    Picasso getPicasso();

    ExampleService getExampleService();
}
