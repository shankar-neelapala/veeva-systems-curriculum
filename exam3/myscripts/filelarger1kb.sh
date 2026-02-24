#!/bib/bash
result=$(find . -maxdepth 1 -type f -size +1k) #-exec ls -lh {} \;
if [[ -z $result ]]
then 
	echo "No files are larger than 1KB"
else
	echo "Files larger than 1KB are"
	echo "$result"
fi
