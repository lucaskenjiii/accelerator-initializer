/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.hopper.controller;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.hopper.config.InitializerConfig;
import com.hopper.config.ValidationConfig;
import com.hopper.initializer.FileProcessor;
import com.hopper.initializer.ProjectCreationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import javax.servlet.http.HttpServletRequest;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ComponentController.class, secure = false)
@Import({InitializerConfig.class, ValidationConfig.class})
public class ComponentControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProjectCreationService projectCreationService;

    @MockBean
    private FileProcessor fileProcessor;

    @Mock
    HttpServletRequest httpServletRequest;

    @Test
    public void whenDownloadRequestBodyIsInvalidExpect400() throws Exception {
        this.mvc.perform(post("/api/projects/components/download")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"projectKey\" : \"\", \"type\" : \"JAVA_SPRING_BOOT\", \"name\": \"$INVALID_ONE\"}")
                )
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errors[*].field").value(hasItems("name", "projectKey")))
        .andExpect(jsonPath("$.errors[*].message").value(hasItems("Name must start with a lower-case letter or number and may contain hyphens, underscores and periods")));
    }

    @Test
    public void whenDownloadBodyIsOkThenExpect201() throws Exception {
        this.mvc.perform(post("/api/projects/components/download")
		                .contentType(MediaType.APPLICATION_JSON_UTF8)
		                .content("{\"projectKey\" : \"HOPPER\", \"type\" : \"JAVA_SPRING_BOOT\", \"name\": \"hopper-intake\"}")
		                )
		.andDo(print())
		.andExpect(status().is2xxSuccessful());
    }
}
