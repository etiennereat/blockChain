package ClientMineur.UI;


import org.jetbrains.annotations.NotNull;
import utils.Block;

import javax.swing.*;
import java.awt.*;

import static java.lang.Integer.min;

public class BlockPanel extends JPanel {

    private String text;
    private String textNeedToHave0;
    private String textGreen;
    private String textRed;
    private String textGrey;

    private Block bloc;

    public void drawText(){
        this.repaint();
    }

    private void cutText(String text, int constraintSize){
        this.text = text;
        constraintSize = min(constraintSize,text.length());
        textGrey = text.substring(constraintSize);
        textNeedToHave0 = text.substring(0,constraintSize);
        String[] tmp = textNeedToHave0.split("^[0]*");
        if(tmp.length != 0) {
            textRed = tmp[tmp.length - 1];
            textGreen = textNeedToHave0.replace(textRed, "");
        }
        else{
            textGreen = textNeedToHave0;
            textRed = "";
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //hash

        cutText(bloc.getHash(),bloc.getConstraint());
        int fontSize = getWidth()/(text.length()+1);
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        g.setColor(new Color(50,150,50));
        g.drawString(textGreen,  (getWidth()- (text.length())* (int)(fontSize/1.5) )/2, (getHeight() + (int)(fontSize/1.5)) /2 );
        g.setColor(new Color(150,50,50));
        g.drawString(textRed,  (getWidth()- (text.length())* (int)(fontSize/1.5) )/2 + textGreen.length()*(int)(fontSize/1.5), (getHeight() + (int)(fontSize/1.5)) /2 );
        g.setColor(new Color(100,100,100));
        g.drawString(textGrey,  (getWidth()- (text.length())* (int)(fontSize/1.5) )/2 + textGreen.length()*(int)(fontSize/1.5) + textRed.length()*(int)(fontSize/1.5), (getHeight() + (int)(fontSize/1.5)) /2 );
        g.drawRect((getWidth()- (text.length())* (int)(fontSize/1.5) )/2,(getHeight() - fontSize) /2 , textGreen.length()*(int)(fontSize/1.5) + textRed.length()*(int)(fontSize/1.5),fontSize);

        //contrainte
        double tmp = Math.pow(16,bloc.getConstraint());
        g.setColor(new Color(0,0,0));
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.drawString(bloc.getConstraint()+" contraintes : "+tmp+"$",0,40);

        //Bloc id
        g.drawString("Bloc n°"+bloc.getId(),getWidth() - ((7 + Integer.toString(bloc.getId()).length())*24),40);

    }

    public void setBloc(Block bloc) {
        this.bloc = bloc;
    }

    public Block getBloc() {
        return bloc;
    }
}