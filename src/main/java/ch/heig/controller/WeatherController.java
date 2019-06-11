package ch.heig.controller;

import ch.heig.mediator.weather.*;
import ch.heig.utils.WeightedCollection;

import java.util.ArrayList;

public class WeatherController {

    private ArrayList<AbstractWeatherMediator> weatherList = new ArrayList<>();
    private int lastWeatherEnding;

    public WeatherController(AbstractWeatherMediator awm) {
        add(awm);
        lastWeatherEnding = awm.getDuration();
    }

    public void checkWeather() {
        AbstractWeatherMediator firstWeather = weatherList.get(0);
        AbstractWeatherMediator lastWeather = weatherList.get(weatherList.size() - 1);

        if (lastWeatherEnding <= 15) {
            // Populate weather collection with different weights
            WeightedCollection<AbstractWeatherMediator> weatherCollection = new WeightedCollection<>();
            weatherCollection.add(5, new NormalWeatherMediator(lastWeather));
            weatherCollection.add(5, new FogWeatherMediator(lastWeather));
            weatherCollection.add(5, new RainWeatherMediator(lastWeather));
            weatherCollection.add(4, new CloudyWeatherMediator(lastWeather));
            weatherCollection.add(4, new BrightWeatherMediator(lastWeather));
            weatherCollection.add(3, new SnowWeatherMediator(lastWeather));
            weatherCollection.add(3, new LightningWeatherMediator(lastWeather));
            weatherCollection.add(3, new RainbowWeatherMediator(lastWeather));
            weatherCollection.add(2, new HeavyRainWeatherMediator(lastWeather));
            weatherCollection.add(2, new BigFogWeatherMediator(lastWeather));
            weatherCollection.add(1, new HurricaneWeatherMediator(lastWeather));

            AbstractWeatherMediator weatherMediator = weatherCollection.next();

            weatherList.add(weatherMediator);
            lastWeatherEnding += weatherMediator.getDuration();
        }

        if (firstWeather.getDuration() <= 0) {
            weatherList.remove(0);
            weatherList.get(0).updateMediator();
        }

        firstWeather.setDuration(firstWeather.getDuration() - 1);

        lastWeatherEnding--;
    }

    public void add(AbstractWeatherMediator awm) {
        weatherList.add(awm);
        lastWeatherEnding += awm.getDuration();
    }
}
