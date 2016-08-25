/*
 * Copyright 2016 the original author or authors.
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

package com.devoxx.watson;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Realizes a REST service endpoint that, given an inquiry such as a question, returns a response from the Devoxx
 * corpus and conversation logic.
 *
 * @author James Weaver
 */
@RestController
@RequestMapping("/inquiry")
public class AskDevoxxController {
  private Log log = LogFactory.getLog(getClass());

  private final AskDevoxxProperties askDevoxxProperties;

  @Autowired
  public AskDevoxxController(AskDevoxxProperties askDevoxxProperties) {
    this.askDevoxxProperties = askDevoxxProperties;
  }

  /**
   * Example endpoint usage is inquiry?text=Java modularity&context=abc123
   *
   * @param inquiryText
   * @param lang
   * @return Response to the client
   */
  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> inquiry(@RequestParam(value = "text") String inquiryText,
                                       @RequestParam(value = "context", defaultValue="") String context) {

    log.info("Receive request, inquiryText: " + inquiryText + ", context: " + context);

    InquiryResponseNear inquiryResponseNear = callDevoxxWatsonServices(inquiryText);



    return Optional.ofNullable(inquiryResponseNear)
        .map(cr -> new ResponseEntity<>((Object)cr, HttpStatus.OK))
        .orElse(new ResponseEntity<>("AskDevoxx inquiry request unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR));
  }

  /**
   * Calls the appropriate Watson services to get an answer to the client's inquiry
   * @param inquiryText
   * @return An answer to the client's inquiry
   */
  private InquiryResponseNear callDevoxxWatsonServices(String inquiryText) {

    InquiryResponseNear inquiryResponseNear = new InquiryResponseNear();

    inquiryResponseNear.setInquiryText(inquiryText);
    inquiryResponseNear.setResponseText("Here is a example response");
    inquiryResponseNear.setContext("example-context");

    return inquiryResponseNear;
  }
}
