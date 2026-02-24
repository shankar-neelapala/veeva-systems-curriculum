#!/bin/bash
read -p "Enter the file name: " filename
file=$(find /mnt/d/myscripts/ -type f -name "$filename")
if [[ ! -f $file ]]
then
	echo "File does not exist"
	exit 1
fi
read -p "Enter the string to search in the file: " str
matches=$(grep -n "$str" "$file")
if [[ -z $matches ]]
then
	echo "No matches found"
else
	echo "Found at line(s)"
	echo "$matches"
fi

