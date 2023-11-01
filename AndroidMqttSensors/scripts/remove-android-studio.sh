#!/usr/Bin/env bash
# script to remove android studio from your mac

pushd ~
    pushd ./Library
    rm -rf ./Android
    rm -rf ./Preferences/AndroidStudio
    rm -rf ./Preferences/com.google.android
    rm -rf ./Preferences/com.android
    rm -rf ./Application/Support/AndroidStudio
    rm -rf ./Logs/AndroidStudio
    rm -rf ./Caches/AndroidStudio
    popd
    rm -rf ./.android
popd

rm -rf /Applications/Android\ Studio.app
