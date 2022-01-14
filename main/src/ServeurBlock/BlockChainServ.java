package ServeurBlock;

import utils.Block;
import utils.BlockChainITF;
import utils.BlockHash;
import utils.BlockHashFactory;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class BlockChainServ implements BlockChainITF {
    private ArrayList<Block> listBlock;
    private Block lastBlock;
    private BlockHash blockHash;
    private Block currentBlockMined;
    private int currentConstraint;
    private Random stringGenerator;


    public BlockChainServ() {
        blockHash = BlockHashFactory.getInstance();
        listBlock = new ArrayList<>();
        lastBlock = null;
        stringGenerator = new Random();
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

    public int getCurrentConstraint(){
        return currentConstraint;
    }

    private void addSafeBlock(Block block){
        listBlock.add(block);
        buildNextBlock();
        lastBlock = block;
    }

    private void buildNextBlock(){
        int currentID;
        if(currentBlockMined == null){
            currentID = 0;
        }
        else{
            currentID = currentBlockMined.getId() + 1;
        }
        currentBlockMined = new Block(currentID);
        if(lastBlock != null) {
            currentBlockMined.setPreviousHash(lastBlock.getHash());
        }
        currentBlockMined.setConstraint(getCurrentConstraint());

        //todo make function to get next data
        byte[] array = new byte[7]; // length is bounded by 7
        stringGenerator.nextBytes(array);
        currentBlockMined.setData(new String(array, Charset.forName("UTF-8")));
    }


}
