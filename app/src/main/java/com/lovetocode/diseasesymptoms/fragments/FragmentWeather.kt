package com.lovetocode.diseasesymptoms.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lovetocode.diseasesymptoms.R
import com.lovetocode.diseasesymptoms.models.BaseBO
import com.lovetocode.diseasesymptoms.others.Constants
import com.lovetocode.diseasesymptoms.utils.DateTimeUtils
import com.lovetocode.diseasesymptoms.utils.TemperatureUtils
import com.lovetocode.diseasesymptoms.viewmodels.CommonViewModel
import com.montymobile.callsignature.utils.KeyUtils
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@AndroidEntryPoint
class FragmentWeather : Fragment() {

    private lateinit var baseBO: BaseBO
    val viewModel: CommonViewModel by viewModels()
    private lateinit var composeView: ComposeView
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        composeView = ComposeView(requireContext())
        return composeView.apply {
            setContent {
                showLoadingMessage()
            }
        }
    }

    @Composable
    fun topBar() {
        TopAppBar(
            backgroundColor = colorResource(R.color.light_slate_grey_color),
            contentPadding = PaddingValues(10.dp, 0.dp, 0.dp, 0.dp)
        ) {
            Text(
                text = stringResource(R.string.text_country_pakistan),
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }

    @Composable
    fun showLoadingMessage() {
        Text(
            text = stringResource(R.string.text_loding_message),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    color = colorResource(R.color.light_slate_grey_color)
                ),
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )
    }

    @Composable
    fun innerContent() {
        ConstraintLayout(
            modifier = Modifier
                .background(color = colorResource(R.color.light_slate_grey_color))
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            var (topLayout, bottomOne, bottomTwo, button) = createRefs()
            ConstraintLayout(
                modifier = Modifier
                    .constrainAs(topLayout)
                    {
                        top.linkTo(parent.top)
                    }
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
                        text = TemperatureUtils.convertKelvinToCelcius(baseBO.main.temp.toDouble()).toInt().toString()+"\u2103",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif
                    )

                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        text = "Max:" + TemperatureUtils.convertKelvinToCelcius(baseBO.main.temp_max.toDouble()).toInt()
                            .toString() + "\u2103" +
                                "~Min:" + TemperatureUtils.convertKelvinToCelcius(baseBO.main.temp_min.toDouble()).toInt()
                            .toString() + "\u2103",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif
                    )

                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        text = "Wind:" + TemperatureUtils.getWindDirection(baseBO.wind.deg.toDouble().toInt()) +
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

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .border(
                        border = BorderStroke(2.dp, color = colorResource(R.color.gray_chateau)),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .background(color = Color.DarkGray)
                    .padding(15.dp, 15.dp, 15.dp, 15.dp)
                    .constrainAs(bottomOne) {
                        top.linkTo(topLayout.bottom, 40.dp)
                        start.linkTo(parent.start)
                        end.linkTo(bottomTwo.start)
                    }) {

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
                    text = stringResource(R.string.pressure_text)+"\n"+baseBO.main.pressure.toString() + " hpa",
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
                        border = BorderStroke(2.dp, color = colorResource(R.color.gray_chateau)),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .background(color = Color.DarkGray)
                    .padding(15.dp, 15.dp, 15.dp, 15.dp)
                    .constrainAs(bottomTwo) {
                        top.linkTo(topLayout.bottom, 40.dp)
                        end.linkTo(parent.end)
                        start.linkTo(bottomOne.end)
                    }) {

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
                    text = stringResource(R.string.humidity_text)+"\n"+baseBO.main.humidity.toString() + " hpa",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }

            TextButton(onClick = {
                findNavController().navigate(R.id.weatherActionTab)
            }, modifier = Modifier
                .constrainAs(button)
                {
                    top.linkTo(bottomOne.bottom, 40.dp)
                }
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(20.dp, 0.dp, 20.dp, 0.dp)
                .background(color = Color.DarkGray)
                .border(
                    2.dp,
                    color = colorResource(R.color.gray_chateau),
                    shape = RoundedCornerShape(5.dp)
                )) {
                Text(
                    text = "Explore More.",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White
                )
            }
        }
    }

    @Preview
    @Composable
    fun mainContent() {
        Scaffold(topBar = {
            topBar()
        }) {
            innerContent()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstances()
        compositeDisposable.add(
            viewModel.getData("Pakistan")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { onSuccess, onError ->
                    if (onSuccess != null) {
                        baseBO = onSuccess
                        composeView.apply {
                            setContent {
                                mainContent()
                            }
                        }
                    }
                    if (onError != null) {

                    }
                }
        )
    }

    private fun initInstances() {
        compositeDisposable = CompositeDisposable()
    }

    override fun onDestroy() {

        if (::compositeDisposable.isInitialized) {
            compositeDisposable.dispose()
        }

        super.onDestroy()
    }
}