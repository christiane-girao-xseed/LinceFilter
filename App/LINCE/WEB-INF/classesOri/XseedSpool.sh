## XseedSpool.sh - Xseed Printer Unix/Linux Spooler
## Instructions: Add specific printer connfiguration options here.
##      Remarks: Only a configuration (Unix/Linux) can be active a time. 

## Unix Syntax:  /usr/bin/lp -d <printer> -o <options> <backup-file>
## /usr/bin/lp -d $1 "$2" 

copies=$3

i=1 

if [ ! -z "$4" ]
then

	FORMID=$4
else

	FORMID=" "
fi

if [ ! -z "$copies" ]
then
	while [ $i -le $copies ]
	do 
		/usr/bin/lp -d $1 -t"$FORMID" "$2"
		i=`expr $i + 1`
	done 
else
	/usr/bin/lp -d $1 -t"$FORMID" "$2"
fi 

## Linux Syntax: /usr/bin/lpr -P <printer> -Z <options> <backup-file>
## /usr/bin/lpr -P $1  "$2" 

##copies=$3
##i=1 

##if [ ! -z "$4" ]
##then
##    FORMID=$4
##else
##    FORMID=" "
##fi 

##if [ ! -z "$copies" ]
##then
##    while [ $i -le $copies ]
##    do 
##        /usr/bin/lpr -P $1 -T"$FORMID" "$2"
##        i=`expr $i + 1`
##    done 
##else
##    /usr/bin/lpr -P $1 -T"$FORMID" "$2"
##fi