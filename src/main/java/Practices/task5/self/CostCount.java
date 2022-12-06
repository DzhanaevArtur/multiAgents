package Practices.task5.self;

/**
 * @author Artur Dzhanaev
 * @created 06.12.2022
 */
public interface CostCount { default int randomCost(int minValue) { return (int) (minValue * (1 + 2 * Math.random())); } }
