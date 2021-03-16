#!/bin/bash

LOG_FILE=/var/log/post-transmission.log
TORRENT_FOLDER="${TR_TORRENT_DIR}/${TR_TORRENT_NAME}"

# opensubtitles username and password:
OPENSUBTITLES_USERNAME="%OPENSUBTITLES_USERNAME%"
OPENSUBTITLES_PASSWORD="%OPENSUBTITLES_PASSWORD%"

echo "Starting at $(date): ${TORRENT_FOLDER}" &>> ${LOG_FILE}
find "${TORRENT_FOLDER}" -type f | xargs -I{} /etc/transmission/subtitle-find.sh "{}" "${OPENSUBTITLES_USERNAME}" "${OPENSUBTITLES_PASSWORD}" &>> ${LOG_FILE}
find "${TORRENT_FOLDER}" -type f | xargs -I{} /etc/transmission/convert2ac3.sh "{}" &>> ${LOG_FILE}
echo "Finished at $(date): ${TORRENT_FOLDER}" &>> ${LOG_FILE}
