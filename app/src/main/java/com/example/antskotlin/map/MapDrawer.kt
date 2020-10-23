package com.example.antskotlin.map


import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.Log
import android.view.SurfaceHolder
import com.larvalabs.svgandroid.SVG
import com.larvalabs.svgandroid.SVGParser
import com.example.antskotlin.R
import com.example.antskotlin.Surface
import com.example.antskotlin.ants.Ant
import com.example.antskotlin.ants.AntsDrawer


class MapDrawer(
    private val surface: SurfaceHolder,
    private val resources: Resources?,
    private val map: Map?,
    view: Surface,
    private val context: Context,
    ants: List<Ant>
) :
    Thread(), Runnable {
    private var canvas: Canvas? = null
    private val view: Surface
    private val antsDrawer: AntsDrawer
    private val ants: List<Ant>
    private var hTime: Long = 0


    override fun run() {

        //antsDrawer.run();
        var elapsedSec: Long
        val now = System.currentTimeMillis()
        val elapsedTime = now - hTime
        if (elapsedTime > 30) {
            antsDrawer.run(elapsedTime,view)
            hTime = now
        }
        try {
            canvas = surface.lockCanvas(null)
            if (canvas != null) {
                synchronized(surface) {
                    canvas!!.drawColor(Color.WHITE)
                    val paint = Paint()
                    for (mapSector in map!!.getSectors()) {
                        canvas!!.drawCircle(mapSector.getX(), mapSector.getY(), 1f, paint)
                    }
                    for (ant in ants) {
                        val currPicture: Bitmap = ant.getCurrPicture()
                        val matrix: Matrix = ant.getMatrix()
                        canvas!!.drawBitmap(currPicture, matrix, null)
                    }
                }
            }
        } catch (e: Exception) {
            Log.v("TOHA", "e=$e")
        } finally {
            if (canvas != null) {
                surface.unlockCanvasAndPost(canvas)
            }
        }
        view.postDelayed(MapDrawer(surface, resources, map, view, context, ants), 1000 / 60)
    }

    init {
        this.ants = ants
        this.view = view
        antsDrawer = AntsDrawer(surface, ants, resources)
    }
}
