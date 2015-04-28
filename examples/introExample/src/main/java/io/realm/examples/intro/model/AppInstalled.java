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
import io.realm.annotations.Index;
import io.realm.annotations.RealmClass;

/**
 * Created by eboudrant on 1/8/15.
 */
@RealmClass
public class AppInstalled extends RealmObject {

  @Index
  private String packageId;
  @Index
  private String activityName;

  private AppCategory category;
  private int dominantColor;
  private boolean iconPack;
  private int versionCode;
  private int appLaunch;
  private int appLaunchNight;
  private int appLaunchMorning;
  private int appLaunchDay;
  private int appLaunchEvening;
  private String name;
  private int color;
  private boolean system;
  private boolean systemUpdated;
  private boolean hidden;
  private boolean updated;
  private boolean added;
  private boolean pinned;
  private int pinPosition;
  private String alternateName;
  private String tags;
  private String customIcon;
  private String imagePath;
  private int index;
  private int status;

  public String getPackageId() {
    return packageId;
  }

  public void setPackageId(String packageId) {
    this.packageId = packageId;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public String getImagePath() {
    return imagePath;
  }

  public AppCategory getCategory() {
    return category;
  }

  public void setCategory(AppCategory category) {
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String mName) {
    this.name = mName;
  }

  public int getDominantColor() {
    return dominantColor;
  }

  public void setDominantColor(int dominantColor) {
    this.dominantColor = dominantColor;
  }

  public boolean isIconPack() {
    return iconPack;
  }

  public void setIconPack(boolean iconPack) {
    this.iconPack = iconPack;
  }

  public int getVersionCode() {
    return versionCode;
  }

  public void setVersionCode(int versionCode) {
    this.versionCode = versionCode;
  }

  public String getActivityName() {
    return activityName;
  }

  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }

  public int getAppLaunch() {
    return appLaunch;
  }

  public void setAppLaunch(int appLaunch) {
    this.appLaunch = appLaunch;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public boolean isSystem() {
    return system;
  }

  public void setSystem(boolean system) {
    this.system = system;
  }

  public boolean isSystemUpdated() {
    return systemUpdated;
  }

  public void setSystemUpdated(boolean systemUpdated) {
    this.systemUpdated = systemUpdated;
  }

  public boolean isHidden() {
    return hidden;
  }

  public void setHidden(boolean hidden) {
    this.hidden = hidden;
  }

  public boolean isUpdated() {
    return updated;
  }

  public void setUpdated(boolean updated) {
    this.updated = updated;
  }

  public boolean isAdded() {
    return added;
  }

  public void setAdded(boolean added) {
    this.added = added;
  }

  public boolean isPinned() {
    return pinned;
  }

  public void setPinned(boolean pinned) {
    this.pinned = pinned;
  }

  public int getPinPosition() {
    return pinPosition;
  }

  public void setPinPosition(int pinPosition) {
    this.pinPosition = pinPosition;
  }

  public String getAlternateName() {
    return alternateName;
  }

  public void setAlternateName(String alternateName) {
    this.alternateName = alternateName;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getCustomIcon() {
    return customIcon;
  }

  public void setCustomIcon(String customIcon) {
    this.customIcon = customIcon;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public int getAppLaunchEvening() {
    return appLaunchEvening;
  }

  public void setAppLaunchEvening(int appLaunchEvening) {
    this.appLaunchEvening = appLaunchEvening;
  }

  public int getAppLaunchMorning() {
    return appLaunchMorning;
  }

  public void setAppLaunchMorning(int appLaunchMorning) {
    this.appLaunchMorning = appLaunchMorning;
  }

  public int getAppLaunchNight() {
    return appLaunchNight;
  }

  public void setAppLaunchNight(int appLaunchNight) {
    this.appLaunchNight = appLaunchNight;
  }

  public int getAppLaunchDay() {
    return appLaunchDay;
  }

  public void setAppLaunchDay(int appLaunchDay) {
    this.appLaunchDay = appLaunchDay;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
