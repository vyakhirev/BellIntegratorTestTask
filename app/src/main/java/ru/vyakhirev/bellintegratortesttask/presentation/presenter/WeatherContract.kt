package ru.vyakhirev.bellintegratortesttask.presentation.presenter

//interface WeatherContract {
//
//    interface BaseContract {
//        fun showProgress()
//        fun hideProgress()
//        fun showError()
//    }
//
//    interface HomeContract : BaseContract {
//        var days: ArrayList<DailyForecast>
//        var weather: CurrentWeatherModelEntity?
//
//        fun addWeatherToDatabase()
//        fun updateWeather(modelCurrent: CurrentWeatherModel)
//
//        fun addDay(modelForecast: ForecastWeatherModel)
//        fun addForecastToDatabase()
//
//        fun fetchWeather()
//        fun fetchForecast()
//    }
//
//    interface HourlyForecastContract: BaseContract {
//        var hours: ArrayList<HourlyForecast>
//
//        fun addHour(forecastWeatherModel: ForecastWeatherModel)
//        fun setTimezone(timezone: Long)
//        fun addHoursToDatabase()
//
//        fun fetchHoursFromDatabase()
//    }
//}