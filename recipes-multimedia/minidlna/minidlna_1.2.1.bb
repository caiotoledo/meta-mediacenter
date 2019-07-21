require ${BPN}.inc

SRCREV = "c760a338e07ebd11d62fef701e3de824a91f8625"
LIC_FILES_CHKSUM = "file://LICENCE.miniupnpd;md5=b0dabf9d8e0f871554e309d62ead8d2b"

FRIENDLY_NAME??="MediaCenter"

do_install_append() {

	sed -i 's/%FRIENDLY_NAME%/${FRIENDLY_NAME}/g' ${D}/etc/minidlna.conf

}
