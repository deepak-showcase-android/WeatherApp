# WeatherApp

## How to build and run the app
1. Clone the code
   (git clone git@github.com:deepak-showcase-android/WeatherApp.git)
2. Build and run either on emulator or on real device
3. Give Location access permission to the app manually  

## How to see weather info
1. Connect device to the internet
2. Start WeatherApp, tap on the Weather tab

## Technical debt
1. Create mock flavor, add mock classes (MockOpenWeatherRemoteDataSource.kt)
2. Add mock data in the mock file to simulate weather data
3. Write Unit and UI tests
4. For any hardcoded values, consider creating CONST or move to resource file 

## Features (if time permits)
1. Display google map, User to see weather info of any tapped place. 
2. Group weather data based on date. Show the groupwise weather data in RecyclerView with expand/collpase feature to see additional weather info available.
3. Request Location access permission

