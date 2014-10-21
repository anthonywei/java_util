
public class IdGenerator {

    /**
     * 2000-01-01 00:00:00.000
     */
    private final static long base_timestamp = 946656000000l;

    /**
     * one byte number to indicate which server the message generated
     */
    private final byte serverId;

    private long lastTimestamp;
    private byte currIncrement;

    public IdGenerator(byte serverId) {
        this.serverId = serverId;
    }

    /**
     * Id = ServerId[1 Byte] + Timestamp[6 Bytes] + Increment[1 Byte]
     * 
     * @return makes a sense: next id always greater than last.
     */
    public synchronized long getNextId() {

        long t = System.currentTimeMillis() - base_timestamp;

        if (t < lastTimestamp) {
            t = lastTimestamp;
        }

        if (t == lastTimestamp) {
            if ((currIncrement & 0xff) == 0xff)
                t++;
            else
                currIncrement++;
        }

        if (t > lastTimestamp) {
            lastTimestamp = t;
            currIncrement = 0;
        }

        return ((0x00000000000000ffl & serverId) << 56
                | (0x0000ffffffffffffl & t) << 8 | (0x00000000000000ffl & currIncrement));
    }
}
