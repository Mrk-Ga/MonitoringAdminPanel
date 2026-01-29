# IoT_project

This is an Android application for an IoT project's admin panel. It allows administrators to manage gates, clients, buildings, and access logs.

## Getting Started

To build and run the application, open the project in Android Studio and run it on an emulator or a physical device.

## Project Structure

The project is structured as follows:

- `app/src/main/java/com/example/iot_project`: The main package of the application.
  - `access_logs`: Contains the screen and view model for managing access logs.
  - `api`: Contains the Retrofit API for communicating with the backend server.
  - `buildings`: Contains the screen and view model for managing buildings.
  - `clients`: Contains the screen and view model for managing clients.
  - `gates`: Contains the screen and view model for managing gates.
  - `models`: Contains the data models for the application.
  - `repositories`: Contains the repositories for accessing data from the API.
  - `ui`: Contains the UI components of the application.
- `app/src/main/res`: Contains the resources of the application.
- `app/build.gradle.kts`: The build script for the application.

## Dependencies

The project uses the following major dependencies:

- [Jetpack Compose](https://developer.android.com/jetpack/compose) for building the UI.
- [Retrofit](https://square.github.io/retrofit/) for making HTTP requests to the backend server.
- [Gson](https://github.com/google/gson) for converting JSON to Kotlin objects.
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) for asynchronous programming.
- [Jetpack Navigation](https://developer.android.com/guide/navigation) for navigating between screens.
- [Material Design 3](https://m3.material.io/) for the UI design.
