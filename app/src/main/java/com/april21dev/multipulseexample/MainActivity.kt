package com.april21dev.multipulseexample

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        purse_count_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                count_label.text = "Purse Count: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                multi_pulse_layout.setPurseCount(seekBar?.progress ?: 1)
                multi_pulse_layout.start()
            }
        })

        duration_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                duration_label.text = "Duration: ${progress}ms"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                multi_pulse_layout.setDuration(seekBar?.progress?.toLong() ?: 1000)
                multi_pulse_layout.start()
            }
        })

        start_radius_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                start_radius_label.text = "Start Radius: ${progress}dp"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                multi_pulse_layout.setStartRadius(SizeUtil.dpToPx(this@MainActivity, seekBar?.progress ?: 0))
                multi_pulse_layout.start()
            }
        })

        purse_color_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                purse_color_label.setTextColor(getColorFromProgress(progress))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                multi_pulse_layout.setPurseColor(getColorFromProgress(seekBar?.progress ?: 0))
                multi_pulse_layout.start()
            }
        })

        stroke_width_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                stroke_width_label.text = "Stroke Width: ${progress}dp"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                multi_pulse_layout.setStrokeWidth(SizeUtil.dpToPx(this@MainActivity, seekBar?.progress ?: 0))
                multi_pulse_layout.start()
            }
        })

        paint_style_switch.setOnCheckedChangeListener { _, isChecked ->
            paint_style_label.text = "Paint Style: ${if (isChecked) "stroke" else "fill"}"
            multi_pulse_layout.setPaintStyle(if (isChecked) Paint.Style.STROKE else Paint.Style.FILL)
            multi_pulse_layout.start()
        }

        //Start Animation
        multi_pulse_layout.start()
    }

    private fun getColorFromProgress(progress: Int): Int {
        var r = 0
        var g = 0
        var b = 0

        when {
            progress < 256 -> b = progress
            progress < 256 * 2 -> {
                g = progress % 256
                b = 256 - progress % 256
            }
            progress < 256 * 3 -> {
                g = 255
                b = progress % 256
            }
            progress < 256 * 4 -> {
                r = progress % 256
                g = 256 - progress % 256
                b = 256 - progress % 256
            }
            progress < 256 * 5 -> {
                r = 255
                g = 0
                b = progress % 256
            }
            progress < 256 * 6 -> {
                r = 255
                g = progress % 256
                b = 256 - progress % 256
            }
            progress < 256 * 7 -> {
                r = 255
                g = 255
                b = progress % 256
            }
        }

        return Color.argb(255, r, g, b)
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
