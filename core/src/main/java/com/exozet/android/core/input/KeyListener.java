package com.exozet.android.core.input;

import android.view.KeyEvent;

public interface KeyListener {

    boolean onKeyUp(final int keyCode, final KeyEvent event);

    boolean onKeyDown(final int keyCode, final KeyEvent event);
}