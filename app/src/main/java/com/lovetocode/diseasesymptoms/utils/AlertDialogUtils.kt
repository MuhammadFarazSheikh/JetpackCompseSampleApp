package com.lovetocode.diseasesymptoms.utils

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lovetocode.diseasesymptoms.R

@Composable
fun openAlertDialogue()
{
    AlertDialog(onDismissRequest = {

    }, confirmButton = {
    }, dismissButton = {

    }, title = {
        Text(text = stringResource( R.string.text_success))
    }, text = {
        Text(text = stringResource( R.string.note_added))
    })
}