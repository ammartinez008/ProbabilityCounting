# ProbabilityCounting

## Contains multiple algorithms that efficiently handle counting problems such as cardinality, and set membership

### Counting Alogrithms
#### Bloom Filter
will check set membership of an element with only a fraction of the memory from the set. This is a probablistic data structure and will guarantee if an element is not contained in the set

#### HyperLogLog 
TODO: HyperLogLog generates the cardinality of a set with only using a tiny amount of memory

#### Cardinality Hash Counter
- standard Hash implementation to keep count of the cardinality of a set. This is mainly used to compare the performance with the other algorithms

### Implementation
These algorithms will be using the Strategy Pattern to keep a nice organization when selecting which algorithm to use.

### Utils
- contains some custom hashing methods, and will eventually contain more sophisticated methods such as google's guava


#### How to Use
- Scheduler is the class that runs the main. runLinear will call the linear parser, and runSynchronized will call the synchronized parser. TODO: add seperate method for ThreadedParser.


#### Contributors
Alex Martinez
