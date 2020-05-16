/*Sample implimentation of rate limiter dynamically changing its decision to accept or deny request
with actual load on the system*/
public class RateLimiter {
    int maxRequests=1000;
    int maxLoad=100;
    Long time_limit=1000L;
    Map<String,Queue<Long>> mapOfClientIds=null;
    public RateLimiter(int maxRequests,int maxLoad,long time_limit){
      this.maxRequests=maxRequests;
      this.maxLoad=maxLoad;
      this.time_limit=time_limit;
      mapOfClientIds=new HashMap<String,Queue<Long>>();
    }
    
    public boolean canAllow(String clientId,int currentLoadAsPercentage){
        int currentCapacity=1-currentLoadAsPercentage;
        if (!mapOfClientIds.containsKey(clientId)){
            Queue<Long> queue=new Dequeue<Long>();
            if (1>maxRequests*((currentCapacity*1.0)/maxLoad))){
                return false;
            }
            queue.offer(System.currentTimeMillis());
            mapOfClientIds.put(clientId,queue);
        }
        else {
            Queue<Long> queue=mapOfClientIds.get(clientId);
            if (!queue.isEmpty() && System.currentTimeMillis()-queue.peek()>=time_limit){
                queue.poll();
            }
            if (queue.size()+1>maxRequests*((currentCapacity*1.0)/maxLoad))){
                return false;
            }
            queue.offer(System.currentTimeMillis());
        }
        return true;    
    }
    
}
