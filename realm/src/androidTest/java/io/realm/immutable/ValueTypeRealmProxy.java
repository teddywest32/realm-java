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

import io.realm.annotations.Immutable;

/**
 * Example of how the proxy class would like like.
 */
public class ValueTypeRealmProxy extends ValueType {

    public ValueTypeRealmProxy(String foo, int bar) {
        // We need to fill object with default data :(
        // There are also threading issues. If we want to keep the data inside Realm. We would have
        // to transfer row accessor between threads. Likely not impossible when thread handoff is
        // introduced.
        // Just copying those data into the object is also an option
        super("", 0);
    }

    public String getFoo() {
        return row.getString(0);
    }

    public int getBar() {
        return (int) row.getLong(0);
    }

    // ... All the other methods
}
