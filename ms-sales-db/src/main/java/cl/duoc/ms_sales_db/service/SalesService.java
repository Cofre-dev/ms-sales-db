package cl.duoc.ms_sales_db.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.ms_sales_db.model.dto.ProductDTO;
import cl.duoc.ms_sales_db.model.dto.SalesDTO;
import cl.duoc.ms_sales_db.model.dto.SalesDetailDTO;
import cl.duoc.ms_sales_db.model.entities.Sales;
import cl.duoc.ms_sales_db.model.entities.SalesDetail;
import cl.duoc.ms_sales_db.model.repository.SalesDetailRepository;
import cl.duoc.ms_sales_db.model.repository.SalesRepository;

@Service
public class SalesService {

    @Autowired
    SalesRepository salesRepository;

    @Autowired
    SalesDetailRepository salesDetailRepository;

    public SalesDTO findSalesById(Long id){
        Optional<Sales> sales = salesRepository.findById(id);       
        //Validamos si existe o no
        SalesDTO salesDto = null;

        if(sales.isPresent()){
            salesDto = translateEntityToDto(sales.get());

            List<SalesDetail> salesDetailList = salesDetailRepository.findBySalesId(sales.get().getId());
            salesDto.setSalesDetailDtoList(translateListEntityToDto(salesDetailList));
        } 
        return salesDto;
    }

    public SalesDTO createSale(SalesDTO salesDTO){
        Sales sales = translateDtoToEntity(salesDTO);
        Sales newSales = salesRepository.save(sales);

        for(SalesDetailDTO salesDetailDTO: salesDTO.getSalesDetailDtoList()){
            SalesDetail salesDetail = new SalesDetail();
            salesDetail.setProductId(salesDetailDTO.getProduct().getId());
            salesDetail.setQuantity(salesDetailDTO.getQuantity());
            salesDetail.setSalesId(newSales.getId());
            salesDetailRepository.save(salesDetail);
        }

        SalesDTO newSalesDTO = null;

        if(newSales != null){
            newSalesDTO = translateEntityToDto(newSales);

            List<SalesDetail> salesDetailList = salesDetailRepository.findBySalesId(newSales.getId());
            newSalesDTO.setSalesDetailDtoList(translateListEntityToDto(salesDetailList));
        }
        return newSalesDTO;
    }

    public Sales translateDtoToEntity(SalesDTO salesDTO){
        Sales sales = new Sales();
        sales.setAmount(salesDTO.getAmount());
        sales.setCustomerId(salesDTO.getCustomerId());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sales.setSalesDate(format.format(new Date()));
        return sales;
    }



    public SalesDTO translateEntityToDto(Sales sale){
        SalesDTO salesDto = new SalesDTO();
        salesDto.setId(sale.getId());
        salesDto.setAmount(sale.getAmount());
        //salesDto.setSalesDate(sale.getSalesDate());
        salesDto.setCustomerId(sale.getCustomerId());
        return salesDto;
    }

    public List<SalesDetailDTO> translateListEntityToDto(List<SalesDetail> saleDetail){
        List<SalesDetailDTO> lista = new ArrayList<>();
        SalesDetailDTO salesDetailDTO = null;
        for(SalesDetail detail: saleDetail){
            salesDetailDTO = new SalesDetailDTO();
            salesDetailDTO.setId(detail.getId());
            
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(detail.getProductId());
            salesDetailDTO.setProduct(productDTO);

            salesDetailDTO.setQuantity(detail.getQuantity());
            salesDetailDTO.setSalesId(detail.getSalesId());
            lista.add(salesDetailDTO);
        }
        return lista;
    }

}
