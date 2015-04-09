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

package io.realm.cookiemanager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.webkit.CookieManager;

import io.realm.Realm;
import io.realm.internal.Util;

public class MainActivity extends FragmentActivity {

    private static final String TAG = "MainActivity";
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Util.setDebugLevel(2);
        makeRealmCrash();
    }

    private void makeRealmCrash() {
        final String dbName = "realm_crash";
        final String key = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";


        Realm.deleteRealmFile(MainActivity.this, dbName);
        for (int i = 0; i < 10; i++) {
            final int ii = i;
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    Realm r = Realm.getInstance(getApplicationContext(), dbName, key.getBytes());
                    SystemClock.sleep(ii * 50);
                    r.close();
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    CookieManager obj = CookieManager.getInstance();
                }
            }.execute();
        }
    }
}
