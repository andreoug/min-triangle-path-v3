#!/bin/bash

#Testing A:
# cat <<EOF | java -jar ./target/min-triangle-path-1.0-SNAPSHOT.jar
# 1
# 1 2
# 1 2 3
# EOF

#Testing B:
#cat data.sample.txt | java -Xmx2048m -jar ./min-triangle-path-1.0-SNAPSHOT.jar

#Testing C:
#cat be_data_small.txt | java  -Xmx2048m -jar ./min-triangle-path-1.0-SNAPSHOT.jar

#Testing D:
cat be_data_big.txt | java  -Xmx2048m -jar ./min-triangle-path-1.0-SNAPSHOT.jar
