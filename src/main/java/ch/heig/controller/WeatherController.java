package ch.heig.controller;

import ch.heig.mediator.weather.*;
import ch.heig.utils.WeightedCollection;

import java.util.ArrayList;

/**
 * The type Weather controller.
 */
public class WeatherController {

    private ArrayList<AbstractWeatherMediator> weatherList = new ArrayList<>();
    private double showingTime;
    private double lastWeatherEnding;

    /**
     * Instantiates a new Weather controller.
     *
     * @param awm         the awm
     * @param showingTime the showing time
     */
    public WeatherController(AbstractWeatherMediator awm, double showingTime) {
        add(awm);
        this.lastWeatherEnding = awm.getDuration();
        this.showingTime = showingTime;
    }

    /**
     * Check weather.
     */
    public void checkWeather() {
        AbstractWeatherMediator firstWeather = weatherList.get(0);
        AbstractWeatherMediator lastWeather = weatherList.get(weatherList.size() - 1);

        if (lastWeatherEnding <= showingTime) {
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
            weatherMediator.initIncomingWeatherIcon(weatherList.size());
            weatherList.add(weatherMediator);
            lastWeatherEnding += weatherMediator.getDuration();
        }

        if (firstWeather.getDuration() <= 0) {
            weatherList.remove(firstWeather);
            weatherList.get(0).removeIncomingWeatherIcon();
            weatherList.get(0).updateMediator();

            for (int i = 1; i <= weatherList.size() - 1; i++) {
                weatherList.get(i).updateIncomingWeatherIcon(i);
            }
        }

        firstWeather.setDuration(firstWeather.getDuration() - 0.1);
        lastWeatherEnding -= 0.1;
    }

    /**
     * Add.
     *
     * @param awm the awm
     */
    public void add(AbstractWeatherMediator awm) {
        weatherList.add(awm);
        lastWeatherEnding += awm.getDuration();
    }
}
