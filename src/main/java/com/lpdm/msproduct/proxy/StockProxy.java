package com.lpdm.msproduct.proxy;

import com.lpdm.msproduct.entity.Stock;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name = "zuul-server", url = "https://zuul.lpdm.kybox.fr")
@RibbonClient(name = "ms-stock")
public interface StockProxy {

    @RequestMapping(value = "/ms-stock/stocks/{id}")
    Stock findById(@PathVariable(value = "id") int id);

    @RequestMapping(value = "/ms-stock/stocks")
    List<Stock> listStock();

    @RequestMapping(value = "/ms-stock/stocks")
    Stock addStock(@RequestBody Stock stock);

    @RequestMapping(value="/ms-stock/stocks/{id}")
    void deleteStock(@PathVariable(value = "id") int id);

    @RequestMapping(value="/ms-stock/stocks")
    Stock updateStock(@RequestBody Stock stock);

    @RequestMapping(value = "/ms-stock/stocks/productor/{id}")
    List<Stock> listStockByProductor(@PathVariable(value = "id") int id);
}
