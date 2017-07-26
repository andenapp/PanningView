# PanningView - The official library from Andén [![GitHub release](https://img.shields.io/github/release/nacho91/PanningView.svg?style=flat-square)](https://github.com/nacho91/PanningView/releases/tag/1.0.1)

PanningView provides a view that can animate background drawable. It's provide a horizontal and vertical panning (You can create your custom animation).

![Splash](https://github.com/nacho91/PanningView/blob/master/splash.gif)

### Setup

Gradle dependency

```gradle
compile 'com.anden.panningview:panning-view:1.0.2'
```

or

Maven dependency

```xml
<dependency>
  <groupId>com.anden.panningview</groupId>
  <artifactId>panning-view</artifactId>
  <version>1.0.2</version>
  <type>pom</type>
</dependency>
```

### Usage

Declare the PanningView in your XML

```xml
 <com.anden.panningview.PanningView
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

## Docs

[Java Docs](https://andenapp.github.io/PanningView/)

## Developed By

* Ignacio Oviedo 
 
&nbsp;&nbsp;&nbsp;**Email** - oviedoignacio91@gmail.com

## License
 * Distributed under the Apache license. See LICENSE for more information.
