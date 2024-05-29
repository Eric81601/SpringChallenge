package com.streamlinity.ct.restService.challenge;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streamlinity.ct.model.Item;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Provide your implementation of the SearchSvcImpl here.
 * Also annotate your methods with Rest end point wrappers as required in the problem statement
 *
 * You can create any auxiliary classes or interfaces in this package if you need them.
 *
 * Do NOT add annotations as a Bean or Component or Service.   This is being handled in the custom Config class
 * PriceAdjustConfiguration
 */

public class SearchSvcImpl implements SearchSvcInterface {
    private List<Item> itemList = new ArrayList<>();
    private String fileName = "itemPrices.json";

    @Override
    public void init(String itemPriceJsonFileName) {

    }

    @Override
    public void init(File itemPriceJsonFile) {
        fileName = itemPriceJsonFile.getName();
    }

    @Override
    public List<Item> getItems() {
        readJSON(fileName);
        return itemList;
    }

    @Override
    public List<Item> getItems(String category) {
        readJSON(fileName);
        return itemList.stream().
                filter(item -> item.getCategory_short_name().equals(category)).
                collect(Collectors.toList());
    }

    @Override
    public List<Item> getItem(String itemShortName) {
        readJSON(fileName);
        return itemList.stream().
                filter(item -> item.getShort_name().equals(itemShortName)).
                collect(Collectors.toList());
    }

    private void readJSON(String fileName) {
        try {
            ClassPathResource resource = new ClassPathResource(fileName);
            InputStream inputStream = resource.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            itemList = mapper.readValue(inputStream, new TypeReference<List<Item>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
