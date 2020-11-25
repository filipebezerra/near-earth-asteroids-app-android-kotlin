# UI Construct

## List

### Characteristics:

- ordered by approach date
- contains a header: approach date
- fast scrolling: by alphabet (asteroid name)
- filter: by asteroid name and orbit class description
- shimmer effect: fetching data
- infinite scrolling: loading more data from internet or local storage

### Card Composition (https://material.io/components/cards)

- Thumbnail: icon/image -> distance/size
- Header text: asteroid name
- Supporting text: orbit class description
- Icons for actions
	- More info (use ChromeCustomTabs for displaying page from nasa_jpl_url)
	- Share
	- Favorite (later)

### Base UI

- https://video.udacity-data.com/topher/2020/June/5edeac1d_screen-shot-2020-06-08-at-2.21.53-pm/screen-shot-2020-06-08-at-2.21.53-pm.png

### Supporting library

- https://github.com/mikepenz/FastAdapter
- https://github.com/afollestad/recyclical (maybe in conjunction with FastAdapter)
- https://www.androidhive.info/2018/01/android-content-placeholder-animation-like-facebook-using-shimmer/

## Detail

### Composition

- Image: Is or not Hazardous (touch status bar, fitsSystemWindows)
- Like a form page, pairs of label and text in a horizontal manner
	- Close approach date
	- Absolute magnitude (H)
	- Estimated diameter (m)
	- Relative velocity (km/s)
	- Distance from Earth (km)
	- Orbit class
- Info about AU (astronomical unit)

### Base UI
- https://video.udacity-data.com/topher/2020/June/5edeac35_screen-shot-2020-06-08-at-2.22.18-pm/screen-shot-2020-06-08-at-2.22.18-pm.png

## General

### Animations

- Navigation transition animation (navigation)
- Share element transition animation
	- asteroid name
- Asteroid animation (lottie)

### Supporting libraries

- Lottie: http://airbnb.io/lottie
- Navigation: https://developer.android.com/guide/navigation/navigation-animate-transitions
- Shared element transition: https://developer.android.com/training/transitions/start-activity

### Additional UI elements

- Dialog: https://github.com/afollestad/material-dialogs