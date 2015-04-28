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

package io.realm.examples.intro;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Test;
import io.realm.examples.intro.model.AppCategory;
import io.realm.examples.intro.model.AppInstalled;
import io.realm.internal.Table;
import io.realm.internal.Util;
import io.realm.processor.Utils;


public class IntroExampleActivity extends Activity {

    private static final String TAG = IntroExampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_basic_example);

        Util.setDebugLevel(1); // Enable JNI Debug
        Realm.deleteRealmFile(this, Realm.DEFAULT_REALM_NAME);
        copyRealm(this, Realm.DEFAULT_REALM_NAME);
        Realm realm = Realm.getInstance(this);
        Test.test(realm); // Run tests in io.realm package to access protected methods
    }

    // Copies a Realm file from assets to app files dir
    public static void copyRealm(Context context, String newName) {
        try {
            InputStream is = context.getResources().openRawResource(R.raw.data);
            File file = new File(context.getFilesDir(), newName);
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buf)) > -1) {
                outputStream.write(buf, 0, bytesRead);
            }
            outputStream.close();
            is.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}