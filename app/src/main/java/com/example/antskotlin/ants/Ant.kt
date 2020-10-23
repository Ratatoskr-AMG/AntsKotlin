package com.example.antskotlin.ants


import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import com.example.antskotlin.R
import java.util.*


class Ant(private val resources: Resources, var speedX: Double, var speedY: Double) {

    val random = Random()
    val myMatrix = Matrix()
    var myCurrPicture: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.ant1)
    var myX = 0.0
    var myY = 0.0
    private var mySpeedY = speedY
    private var mySpeedX = speedX
    var isMyLocked = false
    var myCurrDegrees: Double
    var movStep = 1

    init {
        myCurrPicture = BitmapFactory.decodeResource(resources, R.drawable.ant1)
        val radians = Math.atan2(speedY, speedX)
        myCurrDegrees = radians * 180 / Math.PI
        myMatrix.preRotate(
            myCurrDegrees.toString().toFloat(),
            myCurrPicture.width / 2.toFloat(),
            myCurrPicture.height / 2.toFloat()
        )
        //matrix.postScale(0.2f, 0.2f);
    }

    fun isLocked(): Boolean {
        return isMyLocked
    }

    fun getCurrDegrees(): Double {
        return myCurrDegrees
    }

    fun setCurrDegrees(currDegrees: Double) {
        this.myCurrDegrees = currDegrees
    }

    fun setLocked(locked: Boolean) {
        isMyLocked = locked
    }

    fun getX(): Double {
        return myX
    }

    fun setX(x: Double) {
        this.myX = x
    }

    fun getY(): Double {
        return myY
    }

    fun setY(y: Double) {
        this.myY = y
    }

    fun setMySpeedY(speedY: Double) {
        this.mySpeedY = speedY
    }

    fun setMySpeedX(speedX: Double) {
        this.mySpeedX = speedX
    }

    fun getMySpeedX(): Double {
        return mySpeedX
    }

    fun getMySpeedY(): Double {
        return mySpeedY
    }


    fun getMatrix(): Matrix {
        return myMatrix
    }


    fun getCurrPicture(): Bitmap {
        return myCurrPicture
    }

    fun setCurrPicture(picture: Bitmap) {
        myCurrPicture = picture
    }

    fun getMovStep(): Int? {
        return movStep
    }

    fun setMovStep(movStep: Int?) {
        this.movStep = movStep!!
    }


}
