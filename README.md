# JavaBeanGenerator
Generate Java Bean files.If your want more custom usage,just fork it and make it work for you.

# Quick start(with MySQL)

### step 1
Open the 'demo' directory.Edit the conf/config.properties.(mainly the mysql connect info).
eg:
```
jdbc.url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8
jdbc.username=root
jdbc.password=123456
```
### step 2
run the jar file with command line.
```
java -jar generator.jar
```
on win system you could simply double click the start.bat
