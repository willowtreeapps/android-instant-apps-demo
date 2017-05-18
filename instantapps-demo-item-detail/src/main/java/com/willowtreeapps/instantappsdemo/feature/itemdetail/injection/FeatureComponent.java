package com.willowtreeapps.instantappsdemo.feature.itemdetail.injection;

import android.content.Context;

import com.willowtreeapps.instantappsdemo.feature.itemdetail.ui.ItemDetailActivityFragment;
import com.willowtreeapps.instantappsdemo.feature.itemdetail.ui.ItemDetailViewModel;
import com.willowtreeapps.instantappsdemo.injection.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = ApplicationModule.class)
@Singleton
public interface FeatureComponent {

    void inject(ItemDetailActivityFragment itemDetailActivityFragment);

    void inject(ItemDetailViewModel itemDetailViewModel);

    @Component.Builder
    interface Builder {
        FeatureComponent build();

        @BindsInstance
        Builder bindContext(Context context);
    }
}
