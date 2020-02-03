package com.testapp.dagger2test.di

import android.app.Application
import com.testapp.dagger2test.BaseApplication
import com.testapp.dagger2test.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component (
    modules = ( arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuildersModule::class,
        ViewModelFactoryModule::class
        )
    )
)
interface AppComponent : AndroidInjector<BaseApplication>{

    //Will live for all application lifetime
    val sessionManager : SessionManager

    //fun sessionManager(): SessionManager

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application : Application) : Builder

        fun build() : AppComponent
    }
}