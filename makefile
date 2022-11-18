build:
	mvn package
run:
	java -cp target/semgus_unrealizability-1.0-SNAPSHOT.jar edu.wisc.semgus.App
all:
	mvn package
	java -cp target/semgus_unrealizability-1.0-SNAPSHOT.jar edu.wisc.semgus.App
