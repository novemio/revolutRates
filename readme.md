## Revolut Currencies - Novemio Demo

<p align="left">
<img src="screens/app_preview.png" width="200" hspace="30" />  
<img src="screens/app_no_connection.png" width="200"/>
</p>

## Task

* List all currencies you get from the endpoint (one per row).
* Each row has an input where you can enter any amount of money. 
* When you tap on a currency row it should slide to the top and it's input becomes the first responder.
* When you’re changing the amount the app must simultaneously update the corresponding value for other currencies.

## What describes this code

* Clean architecture
* Deserialization data 
* Repository pattern
* MVVM pattern for presentation layer
* Data binding
* Observable pattern
* Unit tests ( MockK )
* CI/CD 
* Firebase distribution ( The latest version ) : https://appdistribution.firebase.dev/i/n5BMzw6m  
* Bitrise integration :   [![Build Status](https://app.bitrise.io/app/33fdbb1b6b9b7795/status.svg?token=pCDc1XHZ8w5u86rYHrAfqA)](https://app.bitrise.io/app/33fdbb1b6b9b7795)
* Network handling 
* Performance
* Kotlin DSL
