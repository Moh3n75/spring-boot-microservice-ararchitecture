package ir.fardup.product.product.orm;

import com.fardup.msutility.entity.GenericEntity;
import ir.fardup.product.category.orm.Category;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "PRODUCT")
@Data
public class Product extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private BigDecimal price;

    private Integer quantity;

    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}
