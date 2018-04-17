package com.olivierpicard.crachit.Graphics;

/**
 * Created by olivierpicard on 16/04/2018.
 */

public interface IGActivitySwitchable {
    void switchActivity(Class activity);
    void switchActivity(Class activity, String Message);
    void switchActivityWithResult(Class activity, int requestCode);
    void switchActivityWithResult(Class activity, String Message, int requestCode);
}
