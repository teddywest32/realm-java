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

import android.util.Log;

import io.realm.examples.intro.model.AppCategory;

public class Test {
/*
REALM  D   --> Java_io_realm_internal_SharedGroup_nativeCreateReplication
    D   --> Java_io_realm_internal_SharedGroup_createNativeWithImplicitTransactions
    D   --> Java_io_realm_internal_SharedGroup_nativeBeginImplicit -1387096064
    D   --> Java_io_realm_internal_Group_nativeHasTable -1387096064
    D   --> Java_io_realm_internal_Group_nativeHasTable -1387096064
    D   --> Java_io_realm_internal_Group_nativeGetTableNativePtr -1387096064
    D   --> Java_io_realm_internal_SharedGroup_nativePromoteToWrite -1387096064
    D   --> Java_io_realm_internal_Group_nativeHasTable -1387096064
    D   --> Java_io_realm_internal_Group_nativeGetTableNativePtr -1387096064
    D   --> Java_io_realm_internal_Group_nativeHasTable -1387096064
    D   --> Java_io_realm_internal_Group_nativeGetTableNativePtr -1387096064
    D   --> Java_io_realm_internal_Group_nativeHasTable -1387096064
    D   --> Java_io_realm_internal_Group_nativeGetTableNativePtr -1387096064
    D   --> Java_io_realm_internal_Group_nativeGetTableNativePtr -1387096064
    D   --> Java_io_realm_internal_Group_nativeHasTable -1387096064
    D   --> Java_io_realm_internal_Group_nativeGetTableNativePtr -1387096064
    D   --> Java_io_realm_internal_Group_nativeHasTable -1387096064
    D   --> Java_io_realm_internal_Group_nativeGetTableNativePtr -1387096064
    D   --> Java_io_realm_internal_Group_nativeHasTable -1387096064
    D   --> Java_io_realm_internal_Group_nativeGetTableNativePtr -1387096064
    D   --> Java_io_realm_internal_Group_nativeGetTableNativePtr -1387096064
    D   --> Java_io_realm_internal_SharedGroup_nativeRollbackAndContinueAsRead -1387096064
    D   --> Java_io_realm_internal_Group_nativeHasTable -1387096064
    D   --> Java_io_realm_internal_Group_nativeGetTableNativePtr -1387096064
    D   --> Java_io_realm_internal_Row_nativeGetString -1413291984
libc  F  Fatal signal 11 (SIGSEGV), code 2, fault addr 0xb3774000 in tid 5885 (.examples.intro)
 */
    public static void test(Realm realm) {
        AppCategory cat = realm.get(AppCategory.class, 15);
        Log.i("Debug", cat.getCategory());

        // Crashes when getting string from core.
        // Realm browser can open the file and claim that it is a "" (empty) string.
        String subCategory = cat.getSubCategory();
    }
}
