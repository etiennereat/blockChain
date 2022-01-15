import ClientMineur.UI.BlockPanel;
import org.junit.jupiter.api.*;
import utils.Block;

import javax.swing.*;

import java.awt.*;

public class TestBlockPanel {


    BlockPanel panel;
    Block bloc;
    @BeforeEach
    void init() {
        JFrame frame = new JFrame();
        Dimension dimension_screen = new Dimension(1200, 400);
        frame.setPreferredSize(dimension_screen);
        frame.setLayout(new BorderLayout());
        this.panel = new BlockPanel();
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        bloc = new Block(1);
        panel.setBloc(bloc);

    }

    @AfterEach
    void clean() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFontSize(){
        long text = 1;
        for(int nb = 0; nb< 62 ; nb++){
            text += text;
            bloc.setHash(Long.toString(text));
            bloc.setConstraint(6);
            panel.drawText();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    void testColor(){
        bloc.setHash("00001234045");
        bloc.setConstraint(6);
        this.panel.drawText();
    }
    @Test
    void testContaintBiggerThanTextLen(){
        bloc.setHash("00001234045");
        bloc.setConstraint(bloc.getHash().length() + 5);
        this.panel.drawText();
    }

    @Test
    void testContaintSmallerThanTextLen(){
        bloc.setHash("00001234045");
        bloc.setConstraint(bloc.getHash().length() - 5);
        this.panel.drawText();
    }

    @Test
    void testContaintSmallerThanText0(){
        bloc.setHash("00001234045");
        bloc.setConstraint(2);
        this.panel.drawText();
    }

    @Test
    void testContaint0(){
        bloc.setHash("00001234045");
        bloc.setConstraint(0);
        this.panel.drawText();
    }

    @Test
    void testWitout0(){
        bloc.setHash("123456789876543");
        bloc.setConstraint(6);
        this.panel.drawText();
    }

}
