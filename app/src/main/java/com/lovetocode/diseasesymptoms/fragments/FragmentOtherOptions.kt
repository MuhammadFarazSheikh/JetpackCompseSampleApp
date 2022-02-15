package com.lovetocode.diseasesymptoms.fragments

import android.os.Bundle
import com.lovetocode.diseasesymptoms.fragments.FragmentOtherOptions
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.lovetocode.diseasesymptoms.R
import com.lovetocode.diseasesymptoms.composeclasses.TabHome
import com.lovetocode.diseasesymptoms.composeclasses.TabPage
import kotlinx.coroutines.launch

class FragmentOtherOptions : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                mainContent()
            }
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Preview
    @Composable
    fun mainContent()
    {
        var pagerState = rememberPagerState(initialPage = 0)
        val scope = rememberCoroutineScope()
        Scaffold(topBar = {
            TabHome(selectedTabIndex =pagerState.currentPage , onSelectedTabPage ={
                scope.launch {
                    pagerState.animateScrollToPage(it.ordinal)
                }
            } )
        }) {
            HorizontalPager(state = pagerState, count = TabPage.values().size) {index->
                Text(text = TabPage.values().get(index).name)
            }
        }
    }
}