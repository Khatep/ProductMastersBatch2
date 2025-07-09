package kz.kop_flowers.repository;

import kz.kop_flowers.model.dto.FlowerDto;
import kz.kop_flowers.model.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Integer> {

    /*
    @Query("""
        SELECT new kz.kop_flowers.dto.FlowerDto(f.id, f.name, f.price, f.size, f.category.name)
        FROM Flower f
        WHERE f.category.id = :categoryId
    """)
    * */
    List<Flower> findAllByCategory_Id(Integer categoryId);

}

