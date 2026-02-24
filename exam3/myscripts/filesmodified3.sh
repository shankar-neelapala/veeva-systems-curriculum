#!/bin/bash
result=$(find . -maxdepth 2 -type f -mtime -3)
echo "Files modified with in the three days"
echo $result
