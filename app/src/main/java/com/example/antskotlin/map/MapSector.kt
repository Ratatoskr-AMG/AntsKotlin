package com.example.antskotlin.map

import android.content.res.Resources
import android.util.Log


class MapSector(var resources: Resources?, var column: Int, var row: Int, var sectorWidth: Int) {
    private var myX: Float
    private var myY: Float

    init {
        myX = sectorWidth / 2 + sectorWidth * (column - 1).toFloat()
        myY = sectorWidth / 2 + sectorWidth * (row - 1).toFloat()
        Log.v("TOHA", "Sector :: column:$column; row:$row; dx:$myX; dy:$myY")
    }


    fun getX(): Float {
        return myX
    }

    fun setX(x: Float) {
        this.myX = x
    }

    fun getY(): Float {
        return myY
    }

    fun setY(y: Float) {
        this.myY = y
    }

    fun getMyResources(): Resources? {
        return resources
    }

    fun setMyResources(resources: Resources?) {
        this.resources = resources!!
    }
}
