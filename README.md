# Treepat

Treepat is a language to recognize patterns in trees in a similar way as regular expressions recognize patterns in 
strings. Treepat includes analogous expression.operators to regex union, concatenation, and closure, which are extended to the 
notion of trees.

## Installation

Follow these steps to install the project.

### Prerequisites

Make sure you have [maven](https://maven.apache.org/) installed. To make sure you have it installed run:
```bash
mvn --version
```

The project requires JDK 12.
```bash
Java version: 12.0.2, vendor: AdoptOpenJDK
```

### Compile
To compile the source code use the following maven command:
```bash
mvn compile 
```

### Test
To run unit tests:
```bash
mvn test 
```

### Run
The repository contains a sample set of input files. To run the interpreter use the following command:
```bash
mvn exec:java -Dexec.mainClass="Main" -Dexec.args="test.tp test.tef" 
```
Note that `test.tp` is a file that contains the Treepat expression and `test.tef` contains the target tree where the patterns will be matched.

This command should print:
```
A:1(0)
    B:2(1)
        C:3(2)
            E:4(3)
    D:6(5)
    E:7(6)
        F:8(7)
```

## Usage

### Bash
Create a file with extension `.tp` that contains a Treepat expression, and a file `.tef` that contain the target tree.
After creating the files pass them as arguments to the `Main` class:
```bash
mvn exec:java -Dexec.mainClass="Main" -Dexec.args="my-expression-file.tp my-target-tree.tef" 
```

### Library
You can use this project as a part of another project, for this you need to add Treepat dependency in the project 
`pom.xml:
```bash
<dependency>
    <groupId>com.github.treepat</groupId>
    <artifactId>Treepat</artifactId>
    <!-- Check the last version in Maven central repository -->
    <version>1.2.7</version>
</dependency>
```
In this example all the code will be in Kotlin.

First you need to create a `TreepatExpression`, in this case the `args[0]` contain the name of the file that contains 
the Treepat Expression, for more information of this file read the [wiki](https://github.com/Treepat/Treepat/wiki/Treepat-Expression-File):

```kotlin
val treepatExpression = TreepatExpression.createFromFile(args[0])
```

Then you need to create a ``TargetTree`` that represent the tree where you want to find the patterns, 
in this case the `args[1]` contain the name of the file that contains 
the tree, for more information of this file read the [wiki](https://github.com/Treepat/Treepat/wiki/Target-Tree-File):

```kotlin
val targetTree = DefaultTargetTree<DefaultTargetTreeNode>(args[1])
```

To find the patterns you have to code the method `findMatches` in `TargetTree`, this will return a 
`List<List<TargetTreeNode>>` that represent the matches found:
```kotlin
val functionResult = targetTree.findMatches(treepatExpression)
```
You can also get a call the method ``hasMatches`` that return `true` if the `TargetTree` contains any matches otherwise 
return ``false``.
```kotlin
val functionResult = targetTree.hasMatch(treepatExpression)
```

For see complete example you can [click here](https://github.com/Treepat/Treepat-Samples).

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[Apache License 2.0](https://github.com/Treepat/Treepat/blob/dev/LICENSE)
