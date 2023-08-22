package by.teachmeskills.sneakersshopwebserviceexam.dto.complex_wrappwer_dto;

import by.teachmeskills.sneakersshopwebserviceexam.dto.basic_dto.CartDto;
import by.teachmeskills.sneakersshopwebserviceexam.dto.basic_dto.OrderDto;
import by.teachmeskills.sneakersshopwebserviceexam.dto.basic_dto.UserDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutRequestResponseWrapperDto {

    @NotNull(message = "Order field in checkoutRequestResponseWrapperDto is null")
    private OrderDto order;

    @NotNull(message = "Cart field in checkoutRequestResponseWrapperDto is null")
    private CartDto cart;

    @NotNull(message = "User field in checkoutRequestResponseWrapperDto is null")
    private UserDto user;
}
