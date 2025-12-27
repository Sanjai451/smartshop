package com.smartshop.SmartShop.service;

import com.smartshop.SmartShop.ProductRepository;
import com.smartshop.SmartShop.dto.GeminiFunctionCall;
import com.smartshop.SmartShop.dto.GeminiFunctionResponse;
import com.smartshop.SmartShop.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSearchService {

    private final GeminiRouterService gemini;
    private final ProductRepository repo;

    public ProductSearchService(GeminiRouterService gemini,
                                ProductRepository repo) {
        this.gemini = gemini;
        this.repo = repo;
    }

    public List<Product> search(String query) throws Exception {

        GeminiFunctionResponse call = gemini.route(query);

        System.out.println(call.getFunctionName());

        return switch (call.getFunctionName()) {

            case "searchByKeyword" ->
                repo.searchByKeyword(
                        (String) call.getKeyword()
                );
            case "searchByKeywordAndMaxPrice" ->
                    repo.searchByKeywordAndMaxPrice(
                            (String) call.getKeyword(),
                            Double.valueOf(call.getMaxprice().toString())
                    );

            case "searchByCategory" ->
                    repo.searchByCategory(
                            (String) call.getCategory()
                    );

            case "searchByPriceRange" ->
                    repo.searchByPriceRange(
                            Double.valueOf(call.getMinprice().toString()),
                            Double.valueOf(call.getMaxprice().toString())
                    );

            default -> repo.findAll();
        };
    }
}
