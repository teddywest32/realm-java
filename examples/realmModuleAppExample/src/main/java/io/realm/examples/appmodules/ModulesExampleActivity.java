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

package io.realm.examples.appmodules;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.examples.appmodules.model.Cow;
import io.realm.examples.appmodules.model.Pig;
import io.realm.examples.appmodules.model.Snake;
import io.realm.examples.appmodules.model.Spider;
import io.realm.examples.appmodules.modules.CreepyAnimalsModule;
import io.realm.examples.librarymodules.Zoo;
import io.realm.examples.librarymodules.model.Cat;
import io.realm.examples.librarymodules.model.Dog;
import io.realm.examples.librarymodules.model.Elephant;
import io.realm.examples.librarymodules.model.Lion;
import io.realm.examples.librarymodules.model.Zebra;
import io.realm.examples.librarymodules.modules.DomesticAnimalsModule;
import io.realm.examples.librarymodules.modules.ZooAnimalsModule;
import io.realm.exceptions.RealmException;

public class ModulesExampleActivity extends Activity {

    public static final String TAG = ModulesExampleActivity.class.getName();
    private LinearLayout rootLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules_example);
        rootLayout = ((LinearLayout) findViewById(R.id.container));
        rootLayout.removeAllViews();

        // The default Realm instance implicitly know about all classes in the realmModuleAppExample project.
        // This does not include classes from the libraries so a Realm using this configuration would know about the
        // following classes: { Cow, Pig, Spider, Snake }
        RealmConfiguration defaultConfig = new RealmConfiguration.Builder(this).build();

        // It is possible to extend the default schema by adding additional modules using addModule(). This can
        // also be modules from libraries. This Realm contains the following classes: { Cow, Pig, Cat, Dog }
        RealmConfiguration farmAnimalsConfig = new RealmConfiguration.Builder(this)
                .name("farm.realm")
                .addModule(new DomesticAnimalsModule())
                .build();

        // Or you can completely replace the default schema.
        // This Realm contains the following classes: { Elephant, Lion, Zebra, Spider, Snake }
        RealmConfiguration exoticAnimalsConfig = new RealmConfiguration.Builder(this)
                .name("exotic.realm")
                .setModules(new ZooAnimalsModule(), new CreepyAnimalsModule())
                .build();

        // Multiple Realms can be open at the same time
        showStatus("Opening multiple Realms");
        Realm defaultRealm = Realm.getInstance(defaultConfig);
        Realm farmRealm = Realm.getInstance(farmAnimalsConfig);
        Realm exoticRealm = Realm.getInstance(exoticAnimalsConfig);

        // Objects can be added to each Realm independantly
        showStatus("Create objects in the default Realm");
        defaultRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createObject(Cow.class);
                realm.createObject(Pig.class);
                realm.createObject(Spider.class);
                realm.createObject(Snake.class);
            }
        });

        showStatus("Create objects in the farm Realm");
        farmRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createObject(Cow.class);
                realm.createObject(Pig.class);
                realm.createObject(Cat.class);
                realm.createObject(Dog.class);
            }
        });

        showStatus("Create objects in the exotic Realm");
        exoticRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createObject(Elephant.class);
                realm.createObject(Lion.class);
                realm.createObject(Zebra.class);
                realm.createObject(Spider.class);
                realm.createObject(Snake.class);
            }
        });

        // You can copy objects between Realms
        showStatus("Copy objects between Realms");
        showStatus("Number of pigs on the farm : " + farmRealm.where(Pig.class).count());
        Pig defaultPig = defaultRealm.where(Pig.class).findFirst();
        farmRealm.beginTransaction();
        farmRealm.copyToRealm(defaultPig);
        farmRealm.commitTransaction();
        showStatus("Number of pigs on the farm : " + farmRealm.where(Pig.class).count());

        // Each realm is restricted to only accept their configured classes
        showStatus("Trying to add an unsupported class");
        defaultRealm.beginTransaction();
        try {
            defaultRealm.createObject(Elephant.class);
        } catch (RealmException expected) {
            showStatus("This throws a :" + expected.toString());
        } finally {
            defaultRealm.cancelTransaction();
        }

        // And Realms in library projects are independent from Realms in the app code
        showStatus("Interacting with library code that uses Realm internally");
        int animals = 5;
        Zoo libraryZoo = new Zoo(this);
        libraryZoo.open();
        showStatus("Adding animals: " + animals);
        libraryZoo.addAnimals(5);
        showStatus("Number of animals in the library Realm:" + libraryZoo.getNoOfAnimals());
        libraryZoo.close();

        // Remember to close all open Realms
        defaultRealm.close();
        farmRealm.close();
        exoticRealm.close();
    }

    private void showStatus(String txt) {
        Log.i(TAG, txt);
        TextView tv = new TextView(this);
        tv.setText(txt);
        rootLayout.addView(tv);
    }
}
