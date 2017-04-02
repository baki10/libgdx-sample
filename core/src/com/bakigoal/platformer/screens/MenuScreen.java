package com.bakigoal.platformer.screens;

import com.badlogic.gdx.ScreenAdapter;

public class MenuScreen extends ScreenAdapter{

  private boolean logged = false;

  @Override
  public void render(float delta) {
    if(!logged){
      System.out.println("Render of Menu screen");
      logged = true;
    }
  }
}
