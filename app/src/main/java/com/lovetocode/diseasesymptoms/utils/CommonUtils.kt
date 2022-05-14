package com.lovetocode.diseasesymptoms.utils

import android.content.Context
import com.lovetocode.diseasesymptoms.R
import com.lovetocode.diseasesymptoms.models.BaseBO

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

        public fun getFormattedFiveDaysData(arrayList:ArrayList<BaseBO>):ArrayList<BaseBO>
        {
            var tempArray = ArrayList<BaseBO>()

            var tempDate = ""

            tempDate = arrayList.get(0).dt_txt.split(" ")[0]

            for (i in arrayList) {
                if (!tempDate.equals(i.dt_txt.split(" ")[0])) {
                    var arrayTemp = ArrayList<BaseBO>()

                    for (j in arrayList) {
                        if (j != null && j.dt_txt.split(" ")[0].equals(i.dt_txt.split(" ")[0])) {
                            arrayTemp.add(j)
                        }
                    }
                    i.arrayList = ArrayList()
                    i.arrayList.addAll(arrayTemp)
                    tempArray.add(i)
                    tempDate = i.dt_txt.split(" ")[0]
                }
            }
            return tempArray;
        }
    }
}