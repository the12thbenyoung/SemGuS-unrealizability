#!/bin/bash
for cat in benchmarks/*/ ; do
    for bdir in $cat*/ ; do
        b=`expr match "$bdir" '.*\(/.*/\)'`
        b1=${b#/}
        b2=${b1%/}
        parser/semgus-parser --format json --mode batch $bdir$b2.sl > $bdir$b2.json
    done
done
