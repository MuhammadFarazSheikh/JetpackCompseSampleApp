package com.lovetocode.diseasesymptoms.koin;

import com.lovetocode.diseasesymptoms.repositories.CommonRepositry
import com.lovetocode.diseasesymptoms.viewmodels.CommonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val commonModule = module {
    viewModel{
        CommonViewModel(get())
    }

    factory {
        CommonRepositry(get(),get())
    }
}


