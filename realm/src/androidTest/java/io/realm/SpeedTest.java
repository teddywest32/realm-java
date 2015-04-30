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

package io.realm;

import android.test.AndroidTestCase;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import io.realm.exceptions.RealmException;
import io.realm.internal.RealmProxyMediator;

public class SpeedTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testGetMediator() {
        for (int i = 0; i < 10; i++) {
            getDefaultMediator();
        }
    }

    public void testCallMethod() {
        RealmProxyMediator mediator = getDefaultMediator();
        List<Class<? extends RealmObject>> classes = mediator.getModelClasses();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            int size = classes.size();
            for (int j = 0; j < size; j++) {
                String tableName = mediator.getTableName(classes.get(j));
            }
        }
        long end = System.currentTimeMillis();
        Log.i("Speed", "List: All classes (10000): " + (end - start) + "ms.");

        mediator = getHashMapMediator();
        classes = mediator.getModelClasses();
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            int size = classes.size();
            for (int j = 0; j < size; j++) {
                String tableName = mediator.getTableName(classes.get(j));
            }
        }
        end = System.currentTimeMillis();
        Log.i("Speed", "HashMap: All classes (10000): " + (end - start) + "ms.");

    }

    private RealmProxyMediator getDefaultMediator() {
            long start = System.nanoTime();
            RealmProxyMediator mediator = new IfElseMediator();
            long end = System.nanoTime();
        Log.i("Speed", "List: Creating class: " + (end - start) + " ns.");
            return mediator;
    }

    public RealmProxyMediator getHashMapMediator() {
        long start = System.nanoTime();
        RealmProxyMediator mediator = new HashMapMediator();
        long end = System.nanoTime();
        Log.i("Speed", "HashMap: Creating class: " + (end - start) + " ns.");
        return mediator;
    }
}
