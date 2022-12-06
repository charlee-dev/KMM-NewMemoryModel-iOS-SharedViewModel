//
//  ObservableMainViewModel.swift
//  iosApp
//
//  Created by Adrian Witaszak on 17/10/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

class ObservableMainViewModel: ObservableObject {
    let viewModel: MainViewModel = KotlinDependencies.shared.getMainViewModel()

    @Published var mainState: MainState = MainState(userState: nil)

    init() {
        mainState = viewModel.mainState.value as! MainState
        observeUserState()
    }

    private func observeUserState() {
        viewModel.mainState.collect(
                collector: Collector<MainState> { [self] mainState in
                    onStateReceived(mainState: mainState)
                }
        ) { error in
            print("Error occurred during state collection - \(String(describing: error))")
        }
    }

    private func onStateReceived(mainState: MainState) {
        print("mainState = \(mainState)")
        self.mainState = mainState
    }
}

extension MainViewModel {
    func observableState() -> ObservableMainState {
        (mainState.value as! MainState).wrapAsObservable()
    }
}

class ObservableMainState: ObservableObject {
    @Published var value: MainState

    init(value: MainState) {
        self.value = value
    }
}

extension MainState {
    func wrapAsObservable() -> ObservableMainState {
        ObservableMainState(value: self)
    }
}
