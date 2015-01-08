package com.seanchenxi.gwt.storage.test.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.seanchenxi.gwt.storage.test.shared.TestValue;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
  void greetServer(String input, AsyncCallback<String> callback)
      throws IllegalArgumentException;

    void testValue(AsyncCallback<TestValue> async);
}
