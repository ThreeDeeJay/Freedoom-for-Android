package com.beloko.touchcontrols;

public interface ControlInterface {

  void initTouchControls_if(String pngPath, int width, int height);

  boolean touchEvent_if(int action, int pid, float x, float y);

  void keyPress_if(int down, int qkey, int unicode);

  void doAction_if(int state, int action);

  void analogFwd_if(float v);

  void analogSide_if(float v);

  void analogPitch_if(int mode, float v);

  void analogYaw_if(int mode, float v);

  void setTouchSettings_if(float alpha, float strafe, float fwd, float pitch, float yaw, int other);

  void quickCommand_if(String command);

  int mapKey(int acode, int unicode);
}
