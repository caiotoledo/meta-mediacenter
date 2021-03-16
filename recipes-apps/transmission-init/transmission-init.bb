SUMMARY = "Script for Transmission Daemon init"
SECTION = "Connection"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

INITSCRIPT_NAME = "init-transmission"
INITSCRIPT_PARAMS = "defaults 99 10"

FILEEXTRAPATHS_prepend = "${THISDIR}/files"
SRC_URI = "file://init-transmission \
	file://subtitle-find.sh \
	file://convert2ac3.sh \
	file://post-transmission.sh \
	file://settings.json"

RDEPENDS_${PN} = "transmission \
	bash \
	ffmpeg \
	python3 \
	python3-pip"

USERNAME??="admin"
PASSWORD??="admin"

OPENSUBTITLES_USERNAME??=""
OPENSUBTITLES_PASSWORD??=""

do_install_append() {
	install -d ${D}/${sysconfdir}/transmission/
	install -m 0755 ${WORKDIR}/settings.json ${D}/${sysconfdir}/transmission/
	install -m 0755 ${WORKDIR}/subtitle-find.sh ${D}/${sysconfdir}/transmission/
	install -m 0755 ${WORKDIR}/convert2ac3.sh ${D}/${sysconfdir}/transmission/
	install -m 0755 ${WORKDIR}/post-transmission.sh ${D}/${sysconfdir}/transmission/

	install -d ${D}/etc/init.d
	install -m 0755 ${WORKDIR}/init-transmission ${D}/etc/init.d/

	sed -i 's/%USERNAME%/${USERNAME}/g' ${D}/etc/init.d/init-transmission
	sed -i 's/%PASSWORD%/${PASSWORD}/g' ${D}/etc/init.d/init-transmission

	sed -i 's/%OPENSUBTITLES_USERNAME%/${OPENSUBTITLES_USERNAME}/g' ${D}/${sysconfdir}/transmission/post-transmission.sh
	sed -i 's/%OPENSUBTITLES_PASSWORD%/${OPENSUBTITLES_PASSWORD}/g' ${D}/${sysconfdir}/transmission/post-transmission.sh
}

inherit update-rc.d
