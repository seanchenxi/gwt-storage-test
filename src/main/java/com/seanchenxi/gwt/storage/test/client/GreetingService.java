package com.seanchenxi.gwt.storage.test.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.seanchenxi.gwt.storage.test.shared.TestValue;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
  TestValue greetServer(String name) throws IllegalArgumentException;
  TestValue testValue();
}
