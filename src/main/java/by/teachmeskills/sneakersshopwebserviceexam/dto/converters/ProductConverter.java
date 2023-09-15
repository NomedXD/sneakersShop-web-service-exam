package by.teachmeskills.sneakersshopwebserviceexam.dto.converters;


import by.teachmeskills.sneakersshopwebserviceexam.domain.Product;
import by.teachmeskills.sneakersshopwebserviceexam.dto.basic_dto.ImageDto;
import by.teachmeskills.sneakersshopwebserviceexam.dto.basic_dto.ProductDto;
import by.teachmeskills.sneakersshopwebserviceexam.exception.NoSuchOrderException;
import by.teachmeskills.sneakersshopwebserviceexam.repositories.CategoryRepository;
import by.teachmeskills.sneakersshopwebserviceexam.services.ImageService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Data
public class ProductConverter {
    private final CategoryRepository categoryRepository;
    private final ImageConverter imageConverter;
    private final ImageService imageService;

    @Autowired
    public ProductConverter(CategoryRepository categoryRepository, ImageConverter imageConverter, ImageService imageService) {
        this.categoryRepository = categoryRepository;
        this.imageConverter = imageConverter;
        this.imageService = imageService;
    }

    public ProductDto toDto(Product product) {
        return Optional.ofNullable(product).map(p -> ProductDto.builder()
                .id(p.getId())
                .name(p.getName()).imageDtoList(p.getImages().stream().map(imageConverter::toDto).toList())
                .description(p.getDescription())
                .price(p.getPrice())
                .categoryId(p.getCategory().getId())
                .build()).orElse(null);
    }

    public Product fromDto(ProductDto productDto) {
        return Optional.ofNullable(productDto).map(p -> Product.builder()
                .id(p.getId())
                .name(p.getName()).images(productDto.getImageDtoList().stream().map(ImageDto::getId).map(imageService::getImageById).map(imageConverter::fromDto).toList())
                .description(p.getDescription())
                .price(p.getPrice())
                .category(categoryRepository.findCategoryById(p.getCategoryId()).orElseThrow((() -> new NoSuchOrderException("Category not found. Id:", p.getCategoryId()))))
                .build()).orElse(null);
    }
}
