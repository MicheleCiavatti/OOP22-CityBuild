package it.unibo.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Menu extends ScreenAdapter{

    private int index = 0;
    private final String[] imageList = {"buildings1", "buildings2", "buildings3"};
    private String selectedBuildingName;
    private static final String EXTENSION = ".png";
    private static final int NUMBUTTONS = 3;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 100;
    private Stage stage;

    @Override
    public void show() {

        //ora mostro questa schermata

        //apre un nuovo stage sopra quello vecchio
        stage = new Stage();


        Texture iconTexture = new Texture("buildings1.png");
        TextureRegion icon = new TextureRegion(iconTexture);
        ImageButton button = new ImageButton(new TextureRegionDrawable(icon));

        button.setName("buildings1");
        stage.addActor(button);

        //in alto a sinistra
        button.setPosition(0, Gdx.graphics.getHeight() - BUTTON_HEIGHT);
        button.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectedBuildingName = "buildings1"+EXTENSION;
                System.out.println("Selected building: " + selectedBuildingName);
            }
        });

        //mostra gli elementi dello stage
        stage.draw();


    }



    private void roundButtonList(int param){

        Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/scroll.ogg"));
        sound.play();


        if (param == 1){
            index++;
            if (index == NUMBUTTONS){
                index = 0;
            }
        } else if (param == -1){
            index--;
            if (index == -1){
                index = NUMBUTTONS-1;

            }
        }
        refreshButtonList();
        
        selectButton(index);

    }

    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            roundButtonList(1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            roundButtonList(-1);
        }

        this.stage.act(delta);
        this.stage.draw();

    }

    private void refreshButtonList(){
        stage.getActors().removeRange(0, stage.getActors().size-1);
    }




    private void selectButton(int index){
        //stage.clear();
        String buildingPath = imageList[index] + EXTENSION;
        selectedBuildingName = imageList[index] + EXTENSION;
        System.out.println("Selected building: " + selectedBuildingName);
        Texture iconTexture = new Texture(buildingPath);
        TextureRegion icon = new TextureRegion(iconTexture);
        ImageButton button = new ImageButton(new TextureRegionDrawable(icon));
        button.setName(imageList[index]);
        stage.addActor(button);
        button.setPosition(0, 0);

        //posizine in alto a sinistra
        button.setPosition(0, Gdx.graphics.getHeight() - BUTTON_HEIGHT);

        /* posizione centro
        button.setPosition((Gdx.graphics.getWidth() - BUTTON_WIDTH) / 2, (Gdx.graphics.getHeight() - BUTTON_HEIGHT) / 2);*/
        button.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    //render
    public void dispose() {
        stage.dispose();
    }

    public void render() {
        stage.draw();
    }

    public void create() {
        stage = new Stage();
        selectButton(index);
    }


    

}
