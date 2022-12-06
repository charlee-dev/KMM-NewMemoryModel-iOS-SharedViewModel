//
//  Extensions.swift
//  iosApp
//
//  Created by Adrian Witaszak on 17/10/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

extension Binding {
    func onChange(_ handler: @escaping (Value) -> Void) -> Binding<Value> {
        Binding(
            get: { wrappedValue },
            set: { newValue in
                wrappedValue = newValue
                handler(newValue)
            }
        )
    }
}
