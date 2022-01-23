package com.lovetocode.diseasesymptoms.bindingadapters

import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.lovetocode.diseasesymptoms.R
import com.lovetocode.diseasesymptoms.others.Constants
import com.lovetocode.diseasesymptoms.utils.DateTimeUtils

@BindingAdapter("android:setLastChange")
fun setDateAndTimeLastChange(appCompatTextView: AppCompatTextView,dateText:String?)
{
    dateText?.let {
        appCompatTextView.text= appCompatTextView.context.resources.getString(R.string.text_last_change_date)+" "+
                DateTimeUtils.convertDateByFormat(Constants.DATE_FORMATE_OLD,Constants.DATE_FORMAT_NEW,it)+"\n"+
                appCompatTextView.context.resources.getString(R.string.text_last_change_time)+" "+
                DateTimeUtils.convertDateByFormat(Constants.DATE_FORMATE_OLD,Constants.TIME_FORMAT_NEW,it)
    }
}

@BindingAdapter("android:setLastUpdated")
fun setDateAndTimeLastUpdated(appCompatTextView: AppCompatTextView,dateText:String?)
{
    dateText?.let {
        appCompatTextView.text= appCompatTextView.context.resources.getString(R.string.text_last_update_date)+" "+
                DateTimeUtils.convertDateByFormat(Constants.DATE_FORMATE_OLD,Constants.DATE_FORMAT_NEW,it)+"\n"+
                appCompatTextView.context.resources.getString(R.string.text_last_update_time)+" "+
                DateTimeUtils.convertDateByFormat(Constants.DATE_FORMATE_OLD,Constants.TIME_FORMAT_NEW,it)
    }
}