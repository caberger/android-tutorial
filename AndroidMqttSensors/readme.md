# Subscribe to Location Demo App

## Purpose

The application shows how to use RXJava with Jetpack compose. Logic is implemented in Java, while the UI is
done with Jetpack Compose.

We use a pure [Single-Source-Of-Truth](https://redux.js.org/understanding/thinking-in-redux/three-principles).

## Building

Make sure you select Java 17 or higher as Gradle's Java Version.

## Troubleshooting.

Currently there is a problem with location simulation in the android virtual device emulator, see [here](https://issuetracker.google.com/issues/242438611?pli=1).
Until it is fixed you should clear your caches. You can use the remove shell [script](./scripts/remove-android-studio.sh).