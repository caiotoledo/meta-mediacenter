# Delay udev initialization
INITSCRIPT_PARAMS = "${@bb.utils.contains('MACHINE', 'beaglebone-green-wifi', "start 30 S .", "start 04 S .", d )}"
