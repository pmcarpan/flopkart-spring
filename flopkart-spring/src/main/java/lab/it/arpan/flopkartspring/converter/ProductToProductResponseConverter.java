package lab.it.arpan.flopkartspring.converter;

import lab.it.arpan.flopkartspring.model.Product;
import lab.it.arpan.flopkartspring.model.response.ProductResponse;

public class ProductToProductResponseConverter implements Converter<Product, ProductResponse> {

    @Override
    public ProductResponse convert(Product source) {
        double reducedPrice = source.getPrice() * (1 - source.getDiscountPercentage() / 100.0);

        return ProductResponse.of(source.getId(), source.getName(), source.getDescription(), source.getCategory(),
                source.getIsNewArrival(), source.getPrice(), source.getDiscountPercentage(), reducedPrice,
                source.getImageUrl(), source.getFootnote());
    }

}
