package utils;

public class BlockHashFactory {

    private static BlockHash INSTANCE;

    private BlockHashFactory() {
    }

    public static BlockHash getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BlockHash();
        }

        return INSTANCE;
    }
}
