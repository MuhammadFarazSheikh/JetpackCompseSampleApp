package com.lovetocode.diseasesymptoms.composeclasses

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.lovetocode.diseasesymptoms.R
import com.lovetocode.diseasesymptoms.activities.OtherWeatherOptionsActivity
import com.lovetocode.diseasesymptoms.models.BaseBO
import com.lovetocode.diseasesymptoms.others.Constants
import com.lovetocode.diseasesymptoms.utils.DateTimeUtils
import com.lovetocode.diseasesymptoms.utils.TemperatureUtils
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun searchWeather(otherWeatherOptionsActivity: OtherWeatherOptionsActivity, mutableState: MutableState<Any>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = colorResource(
                    R.color.light_slate_grey_color
                )
            )
    ) {
        val textFieldWidget = remember { mutableStateOf(TextFieldValue()) }

        TextField(
            value = textFieldWidget.value,
            onValueChange = {
                textFieldWidget.value = it
            },
            modifier = Modifier
                .background(color = Color.Transparent)
                .padding(15.dp, 15.dp, 15.dp, 0.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .border(
                    border = BorderStroke(2.dp, color = colorResource(R.color.gray_chateau)),
                    shape = RoundedCornerShape(5.dp)
                ),
            placeholder = {
                Text(
                    text = stringResource(R.string.hint_search),
                    color = Color.White,
                    fontSize = 12.sp,
                )
            },
            colors = TextFieldDefaults.textFieldColors(textColor = Color.White)
        )

        if (mutableState.value is BaseBO) {
            var baseBO = mutableState.value as BaseBO
            ConstraintLayout(
                modifier = Modifier
                    .padding(20.dp, 15.dp, 20.dp)
                    .background(color = Color.DarkGray)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .border(
                        border = BorderStroke(5.dp, colorResource(R.color.gray_chateau)),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(15.dp, 15.dp, 15.dp, 15.dp)
            ) {
                var (timeAndDate, weather, weatherCondition) = createRefs()
                Column(modifier = Modifier.constrainAs(timeAndDate)
                {
                    top.linkTo(parent.top)
                }) {
                    Text(
                        text = DateTimeUtils.getCurrentDateByFormat(Constants.DATE_FORMAT_NEW),
                        color = Color.White,
                        fontSize = 10.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        text = DateTimeUtils.getCurrentDateByFormat(Constants.TIME_FORMAT_NEW),
                        color = Color.White,
                        fontSize = 10.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }

                Column(modifier = Modifier.constrainAs(weather) {
                    start.linkTo(timeAndDate.end)
                    end.linkTo(weatherCondition.start)
                }) {
                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        text = TemperatureUtils.convertKelvinToCelcius(baseBO.main.temp.toDouble())
                            .toInt().toString() + "\u2103",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif
                    )

                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        text = "Max:" + TemperatureUtils.convertKelvinToCelcius(baseBO.main.temp_max.toDouble())
                            .toInt()
                            .toString() + "\u2103" +
                                "~Min:" + TemperatureUtils.convertKelvinToCelcius(baseBO.main.temp_min.toDouble())
                            .toInt()
                            .toString() + "\u2103",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif
                    )

                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        text = "Wind:" + TemperatureUtils.getWindDirection(
                            baseBO.wind.deg.toDouble().toInt()
                        ) +
                                "," + baseBO.wind.speed + " KM/h",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }

                Column(modifier = Modifier.constrainAs(weatherCondition) {
                    end.linkTo(parent.end)
                }) {
                    GlideImage(
                        imageModel = stringResource(R.string.base_url_image)
                                + (baseBO.weather.get(0).icon
                                + stringResource(
                            R.string.image_extension
                        )),
                        contentDescription = "",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .width(30.dp)
                            .height(30.dp)
                    )

                    Text(
                        text = baseBO.weather.get(0).description,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 12.sp
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally)
            ) {

                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth()
                        .padding(0.dp, 0.dp, 30.dp, 0.dp)
                        .border(
                            border = BorderStroke(
                                2.dp,
                                color = colorResource(R.color.gray_chateau)
                            ),
                            shape = RoundedCornerShape(5.dp)
                        )
                        .background(color = Color.DarkGray)
                        .padding(15.dp, 15.dp, 15.dp, 15.dp)
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.pressure_icon),
                        contentDescription = "",
                        Modifier
                            .width(20.dp)
                            .height(20.dp)
                            .align(alignment = Alignment.CenterHorizontally),
                        tint = Color.White
                    )

                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.pressure_text) + "\n" + baseBO.main.pressure.toString() + " hpa",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }

                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth()
                        .border(
                            border = BorderStroke(
                                2.dp,
                                color = colorResource(R.color.gray_chateau)
                            ),
                            shape = RoundedCornerShape(5.dp)
                        )
                        .background(color = Color.DarkGray)
                        .padding(15.dp, 15.dp, 15.dp, 15.dp)
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.humidity_icon),
                        contentDescription = "",
                        Modifier
                            .width(20.dp)
                            .height(20.dp)
                            .align(alignment = Alignment.CenterHorizontally),
                        tint = Color.White
                    )

                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.humidity_text) + "\n" + baseBO.main.humidity.toString() + " hpa",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }
        }

        Button(
            onClick = {
                otherWeatherOptionsActivity.getWeatherDetails(textFieldWidget.value.text)
            },
            modifier = Modifier
                .padding(20.dp, 20.dp, 20.dp, 0.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            content = {
                Text(
                    text = stringResource(R.string.txt_search),
                    fontSize = 13.sp,
                )
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(R.color.gray_chateau),
                contentColor = Color.White
            )
        )
    }
}