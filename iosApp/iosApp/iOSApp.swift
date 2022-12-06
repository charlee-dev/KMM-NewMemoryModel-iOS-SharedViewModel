import SwiftUI

@main
struct iOSApp: App {
	@StateObject var observable = ObservableMainViewModel()

	init() {
		startKoin()
	}

	var body: some Scene {
		WindowGroup {
			if observable.mainState.userState != nil {
				HomeScreen()
						.environmentObject(observable)
			} else {
				LoginScreen()
						.environmentObject(observable)
			}
		}
	}
}
