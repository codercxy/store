package com.dd.android.di.components;

import com.dd.android.di.modules.ApplicationModule;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,

})

public interface AppComponent {
    //Bus bus();
}
