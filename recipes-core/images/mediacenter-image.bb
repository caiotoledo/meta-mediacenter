SUMMARY = "A image to create a MediaCenter"

LICENSE = "MIT"

IMAGE_OVERHEAD_FACTOR="3"

CORE_IMAGE_EXTRA_INSTALL += "\
	packagegroup-core-boot \
	packagegroup-core-ssh-openssh \
	minidlna \
	transmission-init \
	udev-extraconf \
	${@bb.utils.contains('MACHINE', 'beaglebone-green-wifi', "wl18xx-fw", "", d )} \
	${@bb.utils.contains('MACHINE', 'beaglebone-green-wifi', "dhcp-client", "", d )} \
	${@bb.utils.contains('MACHINE', 'beaglebone-green-wifi', "wifi-init", "", d )} \
"

IMAGE_INSTALL_append = " kernel-modules kernel-devicetree"

# Boot files for Beaglebone:
BOOT_FILES_BEAGLEBONE = "zImage am335x-bone.dtb am335x-boneblack.dtb uEnv.txt"

IMAGE_BOOT_FILES_append = "${@bb.utils.contains('MACHINE', 'beaglebone', " ${BOOT_FILES_BEAGLEBONE}", "", d )}"

# Boot files for Beaglebone Green Wireless:
BOOT_FILES_BEAGLEBONE_GREEN = "am335x-bonegreen-wireless.dtb am335x-bonegreen.dtb"

IMAGE_BOOT_FILES_append = "${@bb.utils.contains('MACHINE', 'beaglebone-green-wifi', " ${BOOT_FILES_BEAGLEBONE}", "", d )}"
IMAGE_BOOT_FILES_append = "${@bb.utils.contains('MACHINE', 'beaglebone-green-wifi', " ${BOOT_FILES_BEAGLEBONE_GREEN}", "", d )}"

TOOLCHAIN_TARGET_TASK_append = " kernel-devsrc"

inherit core-image
