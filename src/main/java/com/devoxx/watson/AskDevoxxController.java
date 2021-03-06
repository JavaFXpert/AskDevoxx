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

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
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

import java.util.HashMap;
import java.util.Map;
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
   * @param context
   * @return Response to the client
   */
  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> inquiry(@RequestParam(value = "text") String inquiryText,
                                       @RequestParam(value = "context", defaultValue="") String context) {

    log.info("Receive request, inquiryText: " + inquiryText + ", context: " + context);

    InquiryResponseNear inquiryResponseNear = callDevoxxWatsonServices(inquiryText, context);



    return Optional.ofNullable(inquiryResponseNear)
        .map(cr -> new ResponseEntity<>((Object)cr, HttpStatus.OK))
        .orElse(new ResponseEntity<>("AskDevoxx inquiry request unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR));
  }

  /**
   * Calls the appropriate Watson services to get an answer to the client's inquiry
   * @param inquiryText
   * @return An answer to the client's inquiry
   */
  private InquiryResponseNear callDevoxxWatsonServices(String inquiryText, String context) {

    //TODO: Move these
    final String WORKSPACE_ID = "e1faa444-789c-40f6-bc6a-34fd765450a9";
    final String USERNAME = "76df0216-0b65-4b2a-a16a-f5ee57f455be";
    final String PASSWORD = "3EcINlm1GczO";

    final String URL = "https://gateway.watsonplatform.net/conversation/api";

    InquiryResponseNear inquiryResponseNear = new InquiryResponseNear();

    Map<String, Object> contextMap = new HashMap<>();
    contextMap.put("conversation_id", context);

    MessageRequest request = new MessageRequest.Builder()
        .inputText(inquiryText)
        .context(context.length() > 1 ? contextMap : null)
        .build();

    ConversationService service =
        new ConversationService(ConversationService.VERSION_DATE_2016_07_11);
    if (USERNAME != null || PASSWORD != null) {
      service.setUsernameAndPassword(USERNAME, PASSWORD);
    }
    if (URL != null) {
      service.setEndPoint(URL);
    }

    // Use the previously configured service object to make a call to the conversational service
    MessageResponse response = service.message(WORKSPACE_ID, request).execute();


    inquiryResponseNear.setInquiryText(inquiryText);

    inquiryResponseNear.setResponseText(response.getText().toString());

    inquiryResponseNear.setContext(response.getContext().get("conversation_id").toString());

    return inquiryResponseNear;
  }
}
