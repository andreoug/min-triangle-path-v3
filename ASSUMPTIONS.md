#### Assumptions - Notes

1. we assume that we have top one and only minimum path solution
1. The input data are following the guidelines definition
    * they are integers.
    * the triangle is always equilateral and there are no missing numbers or letters. 
1. Node class
    * On each node, we do not use children just parent node.
    * Each rows children, named as latestChildren is located in TriangleGraph class - for the time being.
1. triangleGraph class
    * [be_data_big](./be_data_big.txt) last row's should produce 2^2000 children
    * [be_data_small](./be_data_small.txt) last row's should produce 2^50 children
    * [data.sample](./data.sample.txt) last row's is producing 2^20 children
    
#### Next Steps
1. Implement appropriate partitioning approach for input files greater than ~26-28 lines 
1. As multi-threaded parallel streams are comments, implement Spring's workers to handle the load of partitioned input.
1. Implement an approach to store and load the java objects on disk to overcome current heapsize bottleneck.  
1. clean pom file
1. add log instead of System.out's
1. Break [TriangleGraph.buildTreeLayer] method to minimize current memory footprint of objects *latestChildren*, *children*, and *groupByValueMap*
