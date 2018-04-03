package com.exozet.android.core.services.sound;

import android.content.Context;
import android.media.AudioManager;

import static android.support.v4.math.MathUtils.clamp;
import static net.kibotu.ContextHelper.getApplication;

/**
 * Created by Jan Rabe on 17/08/15.
 */
public enum SoundService {

    instance;

    boolean isMuted;

    private AudioManager audioManager;

    public static void init() {
        instance.audioManager = (AudioManager) getApplication().getSystemService(Context.AUDIO_SERVICE);
    }

    public static void muteAudio() {
        instance.isMuted = true;
        instance.audioManager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
        instance.audioManager.setStreamMute(AudioManager.STREAM_ALARM, true);
        instance.audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
        instance.audioManager.setStreamMute(AudioManager.STREAM_RING, true);
        instance.audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
    }

    public static void unmuteAudio() {
        instance.isMuted = false;
        instance.audioManager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
        instance.audioManager.setStreamMute(AudioManager.STREAM_ALARM, false);
        instance.audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
        instance.audioManager.setStreamMute(AudioManager.STREAM_RING, false);
        instance.audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
    }

    public static boolean isMuted() {
        return instance.isMuted;
    }

    public static void decreaseVolume() {
        instance.changeVolume(-10);
    }

    public static void increaseVolume() {
        instance.changeVolume(10);
    }

    public static void toggleMute() {
        if (isMuted())
            unmuteAudio();
        else
            muteAudio();
    }

    private void changeVolume(final int dt) {
        final int streamMaxVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        final int volume = clamp(streamMaxVolume + dt, 0, streamMaxVolume);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
    }
}
