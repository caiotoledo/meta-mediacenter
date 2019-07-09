SUMMARY = "Script for Transmission Daemon init"
SECTION = "Connection"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

INITSCRIPT_NAME = "init-transmission"
INITSCRIPT_PARAMS = "defaults 99 10"

FILEEXTRAPATHS_prepend = "${THISDIR}/files"
SRC_URI = "file://init-transmission"

DEPENDS = "transmission"
RDEPENDS_${PN} = "transmission"

do_install_append() {
	install -d ${D}/etc/init.d
	install -m 0755 ${WORKDIR}/init-transmission ${D}/etc/init.d/

	sed -i 's/%USERNAME%/${USERNAME}/g' ${D}/etc/init.d/init-transmission
	sed -i 's/%PASSWORD%/${PASSWORD}/g' ${D}/etc/init.d/init-transmission
}

inherit update-rc.d
