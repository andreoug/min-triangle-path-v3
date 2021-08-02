#!/bin/bash

#Testing A:
# cat <<EOF | java -jar ./target/min-triangle-path-1.0-SNAPSHOT.jar
# 1
# 1 2
# 1 2 3
# EOF

#Testing B:
#cat data.txt | java -jar ./target/min-triangle-path-1.0-SNAPSHOT.jar
cat data.sample.txt | java -Xmx2048m -jar ./target/min-triangle-path-1.0-SNAPSHOT.jar