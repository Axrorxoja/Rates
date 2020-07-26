# CurrencyList

One screen Test app, The screen has a list of currencies fetched from the endpoint every 1 sec.


## App Features
1. The app lists the currencies, Each in a row and updates them every 1 sec.
1. User can tap on currency row and it slides to top and its input becomes the first responder.
2. User can change the amount and the app simultaneously updates the corresponding value for other currencies.

## App architecture
Based on MVVM architecture and repository pattern.

### App Packages:
* **data** - contains:
  * **local** -  contains classes needed for making request to local db
  * **remote** - contains classes needed for making API calls to server, using Retrofit.
* **di** - contains dependency injection classes, using Dagger2.
* **ui** - contains classes needed to display Activity and Fragment.
* **common** - contains app constants


### App Specs
* Minimum SDK 21.
* Kotlin.
* MVVM Architecture.
* Android Architecture Components (LiveData, Lifecycle, ViewModel,Room,Navigation component)
* Kotlin Flow
* Dagger 2 for dependency injection.
* Kotlin coroutines.
* Retrofit 2 for API integration.
* Moshi for serialisation.
* Junit 4 for testing.
* Timber for logging
* Glide for loading images
