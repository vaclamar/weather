Building instruction:
1. Install and set JAVA SDK 
http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html
2. Install and set Maven 3.0.4
http://archive.apache.org/dist/maven/binaries/apache-maven-3.0.4-bin.zip
3. Add maven to Path:
4. run form command line:
in the weater folder run:
mvn install

Deployment instruction:
In the command line:
1. cd weather-web
2. mvn jetty:run

You can test application in a web browser in URL http://localhost:8080
credential you can find in the file:\weather\weather-web\src\main\resources\META-INF\access.properties

For testing by SoapUI, install it from http://www.soapui.org/
Open workspace with SoapUI: soapui\Weather-workspace.xml

In order to access the Wunderground service you must register and create new account. This will provide API key that will be used by the application.
The registration can be done at http://www.wunderground.com/weather/api/ and it is free.

Please use your own Wunderground API key:
You can set it by service.api.key property in the \weather\weather-common\src\main\resources\META-INF\common.properties

