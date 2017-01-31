# PanningView - The official libray from Andén

PanningView provides a view that can animate background drawable. It's provide a horizontal and vertical panning (You can create your custom animation).

![Splash](https://github.com/nacho91/PanningView/blob/master/splash.gif)

### Setup

Gradle dependency

```gradle
compile 'com.nacho91:panningview:0.0.1'	
```

or

Maven dependency

```xml
<dependency>
  <groupId>com.nacho91</groupId>
  <artifactId>panningview</artifactId>
  <version>0.0.1</version>
</dependency>
```

### Usage

Declare the PanningView in your XML

```xml
 <com.nacho91.panningview.PanningView
    android:id="@+id/panning"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:duration="YOUR_DURATION_IN_MS"
    app:drawable="YOUR_DRAWABLE" />
```

Set one of the available panning implementations

```java
HorizontalPanning panning = new HorizontalPanning(HorizontalPanning.RIGHT_TO_LEFT);

PanningView panningView = (PanningView) findViewById(R.id.panning);
panningView.setPanning(panning);
```

## License

    Copyright 2016 Ignacio Oviedo

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
