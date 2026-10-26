# Android Management App

Nandi is an Android application built with Java and XML in Android Studio. The project includes screens for managing customers, products, and prices, with local SQLite storage and Firebase Realtime Database integration.

## Features

- Registration and login screens
- Customer, product, and price entry screens
- Lists for viewing saved customers, products, and prices
- Search and item deletion in list screens where implemented
- SQLite helpers for local data
- Firebase Realtime Database integration
- Android UI built with XML layouts

> This README describes the features suggested by the source code. The app has not been fully regression-tested, so do not treat this as a guarantee that every feature works correctly.

## Tech stack

- Java
- XML layouts
- Android Studio and Gradle
- SQLite
- Firebase Realtime Database
- AndroidX, RecyclerView, Material Components, and Volley

## Requirements

- Android Studio
- Android SDK platform 29 (or install the platform requested by the project)
- JDK 11 for the project's legacy Android Gradle Plugin 4.2.1 / Gradle 6.7.1 setup
- A Firebase project configured for the application ID `com.example.nandivishal`

## Open and run

1. Download or clone this repository.
2. Open the `NandiVishal` project folder in Android Studio (the folder containing `settings.gradle`).
3. Allow Gradle sync to finish.
4. Confirm `app/google-services.json` belongs to your Firebase project. If you do not have access to the original Firebase project, replace this file with your own Firebase configuration and configure Realtime Database rules.
5. Connect an Android device or start an emulator, then select **Run**.

## Build check

In Android Studio, choose **Build → Make Project**. Then run the app and test registration/login, customer entry/list/deletion, product entry/list/deletion, price entry/list/deletion, and Firebase-backed operations.

## Screenshots
<img width="390" height="688" alt="image" src="https://github.com/user-attachments/assets/62d85ea5-f670-44b8-b80f-ea406f583188" />
<img width="387" height="681" alt="image" src="https://github.com/user-attachments/assets/1c6bea8d-5762-4ba8-a782-3beb43671c4b" />

<img width="388" height="684" alt="image" src="https://github.com/user-attachments/assets/662c83b6-07dd-4a0a-b082-f0546b7a7cc6" />

<img width="382" height="682" alt="image" src="https://github.com/user-attachments/assets/f210c096-d9fd-47d0-90f8-7d8f78ba5705" />



- `local.properties` is intentionally excluded because it contains a machine-specific Android SDK path.
- Gradle caches and generated `build/` output are excluded from version control.
- Never put private service-account credentials, passwords, or other secrets in this repository.
- The current codebase uses legacy Android build-tool versions. Upgrade Android Gradle Plugin and Gradle as a separate change after confirming the app builds and works.
