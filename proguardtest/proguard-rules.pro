# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/riven_chris/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

###################################################################################

# com.rivent_chris.proguardtest.Model.User

#-keep class * implements java.io.Serializable{
##        !private static <fields>; //invaliad ？
#
#        <fields>;
#        public <methods>;
#}

#-keep class * implements java.io.Serializable

#-keep class com.rivent_chris.proguardtest.Model.**

#-keep class com.rivent_chris.proguardtest.Model.**{
#    *;
#}

#-keepclassmembers class * {
#    public static <methods>;
#}

-keepclasseswithmembers class * {
    public static <methods>;
}

#-keepnames class *
#-keepnames class com.rivent_chris.proguardtest.Utils.**


#-keepclassmembernames class * {
#    public static <methods>;
#}

#-keepclasseswithmembernames class * {
#    public static <methods>;
#}

#-whyareyoukeeping .......

#-assumenosideeffects class android.util.Log {
#        public static boolean isLoggable(java.lang.String, int);
#        public static int v(...);
#        public static int i(...);
#        public static int w(...);
#        public static int d(...);
#        public static int e(...);
#}

#-mergeinterfacesaggressively

#-useuniqueclassmembernames

#-dontusemixedcaseclassnames

#-flattenpackagehierarchy

#-keepattributes Exceptions, InnerClasses, Signature, Deprecated, SourceFile, LineNumberTable, *Annotation*, EnclosingMethod


#-adaptclassstrings com.rivent_chris.proguardtest.Utils.DateUtil //？

#-printconfiguration

#-keepclasseswithmembernames,includedescriptorclasses class * {
#         native <methods>;
#}

-keepclassmembers class * {
    <init>(%,java.lang.String);

    public % *(**);
}

