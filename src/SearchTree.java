

public class SearchTree {

    private SearchTree leftSon;

    private SearchTree rightSon;

    private SearchTree father;

    private String nomImg;
    private double valeurSimilarité;

    private int hauteur;
    private int index;

    public SearchTree(){ // initialiser le raçine
        this.father = this;
        this.valeurSimilarité = 0;
        this.nomImg = "";
        this.hauteur = 0;
    }
    public SearchTree(SearchTree father, double valeurSimilarité, String nomImg, int index){
        this.father = father;
        this.valeurSimilarité = valeurSimilarité;
        this.index = index;
        this.nomImg = nomImg;
        this.hauteur = father.hauteur + 1 ;
    }

    public void setLeftSon(ImageOperations img , int index){
        this.leftSon = new SearchTree(this , this.valeurSimilarité + img.getValeurSimilarite(),img.getNom(), index);
    }
    public void setRightSon(){
        this.rightSon = new SearchTree(this , this.valeurSimilarité , this.nomImg , -1);
    }

    public SearchTree getLeftSon(){
        return this.leftSon;
    }
    public SearchTree getRightSon(){
        return this.rightSon;
    }
    public SearchTree getFather(){
        return this.father;
    }

    public String getNomImg() {
        return nomImg;
    }

    public void setNomImg(String nomImg) {
        this.nomImg = nomImg;
    }

    public double getValeurSimilarité() {
        return valeurSimilarité;
    }

    public void setValeurSimilarité(double valeurSimilarité) {
        this.valeurSimilarité = valeurSimilarité;
    }

    public boolean isRoot(){
        return this.hauteur==0;
    }
    public int getIndexObjet() {
        return this.index;
    }

}