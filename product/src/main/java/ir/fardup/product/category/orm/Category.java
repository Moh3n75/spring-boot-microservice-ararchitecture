package ir.fardup.product.category.orm;

import com.fardup.msutility.entity.GenericEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class Category extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
}
