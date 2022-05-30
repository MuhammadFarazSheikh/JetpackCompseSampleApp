package com.lovetocode.diseasesymptoms.composeclasses

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun userLogin()
{
    val textField by remember { mutableStateOf("Text") }
    BasicTextField(
        value =textField ,
        onValueChange = )
}