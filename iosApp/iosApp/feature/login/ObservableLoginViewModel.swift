//
//  ObservableLoginViewModel.swift
//  iosApp
//
//  Created by Adrian Witaszak on 17/10/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class ObservableLoginModel: ObservableObject {
    let viewModel: LoginViewModel = KotlinDependencies.shared.getLoginViewModel()

    @Published var state = LoginUiState(
            mode: .login,
            email: "",
            password: "",
            userState: nil,
            isButtonDisabled: false,
            isLoading: false,
            error: nil
    )

    init() {
        state = viewModel.uiState.value as! LoginUiState
        observeState()
    }

    private func observeState() {
        viewModel.uiState.collect(
                collector: Collector<LoginUiState> {
                    self.state = $0
                }
        ) { error in
            print("Error occurred during state collection - \(error)")
        }
    }


}

extension LoginViewModel {
    func observableState() -> ObservableLoginUiState {
        (uiState.value as! LoginUiState).wrapAsObservable()
    }
}

class ObservableLoginUiState: ObservableObject {
    @Published var value: LoginUiState

    init(value: LoginUiState) {
        self.value = value
    }
}

extension LoginUiState {
    func wrapAsObservable() -> ObservableLoginUiState {
        ObservableLoginUiState(value: self)
    }
}
