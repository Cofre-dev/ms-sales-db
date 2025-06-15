package cl.duoc.ms_sales_db.model.dto;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SalesDTO {

    @JsonProperty(value = "venta_id")
    private Long   id;
    @JsonProperty(value = "venta_monto")
    private Long   amount; 
    //private String salesDate;
    @JsonProperty(value = "venta_cliente_id")
    private Long   customerId;

    @JsonProperty(value = "venta_detalle")
    private List<SalesDetailDTO> salesDetailDtoList;
}
