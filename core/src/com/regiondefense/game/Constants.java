package com.regiondefense.game;

public interface Constants {
    int WINDOW_WIDTH = 1024;
    int WINDOW_HEIGHT = 768;
    int FPS = 120;

    // We can only ever move diagonally so this is the pre-calculated approximation of sin(45°)
    float DIAGONAL_SPEED = 0.707f;

    // UI
    int UI_BORDER = 10;
}
