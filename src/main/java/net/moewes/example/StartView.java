package net.moewes.example;

import javax.inject.Inject;

import net.moewes.cloud.ui.UiBinder;
import net.moewes.cloud.ui.UiComponent;
import net.moewes.cloud.ui.annotations.CloudUiView;
import net.moewes.cloud.ui.html.Div;
import net.moewes.cloud.ui.html.H1;
import net.moewes.cloud.ui.html.Label;
import net.moewes.cloud.ui.quarkus.runtime.CloudUi;

@CloudUiView("/start")
public class StartView extends Div {

    private UiComponent nameField = new UiComponent("input");
    private UiComponent numberOfTriesField = new UiComponent("input");
    private UiComponent numberRangeField = new UiComponent("input");
    
    @Inject
    public StartView(GameBean gameBean, CloudUi ui) {

        add(new H1("Welcome to Number Guess Game"));

        add(new Label("Your Name:"));
        add(new UiComponent("br"));
        add(nameField);
        add(new UiComponent("br"));
        UiBinder nameBinder = new UiBinder();
        nameBinder.bind(nameField,() -> { return " "; },value -> {gameBean.setPlayersName(value);});

        add(new Label("Number of tries:")); 
        add(new UiComponent("br"));
        add(numberOfTriesField);
        add(new UiComponent("br"));
        UiBinder numberOfTriesBinder = new UiBinder();
        numberOfTriesBinder.bind(numberOfTriesField,() -> { return "3"; },value -> { gameBean.setMaxNumberOfTries(Integer.parseInt(value));});

        add(new Label("Number range:")); 
        add(new UiComponent("br"));
        add(numberRangeField);
        UiBinder numberRangeBinder = new UiBinder();
        numberRangeBinder.bind(numberRangeField, () -> { return "10"; }, value -> {
            gameBean.setNumberRange(Integer.parseInt(value));
        });

        add(new UiComponent("br"));
        add(new UiComponent("br"));

        UiComponent startButton = new UiComponent("Button");
        startButton.setInnerHtml("Start Game");
        add(startButton);

        startButton.addEventListener("click", e -> {
            gameBean.startGame();
            ui.navigate(GuessView.class);
        });
    }
}
