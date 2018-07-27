# gMaps.Static

A simple android library to get a direction rendered on a Google Static Maps from an array
of locations. 
- [Screenshot](#screenshot)
- [Usage](#usage)

### Screenshot
<img src="/screenshots/sample.png" width="400px" />

### Usage
First add jitpack to your projects build.gradle file

```gradle
allprojects {
   	repositories {
   		...
   		maven { url "https://jitpack.io" }
   	}
}
```

Then add the dependency in your android app module's `build.gradle` file.

```gradle
dependencies {
    implementation 'com.github.xc0d3rz:gMaps-Library:0.3.0'
}
```

Then add `android.permission.INTERNET` permission
```xml
    <uses-permission android:name="android.permission.INTERNET" />
```
#### Example 1
Get the static map directly as a bitmap
```java
   ImageView staticMap = (ImageView) findViewById(R.id.static_map);
        List<String> Mylist = new ArrayList<String>();
        Mylist.add("44.8765065,-0.4444849");
        Mylist.add("44.8843778,-0.1368667");
        staticMap.setImageBitmap(gStatic.getMapAsBitmap("45.291002,-0.868131", "44.683159,-0.405704", Mylist));
```

#### Example 2
Get the static map URL only
```java
        List<String> Mylist = new ArrayList<String>();
        Mylist.add("44.8765065,-0.4444849");
        Mylist.add("44.8843778,-0.1368667");
        Log.i("gStatic",gStatic.getMap("45.291002,-0.868131", "44.683159,-0.405704", Mylist)); //staticMap URL
```

### Thanks
Special Thanks to @[amolood](https://github.com/amolood) ♥
