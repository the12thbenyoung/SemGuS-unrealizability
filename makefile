build:
	javac -cp .:lib/json-parser.jar src/ParseTest.java

run:
	java src/ParseTest

all:
	javac -cp .:lib/json-parser.jar src/ParseTest.java
	java src/ParseTest
