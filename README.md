# DaWandaLite 
## Android Programming Test

First, thank you for reviewing. I hope you like it. 

### Environment

Android Studio 2.3.3
compileSdkVersion 25
minSdkVersion 16
targetSdkVersion 25

Tested in:
 - emulators with APIs 16, 17, 19, 21, 24, 25
 - device: oneplus 3 with android 7.1.1

### Tools

I spent quite lot of time trying to decide what tools should I use for this project. At the end I decided to use for the first time the new Architecture Components (Lifecycle, LiveData and ViewModel) that android released recently. I used version `alpha5` because with the newest Android Studio 3.0 is required (the newest is `alpha9`). I have never used before but I will use them again, I liked working with them and I learnt a lot, even thought I feel like I didn't use them at full power.

I was thinking in using Kotlin as well, but that would have required that you have all the environment set up. Also I am still learning it so I wouldnt be as fast as with java. Since I didn't have access to the Data Objects of Kotlin I used AutoValue in combination with Gson and Parcel AutoValue extensions.

It is very easy, with the current architecture to add new screens. But also to add more repositories, like a local database, and fetch products from there if, for example, there is no internet.

### Stage 1: Categories list

I completed stage 1 with no major issues. I focused in API 21+, and in previous versions the UI is not that good. I made a version of DaWanda's home screen section.

I made it state safe and network safe as well. I found it very easy to achieve with the Architecture components.

You can try the error screen by setting flight more, it includes a retry button as well. I also added a refresh button in the toolbar.

Note: I have added an interceptor to the retrofit client to delay the request for one second. It can be found in: `net.albertogarrido.dawandalite.di.DI` in case you want to test it without it.

### Stage 2: products by category list

Here there is not much to add, it works exactly the same as the categories screen. I also added support to display the 3 badges I found in the payload (new, express and sale). The UI is also similar to DaWanda's products list.

The only thing I skipped here is the transition animation from category to products list. But such animation is present in the next step.

### Stage 3: product detail

This screen starts by a shared element transition animation and it displays title, price, seller and badge. 

It looks a bit weird due lack of information. It uses a `CoordinatorLayout` with a `CollapsingToolbarLayout`. That provides a way to animate the image when scrolling. At the moment there is not much data, but in this screen would make sense to add data in a scrolled window (product description, comments, ratings, etc). Also I wanted to show this kind of UI.

### Conclusion

I learnt a lot and had some fun working on this, I hope you like it and looking forward to getting some feedback (if any).