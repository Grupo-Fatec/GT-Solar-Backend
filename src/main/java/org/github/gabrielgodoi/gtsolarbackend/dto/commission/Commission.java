package org.github.gabrielgodoi.gtsolarbackend.dto.commission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.github.gabrielgodoi.gtsolarbackend.enums.PaymentStatus;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Commission {
    private Double value;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDate;
}
