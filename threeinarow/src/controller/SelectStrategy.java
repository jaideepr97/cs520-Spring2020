package controller;

import model.RowGameModel;

public class SelectStrategy {

    private RowGameRulesStrategy strategy;

    public SelectStrategy(RowGameRulesStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(RowGameModel gameModel) {
        this.strategy.reset(gameModel);
    }

    public void startStrategy(RowGameRulesStrategy strategy) {
        strategy.startUp();
    }
}
