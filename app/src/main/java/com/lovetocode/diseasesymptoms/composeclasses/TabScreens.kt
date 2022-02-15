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

enum class TabPage(val icon:ImageVector)
{
    Home(Icons.Default.Home),
    Favourite(Icons.Default.Favorite),
    Messages(Icons.Default.Message)
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
                , icon = { Icon(imageVector = tabPage.icon, contentDescription = "") }
            )
        }
    }
}