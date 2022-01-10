package ServeurBlock;

import utils.Block;
import utils.BlockChainITF;
import utils.BlockHash;
import utils.BlockHashFactory;

import java.util.ArrayList;

public class BlockChainServ implements BlockChainITF {
    private ArrayList<Block> listBlock;
    private Block lastBlock;
    private BlockHash blockHash;
    private Block currentBlockMined;
    private String currentConstraint;


    public BlockChainServ() {
        blockHash = BlockHashFactory.getInstance();
        listBlock = new ArrayList<>();
        lastBlock = null;
        buildNextBlock();
    }


    public Boolean checkBlockChain() {
        return blockHash.checkHash(this) == -1;
    }

    public Boolean tryToAddBlock(String proofWork) {
        currentBlockMined.setProofWork(proofWork);
        blockHash.hash(currentBlockMined);
        if (blockHash.checkHash(currentBlockMined)) {
            addSafeBlock(currentBlockMined);
            return true;
        } else {
            return false;
        }
    }

    public Block getLastBlock() {
        return lastBlock;
    }

    public ArrayList<Block> getList() {
        return listBlock;
    }

    public Block getCurrentBlockMined(){
        return currentBlockMined.clone();
    }

    public String getCurrentConstraint(){
        return currentConstraint;
    }

    private void addSafeBlock(Block block){
        listBlock.add(block);
        lastBlock = block;
        buildNextBlock();
    }

    private void buildNextBlock(){
        //todo make function who give the next block setup
        Block nextBlock = new Block(currentBlockMined.getId());
        nextBlock.setPreviousHash(currentBlockMined.getHash());
        nextBlock.setConstraint(getCurrentConstraint());
    }


}
