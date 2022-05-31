package com.lovetocode.diseasesymptoms.composeclasses

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun userLogin()
{
    var textField by remember { mutableStateOf("Text") }
    BasicTextField(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        value =textField ,
        onValueChange ={
            textField = it
        } )
}