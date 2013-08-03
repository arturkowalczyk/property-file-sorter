Property file sorter
====================

This is a small application that allow you to sort the content of your .properties files.

Usage
=====

The example code below shows how to use the library.

Command line
------------
* clone the repository
* build jar file using maven
```
mvn package
```
* sort your file
```
java -jar target/property-file-sorter-1.0.0-SNAPSHOT.jar your/file/path.properties
```

Java API
--------

```java
InputStream stream = new FileInputStream("src/test/resources/test.properties");
    
PropertyFileParser parser = new PropertyFileParser(stream);
PropertyFile file = parser.parse();

file.serialize(System.out);
```

Algorithm
=========

An algorithm implemented in the library tries to discover groups of keys in the file and to sort each of them separately. Groups are discovered by the analysis of comments and new line characters. Below you can see an example of the algorithm execution.

Input file:
```
#first group
key.d=Value d
key.a=Value a

#second group
key.c=Value c
key.b=Value b
```

Sorted file:
```
# first group
key.a=Value a
key.d=Value d

# second group
key.b=Value b
key.c=Value c
```
