package com.seanchenxi.gwt.storage.test.client;

import com.seanchenxi.gwt.storage.client.StorageKey;
import com.seanchenxi.gwt.storage.client.StorageKeyProvider;
import com.seanchenxi.gwt.storage.test.shared.TestValue;

/**
 * Created by Xi on 2015/1/8.
 */
public interface TestKeyProvider extends StorageKeyProvider {

    StorageKey<TestValue> fvKey();

}
