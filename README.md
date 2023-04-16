<!-- PROJECT LOGO -->

<p align="center">
  <img src="https://github.com/mobillium-android/batuhan-gunes/blob/408a4f11492856f7e4d8f174015c22889d927691/images/ic_launcher_round.png" alt="Logo" width="100" height="100" align="center">
  <h3 align="center" >Movie App</h3>
</p>

<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Architecture](#architecture)
  * [App Screenshots](#app-screenshots)
  * [Libraries Used](#libraries-used)
* [Getting Started](#getting-started)
  * [Built With](#built-with)
  * [Prerequisites](#prerequisites)
* [Contact](#contact)

<!-- ABOUT THE PROJECT -->
## About The Project

This is simple movie list app. The application receives movie information from <a href="https://www.themoviedb.org" target="_blank">TMDB</a> at startup and presents it to the user as a list. The user can select a movie from this list and see its details on a different page.

You can access the api document <a href="https://developers.themoviedb.org/3/getting-started/introduction" target="_blank">here</a>.

<!-- Architecture -->
### Architecture

Android clean architecture was used for this project. It can be installed with clean architecture MVVM or MVP. In this project, this structure was used together with MVVM. clean architecture works in the simplest way in (Figure 1).

![clean-architecture_image1][clean-architecture_image3]
<h6 align="center" >(Figure 1)</h6>

***

As seen in (Figure 1), it consists of 3 main layers. Of these main layers, Presentation and Data layers are dependency on the Domain layer. 
At the same time, all of these 3 main layers are one module. For a modular project, each module in the project contains these 3 main layers within itself.
In this project, only one module was created. Modules can be added with new features. This architecture is conducive to extensibility. You can examine the above-mentioned clean architecture in more detail in (Figure 2) and (Figure 3).

***

![clean-architecture_image2][clean-architecture_image2]
<h6 align="center" >(Figure 2)</h6>

***

![clean-architecture_image1][clean-architecture_image1]
<h6 align="center" >(Figure 3)</h6>

<!-- App Screenshots -->
### App Screenshots

<p align="middle">
  <img width="300" src="https://github.com/mobillium-android/batuhan-gunes/blob/408a4f11492856f7e4d8f174015c22889d927691/images/portrait_screenshot_main_screen.jpg">
  <img width="300" src="https://github.com/mobillium-android/batuhan-gunes/blob/408a4f11492856f7e4d8f174015c22889d927691/images/portrait_screenshot_detail_screen.jpg"
</p>

<p align="middle">
  <img width="600" src="https://github.com/mobillium-android/batuhan-gunes/blob/408a4f11492856f7e4d8f174015c22889d927691/images/landspace_screenshot_main_screen.jpg">
</p>

***

<!-- Libraries Used -->
## Libraries Used

<ul>
<li><a href="https://developer.android.com/kotlin/flow" target="_blank">Flow</a></li>
<li><a href="https://developer.android.com/topic/libraries/architecture/viewmodel" target="_blank">ViewModel</a></li>
<li><a href="https://developer.android.com/topic/libraries/view-binding" target="_blank">ViewBinding</a></li>
<li><a href="https://developer.android.com/training/dependency-injection/hilt-android" target="_blank">Dagger - Hilt</a></li>
<li><a href="https://bumptech.github.io/glide/" target="_blank">Glide</a></li>
<li><a href="https://square.github.io/okhttp/" target="_blank">okHttp</a></li>
<li><a href="https://square.github.io/retrofit/" target="_blank">Retrofit2</a></li>
<li><a href="https://github.com/denzcoskun/ImageSlideshow" target="_blank">ImageSlideShow</a></li>
<li><a href="https://developer.android.com/guide/topics/ui/splash-screen" target="_blank">Splashscreen</a></li>
<li><a href="https://developer.android.com/guide/navigation/navigation-getting-started" target="_blank">Navigation Component</a></li>
<li><a href="https://https://developer.android.com/training/testing/espresso" target="_blank">Espresso</a></li>
<li><a href="https://junit.org/junit5/" target="_blank">Junit</a></li>
</ul>

<!-- Getting Started -->
## Getting Started

The project is available by cloning from the git repository.

<!-- Libraries Used -->
### Built With

- Java 17
- Kotlin Plugin

<!-- Prerequisites -->
### Prerequisites

- A physical or virtual device capable of running an android application so that the application can be tested or run (minSdkVersion = 24).


<!-- Contact -->
### Contact

- <a href="https://github.com/BatuhanGunes" target="_blank">Github Profile</a>
- [![LinkedIn][linkedin-shield]][linkedin-url]

<!-- MARKDOWN LINKS & IMAGES -->
[linkedin-url]: https://www.linkedin.com/in/batuhan-gunes
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555

[clean-architecture_image3]: https://github.com/mobillium-android/batuhan-gunes/blob/408a4f11492856f7e4d8f174015c22889d927691/images/MVVM%20Clean%20architecture_3.png
[clean-architecture_image2]: https://github.com/mobillium-android/batuhan-gunes/blob/408a4f11492856f7e4d8f174015c22889d927691/images/MVVM%20Clean%20architecture_2.png
[clean-architecture_image1]: https://github.com/mobillium-android/batuhan-gunes/blob/408a4f11492856f7e4d8f174015c22889d927691/images/MVVM%20Clean%20architecture_1.png
