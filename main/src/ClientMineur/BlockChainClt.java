package ClientMineur;

import ServeurBlock.BlockChainServ;
import utils.Block;
import utils.BlockChainITF;
import utils.BlockHash;
import utils.BlockHashFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class BlockChainClt implements BlockChainITF, BlockChainUI {


    private ArrayList<Block> listBlock;
    private Block lastBlock;
    private BlockHash blockHash;
    private Block currentBlockMined;



    public BlockChainClt(){
        blockHash = BlockHashFactory.getInstance();
        listBlock = new ArrayList<>();
        lastBlock = null;
    }
//---------------------------------------------------Controleur-------------------------------------------------------//

    @Override
    public Boolean checkBlockChain() {
        return null;
    }

    @Override
    public Boolean tryToAddBlock(String proofWork) {
        return null;
    }

    @Override
    public Block getLastBlock() {
        return null;
    }

    @Override
    public ArrayList<Block> getList() {
        return null;
    }

    @Override
    public Block getCurrentBlockMined() {
        return null;
    }

    @Override
    public String getCurrentConstraint() {
        return null;
    }

//-------------------------------------------------------UI-----------------------------------------------------------//
    @Override
    public void showCurrentWork(BlockPanel panel) {

    }
}
