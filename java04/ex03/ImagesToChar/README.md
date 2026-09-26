# ImagesToChar

## Compilation

From the ImagesToChar project root:

javac -cp "lib/*" -d target $(find src/java -name "*.java")

cp src/resources/it.bmp target/

cd target
jar xf ../lib/jcommander.jar
jar xf ../lib/JCDP-4.0.2.jar
cd ..

jar cfe target/images-to-chars-printer.jar fr._42.printer.app.Main -C target .

## Execution

java -jar target/images-to-chars-printer.jar --white RED --black GREEN

## External libraries

JCommander is used to process command-line arguments.

JCDP is used to display the image using colored output.

The external libraries are located in the lib directory and their classes are included in the final JAR.
