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

get_codec_name() {
	ffprobe -v error -select_streams a:0 -show_entries stream=codec_name -of default=noprint_wrappers=1:nokey=1 "$1"
}

FILE=$1

if [ ! -f "$FILE" ]; then
	echo "$FILE do not exists!"
	exit 1
fi

isVideo=$(is_video "$FILE")
if [ $isVideo == 0 ];then
	echo "${FILE} Not a video file!"
	exit 1
fi

FILENAME="${FILE%.*}"

aac_ac3_info=$(get_codec_name "$FILE" | grep "aac\|ac3")
if [ -z "$aac_ac3_info" ];then
	echo "$FILE converting to ac3 -> $FILENAME.mp4!!!"
	time ffmpeg -i "$FILE" -c:v copy -c:a ac3 "$FILENAME.mp4" -y && rm -v "$FILE"
else
	echo "$FILE already ac3"
	exit 1
fi

exit 0
