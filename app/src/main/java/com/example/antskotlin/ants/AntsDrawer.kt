package com.example.antskotlin.ants

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Log
import android.view.SurfaceHolder
import android.view.View
import com.example.antskotlin.R
import com.example.antskotlin.map.MapSector


class AntsDrawer(
    private val surface: SurfaceHolder,
    private val ants: List<Ant>,
    private val resources: Resources?
) {
    private val canvas: Canvas? = null
    private val hTime: Long = 0
    fun bounce(ant: Ant, d: String?) {
        val degrees: Double
        val radians: Double
        when (d) {
            "x" -> ant.setMySpeedX(-ant.getMySpeedX())
            else -> ant.setMySpeedY(-ant.getMySpeedY())
        }
        radians = Math.atan2(ant.getMySpeedY(), ant.getMySpeedX())
        degrees = radians * 180 / Math.PI
        ant.getMatrix().preRotate(
            -ant.getCurrDegrees().toFloat(),
                (ant.getCurrPicture().getWidth() / 2).toFloat(),
                (ant.getCurrPicture().getHeight() / 2).toFloat()
        )
        ant.getMatrix().preRotate(
            degrees.toLong().toFloat(),
                (ant.getCurrPicture().getWidth() / 2).toFloat(),
                (ant.getCurrPicture().getHeight() / 2).toFloat()
        )
        ant.setCurrDegrees(degrees)
    }

    fun run(elapsedTime: Long, view : View) {

        // 1. рисуем
        val elapsedSec: Long = 100
        for (ant in ants) {
            if (!ant.isLocked()) {
                ant.setLocked(true)

                //обработаем столкновение с правой границей
                if (ant.getX() + ant.getCurrPicture().getWidth() >= view.getWidth()) {
                    bounce(ant, "x")
                }
                //обработаем столкновение с левой границей
                if (ant.getX() < 0) {
                    bounce(ant, "x")
                }
                //обработаем столкновение с низом
                if (ant.getY() + ant.getCurrPicture().getHeight() >= view.getHeight()) {
                    bounce(ant, "y")

                }
                //обработаем столкновение с верхом
                if (ant.getY() < 0) {
                    bounce(ant, "y")
                }

                //рассчитаем
                val dX: Double = ant.getMySpeedX() * elapsedSec
                val dY: Double = ant.getMySpeedY() * elapsedSec
                //переместим
                ant.getMatrix().postTranslate(dX.toFloat(), dY.toFloat())
                ///Log.v("TOHA","ant.getMySpeedY()"+ant.getMySpeedY());
                //Log.v("TOHA","ant.getMySpeedY()"+ant.getMySpeedY());
                //запомним
                ant.setX(ant.getX() + dX)
                ant.setY(ant.getY() + dY)
                when (ant.getMovStep()) {
                    1 -> {
                        ant.setCurrPicture(BitmapFactory.decodeResource(resources, R.drawable.ant1))
                        ant.setMovStep(2)
                    }
                    2 -> {
                        ant.setCurrPicture(BitmapFactory.decodeResource(resources, R.drawable.ant2))
                        ant.setMovStep(3)
                    }
                    3 -> {
                        ant.setCurrPicture(BitmapFactory.decodeResource(resources, R.drawable.ant3))
                        ant.setMovStep(1)
                    }
                }
                ant.setLocked(false)
            }
        }
    }
}
