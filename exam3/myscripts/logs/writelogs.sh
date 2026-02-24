#!/bin/bash
read -p "Enter message to log: " msg
echo "$(date '+%Y-%m-%d %H:%M:%S') - $msg" >> logsdocumnet.txt
echo "Successfully  logged"
