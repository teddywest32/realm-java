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

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Context {

    // Each group of related TightDB objects will have a Context object in the root.
    // The root can be a table, a group, or a shared group.
    // The Context object is used to store a list of native pointers 
    // whose disposal need to be handed over from the garbage 
    // collection thread to the users thread.

    // Store the row references. Without this objects would be garbage collected immediately so don't remove this! ;)
    final Set<Reference<?>> rowReferences = Collections.synchronizedSet(new HashSet<Reference<?>>());

    // This is the actual reference queue in which the garbage collector will insert the row instances ready to be
    // cleaned up
    final ReferenceQueue<NativeObject> rowReferenceQueue = new ReferenceQueue<NativeObject>();

    private List<Long> abandonedTables = new ArrayList<Long>();
    private List<Long> abandonedTableViews = new ArrayList<Long>();
    private List<Long> abandonedQueries = new ArrayList<Long>();

    private boolean isFinalized = false;

    public void executeDelayedDisposal() {
        synchronized (this) {
            for (long nativePointer: abandonedTables) {
                Table.nativeClose(nativePointer);
            }
            abandonedTables.clear();

            for (long nativePointer: abandonedTableViews) {
                TableView.nativeClose(nativePointer);
            }
            abandonedTableViews.clear();

            for (long nativePointer: abandonedQueries) {
                TableQuery.nativeClose(nativePointer);
            }
            abandonedQueries.clear();

            NativeObjectReference reference;
            while ((reference = (NativeObjectReference) rowReferenceQueue.poll()) != null) {
                Row.nativeClose(reference.nativePointer);
                rowReferences.remove(reference);
            }
        }
    }

    public void asyncDisposeTable(long nativePointer, boolean isRoot) {
        if (isRoot || isFinalized) {
            Table.nativeClose(nativePointer);
        }
        else {
            abandonedTables.add(nativePointer);
        }
    }

    public void asyncDisposeTableView(long nativePointer) {
        if (isFinalized) {
            TableView.nativeClose(nativePointer);
        }
        else {
            abandonedTableViews.add(nativePointer);
        }
    }

    public void asyncDisposeQuery(long nativePointer) {
        if (isFinalized) {
            TableQuery.nativeClose(nativePointer);
        }
        else {
            abandonedQueries.add(nativePointer);
        }
    }

    public void asyncDisposeGroup(long nativePointer) {
        Group.nativeClose(nativePointer);
    }

    public void asyncDisposeSharedGroup(long nativePointer) {
        SharedGroup.nativeClose(nativePointer);
    }

    protected void finalize() {
        synchronized (this) {
            isFinalized = true;
        }
        executeDelayedDisposal();
    }
}
