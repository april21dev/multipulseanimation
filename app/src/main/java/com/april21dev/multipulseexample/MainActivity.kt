package com.april21dev.multipulseexample

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        //Set Attributes
        multi_pulse_layout
            .setDuration(2000)
            .setPaintStyle(paintStyle = Paint.Style.STROKE)
            .setPurseCount(5)
            .setStartRadius(100f)
            .setStrokeWidth(10f)
            .setPurseColor(Color.rgb(255, 0, 0))

        //Start Animation
        multi_pulse_layout.start()

        //Stop Animation (Doesn't remove purse drawables from view)
        multi_pulse_layout.stop()

        //Clear Animation (Remove purse drawables from view)
        multi_pulse_layout.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
