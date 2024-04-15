package org.filmrental.com.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DatewiseCummulativeRevenue(LocalDate paymentDate,BigDecimal revenue) {

}
