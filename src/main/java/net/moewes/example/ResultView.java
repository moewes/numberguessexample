package net.moewes.example;

import javax.inject.Inject;

import net.moewes.cloud.ui.annotations.CloudUiView;
import net.moewes.cloud.ui.html.Div;
import net.moewes.cloud.ui.html.H1;

@CloudUiView("/result")
public class ResultView extends Div {
    
    @Inject
    public ResultView(GameBean gameBean) {

        add(new H1(gameBean.getResult()));
    }
}
