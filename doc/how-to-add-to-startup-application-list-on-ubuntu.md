# How to start the app automatically on Ubuntu
Follow these steps to launch `macrpad` automatically at login: 
* Open [Startup Applications](https://help.ubuntu.com/stable/ubuntu-help/startup-applications.html.en) via the Activities overview
* Click `Add` 
* Fill the required fields:
  * Name:`macropad`
  * Command<sup>*</sup>:`java -jar PATH_TO_APP_DIR/macropad.jar --daemon`
  * Comment:`Stack Overflow companion app`
* Click `Add`

> &ast; - change the path and app version to match your setup

**Example** \
![Ubuntu Startup Applications](https://cdn.dobicinaitis.dev/git/macropad-startup-applications-ubuntu.png)

The next time you will log into the system, the application will be launched automatically.

To start it manually, open the terminal (`ctrl`+`alt`+`t`) and execute:
```shell
java -jar PATH_TO_APP_DIR/macropad.jar --daemon

# alternately run the process in background and suppress all outputs
java -jar PATH_TO_APP_DIR/macropad.jar --daemon > /dev/null 2>&1 &
```