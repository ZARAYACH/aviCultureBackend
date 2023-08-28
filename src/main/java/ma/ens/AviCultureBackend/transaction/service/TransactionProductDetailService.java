package ma.ens.AviCultureBackend.transaction.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.modal.Product;
import ma.ens.AviCultureBackend.product.service.ProductService;
import ma.ens.AviCultureBackend.transaction.model.Transaction;
import ma.ens.AviCultureBackend.transaction.model.TransactionProductDetail;
import ma.ens.AviCultureBackend.transaction.model.dto.TransactionProductDetailDto;
import ma.ens.AviCultureBackend.transaction.repository.TransactionProductDetailRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionProductDetailService {

    private final TransactionProductDetailRepo transactionProductDetailRepo;
    private final ProductService productService;

    public TransactionProductDetail getTransactionProductDetailsById(String id) throws NotFoundException {
        return transactionProductDetailRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction Product Detail with id " + id + " not Found"));
    }

    public List<TransactionProductDetail> getAllTransactionProductDetails() throws NotFoundException {
        return transactionProductDetailRepo.findAll();
    }

    public TransactionProductDetail addTransactionProductDetailWithBuilding(TransactionProductDetailDto transactionProductDetailDto) throws NotFoundException {
        Assert.notNull(transactionProductDetailDto, "Provided TransactionProductDetail dto can't be null");
        Product product = productService.getProductById(transactionProductDetailDto.productId());
        return transactionProductDetailRepo.save(TransactionProductDetail.builder()
                .product(product)
                .expirationDate(transactionProductDetailDto.expirationDate())
                .quantity(transactionProductDetailDto.quantity())
                .build());
    }

    public TransactionProductDetail modifyTransactionProductDetail(TransactionProductDetail transactionProductDetail, TransactionProductDetailDto transactionProductDetailDto) throws NotFoundException {
        Assert.notNull(transactionProductDetail, "Provided TransactionProductDetail can't be null");
        Assert.notNull(transactionProductDetailDto, "Provided TransactionProductDetail dto can't be null");
        Product product = productService.getProductById(transactionProductDetailDto.productId());
        transactionProductDetail.setProduct(product);
        transactionProductDetail.setExpirationDate(transactionProductDetailDto.expirationDate());
        transactionProductDetail.setQuantity(transactionProductDetailDto.quantity());
        return transactionProductDetailRepo.save(transactionProductDetail);

    }

    public List<TransactionProductDetail> saveTransactionProductsDetails(List<TransactionProductDetail> transactionProductDetails) {
        Assert.notNull(transactionProductDetails, "provided transactionProductDetail can't be null");
        return transactionProductDetailRepo.saveAll(transactionProductDetails);
    }

    public void deleteTransactionProductDetail(TransactionProductDetail transactionProductDetail) throws NotFoundException {
        Assert.notNull(transactionProductDetail, "transactionProductDetail can't be null");
        transactionProductDetailRepo.delete(transactionProductDetail);
    }

    public void deleteTransactionProductsDetailsByTransaction(Transaction transaction) throws NotFoundException {
        Assert.notNull(transaction, "transaction can't be null");
        List<TransactionProductDetail> transactionProductDetails = getTransactionProductDetailsByTransaction(transaction);
        transactionProductDetailRepo.deleteAll(transactionProductDetails);
    }

    private List<TransactionProductDetail> getTransactionProductDetailsByTransaction(Transaction transaction) {
        return transactionProductDetailRepo.findAllByTransaction(transaction);
    }
}
