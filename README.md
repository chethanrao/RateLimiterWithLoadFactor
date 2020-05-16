# RateLimiterWithLoadFactor
This ratelimiter will deny new requests when the load on the system is high on 
top of the default max number of requests it can serve. The reason the load on the system is added
because usually the rate limit service is free and it must not consume bandwidth of another
service which does not have rate limit that is paid. 
