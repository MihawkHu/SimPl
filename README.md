## SimPL

An interpreter for the programming language SimPL (pronounced simple).  

SimPL is a simplified dialect of ML, which can be used for both functional and imperative programming. This repository is based on the skeleton provided by course CS383.  

### Dependencies  
* *lib/java-cup-11a-runtime.jar*  
* *src/simpl/parser/java-cup-11a.jar*  
* *src/simpl/parser/JFlex.jar*  

### Functions  
* Lexical analyser  
* Syntax analyser  
* Type checking  
* Full evaluation  
* Polymorphic type  
* Garbage collection  
* Part of lazy evaluation  

### How to use  
* Given the program written in SimPL and add it to *\src\simpl\interpreter*, then run the project.  
* Make the project to generate jar file and using  
```
$: java -jar xxx.jar doc/examples/xxx.spl 
```