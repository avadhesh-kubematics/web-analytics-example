/*
 * Copyright (C) 2018 Joumen Harzli
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 */

package com.github.joumenharzli.shop.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * ShopNotFoundException
 *
 * @author Joumen Harzli
 */
public class ShopNotFoundException extends RuntimeException {

  private long shopId;

  /**
   * Constructs a new runtime exception with the specified detail message.
   * The cause is not initialized, and may subsequently be initialized by a
   * call to {@link #initCause}.
   *
   * @param message the detail message. The detail message is saved for
   *                later retrieval by the {@link #getMessage()} method.
   */
  public ShopNotFoundException(String message) {
    super(message);
  }

  public long getShopId() {
    return shopId;
  }

  public void setShopId(long shopId) {
    this.shopId = shopId;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("message", super.toString())
        .append("shopId", shopId)
        .toString();
  }
}
