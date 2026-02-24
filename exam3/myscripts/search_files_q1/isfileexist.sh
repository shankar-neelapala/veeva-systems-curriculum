#!/bin/bash
#search file in system by using the full path
read -p "Enter full file path: " path
if [[ -f "$path" ]]
then
	echo "File exist."
else
	echo "No such file!"
fi
