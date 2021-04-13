# Cache Management System

The objective of this system is to enable user to utilise a generic framework and 
define their own flavour of cache (customised cacheStorage and eviction policies).

**Current Implementation:**

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
  
Future use cases can include (but not limited to):
* Multi Tier Cache Storage with overall storage capacity
* Multi Tier Cache Storage with different storage capacities
* Least Frequently Used (LFU) eviction policy