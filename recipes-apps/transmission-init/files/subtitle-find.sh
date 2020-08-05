#!/bin/bash

get_ffmpeg_info() {
	ffmpeg -i "$1" 2>&1 > /dev/null
}

is_video(){
	info=$(get_ffmpeg_info "$1" | grep 'Metadata')
	if [ -n "$info" ]; then
		echo 1
	else
		echo 0
	fi
}

FILE=$1
OPENSUBTITLES_USERNAME=$2
OPENSUBTITLES_PASSWORD=$3
LANG=$4

if [ ! -f "$FILE" ]; then
	echo "$FILE do not exists!"
	exit 1
fi

OPENSUBTITLE_ARG=''
if [[ -n "$OPENSUBTITLES_USERNAME" && -n "$OPENSUBTITLES_PASSWORD" ]]; then
	OPENSUBTITLE_ARG="--opensubtitles ${OPENSUBTITLES_USERNAME} ${OPENSUBTITLES_PASSWORD}"
fi

if [ -z "$LANG" ]; then
	echo "No language given!"
	echo "Using standard language: [pt-br]"
	LANG="pt-br"
fi

isVideo=$(is_video "$FILE")
if [ $isVideo == 1 ]
then
	# Install subliminal if needed
	if ! pip list --format=columns | grep "subliminal"; then
		pip install -Iv rarfile==2.7 # subliminal depends on rarfile, and only rarfile 2.7 works on python 2.7 version.
		pip install subliminal
	fi
	echo "Searching a subtitle for ${FILE}"
	# Download the Subtitle
	subliminal $OPENSUBTITLE_ARG download -s -l "$LANG" "$FILE"
else
	echo "${FILE} Not a video file!"
	exit 1
fi
