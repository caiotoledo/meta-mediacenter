SUMMARY = "Transmission Application"
SECTION = "Connection"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILEEXTRAPATHS_prepend = "${THISDIR}/files"
SRC_URI = "git://github.com/transmission/transmission.git;tag=2.94"
SRC_URI += "file://arm_build.patch"

S = "${WORKDIR}/git"

DEPENDS = "curl openssl"

inherit pkgconfig cmake
