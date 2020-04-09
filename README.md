# Treepat

Treepat is a language to recognize patterns in trees in a similar way as regular expressions recognize patterns in 
strings. Treepat includes analogous operators to regex union, concatenation, and closure, which are extended to the 
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

To run integration tests:
```bash
mvn verify 
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
Create a file with extension `.tp` that contains a Treepat expression, and a file `.tef` that contain the target tree.
After creating the files pass them as arguments to the `Main` class:
```bash
mvn exec:java -Dexec.mainClass="Main" -Dexec.args="my-expression-file.tp my-target-tree.tef" 
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[Apache License 2.0](https://github.com/Treepat/Treepat/blob/dev/LICENSE)
