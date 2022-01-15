import ClientMineur.BlockChainClt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Block;

public class TestBlockChainClt {

    @Test
    public void testConvertisseur(){
        String str;
        long tmp;
        BlockChainClt bloc = new BlockChainClt();
        for(long i = 0; i< 10000; i++){
            str = bloc.convertisseurIndiceToString(i);
            tmp=0;
            for(int  j=0; j < str.length();j++){
                tmp += (double) (Math.pow(93,j) * (str.charAt(j)-'!'));
            }
            Assertions.assertEquals(i,tmp);
        }
    }

    //8 -> 21 min 46 sec
    //7 ->  1 min 21 sec
    @Test
    public void testFindSolution(){
        BlockChainClt blocClt = new BlockChainClt();
        Block bloc = new Block(1);
        bloc.setConstraint(7);
        bloc.setData("test");
        bloc.setPreviousHash("");
        blocClt.setCurrentBlockMined(bloc);
        blocClt.bestWaySearchWorkProofCurrentBlockMined();
        while(!blocClt.getCurrentBlockMined().isSolved()){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("solution trouvé en : "+blocClt.currentTry);
        System.out.println("La solution : "+blocClt.getCurrentBlockMined().getProofWork());
        System.out.println("le hash : "+blocClt.getCurrentBlockMined().getHash());
    }
}