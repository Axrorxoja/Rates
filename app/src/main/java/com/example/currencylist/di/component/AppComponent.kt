package com.example.currencylist.di.component

import com.example.currencylist.App
import com.example.currencylist.di.module.app.ActivitiesModule
import com.example.currencylist.di.module.app.AppModule
import com.example.currencylist.di.module.app.DatabaseModule
import com.example.currencylist.di.module.app.NetModule
import com.example.currencylist.di.scope.AppScope
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivitiesModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<App>
}
