# Near Earth Asteroids

Near Earth Asteroids is currently under heavy development. Note that some changes (such as database schema modifications) are not backwards compatible and may cause the app to crash. In this case, please uninstall and re-install the app.

## Getting Started

### Nasa API key

Near Earth Asteroids uses the [Nasa APIs](https://api.nasa.gov/) to load near earth Asteroid information and astronomy picture of the day. To use the API, you will need to obtain a free developer API key. See the [Nasa API portal](https://api.nasa.gov/#signUp) for instructions.

Once you have the key, add this line to the gradle.properties file, either in your user home directory (usually ~/.gradle/gradle.properties on Linux and Mac) or in the project's root folder:

```
nasa_api_key=<your NASA access key>
```

