# admin_panel

This application is simple web service that should returns "heatbeat" responce 
Also application is provide login page and draft version of admin panel,

### Parameters

- *Url*: heatbeat

- *Response*: I am live 

- *If errors*: return error message without stacktrace 


### How to Deploy

**Steps** (if server is 212.224.118.153)

1. run maven clean install 
1. open /target directory in terminal
1. run command: 

    "scp admin_panel.war root@212.224.118.153:admin_panel.war" 
    
1. open remote terminal using command:

    "ssh root@212.224.118.153" 
    
1. open "root" directory

1. check that admin_panel.war is exist 

1. run command "java -jar admin_panel.war &"      

1. check in browser "http://212.224.118.153:18081/heatbeat" 

1. You should see "I am live!"

**Steps** (if server is 185.146.20.48)

1. run maven clean install 
1. open /target directory in terminal
1. run command: 
    "scp -P 7822 admin_panel.war root@185.146.20.48:admin_panel.war"
    
1. open remote terminal using command:
    
    "ssh -p 7822 root@185.146.20.48"
    
1. open "root" directory

1. check that admin_panel.war is exist 

1. run command "java -jar admin_panel.war &"      

1. check in browser "http://185.146.20.48:18081/heatbeat"

1. You should see "I am live!"

### How to Stop

1. Run command "ps aux | grep -i java"
1. You can see something like
    root     20997  0.1 13.3 2372396 134452 ?      Sl   Feb27   2:31 java -jar admin_panel.war
1. Run command "kill -9 20997", when 20997 - pid from last command result,
1. Check that application is stoped using browser and command "ps aux | grep -i java"