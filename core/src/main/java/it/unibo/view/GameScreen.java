package it.unibo.view;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import it.unibo.controller.api.Controller;
import it.unibo.model.api.Resource;

public class GameScreen extends ScreenAdapter {

    private static final String SOUND_FOLDER = "sounds" + File.separator;
    private static final String IMAGE_FOLDER = "images" + File.separator;
    private static final Rectangle NULL_RECTANGLE = new Rectangle(0, 0, 0, 0);
    private static final String EXTENSION = ".png";
    //The game is structured in cycles: at the end of a cycle, it checks for new citizens and updates the resources
    private static final float CYCLE_DURATION_SECONDS = 3; 

    private final Controller controller;
    private final Table tablePlayer;
    private Map<Resource, Integer> resources;
    private final Music theme;
    private final ShapeRenderer shapeRenderer;
    private final Map<Rectangle, Image> buildings;
    private final Skin skin;
    private final Dialog constructionFailed;
    private final Dialog upgradeFailed;
    private final Label constructionLabel;
    private final Label upgradeLabel;
    private final GlyphLayout layout;
    private final Stage stage;
    private final Rectangle border;
    private final Label labelResources;
    private Optional<Rectangle> selected; //The building that the user selected from the icon menù to build.
    private float cycle;

    private int index = 0;
    private final String[] imageList = {"Depuratoricon", "Forgeicon", "Foundryicon", "Houseicon", "Lumber_refinaryicon", "Mineicon", 
        "Mineral_stationicon", "Power_planticon", "Quantum_reactoricon", "Skyscrapericon", "Ultrafiltration_complexicon", "Woodcuttericon"};
    private final Table tableBuildings;

    public GameScreen(final Controller controller) {
        this.controller = controller;
        this.skin = new Skin(Gdx.files.internal("skin_flatEarth" + File.separator + "flat-earth-ui.json"));
        this.tablePlayer = new Table(this.skin);
        this.tableBuildings = new Table(this.skin);
        this.labelResources = new Label("", this.skin);
        //Setting up the tablePlayer that contains the resources in possesion of the player
        this.tablePlayer.add(this.labelResources);
        this.updateTablePlayer();
        this.layout = new GlyphLayout();
        this.theme = Gdx.audio.newMusic(Gdx.files.internal(SOUND_FOLDER + "Chill_Day.mp3"));
        this.buildings = new HashMap<>();
        this.shapeRenderer = new ShapeRenderer();
        this.selected = Optional.empty();
        this.constructionFailed = new Dialog("Warning", this.skin);
        this.upgradeFailed = new Dialog("Warning", this.skin);
        this.constructionLabel = new Label("Building label", this.skin);
        this.upgradeLabel = new Label("Upgrade label", this.skin);
        this.stage = new Stage(new ScreenViewport());
        this.border = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(new GameProcessor());
        this.cycle = 0;
    }

    /**{@inheritDoc} */
    @Override
    public void show() {
        this.startMusic();
        this.constructionFailed.hide();
        this.constructionFailed.text("Wrong position or not enough resources");
        this.upgradeFailed.hide();
        this.upgradeFailed.text("Not enough resources or building already upgraded");
        this.stage.addActor(this.constructionFailed);
        this.stage.addActor(this.upgradeFailed);
        this.stage.addActor(this.constructionLabel);
        this.stage.addActor(this.tablePlayer);
        this.stage.addActor(this.upgradeLabel);
        this.tablePlayer.setFillParent(true);
        this.tablePlayer.top().right();
        this.setColorLabel(this.upgradeLabel, Color.BROWN);

        this.constructionLabel.setFontScale(1.2f);
        tableBuildings.setFillParent(true);
        tableBuildings.top().left();
        this.stage.addActor(tableBuildings);
        this.selectButton(this.index);
    }

