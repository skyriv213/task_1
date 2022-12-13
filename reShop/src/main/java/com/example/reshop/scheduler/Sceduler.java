package com.example.reshop.scheduler;

import com.example.reshop.dtos.ItemDto;
import com.example.reshop.entity.Product;
import com.example.reshop.repository.ProductRepository;
import com.example.reshop.service.NaverApiService;
import com.example.reshop.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class Sceduler {

    private final NaverApiService naverApiService;
    private final ProductServiceImpl productService;
    private final ProductRepository productRepository;

    @Scheduled(cron = "0 0 1 * * *")
    public void updatePrice() throws InterruptedException {
        log.info("가격 업데이트 실행");
        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            TimeUnit.SECONDS.sleep(1);
            String title = product.getTitle();
            List<ItemDto> itemDtoList = naverApiService.searchItems(title);
            ItemDto itemDto = itemDtoList.get(0);

            Long id = product.getId();
            productService.updatedBySearch(id, itemDto);

        }
    }

}
