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

package com.github.joumenharzli.shop.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.github.joumenharzli.shop.domain.Shop;
import com.github.joumenharzli.shop.service.ProductService;
import com.github.joumenharzli.shop.service.ShopService;
import com.github.joumenharzli.shop.service.dto.ProductSmallDTO;
import com.github.joumenharzli.shop.service.dto.ShopDTO;
import com.github.joumenharzli.shop.web.error.RestErrorDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * REST resource for the entity {@link Shop}
 *
 * @author Joumen Harzli
 */
@RestController
@RequestMapping("/api/v1")
public class ShopResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(ShopResource.class);

  private final ShopService shopService;
  private final ProductService productService;

  public ShopResource(ShopService shopService, ProductService productService) {
    this.shopService = shopService;
    this.productService = productService;
  }

  /**
   * GET  /shops : get all the shops without their products.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of the shops without their products
   */
  @ApiOperation(notes = "Returns all the found shops without their products.",
      value = "Get all shops and questions without their products",
      nickname = "getAllShops")
  @Timed
  @GetMapping("/shops")
  public List<ShopDTO> getAllShops() {
    LOGGER.debug("REST request to get all the shops without their products");
    return shopService.findAll();
  }

  /**
   * GET  /shops/:id/products : get all the products that belongs to the the specified shop.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of the products that belongs to the the specified shop
   * or with status 404 (Not Found) if the shop was not found
   */
  @ApiOperation(notes = "Returns all the found products that belongs to the the specified shop using the id of the shop.",
      value = "Get all the products that belongs to the the specified shop",
      nickname = "getAllProductsByShopId")
  @ApiResponses({
      @ApiResponse(code = 404, message = "Shop not found", response = RestErrorDto.class),
  })
  @Timed
  @GetMapping("/shops/{id}/products")
  public List<ProductSmallDTO> getAllProductsByShopId(@PathVariable("id") String shopId) {
    LOGGER.debug("REST request to get all the products that belongs to the shop having the id {}", shopId);
    return productService.findAllProductsByShopId(shopId);
  }

}
