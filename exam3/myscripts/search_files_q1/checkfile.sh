#!/bin/bash
read -p "Enter file name: " filename
result=$(find /mnt/d -type f -name "$filename" 2>/dev/null)
if [[ -n $result ]]
then
	echo "File exist in the system"
	echo "Found at $result"
else
	echo "No such file in the system"
fi
