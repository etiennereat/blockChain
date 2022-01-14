import ClientMineur.UI.BlockPanel;
import org.junit.jupiter.api.*;

import javax.swing.*;

import java.awt.*;

public class TestBlockPanel {


    BlockPanel panel;
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
        int text = 1;
        for(int nb = 0; nb< 25 ; nb++){
            text += text;
            panel.drawText(Integer.toString(text),2);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    void testColor(){
        String text ="00001234045";
        this.panel.drawText(text,6);
    }
    @Test
    void testContaintBiggerThanTextLen(){
        String text ="00001234045";
        this.panel.drawText(text,text.length() + 5);
    }

    @Test
    void testContaintSmallerThanTextLen(){
        String text ="00001234045";
        this.panel.drawText(text,text.length() - 5);
    }

    @Test
    void testContaintSmallerThanText0(){
        String text ="00001234045";
        this.panel.drawText(text, 2);
    }

    @Test
    void testContaint0(){
        String text ="00001234045";
        this.panel.drawText(text, 0);
    }

    @Test
    void testWitout0(){
        String text ="123456789876543";
        this.panel.drawText(text, 6);
    }
    @Test
    void testWithConstraint(){
        String text ="123456789876543";
        panel.setContrainte(6);
        this.panel.drawText(text);
    }

}
