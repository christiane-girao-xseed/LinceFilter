CLASSPATH="$1":"$1"/mail.jar:"$1"/activation.jar:"$7"/HLegacy.jar:"$7"/XseedRts.jar:$CLASSPATH
$JAVA_HOME/bin/java -classpath $CLASSPATH "$2" "$3" "$4" "$5" "$6"


