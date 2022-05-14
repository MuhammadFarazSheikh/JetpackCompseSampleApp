package com.lovetocode.diseasesymptoms.composeclasses

import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.lovetocode.diseasesymptoms.R

enum class TabPage()
{
    FiveDays,
    SearchWeather,
    YourLocation
}

@Composable
fun TabHome(selectedTabIndex:Int,onSelectedTabPage: (TabPage)->Unit)
{
    TabRow(selectedTabIndex = selectedTabIndex,
        backgroundColor = colorResource(R.color.light_slate_grey_color)
        , contentColor = Color.White) {
        TabPage.values().forEachIndexed { index, tabPage ->
            Tab(
                selected = index == selectedTabIndex
                , onClick = { onSelectedTabPage(tabPage) }
                , text ={ Text(text = tabPage.name) }
            )
        }
    }
}