//
// Created by Adrian Witaszak on 17/10/2022.
// Copyright (c) 2022 orgName. All rights reserved.
//

import Foundation
import shared

func startKoin() {
    let koinApplication = PlatformModuleKt.doInitKoin { _ in  }
    _koin = koinApplication.koin
}

private var _koin: Koin_coreKoin?
var koin: Koin_coreKoin {
    _koin!
}
