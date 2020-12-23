# Engineering Notes

## Building UI Layer

### Asteroid List

#### Characteristics:

- ordered by approach date
- contains a header: approach date
- fast scrolling: by alphabet (asteroid name)
- filter: by asteroid name and orbit class description
- shimmer effect: fetching data
- infinite scrolling: loading more data from internet or local storage
- asteroid image (estimate asteroid size)

#### Card Composition (https://material.io/components/cards)

- Thumbnail: icon/image -> distance/size
- Header text: asteroid name
- Supporting text: orbit class description
- Icons for actions
	- More info (use ChromeCustomTabs for displaying page from nasa_jpl_url)
	- Share
	- Favorite (later)

#### Base UI

- https://video.udacity-data.com/topher/2020/June/5edeac1d_screen-shot-2020-06-08-at-2.21.53-pm/screen-shot-2020-06-08-at-2.21.53-pm.png

#### Supporting library

- https://github.com/mikepenz/FastAdapter
- https://github.com/afollestad/recyclical (maybe in conjunction with FastAdapter)
- https://www.androidhive.info/2018/01/android-content-placeholder-animation-like-facebook-using-shimmer/

### Asteroid Detail

#### Composition

- Image: Is or not Hazardous (touch status bar, fitsSystemWindows)
- Like a form page, pairs of label and text in a horizontal manner
	- Close approach date
	- Absolute magnitude (H)
	- Estimated diameter (m)
	- Relative velocity (km/s)
	- Distance from Earth (km)
	- Orbit class
- Info about AU (astronomical unit)
- Dialog explaning Absolut magnitude Unit
- Dialog explaning about hazardous asteroids or not (solarsystem.nasa.gov/asteroids-comets-and-meteors/asteroids/in-depth)

#### Base UI
- https://video.udacity-data.com/topher/2020/June/5edeac35_screen-shot-2020-06-08-at-2.22.18-pm/screen-shot-2020-06-08-at-2.22.18-pm.png

### General

#### Animations

- Navigation transition animation (navigation)
- Share element transition animation
	- asteroid name
- Asteroid animation (lottie)

#### Supporting libraries

- Lottie: http://airbnb.io/lottie
- Navigation: https://developer.android.com/guide/navigation/navigation-animate-transitions
- Shared element transition: https://developer.android.com/training/transitions/start-activity

#### Additional UI elements

- Dialog: https://github.com/afollestad/material-dialogs

## Rubric checklist

Use networking and image best practices to fetch data and images
- [x] The NASA image of the day is displayed in the Main Screen

Create a database to store and access user data over time
- [x] The app filters asteroids from the past

Implement offline caching to allow users to interact with online content offline
- [x] The app downloads the next 7 days asteroids and saves them in the database once a day using workManager with requirements of internet connection and device plugged in. The app can display saved asteroids from the database even if internet connection is not available

Add talkback and push-button navigation to make an Android app accessible
- [x] The app works correctly in talk back mode, it provides descriptions for all the texts and images: Asteroid images in details screen and image of the day. It also provides description for the details screen help button

Extra

Modify the app to support multiple languages, device sizes and orientations.
- [x] English, Portuguese and Spanish

Main screen shows only the asteroids of the day
> main screen filters asteroids from database to show happing in that day, with a possible empty state
> History page shows previous asteroids, from yesterday and before
> Future asteroids isn't displayed in the app

Implement a History page with paging, filtering and grouping
> Show asteroids 

## Implementation Issues

- [x] Socket time out error when first RefreshAsteroidDataWork is called
- [x] Close approach date is presenting wrong relative date 
- [ ] Close approach date is being calculated within one day ahead
- [ ] Display units after the number, not to the left after the field description
- [ ] Display helpfull information about each field description
- [ ] Improve numeric field formatting: follow AsteroidTracker app
- [ ] Show shimmer first time loading asteroids (no cache yet)
- [ ] The application may be doing too much work on its main thread.

## Engineering Checklist

### Developer Guide

https://developer.android.com/guide

1. [Paginate queries with the Paging library](https://developer.android.com/training/data-storage/room/accessing-data#paging-integration)
2. [Reactive queries with Flow](https://developer.android.com/training/data-storage/room/accessing-data#query-flow)
    - [Declaring dependencies:](https://developer.android.com/jetpack/androidx/releases/room#declaring_dependencies)

### Keep your code spotless

https://github.com/diffplug/spotless
https://proandroiddev.com/official-kotlin-style-guide-with-ktlint-4a649c172956
https://medium.com/@int_32/android-project-code-style-using-spotless-and-ktlint-5422fd90976c

### Flipper

A desktop debugging platform for mobile developers.
https://github.com/facebook/flipper

### Infer

A static analyzer for Java, C, C++, and Objective-C.
https://github.com/facebook/infer

### Profilo

Android library for collecting performance traces from production builds of an app.
https://github.com/facebookincubator/profilo

### Battery Metrics

Android library to help instrument battery-related system metrics.
https://github.com/facebookincubator/Battery-Metrics

### Redex

A bytecode optimizer for Android apps.
https://github.com/facebook/redex

### Jetpack Hilt

https://developer.android.com/training/dependency-injection?hl=pt-br

## More ideas

- Image of the day
    - play video fullscreen when media_type is "video"
    - be able to open image in fullscreen with title and description
    - be able to read explanation
    - be able to change the data of the picture
    - maybe see more?
    - inter related images
    - apod.nasa.gov/apod site shows links within the explanation text - we could display that text as plain html or even as Spannable text
- Asteroids
    - viewed by community - users can publish their captured photos of asteroids