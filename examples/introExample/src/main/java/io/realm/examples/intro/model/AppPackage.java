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

package io.realm.examples.intro.model;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by eboudrant on 1/7/15.
 */
@RealmClass
public class AppPackage extends RealmObject {

  private String packageId;
  private AppCategory category;

  public String getPackageId() {
    return packageId;
  }

  public void setPackageId(String packageId) {
    this.packageId = packageId;
  }

  public AppCategory getCategory() {
    return category;
  }

  public void setCategory(AppCategory category) {
    this.category = category;
  }

}
