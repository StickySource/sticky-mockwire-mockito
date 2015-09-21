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
package net.stickycode.mockwire.mockito;

import java.lang.reflect.Field;
import java.util.Objects;

import org.mockito.Mockito;

import net.stickycode.bootstrap.StickyBootstrap;
import net.stickycode.mockwire.MockwireMockerBridge;

public class MockitoMockwireMockerBridge
    implements MockwireMockerBridge {

  private StickyBootstrap bootstrap;

  @Override
  public void initialise(StickyBootstrap bootstrap, Class<?> metadata) {
    this.bootstrap = Objects.requireNonNull(bootstrap);
  }

  @Override
  public void process(String name, Object target, Field field, Class<?> type) {
    bootstrap.registerSingleton(name, Mockito.mock(type, name), type);
  }

}
