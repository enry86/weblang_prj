Remote Deployment of Web Services
---------------------------------------------------------------------

Version 2.0, 2-June-2009

Author: Gaia Trecarichi
        EMail:    gtrecari@disi.unitn.it
        WL Course Homepage: http://disi.unitn.it/~gtrecari/weblanguages/

---------------------------------------
NOTES:
---------------------------------------

* It is assumed that your project includes the following types of directories:

classes --> .class files
lib --> library used by your service (eg., rome,jdom, etc )
deployment --> .wsdd file

The names of your directories may vary according to your settings

* It is assumed that you already have the compiled classes of your service!

---------------------------------------
FILES INCLUDED
---------------------------------------

* jsch-0.1.41.jar

- Library needed to run the ant task "scp" and "sshexec" (included in build.xml)
- Include this library in your 'ANT_HOME/lib' directory (if you run ant tasks from command line)
- Change the ANT settings of your IDE (if you run ant tasks from your IDE) 
  (eg., in Eclipse: go to Window-->Preferences-->Ant-->Runtime-->Global Entries and "add External JARs")

* build.xml

-include task "remote-deploy" to deploy your service remotely
-include task "remote-undeploy" to undeploy your service remotely

* service.properties

- define variables needed for the above tasks
- change the parameters according to your settings

- variable "service.main.package" (default 'groupname_myservicename'): define the main package of the service implementation
  A directory named 'groupname_myservicename' will be created under '.../axis/WEB-INF/classes'
  This name must be univoque for each service of each group!

- variable "service.name" (default 'myservicename'): define the service name
A directory named 'myservicename' will be created in your account on the remote server 

* deploy.xml

-include task "deploy" which is run on the remote server
-include task "undeploy" which is run on the remote server
- you SHOULD NOT modify this file

* server.properties

- define parameters of the remote server
- you SHOULD NOT modify this file

----------------------------------------
DEPLOY YOUR SERVICE IN A REMOTE SERVER
---------------------------------------

- Run task "remote-deploy" of the build.xml

Description: after login into the remote server, a directory "myservicename" is 
created under directory wl09 of your account. 
Such directory contains 3 sub-directories (default 'classes', 'lib', 'deployment') and
3 files (server.properties, service.properties, deploy.xml). 
The task "deploy" of the deploy.xml is executed in the remote server: the main package of the service 
is copied under '.../axis/WEB-INF/classes', the libraries used by the service are 
copied under '.../axis/WEB-INF/lib' directory and the axis-admin task is called to deploy the service

- Check whether your service has been deployed at 

http://demo.liquidpub.org:8180/axis/

----------------------------------------
UNDEPLOY YOUR SERVICE FROM A REMOTE SERVER
---------------------------------------

- Run task "remote-undeploy" of the build.xml

Description: after login into the remote server, the task "undeploy" of the deploy.xml 
is executed in the remote server: the axis-admin task is called to undeploy the service and
the main package of the service is removed from '.../axis/WEB-INF/classes' directory 

- Check whether your service has been undeployed at 

http://demo.liquidpub.org:8180/axis/


----------------------------------------
RESTART TOMCAT 
---------------------------------------

The command to issue is:

sudo /etc/init.d/tomcat5.5 force-reload

Please, be careful when using this feature not to disturb other groups
working on the deployment machine (i.e., wait for a reasonable time span
between each restart). Avoid abuses and use this command sparingly.
