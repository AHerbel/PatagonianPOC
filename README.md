# PatagonianPOC
POC for Patagonian

# Build

To build:

```bash
$ git clone https://github.com/AHerbel/PatagonianPOC.git
$ cd PatagonianPOC/
$ ./gradlew build
```

# Run

To run:

Prerequistes: <br>
Have the $ANDROID_SDK_HOME environment varialble setted in your computer<br>
Have the $ANDROID_SDK_HOME/platform-tools setted in your PATH environment variable

```bash
$ emulator -list-advs
$ emulator -avd [avd-name]
$ adb install <path to repo folder>/app/build/outputs/apk/debug/app-debug.apk
$ adb shell am start -n com.aherbel.patagonianpoc/com.aherbel.patagonianpoc.presentation.screens.main.MainActivity
```

PD: <br>
```bash
$ANDROID_HOME=/Users/<your_user>/Library/Android/sdk (Mac OS X)
$ANDROID_HOME=C:\Users\<your_user>\AppData\Local\Android\Sdk (Windows)
```
