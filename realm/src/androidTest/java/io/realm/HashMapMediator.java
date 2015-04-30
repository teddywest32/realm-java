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


import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.entities.AllTypes;
import io.realm.entities.AllTypesPrimaryKey;
import io.realm.entities.AnnotationNameConventions;
import io.realm.entities.AnnotationTypes;
import io.realm.entities.Cat;
import io.realm.entities.CyclicType;
import io.realm.entities.CyclicTypePrimaryKey;
import io.realm.entities.Dog;
import io.realm.entities.DogPrimaryKey;
import io.realm.entities.FieldOrder;
import io.realm.entities.IOSAllTypes;
import io.realm.entities.IOSChild;
import io.realm.entities.NonLatinFieldNames;
import io.realm.entities.Owner;
import io.realm.entities.OwnerPrimaryKey;
import io.realm.entities.PrimaryKeyAsLong;
import io.realm.entities.PrimaryKeyAsString;
import io.realm.entities.PrimaryKeyMix;
import io.realm.entities.StringOnly;
import io.realm.entities.Thread;
import io.realm.exceptions.RealmException;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Table;

class HashMapMediator implements RealmProxyMediator {

    private static final List<Class<? extends RealmObject>> MODEL_CLASSES;
    static {
        List<Class<? extends RealmObject>> modelClasses = new ArrayList<Class<? extends RealmObject>>();
        modelClasses.add(AllTypes.class);
        modelClasses.add(NonLatinFieldNames.class);
        modelClasses.add(Owner.class);
        modelClasses.add(Dog.class);
        modelClasses.add(IOSAllTypes.class);
        modelClasses.add(PrimaryKeyAsLong.class);
        modelClasses.add(CyclicType.class);
        modelClasses.add(AllTypesPrimaryKey.class);
        modelClasses.add(StringOnly.class);
        modelClasses.add(FieldOrder.class);
        modelClasses.add(AnnotationTypes.class);
        modelClasses.add(IOSChild.class);
        modelClasses.add(PrimaryKeyMix.class);
        modelClasses.add(DogPrimaryKey.class);
        modelClasses.add(OwnerPrimaryKey.class);
        modelClasses.add(CyclicTypePrimaryKey.class);
        modelClasses.add(PrimaryKeyAsString.class);
        modelClasses.add(Thread.class);
        modelClasses.add(AnnotationNameConventions.class);
        modelClasses.add(Cat.class);
        MODEL_CLASSES = Collections.unmodifiableList(modelClasses);
    }

