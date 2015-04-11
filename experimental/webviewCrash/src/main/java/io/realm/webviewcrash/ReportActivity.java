/*
 * Copyright 2014 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.realm.webviewcrash;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import io.realm.Realm;

import java.security.SecureRandom;

/**
 E/chromium(29765): ### WebView Version 40 (1832189-arm) (code 424501)
 F/libc    (29765): Fatal signal 11 (SIGSEGV), code -6, fault addr 0x7445 in tid 29765 (realm.issue1023)
 I/DEBUG   (  186): *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***
 I/DEBUG   (  186): Build fingerprint: 'google/hammerhead/hammerhead:5.1/LMY47D/1743759:user/release-keys'
 I/DEBUG   (  186): Revision: '11'
 I/DEBUG   (  186): ABI: 'arm'
 I/DEBUG   (  186): pid: 29765, tid: 29765, name: realm.issue1023  >>> io.realm.issue1023 <<<
 I/DEBUG   (  186): signal 11 (SIGSEGV), code -6 (SI_TKILL), fault addr 0xb346f000
 I/DEBUG   (  186):     r0 b4812000  r1 b4812b85  r2 b346f000  r3 00000000
 I/DEBUG   (  186):     r4 00000b85  r5 00000000  r6 b4af7bbc  r7 00000000
 I/DEBUG   (  186):     r8 b4af7b60  r9 b3471040  sl b4af7bb0  fp befb3ec4
 I/DEBUG   (  186):     ip 00000000  sp befb3eb8  lr b34a6fa1  pc b34cffcc  cpsr 800d0030
 I/DEBUG   (  186):
 I/DEBUG   (  186): backtrace:
 I/DEBUG   (  186):     #00 pc 0005dfcc  /data/app/io.realm.issue1023-1/lib/arm/libtightdb-jni.so
 I/DEBUG   (  186):     #01 pc 0005e043  /data/app/io.realm.issue1023-1/lib/arm/libtightdb-jni.so
 I/DEBUG   (  186):     #02 pc 0005fb03  /data/app/io.realm.issue1023-1/lib/arm/libtightdb-jni.so
 I/DEBUG   (  186):     #03 pc 00068e93  /data/app/io.realm.issue1023-1/lib/arm/libtightdb-jni.so (Java_io_realm_internal_SharedGroup_nativeCommitAndContinueAsRead+42)
 I/DEBUG   (  186):     #04 pc 000625ef  /data/dalvik-cache/arm/data@app@io.realm.issue1023-1@base.apk@classes.dex
 */

public class ReportActivity extends Activity {

    WebView webview;
    private Realm realm = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        byte[] key = new byte[64];
        new SecureRandom().nextBytes(key);

        Realm.deleteRealmFile(this);
        realm = Realm.getInstance(this, key);

        webview = new WebView(this);
        webview.getSettings().setDefaultTextEncodingName("UTF-8");
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setSupportZoom(true);
        webview.setWebViewClient(new WebViewClient());
        webview.setWebChromeClient(new WebChromeClient());
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webview.setScrollbarFadingEnabled(false);
        webview.loadUrl("about:blank");
        setContentView(webview);

        realm.beginTransaction();
        realm.clear(TestEntity.class);
        for (int i = 1; i < 100; i++) {
            TestEntity e = realm.createObject(TestEntity.class);
            e.setId(String.valueOf(i));
            e.setCode(String.valueOf(i * 100));
            e.setId2(String.valueOf(i * 1000));
        }
        realm.commitTransaction();
    }


    @Override
    public void finish() {
        realm.close();
        super.finish();
    }
}
