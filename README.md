# The Key companion app
![The Key](https://cdn.dobicinaitis.dev/git/the-key-v1.jpg)

Do you own a [Stack Overflow macropad](https://drop.com/buy/stack-overflow-the-key-macropad) and wish that it would actually interact with the site?  
This simple CLI app will grab text from your clipboard and use it to open the Stack Overflow search page whenever The Key is pressed.

## Info
```text
NAME
      macropad - The Key companion app

USAGE
      macropad [-dhvV] <search text>

DESCRIPTION
      Find the best answer to your technical issue with a click of a key.
      Simply copy an error message and hit The Key to view Stack Overflow search results for it in your default browser.

PARAMETERS
      <search text>   Search query (optional). Clipboard content will be used if parameter is omitted.

OPTIONS
  -d, --daemon        Wait for The Key to be pressed, then open Stack Overflow search results with query from your clipboard.
  -h, --help          Show this help message and exit.
  -v, --verbose       Print debug information.
  -V, --version       Print version information and exit.
```

## Requirements
* Java 11 or later

## Setup
### The Key
You'll need to assign a custom hotkey `ctrl`+`alt`+`1` to the Stack Overflow key.
If you're on macOS or Windows, follow the official [guide](https://drop.com/talk/93641/how-to-configure-stack-overflow-the-key-macropad) for information on how to flash custom configuration to the macropad.
A guide for Linux users can be found [here](doc/how-to-flash-firmware-on-linux.md).

> **Note** The app uses only the Stack Overflow key. You can assign any hotkeys to `C` and `K` keys and use them for other automations.

### App
#### Download a release version
Get the latest release from [here](https://github.com/dobicinaitis/stack-overflow-macropad/releases).

Try running the app:
```shell
java -jar macropad-0.1.0.jar --help
```

#### Build it yourself
```shell
# clone the repository
git clone https://github.com/dobicinaitis/stack-overflow-macropad
cd stack-overflow-macropad

# (optional) change the default hotkey
vim src/main/resources/application.yml

# build it
./gradlew clean build

# run it
java -jar build/libs/macropad-0.1.0.jar --help
```

### Bring it all together
You have 2 options for letting the app know when to open Stack Overflow:
* Daemon mode
* Global shortcut

#### Daemon mode
Run the app with `--daemon` option. It will listen for hotkey keystrokes and open Stack Overflow whenever The Key is pressed.
```shell
java -jar macropad-0.1.0.jar --daemon
```

You can also add the app to your OS's application startup list to launch it automatically at login.

Ubuntu [guide](doc/how-to-add-to-startup-application-list-on-ubuntu.md).

#### Global shortcut
Use tools provided by your OS to configure a keyboard shortcut that executes `java -jar PATH_TO_APP_DIR/macropad-0.1.0.jar` when `ctrl`+`alt`+`1` is pressed.

Ubuntu [guide](https://help.ubuntu.com/stable/ubuntu-help/keyboard-shortcuts-set.html.en).

> **Note** This option is slower as it takes a bit for the JVM to start.

## Alternatives
Not a fan of this app or Java? It's ok. Honestly, a solution to grab clipboard text and open a URL can be implemented in 
couple lines of code using most programming languages, so just give it a try using your favorite language.

## TODO (if bored)
- [ ] Compile to a standalone executable
- [ ] Allow overriding the default hotkey using `--hotkey` option
- [ ] Package the app for multiple OSs
- [ ] Add a GUI for configuring and flashing The Key

## joy++
<a href="https://www.buymeacoffee.com/dobicinaitis" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/default-orange.png" alt="Buy Me A Coffee" height="41" width="174"></a>