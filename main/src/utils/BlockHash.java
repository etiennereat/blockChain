package utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BlockHash {

    public void hash(@NotNull Block block){
        block.setHash(computeHash(block));
    }

    public Boolean checkHash(@NotNull Block block){

        return block.getHash().equals(computeHash(block));
    }

    public Integer checkHash(@NotNull BlockChainITF blockChain){
        if(blockChain.getLastBlock() != null) {
            Integer lastIDblock = blockChain.getLastBlock().getId();
            Integer nextBlockIndex = 1;
            ArrayList<Block> listBlock = blockChain.getList();
            for (Block current : listBlock) {
                if (current.getId() != lastIDblock) {
                    // if current block have a wrong workProof
                    if(!(checkHash(current))){
                        return current.getId();
                    }
                    // if next block have a different hash previous between current
                    if(current.getHash() != listBlock.get(nextBlockIndex).getPreviousHash()){
                        return  listBlock.get(nextBlockIndex).getId();
                    }
                }
                nextBlockIndex++;
            }
        }
        return -1;
    }

    private String computeHash(@NotNull Block block){
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(block.getPreviousHash()+block.getData()+block.getProofWork());
    }
}
