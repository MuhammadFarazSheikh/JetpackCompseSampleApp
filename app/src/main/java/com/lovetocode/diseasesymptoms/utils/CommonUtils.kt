package com.lovetocode.diseasesymptoms.utils

import android.content.Context
import com.lovetocode.diseasesymptoms.R

class CommonUtils
{
    companion object
    {
        public fun getMostCommonSymptomsList(context: Context):List<String>
        {
            return context.resources.getStringArray(R.array.most_common_symtoms).toList()
        }

        public fun getLessCommonSymptomsList(context: Context):List<String>
        {
            return context.resources.getStringArray(R.array.less_common_symtoms).toList()
        }

        public fun getSeriousSymptomsList(context: Context):List<String>
        {
            return context.resources.getStringArray(R.array.serious_symptoms).toList()
        }

        public fun getCovidPreventionsList(context: Context):List<String>
        {
            return context.resources.getStringArray(R.array.covid_preventions).toList()
        }

        public fun getCountoriesList(context: Context):List<String>
        {
            return context.resources.getStringArray(R.array.countories_list).toList()
        }
    }
}