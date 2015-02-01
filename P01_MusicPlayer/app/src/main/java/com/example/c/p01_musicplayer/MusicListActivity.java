package com.example.c.p01_musicplayer;

import android.support.v4.app.Fragment;

/**
 * Created by c on 2015-02-01.
 */
public class MusicListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MusicListFragment();
    }
}
