package com.shop.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private String buyer;
    private String address;
    private String phone;
    private String email;
    private Integer methodPayment;
}
