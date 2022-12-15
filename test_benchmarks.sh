#!/bin/bash
for cat in benchmarks/*/ ; do
    for bdir in $cat*/ ; do
        b=`expr match "$bdir" '.*\(/.*/\)'`
        b1=${b#/}
        b2=${b1%/}
        java -cp target/semgus_unrealizability-1.0-SNAPSHOT.jar edu.wisc.semgus.App $bdir$b2.json > $bdir$b2-output.txt
    done
done
