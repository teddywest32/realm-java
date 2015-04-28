
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

@RealmClass
public class AppCategory extends RealmObject {

  private String category;
  private String subCategory;
  private int installed;
  private int pinPosition = -1;
  private boolean hidden;

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getSubCategory() {
    return subCategory;
  }

  public void setSubCategory(String subCategory) {
    this.subCategory = subCategory;
  }

  public void setInstalled(int installed) {
    this.installed = installed;
  }

  public int getInstalled() {
    return installed;
  }

  public void setPinPosition(int pinPosition) {
    this.pinPosition = pinPosition;
  }

  public int getPinPosition() {
    return pinPosition;
  }

  public boolean isHidden() {
    return hidden;
  }

  public void setHidden(boolean hidden) {
    this.hidden = hidden;
  }
}
