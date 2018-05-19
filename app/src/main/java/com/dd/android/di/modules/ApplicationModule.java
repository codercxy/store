package com.dd.android.di.modules;

import android.content.Context;

import com.dd.android.DDWineApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private final DDWineApp application;

    public ApplicationModule(DDWineApp application) {
        this.application = application;
    }
    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }


}
