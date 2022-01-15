package utils;



public class Block  implements Cloneable{
    private int id;
    private String previousHash;
    private String data;
    private String proofWork;
    private int constraint;
    private String hash;
    private int bestHash0size;

    private boolean solved = false;

    public Block(Integer id){
        setId(id);
    }

    private Block(Integer id, String previousHash,String data, String proofWork, int constraint, String hash){
        setId(id);
        setConstraint((constraint));
        setData((data));
        setHash((hash));
        setProofWork((proofWork));
        setPreviousHash((previousHash));
    }

    private void setId(Integer id){
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getProofWork() {
        return proofWork;
    }

    public void setProofWork(String proofWork) {
        this.proofWork = proofWork;
    }

    public int getConstraint() {
        return constraint;
    }

    public void setConstraint(int constraint) {
        this.constraint = constraint;
    }

    public String getHash() {
        return hash;
    }

    public synchronized void setHash(String hash) {
        this.hash = hash;
    }

    public synchronized boolean isSolved() {
        return solved;
    }

    public synchronized void setSolved(boolean solved) {
        this.solved = solved;
    }

    public synchronized int getBestHash0size() {
        return bestHash0size;
    }

    public synchronized void setBestHash0size(int bestHash0size) {
        this.bestHash0size = bestHash0size;
    }

    public int nb0inHash(){
        if(getHash() == null){
            return 0;
        }
        else{
            for(int i = 0; i<constraint ; i++){
                if(hash.charAt(i)!= '0'){
                    return i;
                }
            }
            return constraint;
        }
    }

    public Block clone(){
        return new Block(getId(),getPreviousHash(),getData(),getProofWork(),getConstraint(),getHash());
    }
}
