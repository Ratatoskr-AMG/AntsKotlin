package com.example.antskotlin


import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.antskotlin.ants.Ant
import com.example.antskotlin.ants.AntsDrawer
import com.example.antskotlin.map.Map
import com.example.antskotlin.map.MapDrawer
import java.util.*


class Surface(val myContext: Context, private val resourses: Resources?) :
    SurfaceView(myContext), SurfaceHolder.Callback {
    private var map: Map? = null
    private var mapDrawer: MapDrawer? = null
    private val antsDrawer: AntsDrawer? = null
    private val canvas: Canvas? = null
    private val ants: MutableList<Ant> = ArrayList<Ant>()
    override fun surfaceCreated(holder: SurfaceHolder) {
        //получаем карту
        ants.add(Ant(resources, 0.06, 0.06))
        ants.add(Ant(resources, 0.03, 0.06))
        ants.add(Ant(resources, 0.02, 0.04))
        ants.add(Ant(resources, 0.03, 0.04))
        ants.add(Ant(resources, 0.04, 0.04))
        ants.add(Ant(resources, 0.03, 0.03))
        ants.add(Ant(resources, 0.01, 0.03))
        map = Map.getInstance(this, resourses)
        mapDrawer = MapDrawer(holder, resourses, map, this, context, ants)
        mapDrawer!!.run()
        //antsDrawer.run();
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        //временно без обработки
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        //временно без обработки
    }

    init {
        holder.addCallback(this)
    }
}

