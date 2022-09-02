# The Key V1 Linux flashing guide
## Set up your QMK environment
Follow the official [guide](https://docs.qmk.fm/#/newbs_getting_started) for this step.
> **TIP**: QMK can be installed to a custom location using `qmk setup --home <location>/qmk_firmware`

## Test your build environment
Let's build and flash some [examples](https://github.com/qmk/qmk_firmware/tree/master/keyboards/massdrop/thekey).

```shell
cd ~/qmk_firmware

# build the default configuration provided by Drop / Stack Overflow
make massdrop/thekey:default
# flash it (enter bootloader mode first)
make massdrop/thekey:default:dfu

# build a common modification where C = CTRL+C, V = CTRL+V
make massdrop/thekey:url-copy-paste
# flash it (enter bootloader mode first)
make massdrop/thekey:url-copy-pastet:dfu
```

Entering the bootloader mode:
* **Bootmagic reset** - Hold down the "Stack Overflow" key, the "left-most" or furthest from the USB plug while inserting the USB cable for a few seconds. The LEDs will NOT turn on.
* **Physical reset button** - Briefly press and hold the reset button while pluggin in the USB port. The LEDs on the back will NOT turn on. Depending on your case revision, you may have to remove the 4 screws on the back plate to access the switch OR you can use the associated access hole on newer releases.

## Create your own custom configuration
Let's configure the following key mappings:

|        Key         |        Hotkey        |
|:------------------:|:--------------------:|
| Stack Overflow key | `ctrl` + `alt` + `1` |
|        `C`         | `ctrl` + `alt` + `2` |
|        `V`         | `ctrl` + `alt` + `3` |

If you wish to use a different mapping, then the full list of available key codes can be found [here](https://docs.qmk.fm/#/keycodes). 

```shell
# create a directory for your custom config
cd ~/qmk_firmware/keyboards/massdrop/thekey/keymaps
mkdir custom

# create a keymap file 
cat <<EOF >> custom/keymap.c
#include QMK_KEYBOARD_H

const uint16_t PROGMEM keymaps[][MATRIX_ROWS][MATRIX_COLS] = {
    [0] = LAYOUT(0, 1, 2)
};

bool process_record_user(uint16_t keycode, keyrecord_t *record) {
    switch (keycode) {
        case 0:
            if (record->event.pressed) {
                register_code(KC_LCTL);
                register_code(KC_LALT);
                register_code(KC_1);
                clear_keyboard();
            }
            break;
        case 1:
            if (record->event.pressed) {
                register_code(KC_LCTL);
                register_code(KC_LALT);
                register_code(KC_2);
                clear_keyboard();
            }
            break;
        case 2:
            if (record->event.pressed) {
                register_code(KC_LCTL);
                register_code(KC_LALT);
                register_code(KC_3);
                clear_keyboard();
            }
            break;
    }
    return true;
};
EOF

# flash it (enter bootloader mode first)
cd ~/qmk_firmware
make massdrop/thekey:custom:dfu
```