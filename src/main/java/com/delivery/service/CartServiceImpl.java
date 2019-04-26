package com.delivery.service;

import com.delivery.domain.Order;
import com.delivery.domain.Product;
import com.delivery.domain.ProductInOrder;
import com.delivery.domain.User;
import com.delivery.domain.dto.ProductDto;
import com.delivery.enums.ProductStatusEnum;
import com.delivery.enums.ResultEnum;
import com.delivery.exception.MyException;
import com.delivery.form.ItemForm;
import com.delivery.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService {
    @Autowired
    ProductService productService;
    @Autowired
    OrderRepo orderRepo;

    private Map<Long, ProductDto> map = new LinkedHashMap<>();

    @Override
    public void addItem(ItemForm itemForm) {
        Long id = Long.parseLong(itemForm.getProductId());
        Product product = productService.findById(id);

        if (product.getProductStatus() == ProductStatusEnum.DOWN.getCode()) {
            throw new MyException(ResultEnum.PRODUCT_OFF_SALE);
        }

        // Check whether is in the cart
        if(map.containsKey(id)){
            // Update quantity
            Integer old = map.get(id).getQuantity();
            itemForm.setQuantity(old + itemForm.getQuantity());
        }

        map.put(id, new ProductDto(product, itemForm.getQuantity()));
    }

    @Override
    public void removeItem(Long productId) {
        if (!map.containsKey(productId)) throw new MyException(ResultEnum.PRODUCT_NOT_IN_CART);
        map.remove(productId);
    }

    @Override
    public void updateQuantity(Long productId, Integer quantity) {
        if (!map.containsKey(productId)) throw new MyException(ResultEnum.PRODUCT_NOT_IN_CART);
        ProductDto item = map.get(productId);
        Integer max = item.getQuantityInWarehouse();
        if(quantity > 0) {
            item.setQuantity(quantity > max ? max : quantity);
        }
    }

    @Override
    public Collection<ProductDto> findAll() {
        return map.values();
    }

    @Override
    @Transactional
    public void checkout(User user, String address) {
        Order order = new Order(address, 0, user);
        for (Long productId : map.keySet()) {
            ProductDto productDto = map.get(productId);
            ProductInOrder productInOrder = new ProductInOrder(productDto.buildProduct(),
                    productDto.getQuantity());
            productInOrder.setOrder(order);
            order.getProductsInOrder().add(productInOrder);
            productService.decreaseQuantityInWarehouse(productId, productDto.getQuantity());
        }
        order.setOrderAmount(getTotal());
        orderRepo.save(order);
        map.clear();
    }

    @Override
    public BigDecimal getTotal() {
        Collection<ProductDto> productDtos = findAll();
        BigDecimal total = new BigDecimal(0);
        for (ProductDto productDto : productDtos) {
            BigDecimal price = productDto.getPrice();
            BigDecimal quantity = new BigDecimal(productDto.getQuantity());
            total = total.add(price.multiply(quantity));
        }
        return total;
    }
}
