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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.ComposeView
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
import com.lovetocode.diseasesymptoms.viewmodels.CommonViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@AndroidEntryPoint
class FragmentWeather : Fragment() {

    val viewModel: CommonViewModel by viewModels()
    private lateinit var composeView: ComposeView
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        composeView = ComposeView(requireContext())
        return composeView
    }

    @Composable
    fun topBar() {
        TopAppBar(
            backgroundColor = Color(R.color.cloud_burst),
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
    fun innerContent() {
        ConstraintLayout(
            modifier = Modifier
                .background(color = Color(R.color.cloud_burst))
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
                        border = BorderStroke(2.dp, Color.Red),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(15.dp, 10.dp, 15.dp, 10.dp)
            ) {
                var (timeAndDate, weather, weatherCondition) = createRefs()
                Column(modifier = Modifier.constrainAs(timeAndDate)
                {
                    top.linkTo(parent.top)
                }) {
                    Text(
                        text = "28 Sep 2019",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        text = "11:47 AM",
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
                        text = "13℃",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif
                    )

                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        text = "Max 15℃~Min 12℃",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif
                    )

                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        text = "Wind N,1.5KM/h",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }

                Column(modifier = Modifier.constrainAs(weatherCondition) {
                    end.linkTo(parent.end)
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.autoimmune_disease),
                        contentDescription = "",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .width(15.dp)
                            .height(15.dp)
                    )

                    Text(
                        text = "Broken Clouds",
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
                        border = BorderStroke(2.dp, color = Color.Red),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .background(color = Color.DarkGray)
                    .padding(10.dp, 10.dp, 10.dp, 10.dp)
                    .constrainAs(bottomOne) {
                        top.linkTo(topLayout.bottom, 15.dp)
                        start.linkTo(parent.start)
                        end.linkTo(bottomTwo.start)
                    }) {

                Image(
                    painter = painterResource(id = R.drawable.autoimmune_disease),
                    contentDescription = "",
                    Modifier
                        .width(20.dp)
                        .height(20.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                )

                Text(
                    textAlign = TextAlign.Center,
                    text = "Pressure\n1010.0hpa",
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
                        border = BorderStroke(2.dp, color = Color.Red),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .background(color = Color.DarkGray)
                    .padding(10.dp, 10.dp, 10.dp, 10.dp)
                    .constrainAs(bottomTwo) {
                        top.linkTo(topLayout.bottom, 15.dp)
                        end.linkTo(parent.end)
                        start.linkTo(bottomOne.end)
                    }) {

                Image(
                    painter = painterResource(id = R.drawable.autoimmune_disease),
                    contentDescription = "",
                    Modifier
                        .width(20.dp)
                        .height(20.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                )

                Text(
                    textAlign = TextAlign.Center,
                    text = "Pressure\n1010.0hpa",
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
                    top.linkTo(bottomOne.bottom, 10.dp)
                }
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(20.dp, 0.dp, 20.dp, 0.dp)
                .background(color = Color.DarkGray)
                .border(2.dp, color = Color.Red, shape = RoundedCornerShape(5.dp))) {
                Text(
                    text = "See 5 days weather",
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