    /**{@inheritDoc} */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        this.cycle += delta;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        drawRectangle(this.selected.orElse(NULL_RECTANGLE));
        shapeRenderer.end();
        if (this.cycle >= CYCLE_DURATION_SECONDS) {
            this.cycle = 0;
            this.controller.doCycle();
            this.updateTablePlayer();
        }
        this.stage.act(delta);
        this.stage.draw();
    }

    /**{@inheritDoc} */
    @Override
    public void dispose() {
        this.shapeRenderer.dispose();
        this.theme.dispose();
        this.stage.dispose();
        this.skin.dispose();
    }

    /*This method is called at the end of every cycle of the game to update the resources and citizens in town.
     * It is also called when the player spends resources to create or upgrade buildings.
     */
    private void updateTablePlayer() {
        System.out.println("Cycling");
        this.resources = this.controller.getPlayerResources();
        this.labelResources.setText(this.computeTextResources(this.resources) + 
            Resource.CITIZEN + ": " + this.controller.getCitizensInTown() + "/" + this.resources.get(Resource.CITIZEN));
    }

    //Given a map of resources, returns a string containing the resources with the respective value (except for CITIZEN)
    private String computeTextResources(final Map<Resource, Integer> map) {
        final StringBuilder str = new StringBuilder();
        map.entrySet().stream()
            .filter(entry -> entry.getKey() != Resource.CITIZEN)
            .forEach(entry -> str.append(entry.getKey() + ": " + entry.getValue() + "\n"));
        return str.toString();
    }

    //A method to set the labels dimensions based on the string they contain
    private void setLabelDimensions(final Label l) {
        this.layout.setText(l.getStyle().font, l.getText());
        l.setWidth(this.layout.width);
        l.setHeight(this.layout.height);
    }

    private void startMusic() {
        this.theme.play();
        this.theme.setVolume(0.25f);
        this.theme.setOnCompletionListener(Music::play);
    }

    private void drawRectangle(final Rectangle rectangle) {
        this.shapeRenderer.setColor(this.isValidPosition(rectangle)
            ? Color.GREEN
            : Color.RED);
        this.shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    /*A position is not valid when overlaps with another building or when out of the screen. */
    private boolean isValidPosition(final Rectangle rectangle) {
        return buildings.keySet().stream().noneMatch(rect -> rect.overlaps(rectangle))
            && this.border.contains(rectangle);
    }

    //A method for coloring the background of labels
    private void setColorLabel(final Label l, final Color c) {
        final Pixmap labelColor = new Pixmap((int) l.getWidth(), (int) l.getHeight(), Pixmap.Format.RGB888);
        labelColor.setColor(c);
        labelColor.fill();
        l.getStyle().background = new Image(new Texture(labelColor)).getDrawable();
    }

    private void selectButton(int index){

        //crea un pane con un bottone
        tableBuildings.clear();
        String buildingPath = IMAGE_FOLDER + imageList[index] + EXTENSION;
        Texture iconTexture = new Texture(buildingPath);
        TextureRegion icon = new TextureRegion(iconTexture);
        ImageButton button = new ImageButton(new TextureRegionDrawable(icon));
        button.setName(imageList[index].replace("icon", "").replace("_", " "));
        this.constructionLabel.setText(button.getName());
        this.setLabelDimensions(this.constructionLabel);
        tableBuildings.add(button).pad(5);
        tableBuildings.add(this.constructionLabel);
        //posiziona la tabella in alto a sinistra rispetto allo schermo
    }

    private class GameProcessor extends InputAdapter {

        private static final int RECT_WIDTH = 200;
        private static final int RECT_HEIGHT = 200;
        private final float DURATION_WARNINGS = 2f;
        private final String ICON_SUFFIX = "icon";

        private final Sound selection;
        private final Sound destruction;
        private final Sound construction;
        private final Sound wrong;
        private final Sound scroll;
        private final Sound upgrading;
        private String selectedName;
        private boolean pressingShift;
        private boolean pressingCtrl;

        public GameProcessor() {
            this.selection = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "select_building.ogg"));
            this.destruction = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "destruction.ogg"));
            this.construction = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "construction.ogg"));
            this.wrong = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "wrong1.ogg"));
            this.scroll = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "scroll.ogg"));
            this.upgrading = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "upgrade.ogg"));
            this.pressingShift = false;
            this.pressingCtrl = false;
        }

        /**{@inheritDoc} */
        @Override
        public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
            return selected.isPresent()
                ? this.handlePlacement()
                : this.handleTouch(screenX, screenY);
        }

        /**{@inheritDoc} */
        @Override
        public boolean keyDown(final int keycode) {
            switch(keycode) {
                case Input.Keys.Q -> this.selectingBuilding();
                case Input.Keys.SHIFT_LEFT -> this.pressingShift = true;
                case Input.Keys.CONTROL_LEFT -> this.pressingCtrl = true;
                case Input.Keys.UP -> this.roundButtonList(1);
                case Input.Keys.DOWN -> this.roundButtonList(-1);
                case Input.Keys.ESCAPE -> Gdx.app.exit(); //TODO exit game.
            }
            return false;
        }

        /**{@inheritDoc} */
        @Override
        public boolean keyUp(final int keycode) {
            switch(keycode) {
                case Input.Keys.SHIFT_LEFT -> this.pressingShift = false;
                case Input.Keys.CONTROL_LEFT -> this.pressingCtrl = false;
            }
            return true;
        }

        /**{@inheritDoc} */
        @Override
        public boolean mouseMoved(final int screenX, final int screenY) {
            upgradeLabel.setVisible(false);
            if (selected.isPresent()) { //Verifies if the user has selected a building to place
                selected.get().setPosition(this.computeX(screenX), this.computeY(screenY));
                return true;
            } else { //Allows to display a label containing info about upgrade costs when mouse is over buildings
                var building = buildings.entrySet().stream()
                    .filter(b -> b.getKey().contains(screenX, Gdx.graphics.getHeight() - screenY)).findFirst();
                if (building.isPresent() && pressingCtrl) {
                    final var name = building.get().getValue().getName();
                    upgradeLabel.setText(name + "\n" + computeTextResources(controller.getUpgrade(name))
                        + Resource.CITIZEN + ": " + controller.getUpgrade(name).get(Resource.CITIZEN));
                    setLabelDimensions(upgradeLabel);
                    upgradeLabel.setVisible(true);
                    upgradeLabel.setPosition(screenX, Gdx.graphics.getHeight() - screenY);
                }
            }
            return false;
        }

        /**{@inheritDoc} */
        @Override
        public boolean scrolled(final float amountX, final float amountY) {
            this.roundButtonList((int) amountY);
            return true;
        }

        private float computeX(final float screenX) {
            return screenX - RECT_WIDTH / 2;
        }
    
        private float computeY(final float screenY) {
            return Gdx.graphics.getHeight() - screenY - RECT_HEIGHT / 2;
        }

        //Creates a square for placing the building and stores the name of the building selected, retrieving it from the icon.
        private boolean selectingBuilding() {
            if (selected.isEmpty()) {
                this.selection.play();
                selected = Optional.of(new Rectangle(
                    computeX(Gdx.input.getX()), 
                    computeY(Gdx.input.getY()),
                    RECT_WIDTH, RECT_HEIGHT));
                this.selectedName = tableBuildings.getChild(0).getName().replace(ICON_SUFFIX, "");
                return true;
            }
            return false;
        }

        /*When the user has selected a building from the icon menù, this method 
        * is used to determine the consequences of a click of the mouse.
        * It allows to place the building selected only if not overlapping another building and if the player
        * has enough resources. */
        private boolean handlePlacement() {
            if (isValidPosition(selected.get()) && controller.checkResourcesAndBuild(this.selectedName)) {
                this.construction.play();
                final var im = new Image(new Texture(Gdx.files.internal(IMAGE_FOLDER + imageList[index].replace(ICON_SUFFIX, EXTENSION))));
                im.setPosition(selected.get().x, selected.get().y);
                im.setName(imageList[index].replace(ICON_SUFFIX, "").replace("_", " "));
                stage.addActor(im);
                im.setZIndex(0);
                buildings.put(selected.get(), im);
            } else {
                this.displayWarning(constructionFailed);
            }
            selected = Optional.empty();
            return true;
        }

        /*This method is used to determine the consequences of a click of the mouse when not carrying a building for placement.
         * It allows for upgrading and destroying buildings already placed.*/
        private boolean handleTouch(final int screenX, final int screenY) {
            final var touched = buildings.entrySet().stream()
                .filter(entry -> entry.getKey().contains(screenX, Gdx.graphics.getHeight() - screenY))
                .findFirst();
            if (touched.isPresent()) {
                if (this.pressingShift) {
                    this.destruction.play();
                    controller.removeBuilding(touched.get().getValue().getName());
                    buildings.remove(touched.get().getKey());
                    touched.get().getValue().remove();
                } else if (this.pressingCtrl) {
                    this.upgrade(controller.upgradeBuilding(touched.get().getValue().getName()));
                }
                return true;
            }
            return false;
        }

        private void upgrade(final boolean success) {
            if (success) {
                this.upgrading.play();
                updateTablePlayer();
            } else {
                this.displayWarning(upgradeFailed);
            }
        }

        /*Used to display warning regarding wrong inputs from the user. */
        private void displayWarning(final Dialog d) {
            this.wrong.play();
            upgradeFailed.hide(); //Hides other warnings that may be active
            constructionFailed.hide();
            d.show(stage);
            d.setPosition(Gdx.graphics.getWidth() / 2 - d.getWidth() / 2, 
                Gdx.graphics.getHeight() - d.getHeight() - Gdx.graphics.getHeight() / 14);
            Timer.schedule(new Task() {
                @Override
                public void run() {
                    d.hide();
                }
            }, DURATION_WARNINGS);    
        }

        private void roundButtonList(int param){
            if (selected.isEmpty()) {
                this.scroll.play();
                if (param == 1){
                    index++;
                    if (index == imageList.length){
                        index = 0;
                    }
                } else if (param == -1){
                    index--;
                    if (index == -1){
                        index = imageList.length-1;
                    }
                }
                selectButton(index);
            }
        }
    }
}
