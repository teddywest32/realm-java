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
package io.realm.internal;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * This phantom reference extension is specialized in holding the pointer to the native objects that needs to be
 * handled.s
 */
public class NativeObjectReference extends PhantomReference<NativeObject> {

    // The pointer to the native object to be handled
    final long nativePointer;

    public NativeObjectReference(NativeObject referent, ReferenceQueue<? super NativeObject> referenceQueue) {
        super(referent, referenceQueue);
        nativePointer = referent.nativePointer;
    }
}
