#!/bin/bash
#search in present working directory
read -p "Enter file name: " name
if [[ -f "./$name" ]]
then
	echo "File exist in current working directory"
else
	echo "No such file in current working directory"
fi
