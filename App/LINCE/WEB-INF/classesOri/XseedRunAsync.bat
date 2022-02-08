CD %1 
SET PATH=%1;%PATH% 
SET CLASSPATH=%1;%1\..\lib\mail.jar;%1\..\lib\activation.jar;%1\..\lib\HLegacy.jar;%1\..\lib\XseedRts.jar;%CLASSPATH% 
%JAVA_HOME%\bin\java -classpath "%CLASSPATH%"  %2 %3 %4 %5 %6