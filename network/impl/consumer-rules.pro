# kotlinx.serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt

# kotlinx-serialization-json specific. Add this if you have java.lang.NoClassDefFoundError kotlinx.serialization.json.JsonObjectSerializer
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}

-keep,includedescriptorclasses class xyz.pavelkorolev.randomuser.**$$serializer { *; }
-keepclassmembers class xyz.pavelkorolev.randomuser.** {
    *** Companion;
}
-keepclasseswithmembers class xyz.pavelkorolev.randomuser.** {
    kotlinx.serialization.KSerializer serializer(...);
}
