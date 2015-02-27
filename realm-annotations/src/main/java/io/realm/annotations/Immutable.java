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

package io.realm.annotations;

/**
 * Marking an object has Immutable changes the object from an entity to an an value type.
 * Doing so require a constructor with all fields as parameters in the order they appear
 * and only getters to present. This is enforced by the annotation processor.
 *
 * Once we get thread handoff. The RowAccessor is transfered around to each thread as needed making
 * it possible to transfer the object around.
 */
public @interface Immutable {

}
