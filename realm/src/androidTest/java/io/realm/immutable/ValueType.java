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

import io.realm.RealmObject;
import io.realm.annotations.Immutable;

/**
 * Marking it immutable changes requirements:
 *
 * - All fields must be final
 * - Only 1 constructor taking all fields as parameters in the order they appear is allowed
 * - Only Getters are allowed
 *
 * This will be enforced by the annotation processor
 *
 * Tricky cases:
 * - Can only reference other objects that are also immutable.
 * - We have to make an immutable variant of RealmList.
 *
 * Other things to consider
 * - Make it possible to play nice with AutoValue (Probably not impossible).
 * - We would most likely need to allow people to override equals, hashcode, and toString()
 * - If we don't copy data directly into the object we need to keep older versions of the SharedGroup
 *   around. This can potentially get very costly, but so is copying the data into the object.
 *
 *   I probably depends on most common usage: If multiple objects are pulled from the same sharedGroup,
 *   it is probably cheaper to keep it around. If single objects are taken from many versions of the SharedGroup
 *   copying the data is probably better.
 */
@Immutable
public class ValueType extends RealmObject {

    private final String foo;
    private final int bar;

    public ValueType(String foo, int bar) {
        this.foo = foo;
        this.bar = bar;
    }

    public String getFoo() {
        return foo;
    }

    public int getBar() {
        return bar;
    }
}
