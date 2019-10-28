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
LANG=$2

if [ ! -f "$FILE" ]; then
	echo "$FILE do not exists!"
	exit 1
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
	pip install subliminal
	echo "Searching a subtitle for ${FILE}"
	# Download the Subtitle
	subliminal download -s -l "$LANG" "$FILE"
else
	echo "${FILE} Not a video file!"
	exit 1
fi
