package com.petz.pros.di.component;

import android.app.Application;
import android.content.Context;

import com.petz.pros.data.DataManager;
import com.petz.pros.di.ApplicationContext;
import com.petz.pros.di.module.ApplicationModule;
import com.petz.pros.root.WaveApp;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created on : Jan 19, 2019
 * Author     : AndroidWave
 * Email    : info@androidwave.com
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(WaveApp app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
//    @Component.Builder
//    interface Builder {
//        ApplicationComponent build();
//
//        Builder applicationModule(ApplicationModule applicationModule);
//
//        DataManager getDataManager();
//    }


}
