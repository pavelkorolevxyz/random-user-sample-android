# kotlinx.serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.SerializationKt
-keep,includedescriptorclasses class xyz.pavelkorolev.randomuser.**$$serializer { *; }
-keepclassmembers class xyz.pavelkorolev.randomuser.** {
    *** Companion;
}
-keepclasseswithmembers class xyz.pavelkorolev.randomuser.** {
    kotlinx.serialization.KSerializer serializer(...);
}
