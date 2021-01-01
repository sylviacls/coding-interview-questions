/**
 * Token Bucket Algorithm for a Rate Limiter Application
 */
public class TokenBucket {
    private final long maxBucketSize;
    private final long refillRate;

    private double currentBucketSize;
    private long lastRefillTimeStamp;

    public TokenBucket(long maxBucketSize, long refillRate) {
        this.maxBucketSize = maxBucketSize;
        this.refillRate = refillRate;

        this.currentBucketSize = maxBucketSize;
        this.lastRefillTimeStamp = System.nanoTime();
    }

    public synchronized boolean allowRequest(int tokens) {
        // first, we refill bucket with tokens accumulated since the last call
        refillBucket();
        if (this.currentBucketSize > tokens) {
            this.currentBucketSize -= tokens;
            return true;
        }
        return false; // not enough tokens, request is throttled
    }

    private void refillBucket() {
        long currentTime = System.nanoTime();
        double newTokens = (currentTime - this.lastRefillTimeStamp) * refillRate / 1e9; // conversion from nanoseconds
                                                                                        // to seconds.
        // number of tokens should never exceed maximum capacity
        currentBucketSize = Math.min(currentBucketSize + newTokens, maxBucketSize);
        lastRefillTimeStamp = currentTime;
    }

}
