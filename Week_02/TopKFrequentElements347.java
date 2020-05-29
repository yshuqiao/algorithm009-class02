import java.util.HashMap;
import java.util.PriorityQueue;

public class TopKFrequentElements347 {
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {

            if(k==nums.length){
                return nums;
            }
            HashMap<Integer,Integer> count = new HashMap<>();
            for(int num:nums){
                count.put(num,count.getOrDefault(num,0)+1);
            }

            PriorityQueue<Integer> heap = new PriorityQueue<Integer>((o1, o2)->count.get(o1)-count.get(o2));

            for(int num:count.keySet()){
                heap.add(num);
                if(heap.size()>k){
                    heap.poll();
                }
            }

            int[] res = new int[k];
            for(int i=k-1;i>=0;i--){
                res[i]=heap.poll();
            }
            return res;
        }
    }

}
