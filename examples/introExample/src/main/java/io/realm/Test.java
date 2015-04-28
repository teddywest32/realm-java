package io.realm;
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

import android.content.Context;
import android.util.Log;

import java.io.File;

import io.realm.examples.intro.IntroExampleActivity;
import io.realm.examples.intro.model.AppCategory;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.SharedGroup;
import io.realm.internal.Table;

public class Test {
/*
D/REALM﹕ --> Java_io_realm_internal_SharedGroup_nativeCreate
D/REALM﹕ --> Java_io_realm_internal_SharedGroup_nativeBeginImplicit -1266426112
D/REALM﹕ --> Java_io_realm_internal_Group_nativeHasTable -1266426112
D/REALM﹕ --> Java_io_realm_internal_Group_nativeGetTableNativePtr -1266426112
A/libc﹕ Fatal signal 11 (SIGSEGV), code 1, fault addr 0xb3437000 in tid 18748 (.examples.intro)
I/DEBUG﹕ pid: 18748, tid: 18748, name: .examples.intro  >>> io.realm.examples.intro <<<
I/DEBUG﹕ #00 pc 000168ec  /data/app/io.realm.examples.intro-1/lib/arm/libtightdb-jni.so
I/DEBUG﹕ #01 pc 0007e7bf  /data/app/io.realm.examples.intro-1/lib/arm/libtightdb-jni.so
I/DEBUG﹕ #02 pc 000786cb  /data/app/io.realm.examples.intro-1/lib/arm/libtightdb-jni.so (Java_io_realm_internal_Table_nativeGetString+58)
*/
    public static void test(Context context) {
        SharedGroup sg = new SharedGroup(new File(context.getFilesDir(), Realm.DEFAULT_REALM_NAME).getAbsolutePath());
        ImplicitTransaction tr = sg.beginImplicitTransaction();
        Table table = tr.getTable("class_AppCategory");
        long columnIndex = table.getColumnIndex("subCategory");
        String subCategory = table.getString(columnIndex, 15);
        Log.i("Debug", subCategory);
    }
}
