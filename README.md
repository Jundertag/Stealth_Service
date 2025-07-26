# Stealth_Service

## Description
This is an android application that has a very basic interface. It has buttons to start a foreground service, ping it, and respective controls to stop pinging and kill the service.

## Disclaimer
**Do not use this template to develop any malware or otherwise malicious software that collects user's information without their consent**.
This project is for the sole purpose of my own and/or other's education. It is provided AS IS, meaning all features are available for free with minimal effort.
I am not responsible for any program, executable, or otherwise, that uses this template for malicious intent.
Any and all that do so will face legal punishment under applicable law.

## How to use
Before you use, it is recommended to download android studio and clone the master repository to then deploy on a mobile device, this way you can see all logcat output with minimal OS interference.

Press on "start stealth service" to create and initialize the service, by this time a notification channel will be created and the service will have called `startForeground()` with the `createNotification()` function passed in as the notification object.

by this point, no matter what you do (besides pressing "kill stealth service" or closing the app in the active apps menu, or going to settings and tapping "force stop" on the application), it will not stop running.

you can validate this by pressing "ping stealth service", which will launch a handler object that repeatedly logs to logcat, with the message "Ping Successful". Again, do what you want (besides tapping "stop pinging", "force stop" in settings, or "stop" in the active apps menu, it will continue logging, every second, even when the app is closed)

I will warn, this functionality isn't fully robust in this pre-release version. Closing the app and relaunching it will make the service harder to kill.

in this instance, press "kill stealth service", once this is done, it is no longer registered as a foreground service, and is just a normal service. At this point on, you can close the app, and the android system will kill the service just like any normal one.

## Extra Notes

For technical details, refer to the latest release in the tags section by clicking on "tags" on the right hand side of github.
