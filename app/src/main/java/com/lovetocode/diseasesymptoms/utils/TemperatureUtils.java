package com.lovetocode.diseasesymptoms.utils;

public class TemperatureUtils {

    /*CONVERT FROM CELCIUS TO FAHRENHEIT*/
    public static double celciusToFahrenheit(double celcius)
    {
        return (celcius*9/5+32);
    }

    /*CONVERT FROM FAHRENHEIT TO CELCIUS*/
    public static double fahrenheitToCelcius(double fahrenheit)
    {
        return (((fahrenheit-32)*5)/9);
    }

    public static double convertKelvinToCelcius(double kelvin)
    {
        return kelvin-273.15;
    }

    public static String getWindDirection(int degrees)
    {
        String windDirection = "";
        if(degrees>=0 && degrees<90)
        {
            windDirection = "N";
        }
        else if(degrees>=90 && degrees<180)
        {
            windDirection = "E";
        }
        else if(degrees>=180 && degrees<270)
        {
            windDirection = "S";
        }
        else
        {
            windDirection = "W";
        }

        return windDirection;
    }
}
