import java.util.HashMap;
import java.util.Map;

public class LruCache146 {
    //java:LRU缓存机制
    //来源：https://leetcode-cn.com/problems/lru-cache/solution/lruhuan-cun-ji-zhi-by-leetcode-solution/
    //时间复杂度：对于put和get都是O(1)。
    //空间复杂度：O(capacity)，因为哈希表和双向链表最多存储capacity+1个元素。
    class LRUCache {
        class  DLinkedNode{
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;
            public DLinkedNode(){}
            public DLinkedNode(int _key,int _value){
                key = _key;value=_value;
            }
        }
        private Map<Integer,DLinkedNode> cache =  new HashMap<Integer,DLinkedNode>();
        private int size;
        private int capacity;
        private DLinkedNode head,tail;

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            //使用伪头部和伪尾部节点
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if(node==null){
                return -1;
            }
            // 如果key存在，先通过哈希表定位，再移到头部
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                //如果key不存在，创建一个新的节点
                DLinkedNode newNode = new DLinkedNode(key, value);
                //添加进哈希表
                cache.put(key, newNode);
                //添加至双向链表的头部
                addToHead(newNode);
                ++size;
                if (size > capacity) {
                    //如果超出容量，删除双向链表的尾部节点
                    DLinkedNode tail = removeTail();
                    //删除哈希表中对应的项
                    cache.remove(tail.key);
                    --size;
                }
            } else {
                //如果key存在，先通过哈希表定位，再修改value，并移到头部
                node.value = value;
                moveToHead(node);
            }
        }

        private void addToHead(DLinkedNode node){
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }
        private void removeNode(DLinkedNode node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        private void moveToHead(DLinkedNode node){
            removeNode(node);
            addToHead(node);
        }
        private DLinkedNode removeTail(){
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }
    }


}
