package utils;



public class Block  implements Cloneable{
    private int id;
    private String previousHash;
    private String data;
    private String proofWork;
    private String constraint;
    private String hash;

    public Block(Integer id){
        setId(id);
    }

    private Block(Integer id, String previousHash,String data, String proofWork, String constraint, String hash){
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

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Block clone(){
        return new Block(getId(),getPreviousHash(),getData(),getProofWork(),getConstraint(),getHash());
    }
}
