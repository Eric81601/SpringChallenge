package com.streamlinity.ct.restService.challenge;

import com.streamlinity.ct.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * This controller needs to expose the following rest endpoints.  You need to fill in the implementation here
 *
 * Required REST Endpoints
 *
 *      /item                       Get all items
 *      /item?category=C            Get all items in category specified by Category shortName
 *      /item/{itemShortName}       Get item that matches the specified Item shortName
 */

@Profile("default")
@RestController
public class SearchRestControllerImpl {

    @Autowired
    private SearchSvcInterface searchSvcInterface;

    @GetMapping("/item")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = searchSvcInterface.getItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping(value = "/item", params = "category")
    public ResponseEntity<List<Item>> getItemsByCategoryShortName(@RequestParam String category) {
        List<Item> items = searchSvcInterface.getItems(category);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/item/{itemShortName}")
    public ResponseEntity<List<Item>> getItemsByItemShortName(@PathVariable("itemShortName") String itemShortName) {
        List<Item> items = searchSvcInterface.getItem(itemShortName);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
