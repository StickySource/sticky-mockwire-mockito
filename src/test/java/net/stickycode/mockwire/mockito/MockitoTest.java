package net.stickycode.mockwire.mockito;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import net.stickycode.bootstrap.StickyBootstrap;
import net.stickycode.mockwire.MockwireMockerBridge;

/**
 * Copyright (c) 2010 RedEngine Ltd, http://www.redengine.co.nz. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */

public class MockitoTest {

  @Test(expected=NullPointerException.class)
  public void initaliseNull() {
    MockwireMockerBridge.bridge().initialise(null, getClass());
  }

  @Test
  public void initalise() {
    MockwireMockerBridge.bridge().initialise(Mockito.mock(StickyBootstrap.class), getClass());
  }

  @Test(expected=NullPointerException.class)
  public void fail() {
    StickyBootstrap bootstrap = Mockito.mock(StickyBootstrap.class);
    MockwireMockerBridge bridge = MockwireMockerBridge.bridge();
    bridge.initialise(bootstrap, getClass());
    bridge.process("b", null, null,null);
    verify(bootstrap).registerSingleton(null, null, null);
  }

  @Test
  public void wire() {
    StickyBootstrap bootstrap = Mockito.mock(StickyBootstrap.class);
    MockwireMockerBridge bridge = MockwireMockerBridge.bridge();
    bridge.initialise(bootstrap, getClass());
    Object o = new Object();
    bridge.process("b", o, null,Object.class);
    verify(bootstrap).registerSingleton(Matchers.eq("b"), Matchers.anyObject(), Matchers.eq(Object.class));
  }
}
