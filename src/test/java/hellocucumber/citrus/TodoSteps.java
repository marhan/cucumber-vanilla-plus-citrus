/*
 * Copyright 2006-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hellocucumber.citrus;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.dsl.design.TestDesigner;
import com.consol.citrus.dsl.runner.TestRunner;
import com.consol.citrus.message.MessageType;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TodoSteps {

    /** Test designer filled with actions by step definitions */
    @CitrusResource
    private TestRunner runner;

    @Given("^Todo list is empty$")
    public void empty_todos() {
        runner.http(http -> http
                .client("todoListClient")
                .send()
                .delete("/api/todolist"));

        runner.http(http -> http
                .client("todoListClient")
                .receive()
                .response(HttpStatus.OK));
    }

    @When("^(?:I|user) adds? entry \"([^\"]*)\"$")
    public void add_entry(final String todoName) {
        runner.http(http -> http
                .client("todoListClient")
                .send()
                .post("/todolist")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .payload("title=" + todoName));

        runner.http(http -> http
                .client("todoListClient")
                .receive()
                .response(HttpStatus.FOUND));
    }

    @When("^(?:I|user) removes? entry \"([^\"]*)\"$")
    public void remove_entry(final String todoName) throws UnsupportedEncodingException {

        final String encodedTodoName = URLEncoder.encode(todoName, "UTF-8");
        runner.http(http -> http
                .client("todoListClient")
                .send()
                .delete("/api/todo?title=" + encodedTodoName));

        runner.http(http -> http
                .client("todoListClient")
                .receive()
                .response(HttpStatus.OK)
                .messageType(MessageType.PLAINTEXT));
    }

    @Then("^(?:the )?number of todo entries should be (\\d+)$")
    public void verify_todos(final int todoCnt) {
        runner.http(http -> http
                .client("todoListClient")
                .send()
                .get("/api/todolist/count"));

        runner.http(http -> http
                .client("todoListClient")
                .receive()
                .response(HttpStatus.OK)
                .messageType(MessageType.PLAINTEXT)
                .payload(String.valueOf(todoCnt)));
    }

    @Then("^(?:the )?todo list should be empty$")
    public void verify_empty_todos() {
        verify_todos(0);
    }

}
