#!/bin/bash
read -p "Enter the html file: " filename
file=$(find /mnt/d/myscripts/ -type f -name "$filename")
if [[ -z "$file" ]]
then
	echo "No such file"
	exit 1
fi
read -p "Enter tag name: " tag
matches=$(grep -oP "(?<=<$tag>).*?(?=</$tag>)" "$file")
if [[ -z $matches ]]
then
	echo "No tags are exists"
else
	echo "Text found between the tag(s)"
	echo "$matches"
fi
