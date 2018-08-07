/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.hopper.initializer.creator.common;

import com.hopper.initializer.creator.FileCreator;
import com.hopper.initializer.creator.annotation.Node;
import com.hopper.initializer.creator.annotation.React;
import com.hopper.initializer.model.ProjectCreation;
import org.springframework.stereotype.Component;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@React
@Node
public class TestFolderCreator implements FileCreator<ProjectCreation> {

    public static final String TEST_PATH = "test";

    @Override
    public void create(ProjectCreation request) {
        log.info("Creating test folder for project type {}", request.getType());
        Paths.get(request.getRootDir(), TEST_PATH)
             .toFile()
             .mkdir();
    }
    
}