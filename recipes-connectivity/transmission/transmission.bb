SUMMARY = "Transmission Application"
SECTION = "Connection"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILEEXTRAPATHS_prepend = "${THISDIR}/files"
SRC_URI = "git://github.com/transmission/transmission.git"
SRC_URI += "file://arm_build.patch"

# Transmission verision 2.94
SRCREV = "d8e60ee44f4295935bd98bf741f85ed19f5a7dfb"
PV = "2.94"

S = "${WORKDIR}/git"

DEPENDS = "curl openssl glib-2.0"
RDEPENDS_${PN} = "libevent"

# Workaround for transmission build, it needs the transmission library to be built first to avoid dependency issues.
OECMAKE_TARGET_COMPILE = "transmission"
do_compile_append() {
	cmake_runcmake_build --target all
}
do_install_append() {
	DESTDIR='${D}' cmake_runcmake_build --target all
}

inherit pkgconfig cmake
