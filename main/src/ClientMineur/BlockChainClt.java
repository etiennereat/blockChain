package ClientMineur;

import ClientMineur.UI.BlockChainUI;
import ClientMineur.UI.BlockPanel;
import utils.Block;
import utils.BlockChainITF;
import utils.BlockHash;
import utils.BlockHashFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BlockChainClt implements BlockChainITF, BlockChainUI {


    private ArrayList<Block> listBlock;
    private Block lastBlock;
    private BlockHash blockHash;
    private Block currentBlockMined;
    private BlockPanel panel;
    private volatile JFrame frame;
    public  long currentTry;


    public BlockChainClt(){
        blockHash = BlockHashFactory.getInstance();
        listBlock = new ArrayList<>();
        lastBlock = null;
    }
//---------------------------------------------------Controleur-------------------------------------------------------//

    @Override
    public Boolean checkBlockChain() {
        return false;
    }

    @Override
    public Boolean tryToAddBlock(String proofWork) {
        return false;
    }

    @Override
    public Block getLastBlock() {
        return lastBlock;
    }

    @Override
    public ArrayList<Block> getList() {
        return listBlock;
    }

    @Override
    public Block getCurrentBlockMined() {
        return currentBlockMined;
    }

    @Override
    public int getCurrentConstraint() {
        return 0;
    }

    public void setCurrentBlockMined(Block currentBlockMined){
        this.currentBlockMined = currentBlockMined;
    }

    public void bestWaySearchWorkProofCurrentBlockMined(){
        customeWaySearchWorkProofCurrentBlockMined(Runtime.getRuntime().availableProcessors());
    }

    public void customeWaySearchWorkProofCurrentBlockMined(int nbTrhread){
        reinitTry();
        for(int i =0; i< nbTrhread ; i++) {
            Thread searcher = new Thread() {
                public void run() {
                    Block copieBlock = currentBlockMined.clone();
                    long current;
                    String currentProofOfWork;
                    while (!currentBlockMined.isSolved()) {
                        current = nextTry();
                        currentProofOfWork = convertisseurIndiceToString(current);
                        copieBlock.setProofWork(currentProofOfWork);
                        blockHash.hash(copieBlock);
                        if (copieBlock.nb0inHash() >= currentBlockMined.getBestHash0size()) {
                            currentBlockMined.setBestHash0size(copieBlock.nb0inHash());
                            currentBlockMined.setHash(copieBlock.getHash());
                            showCurrentWork();
                            if(currentBlockMined.getBestHash0size() >= currentBlockMined.getConstraint()){
                                currentBlockMined.setSolved(true);
                                currentBlockMined.setProofWork(currentProofOfWork);
                                currentBlockMined.setHash(copieBlock.getHash());
                            }

                        }
                    }
                }
            };
            searcher.start();
        }
    }

    public  synchronized void reinitTry() {
        currentTry = 0;
    }

    public  synchronized long nextTry(){
        long tmp = currentTry;
        currentTry++;
        return tmp;
    }


    public String convertisseurIndiceToString(long indice){
        String s = new String();
        while(indice >= 1) {
            char tmp = (char) ('!' + (indice % 93));
            indice= indice /93;
            s = s.concat(String.valueOf(tmp));
        }
        return s;
    }

//-------------------------------------------------------UI-----------------------------------------------------------//
    @Override
    public synchronized void showCurrentWork() {
        if(frame == null){
            initUi();
        }
        panel.drawText();
    }

    private  synchronized void  initUi(){
        this.frame = new JFrame();
        Dimension dimension_screen = new Dimension(1200, 400);
        frame.setPreferredSize(dimension_screen);
        frame.setLayout(new BorderLayout());
        this.panel = new BlockPanel();
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.setBloc(currentBlockMined);
    }
}
