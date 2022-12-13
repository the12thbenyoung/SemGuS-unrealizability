#!/bin/bash
for b in */ ; do
    echo "$b"
	java -cp ../target/semgus_unrealizability-1.0-SNAPSHOT.jar edu.wisc.semgus.App $b/*.json
done
