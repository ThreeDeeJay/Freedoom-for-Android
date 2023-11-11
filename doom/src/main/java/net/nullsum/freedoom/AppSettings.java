package net.nullsum.freedoom;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import com.beloko.touchcontrols.TouchSettings;
import java.io.File;
import java.io.IOException;

public class AppSettings {

  public static String freedoomBaseDir;

  public static String musicBaseDir;

  public static String graphicsDir = "";

  public static boolean vibrate;
  public static boolean immersionMode;

  public static Context ctx;

  public static void resetBaseDir(Context ctx) {
    freedoomBaseDir = Environment.getExternalStorageDirectory().toString() + "/Freedoom";
    setStringOption(ctx, "base_path", freedoomBaseDir);
  }

  public static void reloadSettings(Context ctx) {
    AppSettings.ctx = ctx;

    TouchSettings.reloadSettings(ctx);

    freedoomBaseDir = getStringOption(ctx, "base_path", null);
    if (freedoomBaseDir == null) {
      resetBaseDir(ctx);
    }

    String music = getStringOption(ctx, "music_path", null);
    if (music == null) {
      music = freedoomBaseDir + "/doom/Music";
      setStringOption(ctx, "music_path", music);
    }

    musicBaseDir = music;

    graphicsDir = ctx.getFilesDir().toString() + "/";

    vibrate = getBoolOption(ctx, "vibrate", true);

    immersionMode = getBoolOption(ctx, "immersion_mode", true);
  }

  public static String getQuakeFullDir() {
    String quakeFilesDir = AppSettings.freedoomBaseDir;
    return quakeFilesDir + "/config";
  }

  public static void createDirectories(Context ctx) {
    boolean scan = false;

    String d = "";
    if (!new File(getQuakeFullDir() + d).exists()) scan = true;

    new File(getQuakeFullDir() + d).mkdirs();

    // create folder gzdoom_dev where we store a modified ini
    new File(getQuakeFullDir() + "/gzdoom_dev").mkdirs();

    // create a folder for user wads
    new File(getQuakeFullDir() + "/wads").mkdirs();

    // create a folder for user mods (experimental)
    new File(getQuakeFullDir() + "/mods").mkdirs();

    // This is totally stupid, need to do this so folder shows up in explorer!
    if (scan) {
      File f = new File(getQuakeFullDir() + d, "temp_");
      try {
        f.createNewFile();
        new SingleMediaScanner(ctx, false, f.getAbsolutePath());
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      new File(getQuakeFullDir() + d, "temp_").delete();
    }
  }

  public static void setFloatOption(Context ctx, String name, float value) {
    SharedPreferences settings = ctx.getSharedPreferences("OPTIONS", Context.MODE_MULTI_PROCESS);
    SharedPreferences.Editor editor = settings.edit();
    editor.putFloat(name, value);
    editor.apply();
  }

  public static boolean getBoolOption(Context ctx, String name, boolean def) {
    SharedPreferences settings = ctx.getSharedPreferences("OPTIONS", Context.MODE_MULTI_PROCESS);
    return settings.getBoolean(name, def);
  }

  public static void setBoolOption(Context ctx, String name, boolean value) {
    SharedPreferences settings = ctx.getSharedPreferences("OPTIONS", Context.MODE_MULTI_PROCESS);
    SharedPreferences.Editor editor = settings.edit();
    editor.putBoolean(name, value);
    editor.apply();
  }

  public static int getIntOption(Context ctx, String name, int def) {
    SharedPreferences settings = ctx.getSharedPreferences("OPTIONS", Context.MODE_MULTI_PROCESS);
    return settings.getInt(name, def);
  }

  public static void setIntOption(Context ctx, String name, int value) {
    SharedPreferences settings = ctx.getSharedPreferences("OPTIONS", Context.MODE_MULTI_PROCESS);
    SharedPreferences.Editor editor = settings.edit();
    editor.putInt(name, value);
    editor.apply();
  }

  public static long getLongOption(Context ctx, String name, long def) {
    SharedPreferences settings = ctx.getSharedPreferences("OPTIONS", Context.MODE_MULTI_PROCESS);
    return settings.getLong(name, def);
  }

  public static void setLongOption(Context ctx, String name, long value) {
    SharedPreferences settings = ctx.getSharedPreferences("OPTIONS", Context.MODE_MULTI_PROCESS);
    SharedPreferences.Editor editor = settings.edit();
    editor.putLong(name, value);
    editor.apply();
  }

  public static String getStringOption(Context ctx, String name, String def) {
    SharedPreferences settings = ctx.getSharedPreferences("OPTIONS", Context.MODE_MULTI_PROCESS);
    return settings.getString(name, def);
  }

  public static void setStringOption(Context ctx, String name, String value) {
    SharedPreferences settings = ctx.getSharedPreferences("OPTIONS", Context.MODE_MULTI_PROCESS);
    SharedPreferences.Editor editor = settings.edit();
    editor.putString(name, value);
    editor.apply();
  }
}
