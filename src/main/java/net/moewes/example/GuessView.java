package net.moewes.example;

import javax.inject.Inject;

import net.moewes.cloud.ui.UiBinder;
import net.moewes.cloud.ui.UiComponent;
import net.moewes.cloud.ui.annotations.CloudUiView;
import net.moewes.cloud.ui.html.Div;
import net.moewes.cloud.ui.html.Label;
import net.moewes.cloud.ui.quarkus.runtime.CloudUi;

@CloudUiView("/guess")
public class GuessView extends Div {

    private int guess = 0;
    private UiComponent guessField = new UiComponent("input");
    
    @Inject
    public GuessView(GameBean gameBean, CloudUi ui) {

        add(new Label("Your Guess:")); 
        add(new UiComponent("br"));
        add(guessField);

        UiBinder binder = new UiBinder();
        binder.bind(guessField,this::getGuess,this::setGuess);

        add(new UiComponent("br"));
        add(new UiComponent("br"));
        
        UiComponent startButton = new UiComponent("Button");
        startButton.setInnerHtml("Guess");
        add(startButton);

        startButton.addEventListener("click", e -> {
            gameBean.evaluateGuess(guess);
            if (gameBean.hasEnded()) {
                ui.navigate(ResultView.class);
            } else {
                add(new Label(gameBean.getHint()));
            }
        });
    }

    public String getGuess() {
        return "" + guess;
    }

    public void setGuess(String value) {
        guess = Integer.parseInt(value);
    }
}
