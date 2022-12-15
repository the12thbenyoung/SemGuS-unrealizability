This is an unrealizability and semantic equivalence prover for [SemGuS](semgus.org) problems in the theory of conditional integer linear arithmetic (CLIA).

Requires local dependencies `lib/cvc5-1.0.2.jar` and `lib/libz3-4.8.8.jar`, which can be obtained from [here](https://cvc5.github.io/docs/cvc5-1.0.2/installation/installation.html) and [here](https://mvnrepository.com/artifact/com.microsoft.z3/libz3.java.linux/4.8.8), respectively.

To build, execute `mvn package` or `make build`.
To run, execute `make run`. The makefile defaults to evaluating `tmp/parsed_semgus.json`

To generate json files from `.sl` SemGuS files in benchmarks, run `./parse_benchmarks.sh`.
To evaluate all benchmarks (which writes the output to text files within each benchmark's directory), run `./test_benchmarks.sh`.