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

package io.realm.immutable;

import android.test.AndroidTestCase;

import io.realm.Realm;

// Note these tests don't compile currently.
public class ImmutableTest extends AndroidTestCase {

    private Realm realm;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Realm.deleteRealmFile(getContext());
        realm = Realm.getInstance(getContext());
    }

    public void testWorkingWithImmutableObjects() {

        final ValueType originalObject = new ValueType("Foo", 1);

        // Data can only be added using copyToRealm
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(originalObject);
            }
        });

        // Getting them out is the same as normal
        ValueType obj = realm.allObjects(ValueType.class).first();

        // Equals will be tricky as without a proper equals() implementation this will fail
        assertEquals(originalObject, obj);
    }


}
