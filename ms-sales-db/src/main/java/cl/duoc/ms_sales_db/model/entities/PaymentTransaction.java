package cl.duoc.ms_sales_db.model.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name="sales_payment_transactions")
public class PaymentTransaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private long order_id;
    @Column(unique = true)
    private String transactionId;
    @Column(name="amount")
    private double amount;
    @Column(name="currency")
    private String currency;
    @Column(name = "payment_method")
    private String paymentMethod;

}
