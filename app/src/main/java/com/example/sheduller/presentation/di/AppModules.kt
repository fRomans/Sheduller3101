package com.example.sheduller.presentation.di

import androidx.room.Room
import com.example.sheduller.presentation.authentication.AuthenticationViewModel
import com.example.sheduller.data.api.ApiInterface
import com.example.sheduller.data.localDB.DbTsFwo
import com.example.sheduller.data.repository.dataSource.AuthenticationApiDataSource
import com.example.sheduller.data.repository.dataSource.EventsApiDataSource
import com.example.sheduller.data.repository.dataSource.EventsDataSource
import com.example.sheduller.data.repository.dataSource.GroupsApiDataSource
import com.example.sheduller.data.repository.dataSource.GroupsDataSource
import com.example.sheduller.data.repository.dataSourceImpl.AuthenticationApiDataSourceImpl
import com.example.sheduller.data.repository.dataSourceImpl.EventsApiDataSourceImpl
import com.example.sheduller.data.repository.dataSourceImpl.EventsDataSourceImpl
import com.example.sheduller.data.repository.dataSourceImpl.GroupsApiDataSourceImpl
import com.example.sheduller.data.repository.dataSourceImpl.GroupsDataSourceImpl
import com.example.sheduller.data.repository.repository.AuthenticationRepository
import com.example.sheduller.data.repository.repository.EventsRepository
import com.example.sheduller.data.repository.repository.GroupsRepository
import com.example.sheduller.domain.repository.AuthenticationCall
import com.example.sheduller.domain.repository.EventsCall
import com.example.sheduller.domain.repository.GroupsCall
import com.example.sheduller.domain.useCase.AuthenticationUseCase
import com.example.sheduller.domain.useCase.EventsUseCase
import com.example.sheduller.domain.useCase.GroupsUseCase
import com.example.sheduller.presentation.EventsDay.EventsViewModel
import com.example.sheduller.presentation.Groups.GroupsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleDB = module {

    single {
        Room.databaseBuilder(
            androidContext(), DbTsFwo::class.java,
            "localDBTSFwo"
        ).build()
    }

}

val moduleEvents = module{

    moduleDB

    single { get<DbTsFwo>().eventsDao }



    single<EventsDataSource> {
        EventsDataSourceImpl(
            get()
        )
    }

    single<EventsApiDataSource> {
        EventsApiDataSourceImpl(
            get()
        )
    }

    single<EventsCall> { EventsRepository(get(),get()) }

    single { EventsUseCase(get()) }

    viewModel { EventsViewModel(get()) }

}


val moduleAuthentication = module{


    single { get<ApiInterface>() }





    single<AuthenticationApiDataSource> {
        AuthenticationApiDataSourceImpl(

        )
    }

    single<AuthenticationCall> { AuthenticationRepository(get()) }

    single { AuthenticationUseCase(get()) }

    viewModel { AuthenticationViewModel(get()) }

}

val moduleCreateGroup = module{

    single { get<ApiInterface>() }

    single<GroupsApiDataSource> {
        GroupsApiDataSourceImpl(
            get()
        )
    }

    single<GroupsCall> { GroupsRepository(get(), get()) }

    single { GroupsUseCase(get()) }

    viewModel { GroupsViewModel(get()) }

}



val moduleGroups = module{

    moduleDB

    single { get<DbTsFwo>().groupsDao }



    single<GroupsDataSource> {
        GroupsDataSourceImpl(
            get()
        )
    }

    single<GroupsApiDataSource> {
        GroupsApiDataSourceImpl(
            get()
        )
    }

    single<GroupsCall> { GroupsRepository(get(),get()) }

    single { GroupsUseCase(get()) }

    viewModel { GroupsViewModel(get()) }

}


