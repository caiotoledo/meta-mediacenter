SUMMARY = "Deluge Application"
SECTION = "Connection"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRCREV = "d62987089e55d6afe7c85addbdcb6ab515db69ea"
SRC_URI = "git://github.com/deluge-torrent/deluge.git;branch=1.3-stable"

DEPENDS= "python-native"
RDEPENDS_${PN} = "bash"

S = "${WORKDIR}/git"

inherit setuptools

do_compile() {
	${STAGING_BINDIR_NATIVE}/python-native/python setup.py build
}

do_install() {
	${STAGING_BINDIR_NATIVE}/python-native/python setup.py install

	install -d ${D}${bindir}
	install -m 0755 osx/deluge ${D}${bindir}
	install -m 0755 osx/deluge-console ${D}${bindir}
	install -m 0755 osx/deluge-web ${D}${bindir}
	install -m 0755 osx/deluged ${D}${bindir}
}

