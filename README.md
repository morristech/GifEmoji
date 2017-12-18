# GifEmoji
Library gif emoji like skype, messenger, wechat, whatapp, snapchat, facebook, zalo

# Demo

[<img src="/store/GIF.gif">](https://play.google.com/store/apps/details?id=com.hoanganhtuan01101995.sola_date)

# Project

Icon | Name | Link Google Play
------------ | ------------ | -------------
[<img src="/store/ic_launcher.png">](https://play.google.com/store/apps/details?id=com.hoanganhtuan01101995.sola_date) | [Sola - Stranger chat, Anonymous chat & Date](https://play.google.com/store/apps/details?id=com.hoanganhtuan01101995.sola_date) | [<img src="/store/Store.png">](https://play.google.com/store/apps/details?id=com.hoanganhtuan01101995.sola_date)


# Download

* Step 1. Add it in your root build.gradle at the end of repositories:
```java
    allprojects {
        repositories {
          ...
          maven { url 'https://jitpack.io' }
        }
    }
```
* Step 2. Add the dependency
```java
    dependencies {
	  compile 'com.github.hoanganhtuan95ptit:GifEmoji:1.0'
    }
```

# Using

* xml

```java
    <com.hoanganhtuan95ptit.gifemoji.EmoticonTextView
        android:id="@+id/tv_infor"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:emoticonSize="30sp"
        app:emoticonSchedule="40"/>
```
attr | description 
------------ | ------------ 
emoticonSize | the size of the emoticon, default textSize
emoticonSchedule | Emoticon refresh cycle, default 40

# How to customize the EmoticonTextView
* Customize EmoticonProvider
```java
    public interface EmoticonProvider {

        HashMap<String, Integer> getEmoticons();

    }
```
* Example
```java
    public class GifEmoticonProvider implements EmoticonProvider {

        static final HashMap<String, Integer> EMOTICONS = new HashMap<>();

        static {
            EMOTICONS.put("(giggle)", R.drawable.giggle);
            EMOTICONS.put("(monkey)", R.drawable.monkey);
            EMOTICONS.put(":)", R.drawable.emoji_1);
            ...
        }

        private static GifEmoticonProvider exampleEmoticonProvider;

        public static GifEmoticonProvider create() {
            if (exampleEmoticonProvider == null) {
                exampleEmoticonProvider = new GifEmoticonProvider();
            }
            return exampleEmoticonProvider;
        }

        private GifEmoticonProvider() {
        }

        @Override
        public HashMap<String, Integer> getEmoticons() {
            return EMOTICONS;
        }

    }
```
 * set EmoticonProvider
 ```java
     emoticonTextView.setEmoticonProvider(GifEmoticonProvider.create());
 ```
* set Text
 ```java
     emoticonTextView.setTextEmoticon(message.getInfo());
 ```
# Thank 

 Name | Library
------------ | -------------
waynejo | [android-ndk-gif](https://github.com/waynejo/android-ndk-gif) 

