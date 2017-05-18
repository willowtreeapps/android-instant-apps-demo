package com.willowtreeapps.instantappsdemo.feature.cart.injection;

import android.content.Context;

import com.willowtreeapps.instantappsdemo.feature.cart.ui.CartViewModel;
import com.willowtreeapps.instantappsdemo.injection.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by willowtree on 5/12/17.
 */
@Component(modules = ApplicationModule.class)
@Singleton
public interface FeatureComponent {

    void inject(CartViewModel cartViewModel);

    @Component.Builder
    interface Builder {
        FeatureComponent build();

        @BindsInstance
        Builder bindContext(Context context);
    }
}