    private static final Map<Class<? extends RealmObject>, GetTableNameFunc> tableNameFuncs = new HashMap<Class<? extends RealmObject>, GetTableNameFunc>();
    static {
        tableNameFuncs.put(AllTypes.class, new GetTableNameFunc() {
            @Override
            public String call() {
                return AllTypesRealmProxy.getTableName();
            }
        });
        tableNameFuncs.put(NonLatinFieldNames.class, new GetTableNameFunc() {
            public String call() {
                return AllTypesRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(Owner.class, new GetTableNameFunc() {
            public String call() {
                return OwnerRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(Dog.class, new GetTableNameFunc() {
            public String call() {
                return DogRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(IOSAllTypes.class, new GetTableNameFunc() {
            public String call() {
                return IOSAllTypesRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(PrimaryKeyAsLong.class, new GetTableNameFunc() {
            public String call() {
                return PrimaryKeyAsLongRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(CyclicType.class, new GetTableNameFunc() {
            public String call() {
                return CyclicTypeRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(AllTypesPrimaryKey.class, new GetTableNameFunc() {
            public String call() {
                return AllTypesPrimaryKeyRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(StringOnly.class, new GetTableNameFunc() {
            public String call() {
                return StringOnlyRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(FieldOrder.class, new GetTableNameFunc() {
            public String call() {
                return FieldOrderRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(AnnotationTypes.class, new GetTableNameFunc() {
            public String call() {
                return AnnotationTypesRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(IOSChild.class, new GetTableNameFunc() {
            public String call() {
                return IOSChildRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(PrimaryKeyMix.class, new GetTableNameFunc() {
            public String call() {
                return PrimaryKeyMixRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(DogPrimaryKey.class, new GetTableNameFunc() {
            public String call() {
                return DogPrimaryKeyRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(OwnerPrimaryKey.class, new GetTableNameFunc() {
            public String call() {
                return OwnerPrimaryKeyRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(CyclicTypePrimaryKey.class, new GetTableNameFunc() {
            public String call() {
                return CyclicTypePrimaryKeyRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(PrimaryKeyAsString.class, new GetTableNameFunc() {
            public String call() {
                return PrimaryKeyAsStringRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(Thread.class, new GetTableNameFunc() {
            public String call() {
                return ThreadRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(AnnotationNameConventions.class, new GetTableNameFunc() {
            public String call() {
                return AnnotationNameConventionsRealmProxy.getTableName();
            }
        });;
        tableNameFuncs.put(Cat.class, new GetTableNameFunc() {  public String call() { return CatRealmProxy.getTableName();  }});;
    }

    private static final Map<Class<? extends RealmObject>, FieldNameFunc> fieldNamesFunc = new HashMap<Class<? extends RealmObject>, FieldNameFunc>();
    static {
        tableNameFuncs.put(AllTypes.class, new GetTableNameFunc() {
            @Override
            public String call() {
                return AllTypesRealmProxy.getTableName();
            }
        });
        fieldNamesFunc.put(NonLatinFieldNames.class, new FieldNameFunc() {  public List<String> call() { return AllTypesRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(Owner.class, new FieldNameFunc() {  public List<String> call() { return OwnerRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(Dog.class, new FieldNameFunc() {  public List<String> call() { return DogRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(IOSAllTypes.class, new FieldNameFunc() {  public List<String> call() { return IOSAllTypesRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(PrimaryKeyAsLong.class, new FieldNameFunc() {  public List<String> call() { return PrimaryKeyAsLongRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(CyclicType.class, new FieldNameFunc() {  public List<String> call() { return CyclicTypeRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(AllTypesPrimaryKey.class, new FieldNameFunc() {  public List<String> call() { return AllTypesPrimaryKeyRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(StringOnly.class, new FieldNameFunc() {  public List<String> call() { return StringOnlyRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(FieldOrder.class, new FieldNameFunc() {  public List<String> call() { return FieldOrderRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(AnnotationTypes.class, new FieldNameFunc() {  public List<String> call() { return AnnotationTypesRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(IOSChild.class, new FieldNameFunc() {  public List<String> call() { return IOSChildRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(PrimaryKeyMix.class, new FieldNameFunc() {  public List<String> call() { return PrimaryKeyMixRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(DogPrimaryKey.class, new FieldNameFunc() {  public List<String> call() { return DogPrimaryKeyRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(OwnerPrimaryKey.class, new FieldNameFunc() {  public List<String> call() { return OwnerPrimaryKeyRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(CyclicTypePrimaryKey.class, new FieldNameFunc() {  public List<String> call() { return CyclicTypePrimaryKeyRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(PrimaryKeyAsString.class, new FieldNameFunc() {  public List<String> call() { return PrimaryKeyAsStringRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(Thread.class, new FieldNameFunc() {  public List<String> call() { return ThreadRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(AnnotationNameConventions.class, new FieldNameFunc() {  public List<String> call() { return AnnotationNameConventionsRealmProxy.getFieldNames();  }});;
        fieldNamesFunc.put(Cat.class, new FieldNameFunc() {  public List<String> call() { return CatRealmProxy.getFieldNames();  }});;
    }


    @Override
    public Table createTable(Class<? extends RealmObject> clazz, ImplicitTransaction transaction) {
        if (clazz == null) throw new NullPointerException("A class extending RealmObject must be provided");

        if (clazz.equals(AllTypes.class)) {
            return AllTypesRealmProxy.initTable(transaction);
        } else if (clazz.equals(NonLatinFieldNames.class)) {
            return NonLatinFieldNamesRealmProxy.initTable(transaction);
        } else if (clazz.equals(Owner.class)) {
            return OwnerRealmProxy.initTable(transaction);
        } else if (clazz.equals(Dog.class)) {
            return DogRealmProxy.initTable(transaction);
        } else if (clazz.equals(IOSAllTypes.class)) {
            return IOSAllTypesRealmProxy.initTable(transaction);
        } else if (clazz.equals(PrimaryKeyAsLong.class)) {
            return PrimaryKeyAsLongRealmProxy.initTable(transaction);
        } else if (clazz.equals(CyclicType.class)) {
            return CyclicTypeRealmProxy.initTable(transaction);
        } else if (clazz.equals(AllTypesPrimaryKey.class)) {
            return AllTypesPrimaryKeyRealmProxy.initTable(transaction);
        } else if (clazz.equals(StringOnly.class)) {
            return StringOnlyRealmProxy.initTable(transaction);
        } else if (clazz.equals(FieldOrder.class)) {
            return FieldOrderRealmProxy.initTable(transaction);
        } else if (clazz.equals(AnnotationTypes.class)) {
            return AnnotationTypesRealmProxy.initTable(transaction);
        } else if (clazz.equals(IOSChild.class)) {
            return IOSChildRealmProxy.initTable(transaction);
        } else if (clazz.equals(PrimaryKeyMix.class)) {
            return PrimaryKeyMixRealmProxy.initTable(transaction);
        } else if (clazz.equals(DogPrimaryKey.class)) {
            return DogPrimaryKeyRealmProxy.initTable(transaction);
        } else if (clazz.equals(OwnerPrimaryKey.class)) {
            return OwnerPrimaryKeyRealmProxy.initTable(transaction);
        } else if (clazz.equals(CyclicTypePrimaryKey.class)) {
            return CyclicTypePrimaryKeyRealmProxy.initTable(transaction);
        } else if (clazz.equals(PrimaryKeyAsString.class)) {
            return PrimaryKeyAsStringRealmProxy.initTable(transaction);
        } else if (clazz.equals(Thread.class)) {
            return ThreadRealmProxy.initTable(transaction);
        } else if (clazz.equals(AnnotationNameConventions.class)) {
            return AnnotationNameConventionsRealmProxy.initTable(transaction);
        } else if (clazz.equals(Cat.class)) {
            return CatRealmProxy.initTable(transaction);
        } else {
            throw new RealmException("Could not find the generated proxy class for " + clazz + ". " + RealmProxyMediator.APT_NOT_EXECUTED_MESSAGE);
        }
    }

    @Override
    public void validateTable(Class<? extends RealmObject> clazz, ImplicitTransaction transaction) {
        if (clazz == null) throw new NullPointerException("A class extending RealmObject must be provided");

        if (clazz.equals(AllTypes.class)) {
            AllTypesRealmProxy.validateTable(transaction);
        } else if (clazz.equals(NonLatinFieldNames.class)) {
            NonLatinFieldNamesRealmProxy.validateTable(transaction);
        } else if (clazz.equals(Owner.class)) {
            OwnerRealmProxy.validateTable(transaction);
        } else if (clazz.equals(Dog.class)) {
            DogRealmProxy.validateTable(transaction);
        } else if (clazz.equals(IOSAllTypes.class)) {
            IOSAllTypesRealmProxy.validateTable(transaction);
        } else if (clazz.equals(PrimaryKeyAsLong.class)) {
            PrimaryKeyAsLongRealmProxy.validateTable(transaction);
        } else if (clazz.equals(CyclicType.class)) {
            CyclicTypeRealmProxy.validateTable(transaction);
        } else if (clazz.equals(AllTypesPrimaryKey.class)) {
            AllTypesPrimaryKeyRealmProxy.validateTable(transaction);
        } else if (clazz.equals(StringOnly.class)) {
            StringOnlyRealmProxy.validateTable(transaction);
        } else if (clazz.equals(FieldOrder.class)) {
            FieldOrderRealmProxy.validateTable(transaction);
        } else if (clazz.equals(AnnotationTypes.class)) {
            AnnotationTypesRealmProxy.validateTable(transaction);
        } else if (clazz.equals(IOSChild.class)) {
            IOSChildRealmProxy.validateTable(transaction);
        } else if (clazz.equals(PrimaryKeyMix.class)) {
            PrimaryKeyMixRealmProxy.validateTable(transaction);
        } else if (clazz.equals(DogPrimaryKey.class)) {
            DogPrimaryKeyRealmProxy.validateTable(transaction);
        } else if (clazz.equals(OwnerPrimaryKey.class)) {
            OwnerPrimaryKeyRealmProxy.validateTable(transaction);
        } else if (clazz.equals(CyclicTypePrimaryKey.class)) {
            CyclicTypePrimaryKeyRealmProxy.validateTable(transaction);
        } else if (clazz.equals(PrimaryKeyAsString.class)) {
            PrimaryKeyAsStringRealmProxy.validateTable(transaction);
        } else if (clazz.equals(Thread.class)) {
            ThreadRealmProxy.validateTable(transaction);
        } else if (clazz.equals(AnnotationNameConventions.class)) {
            AnnotationNameConventionsRealmProxy.validateTable(transaction);
        } else if (clazz.equals(Cat.class)) {
            CatRealmProxy.validateTable(transaction);
        } else {
            throw new RealmException("Could not find the generated proxy class for " + clazz + ". " + RealmProxyMediator.APT_NOT_EXECUTED_MESSAGE);
        }
    }

    @Override
    public List<String> getFieldNames(Class<? extends RealmObject> clazz) {
        if (clazz == null) throw new NullPointerException("A class extending RealmObject must be provided");

        FieldNameFunc func = fieldNamesFunc.get(clazz);
        if (func != null) {
            return func.call();
        } else {
            throw new RealmException("Could not find the generated proxy class for " + clazz + ". " + RealmProxyMediator.APT_NOT_EXECUTED_MESSAGE);
        }
    }

    @Override
    public String getTableName(Class<? extends RealmObject> clazz) {
        if (clazz == null) throw new NullPointerException("A class extending RealmObject must be provided");

        GetTableNameFunc func = tableNameFuncs.get(clazz);
        if (func != null) {
            return func.call();
        } else {
            throw new RealmException("Could not find the generated proxy class for " + clazz + ". " + RealmProxyMediator.APT_NOT_EXECUTED_MESSAGE);
        }
    }

    @Override
    public <E extends RealmObject> E newInstance(Class<E> clazz) {
        if (clazz == null) throw new NullPointerException("A class extending RealmObject must be provided");

        if (clazz.equals(AllTypes.class)) {
            return (E) new AllTypesRealmProxy();
        } else if (clazz.equals(NonLatinFieldNames.class)) {
            return (E) new NonLatinFieldNamesRealmProxy();
        } else if (clazz.equals(Owner.class)) {
            return (E) new OwnerRealmProxy();
        } else if (clazz.equals(Dog.class)) {
            return (E) new DogRealmProxy();
        } else if (clazz.equals(IOSAllTypes.class)) {
            return (E) new IOSAllTypesRealmProxy();
        } else if (clazz.equals(PrimaryKeyAsLong.class)) {
            return (E) new PrimaryKeyAsLongRealmProxy();
        } else if (clazz.equals(CyclicType.class)) {
            return (E) new CyclicTypeRealmProxy();
        } else if (clazz.equals(AllTypesPrimaryKey.class)) {
            return (E) new AllTypesPrimaryKeyRealmProxy();
        } else if (clazz.equals(StringOnly.class)) {
            return (E) new StringOnlyRealmProxy();
        } else if (clazz.equals(FieldOrder.class)) {
            return (E) new FieldOrderRealmProxy();
        } else if (clazz.equals(AnnotationTypes.class)) {
            return (E) new AnnotationTypesRealmProxy();
        } else if (clazz.equals(IOSChild.class)) {
            return (E) new IOSChildRealmProxy();
        } else if (clazz.equals(PrimaryKeyMix.class)) {
            return (E) new PrimaryKeyMixRealmProxy();
        } else if (clazz.equals(DogPrimaryKey.class)) {
            return (E) new DogPrimaryKeyRealmProxy();
        } else if (clazz.equals(OwnerPrimaryKey.class)) {
            return (E) new OwnerPrimaryKeyRealmProxy();
        } else if (clazz.equals(CyclicTypePrimaryKey.class)) {
            return (E) new CyclicTypePrimaryKeyRealmProxy();
        } else if (clazz.equals(PrimaryKeyAsString.class)) {
            return (E) new PrimaryKeyAsStringRealmProxy();
        } else if (clazz.equals(Thread.class)) {
            return (E) new ThreadRealmProxy();
        } else if (clazz.equals(AnnotationNameConventions.class)) {
            return (E) new AnnotationNameConventionsRealmProxy();
        } else if (clazz.equals(Cat.class)) {
            return (E) new CatRealmProxy();
        } else {
            throw new RealmException("Could not find the generated proxy class for " + clazz + ". " + RealmProxyMediator.APT_NOT_EXECUTED_MESSAGE);
        }
    }

    @Override
    public List<Class<? extends RealmObject>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public Map<String, Long> getColumnIndices(Class<? extends RealmObject> clazz) {
        if (clazz.equals(AllTypes.class)) {
            return AllTypesRealmProxy.getColumnIndices();
        } else if (clazz.equals(NonLatinFieldNames.class)) {
            return NonLatinFieldNamesRealmProxy.getColumnIndices();
        } else if (clazz.equals(Owner.class)) {
            return OwnerRealmProxy.getColumnIndices();
        } else if (clazz.equals(Dog.class)) {
            return DogRealmProxy.getColumnIndices();
        } else if (clazz.equals(IOSAllTypes.class)) {
            return IOSAllTypesRealmProxy.getColumnIndices();
        } else if (clazz.equals(PrimaryKeyAsLong.class)) {
            return PrimaryKeyAsLongRealmProxy.getColumnIndices();
        } else if (clazz.equals(CyclicType.class)) {
            return CyclicTypeRealmProxy.getColumnIndices();
        } else if (clazz.equals(AllTypesPrimaryKey.class)) {
            return AllTypesPrimaryKeyRealmProxy.getColumnIndices();
        } else if (clazz.equals(StringOnly.class)) {
            return StringOnlyRealmProxy.getColumnIndices();
        } else if (clazz.equals(FieldOrder.class)) {
            return FieldOrderRealmProxy.getColumnIndices();
        } else if (clazz.equals(AnnotationTypes.class)) {
            return AnnotationTypesRealmProxy.getColumnIndices();
        } else if (clazz.equals(IOSChild.class)) {
            return IOSChildRealmProxy.getColumnIndices();
        } else if (clazz.equals(PrimaryKeyMix.class)) {
            return PrimaryKeyMixRealmProxy.getColumnIndices();
        } else if (clazz.equals(DogPrimaryKey.class)) {
            return DogPrimaryKeyRealmProxy.getColumnIndices();
        } else if (clazz.equals(OwnerPrimaryKey.class)) {
            return OwnerPrimaryKeyRealmProxy.getColumnIndices();
        } else if (clazz.equals(CyclicTypePrimaryKey.class)) {
            return CyclicTypePrimaryKeyRealmProxy.getColumnIndices();
        } else if (clazz.equals(PrimaryKeyAsString.class)) {
            return PrimaryKeyAsStringRealmProxy.getColumnIndices();
        } else if (clazz.equals(Thread.class)) {
            return ThreadRealmProxy.getColumnIndices();
        } else if (clazz.equals(AnnotationNameConventions.class)) {
            return AnnotationNameConventionsRealmProxy.getColumnIndices();
        } else if (clazz.equals(Cat.class)) {
            return CatRealmProxy.getColumnIndices();
        } else {
            throw new RealmException("Could not find the generated proxy class for " + clazz + ". " + RealmProxyMediator.APT_NOT_EXECUTED_MESSAGE);
        }
    }

    @Override
    public <E extends RealmObject> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmObject, RealmObjectProxy> cache) {
        Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(AllTypes.class)) {
            return (E) AllTypesRealmProxy.copyOrUpdate(realm, (AllTypes) obj, update, cache);
        } else if (clazz.equals(NonLatinFieldNames.class)) {
            return (E) NonLatinFieldNamesRealmProxy.copyOrUpdate(realm, (NonLatinFieldNames) obj, update, cache);
        } else if (clazz.equals(Owner.class)) {
            return (E) OwnerRealmProxy.copyOrUpdate(realm, (Owner) obj, update, cache);
        } else if (clazz.equals(Dog.class)) {
            return (E) DogRealmProxy.copyOrUpdate(realm, (Dog) obj, update, cache);
        } else if (clazz.equals(IOSAllTypes.class)) {
            return (E) IOSAllTypesRealmProxy.copyOrUpdate(realm, (IOSAllTypes) obj, update, cache);
        } else if (clazz.equals(PrimaryKeyAsLong.class)) {
            return (E) PrimaryKeyAsLongRealmProxy.copyOrUpdate(realm, (PrimaryKeyAsLong) obj, update, cache);
        } else if (clazz.equals(CyclicType.class)) {
            return (E) CyclicTypeRealmProxy.copyOrUpdate(realm, (CyclicType) obj, update, cache);
        } else if (clazz.equals(AllTypesPrimaryKey.class)) {
            return (E) AllTypesPrimaryKeyRealmProxy.copyOrUpdate(realm, (AllTypesPrimaryKey) obj, update, cache);
        } else if (clazz.equals(StringOnly.class)) {
            return (E) StringOnlyRealmProxy.copyOrUpdate(realm, (StringOnly) obj, update, cache);
        } else if (clazz.equals(FieldOrder.class)) {
            return (E) FieldOrderRealmProxy.copyOrUpdate(realm, (FieldOrder) obj, update, cache);
        } else if (clazz.equals(AnnotationTypes.class)) {
            return (E) AnnotationTypesRealmProxy.copyOrUpdate(realm, (AnnotationTypes) obj, update, cache);
        } else if (clazz.equals(IOSChild.class)) {
            return (E) IOSChildRealmProxy.copyOrUpdate(realm, (IOSChild) obj, update, cache);
        } else if (clazz.equals(PrimaryKeyMix.class)) {
            return (E) PrimaryKeyMixRealmProxy.copyOrUpdate(realm, (PrimaryKeyMix) obj, update, cache);
        } else if (clazz.equals(DogPrimaryKey.class)) {
            return (E) DogPrimaryKeyRealmProxy.copyOrUpdate(realm, (DogPrimaryKey) obj, update, cache);
        } else if (clazz.equals(OwnerPrimaryKey.class)) {
            return (E) OwnerPrimaryKeyRealmProxy.copyOrUpdate(realm, (OwnerPrimaryKey) obj, update, cache);
        } else if (clazz.equals(CyclicTypePrimaryKey.class)) {
            return (E) CyclicTypePrimaryKeyRealmProxy.copyOrUpdate(realm, (CyclicTypePrimaryKey) obj, update, cache);
        } else if (clazz.equals(PrimaryKeyAsString.class)) {
            return (E) PrimaryKeyAsStringRealmProxy.copyOrUpdate(realm, (PrimaryKeyAsString) obj, update, cache);
        } else if (clazz.equals(Thread.class)) {
            return (E) ThreadRealmProxy.copyOrUpdate(realm, (Thread) obj, update, cache);
        } else if (clazz.equals(AnnotationNameConventions.class)) {
            return (E) AnnotationNameConventionsRealmProxy.copyOrUpdate(realm, (AnnotationNameConventions) obj, update, cache);
        } else if (clazz.equals(Cat.class)) {
            return (E) CatRealmProxy.copyOrUpdate(realm, (Cat) obj, update, cache);
        } else {
            throw new RealmException("Could not find the generated proxy class for " + clazz + ". " + RealmProxyMediator.APT_NOT_EXECUTED_MESSAGE);
        }
    }

    @Override
    public <E extends RealmObject> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        if (clazz == null) throw new NullPointerException("A class extending RealmObject must be provided");

        if (clazz.equals(AllTypes.class)) {
            return (E) AllTypesRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(NonLatinFieldNames.class)) {
            return (E) NonLatinFieldNamesRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(Owner.class)) {
            return (E) OwnerRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(Dog.class)) {
            return (E) DogRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(IOSAllTypes.class)) {
            return (E) IOSAllTypesRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(PrimaryKeyAsLong.class)) {
            return (E) PrimaryKeyAsLongRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(CyclicType.class)) {
            return (E) CyclicTypeRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(AllTypesPrimaryKey.class)) {
            return (E) AllTypesPrimaryKeyRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(StringOnly.class)) {
            return (E) StringOnlyRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(FieldOrder.class)) {
            return (E) FieldOrderRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(AnnotationTypes.class)) {
            return (E) AnnotationTypesRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(IOSChild.class)) {
            return (E) IOSChildRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(PrimaryKeyMix.class)) {
            return (E) PrimaryKeyMixRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(DogPrimaryKey.class)) {
            return (E) DogPrimaryKeyRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(OwnerPrimaryKey.class)) {
            return (E) OwnerPrimaryKeyRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(CyclicTypePrimaryKey.class)) {
            return (E) CyclicTypePrimaryKeyRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(PrimaryKeyAsString.class)) {
            return (E) PrimaryKeyAsStringRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(Thread.class)) {
            return (E) ThreadRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(AnnotationNameConventions.class)) {
            return (E) AnnotationNameConventionsRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else if (clazz.equals(Cat.class)) {
            return (E) CatRealmProxy.createOrUpdateUsingJsonObject(realm, json, update);
        } else {
            throw new RealmException("Could not find the generated proxy class for " + clazz + ". " + RealmProxyMediator.APT_NOT_EXECUTED_MESSAGE);
        }
    }

    @Override
    public <E extends RealmObject> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        if (clazz == null) throw new NullPointerException("A class extending RealmObject must be provided");

        if (clazz.equals(AllTypes.class)) {
            return (E) AllTypesRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(NonLatinFieldNames.class)) {
            return (E) NonLatinFieldNamesRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(Owner.class)) {
            return (E) OwnerRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(Dog.class)) {
            return (E) DogRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(IOSAllTypes.class)) {
            return (E) IOSAllTypesRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(PrimaryKeyAsLong.class)) {
            return (E) PrimaryKeyAsLongRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(CyclicType.class)) {
            return (E) CyclicTypeRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(AllTypesPrimaryKey.class)) {
            return (E) AllTypesPrimaryKeyRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(StringOnly.class)) {
            return (E) StringOnlyRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(FieldOrder.class)) {
            return (E) FieldOrderRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(AnnotationTypes.class)) {
            return (E) AnnotationTypesRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(IOSChild.class)) {
            return (E) IOSChildRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(PrimaryKeyMix.class)) {
            return (E) PrimaryKeyMixRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(DogPrimaryKey.class)) {
            return (E) DogPrimaryKeyRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(OwnerPrimaryKey.class)) {
            return (E) OwnerPrimaryKeyRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(CyclicTypePrimaryKey.class)) {
            return (E) CyclicTypePrimaryKeyRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(PrimaryKeyAsString.class)) {
            return (E) PrimaryKeyAsStringRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(Thread.class)) {
            return (E) ThreadRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(AnnotationNameConventions.class)) {
            return (E) AnnotationNameConventionsRealmProxy.createUsingJsonStream(realm, reader);
        } else if (clazz.equals(Cat.class)) {
            return (E) CatRealmProxy.createUsingJsonStream(realm, reader);
        } else {
            throw new RealmException("Could not find the generated proxy class for " + clazz + ". " + RealmProxyMediator.APT_NOT_EXECUTED_MESSAGE);
        }
    }

    private interface GetTableNameFunc {
        public String call();
    }

    private interface FieldNameFunc {
        public List<String> call();
    }
}
