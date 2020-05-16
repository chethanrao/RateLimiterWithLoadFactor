/*Sample implimentation of rate limiter dynamically changing its decision to accept or deny request
with actual load on the system.This code is not compiled yet. Just wrote an outline implementation.*/
public class RateLimiter {
    int maxRequests=1000;
    float maxCapacityPercent=1;
    Long time_limit=1000L;
    Map<String,Queue<Long>> mapOfClientIds=null;
    public RateLimiter(int maxRequests,int maxCapacityPercent,long time_limit){
      this.maxRequests=maxRequests;
      this.maxCapacityPercent=maxCapacityPercent;
      this.time_limit=time_limit;
      mapOfClientIds=new HashMap<String,Queue<Long>>();
    }
    
    public boolean canAllow(String clientId,int currentLoadAsPercentage){
        float currentCapacity=1-currentLoadAsPercentage;
        long currentMillis=System.currentTimeMillis();

        if (!mapOfClientIds.containsKey(clientId)){
            Queue<Long> queue=new Dequeue<Long>();
            if (1>maxRequests*((currentCapacity*1.0)/maxCapacityPercent))){
                return false;
            }
            queue.offer(currentMillis);
            mapOfClientIds.put(clientId,queue);
        }
        else {
            Queue<Long> queue=mapOfClientIds.get(clientId);
            while (!queue.isEmpty() && currentMillis-queue.peek()>=time_limit){
                queue.poll();
            }
            if (queue.size()+1>maxRequests*((currentCapacity*1.0)/maxCapacityPercent))){
                return false;
            }
            queue.offer(currentMillis);
        }
        return true;    
    }
    
}
