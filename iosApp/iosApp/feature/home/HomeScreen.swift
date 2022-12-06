//
//  HomeScreen.swift
//  iosApp
//
//  Created by Adrian Witaszak on 17/10/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct HomeScreen: View {
    @EnvironmentObject var observable: ObservableMainViewModel

    var body: some View {
        VStack(alignment: .center) {
            Text("Home screen")
            Button("Sign out", action: { observable.viewModel.signOut() })
        }
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
