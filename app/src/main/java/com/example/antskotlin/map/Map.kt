package com.example.antskotlin.map

import android.content.res.Resources
import android.util.Log
import com.example.antskotlin.Surface
import java.util.*


class Map(surface: Surface) {
    var mySectors: MutableList<MapSector> = ArrayList()
    var columnsNum = 20
    var rowsNum: Int
    var mySectorWidth: Int
    var displayWidth: Int? = null
    var displayHeight: Int? = null
    var resourses: Resources? = null

    fun getSectorWidth(): Double {
        return mySectorWidth.toDouble()
    }

    fun setSectorWidth(sectorWidth: Int) {
        this.mySectorWidth = sectorWidth
    }

    fun getSectors(): List<MapSector> {
        return mySectors
    }

    fun setSectors(sectors: MutableList<MapSector>) {
        this.mySectors = sectors
    }

    companion object {
        private var instance: Map? = null
        fun getInstance(surface: Surface, resourses: Resources?): Map? {
            if (instance == null) {
                instance = Map(surface)
                instance!!.resourses = resourses
            }
            return instance
        }
    }

    init {
        mySectorWidth = surface.getWidth() / columnsNum
        rowsNum = surface.getHeight() / mySectorWidth
        Log.v("TOHA", "Задаём 'плотность' через количество столбцов = $columnsNum")
        Log.v("TOHA", "Узнаём, что ширина поверхности (surface.getWidth()) = " + surface.getWidth())
        Log.v(
            "TOHA",
            "Узнаём, что высота поверхности (surface.getHeight()) = " + surface.getHeight()
        )
        Log.v(
            "TOHA",
            "Тогда получается, что ширина сектора должна быть = $mySectorWidth"
        )
        Log.v("TOHA", "А количество рядов = $rowsNum")
        for (i in 1..rowsNum) {
            for (j in 1..columnsNum) {
                mySectors.add(MapSector(resourses, j, i, mySectorWidth))
            }
        }
        Log.v("TOHA", "А общее количество секторов на карте = " + mySectors.size)
    }
}
