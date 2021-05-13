package connectfour.gui;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CellButton extends Button {

    private static final Image PLAY_EMPTY = new Image("/resources/noplayer.png");
    private static final Image PLAY_PLAYER1 = new Image("/resources/player1.png");
    private static final Image PLAY_PLAYER2 = new Image("/resources/player2.png");

    private final ImageView imageView;

    // constructor class
    public CellButton() {
        this.imageView = new ImageView(PLAY_EMPTY);
        this.setGraphic(imageView);
    }

    public void setTic(){
        this.imageView.setImage(PLAY_PLAYER1);
    }

    public void setTac(){
        this.imageView.setImage(PLAY_PLAYER2);
    }
}




