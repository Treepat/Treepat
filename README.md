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

### Compile
To compile the source code use the following maven command:
```bash
mvn compile 
```

### Test
To make sure that everything was installed correctly run the tests by running:
```bash
mvn test 
```

### Run
The repository contains a sample input files. To run the interpreter use the following command:
```bash
mvn exec:java -Dexec.mainClass="Main" -Dexec.args="test.tp test.tef" 
```
Note that `test.tp` is a file that contains the Treepat expression and `test.tef` contains the target tree to find 
patterns.

This command should print:
```
A:1(0)
    B:2(2)
    C:3(3)
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
