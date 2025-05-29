package cl.duoc.ms_sales_db.model.entities;


import java.time.LocalDateTime;
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
@Table(name="sales_orders")
@Getter
@Setter 
@ToString
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "customer_id")
    private Long customerId;
    @Column(name="orderDate")
    private LocalDateTime orderDate;
    @Column(name="totalAmount")
    private double totalAmount;
    
}




