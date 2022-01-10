package utils;

import java.util.ArrayList;

public interface BlockChainITF {
    public Boolean checkBlockChain();

    public Boolean tryToAddBlock(String proofWork);

    public Block getLastBlock();

    public ArrayList<Block> getList();

    public Block getCurrentBlockMined();

    public String getCurrentConstraint();


}


