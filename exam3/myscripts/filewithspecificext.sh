#!/bin/bash
read -p "Enter file extension (txt/java) " extension
files=$(find . -type f -name "*.$extension")
if [[ -z $files ]]
then
	echo "No such files"
else
	echo "Files matched with specified extension"
	echo "$files"
fi
