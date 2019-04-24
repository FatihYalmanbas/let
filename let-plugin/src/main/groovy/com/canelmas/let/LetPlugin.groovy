/*
 * Copyright (C) 2017 Can Elmas
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.canelmas.let

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class LetPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        makeSureProjectIsAndroidAppOrLib(project)

        project.android.registerTransform(new LetTransform(project))

        project.dependencies {
            implementation 'com.canelmas.let:let-runtime:1.0.0-beta1'
            implementation 'com.canelmas.let:let-annotations:1.0.0-beta1'
        }
    }

    private void makeSureProjectIsAndroidAppOrLib(Project project) {
        def hasApp = project.plugins.withType(AppPlugin)
        def hasLib = project.plugins.withType(LibraryPlugin)

        if (!hasApp && !hasLib) {
            throw new IllegalStateException("'android' or 'android-library' plugin required.")
        }
    }

}