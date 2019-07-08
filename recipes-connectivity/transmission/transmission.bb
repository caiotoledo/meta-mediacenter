SUMMARY = "Transmission Application"
SECTION = "Connection"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

INITSCRIPT_NAME = "init-transmission"
INITSCRIPT_PARAMS = "defaults 99 10"

FILEEXTRAPATHS_prepend = "${THISDIR}/files"
SRC_URI = "git://github.com/transmission/transmission.git;tag=2.94"
SRC_URI += "file://init-transmission"
SRC_URI += "file://arm_build.patch"

S = "${WORKDIR}/git"

DEPENDS = "curl openssl"

do_install_append() {
	install -d ${D}/etc/init.d
	install -m 0755 ${WORKDIR}/init-transmission ${D}/etc/init.d/
}

inherit pkgconfig cmake update-rc.d
