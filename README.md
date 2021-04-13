# Cache Management System

The objective of this system is to enable user to utilise a generic framework and 
define their own flavour of cache (customised cacheStorage and eviction policies).

#### Current Implementation:

* A generic class `Cache.java` which can be initialised using a cacheStorage class 
(eg: `SingleTierStorage.java`) and an eviction policy class 
(eg: `LRUEvictionPolicy.java`, `FIFOCacheEvictionPolicy.java`)

* `CacheStorage.java` is an interface with generic `<K, V>` and every implementation 
must implement:
  * `void add(K k, V v)`
  * `V get(K k)`
  * `List<K> getAll()`
  * `V remove(K k)`
  
* `CacheEvictionPolicy.java` is an interface with generic `<K>` and every implementation
much implement:
  * `void include(K k)`
  * `void access(K k)`
  * `K evict()`
  
#### Future use cases can include (but not limited to):
* Multi Tier Cache Storage with overall storage capacity
* Multi Tier Cache Storage with different storage capacities
* Least Frequently Used (LFU) eviction policy

#### Examples:
* Single Tier Cache with initial capacity = 5, eviction policy = FIFO
```
put("Key1", "Value1");
put("Key2", "Value2");
put("Key3", "Value3");
put("Key4", "Value4");
get("Key1"); // Key1 accessed, makes no difference in eviction
get("Key3"); // Key3 accessed, makes no difference in eviction
put("Key5", "Value5");
put("Key6", "Value6"); // Key1 will be removed to put Key6
put("Key7", "Value7"); // Key2 will be removed to put Key7

Cache Contents: [Key3, Key4, Key5, Key6, Key 7]
``` 

* Single Tier Cache with initial capacity = 5, eviction policy = LRU
```
put("Key1", "Value1");
put("Key2", "Value2");
put("Key3", "Value3");
put("Key4", "Value4");
get("Key1"); // Key1 accessed, makes it most recently used
get("Key3"); // Key3 accessed, makes it most recently used
put("Key5", "Value5");
put("Key6", "Value6"); // Key2 will be removed to put Key6
put("Key7", "Value7"); // Key4 will be removed to put Key7

Cache Contents: [Key1, Key3, Key5, Key6, Key 7]
``` 