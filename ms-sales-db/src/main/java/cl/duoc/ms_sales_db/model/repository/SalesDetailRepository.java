package cl.duoc.ms_sales_db.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.duoc.ms_sales_db.model.entities.Sales;


@Repository
public interface SalesDetailRepository extends JpaRepository<Sales, Long>{
    @Query(value = "SELECT dt.id as id, dt.product_id as product_id, "+
                   "      dt.quantity as quantity, dt.sales_id as sales_id "+
                   "FROM sales_detail dt "+
                   "WHERE dt.sales_id = ?1", nativeQuery = true)

    public List<Sales> findBySalesId(Long salesId);
}