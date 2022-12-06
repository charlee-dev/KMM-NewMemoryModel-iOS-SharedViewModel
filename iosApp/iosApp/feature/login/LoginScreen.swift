//
//  LoginScreen.swift
//  iosApp
//
//  Created by Adrian Witaszak on 17/10/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct LoginScreen: View {
    @StateObject var observable = ObservableLoginModel()
    
    var body: some View {
        NavigationView {
            VStack(alignment: .center) {
                Spacer()
                Text("Welcome to Shoppe")
                    .font(.largeTitle)
                Spacer()
                VStack {
                    TextField("Email", text: $observable.state.email.onChange(observable.viewModel.onEmailChanged))
                        .keyboardType(.emailAddress)
                    
                    if observable.state.mode != .forgotPassword {
                        SecureField("Password", text: $observable.state.password.onChange(observable.viewModel.onPasswordChanged))
                    }
                    
                    if observable.state.mode == .registration {
                        SecureField("Repeat Password", text: $observable.state.password.onChange(observable.viewModel.onPasswordChanged))
                    }
                    
                    HStack {
                        Spacer()
                        Button("Forgot password") {
                            observable.viewModel.setMode(mode: .forgotPassword)
                        }
                        .opacity(observable.state.mode == .login ? 1 : 0)
                    }
                    .padding(.bottom, 24)
                }
                
                Group {
                    switch observable.state.mode {
                    case .registration:
                        Button("Sign Up") {
                            observable.viewModel.signUp()
                        }
                        .padding()
                        .background(Color(red: 0, green: 0, blue: 0.5))
                        .foregroundColor(.white)
                        .clipShape(Capsule())
                        .disabled(observable.state.isButtonDisabled)
                    case .forgotPassword:
                        Button("Remind password") {
                            observable.viewModel.signUp()
                            // TODO:
                        }
                        .padding()
                        .background(Color(red: 0, green: 0, blue: 0.5))
                        .foregroundColor(.white)
                        .clipShape(Capsule())
                        .disabled(observable.state.isButtonDisabled)
                    default:
                        Button("Log In") {
                            observable.viewModel.signIn()
                        }
                        .padding()
                        .background(
                            observable.state.isButtonDisabled ? .gray : Color(red: 0, green: 0, blue: 0.5))
                        .foregroundColor(.white)
                        .clipShape(Capsule())
                        .disabled(observable.state.isButtonDisabled)
                    }
                }

                Spacer()

                if observable.state.isLoading {
                    ProgressView()
                }

                Text(observable.state.userState?.token ?? "")
                Spacer()
                
                HStack {
                    if observable.state.mode != .registration {
                        Button("Register") {
                            observable.viewModel.setMode(mode: .registration)
                        }
                    }
                    
                    if observable.state.mode != .login {
                        Button("Login") {
                            observable.viewModel.setMode(mode: .login)
                        }
                    }
                }
            }
            .autocapitalization(.none)
            .textFieldStyle(RoundedBorderTextFieldStyle())
            .padding()
            .disabled(observable.state.isLoading)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreen()
    }
}
