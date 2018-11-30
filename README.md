# Android Multiple Pulse Animation


<br/><br/>
[![https://img.youtube.com/vi/NpsAhUKhGQE/hqdefault.jpg](http://img.youtube.com/vi/NpsAhUKhGQE/0.jpg)](http://www.youtube.com/watch?v=NpsAhUKhGQE "Multipulse Animation")
<br/><br/>


## Gradle
```gradle
dependencies {
    implementation 'com.april21dev.multipulseanimation:MultiPulseAnimation:1.0.1'
}
```


## How To Use
### XML
```xml
<com.april21dev.multipulseanimation.MultiPulseLayout
            android:id="@+id/multi_pulse_layout"
            android:layout_width="200dp"
            android:layout_height="200dp"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            android:layout_marginTop="20dp"
            app:mpa_duration="1000"
            app:mpa_paint_style="fill"
            app:mpa_purse_color="#000000"
            app:mpa_purse_count="3"
            app:mpa_start_radius="10dp"
            app:mpa_stroke_width="5dp"/>
```

### Kotlin
You can set attributes programmatically also.

Just start, stop, or clear
```java
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
```



## License 
 ```code
Copyright 2018 april21dev

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
