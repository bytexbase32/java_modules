        # ImagesToChar

## Build

From the `ImagesToChar` project root:

```bash
mkdir -p target
javac -d target src/java/fr/_42/printer/app/Main.java src/java/fr/_42/printer/logic/ImageConverter.java
cp src/resources/it.bmp target/
jar cfm target/images-to-chars-printer.jar src/manifest.txt -C target fr -C target it.bmp
```

## Run

```bash
java -jar target/images-to-chars-printer.jar
```

The program asks for characters representing white and black pixels.

## JAR contents

The JAR contains:

* Compiled `.class` files
* `it.bmp`
* Manifest with `fr._42.printer.app.Main` as the entry point
