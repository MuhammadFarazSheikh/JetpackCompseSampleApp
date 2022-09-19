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
import androidx.compose.ui.graphics.vector.ImageVector

enum class TabPage()
{
    FiveDays,
    Search,
    Maps
}

@Composable
fun TabHome(selectedTabIndex:Int,onSelectedTabPage: (TabPage)->Unit)
{
    TabRow(selectedTabIndex = selectedTabIndex) {
        TabPage.values().forEachIndexed { index, tabPage ->
            Tab(
                selected = index == selectedTabIndex
                , onClick = { onSelectedTabPage(tabPage) }
                , text ={ Text(text = tabPage.name) }
            )
        }
    }
}